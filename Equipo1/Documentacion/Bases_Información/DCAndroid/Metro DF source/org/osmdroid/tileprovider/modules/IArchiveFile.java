package org.osmdroid.tileprovider.modules;

import java.io.InputStream;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.tilesource.ITileSource;

public abstract interface IArchiveFile
{
  public abstract InputStream getInputStream(ITileSource paramITileSource, MapTile paramMapTile);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.modules.IArchiveFile
 * JD-Core Version:    0.6.0
 */