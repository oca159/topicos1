package org.osmdroid.tileprovider;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LRUMapTileCache extends LinkedHashMap<MapTile, Drawable>
{
  private static final Logger logger = LoggerFactory.getLogger(LRUMapTileCache.class);
  private static final long serialVersionUID = -541142277575493335L;
  private int mCapacity;

  public LRUMapTileCache(int paramInt)
  {
    super(paramInt + 2, 0.1F, true);
    this.mCapacity = paramInt;
  }

  public void clear()
  {
    while (true)
    {
      if (size() <= 0)
      {
        super.clear();
        return;
      }
      remove(keySet().iterator().next());
    }
  }

  public void ensureCapacity(int paramInt)
  {
    if (paramInt > this.mCapacity)
    {
      logger.info("Tile cache increased from " + this.mCapacity + " to " + paramInt);
      this.mCapacity = paramInt;
    }
  }

  public Drawable remove(Object paramObject)
  {
    Drawable localDrawable = (Drawable)super.remove(paramObject);
    if ((localDrawable instanceof BitmapDrawable))
    {
      Bitmap localBitmap = ((BitmapDrawable)localDrawable).getBitmap();
      if (localBitmap != null)
        localBitmap.recycle();
    }
    return localDrawable;
  }

  protected boolean removeEldestEntry(Map.Entry<MapTile, Drawable> paramEntry)
  {
    if (size() > this.mCapacity)
      remove(paramEntry.getKey());
    return false;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.LRUMapTileCache
 * JD-Core Version:    0.6.0
 */