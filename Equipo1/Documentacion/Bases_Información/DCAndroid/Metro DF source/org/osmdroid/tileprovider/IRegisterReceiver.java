package org.osmdroid.tileprovider;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

public abstract interface IRegisterReceiver
{
  public abstract Intent registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter);

  public abstract void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.IRegisterReceiver
 * JD-Core Version:    0.6.0
 */