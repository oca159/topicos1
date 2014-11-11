package com.crayonlion.metro.controller.interfaces;

import android.location.Location;
import com.crayonlion.daogenerator.Estacion;

public abstract interface EstacionMasCercanaListener
{
  public abstract void onEstacionMasCercanaFound(Estacion paramEstacion, boolean paramBoolean);

  public abstract void onGeoFix(Location paramLocation);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.controller.interfaces.EstacionMasCercanaListener
 * JD-Core Version:    0.6.0
 */