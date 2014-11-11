package org.osmdroid;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;

public class LocationListenerProxy
  implements LocationListener
{
  private LocationListener mListener = null;
  private final LocationManager mLocationManager;

  public LocationListenerProxy(LocationManager paramLocationManager)
  {
    this.mLocationManager = paramLocationManager;
  }

  public void onLocationChanged(Location paramLocation)
  {
    if (this.mListener != null)
      this.mListener.onLocationChanged(paramLocation);
  }

  public void onProviderDisabled(String paramString)
  {
    if (this.mListener != null)
      this.mListener.onProviderDisabled(paramString);
  }

  public void onProviderEnabled(String paramString)
  {
    if (this.mListener != null)
      this.mListener.onProviderEnabled(paramString);
  }

  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
    if (this.mListener != null)
      this.mListener.onStatusChanged(paramString, paramInt, paramBundle);
  }

  public boolean startListening(LocationListener paramLocationListener, long paramLong, float paramFloat)
  {
    int i = 0;
    this.mListener = paramLocationListener;
    Iterator localIterator = this.mLocationManager.getProviders(true).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return i;
      String str = (String)localIterator.next();
      if ((!"gps".equals(str)) && (!"network".equals(str)))
        continue;
      i = 1;
      this.mLocationManager.requestLocationUpdates(str, paramLong, paramFloat, this);
    }
  }

  public void stopListening()
  {
    this.mListener = null;
    this.mLocationManager.removeUpdates(this);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.LocationListenerProxy
 * JD-Core Version:    0.6.0
 */