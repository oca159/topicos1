package org.osmdroid.views;

import android.graphics.Point;
import android.widget.Scroller;
import microsoft.mappoint.TileSystem;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.api.IMapController;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.util.MyMath;
import org.osmdroid.views.util.constants.MapViewConstants;
import org.osmdroid.views.util.constants.MathConstants;

public class MapController
  implements IMapController, MapViewConstants
{
  private AbstractAnimationRunner mCurrentAnimationRunner;
  private final MapView mOsmv;

  public MapController(MapView paramMapView)
  {
    this.mOsmv = paramMapView;
  }

  public void animateTo(int paramInt1, int paramInt2, AnimationType paramAnimationType)
  {
    animateTo(paramInt1, paramInt2, paramAnimationType, 10, 1000);
  }

  public void animateTo(int paramInt1, int paramInt2, AnimationType paramAnimationType, int paramInt3, int paramInt4)
  {
    stopAnimation(false);
    switch ($SWITCH_TABLE$org$osmdroid$views$MapController$AnimationType()[paramAnimationType.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      this.mCurrentAnimationRunner.start();
      return;
      this.mCurrentAnimationRunner = new LinearAnimationRunner(paramInt1, paramInt2, paramInt3, paramInt4);
      continue;
      this.mCurrentAnimationRunner = new ExponentialDeceleratingAnimationRunner(paramInt1, paramInt2, paramInt3, paramInt4);
      continue;
      this.mCurrentAnimationRunner = new QuarterCosinusalDeceleratingAnimationRunner(paramInt1, paramInt2, paramInt3, paramInt4);
      continue;
      this.mCurrentAnimationRunner = new HalfCosinusalDeceleratingAnimationRunner(paramInt1, paramInt2, paramInt3, paramInt4);
      continue;
      this.mCurrentAnimationRunner = new MiddlePeakSpeedAnimationRunner(paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }

  public void animateTo(IGeoPoint paramIGeoPoint)
  {
    int i = this.mOsmv.getScrollX();
    int j = this.mOsmv.getScrollY();
    Point localPoint = TileSystem.LatLongToPixelXY(paramIGeoPoint.getLatitudeE6() / 1000000.0D, paramIGeoPoint.getLongitudeE6() / 1000000.0D, this.mOsmv.getZoomLevel(), null);
    int k = TileSystem.MapSize(this.mOsmv.getZoomLevel()) / 2;
    this.mOsmv.getScroller().startScroll(i, j, localPoint.x - k - i, localPoint.y - k - j, 1000);
    this.mOsmv.postInvalidate();
  }

  public void animateTo(GeoPoint paramGeoPoint, AnimationType paramAnimationType)
  {
    animateTo(paramGeoPoint.getLatitudeE6(), paramGeoPoint.getLongitudeE6(), paramAnimationType, 1000, 10);
  }

  public void animateTo(GeoPoint paramGeoPoint, AnimationType paramAnimationType, int paramInt1, int paramInt2)
  {
    animateTo(paramGeoPoint.getLatitudeE6(), paramGeoPoint.getLongitudeE6(), paramAnimationType, paramInt1, paramInt2);
  }

  public void scrollBy(int paramInt1, int paramInt2)
  {
    this.mOsmv.scrollBy(paramInt1, paramInt2);
  }

  public void setCenter(IGeoPoint paramIGeoPoint)
  {
    Point localPoint = TileSystem.LatLongToPixelXY(paramIGeoPoint.getLatitudeE6() / 1000000.0D, paramIGeoPoint.getLongitudeE6() / 1000000.0D, this.mOsmv.getZoomLevel(), null);
    int i = TileSystem.MapSize(this.mOsmv.getZoomLevel()) / 2;
    this.mOsmv.scrollTo(localPoint.x - i, localPoint.y - i);
  }

  public int setZoom(int paramInt)
  {
    return this.mOsmv.setZoomLevel(paramInt);
  }

  public void stopAnimation(boolean paramBoolean)
  {
    AbstractAnimationRunner localAbstractAnimationRunner = this.mCurrentAnimationRunner;
    if ((localAbstractAnimationRunner != null) && (!localAbstractAnimationRunner.isDone()))
    {
      localAbstractAnimationRunner.interrupt();
      if (paramBoolean)
        setCenter(new GeoPoint(localAbstractAnimationRunner.mTargetLatitudeE6, localAbstractAnimationRunner.mTargetLongitudeE6));
    }
  }

  public boolean zoomIn()
  {
    return this.mOsmv.zoomIn();
  }

  public boolean zoomInFixing(int paramInt1, int paramInt2)
  {
    return this.mOsmv.zoomInFixing(paramInt1, paramInt2);
  }

  public boolean zoomInFixing(GeoPoint paramGeoPoint)
  {
    return this.mOsmv.zoomInFixing(paramGeoPoint);
  }

  public boolean zoomOut()
  {
    return this.mOsmv.zoomOut();
  }

  public boolean zoomOutFixing(int paramInt1, int paramInt2)
  {
    return this.mOsmv.zoomOutFixing(paramInt1, paramInt2);
  }

  public boolean zoomOutFixing(GeoPoint paramGeoPoint)
  {
    return this.mOsmv.zoomOutFixing(paramGeoPoint);
  }

  public void zoomToSpan(int paramInt1, int paramInt2)
  {
    if ((paramInt1 <= 0) || (paramInt2 <= 0));
    int i;
    float f;
    do
    {
      return;
      BoundingBoxE6 localBoundingBoxE6 = this.mOsmv.getBoundingBox();
      i = this.mOsmv.getZoomLevel();
      int j = localBoundingBoxE6.getLatitudeSpanE6();
      int k = localBoundingBoxE6.getLongitudeSpanE6();
      f = Math.max(paramInt1 / j, paramInt2 / k);
      if (f <= 1.0F)
        continue;
      this.mOsmv.setZoomLevel(i - MyMath.getNextSquareNumberAbove(f));
      return;
    }
    while (f >= 0.5D);
    this.mOsmv.setZoomLevel(-1 + (i + MyMath.getNextSquareNumberAbove(1.0F / f)));
  }

  public void zoomToSpan(BoundingBoxE6 paramBoundingBoxE6)
  {
    zoomToSpan(paramBoundingBoxE6.getLatitudeSpanE6(), paramBoundingBoxE6.getLongitudeSpanE6());
  }

  private abstract class AbstractAnimationRunner extends Thread
  {
    protected boolean mDone = false;
    protected final int mPanTotalLatitudeE6;
    protected final int mPanTotalLongitudeE6;
    protected final int mSmoothness;
    protected final int mStepDuration;
    protected final int mTargetLatitudeE6;
    protected final int mTargetLongitudeE6;

    public AbstractAnimationRunner(int paramInt1, int paramInt2, int paramInt3, int arg5)
    {
      this.mTargetLatitudeE6 = paramInt1;
      this.mTargetLongitudeE6 = paramInt2;
      this.mSmoothness = paramInt3;
      int i;
      this.mStepDuration = (i / paramInt3);
      GeoPoint localGeoPoint = this$1.mOsmv.getMapCenter();
      this.mPanTotalLatitudeE6 = (localGeoPoint.getLatitudeE6() - paramInt1);
      this.mPanTotalLongitudeE6 = (localGeoPoint.getLongitudeE6() - paramInt2);
    }

    public AbstractAnimationRunner(MapController paramInt1, int paramInt2, int arg4)
    {
      this(paramInt2, i, 10, 1000);
    }

    public boolean isDone()
    {
      return this.mDone;
    }

    public abstract void onRunAnimation();

    public void run()
    {
      onRunAnimation();
      this.mDone = true;
    }
  }

  public static enum AnimationType
  {
    static
    {
      EXPONENTIALDECELERATING = new AnimationType("EXPONENTIALDECELERATING", 1);
      QUARTERCOSINUSALDECELERATING = new AnimationType("QUARTERCOSINUSALDECELERATING", 2);
      HALFCOSINUSALDECELERATING = new AnimationType("HALFCOSINUSALDECELERATING", 3);
      MIDDLEPEAKSPEED = new AnimationType("MIDDLEPEAKSPEED", 4);
      AnimationType[] arrayOfAnimationType = new AnimationType[5];
      arrayOfAnimationType[0] = LINEAR;
      arrayOfAnimationType[1] = EXPONENTIALDECELERATING;
      arrayOfAnimationType[2] = QUARTERCOSINUSALDECELERATING;
      arrayOfAnimationType[3] = HALFCOSINUSALDECELERATING;
      arrayOfAnimationType[4] = MIDDLEPEAKSPEED;
      ENUM$VALUES = arrayOfAnimationType;
    }
  }

  private class CosinusalBasedAnimationRunner extends MapController.AbstractAnimationRunner
    implements MathConstants
  {
    protected final float mAmountStretch;
    protected final float mStart;
    protected final float mStepIncrement;
    protected final float mYOffset;

    public CosinusalBasedAnimationRunner(int paramInt1, int paramFloat1, float paramFloat2, float paramFloat3, float arg6)
    {
      this(paramInt1, paramFloat1, 10, 1000, paramFloat2, paramFloat3, localObject);
    }

    public CosinusalBasedAnimationRunner(int paramInt1, int paramInt2, int paramInt3, int paramFloat1, float paramFloat2, float paramFloat3, float arg8)
    {
      super(paramInt1, paramInt2, paramInt3, paramFloat1);
      Object localObject;
      this.mYOffset = localObject;
      this.mStart = paramFloat2;
      this.mStepIncrement = (paramFloat3 / paramInt3);
      float f = 0.0F;
      for (int i = 0; ; i++)
      {
        if (i >= paramInt3)
        {
          this.mAmountStretch = (1.0F / f);
          setName("QuarterCosinusalDeceleratingAnimationRunner");
          return;
        }
        f = (float)(f + (localObject + Math.cos(paramFloat2 + this.mStepIncrement * i)));
      }
    }

    public void onRunAnimation()
    {
      MapView localMapView = MapController.this.mOsmv;
      GeoPoint localGeoPoint = localMapView.getMapCenter();
      int i = this.mStepDuration;
      float f = this.mAmountStretch;
      int j = 0;
      try
      {
        while (true)
        {
          if (j >= this.mSmoothness)
          {
            localMapView.setMapCenter(this.mTargetLatitudeE6, this.mTargetLongitudeE6);
            return;
          }
          double d = (this.mYOffset + Math.cos(this.mStepIncrement * j + this.mStart)) * f;
          int k = (int)(d * this.mPanTotalLatitudeE6);
          int m = (int)(d * this.mPanTotalLongitudeE6);
          localMapView.setMapCenter(localGeoPoint.getLatitudeE6() - k, localGeoPoint.getLongitudeE6() - m);
          Thread.sleep(i);
          j++;
        }
      }
      catch (Exception localException)
      {
        interrupt();
      }
    }
  }

  private class ExponentialDeceleratingAnimationRunner extends MapController.AbstractAnimationRunner
  {
    public ExponentialDeceleratingAnimationRunner(int paramInt1, int arg3)
    {
      this(paramInt1, i, 10, 1000);
    }

    public ExponentialDeceleratingAnimationRunner(int paramInt1, int paramInt2, int paramInt3, int arg5)
    {
      super(paramInt1, paramInt2, paramInt3, i);
      setName("ExponentialDeceleratingAnimationRunner");
    }

    public void onRunAnimation()
    {
      MapView localMapView = MapController.this.mOsmv;
      GeoPoint localGeoPoint = localMapView.getMapCenter();
      int i = this.mStepDuration;
      int j = 0;
      try
      {
        while (true)
        {
          if (j >= this.mSmoothness)
          {
            localMapView.setMapCenter(this.mTargetLatitudeE6, this.mTargetLongitudeE6);
            return;
          }
          double d = Math.pow(0.5D, j + 1);
          int k = (int)(d * this.mPanTotalLatitudeE6);
          int m = (int)(d * this.mPanTotalLongitudeE6);
          localMapView.setMapCenter(localGeoPoint.getLatitudeE6() - k, localGeoPoint.getLongitudeE6() - m);
          Thread.sleep(i);
          j++;
        }
      }
      catch (Exception localException)
      {
        interrupt();
      }
    }
  }

  protected class HalfCosinusalDeceleratingAnimationRunner extends MapController.CosinusalBasedAnimationRunner
    implements MathConstants
  {
    protected HalfCosinusalDeceleratingAnimationRunner(int paramInt1, int arg3)
    {
      this(paramInt1, i, 10, 1000);
    }

    protected HalfCosinusalDeceleratingAnimationRunner(int paramInt1, int paramInt2, int paramInt3, int arg5)
    {
      super(paramInt1, paramInt2, paramInt3, i, 0.0F, 3.141593F, 1.0F);
    }
  }

  private class LinearAnimationRunner extends MapController.AbstractAnimationRunner
  {
    protected final int mPanPerStepLatitudeE6;
    protected final int mPanPerStepLongitudeE6;

    public LinearAnimationRunner(int paramInt1, int arg3)
    {
      this(paramInt1, i, 10, 1000);
    }

    public LinearAnimationRunner(int paramInt1, int paramInt2, int paramInt3, int arg5)
    {
      super(paramInt1, paramInt2, paramInt3, i);
      GeoPoint localGeoPoint = MapController.this.mOsmv.getMapCenter();
      this.mPanPerStepLatitudeE6 = ((localGeoPoint.getLatitudeE6() - paramInt1) / paramInt3);
      this.mPanPerStepLongitudeE6 = ((localGeoPoint.getLongitudeE6() - paramInt2) / paramInt3);
      setName("LinearAnimationRunner");
    }

    public void onRunAnimation()
    {
      MapView localMapView = MapController.this.mOsmv;
      GeoPoint localGeoPoint = localMapView.getMapCenter();
      int i = this.mPanPerStepLatitudeE6;
      int j = this.mPanPerStepLongitudeE6;
      int k = this.mStepDuration;
      int m;
      do
        try
        {
          m = this.mSmoothness;
          continue;
          localMapView.setMapCenter(localGeoPoint.getLatitudeE6() - i, localGeoPoint.getLongitudeE6() - j);
          Thread.sleep(k);
          m--;
        }
        catch (Exception localException)
        {
          interrupt();
          return;
        }
      while (m > 0);
    }
  }

  protected class MiddlePeakSpeedAnimationRunner extends MapController.CosinusalBasedAnimationRunner
    implements MathConstants
  {
    protected MiddlePeakSpeedAnimationRunner(int paramInt1, int arg3)
    {
      this(paramInt1, i, 10, 1000);
    }

    protected MiddlePeakSpeedAnimationRunner(int paramInt1, int paramInt2, int paramInt3, int arg5)
    {
      super(paramInt1, paramInt2, paramInt3, i, -1.570796F, 3.141593F, 0.0F);
    }
  }

  protected class QuarterCosinusalDeceleratingAnimationRunner extends MapController.CosinusalBasedAnimationRunner
    implements MathConstants
  {
    protected QuarterCosinusalDeceleratingAnimationRunner(int paramInt1, int arg3)
    {
      this(paramInt1, i, 10, 1000);
    }

    protected QuarterCosinusalDeceleratingAnimationRunner(int paramInt1, int paramInt2, int paramInt3, int arg5)
    {
      super(paramInt1, paramInt2, paramInt3, i, 0.0F, 1.570796F, 0.0F);
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.MapController
 * JD-Core Version:    0.6.0
 */