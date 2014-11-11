package com.crayonlion.metro.utils;

import android.util.Log;

public class Console
{
  private static String TAG = "DEFAULT";

  public static void log(String paramString)
  {
    Log.v(TAG, paramString);
  }

  public static void setTag(String paramString)
  {
    TAG = paramString;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.utils.Console
 * JD-Core Version:    0.6.0
 */