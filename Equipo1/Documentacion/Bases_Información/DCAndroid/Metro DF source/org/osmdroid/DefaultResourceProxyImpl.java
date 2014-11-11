package org.osmdroid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import org.osmdroid.views.util.constants.MapViewConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultResourceProxyImpl
  implements ResourceProxy, MapViewConstants
{
  private static final Logger logger = LoggerFactory.getLogger(DefaultResourceProxyImpl.class);
  private DisplayMetrics mDisplayMetrics;

  public DefaultResourceProxyImpl(Context paramContext)
  {
    if (paramContext != null)
      this.mDisplayMetrics = paramContext.getResources().getDisplayMetrics();
  }

  private BitmapFactory.Options getBitmapOptions()
  {
    try
    {
      Field localField1 = DisplayMetrics.class.getDeclaredField("DENSITY_DEFAULT");
      Field localField2 = BitmapFactory.Options.class.getDeclaredField("inDensity");
      Field localField3 = BitmapFactory.Options.class.getDeclaredField("inTargetDensity");
      Field localField4 = DisplayMetrics.class.getDeclaredField("densityDpi");
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localField2.setInt(localOptions, localField1.getInt(null));
      localField3.setInt(localOptions, localField4.getInt(this.mDisplayMetrics));
      return localOptions;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      return null;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      label76: break label76;
    }
  }

  public Bitmap getBitmap(ResourceProxy.bitmap parambitmap)
  {
    InputStream localInputStream = null;
    try
    {
      String str = parambitmap.name() + ".png";
      localInputStream = getClass().getResourceAsStream(str);
      if (localInputStream == null)
        throw new IllegalArgumentException("Resource not found: " + str);
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      logger.error("OutOfMemoryError getting bitmap resource: " + parambitmap);
      System.gc();
      throw localOutOfMemoryError;
    }
    finally
    {
      if (localInputStream == null);
    }
    try
    {
      localInputStream.close();
      label106: throw localObject;
      DisplayMetrics localDisplayMetrics = this.mDisplayMetrics;
      BitmapFactory.Options localOptions = null;
      if (localDisplayMetrics != null)
        localOptions = getBitmapOptions();
      Bitmap localBitmap = BitmapFactory.decodeStream(localInputStream, null, localOptions);
      if (localInputStream != null);
      try
      {
        localInputStream.close();
        return localBitmap;
      }
      catch (IOException localIOException2)
      {
        return localBitmap;
      }
    }
    catch (IOException localIOException1)
    {
      break label106;
    }
  }

  public float getDisplayMetricsDensity()
  {
    return this.mDisplayMetrics.density;
  }

  public Drawable getDrawable(ResourceProxy.bitmap parambitmap)
  {
    return new BitmapDrawable(getBitmap(parambitmap));
  }

  public String getString(ResourceProxy.string paramstring)
  {
    switch ($SWITCH_TABLE$org$osmdroid$ResourceProxy$string()[paramstring.ordinal()])
    {
    default:
      throw new IllegalArgumentException();
    case 1:
      return "Osmarender";
    case 2:
      return "Mapnik";
    case 3:
      return "Cycle Map";
    case 4:
      return "Public transport";
    case 5:
      return "OSM base layer";
    case 6:
      return "Topographic";
    case 7:
      return "Hills";
    case 9:
      return "CloudMade (Standard tiles)";
    case 8:
      return "CloudMade (small tiles)";
    case 10:
      return "Mapquest";
    case 11:
      return "OpenFietsKaart overlay";
    case 12:
      return "Netherlands base overlay";
    case 13:
      return "Netherlands roads overlay";
    case 14:
      return "Unknown";
    case 15:
      return "%s m";
    case 16:
      return "%s km";
    case 17:
      return "%s mi";
    case 18:
      return "%s nm";
    case 19:
      return "%s ft";
    case 20:
      return "Online mode";
    case 21:
      return "Offline mode";
    case 22:
      return "My location";
    case 23:
      return "Compass";
    case 24:
    }
    return "Map mode";
  }

  public String getString(ResourceProxy.string paramstring, Object[] paramArrayOfObject)
  {
    return String.format(getString(paramstring), paramArrayOfObject);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.DefaultResourceProxyImpl
 * JD-Core Version:    0.6.0
 */