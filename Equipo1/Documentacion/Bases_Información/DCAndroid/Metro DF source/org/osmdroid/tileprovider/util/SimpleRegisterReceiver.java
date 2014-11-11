package org.osmdroid.tileprovider.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import org.osmdroid.tileprovider.IRegisterReceiver;

public class SimpleRegisterReceiver
  implements IRegisterReceiver
{
  private final Context mContext;

  public SimpleRegisterReceiver(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter)
  {
    return this.mContext.registerReceiver(paramBroadcastReceiver, paramIntentFilter);
  }

  public void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver)
  {
    this.mContext.unregisterReceiver(paramBroadcastReceiver);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.util.SimpleRegisterReceiver
 * JD-Core Version:    0.6.0
 */