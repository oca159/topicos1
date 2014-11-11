package org.osmdroid.views.util;

import android.graphics.Point;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.util.constants.MapViewConstants;

public class Mercator
  implements MapViewConstants
{
  static final double DEG2RAD = 0.0174532925199433D;

  public static BoundingBoxE6 getBoundingBoxFromCoords(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    return new BoundingBoxE6(tile2lat(paramInt2, paramInt5), tile2lon(paramInt3, paramInt5), tile2lat(paramInt4, paramInt5), tile2lon(paramInt1, paramInt5));
  }

  public static BoundingBoxE6 getBoundingBoxFromPointInMapTile(Point paramPoint, int paramInt)
  {
    return new BoundingBoxE6(tile2lat(paramPoint.y, paramInt), tile2lon(1 + paramPoint.x, paramInt), tile2lat(1 + paramPoint.y, paramInt), tile2lon(paramPoint.x, paramInt));
  }

  public static Point projectGeoPoint(double paramDouble1, double paramDouble2, int paramInt, Point paramPoint)
  {
    if (paramPoint != null);
    for (Point localPoint = paramPoint; ; localPoint = new Point(0, 0))
    {
      localPoint.x = (int)Math.floor((180.0D + paramDouble2) / 360.0D * (1 << paramInt));
      localPoint.y = (int)Math.floor((1.0D - Math.log(Math.tan(paramDouble1 * 0.0174532925199433D) + 1.0D / Math.cos(paramDouble1 * 0.0174532925199433D)) / 3.141592653589793D) / 2.0D * (1 << paramInt));
      return localPoint;
    }
  }

  public static Point projectGeoPoint(int paramInt1, int paramInt2, int paramInt3, Point paramPoint)
  {
    return projectGeoPoint(1.0E-006D * paramInt1, 1.0E-006D * paramInt2, paramInt3, paramPoint);
  }

  public static Point projectGeoPoint(IGeoPoint paramIGeoPoint, int paramInt, Point paramPoint)
  {
    return projectGeoPoint(1.0E-006D * paramIGeoPoint.getLatitudeE6(), 1.0E-006D * paramIGeoPoint.getLongitudeE6(), paramInt, paramPoint);
  }

  public static GeoPoint projectPoint(int paramInt1, int paramInt2, int paramInt3)
  {
    return new GeoPoint((int)(1000000.0D * tile2lat(paramInt2, paramInt3)), (int)(1000000.0D * tile2lon(paramInt1, paramInt3)));
  }

  public static double tile2lat(int paramInt1, int paramInt2)
  {
    double d = 3.141592653589793D - 6.283185307179586D * paramInt1 / (1 << paramInt2);
    return 57.295779513082323D * Math.atan(0.5D * (Math.exp(d) - Math.exp(-d)));
  }

  public static double tile2lon(int paramInt1, int paramInt2)
  {
    return 360.0D * (paramInt1 / (1 << paramInt2)) - 180.0D;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.util.Mercator
 * JD-Core Version:    0.6.0
 */