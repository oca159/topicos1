package org.osmdroid.tileprovider.modules;

import java.io.InputStream;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.tilesource.ITileSource;

public abstract interface IFilesystemCache
{
  public abstract boolean saveFile(ITileSource paramITileSource, MapTile paramMapTile, InputStream paramInputStream);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.modules.IFilesystemCache
 * JD-Core Version:    0.6.0
 */