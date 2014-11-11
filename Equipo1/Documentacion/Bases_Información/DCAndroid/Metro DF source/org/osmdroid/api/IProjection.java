package org.osmdroid.api;

import android.graphics.Point;
import org.osmdroid.util.GeoPoint;

public abstract interface IProjection
{
  public abstract GeoPoint fromPixels(int paramInt1, int paramInt2);

  public abstract float metersToEquatorPixels(float paramFloat);

  public abstract Point toPixels(GeoPoint paramGeoPoint, Point paramPoint);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.api.IProjection
 * JD-Core Version:    0.6.0
 */