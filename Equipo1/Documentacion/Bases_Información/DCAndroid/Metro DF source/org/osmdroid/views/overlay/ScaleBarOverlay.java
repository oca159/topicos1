package org.osmdroid.views.overlay;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Picture;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Field;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.ResourceProxy.string;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.constants.GeoConstants;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;

public class ScaleBarOverlay extends Overlay
  implements GeoConstants
{
  private final Activity activity;
  private Paint barPaint;
  boolean imperial = false;
  private float lastLatitude = 0.0F;
  private int lastZoomLevel = -1;
  boolean latitudeBar = true;
  float lineWidth = 2.0F;
  boolean longitudeBar = false;
  private final Rect mBounds = new Rect();
  int minZoom = 0;
  boolean nautical = false;
  private MapView.Projection projection;
  private final ResourceProxy resourceProxy;
  protected final Picture scaleBarPicture = new Picture();
  public int screenHeight;
  public int screenWidth;
  private Paint textPaint;
  final int textSize = 12;
  float xOffset = 10.0F;
  public float xdpi;
  float yOffset = 10.0F;
  public float ydpi;

  public ScaleBarOverlay(Activity paramActivity)
  {
    this(paramActivity, new DefaultResourceProxyImpl(paramActivity));
  }

  public ScaleBarOverlay(Activity paramActivity, ResourceProxy paramResourceProxy)
  {
    super(paramResourceProxy);
    this.resourceProxy = paramResourceProxy;
    this.activity = paramActivity;
    this.barPaint = new Paint();
    this.barPaint.setColor(-16777216);
    this.barPaint.setAntiAlias(true);
    this.barPaint.setStyle(Paint.Style.FILL);
    this.barPaint.setAlpha(255);
    this.textPaint = new Paint();
    this.textPaint.setColor(-16777216);
    this.textPaint.setAntiAlias(true);
    this.textPaint.setStyle(Paint.Style.FILL);
    this.textPaint.setAlpha(255);
    this.textPaint.setTextSize(12.0F);
    this.xdpi = this.activity.getResources().getDisplayMetrics().xdpi;
    this.ydpi = this.activity.getResources().getDisplayMetrics().ydpi;
    this.screenWidth = this.activity.getResources().getDisplayMetrics().widthPixels;
    this.screenHeight = this.activity.getResources().getDisplayMetrics().heightPixels;
    try
    {
      str = (String)Build.class.getField("MANUFACTURER").get(null);
      if (("motorola".equals(str)) && ("DROIDX".equals(Build.MODEL)))
        if (paramActivity.getWindowManager().getDefaultDisplay().getOrientation() > 0)
        {
          this.xdpi = (float)(this.screenWidth / 3.75D);
          this.ydpi = (float)(this.screenHeight / 2.1D);
        }
      do
      {
        return;
        this.xdpi = (float)(this.screenWidth / 2.1D);
        this.ydpi = (float)(this.screenHeight / 3.75D);
        return;
      }
      while ((!"motorola".equals(str)) || (!"Droid".equals(Build.MODEL)));
      this.xdpi = 264.0F;
      this.ydpi = 264.0F;
      return;
    }
    catch (Exception localException)
    {
      while (true)
        String str = null;
    }
  }

  private void createScaleBarPicture(MapView paramMapView)
  {
    this.projection = paramMapView.getProjection();
    if (this.projection == null)
      return;
    int i = this.projection.fromPixels(this.screenWidth / 2 - this.xdpi / 2.0F, this.screenHeight / 2).distanceTo(this.projection.fromPixels(this.screenWidth / 2 + this.xdpi / 2.0F, this.screenHeight / 2));
    int j = this.projection.fromPixels(this.screenWidth / 2, this.screenHeight / 2 - this.ydpi / 2.0F).distanceTo(this.projection.fromPixels(this.screenWidth / 2, this.screenHeight / 2 + this.ydpi / 2.0F));
    Canvas localCanvas = this.scaleBarPicture.beginRecording((int)this.xdpi, (int)this.ydpi);
    if (this.latitudeBar)
    {
      String str2 = scaleBarLengthText(i, this.imperial, this.nautical);
      Rect localRect2 = new Rect();
      this.textPaint.getTextBounds(str2, 0, str2.length(), localRect2);
      int m = (int)(localRect2.height() / 5.0D);
      localCanvas.drawRect(0.0F, 0.0F, this.xdpi, this.lineWidth, this.barPaint);
      localCanvas.drawRect(this.xdpi, 0.0F, this.xdpi + this.lineWidth, localRect2.height() + this.lineWidth + m, this.barPaint);
      if (!this.longitudeBar)
        localCanvas.drawRect(0.0F, 0.0F, this.lineWidth, localRect2.height() + this.lineWidth + m, this.barPaint);
      localCanvas.drawText(str2, this.xdpi / 2.0F - localRect2.width() / 2, localRect2.height() + this.lineWidth + m, this.textPaint);
    }
    if (this.longitudeBar)
    {
      String str1 = scaleBarLengthText(j, this.imperial, this.nautical);
      Rect localRect1 = new Rect();
      this.textPaint.getTextBounds(str1, 0, str1.length(), localRect1);
      int k = (int)(localRect1.height() / 5.0D);
      localCanvas.drawRect(0.0F, 0.0F, this.lineWidth, this.ydpi, this.barPaint);
      localCanvas.drawRect(0.0F, this.ydpi, localRect1.height() + this.lineWidth + k, this.ydpi + this.lineWidth, this.barPaint);
      if (!this.latitudeBar)
        localCanvas.drawRect(0.0F, 0.0F, localRect1.height() + this.lineWidth + k, this.lineWidth, this.barPaint);
      float f1 = localRect1.height() + this.lineWidth + k;
      float f2 = this.ydpi / 2.0F + localRect1.width() / 2;
      localCanvas.rotate(-90.0F, f1, f2);
      localCanvas.drawText(str1, f1, f2 + k, this.textPaint);
    }
    this.scaleBarPicture.endRecording();
  }

  private String scaleBarLengthText(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.imperial)
    {
      if (paramInt >= 8046.7200000000003D)
      {
        ResourceProxy localResourceProxy9 = this.resourceProxy;
        ResourceProxy.string localstring9 = ResourceProxy.string.format_distance_miles;
        Object[] arrayOfObject9 = new Object[1];
        arrayOfObject9[0] = Integer.valueOf((int)(paramInt / 1609.3440000000001D));
        return localResourceProxy9.getString(localstring9, arrayOfObject9);
      }
      if (paramInt >= 321.86880000000002D)
      {
        ResourceProxy localResourceProxy8 = this.resourceProxy;
        ResourceProxy.string localstring8 = ResourceProxy.string.format_distance_miles;
        Object[] arrayOfObject8 = new Object[1];
        arrayOfObject8[0] = Double.valueOf((int)(paramInt / 160.93440000000001D) / 10.0D);
        return localResourceProxy8.getString(localstring8, arrayOfObject8);
      }
      ResourceProxy localResourceProxy7 = this.resourceProxy;
      ResourceProxy.string localstring7 = ResourceProxy.string.format_distance_feet;
      Object[] arrayOfObject7 = new Object[1];
      arrayOfObject7[0] = Integer.valueOf((int)(3.2808399D * paramInt));
      return localResourceProxy7.getString(localstring7, arrayOfObject7);
    }
    if (this.nautical)
    {
      if (paramInt >= 9260.0D)
      {
        ResourceProxy localResourceProxy6 = this.resourceProxy;
        ResourceProxy.string localstring6 = ResourceProxy.string.format_distance_nautical_miles;
        Object[] arrayOfObject6 = new Object[1];
        arrayOfObject6[0] = Integer.valueOf((int)(paramInt / 1852.0D));
        return localResourceProxy6.getString(localstring6, arrayOfObject6);
      }
      if (paramInt >= 370.39999999999998D)
      {
        ResourceProxy localResourceProxy5 = this.resourceProxy;
        ResourceProxy.string localstring5 = ResourceProxy.string.format_distance_nautical_miles;
        Object[] arrayOfObject5 = new Object[1];
        arrayOfObject5[0] = Double.valueOf((int)(paramInt / 185.19999999999999D) / 10.0D);
        return localResourceProxy5.getString(localstring5, arrayOfObject5);
      }
      ResourceProxy localResourceProxy4 = this.resourceProxy;
      ResourceProxy.string localstring4 = ResourceProxy.string.format_distance_feet;
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = Integer.valueOf((int)(3.2808399D * paramInt));
      return localResourceProxy4.getString(localstring4, arrayOfObject4);
    }
    if (paramInt >= 5000)
    {
      ResourceProxy localResourceProxy3 = this.resourceProxy;
      ResourceProxy.string localstring3 = ResourceProxy.string.format_distance_kilometers;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Integer.valueOf(paramInt / 1000);
      return localResourceProxy3.getString(localstring3, arrayOfObject3);
    }
    if (paramInt >= 200)
    {
      ResourceProxy localResourceProxy2 = this.resourceProxy;
      ResourceProxy.string localstring2 = ResourceProxy.string.format_distance_kilometers;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Double.valueOf((int)(paramInt / 100.0D) / 10.0D);
      return localResourceProxy2.getString(localstring2, arrayOfObject2);
    }
    ResourceProxy localResourceProxy1 = this.resourceProxy;
    ResourceProxy.string localstring1 = ResourceProxy.string.format_distance_meters;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(paramInt);
    return localResourceProxy1.getString(localstring1, arrayOfObject1);
  }

  public void disableScaleBar()
  {
    setEnabled(false);
  }

  public void draw(Canvas paramCanvas, MapView paramMapView, boolean paramBoolean)
  {
    if (paramBoolean);
    int i;
    MapView.Projection localProjection;
    do
    {
      do
      {
        do
          return;
        while (paramMapView.isAnimating());
        i = paramMapView.getZoomLevel();
      }
      while (i < this.minZoom);
      localProjection = paramMapView.getProjection();
    }
    while (localProjection == null);
    GeoPoint localGeoPoint = localProjection.fromPixels(this.screenWidth / 2, this.screenHeight / 2);
    if ((i != this.lastZoomLevel) || ((int)(localGeoPoint.getLatitudeE6() / 1000000.0D) != (int)(this.lastLatitude / 1000000.0D)))
    {
      this.lastZoomLevel = i;
      this.lastLatitude = localGeoPoint.getLatitudeE6();
      createScaleBarPicture(paramMapView);
    }
    this.mBounds.set(localProjection.getScreenRect());
    this.mBounds.offset((int)this.xOffset, (int)this.yOffset);
    this.mBounds.set(this.mBounds.left, this.mBounds.top, this.mBounds.left + this.scaleBarPicture.getWidth(), this.mBounds.top + this.scaleBarPicture.getHeight());
    paramCanvas.drawPicture(this.scaleBarPicture, this.mBounds);
  }

  public void drawLatitudeScale(boolean paramBoolean)
  {
    this.latitudeBar = paramBoolean;
  }

  public void drawLongitudeScale(boolean paramBoolean)
  {
    this.longitudeBar = paramBoolean;
  }

  public void enableScaleBar()
  {
    setEnabled(true);
  }

  public Paint getBarPaint()
  {
    return this.barPaint;
  }

  public Paint getTextPaint()
  {
    return this.textPaint;
  }

  public void setBarPaint(Paint paramPaint)
  {
    if (paramPaint == null)
      throw new IllegalArgumentException("pBarPaint argument cannot be null");
    this.barPaint = paramPaint;
  }

  public void setImperial()
  {
    this.imperial = true;
    this.nautical = false;
    this.lastZoomLevel = -1;
  }

  public void setLineWidth(float paramFloat)
  {
    this.lineWidth = paramFloat;
  }

  public void setMetric()
  {
    this.nautical = false;
    this.imperial = false;
    this.lastZoomLevel = -1;
  }

  public void setMinZoom(int paramInt)
  {
    this.minZoom = paramInt;
  }

  public void setNautical()
  {
    this.nautical = true;
    this.imperial = false;
    this.lastZoomLevel = -1;
  }

  public void setScaleBarOffset(float paramFloat1, float paramFloat2)
  {
    this.xOffset = paramFloat1;
    this.yOffset = paramFloat2;
  }

  public void setTextPaint(Paint paramPaint)
  {
    if (paramPaint == null)
      throw new IllegalArgumentException("pTextPaint argument cannot be null");
    this.textPaint = paramPaint;
  }

  public void setTextSize(float paramFloat)
  {
    this.textPaint.setTextSize(paramFloat);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.overlay.ScaleBarOverlay
 * JD-Core Version:    0.6.0
 */