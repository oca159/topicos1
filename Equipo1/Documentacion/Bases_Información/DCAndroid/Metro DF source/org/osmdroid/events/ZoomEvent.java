package org.osmdroid.events;

import org.osmdroid.views.MapView;

public class ZoomEvent
  implements MapEvent
{
  protected MapView source;
  protected int zoomLevel;

  public ZoomEvent(MapView paramMapView, int paramInt)
  {
    this.source = paramMapView;
    this.zoomLevel = paramInt;
  }

  public MapView getSource()
  {
    return this.source;
  }

  public int getZoomLevel()
  {
    return this.zoomLevel;
  }

  public String toString()
  {
    return "ZoomEvent [source=" + this.source + ", zoomLevel=" + this.zoomLevel + "]";
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.events.ZoomEvent
 * JD-Core Version:    0.6.0
 */