package org.osmdroid.tileprovider;

public class MapTile
{
  public static final int MAPTILE_FAIL_ID = 1;
  public static final int MAPTILE_SUCCESS_ID;
  private final int x;
  private final int y;
  private final int zoomLevel;

  public MapTile(int paramInt1, int paramInt2, int paramInt3)
  {
    this.zoomLevel = paramInt1;
    this.x = paramInt2;
    this.y = paramInt3;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    MapTile localMapTile;
    do
    {
      do
      {
        return false;
        if (paramObject == this)
          return true;
      }
      while (!(paramObject instanceof MapTile));
      localMapTile = (MapTile)paramObject;
    }
    while ((this.zoomLevel != localMapTile.zoomLevel) || (this.x != localMapTile.x) || (this.y != localMapTile.y));
    return true;
  }

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

  public int getZoomLevel()
  {
    return this.zoomLevel;
  }

  public int hashCode()
  {
    return 17 * (37 + this.zoomLevel) * (37 + this.x) * (37 + this.y);
  }

  public String toString()
  {
    return "/" + this.zoomLevel + "/" + this.x + "/" + this.y;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.MapTile
 * JD-Core Version:    0.6.0
 */