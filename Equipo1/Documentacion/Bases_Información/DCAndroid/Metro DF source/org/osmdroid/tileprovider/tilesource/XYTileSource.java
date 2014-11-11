package org.osmdroid.tileprovider.tilesource;

import org.osmdroid.ResourceProxy.string;
import org.osmdroid.tileprovider.MapTile;

public class XYTileSource extends OnlineTileSourceBase
{
  public XYTileSource(String paramString1, ResourceProxy.string paramstring, int paramInt1, int paramInt2, int paramInt3, String paramString2, String[] paramArrayOfString)
  {
    super(paramString1, paramstring, paramInt1, paramInt2, paramInt3, paramString2, paramArrayOfString);
  }

  public String getTileURLString(MapTile paramMapTile)
  {
    return getBaseUrl() + paramMapTile.getZoomLevel() + "/" + paramMapTile.getX() + "/" + paramMapTile.getY() + this.mImageFilenameEnding;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.tilesource.XYTileSource
 * JD-Core Version:    0.6.0
 */