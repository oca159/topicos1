package org.osmdroid.api;

public abstract interface IMapView
{
  public abstract IMapController getController();

  public abstract int getLatitudeSpan();

  public abstract int getLongitudeSpan();

  public abstract IGeoPoint getMapCenter();

  public abstract int getMaxZoomLevel();

  public abstract IProjection getProjection();

  public abstract int getZoomLevel();

  public abstract void setBackgroundColor(int paramInt);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.api.IMapView
 * JD-Core Version:    0.6.0
 */