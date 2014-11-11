package com.flurry.android;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class r
{
  static int a(Context paramContext, int paramInt)
  {
    return (int)(0.5F + paramContext.getResources().getDisplayMetrics().density * paramInt);
  }

  static String a(String paramString)
  {
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      ai.d("FlurryAgent", "Cannot encode '" + paramString + "'");
    }
    return "";
  }

  static String a(String paramString, int paramInt)
  {
    if (paramString == null)
      paramString = "";
    do
      return paramString;
    while (paramString.length() <= paramInt);
    return paramString.substring(0, paramInt);
  }

  static void a(Context paramContext, ImageView paramImageView, int paramInt1, int paramInt2)
  {
    paramImageView.setAdjustViewBounds(true);
    paramImageView.setMinimumWidth(a(paramContext, paramInt1));
    paramImageView.setMinimumHeight(a(paramContext, paramInt2));
    paramImageView.setMaxWidth(a(paramContext, paramInt1));
    paramImageView.setMaxHeight(a(paramContext, paramInt2));
  }

  static void a(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  static byte[] b(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.update(paramString.getBytes(), 0, paramString.length());
      byte[] arrayOfByte = localMessageDigest.digest();
      return arrayOfByte;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      ai.b("FlurryAgent", "Unsupported SHA1: " + localNoSuchAlgorithmException.getMessage());
    }
    return null;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.r
 * JD-Core Version:    0.6.0
 */