package com.flurry.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class InstallReceiver extends BroadcastReceiver
{
  private final Handler a;
  private File b = null;

  private InstallReceiver()
  {
    HandlerThread localHandlerThread = new HandlerThread("InstallReceiver");
    localHandlerThread.start();
    this.a = new Handler(localHandlerThread.getLooper());
  }

  private static Map a(String paramString)
  {
    if ((paramString == null) || (paramString.trim().equals("")))
      throw new IllegalArgumentException("Referrer is null or empty");
    HashMap localHashMap = new HashMap();
    String[] arrayOfString1 = paramString.split("&");
    int i = arrayOfString1.length;
    int j = 0;
    if (j < i)
    {
      String[] arrayOfString2 = arrayOfString1[j].split("=");
      if (arrayOfString2.length != 2)
        ai.a("InstallReceiver", "Invalid referrer Element: " + arrayOfString1[j] + " in referrer tag " + paramString);
      while (true)
      {
        j++;
        break;
        localHashMap.put(arrayOfString2[0], arrayOfString2[1]);
      }
    }
    StringBuilder localStringBuilder = new StringBuilder();
    if (localHashMap.get("utm_source") == null)
      localStringBuilder.append("Campaign Source is missing.\n");
    if (localHashMap.get("utm_medium") == null)
      localStringBuilder.append("Campaign Medium is missing.\n");
    if (localHashMap.get("utm_campaign") == null)
      localStringBuilder.append("Campaign Name is missing.\n");
    if (localStringBuilder.length() > 0)
      throw new IllegalArgumentException(localStringBuilder.toString());
    return localHashMap;
  }

  private void a(Map paramMap)
  {
    monitorenter;
    try
    {
      t localt = new t(this, paramMap);
      this.a.post(localt);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    this.b = paramContext.getFileStreamPath(".flurryinstallreceiver." + Integer.toString(FlurryAgent.e().hashCode(), 16));
    if (FlurryAgent.isCaptureUncaughtExceptions())
      Thread.setDefaultUncaughtExceptionHandler(new FlurryAgent.FlurryDefaultExceptionHandler());
    String str = paramIntent.getStringExtra("referrer");
    if ((str == null) || (!"com.android.vending.INSTALL_REFERRER".equals(paramIntent.getAction())))
      return;
    try
    {
      a(a(str));
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      ai.c("InstallReceiver", "Invalid referrer Tag: " + localIllegalArgumentException.getMessage());
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.InstallReceiver
 * JD-Core Version:    0.6.0
 */