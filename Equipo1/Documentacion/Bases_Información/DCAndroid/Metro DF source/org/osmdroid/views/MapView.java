package org.osmdroid.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.Scroller;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import microsoft.mappoint.TileSystem;
import net.wigle.wigleandroid.ZoomButtonsController;
import net.wigle.wigleandroid.ZoomButtonsController.OnZoomListener;
import org.metalev.multitouch.controller.MultiTouchController;
import org.metalev.multitouch.controller.MultiTouchController.MultiTouchObjectCanvas;
import org.metalev.multitouch.controller.MultiTouchController.PointInfo;
import org.metalev.multitouch.controller.MultiTouchController.PositionAndScale;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.api.IMapView;
import org.osmdroid.api.IProjection;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.tilesource.IStyledTileSource;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.util.SimpleInvalidationHandler;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.constants.GeoConstants;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.TilesOverlay;
import org.osmdroid.views.util.constants.MapViewConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapView extends ViewGroup
  implements IMapView, MapViewConstants, MultiTouchController.MultiTouchObjectCanvas<Object>
{
  private static final double ZOOM_LOG_BASE_INV = 0.0D;
  private static final double ZOOM_SENSITIVITY = 1.3D;
  private static final Logger logger = LoggerFactory.getLogger(MapView.class);
  private final MyAnimationListener mAnimationListener = new MyAnimationListener(null);
  private final MapController mController;
  private boolean mEnableZoomController = false;
  private final GestureDetector mGestureDetector;
  protected MapListener mListener;
  private final TilesOverlay mMapOverlay;
  private final Matrix mMatrix = new Matrix();
  private MultiTouchController<Object> mMultiTouchController;
  private float mMultiTouchScale = 1.0F;
  private final OverlayManager mOverlayManager;
  private final Point mPoint = new Point();
  private Projection mProjection;
  private final ResourceProxy mResourceProxy;
  private final Scroller mScroller;
  private final MapTileProviderBase mTileProvider;
  private final Handler mTileRequestCompleteHandler;
  private final ZoomButtonsController mZoomController;
  private final ScaleAnimation mZoomInAnimation;
  private int mZoomLevel = 0;
  private final ScaleAnimation mZoomOutAnimation;

  static
  {
    ZOOM_LOG_BASE_INV = 1.0D / Math.log(1.538461538461538D);
  }

  public MapView(Context paramContext, int paramInt)
  {
    this(paramContext, paramInt, new DefaultResourceProxyImpl(paramContext));
  }

  public MapView(Context paramContext, int paramInt, ResourceProxy paramResourceProxy)
  {
    this(paramContext, paramInt, paramResourceProxy, null);
  }

  public MapView(Context paramContext, int paramInt, ResourceProxy paramResourceProxy, MapTileProviderBase paramMapTileProviderBase)
  {
    this(paramContext, paramInt, paramResourceProxy, paramMapTileProviderBase, null);
  }

  public MapView(Context paramContext, int paramInt, ResourceProxy paramResourceProxy, MapTileProviderBase paramMapTileProviderBase, Handler paramHandler)
  {
    this(paramContext, paramInt, paramResourceProxy, paramMapTileProviderBase, paramHandler, null);
  }

  private MapView(Context paramContext, int paramInt, ResourceProxy paramResourceProxy, MapTileProviderBase paramMapTileProviderBase, Handler paramHandler, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mResourceProxy = paramResourceProxy;
    this.mController = new MapController(this);
    this.mScroller = new Scroller(paramContext);
    TileSystem.setTileSize(paramInt);
    if (paramMapTileProviderBase == null)
      paramMapTileProviderBase = new MapTileProviderBasic(paramContext, getTileSourceFromAttributes(paramAttributeSet));
    if (paramHandler == null)
      paramHandler = new SimpleInvalidationHandler(this);
    this.mTileRequestCompleteHandler = paramHandler;
    this.mTileProvider = paramMapTileProviderBase;
    this.mTileProvider.setTileRequestCompleteHandler(this.mTileRequestCompleteHandler);
    this.mMapOverlay = new TilesOverlay(this.mTileProvider, this.mResourceProxy);
    this.mOverlayManager = new OverlayManager(this.mMapOverlay);
    this.mZoomController = new ZoomButtonsController(this);
    this.mZoomController.setOnZoomListener(new MapViewZoomListener(null));
    this.mZoomInAnimation = new ScaleAnimation(1.0F, 2.0F, 1.0F, 2.0F, 1, 0.5F, 1, 0.5F);
    this.mZoomOutAnimation = new ScaleAnimation(1.0F, 0.5F, 1.0F, 0.5F, 1, 0.5F, 1, 0.5F);
    this.mZoomInAnimation.setDuration(500L);
    this.mZoomOutAnimation.setDuration(500L);
    this.mZoomInAnimation.setAnimationListener(this.mAnimationListener);
    this.mZoomOutAnimation.setAnimationListener(this.mAnimationListener);
    this.mGestureDetector = new GestureDetector(paramContext, new MapViewGestureDetectorListener(null));
    this.mGestureDetector.setOnDoubleTapListener(new MapViewDoubleClickListener(null));
  }

  public MapView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, 256, new DefaultResourceProxyImpl(paramContext), null, null, paramAttributeSet);
  }

  private void checkZoomButtons()
  {
    this.mZoomController.setZoomInEnabled(canZoomIn());
    this.mZoomController.setZoomOutEnabled(canZoomOut());
  }

  private ITileSource getTileSourceFromAttributes(AttributeSet paramAttributeSet)
  {
    Object localObject = TileSourceFactory.DEFAULT_TILE_SOURCE;
    String str2;
    if (paramAttributeSet != null)
    {
      str2 = paramAttributeSet.getAttributeValue(null, "tilesource");
      if (str2 == null);
    }
    try
    {
      ITileSource localITileSource = TileSourceFactory.getTileSource(str2);
      logger.info("Using tile source specified in layout attributes: " + localITileSource);
      localObject = localITileSource;
      if ((paramAttributeSet != null) && ((localObject instanceof IStyledTileSource)))
      {
        str1 = paramAttributeSet.getAttributeValue(null, "style");
        if (str1 == null)
          str1 = paramAttributeSet.getAttributeValue(null, "cloudmadeStyle");
        if (str1 == null)
          logger.info("Using default style: 1");
      }
      else
      {
        logger.info("Using tile source: " + localObject);
        return localObject;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
      {
        String str1;
        logger.warn("Invalid tile souce specified in layout attributes: " + localObject);
        continue;
        logger.info("Using style specified in layout attributes: " + str1);
        ((IStyledTileSource)localObject).setStyle(str1);
      }
    }
  }

  public boolean canZoomIn()
  {
    int i = getMaxZoomLevel();
    if (this.mZoomLevel >= i);
    do
      return false;
    while ((this.mAnimationListener.isAnimating()) && (this.mAnimationListener.targetZoomLevel >= i));
    return true;
  }

  public boolean canZoomOut()
  {
    int i = getMinZoomLevel();
    if (this.mZoomLevel <= i);
    do
      return false;
    while ((this.mAnimationListener.isAnimating()) && (this.mAnimationListener.targetZoomLevel <= i));
    return true;
  }

  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }

  public void computeScroll()
  {
    if (this.mScroller.computeScrollOffset())
    {
      if (!this.mScroller.isFinished())
        break label34;
      setZoomLevel(this.mZoomLevel);
    }
    while (true)
    {
      postInvalidate();
      return;
      label34: scrollTo(this.mScroller.getCurrX(), this.mScroller.getCurrY());
    }
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    System.currentTimeMillis();
    this.mProjection = new Projection(null);
    paramCanvas.save();
    if (this.mMultiTouchScale == 1.0F)
      paramCanvas.translate(getWidth() / 2, getHeight() / 2);
    while (true)
    {
      this.mOverlayManager.onDraw(paramCanvas, this);
      paramCanvas.restore();
      super.dispatchDraw(paramCanvas);
      System.currentTimeMillis();
      return;
      paramCanvas.getMatrix(this.mMatrix);
      this.mMatrix.postTranslate(getWidth() / 2, getHeight() / 2);
      this.mMatrix.preScale(this.mMultiTouchScale, this.mMultiTouchScale, getScrollX(), getScrollY());
      paramCanvas.setMatrix(this.mMatrix);
    }
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool;
    if ((this.mZoomController.isVisible()) && (this.mZoomController.onTouch(this, paramMotionEvent)))
      bool = true;
    do
    {
      return bool;
      if (this.mOverlayManager.onTouchEvent(paramMotionEvent, this))
        return true;
      if ((this.mMultiTouchController != null) && (this.mMultiTouchController.onTouchEvent(paramMotionEvent)))
        return true;
      bool = super.dispatchTouchEvent(paramMotionEvent);
      if (this.mGestureDetector.onTouchEvent(paramMotionEvent))
        return true;
    }
    while (!bool);
    return bool;
  }

  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-2, -2, null, 8, 0, 0);
  }

  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }

  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new LayoutParams(paramLayoutParams);
  }

  public BoundingBoxE6 getBoundingBox()
  {
    return getBoundingBox(getWidth(), getHeight());
  }

  public BoundingBoxE6 getBoundingBox(int paramInt1, int paramInt2)
  {
    int i = TileSystem.MapSize(this.mZoomLevel) / 2;
    Rect localRect = getScreenRect(null);
    localRect.offset(i, i);
    GeoPoint localGeoPoint1 = TileSystem.PixelXYToLatLong(localRect.right, localRect.top, this.mZoomLevel, null);
    GeoPoint localGeoPoint2 = TileSystem.PixelXYToLatLong(localRect.left, localRect.bottom, this.mZoomLevel, null);
    return new BoundingBoxE6(localGeoPoint1.getLatitudeE6(), localGeoPoint1.getLongitudeE6(), localGeoPoint2.getLatitudeE6(), localGeoPoint2.getLongitudeE6());
  }

  public MapController getController()
  {
    return this.mController;
  }

  public Object getDraggableObjectAtPoint(MultiTouchController.PointInfo paramPointInfo)
  {
    return this;
  }

  public int getLatitudeSpan()
  {
    return getBoundingBox().getLatitudeSpanE6();
  }

  public int getLongitudeSpan()
  {
    return getBoundingBox().getLongitudeSpanE6();
  }

  public GeoPoint getMapCenter()
  {
    int i = TileSystem.MapSize(this.mZoomLevel) / 2;
    Rect localRect = getScreenRect(null);
    localRect.offset(i, i);
    return TileSystem.PixelXYToLatLong(localRect.centerX(), localRect.centerY(), this.mZoomLevel, null);
  }

  public int getMaxZoomLevel()
  {
    return this.mMapOverlay.getMaximumZoomLevel();
  }

  public int getMinZoomLevel()
  {
    return this.mMapOverlay.getMinimumZoomLevel();
  }

  public OverlayManager getOverlayManager()
  {
    return this.mOverlayManager;
  }

  public List<Overlay> getOverlays()
  {
    return this.mOverlayManager;
  }

  public void getPositionAndScale(Object paramObject, MultiTouchController.PositionAndScale paramPositionAndScale)
  {
    paramPositionAndScale.set(0.0F, 0.0F, true, this.mMultiTouchScale, false, 0.0F, 0.0F, false, 0.0F);
  }

  public Projection getProjection()
  {
    if (this.mProjection == null)
      this.mProjection = new Projection(null);
    return this.mProjection;
  }

  public ResourceProxy getResourceProxy()
  {
    return this.mResourceProxy;
  }

  public Rect getScreenRect(Rect paramRect)
  {
    if (paramRect == null);
    for (Rect localRect = new Rect(); ; localRect = paramRect)
    {
      localRect.set(getScrollX() - getWidth() / 2, getScrollY() - getHeight() / 2, getScrollX() + getWidth() / 2, getScrollY() + getHeight() / 2);
      return localRect;
    }
  }

  public Scroller getScroller()
  {
    return this.mScroller;
  }

  public MapTileProviderBase getTileProvider()
  {
    return this.mTileProvider;
  }

  public Handler getTileRequestCompleteHandler()
  {
    return this.mTileRequestCompleteHandler;
  }

  public int getZoomLevel()
  {
    return getZoomLevel(true);
  }

  public int getZoomLevel(boolean paramBoolean)
  {
    if ((paramBoolean) && (this.mAnimationListener.isAnimating()))
      return this.mAnimationListener.targetZoomLevel;
    return this.mZoomLevel;
  }

  public boolean isAnimating()
  {
    return this.mAnimationListener.isAnimating();
  }

  public void onDetach()
  {
    this.mOverlayManager.onDetach(this);
  }

  protected void onDetachedFromWindow()
  {
    this.mZoomController.setVisible(false);
    onDetach();
    super.onDetachedFromWindow();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return (this.mOverlayManager.onKeyDown(paramInt, paramKeyEvent, this)) || (super.onKeyDown(paramInt, paramKeyEvent));
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    return (this.mOverlayManager.onKeyUp(paramInt, paramKeyEvent, this)) || (super.onKeyUp(paramInt, paramKeyEvent));
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getChildCount();
    int j = 0;
    if (j >= i)
      return;
    View localView = getChildAt(j);
    LayoutParams localLayoutParams;
    int k;
    int m;
    int n;
    int i1;
    int i2;
    int i3;
    if (localView.getVisibility() != 8)
    {
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      k = localView.getMeasuredHeight();
      m = localView.getMeasuredWidth();
      getProjection().toMapPixels(localLayoutParams.geoPoint, this.mPoint);
      n = this.mPoint.x + getWidth() / 2;
      i1 = this.mPoint.y + getHeight() / 2;
      i2 = n;
      i3 = i1;
      switch (localLayoutParams.alignment)
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      }
    }
    while (true)
    {
      int i4 = i2 + localLayoutParams.offsetX;
      int i5 = i3 + localLayoutParams.offsetY;
      localView.layout(i4, i5, i4 + m, i5 + k);
      j++;
      break;
      i2 = n + getPaddingLeft();
      i3 = i1 + getPaddingTop();
      continue;
      i2 = n + getPaddingLeft() - m / 2;
      i3 = i1 + getPaddingTop();
      continue;
      i2 = n + getPaddingLeft() - m;
      i3 = i1 + getPaddingTop();
      continue;
      i2 = n + getPaddingLeft();
      i3 = i1 + getPaddingTop() - k / 2;
      continue;
      i2 = n + getPaddingLeft() - m / 2;
      i3 = i1 + getPaddingTop() - k / 2;
      continue;
      i2 = n + getPaddingLeft() - m;
      i3 = i1 + getPaddingTop() - k / 2;
      continue;
      i2 = n + getPaddingLeft();
      i3 = i1 + getPaddingTop() - k;
      continue;
      i2 = n + getPaddingLeft() - m / 2;
      i3 = i1 + getPaddingTop() - k;
      continue;
      i2 = n + getPaddingLeft() - m;
      i3 = i1 + getPaddingTop() - k;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = getChildCount();
    int j = 0;
    int k = 0;
    measureChildren(paramInt1, paramInt2);
    int m = 0;
    if (m >= i)
    {
      int i8 = k + (getPaddingLeft() + getPaddingRight());
      int i9 = Math.max(j + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight());
      setMeasuredDimension(resolveSize(Math.max(i8, getSuggestedMinimumWidth()), paramInt1), resolveSize(i9, paramInt2));
      return;
    }
    View localView = getChildAt(m);
    LayoutParams localLayoutParams;
    int n;
    int i1;
    int i2;
    int i3;
    int i4;
    int i5;
    if (localView.getVisibility() != 8)
    {
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      n = localView.getMeasuredHeight();
      i1 = localView.getMeasuredWidth();
      getProjection().toMapPixels(localLayoutParams.geoPoint, this.mPoint);
      i2 = this.mPoint.x + getWidth() / 2;
      i3 = this.mPoint.y + getHeight() / 2;
      i4 = i2;
      i5 = i3;
      switch (localLayoutParams.alignment)
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      }
    }
    while (true)
    {
      int i6 = i4 + localLayoutParams.offsetX;
      int i7 = i5 + localLayoutParams.offsetY;
      k = Math.max(k, i6);
      j = Math.max(j, i7);
      m++;
      break;
      i4 = i2 + i1;
      i5 = i3;
      continue;
      i4 = i2 + i1 / 2;
      i5 = i3;
      continue;
      i4 = i2;
      i5 = i3;
      continue;
      i4 = i2 + i1;
      i5 = i3 + n / 2;
      continue;
      i4 = i2 + i1 / 2;
      i5 = i3 + n / 2;
      continue;
      i4 = i2;
      i5 = i3 + n / 2;
      continue;
      i4 = i2 + i1;
      i5 = i3 + n;
      continue;
      i4 = i2 + i1 / 2;
      i5 = i3 + n;
      continue;
      i4 = i2;
      i5 = i3 + n;
    }
  }

  public boolean onTrackballEvent(MotionEvent paramMotionEvent)
  {
    if (this.mOverlayManager.onTrackballEvent(paramMotionEvent, this))
      return true;
    scrollBy((int)(25.0F * paramMotionEvent.getX()), (int)(25.0F * paramMotionEvent.getY()));
    return super.onTrackballEvent(paramMotionEvent);
  }

  public void scrollTo(int paramInt1, int paramInt2)
  {
    int i = TileSystem.MapSize(this.mZoomLevel) / 2;
    if (paramInt1 >= -i)
    {
      label16: if (paramInt1 > i)
        break label79;
      label21: if (paramInt2 < -i)
        break label88;
    }
    while (true)
    {
      if (paramInt2 <= i)
      {
        super.scrollTo(paramInt1, paramInt2);
        if (this.mListener != null)
        {
          ScrollEvent localScrollEvent = new ScrollEvent(this, paramInt1, paramInt2);
          this.mListener.onScroll(localScrollEvent);
        }
        return;
        paramInt1 += i * 2;
        break;
        label79: paramInt1 -= i * 2;
        break label16;
        label88: paramInt2 += i * 2;
        break label21;
      }
      paramInt2 -= i * 2;
    }
  }

  public void selectObject(Object paramObject, MultiTouchController.PointInfo paramPointInfo)
  {
    if ((paramObject == null) && (this.mMultiTouchScale != 1.0F))
      setZoomLevel(Math.round((float)(Math.log(this.mMultiTouchScale) * ZOOM_LOG_BASE_INV)) + this.mZoomLevel);
    this.mMultiTouchScale = 1.0F;
  }

  public void setBackgroundColor(int paramInt)
  {
    this.mMapOverlay.setLoadingBackgroundColor(paramInt);
    invalidate();
  }

  public void setBuiltInZoomControls(boolean paramBoolean)
  {
    this.mEnableZoomController = paramBoolean;
    checkZoomButtons();
  }

  void setMapCenter(int paramInt1, int paramInt2)
  {
    Point localPoint = TileSystem.LatLongToPixelXY(paramInt1 / 1000000.0D, paramInt2 / 1000000.0D, getZoomLevel(), null);
    int i = TileSystem.MapSize(this.mZoomLevel) / 2;
    if ((getAnimation() == null) || (getAnimation().hasEnded()))
    {
      logger.debug("StartScroll");
      this.mScroller.startScroll(getScrollX(), getScrollY(), localPoint.x - i - getScrollX(), localPoint.y - i - getScrollY(), 500);
      postInvalidate();
    }
  }

  void setMapCenter(GeoPoint paramGeoPoint)
  {
    setMapCenter(paramGeoPoint.getLatitudeE6(), paramGeoPoint.getLongitudeE6());
  }

  public void setMapListener(MapListener paramMapListener)
  {
    this.mListener = paramMapListener;
  }

  public void setMultiTouchControls(boolean paramBoolean)
  {
    if (paramBoolean);
    for (MultiTouchController localMultiTouchController = new MultiTouchController(this, false); ; localMultiTouchController = null)
    {
      this.mMultiTouchController = localMultiTouchController;
      return;
    }
  }

  public boolean setPositionAndScale(Object paramObject, MultiTouchController.PositionAndScale paramPositionAndScale, MultiTouchController.PointInfo paramPointInfo)
  {
    float f = paramPositionAndScale.getScale();
    if ((f > 1.0F) && (!canZoomIn()))
      f = 1.0F;
    if ((f < 1.0F) && (!canZoomOut()))
      f = 1.0F;
    this.mMultiTouchScale = f;
    invalidate();
    return true;
  }

  public void setTileSource(ITileSource paramITileSource)
  {
    this.mTileProvider.setTileSource(paramITileSource);
    TileSystem.setTileSize(paramITileSource.getTileSizePixels());
    checkZoomButtons();
    setZoomLevel(this.mZoomLevel);
    postInvalidate();
  }

  public void setUseDataConnection(boolean paramBoolean)
  {
    this.mMapOverlay.setUseDataConnection(paramBoolean);
  }

  int setZoomLevel(int paramInt)
  {
    int i = Math.max(getMinZoomLevel(), Math.min(getMaxZoomLevel(), paramInt));
    int j = this.mZoomLevel;
    this.mZoomLevel = i;
    checkZoomButtons();
    if (i > j)
      scrollTo(getScrollX() << i - j, getScrollY() << i - j);
    while (true)
    {
      Point localPoint = new Point();
      this.mProjection = new Projection(null);
      if (this.mOverlayManager.onSnapToItem(getScrollX(), getScrollY(), localPoint, this))
        scrollTo(localPoint.x, localPoint.y);
      if ((i != j) && (this.mListener != null))
      {
        ZoomEvent localZoomEvent = new ZoomEvent(this, i);
        this.mListener.onZoom(localZoomEvent);
      }
      return this.mZoomLevel;
      if (i >= j)
        continue;
      scrollTo(getScrollX() >> j - i, getScrollY() >> j - i);
    }
  }

  public boolean useDataConnection()
  {
    return this.mMapOverlay.useDataConnection();
  }

  boolean zoomIn()
  {
    if ((!canZoomIn()) || (this.mAnimationListener.isAnimating()))
      return false;
    this.mAnimationListener.targetZoomLevel = (1 + this.mZoomLevel);
    startAnimation(this.mZoomInAnimation);
    return true;
  }

  boolean zoomInFixing(int paramInt1, int paramInt2)
  {
    setMapCenter(paramInt1, paramInt2);
    return zoomIn();
  }

  boolean zoomInFixing(GeoPoint paramGeoPoint)
  {
    setMapCenter(paramGeoPoint);
    return zoomIn();
  }

  boolean zoomOut()
  {
    if ((!canZoomOut()) || (this.mAnimationListener.isAnimating()))
      return false;
    this.mAnimationListener.targetZoomLevel = (-1 + this.mZoomLevel);
    startAnimation(this.mZoomOutAnimation);
    return true;
  }

  boolean zoomOutFixing(int paramInt1, int paramInt2)
  {
    setMapCenter(paramInt1, paramInt2);
    return zoomOut();
  }

  boolean zoomOutFixing(GeoPoint paramGeoPoint)
  {
    setMapCenter(paramGeoPoint);
    return zoomOut();
  }

  public static class LayoutParams extends ViewGroup.LayoutParams
  {
    public static final int BOTTOM_CENTER = 8;
    public static final int BOTTOM_LEFT = 7;
    public static final int BOTTOM_RIGHT = 9;
    public static final int CENTER = 5;
    public static final int CENTER_LEFT = 4;
    public static final int CENTER_RIGHT = 6;
    public static final int TOP_CENTER = 2;
    public static final int TOP_LEFT = 1;
    public static final int TOP_RIGHT = 3;
    public int alignment;
    public GeoPoint geoPoint;
    public int offsetX;
    public int offsetY;

    public LayoutParams(int paramInt1, int paramInt2, GeoPoint paramGeoPoint, int paramInt3, int paramInt4, int paramInt5)
    {
      super(paramInt2);
      if (paramGeoPoint != null);
      for (this.geoPoint = paramGeoPoint; ; this.geoPoint = new GeoPoint(0, 0))
      {
        this.alignment = paramInt3;
        this.offsetX = paramInt4;
        this.offsetY = paramInt5;
        return;
      }
    }

    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      this.geoPoint = new GeoPoint(0, 0);
      this.alignment = 8;
    }

    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
  }

  private class MapViewDoubleClickListener
    implements GestureDetector.OnDoubleTapListener
  {
    private MapViewDoubleClickListener()
    {
    }

    public boolean onDoubleTap(MotionEvent paramMotionEvent)
    {
      if (MapView.this.mOverlayManager.onDoubleTap(paramMotionEvent, MapView.this))
        return true;
      GeoPoint localGeoPoint = MapView.this.getProjection().fromPixels(paramMotionEvent.getX(), paramMotionEvent.getY());
      return MapView.this.zoomInFixing(localGeoPoint);
    }

    public boolean onDoubleTapEvent(MotionEvent paramMotionEvent)
    {
      return MapView.this.mOverlayManager.onDoubleTapEvent(paramMotionEvent, MapView.this);
    }

    public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
    {
      return MapView.this.mOverlayManager.onSingleTapConfirmed(paramMotionEvent, MapView.this);
    }
  }

  private class MapViewGestureDetectorListener
    implements GestureDetector.OnGestureListener
  {
    private MapViewGestureDetectorListener()
    {
    }

    public boolean onDown(MotionEvent paramMotionEvent)
    {
      if (MapView.this.mOverlayManager.onDown(paramMotionEvent, MapView.this))
        return true;
      MapView.this.mZoomController.setVisible(MapView.this.mEnableZoomController);
      return true;
    }

    public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      if (MapView.this.mOverlayManager.onFling(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2, MapView.this))
        return true;
      int i = TileSystem.MapSize(MapView.this.mZoomLevel);
      MapView.this.mScroller.fling(MapView.this.getScrollX(), MapView.this.getScrollY(), (int)(-paramFloat1), (int)(-paramFloat2), -i, i, -i, i);
      return true;
    }

    public void onLongPress(MotionEvent paramMotionEvent)
    {
      MapView.this.mOverlayManager.onLongPress(paramMotionEvent, MapView.this);
    }

    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      if (MapView.this.mOverlayManager.onScroll(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2, MapView.this))
        return true;
      MapView.this.scrollBy((int)paramFloat1, (int)paramFloat2);
      return true;
    }

    public void onShowPress(MotionEvent paramMotionEvent)
    {
      MapView.this.mOverlayManager.onShowPress(paramMotionEvent, MapView.this);
    }

    public boolean onSingleTapUp(MotionEvent paramMotionEvent)
    {
      return MapView.this.mOverlayManager.onSingleTapUp(paramMotionEvent, MapView.this);
    }
  }

  private class MapViewZoomListener
    implements ZoomButtonsController.OnZoomListener
  {
    private MapViewZoomListener()
    {
    }

    public void onVisibilityChanged(boolean paramBoolean)
    {
    }

    public void onZoom(boolean paramBoolean)
    {
      if (paramBoolean)
      {
        MapView.this.getController().zoomIn();
        return;
      }
      MapView.this.getController().zoomOut();
    }
  }

  private class MyAnimationListener
    implements Animation.AnimationListener
  {
    private AtomicBoolean animating = new AtomicBoolean(false);
    private int targetZoomLevel;

    private MyAnimationListener()
    {
    }

    public boolean isAnimating()
    {
      return this.animating.get();
    }

    public void onAnimationEnd(Animation paramAnimation)
    {
      this.animating.set(false);
      MapView.this.post(new Runnable()
      {
        public void run()
        {
          MapView.this.clearAnimation();
          MapView.this.setZoomLevel(MapView.MyAnimationListener.this.targetZoomLevel);
        }
      });
    }

    public void onAnimationRepeat(Animation paramAnimation)
    {
    }

    public void onAnimationStart(Animation paramAnimation)
    {
      this.animating.set(true);
    }
  }

  public class Projection
    implements IProjection, GeoConstants
  {
    private final BoundingBoxE6 mBoundingBoxProjection = MapView.this.getBoundingBox();
    private final Rect mScreenRectProjection = MapView.this.getScreenRect(null);
    private final int mZoomLevelProjection = MapView.this.mZoomLevel;
    private final int offsetX = -this.worldSize_2;
    private final int offsetY = -this.worldSize_2;
    private final int viewHeight_2 = MapView.this.getHeight() / 2;
    private final int viewWidth_2 = MapView.this.getWidth() / 2;
    private final int worldSize_2 = TileSystem.MapSize(MapView.this.mZoomLevel) / 2;

    private Projection()
    {
    }

    public Point fromMapPixels(int paramInt1, int paramInt2, Point paramPoint)
    {
      if (paramPoint != null);
      for (Point localPoint = paramPoint; ; localPoint = new Point())
      {
        localPoint.set(paramInt1 - this.viewWidth_2, paramInt2 - this.viewHeight_2);
        localPoint.offset(MapView.this.getScrollX(), MapView.this.getScrollY());
        return localPoint;
      }
    }

    public GeoPoint fromPixels(float paramFloat1, float paramFloat2)
    {
      Rect localRect = getScreenRect();
      return TileSystem.PixelXYToLatLong(localRect.left + (int)paramFloat1 + this.worldSize_2, localRect.top + (int)paramFloat2 + this.worldSize_2, this.mZoomLevelProjection, null);
    }

    public GeoPoint fromPixels(int paramInt1, int paramInt2)
    {
      return fromPixels(paramInt1, paramInt2);
    }

    public Rect fromPixelsToProjected(Rect paramRect)
    {
      Rect localRect = new Rect();
      int i = 22 - getZoomLevel();
      int j = paramRect.left - this.offsetX << i;
      int k = paramRect.right - this.offsetX << i;
      int m = paramRect.bottom - this.offsetY << i;
      int n = paramRect.top - this.offsetY << i;
      localRect.set(Math.min(j, k), Math.min(m, n), Math.max(j, k), Math.max(m, n));
      return localRect;
    }

    public BoundingBoxE6 getBoundingBox()
    {
      return this.mBoundingBoxProjection;
    }

    public Point getCenterMapTileCoords()
    {
      Rect localRect = getScreenRect();
      return TileSystem.PixelXYToTileXY(localRect.centerX(), localRect.centerY(), null);
    }

    public Rect getScreenRect()
    {
      return this.mScreenRectProjection;
    }

    public int getTileSizePixels()
    {
      return TileSystem.getTileSize();
    }

    public Point getUpperLeftCornerOfCenterMapTile()
    {
      Point localPoint = getCenterMapTileCoords();
      return TileSystem.TileXYToPixelXY(localPoint.x, localPoint.y, null);
    }

    public int getZoomLevel()
    {
      return this.mZoomLevelProjection;
    }

    public float metersToEquatorPixels(float paramFloat)
    {
      return paramFloat / (float)TileSystem.GroundResolution(0.0D, this.mZoomLevelProjection);
    }

    public Point toMapPixels(IGeoPoint paramIGeoPoint, Point paramPoint)
    {
      if (paramPoint != null);
      for (Point localPoint1 = paramPoint; ; localPoint1 = new Point())
      {
        Point localPoint2 = TileSystem.LatLongToPixelXY(paramIGeoPoint.getLatitudeE6() / 1000000.0D, paramIGeoPoint.getLongitudeE6() / 1000000.0D, getZoomLevel(), null);
        localPoint1.set(localPoint2.x, localPoint2.y);
        localPoint1.offset(this.offsetX, this.offsetY);
        return localPoint1;
      }
    }

    public Point toMapPixelsProjected(int paramInt1, int paramInt2, Point paramPoint)
    {
      if (paramPoint != null);
      for (Point localPoint = paramPoint; ; localPoint = new Point())
      {
        TileSystem.LatLongToPixelXY(paramInt1 / 1000000.0D, paramInt2 / 1000000.0D, 22, localPoint);
        return localPoint;
      }
    }

    public Point toMapPixelsTranslated(Point paramPoint1, Point paramPoint2)
    {
      if (paramPoint2 != null);
      for (Point localPoint = paramPoint2; ; localPoint = new Point())
      {
        int i = 22 - getZoomLevel();
        localPoint.set((paramPoint1.x >> i) + this.offsetX, (paramPoint1.y >> i) + this.offsetY);
        return localPoint;
      }
    }

    public Point toPixels(int paramInt1, int paramInt2, Point paramPoint)
    {
      return TileSystem.TileXYToPixelXY(paramInt1, paramInt2, paramPoint);
    }

    public Point toPixels(Point paramPoint1, Point paramPoint2)
    {
      return toPixels(paramPoint1.x, paramPoint1.y, paramPoint2);
    }

    public Point toPixels(GeoPoint paramGeoPoint, Point paramPoint)
    {
      return toMapPixels(paramGeoPoint, paramPoint);
    }

    public Rect toPixels(BoundingBoxE6 paramBoundingBoxE6)
    {
      Rect localRect = new Rect();
      Point localPoint = new Point();
      toMapPixels(new GeoPoint(paramBoundingBoxE6.getLatNorthE6(), paramBoundingBoxE6.getLonWestE6()), localPoint);
      localRect.left = localPoint.x;
      localRect.top = localPoint.y;
      toMapPixels(new GeoPoint(paramBoundingBoxE6.getLatSouthE6(), paramBoundingBoxE6.getLonEastE6()), localPoint);
      localRect.right = localPoint.x;
      localRect.bottom = localPoint.y;
      return localRect;
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.MapView
 * JD-Core Version:    0.6.0
 */