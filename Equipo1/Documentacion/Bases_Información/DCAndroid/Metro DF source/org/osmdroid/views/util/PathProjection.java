package org.osmdroid.views.util;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import java.util.Iterator;
import java.util.List;
import microsoft.mappoint.TileSystem;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView.Projection;

public class PathProjection
{
  public static Path toPixels(MapView.Projection paramProjection, List<? extends GeoPoint> paramList, Path paramPath)
  {
    return toPixels(paramProjection, paramList, paramPath, true);
  }

  public static Path toPixels(MapView.Projection paramProjection, List<? extends GeoPoint> paramList, Path paramPath, boolean paramBoolean)
    throws IllegalArgumentException
  {
    if (paramList.size() < 2)
      throw new IllegalArgumentException("List of GeoPoints needs to be at least 2.");
    if (paramPath != null);
    int i;
    Iterator localIterator;
    for (Path localPath = paramPath; ; localPath = new Path())
    {
      localPath.incReserve(paramList.size());
      i = 1;
      localIterator = paramList.iterator();
      if (localIterator.hasNext())
        break;
      return localPath;
    }
    GeoPoint localGeoPoint1 = (GeoPoint)localIterator.next();
    Point localPoint1 = TileSystem.LatLongToPixelXY(localGeoPoint1.getLatitudeE6() / 1000000.0D, localGeoPoint1.getLongitudeE6() / 1000000.0D, paramProjection.getZoomLevel(), null);
    TileSystem.PixelXYToTileXY(localPoint1.x, localPoint1.y, localPoint1);
    Point localPoint2 = TileSystem.TileXYToPixelXY(localPoint1.x, localPoint1.y, null);
    Point localPoint3 = TileSystem.TileXYToPixelXY(localPoint1.x + TileSystem.getTileSize(), localPoint1.y + TileSystem.getTileSize(), null);
    GeoPoint localGeoPoint2 = TileSystem.PixelXYToLatLong(localPoint2.x, localPoint2.y, paramProjection.getZoomLevel(), null);
    GeoPoint localGeoPoint3 = TileSystem.PixelXYToLatLong(localPoint3.x, localPoint3.y, paramProjection.getZoomLevel(), null);
    BoundingBoxE6 localBoundingBoxE6 = new BoundingBoxE6(localGeoPoint2.getLatitudeE6(), localGeoPoint2.getLongitudeE6(), localGeoPoint3.getLatitudeE6(), localGeoPoint3.getLongitudeE6());
    PointF localPointF;
    label272: int i1;
    int i2;
    if ((paramBoolean) && (paramProjection.getZoomLevel() < 7))
    {
      localPointF = localBoundingBoxE6.getRelativePositionOfGeoPointInBoundingBoxWithExactGudermannInterpolation(localGeoPoint1.getLatitudeE6(), localGeoPoint1.getLongitudeE6(), null);
      Rect localRect = paramProjection.getScreenRect();
      Point localPoint4 = TileSystem.PixelXYToTileXY(localRect.centerX(), localRect.centerY(), null);
      Point localPoint5 = TileSystem.TileXYToPixelXY(localPoint4.x, localPoint4.y, null);
      int j = localPoint4.x - localPoint1.x;
      int k = localPoint4.y - localPoint1.y;
      int m = localPoint5.x - j * TileSystem.getTileSize();
      int n = localPoint5.y - k * TileSystem.getTileSize();
      i1 = m + (int)(localPointF.x * TileSystem.getTileSize());
      i2 = n + (int)(localPointF.y * TileSystem.getTileSize());
      if (i == 0)
        break label439;
      localPath.moveTo(i1, i2);
    }
    while (true)
    {
      i = 0;
      break;
      localPointF = localBoundingBoxE6.getRelativePositionOfGeoPointInBoundingBoxWithLinearInterpolation(localGeoPoint1.getLatitudeE6(), localGeoPoint1.getLongitudeE6(), null);
      break label272;
      label439: localPath.lineTo(i1, i2);
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.util.PathProjection
 * JD-Core Version:    0.6.0
 */