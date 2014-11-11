package org.osmdroid.tileprovider.modules;

import android.graphics.drawable.Drawable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.osmdroid.tileprovider.IMapTileProviderCallback;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.MapTileRequestState;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MapTileModuleProviderBase
  implements OpenStreetMapTileProviderConstants
{
  private static final Logger logger = LoggerFactory.getLogger(MapTileModuleProviderBase.class);
  final LinkedHashMap<MapTile, MapTileRequestState> mPending;
  private final ThreadGroup mThreadPool = new ThreadGroup(getThreadGroupName());
  private final int mThreadPoolSize;
  private final ConcurrentHashMap<MapTile, MapTileRequestState> mWorking;

  public MapTileModuleProviderBase(int paramInt1, int paramInt2)
  {
    this.mThreadPoolSize = paramInt1;
    this.mWorking = new ConcurrentHashMap();
    this.mPending = new LinkedHashMap(paramInt2 + 2, 0.1F, true, paramInt2)
    {
      private static final long serialVersionUID = 6455337315681858866L;

      protected boolean removeEldestEntry(Map.Entry<MapTile, MapTileRequestState> paramEntry)
      {
        return size() > this.val$pPendingQueueSize;
      }
    };
  }

  private void clearQueue()
  {
    synchronized (this.mPending)
    {
      this.mPending.clear();
      this.mWorking.clear();
      return;
    }
  }

  private void removeTileFromQueues(MapTile paramMapTile)
  {
    synchronized (this.mPending)
    {
      this.mPending.remove(paramMapTile);
      this.mWorking.remove(paramMapTile);
      return;
    }
  }

  public void detach()
  {
    clearQueue();
    this.mThreadPool.interrupt();
  }

  public abstract int getMaximumZoomLevel();

  public abstract int getMinimumZoomLevel();

  protected abstract String getName();

  protected abstract String getThreadGroupName();

  protected abstract Runnable getTileLoader();

  public abstract boolean getUsesDataConnection();

  public void loadMapTileAsync(MapTileRequestState paramMapTileRequestState)
  {
    int i = this.mThreadPool.activeCount();
    synchronized (this.mPending)
    {
      this.mPending.put(paramMapTileRequestState.getMapTile(), paramMapTileRequestState);
      if (i < this.mThreadPoolSize)
        new Thread(this.mThreadPool, getTileLoader()).start();
      return;
    }
  }

  public abstract void setTileSource(ITileSource paramITileSource);

  public class CantContinueException extends Exception
  {
    private static final long serialVersionUID = 146526524087765133L;

    public CantContinueException(String arg2)
    {
      super();
    }

    public CantContinueException(Throwable arg2)
    {
      super();
    }
  }

  protected abstract class TileLoader
    implements Runnable
  {
    protected TileLoader()
    {
    }

    private MapTileRequestState nextTile()
    {
      LinkedHashMap localLinkedHashMap = MapTileModuleProviderBase.this.mPending;
      monitorenter;
      Object localObject1 = null;
      while (true)
      {
        try
        {
          Iterator localIterator = MapTileModuleProviderBase.this.mPending.keySet().iterator();
          if (localIterator.hasNext())
            continue;
          if (localObject1 == null)
            continue;
          MapTileModuleProviderBase.this.mWorking.put(localObject1, (MapTileRequestState)MapTileModuleProviderBase.this.mPending.get(localObject1));
          if (localObject1 != null)
          {
            localMapTileRequestState = (MapTileRequestState)MapTileModuleProviderBase.this.mPending.get(localObject1);
            return localMapTileRequestState;
            try
            {
              MapTile localMapTile = (MapTile)localIterator.next();
              boolean bool = MapTileModuleProviderBase.this.mWorking.containsKey(localMapTile);
              if (bool)
                continue;
              localObject1 = localMapTile;
            }
            catch (ConcurrentModificationException localConcurrentModificationException)
            {
            }
            if (localObject1 != null)
              continue;
            localIterator = MapTileModuleProviderBase.this.mPending.keySet().iterator();
            continue;
          }
        }
        finally
        {
          monitorexit;
        }
        MapTileRequestState localMapTileRequestState = null;
      }
    }

    private void tileLoaded(MapTileRequestState paramMapTileRequestState, Drawable paramDrawable)
    {
      MapTileModuleProviderBase.this.removeTileFromQueues(paramMapTileRequestState.getMapTile());
      paramMapTileRequestState.getCallback().mapTileRequestCompleted(paramMapTileRequestState, paramDrawable);
    }

    private void tileLoadedFailed(MapTileRequestState paramMapTileRequestState)
    {
      MapTileModuleProviderBase.this.removeTileFromQueues(paramMapTileRequestState.getMapTile());
      paramMapTileRequestState.getCallback().mapTileRequestFailed(paramMapTileRequestState);
    }

    protected abstract Drawable loadTile(MapTileRequestState paramMapTileRequestState)
      throws MapTileModuleProviderBase.CantContinueException;

    public final void run()
    {
      while (true)
      {
        MapTileRequestState localMapTileRequestState = nextTile();
        if (localMapTileRequestState == null)
          return;
        try
        {
          Drawable localDrawable2 = loadTile(localMapTileRequestState);
          localDrawable1 = localDrawable2;
          if (localDrawable1 != null)
            tileLoaded(localMapTileRequestState, localDrawable1);
        }
        catch (MapTileModuleProviderBase.CantContinueException localCantContinueException)
        {
          while (true)
          {
            MapTileModuleProviderBase.logger.info("Tile loader can't continue", localCantContinueException);
            MapTileModuleProviderBase.this.clearQueue();
            localDrawable1 = null;
          }
        }
        catch (Throwable localThrowable)
        {
          while (true)
          {
            MapTileModuleProviderBase.logger.error("Error downloading tile: " + localMapTileRequestState, localThrowable);
            Drawable localDrawable1 = null;
          }
          tileLoadedFailed(localMapTileRequestState);
        }
      }
    }

    protected void tileCandidateLoaded(MapTileRequestState paramMapTileRequestState, Drawable paramDrawable)
    {
      paramMapTileRequestState.getCallback().mapTileRequestCandidate(paramMapTileRequestState, paramDrawable);
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
 * JD-Core Version:    0.6.0
 */