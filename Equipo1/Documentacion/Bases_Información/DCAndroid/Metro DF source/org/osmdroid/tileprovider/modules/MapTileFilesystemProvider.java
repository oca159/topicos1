package org.osmdroid.tileprovider.modules;

import android.graphics.drawable.Drawable;
import java.io.File;
import org.osmdroid.tileprovider.IRegisterReceiver;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.MapTileRequestState;
import org.osmdroid.tileprovider.tilesource.BitmapTileSourceBase.LowMemoryException;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapTileFilesystemProvider extends MapTileFileStorageProviderBase
{
  private static final Logger logger = LoggerFactory.getLogger(MapTileFilesystemProvider.class);
  private final long mMaximumCachedFileAge;
  private ITileSource mTileSource;

  public MapTileFilesystemProvider(IRegisterReceiver paramIRegisterReceiver)
  {
    this(paramIRegisterReceiver, TileSourceFactory.DEFAULT_TILE_SOURCE);
  }

  public MapTileFilesystemProvider(IRegisterReceiver paramIRegisterReceiver, ITileSource paramITileSource)
  {
    this(paramIRegisterReceiver, paramITileSource, 604800000L);
  }

  public MapTileFilesystemProvider(IRegisterReceiver paramIRegisterReceiver, ITileSource paramITileSource, long paramLong)
  {
    super(paramIRegisterReceiver, 8, 40);
    this.mTileSource = paramITileSource;
    this.mMaximumCachedFileAge = paramLong;
  }

  public int getMaximumZoomLevel()
  {
    if (this.mTileSource != null)
      return this.mTileSource.getMaximumZoomLevel();
    return 0;
  }

  public int getMinimumZoomLevel()
  {
    if (this.mTileSource != null)
      return this.mTileSource.getMinimumZoomLevel();
    return 22;
  }

  protected String getName()
  {
    return "File System Cache Provider";
  }

  protected String getThreadGroupName()
  {
    return "filesystem";
  }

  protected Runnable getTileLoader()
  {
    return new TileLoader(null);
  }

  public boolean getUsesDataConnection()
  {
    return false;
  }

  public void setTileSource(ITileSource paramITileSource)
  {
    this.mTileSource = paramITileSource;
  }

  private class TileLoader extends MapTileModuleProviderBase.TileLoader
  {
    private TileLoader()
    {
      super();
    }

    public Drawable loadTile(MapTileRequestState paramMapTileRequestState)
      throws MapTileModuleProviderBase.CantContinueException
    {
      if (MapTileFilesystemProvider.this.mTileSource == null)
        return null;
      MapTile localMapTile = paramMapTileRequestState.getMapTile();
      if (!MapTileFilesystemProvider.this.getSdCardAvailable())
        return null;
      File localFile = new File(MapTileFilesystemProvider.TILE_PATH_BASE, MapTileFilesystemProvider.this.mTileSource.getTileRelativeFilenameString(localMapTile) + ".tile");
      if (localFile.exists())
      {
        long l = System.currentTimeMillis();
        int i;
        if (localFile.lastModified() < l - MapTileFilesystemProvider.this.mMaximumCachedFileAge)
          i = 1;
        while (i == 0)
          try
          {
            Drawable localDrawable = MapTileFilesystemProvider.this.mTileSource.getDrawable(localFile.getPath());
            return localDrawable;
            i = 0;
          }
          catch (BitmapTileSourceBase.LowMemoryException localLowMemoryException2)
          {
            MapTileFilesystemProvider.logger.warn("LowMemoryException downloading MapTile: " + localMapTile + " : " + localLowMemoryException2);
            throw new MapTileModuleProviderBase.CantContinueException(MapTileFilesystemProvider.this, localLowMemoryException2);
          }
        try
        {
          tileCandidateLoaded(paramMapTileRequestState, MapTileFilesystemProvider.this.mTileSource.getDrawable(localFile.getPath()));
          return null;
        }
        catch (BitmapTileSourceBase.LowMemoryException localLowMemoryException1)
        {
          MapTileFilesystemProvider.logger.warn("LowMemoryException downloading MapTile: " + localMapTile + " : " + localLowMemoryException1);
          throw new MapTileModuleProviderBase.CantContinueException(MapTileFilesystemProvider.this, localLowMemoryException1);
        }
      }
      return null;
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.modules.MapTileFilesystemProvider
 * JD-Core Version:    0.6.0
 */