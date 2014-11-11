package com.crayonlion.metro.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Utils
{
  public static final int IMAGE_MAX_SIZE = 32;

  public static <T> void addArrayToList(List<T> paramList, T[] paramArrayOfT)
  {
    int i = paramArrayOfT.length;
    for (int j = 0; j < i; j++)
      paramList.add(paramArrayOfT[j]);
  }

  public static boolean copyAssetToFileSystem(Context paramContext, String paramString)
  {
    try
    {
      Console.log(paramContext.getFilesDir().getAbsolutePath());
      if (new File(paramContext.getFilesDir().getAbsolutePath() + "/" + paramString).exists())
      {
        Console.log("Ya existe el archivo");
        return true;
      }
      InputStream localInputStream = paramContext.getAssets().open(paramString);
      FileOutputStream localFileOutputStream = paramContext.openFileOutput(paramString, 0);
      byte[] arrayOfByte = new byte[1024];
      while (true)
      {
        int i = localInputStream.read(arrayOfByte);
        if (i <= 0)
          break;
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
      localFileOutputStream.flush();
      localFileOutputStream.close();
      localInputStream.close();
      Console.log("Terminado con Mapa");
      return true;
    }
    catch (IOException localIOException)
    {
    }
    return false;
  }

  public static <T> List<T> createListFromArray(T[] paramArrayOfT)
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramArrayOfT.length;
    for (int j = 0; j < i; j++)
      localArrayList.add(paramArrayOfT[j]);
    return localArrayList;
  }

  public static Bitmap decodeFileSimple(AssetFileDescriptor paramAssetFileDescriptor)
  {
    Bitmap localBitmap = null;
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inPurgeable = true;
    localOptions.inInputShareable = true;
    try
    {
      FileInputStream localFileInputStream = paramAssetFileDescriptor.createInputStream();
      localBitmap = BitmapFactory.decodeStream(localFileInputStream, null, localOptions);
      localFileInputStream.close();
      return localBitmap;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return localBitmap;
  }

  public static File getSystemFile(Context paramContext, String paramString)
  {
    return new File(paramContext.getFilesDir().getAbsolutePath() + "/" + paramString);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.utils.Utils
 * JD-Core Version:    0.6.0
 */