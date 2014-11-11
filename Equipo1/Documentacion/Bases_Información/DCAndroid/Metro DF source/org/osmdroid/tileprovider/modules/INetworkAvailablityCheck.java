package org.osmdroid.tileprovider.modules;

public abstract interface INetworkAvailablityCheck
{
  public abstract boolean getCellularDataNetworkAvailable();

  public abstract boolean getNetworkAvailable();

  public abstract boolean getRouteToPathExists(int paramInt);

  public abstract boolean getWiFiNetworkAvailable();
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.modules.INetworkAvailablityCheck
 * JD-Core Version:    0.6.0
 */