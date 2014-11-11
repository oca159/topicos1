package org.osmdroid.tileprovider;

import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapTileProviderArray extends MapTileProviderBase
{
  private static final Logger logger = LoggerFactory.getLogger(MapTileProviderArray.class);
  protected final List<MapTileModuleProviderBase> mTileProviderList = new ArrayList();
  private final ConcurrentHashMap<MapTileRequestState, MapTile> mWorking = new ConcurrentHashMap();

  protected MapTileProviderArray(ITileSource paramITileSource, IRegisterReceiver paramIRegisterReceiver)
  {
    this(paramITileSource, paramIRegisterReceiver, new MapTileModuleProviderBase[0]);
  }

  public MapTileProviderArray(ITileSource paramITileSource, IRegisterReceiver paramIRegisterReceiver, MapTileModuleProviderBase[] paramArrayOfMapTileModuleProviderBase)
  {
    super(paramITileSource);
    Collections.addAll(this.mTileProviderList, paramArrayOfMapTileModuleProviderBase);
  }

  private MapTileModuleProviderBase findNextAppropriateProvider(MapTileRequestState paramMapTileRequestState)
  {
    MapTileModuleProviderBase localMapTileModuleProviderBase;
    do
      localMapTileModuleProviderBase = paramMapTileRequestState.getNextProvider();
    while ((localMapTileModuleProviderBase != null) && ((!getProviderExists(localMapTileModuleProviderBase)) || ((!useDataConnection()) && (localMapTileModuleProviderBase.getUsesDataConnection()))));
    return localMapTileModuleProviderBase;
  }

  public void detach()
  {
    synchronized (this.mTileProviderList)
    {
      Iterator localIterator = this.mTileProviderList.iterator();
      if (!localIterator.hasNext())
        return;
      ((MapTileModuleProviderBase)localIterator.next()).detach();
    }
  }

  public Drawable getMapTile(MapTile paramMapTile)
  {
    if (this.mTileCache.containsTile(paramMapTile))
      return this.mTileCache.getMapTile(paramMapTile);
    MapTileRequestState localMapTileRequestState;
    synchronized (this.mWorking)
    {
      boolean bool = this.mWorking.containsValue(paramMapTile);
      if (!bool)
        synchronized (this.mTileProviderList)
        {
          MapTileModuleProviderBase[] arrayOfMapTileModuleProviderBase = new MapTileModuleProviderBase[this.mTileProviderList.size()];
          localMapTileRequestState = new MapTileRequestState(paramMapTile, (MapTileModuleProviderBase[])this.mTileProviderList.toArray(arrayOfMapTileModuleProviderBase), this);
        }
    }
    while (true)
    {
      synchronized (this.mWorking)
      {
        if (this.mWorking.containsValue(paramMapTile))
        {
          return null;
          localObject1 = finally;
          monitorexit;
          throw localObject1;
          localObject2 = finally;
          monitorexit;
          throw localObject2;
        }
        this.mWorking.put(localMapTileRequestState, paramMapTile);
        MapTileModuleProviderBase localMapTileModuleProviderBase = findNextAppropriateProvider(localMapTileRequestState);
        if (localMapTileModuleProviderBase != null)
        {
          localMapTileModuleProviderBase.loadMapTileAsync(localMapTileRequestState);
          return null;
        }
      }
      mapTileRequestFailed(localMapTileRequestState);
    }
  }

  public int getMaximumZoomLevel()
  {
    int i = 0;
    synchronized (this.mTileProviderList)
    {
      Iterator localIterator = this.mTileProviderList.iterator();
      MapTileModuleProviderBase localMapTileModuleProviderBase;
      do
      {
        if (!localIterator.hasNext())
          return i;
        localMapTileModuleProviderBase = (MapTileModuleProviderBase)localIterator.next();
      }
      while (localMapTileModuleProviderBase.getMaximumZoomLevel() <= i);
      i = localMapTileModuleProviderBase.getMaximumZoomLevel();
    }
  }

  public int getMinimumZoomLevel()
  {
    int i = 22;
    synchronized (this.mTileProviderList)
    {
      Iterator localIterator = this.mTileProviderList.iterator();
      MapTileModuleProviderBase localMapTileModuleProviderBase;
      do
      {
        if (!localIterator.hasNext())
          return i;
        localMapTileModuleProviderBase = (MapTileModuleProviderBase)localIterator.next();
      }
      while (localMapTileModuleProviderBase.getMinimumZoomLevel() >= i);
      i = localMapTileModuleProviderBase.getMinimumZoomLevel();
    }
  }

  public boolean getProviderExists(MapTileModuleProviderBase paramMapTileModuleProviderBase)
  {
    synchronized (this.mTileProviderList)
    {
      boolean bool = this.mTileProviderList.contains(paramMapTileModuleProviderBase);
      return bool;
    }
  }

  public void mapTileRequestCompleted(MapTileRequestState paramMapTileRequestState, Drawable paramDrawable)
  {
    synchronized (this.mWorking)
    {
      this.mWorking.remove(paramMapTileRequestState);
      super.mapTileRequestCompleted(paramMapTileRequestState, paramDrawable);
      return;
    }
  }

  public void mapTileRequestFailed(MapTileRequestState paramMapTileRequestState)
  {
    MapTileModuleProviderBase localMapTileModuleProviderBase = findNextAppropriateProvider(paramMapTileRequestState);
    if (localMapTileModuleProviderBase != null)
    {
      localMapTileModuleProviderBase.loadMapTileAsync(paramMapTileRequestState);
      return;
    }
    synchronized (this.mWorking)
    {
      this.mWorking.remove(paramMapTileRequestState);
      super.mapTileRequestFailed(paramMapTileRequestState);
      return;
    }
  }

  public void setTileSource(ITileSource paramITileSource)
  {
    super.setTileSource(paramITileSource);
    Iterator localIterator = this.mTileProviderList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((MapTileModuleProviderBase)localIterator.next()).setTileSource(paramITileSource);
      clearTileCache();
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.MapTileProviderArray
 * JD-Core Version:    0.6.0
 */