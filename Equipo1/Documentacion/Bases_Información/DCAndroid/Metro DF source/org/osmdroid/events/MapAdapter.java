package org.osmdroid.events;

public abstract class MapAdapter
  implements MapListener
{
  public boolean onScroll(ScrollEvent paramScrollEvent)
  {
    return false;
  }

  public boolean onZoom(ZoomEvent paramZoomEvent)
  {
    return false;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.events.MapAdapter
 * JD-Core Version:    0.6.0
 */