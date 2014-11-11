package org.osmdroid.tileprovider.tilesource;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.io.File;
import java.io.InputStream;
import java.util.Random;
import org.osmdroid.ResourceProxy;
import org.osmdroid.ResourceProxy.string;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BitmapTileSourceBase
  implements ITileSource, OpenStreetMapTileProviderConstants
{
  private static int globalOrdinal;
  private static final Logger logger = LoggerFactory.getLogger(BitmapTileSourceBase.class);
  protected final String mImageFilenameEnding;
  private final int mMaximumZoomLevel;
  private final int mMinimumZoomLevel;
  protected final String mName;
  private final int mOrdinal;
  private final ResourceProxy.string mResourceId;
  private final int mTileSizePixels;
  protected final Random random = new Random();

  static
  {
    globalOrdinal = 0;
  }

  public BitmapTileSourceBase(String paramString1, ResourceProxy.string paramstring, int paramInt1, int paramInt2, int paramInt3, String paramString2)
  {
    this.mResourceId = paramstring;
    int i = globalOrdinal;
    globalOrdinal = i + 1;
    this.mOrdinal = i;
    this.mName = paramString1;
    this.mMinimumZoomLevel = paramInt1;
    this.mMaximumZoomLevel = paramInt2;
    this.mTileSizePixels = paramInt3;
    this.mImageFilenameEnding = paramString2;
  }

  public Drawable getDrawable(InputStream paramInputStream)
    throws BitmapTileSourceBase.LowMemoryException
  {
    try
    {
      Bitmap localBitmap = BitmapFactory.decodeStream(paramInputStream);
      if (localBitmap != null)
      {
        BitmapDrawable localBitmapDrawable = new BitmapDrawable(localBitmap);
        return localBitmapDrawable;
      }
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      logger.error("OutOfMemoryError loading bitmap");
      System.gc();
      throw new LowMemoryException(localOutOfMemoryError);
    }
    return null;
  }

  public Drawable getDrawable(String paramString)
  {
    try
    {
      Bitmap localBitmap = BitmapFactory.decodeFile(paramString);
      if (localBitmap != null)
      {
        BitmapDrawable localBitmapDrawable = new BitmapDrawable(localBitmap);
        return localBitmapDrawable;
      }
      try
      {
        new File(paramString).delete();
        return null;
      }
      catch (Throwable localThrowable)
      {
        while (true)
          logger.error("Error deleting invalid file: " + paramString, localThrowable);
      }
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      while (true)
      {
        logger.error("OutOfMemoryError loading bitmap: " + paramString);
        System.gc();
      }
    }
  }

  public int getMaximumZoomLevel()
  {
    return this.mMaximumZoomLevel;
  }

  public int getMinimumZoomLevel()
  {
    return this.mMinimumZoomLevel;
  }

  public String getTileRelativeFilenameString(MapTile paramMapTile)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(pathBase());
    localStringBuilder.append('/');
    localStringBuilder.append(paramMapTile.getZoomLevel());
    localStringBuilder.append('/');
    localStringBuilder.append(paramMapTile.getX());
    localStringBuilder.append('/');
    localStringBuilder.append(paramMapTile.getY());
    localStringBuilder.append(imageFilenameEnding());
    return localStringBuilder.toString();
  }

  public int getTileSizePixels()
  {
    return this.mTileSizePixels;
  }

  public String imageFilenameEnding()
  {
    return this.mImageFilenameEnding;
  }

  public String localizedName(ResourceProxy paramResourceProxy)
  {
    return paramResourceProxy.getString(this.mResourceId);
  }

  public String name()
  {
    return this.mName;
  }

  public int ordinal()
  {
    return this.mOrdinal;
  }

  public String pathBase()
  {
    return this.mName;
  }

  public final class LowMemoryException extends Exception
  {
    private static final long serialVersionUID = 146526524087765134L;

    public LowMemoryException(String arg2)
    {
      super();
    }

    public LowMemoryException(Throwable arg2)
    {
      super();
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.tilesource.BitmapTileSourceBase
 * JD-Core Version:    0.6.0
 */