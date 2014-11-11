package org.osmdroid.tileprovider;

import android.graphics.drawable.Drawable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;

public final class MapTileCache
  implements OpenStreetMapTileProviderConstants
{
  protected LRUMapTileCache mCachedTiles;
  private final ReadWriteLock mReadWriteLock = new ReentrantReadWriteLock();

  public MapTileCache()
  {
    this(9);
  }

  public MapTileCache(int paramInt)
  {
    this.mCachedTiles = new LRUMapTileCache(paramInt);
  }

  public void clear()
  {
    this.mReadWriteLock.writeLock().lock();
    this.mCachedTiles.clear();
    this.mReadWriteLock.writeLock().unlock();
  }

  public boolean containsTile(MapTile paramMapTile)
  {
    this.mReadWriteLock.readLock().lock();
    boolean bool = this.mCachedTiles.containsKey(paramMapTile);
    this.mReadWriteLock.readLock().unlock();
    return bool;
  }

  public void ensureCapacity(int paramInt)
  {
    this.mReadWriteLock.readLock().lock();
    this.mCachedTiles.ensureCapacity(paramInt);
    this.mReadWriteLock.readLock().unlock();
  }

  public Drawable getMapTile(MapTile paramMapTile)
  {
    this.mReadWriteLock.readLock().lock();
    Drawable localDrawable = (Drawable)this.mCachedTiles.get(paramMapTile);
    this.mReadWriteLock.readLock().unlock();
    return localDrawable;
  }

  public void putTile(MapTile paramMapTile, Drawable paramDrawable)
  {
    if (paramDrawable != null)
    {
      this.mReadWriteLock.writeLock().lock();
      this.mCachedTiles.put(paramMapTile, paramDrawable);
      this.mReadWriteLock.writeLock().unlock();
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.MapTileCache
 * JD-Core Version:    0.6.0
 */