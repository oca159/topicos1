package org.osmdroid.api;

public abstract interface IMapController
{
  public abstract void animateTo(IGeoPoint paramIGeoPoint);

  public abstract void setCenter(IGeoPoint paramIGeoPoint);

  public abstract int setZoom(int paramInt);

  public abstract boolean zoomIn();

  public abstract boolean zoomInFixing(int paramInt1, int paramInt2);

  public abstract boolean zoomOut();

  public abstract boolean zoomOutFixing(int paramInt1, int paramInt2);

  public abstract void zoomToSpan(int paramInt1, int paramInt2);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.api.IMapController
 * JD-Core Version:    0.6.0
 */