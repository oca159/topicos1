package org.osmdroid.util;

import android.location.Location;
import android.location.LocationManager;
import org.osmdroid.util.constants.UtilConstants;

public class LocationUtils
  implements UtilConstants
{
  public static Location getLastKnownLocation(LocationManager paramLocationManager)
  {
    Location localLocation1 = paramLocationManager.getLastKnownLocation("gps");
    Location localLocation2 = paramLocationManager.getLastKnownLocation("network");
    if (localLocation1 == null);
    do
    {
      return localLocation2;
      if (localLocation2 == null)
        return localLocation1;
    }
    while (localLocation2.getTime() > 20000L + localLocation1.getTime());
    return localLocation1;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.util.LocationUtils
 * JD-Core Version:    0.6.0
 */