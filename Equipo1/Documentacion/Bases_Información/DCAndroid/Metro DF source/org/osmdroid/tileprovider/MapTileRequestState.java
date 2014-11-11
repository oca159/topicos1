package org.osmdroid.tileprovider;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;

public class MapTileRequestState
{
  private final IMapTileProviderCallback mCallback;
  private MapTileModuleProviderBase mCurrentProvider;
  private final MapTile mMapTile;
  private final Queue<MapTileModuleProviderBase> mProviderQueue = new LinkedList();

  public MapTileRequestState(MapTile paramMapTile, MapTileModuleProviderBase[] paramArrayOfMapTileModuleProviderBase, IMapTileProviderCallback paramIMapTileProviderCallback)
  {
    Collections.addAll(this.mProviderQueue, paramArrayOfMapTileModuleProviderBase);
    this.mMapTile = paramMapTile;
    this.mCallback = paramIMapTileProviderCallback;
  }

  public IMapTileProviderCallback getCallback()
  {
    return this.mCallback;
  }

  public MapTileModuleProviderBase getCurrentProvider()
  {
    return this.mCurrentProvider;
  }

  public MapTile getMapTile()
  {
    return this.mMapTile;
  }

  public MapTileModuleProviderBase getNextProvider()
  {
    this.mCurrentProvider = ((MapTileModuleProviderBase)this.mProviderQueue.poll());
    return this.mCurrentProvider;
  }

  public boolean isEmpty()
  {
    return this.mProviderQueue.isEmpty();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.MapTileRequestState
 * JD-Core Version:    0.6.0
 */