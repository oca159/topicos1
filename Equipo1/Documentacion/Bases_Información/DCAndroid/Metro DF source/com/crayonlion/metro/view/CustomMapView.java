package com.crayonlion.metro.view;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import microsoft.mappoint.TileSystem;
import org.osmdroid.ResourceProxy;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.views.MapView;

public class CustomMapView extends MapView
{
  public static final float AUXL = 0.01F;
  public static final int LIMITE = 100;
  private Rect mScrollableAreaLimit;

  public CustomMapView(Context paramContext, int paramInt, ResourceProxy paramResourceProxy, MapTileProviderBase paramMapTileProviderBase)
  {
    super(paramContext, paramInt, paramResourceProxy, paramMapTileProviderBase);
  }

  public void scrollTo(int paramInt1, int paramInt2)
  {
    int i = TileSystem.MapSize(getZoomLevel()) / 2;
    while (paramInt1 < -i)
      paramInt1 += i * 2;
    while (paramInt1 > i)
      paramInt1 -= i * 2;
    while (paramInt2 < -i)
      paramInt2 += i * 2;
    while (paramInt2 > i)
      paramInt2 -= i * 2;
    float f = 1.0F;
    int n;
    int i1;
    if (this.mScrollableAreaLimit != null)
    {
      int j = 22 - getZoomLevel();
      int k = this.mScrollableAreaLimit.left >> j;
      int m = this.mScrollableAreaLimit.top >> j;
      n = this.mScrollableAreaLimit.right >> j;
      i1 = this.mScrollableAreaLimit.bottom >> j;
      f = paramInt1 / k;
      if (paramInt1 >= k)
        break label215;
      paramInt1 = k;
      if (paramInt2 >= m)
        break label227;
      paramInt2 = m;
    }
    while (true)
    {
      if ((f > 0.99F) && (f < 1.01F))
        super.scrollTo(paramInt1, paramInt2);
      if (this.mListener != null)
      {
        ScrollEvent localScrollEvent = new ScrollEvent(this, paramInt1, paramInt2);
        this.mListener.onScroll(localScrollEvent);
      }
      return;
      label215: if (paramInt1 <= n)
        break;
      paramInt1 = n;
      break;
      label227: if (paramInt2 <= i1)
        continue;
      paramInt2 = i1;
    }
  }

  public void setScrollableAreaLimit(BoundingBoxE6 paramBoundingBoxE6)
  {
    int i = TileSystem.MapSize(22) / 2;
    if (paramBoundingBoxE6 == null)
    {
      this.mScrollableAreaLimit = null;
      return;
    }
    Point localPoint1 = TileSystem.LatLongToPixelXY(paramBoundingBoxE6.getLatNorthE6() / 1000000.0D, paramBoundingBoxE6.getLonWestE6() / 1000000.0D, 22, null);
    localPoint1.offset(-i, -i);
    Point localPoint2 = TileSystem.LatLongToPixelXY(paramBoundingBoxE6.getLatSouthE6() / 1000000.0D, paramBoundingBoxE6.getLonEastE6() / 1000000.0D, 22, null);
    localPoint2.offset(-i, -i);
    this.mScrollableAreaLimit = new Rect(localPoint1.x, localPoint1.y, localPoint2.x, localPoint2.y);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.view.CustomMapView
 * JD-Core Version:    0.6.0
 */