package org.osmdroid.tileprovider.tilesource;

public abstract interface IStyledTileSource<T>
{
  public abstract T getStyle();

  public abstract void setStyle(T paramT);

  public abstract void setStyle(String paramString);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.tilesource.IStyledTileSource
 * JD-Core Version:    0.6.0
 */