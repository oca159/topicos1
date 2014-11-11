package microsoft.mappoint;

import android.graphics.Point;
import org.osmdroid.util.GeoPoint;

public final class TileSystem
{
  private static final double EarthRadius = 6378137.0D;
  private static final double MaxLatitude = 85.051128779999999D;
  private static final double MaxLongitude = 180.0D;
  private static final double MinLatitude = -85.051128779999999D;
  private static final double MinLongitude = -180.0D;
  protected static int mTileSize = 256;

  private static double Clip(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    return Math.min(Math.max(paramDouble1, paramDouble2), paramDouble3);
  }

  public static double GroundResolution(double paramDouble, int paramInt)
  {
    return 6378137.0D * (3.141592653589793D * (2.0D * Math.cos(3.141592653589793D * Clip(paramDouble, -85.051128779999999D, 85.051128779999999D) / 180.0D))) / MapSize(paramInt);
  }

  public static Point LatLongToPixelXY(double paramDouble1, double paramDouble2, int paramInt, Point paramPoint)
  {
    if (paramPoint == null);
    for (Point localPoint = new Point(); ; localPoint = paramPoint)
    {
      double d1 = Clip(paramDouble1, -85.051128779999999D, 85.051128779999999D);
      double d2 = (180.0D + Clip(paramDouble2, -180.0D, 180.0D)) / 360.0D;
      double d3 = Math.sin(3.141592653589793D * d1 / 180.0D);
      double d4 = 0.5D - Math.log((1.0D + d3) / (1.0D - d3)) / 12.566370614359172D;
      int i = MapSize(paramInt);
      localPoint.x = (int)Clip(0.5D + d2 * i, 0.0D, i - 1);
      localPoint.y = (int)Clip(0.5D + d4 * i, 0.0D, i - 1);
      return localPoint;
    }
  }

  public static double MapScale(double paramDouble, int paramInt1, int paramInt2)
  {
    return GroundResolution(paramDouble, paramInt1) * paramInt2 / 0.0254D;
  }

  public static int MapSize(int paramInt)
  {
    return mTileSize << paramInt;
  }

  public static GeoPoint PixelXYToLatLong(int paramInt1, int paramInt2, int paramInt3, GeoPoint paramGeoPoint)
  {
    if (paramGeoPoint == null);
    for (GeoPoint localGeoPoint = new GeoPoint(0, 0); ; localGeoPoint = paramGeoPoint)
    {
      double d1 = MapSize(paramInt3);
      double d2 = Clip(paramInt1, 0.0D, d1 - 1.0D) / d1 - 0.5D;
      double d3 = 90.0D - 360.0D * Math.atan(Math.exp(3.141592653589793D * (2.0D * -(0.5D - Clip(paramInt2, 0.0D, d1 - 1.0D) / d1)))) / 3.141592653589793D;
      double d4 = 360.0D * d2;
      localGeoPoint.setLatitudeE6((int)(1000000.0D * d3));
      localGeoPoint.setLongitudeE6((int)(1000000.0D * d4));
      return localGeoPoint;
    }
  }

  public static Point PixelXYToTileXY(int paramInt1, int paramInt2, Point paramPoint)
  {
    if (paramPoint == null);
    for (Point localPoint = new Point(); ; localPoint = paramPoint)
    {
      localPoint.x = (paramInt1 / mTileSize);
      localPoint.y = (paramInt2 / mTileSize);
      return localPoint;
    }
  }

  public static Point QuadKeyToTileXY(String paramString, Point paramPoint)
  {
    if (paramPoint == null);
    int i;
    int j;
    int k;
    int m;
    for (Point localPoint = new Point(); ; localPoint = paramPoint)
    {
      i = 0;
      j = 0;
      k = paramString.length();
      m = k;
      if (m > 0)
        break;
      localPoint.set(i, j);
      return localPoint;
    }
    int n = 1 << m - 1;
    switch (paramString.charAt(k - m))
    {
    default:
      throw new IllegalArgumentException("Invalid QuadKey digit sequence.");
    case '1':
      i |= n;
    case '0':
    case '2':
    case '3':
    }
    while (true)
    {
      m--;
      break;
      j |= n;
      continue;
      i |= n;
      j |= n;
    }
  }

  public static Point TileXYToPixelXY(int paramInt1, int paramInt2, Point paramPoint)
  {
    if (paramPoint == null);
    for (Point localPoint = new Point(); ; localPoint = paramPoint)
    {
      localPoint.x = (paramInt1 * mTileSize);
      localPoint.y = (paramInt2 * mTileSize);
      return localPoint;
    }
  }

  public static String TileXYToQuadKey(int paramInt1, int paramInt2, int paramInt3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = paramInt3; ; i--)
    {
      if (i <= 0)
        return localStringBuilder.toString();
      char c = '0';
      int j = 1 << i - 1;
      if ((paramInt1 & j) != 0)
        c = (char)49;
      if ((paramInt2 & j) != 0)
        c = (char)('\001' + (char)(c + '\001'));
      localStringBuilder.append(c);
    }
  }

  public static int getTileSize()
  {
    return mTileSize;
  }

  public static void setTileSize(int paramInt)
  {
    mTileSize = paramInt;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     microsoft.mappoint.TileSystem
 * JD-Core Version:    0.6.0
 */