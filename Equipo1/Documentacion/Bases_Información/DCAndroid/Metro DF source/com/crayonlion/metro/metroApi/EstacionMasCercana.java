package com.crayonlion.metro.metroApi;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.crayonlion.daogenerator.Estacion;
import com.crayonlion.metro.controller.interfaces.EstacionMasCercanaListener;
import java.util.List;

public class EstacionMasCercana
  implements LocationListener
{
  private static final int UPDATE_DIST = 200;
  private static final int UPDATE_RATE = 60000;
  float accuracyPasada = 3.4028235E+38F;
  private Context context;
  EstacionMasCercanaListener listener;
  private LocationManager locationManagerCel;
  private LocationManager locationManagerGPS;
  Location pasada;

  public EstacionMasCercana(Context paramContext)
  {
    this.context = paramContext;
  }

  private Estacion estacionMasCercana(Location paramLocation)
  {
    List localList = MetroManager.getAllEstaciones();
    Estacion localEstacion = null;
    Location localLocation = new Location("");
    float f1 = 3.4028235E+38F;
    for (int i = 0; i < -1 + localList.size(); i++)
    {
      localLocation.setLatitude(((Estacion)localList.get(i + 1)).getLatitud().floatValue());
      localLocation.setLongitude(((Estacion)localList.get(i + 1)).getLongitud().floatValue());
      float f2 = paramLocation.distanceTo(localLocation);
      if (f2 >= f1)
        continue;
      localEstacion = (Estacion)localList.get(i + 1);
      f1 = f2;
    }
    return localEstacion;
  }

  public void onLastKnownLocation(Location paramLocation)
  {
    if (paramLocation != null)
    {
      Estacion localEstacion = estacionMasCercana(paramLocation);
      this.listener.onEstacionMasCercanaFound(localEstacion, true);
    }
  }

  public void onLocationChanged(Location paramLocation)
  {
    Log.v("metro", "GEO FIX: " + paramLocation.getProvider() + " Accuracy:" + paramLocation.getAccuracy());
    if ((paramLocation != null) && (paramLocation.getAccuracy() < this.accuracyPasada))
    {
      if (this.pasada != null)
        break label98;
      this.pasada = paramLocation;
      Estacion localEstacion2 = estacionMasCercana(paramLocation);
      this.listener.onEstacionMasCercanaFound(localEstacion2, false);
      this.listener.onGeoFix(paramLocation);
    }
    label98: 
    do
      return;
    while (paramLocation.distanceTo(this.pasada) <= 500.0F);
    Estacion localEstacion1 = estacionMasCercana(paramLocation);
    this.listener.onEstacionMasCercanaFound(localEstacion1, false);
    this.listener.onGeoFix(paramLocation);
  }

  public void onProviderDisabled(String paramString)
  {
    Log.v("metro", "El provider " + paramString + " se ha desactivado");
    stopGeoUpdates();
  }

  public void onProviderEnabled(String paramString)
  {
    Log.v("metro", "El provider " + paramString + " se ha activado");
  }

  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
    Log.v("metro", "El status del provider " + paramString + " ha cambiado");
  }

  public void setOnEstacionMasCercanaListener(EstacionMasCercanaListener paramEstacionMasCercanaListener)
  {
    this.listener = paramEstacionMasCercanaListener;
  }

  public void startGeoUpdates()
  {
    this.locationManagerGPS = ((LocationManager)this.context.getSystemService("location"));
    this.locationManagerCel = ((LocationManager)this.context.getSystemService("location"));
    this.locationManagerGPS.requestLocationUpdates("gps", 60000L, 200.0F, this);
    this.locationManagerCel.requestLocationUpdates("network", 60000L, 200.0F, this);
    onLastKnownLocation(this.locationManagerCel.getLastKnownLocation("network"));
  }

  public void stopGeoUpdates()
  {
    this.locationManagerGPS.removeUpdates(this);
    this.locationManagerCel.removeUpdates(this);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.metroApi.EstacionMasCercana
 * JD-Core Version:    0.6.0
 */