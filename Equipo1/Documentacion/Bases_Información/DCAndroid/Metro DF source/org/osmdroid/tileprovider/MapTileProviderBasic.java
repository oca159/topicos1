package org.osmdroid.tileprovider;

import android.content.Context;
import java.util.List;
import org.osmdroid.tileprovider.modules.INetworkAvailablityCheck;
import org.osmdroid.tileprovider.modules.MapTileDownloader;
import org.osmdroid.tileprovider.modules.MapTileFileArchiveProvider;
import org.osmdroid.tileprovider.modules.MapTileFilesystemProvider;
import org.osmdroid.tileprovider.modules.NetworkAvailabliltyCheck;
import org.osmdroid.tileprovider.modules.TileWriter;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.util.SimpleRegisterReceiver;

public class MapTileProviderBasic extends MapTileProviderArray
  implements IMapTileProviderCallback
{
  public MapTileProviderBasic(Context paramContext)
  {
    this(paramContext, TileSourceFactory.DEFAULT_TILE_SOURCE);
  }

  public MapTileProviderBasic(Context paramContext, ITileSource paramITileSource)
  {
    this(new SimpleRegisterReceiver(paramContext), new NetworkAvailabliltyCheck(paramContext), paramITileSource);
  }

  public MapTileProviderBasic(IRegisterReceiver paramIRegisterReceiver, INetworkAvailablityCheck paramINetworkAvailablityCheck, ITileSource paramITileSource)
  {
    super(paramITileSource, paramIRegisterReceiver);
    TileWriter localTileWriter = new TileWriter();
    MapTileFilesystemProvider localMapTileFilesystemProvider = new MapTileFilesystemProvider(paramIRegisterReceiver, paramITileSource);
    this.mTileProviderList.add(localMapTileFilesystemProvider);
    MapTileFileArchiveProvider localMapTileFileArchiveProvider = new MapTileFileArchiveProvider(paramIRegisterReceiver, paramITileSource);
    this.mTileProviderList.add(localMapTileFileArchiveProvider);
    MapTileDownloader localMapTileDownloader = new MapTileDownloader(paramITileSource, localTileWriter, paramINetworkAvailablityCheck);
    this.mTileProviderList.add(localMapTileDownloader);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.MapTileProviderBasic
 * JD-Core Version:    0.6.0
 */