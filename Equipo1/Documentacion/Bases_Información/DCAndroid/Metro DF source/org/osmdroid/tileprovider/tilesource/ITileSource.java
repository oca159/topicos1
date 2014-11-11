package org.osmdroid.tileprovider.tilesource;

import android.graphics.drawable.Drawable;
import java.io.InputStream;
import org.osmdroid.ResourceProxy;
import org.osmdroid.tileprovider.MapTile;

public abstract interface ITileSource
{
  public abstract Drawable getDrawable(InputStream paramInputStream)
    throws BitmapTileSourceBase.LowMemoryException;

  public abstract Drawable getDrawable(String paramString)
    throws BitmapTileSourceBase.LowMemoryException;

  public abstract int getMaximumZoomLevel();

  public abstract int getMinimumZoomLevel();

  public abstract String getTileRelativeFilenameString(MapTile paramMapTile);

  public abstract int getTileSizePixels();

  public abstract String localizedName(ResourceProxy paramResourceProxy);

  public abstract String name();

  public abstract int ordinal();
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.tilesource.ITileSource
 * JD-Core Version:    0.6.0
 */