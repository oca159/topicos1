package org.osmdroid.tileprovider.tilesource;

import org.osmdroid.ResourceProxy.string;
import org.osmdroid.tileprovider.MapTile;

public class QuadTreeTileSource extends OnlineTileSourceBase
{
  public QuadTreeTileSource(String paramString1, ResourceProxy.string paramstring, int paramInt1, int paramInt2, int paramInt3, String paramString2, String[] paramArrayOfString)
  {
    super(paramString1, paramstring, paramInt1, paramInt2, paramInt3, paramString2, paramArrayOfString);
  }

  public String getTileURLString(MapTile paramMapTile)
  {
    return getBaseUrl() + quadTree(paramMapTile) + this.mImageFilenameEnding;
  }

  protected String quadTree(MapTile paramMapTile)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = paramMapTile.getZoomLevel(); ; i--)
    {
      if (i <= 0)
        return localStringBuilder.toString();
      int j = 1 << i - 1;
      int k = j & paramMapTile.getX();
      int m = 0;
      if (k != 0)
        m = 0 + 1;
      if ((j & paramMapTile.getY()) != 0)
        m += 2;
      localStringBuilder.append(m);
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.tilesource.QuadTreeTileSource
 * JD-Core Version:    0.6.0
 */