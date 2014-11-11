package org.osmdroid.views.overlay;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import java.util.Iterator;
import java.util.LinkedList;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.LocationListenerProxy;
import org.osmdroid.ResourceProxy;
import org.osmdroid.ResourceProxy.bitmap;
import org.osmdroid.ResourceProxy.string;
import org.osmdroid.SensorEventListenerProxy;
import org.osmdroid.api.IMyLocationOverlay;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.LocationUtils;
import org.osmdroid.util.NetworkLocationIgnorer;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLocationOverlay extends Overlay
  implements IMyLocationOverlay, IOverlayMenuProvider, SensorEventListener, LocationListener, Overlay.Snappable
{
  public static final int MENU_COMPASS;
  public static final int MENU_MY_LOCATION;
  private static final Logger logger = LoggerFactory.getLogger(MyLocationOverlay.class);
  private final float COMPASS_FRAME_CENTER_X;
  private final float COMPASS_FRAME_CENTER_Y;
  private final float COMPASS_ROSE_CENTER_X;
  private final float COMPASS_ROSE_CENTER_Y;
  protected final Bitmap DIRECTION_ARROW;
  private final float DIRECTION_ARROW_CENTER_X;
  private final float DIRECTION_ARROW_CENTER_Y;
  protected final PointF PERSON_HOTSPOT;
  protected final Bitmap PERSON_ICON;
  private final Matrix directionRotater = new Matrix();
  private float mAzimuth = -1.0F;
  protected final Paint mCirclePaint = new Paint();
  private float mCompassCenterX = 35.0F;
  private float mCompassCenterY = 35.0F;
  protected final Picture mCompassFrame = new Picture();
  private final Matrix mCompassMatrix = new Matrix();
  private final float mCompassRadius = 20.0F;
  protected final Picture mCompassRose = new Picture();
  protected boolean mDrawAccuracyEnabled = true;
  protected boolean mFollow = false;
  private final GeoPoint mGeoPoint = new GeoPoint(0, 0);
  private final NetworkLocationIgnorer mIgnorer = new NetworkLocationIgnorer();
  private Location mLocation;
  public LocationListenerProxy mLocationListener = null;
  private final LocationManager mLocationManager;
  private float mLocationUpdateMinDistance = 0.0F;
  private long mLocationUpdateMinTime = 0L;
  private final MapController mMapController;
  private final Point mMapCoords = new Point();
  protected final MapView mMapView;
  private final Matrix mMatrix = new Matrix();
  private final float[] mMatrixValues = new float[9];
  private boolean mOptionsMenuEnabled = true;
  protected final Paint mPaint = new Paint();
  private final LinkedList<Runnable> mRunOnFirstFix = new LinkedList();
  public SensorEventListenerProxy mSensorListener = null;
  private final SensorManager mSensorManager;

  static
  {
    MENU_MY_LOCATION = getSafeMenuId();
    MENU_COMPASS = getSafeMenuId();
  }

  public MyLocationOverlay(Context paramContext, MapView paramMapView)
  {
    this(paramContext, paramMapView, new DefaultResourceProxyImpl(paramContext));
  }

  public MyLocationOverlay(Context paramContext, MapView paramMapView, ResourceProxy paramResourceProxy)
  {
    super(paramResourceProxy);
    this.mMapView = paramMapView;
    this.mLocationManager = ((LocationManager)paramContext.getSystemService("location"));
    this.mSensorManager = ((SensorManager)paramContext.getSystemService("sensor"));
    this.mMapController = paramMapView.getController();
    this.mCirclePaint.setARGB(0, 100, 100, 255);
    this.mCirclePaint.setAntiAlias(true);
    this.PERSON_ICON = this.mResourceProxy.getBitmap(ResourceProxy.bitmap.person);
    this.DIRECTION_ARROW = this.mResourceProxy.getBitmap(ResourceProxy.bitmap.direction_arrow);
    this.DIRECTION_ARROW_CENTER_X = (this.DIRECTION_ARROW.getWidth() / 2 - 0.5F);
    this.DIRECTION_ARROW_CENTER_Y = (this.DIRECTION_ARROW.getHeight() / 2 - 0.5F);
    this.PERSON_HOTSPOT = new PointF(0.5F + 24.0F * this.mScale, 0.5F + 39.0F * this.mScale);
    createCompassFramePicture();
    createCompassRosePicture();
    this.COMPASS_FRAME_CENTER_X = (this.mCompassFrame.getWidth() / 2 - 0.5F);
    this.COMPASS_FRAME_CENTER_Y = (this.mCompassFrame.getHeight() / 2 - 0.5F);
    this.COMPASS_ROSE_CENTER_X = (this.mCompassRose.getWidth() / 2 - 0.5F);
    this.COMPASS_ROSE_CENTER_Y = (this.mCompassRose.getHeight() / 2 - 0.5F);
  }

  private Point calculatePointOnCircle(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    double d = Math.toRadians(90.0F + -paramFloat4);
    int i = (int)(paramFloat3 * Math.cos(d));
    int j = (int)(paramFloat3 * Math.sin(d));
    return new Point(i + (int)paramFloat1, (int)paramFloat2 - j);
  }

  private void createCompassFramePicture()
  {
    Paint localPaint1 = new Paint();
    localPaint1.setColor(-1);
    localPaint1.setAntiAlias(true);
    localPaint1.setStyle(Paint.Style.FILL);
    localPaint1.setAlpha(200);
    Paint localPaint2 = new Paint();
    localPaint2.setColor(-7829368);
    localPaint2.setAntiAlias(true);
    localPaint2.setStyle(Paint.Style.STROKE);
    localPaint2.setStrokeWidth(2.0F);
    localPaint2.setAlpha(200);
    Canvas localCanvas = this.mCompassFrame.beginRecording(50, 50);
    localCanvas.drawCircle(25.0F, 25.0F, 20.0F * this.mScale, localPaint1);
    localCanvas.drawCircle(25.0F, 25.0F, 20.0F * this.mScale, localPaint2);
    drawTriangle(localCanvas, 25.0F, 25.0F, 20.0F * this.mScale, 0.0F, localPaint2);
    drawTriangle(localCanvas, 25.0F, 25.0F, 20.0F * this.mScale, 90.0F, localPaint2);
    drawTriangle(localCanvas, 25.0F, 25.0F, 20.0F * this.mScale, 180.0F, localPaint2);
    drawTriangle(localCanvas, 25.0F, 25.0F, 20.0F * this.mScale, 270.0F, localPaint2);
    this.mCompassFrame.endRecording();
  }

  private void createCompassRosePicture()
  {
    Paint localPaint1 = new Paint();
    localPaint1.setColor(-6291456);
    localPaint1.setAntiAlias(true);
    localPaint1.setStyle(Paint.Style.FILL);
    localPaint1.setAlpha(220);
    Paint localPaint2 = new Paint();
    localPaint2.setColor(-16777216);
    localPaint2.setAntiAlias(true);
    localPaint2.setStyle(Paint.Style.FILL);
    localPaint2.setAlpha(220);
    Paint localPaint3 = new Paint();
    localPaint3.setColor(-1);
    localPaint3.setAntiAlias(true);
    localPaint3.setStyle(Paint.Style.FILL);
    localPaint3.setAlpha(220);
    Canvas localCanvas = this.mCompassRose.beginRecording(50, 50);
    Path localPath1 = new Path();
    localPath1.moveTo(25.0F, 25.0F - 17.0F * this.mScale);
    localPath1.lineTo(25.0F + 4.0F * this.mScale, 25.0F);
    localPath1.lineTo(25.0F - 4.0F * this.mScale, 25.0F);
    localPath1.lineTo(25.0F, 25.0F - 17.0F * this.mScale);
    localPath1.close();
    localCanvas.drawPath(localPath1, localPaint1);
    Path localPath2 = new Path();
    localPath2.moveTo(25.0F, 25.0F + 17.0F * this.mScale);
    localPath2.lineTo(25.0F + 4.0F * this.mScale, 25.0F);
    localPath2.lineTo(25.0F - 4.0F * this.mScale, 25.0F);
    localPath2.lineTo(25.0F, 25.0F + 17.0F * this.mScale);
    localPath2.close();
    localCanvas.drawPath(localPath2, localPaint2);
    localCanvas.drawCircle(25.0F, 25.0F, 2.0F, localPaint3);
    this.mCompassRose.endRecording();
  }

  private void drawTriangle(Canvas paramCanvas, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, Paint paramPaint)
  {
    paramCanvas.save();
    Point localPoint = calculatePointOnCircle(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    paramCanvas.rotate(paramFloat4, localPoint.x, localPoint.y);
    Path localPath = new Path();
    localPath.moveTo(localPoint.x - 2.0F * this.mScale, localPoint.y);
    localPath.lineTo(localPoint.x + 2.0F * this.mScale, localPoint.y);
    localPath.lineTo(localPoint.x, localPoint.y - 5.0F * this.mScale);
    localPath.close();
    paramCanvas.drawPath(localPath, paramPaint);
    paramCanvas.restore();
  }

  public void disableCompass()
  {
    if (this.mSensorListener != null)
      this.mSensorListener.stopListening();
    this.mSensorListener = null;
    this.mAzimuth = -1.0F;
    if (this.mMapView != null)
      this.mMapView.postInvalidate();
  }

  public void disableFollowLocation()
  {
    this.mFollow = false;
  }

  public void disableMyLocation()
  {
    if (this.mLocationListener != null)
      this.mLocationListener.stopListening();
    this.mLocationListener = null;
    if (this.mMapView != null)
      this.mMapView.postInvalidate();
  }

  public void draw(Canvas paramCanvas, MapView paramMapView, boolean paramBoolean)
  {
    if (paramBoolean);
    label548: label680: 
    while (true)
    {
      return;
      int i;
      float f3;
      if (paramMapView.getResources().getConfiguration().orientation == 2)
      {
        i = -90;
        if ((this.mLocationListener != null) && (this.mLocation != null))
        {
          MapView.Projection localProjection = paramMapView.getProjection();
          this.mGeoPoint.setCoordsE6((int)(1000000.0D * this.mLocation.getLatitude()), (int)(1000000.0D * this.mLocation.getLongitude()));
          localProjection.toMapPixels(this.mGeoPoint, this.mMapCoords);
          if (this.mDrawAccuracyEnabled)
          {
            float f4 = localProjection.metersToEquatorPixels(this.mLocation.getAccuracy());
            this.mCirclePaint.setAlpha(50);
            this.mCirclePaint.setStyle(Paint.Style.FILL);
            paramCanvas.drawCircle(this.mMapCoords.x, this.mMapCoords.y, f4, this.mCirclePaint);
            this.mCirclePaint.setAlpha(150);
            this.mCirclePaint.setStyle(Paint.Style.STROKE);
            paramCanvas.drawCircle(this.mMapCoords.x, this.mMapCoords.y, f4, this.mCirclePaint);
          }
          paramCanvas.getMatrix(this.mMatrix);
          this.mMatrix.getValues(this.mMatrixValues);
          f3 = -1.0F;
          if ((!this.mLocation.getProvider().equals("gps")) || (this.mAzimuth < 0.0F))
            break label548;
          f3 = this.mAzimuth;
          label254: if (f3 < 0.0F)
            break label592;
          this.directionRotater.setRotate(-f3 + i, this.DIRECTION_ARROW_CENTER_X, this.DIRECTION_ARROW_CENTER_Y);
          this.directionRotater.postTranslate(-this.DIRECTION_ARROW_CENTER_X, -this.DIRECTION_ARROW_CENTER_Y);
          this.directionRotater.postScale(1.0F / this.mMatrixValues[0], 1.0F / this.mMatrixValues[4]);
          this.directionRotater.postTranslate(this.mMapCoords.x, this.mMapCoords.y);
          paramCanvas.drawBitmap(this.DIRECTION_ARROW, this.directionRotater, this.mPaint);
        }
      }
      while (true)
      {
        if ((!isCompassEnabled()) || (this.mAzimuth < 0.0F))
          break label680;
        float f1 = this.mCompassCenterX * this.mScale;
        float f2 = this.mCompassCenterY * this.mScale + (paramCanvas.getHeight() - this.mMapView.getHeight());
        this.mCompassMatrix.setTranslate(-this.COMPASS_FRAME_CENTER_X, -this.COMPASS_FRAME_CENTER_Y);
        this.mCompassMatrix.postTranslate(f1, f2);
        paramCanvas.save();
        paramCanvas.setMatrix(this.mCompassMatrix);
        paramCanvas.drawPicture(this.mCompassFrame);
        this.mCompassMatrix.setRotate(-this.mAzimuth + i, this.COMPASS_ROSE_CENTER_X, this.COMPASS_ROSE_CENTER_Y);
        this.mCompassMatrix.postTranslate(-this.COMPASS_ROSE_CENTER_X, -this.COMPASS_ROSE_CENTER_Y);
        this.mCompassMatrix.postTranslate(f1, f2);
        paramCanvas.setMatrix(this.mCompassMatrix);
        paramCanvas.drawPicture(this.mCompassRose);
        paramCanvas.restore();
        return;
        i = 0;
        break;
        if ((!this.mLocation.hasSpeed()) || (this.mLocation.getSpeed() <= 1.0F) || (!this.mLocation.hasBearing()))
          break label254;
        f3 = this.mLocation.getBearing();
        break label254;
        label592: this.directionRotater.setTranslate(-this.PERSON_HOTSPOT.x, -this.PERSON_HOTSPOT.y);
        this.directionRotater.postScale(1.0F / this.mMatrixValues[0], 1.0F / this.mMatrixValues[4]);
        this.directionRotater.postTranslate(this.mMapCoords.x, this.mMapCoords.y);
        paramCanvas.drawBitmap(this.PERSON_ICON, this.directionRotater, this.mPaint);
      }
    }
  }

  public boolean enableCompass()
  {
    boolean bool = true;
    if (this.mSensorListener == null)
    {
      this.mSensorListener = new SensorEventListenerProxy(this.mSensorManager);
      bool = this.mSensorListener.startListening(this, 3, 2);
    }
    if (this.mMapView != null)
      this.mMapView.postInvalidate();
    return bool;
  }

  public void enableFollowLocation()
  {
    this.mFollow = true;
    if (isMyLocationEnabled())
    {
      this.mLocation = LocationUtils.getLastKnownLocation(this.mLocationManager);
      if (this.mLocation != null)
        this.mMapController.animateTo(new GeoPoint(this.mLocation));
    }
    if (this.mMapView != null)
      this.mMapView.postInvalidate();
  }

  public boolean enableMyLocation()
  {
    boolean bool = true;
    if (this.mLocationListener == null)
    {
      this.mLocationListener = new LocationListenerProxy(this.mLocationManager);
      bool = this.mLocationListener.startListening(this, this.mLocationUpdateMinTime, this.mLocationUpdateMinDistance);
    }
    if (isFollowLocationEnabled())
    {
      this.mLocation = LocationUtils.getLastKnownLocation(this.mLocationManager);
      if (this.mLocation != null)
        this.mMapController.animateTo(new GeoPoint(this.mLocation));
    }
    if (this.mMapView != null)
      this.mMapView.postInvalidate();
    return bool;
  }

  @Deprecated
  public void followLocation(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      enableFollowLocation();
      return;
    }
    disableFollowLocation();
  }

  public Location getLastFix()
  {
    return this.mLocation;
  }

  public float getLocationUpdateMinDistance()
  {
    return this.mLocationUpdateMinDistance;
  }

  public long getLocationUpdateMinTime()
  {
    return this.mLocationUpdateMinTime;
  }

  public GeoPoint getMyLocation()
  {
    if (this.mLocation == null)
      return null;
    return new GeoPoint(this.mLocation);
  }

  public boolean isCompassEnabled()
  {
    return this.mSensorListener != null;
  }

  public boolean isDrawAccuracyEnabled()
  {
    return this.mDrawAccuracyEnabled;
  }

  public boolean isFollowLocationEnabled()
  {
    return this.mFollow;
  }

  public boolean isMyLocationEnabled()
  {
    return this.mLocationListener != null;
  }

  public boolean isOptionsMenuEnabled()
  {
    return this.mOptionsMenuEnabled;
  }

  public void onAccuracyChanged(Sensor paramSensor, int paramInt)
  {
  }

  public boolean onCreateOptionsMenu(Menu paramMenu, int paramInt, MapView paramMapView)
  {
    paramMenu.add(0, paramInt + MENU_MY_LOCATION, 0, this.mResourceProxy.getString(ResourceProxy.string.my_location)).setIcon(this.mResourceProxy.getDrawable(ResourceProxy.bitmap.ic_menu_mylocation));
    paramMenu.add(0, paramInt + MENU_COMPASS, 0, this.mResourceProxy.getString(ResourceProxy.string.compass)).setIcon(this.mResourceProxy.getDrawable(ResourceProxy.bitmap.ic_menu_compass));
    return true;
  }

  public void onLocationChanged(Location paramLocation)
  {
    if (this.mIgnorer.shouldIgnore(paramLocation.getProvider(), System.currentTimeMillis()))
    {
      logger.debug("Ignore temporary non-gps location");
      return;
    }
    this.mLocation = paramLocation;
    Iterator localIterator;
    if (this.mFollow)
    {
      this.mMapController.animateTo(new GeoPoint(paramLocation));
      localIterator = this.mRunOnFirstFix.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        this.mRunOnFirstFix.clear();
        return;
        this.mMapView.postInvalidate();
        break;
      }
      new Thread((Runnable)localIterator.next()).start();
    }
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem, int paramInt, MapView paramMapView)
  {
    int i = paramMenuItem.getItemId() - paramInt;
    if (i == MENU_MY_LOCATION)
    {
      if (isMyLocationEnabled())
      {
        disableFollowLocation();
        disableMyLocation();
        return true;
      }
      enableFollowLocation();
      enableMyLocation();
      return true;
    }
    if (i == MENU_COMPASS)
    {
      if (isCompassEnabled())
      {
        disableCompass();
        return true;
      }
      enableCompass();
      return true;
    }
    return false;
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu, int paramInt, MapView paramMapView)
  {
    return false;
  }

  public void onProviderDisabled(String paramString)
  {
  }

  public void onProviderEnabled(String paramString)
  {
  }

  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    if ((paramSensorEvent.sensor.getType() == 3) && (paramSensorEvent.values != null))
    {
      this.mAzimuth = paramSensorEvent.values[0];
      this.mMapView.postInvalidate();
    }
  }

  public boolean onSnapToItem(int paramInt1, int paramInt2, Point paramPoint, MapView paramMapView)
  {
    Location localLocation = this.mLocation;
    int i = 0;
    if (localLocation != null)
    {
      paramMapView.getProjection().toMapPixels(new GeoPoint(this.mLocation), this.mMapCoords);
      paramPoint.x = this.mMapCoords.x;
      paramPoint.y = this.mMapCoords.y;
      double d1 = paramInt1 - this.mMapCoords.x;
      double d2 = paramInt2 - this.mMapCoords.y;
      boolean bool = d1 * d1 + d2 * d2 < 64.0D;
      i = 0;
      if (bool)
        i = 1;
    }
    return i;
  }

  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    if (paramMotionEvent.getAction() == 2)
      disableFollowLocation();
    return super.onTouchEvent(paramMotionEvent, paramMapView);
  }

  public boolean runOnFirstFix(Runnable paramRunnable)
  {
    if ((this.mLocationListener != null) && (this.mLocation != null))
    {
      paramRunnable.run();
      return true;
    }
    this.mRunOnFirstFix.addLast(paramRunnable);
    return false;
  }

  public void setCompassCenter(float paramFloat1, float paramFloat2)
  {
    this.mCompassCenterX = paramFloat1;
    this.mCompassCenterY = paramFloat2;
  }

  public void setDrawAccuracyEnabled(boolean paramBoolean)
  {
    this.mDrawAccuracyEnabled = paramBoolean;
  }

  public void setLocationUpdateMinDistance(float paramFloat)
  {
    this.mLocationUpdateMinDistance = paramFloat;
  }

  public void setLocationUpdateMinTime(long paramLong)
  {
    this.mLocationUpdateMinTime = paramLong;
  }

  public void setOptionsMenuEnabled(boolean paramBoolean)
  {
    this.mOptionsMenuEnabled = paramBoolean;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.overlay.MyLocationOverlay
 * JD-Core Version:    0.6.0
 */