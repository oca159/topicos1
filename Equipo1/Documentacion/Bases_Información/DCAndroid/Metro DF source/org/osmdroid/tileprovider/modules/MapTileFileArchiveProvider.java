package org.osmdroid.tileprovider.modules;

import android.graphics.drawable.Drawable;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.osmdroid.tileprovider.IRegisterReceiver;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.MapTileRequestState;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.util.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapTileFileArchiveProvider extends MapTileFileStorageProviderBase
{
  private static final Logger logger = LoggerFactory.getLogger(MapTileFileArchiveProvider.class);
  private final ArrayList<IArchiveFile> mArchiveFiles = new ArrayList();
  protected ITileSource mTileSource;
  private boolean specificArchivesProvided;

  public MapTileFileArchiveProvider(IRegisterReceiver paramIRegisterReceiver, ITileSource paramITileSource)
  {
    this(paramIRegisterReceiver, paramITileSource, null);
  }

  public MapTileFileArchiveProvider(IRegisterReceiver paramIRegisterReceiver, ITileSource paramITileSource, IArchiveFile[] paramArrayOfIArchiveFile)
  {
    super(paramIRegisterReceiver, 8, 40);
    this.mTileSource = paramITileSource;
    boolean bool;
    if (paramArrayOfIArchiveFile == null)
    {
      bool = false;
      this.specificArchivesProvided = bool;
      if (paramArrayOfIArchiveFile != null)
        break label53;
      findArchiveFiles();
    }
    while (true)
    {
      return;
      bool = true;
      break;
      label53: for (int i = -1 + paramArrayOfIArchiveFile.length; i >= 0; i--)
        this.mArchiveFiles.add(paramArrayOfIArchiveFile[i]);
    }
  }

  private void findArchiveFiles()
  {
    this.mArchiveFiles.clear();
    if (!getSdCardAvailable());
    while (true)
    {
      return;
      File[] arrayOfFile = OSMDROID_PATH.listFiles();
      if (arrayOfFile == null)
        continue;
      int i = arrayOfFile.length;
      for (int j = 0; j < i; j++)
      {
        IArchiveFile localIArchiveFile = ArchiveFileFactory.getArchiveFile(arrayOfFile[j]);
        if (localIArchiveFile == null)
          continue;
        this.mArchiveFiles.add(localIArchiveFile);
      }
    }
  }

  private InputStream getInputStream(MapTile paramMapTile)
  {
    monitorenter;
    try
    {
      Iterator localIterator = this.mArchiveFiles.iterator();
      boolean bool = localIterator.hasNext();
      Object localObject2;
      if (!bool)
        localObject2 = null;
      while (true)
      {
        return localObject2;
        InputStream localInputStream = ((IArchiveFile)localIterator.next()).getInputStream(this.mTileSource, paramMapTile);
        localObject2 = localInputStream;
        if (localObject2 == null)
          break;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
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
    return "File Archive Provider";
  }

  protected String getThreadGroupName()
  {
    return "filearchive";
  }

  protected Runnable getTileLoader()
  {
    return new TileLoader(null);
  }

  public boolean getUsesDataConnection()
  {
    return false;
  }

  protected void onMediaMounted()
  {
    if (!this.specificArchivesProvided)
      findArchiveFiles();
  }

  protected void onMediaUnmounted()
  {
    if (!this.specificArchivesProvided)
      findArchiveFiles();
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
    {
      ITileSource localITileSource = MapTileFileArchiveProvider.this.mTileSource;
      Object localObject1 = null;
      if (localITileSource == null);
      InputStream localInputStream;
      do
      {
        while (true)
        {
          return localObject1;
          MapTile localMapTile = paramMapTileRequestState.getMapTile();
          boolean bool = MapTileFileArchiveProvider.this.getSdCardAvailable();
          localObject1 = null;
          if (!bool)
            continue;
          localInputStream = null;
          try
          {
            localInputStream = MapTileFileArchiveProvider.this.getInputStream(localMapTile);
            if (localInputStream == null)
              break;
            Drawable localDrawable = MapTileFileArchiveProvider.this.mTileSource.getDrawable(localInputStream);
            localObject1 = localDrawable;
            return localObject1;
          }
          catch (Throwable localThrowable)
          {
            MapTileFileArchiveProvider.logger.error("Error loading tile", localThrowable);
            localObject1 = null;
            return null;
          }
          finally
          {
            if (localInputStream != null)
              StreamUtils.closeStream(localInputStream);
          }
        }
        localObject1 = null;
      }
      while (localInputStream == null);
      StreamUtils.closeStream(localInputStream);
      return null;
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.modules.MapTileFileArchiveProvider
 * JD-Core Version:    0.6.0
 */