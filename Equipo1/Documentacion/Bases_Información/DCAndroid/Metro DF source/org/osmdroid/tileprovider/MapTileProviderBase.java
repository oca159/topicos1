package org.osmdroid.tileprovider;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MapTileProviderBase
  implements IMapTileProviderCallback, OpenStreetMapTileProviderConstants
{
  private static final Logger logger = LoggerFactory.getLogger(MapTileProviderBase.class);
  protected final MapTileCache mTileCache = new MapTileCache();
  protected Handler mTileRequestCompleteHandler;
  private ITileSource mTileSource;
  protected boolean mUseDataConnection = true;

  public MapTileProviderBase(ITileSource paramITileSource)
  {
    this(paramITileSource, null);
  }

  public MapTileProviderBase(ITileSource paramITileSource, Handler paramHandler)
  {
    this.mTileRequestCompleteHandler = paramHandler;
    this.mTileSource = paramITileSource;
  }

  public void clearTileCache()
  {
    this.mTileCache.clear();
  }

  public abstract void detach();

  public void ensureCapacity(int paramInt)
  {
    this.mTileCache.ensureCapacity(paramInt);
  }

  public abstract Drawable getMapTile(MapTile paramMapTile);

  public abstract int getMaximumZoomLevel();

  public abstract int getMinimumZoomLevel();

  public ITileSource getTileSource()
  {
    return this.mTileSource;
  }

  public void mapTileRequestCandidate(MapTileRequestState paramMapTileRequestState, Drawable paramDrawable)
  {
    mapTileRequestCompleted(paramMapTileRequestState, paramDrawable);
  }

  public void mapTileRequestCompleted(MapTileRequestState paramMapTileRequestState, Drawable paramDrawable)
  {
    MapTile localMapTile = paramMapTileRequestState.getMapTile();
    if (paramDrawable != null)
      this.mTileCache.putTile(localMapTile, paramDrawable);
    if (this.mTileRequestCompleteHandler != null)
      this.mTileRequestCompleteHandler.sendEmptyMessage(0);
  }

  public void mapTileRequestFailed(MapTileRequestState paramMapTileRequestState)
  {
    paramMapTileRequestState.getMapTile();
    if (this.mTileRequestCompleteHandler != null)
      this.mTileRequestCompleteHandler.sendEmptyMessage(1);
  }

  public void setTileRequestCompleteHandler(Handler paramHandler)
  {
    this.mTileRequestCompleteHandler = paramHandler;
  }

  public void setTileSource(ITileSource paramITileSource)
  {
    this.mTileSource = paramITileSource;
    clearTileCache();
  }

  public void setUseDataConnection(boolean paramBoolean)
  {
    this.mUseDataConnection = paramBoolean;
  }

  public boolean useDataConnection()
  {
    return this.mUseDataConnection;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.MapTileProviderBase
 * JD-Core Version:    0.6.0
 */