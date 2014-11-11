package org.osmdroid.tileprovider.modules;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkAvailabliltyCheck
  implements INetworkAvailablityCheck
{
  private final ConnectivityManager mConnectionManager;

  public NetworkAvailabliltyCheck(Context paramContext)
  {
    this.mConnectionManager = ((ConnectivityManager)paramContext.getSystemService("connectivity"));
  }

  public boolean getCellularDataNetworkAvailable()
  {
    NetworkInfo localNetworkInfo = this.mConnectionManager.getNetworkInfo(0);
    int i = 0;
    if (localNetworkInfo != null)
    {
      boolean bool = localNetworkInfo.isAvailable();
      i = 0;
      if (bool)
        i = 1;
    }
    return i;
  }

  public boolean getNetworkAvailable()
  {
    NetworkInfo localNetworkInfo = this.mConnectionManager.getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isAvailable());
  }

  public boolean getRouteToPathExists(int paramInt)
  {
    return (this.mConnectionManager.requestRouteToHost(1, paramInt)) || (this.mConnectionManager.requestRouteToHost(0, paramInt));
  }

  public boolean getWiFiNetworkAvailable()
  {
    NetworkInfo localNetworkInfo = this.mConnectionManager.getNetworkInfo(1);
    return (localNetworkInfo != null) && (localNetworkInfo.isAvailable());
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.modules.NetworkAvailabliltyCheck
 * JD-Core Version:    0.6.0
 */