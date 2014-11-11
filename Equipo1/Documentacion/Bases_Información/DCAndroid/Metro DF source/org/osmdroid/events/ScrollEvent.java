package org.osmdroid.events;

import org.osmdroid.views.MapView;

public class ScrollEvent
  implements MapEvent
{
  protected MapView source;
  protected int x;
  protected int y;

  public ScrollEvent(MapView paramMapView, int paramInt1, int paramInt2)
  {
    this.source = paramMapView;
    this.x = paramInt1;
    this.y = paramInt2;
  }

  public MapView getSource()
  {
    return this.source;
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

  public String toString()
  {
    return "ScrollEvent [source=" + this.source + ", x=" + this.x + ", y=" + this.y + "]";
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.events.ScrollEvent
 * JD-Core Version:    0.6.0
 */