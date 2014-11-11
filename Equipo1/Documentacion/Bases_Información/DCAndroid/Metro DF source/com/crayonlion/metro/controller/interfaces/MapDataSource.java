package com.crayonlion.metro.controller.interfaces;

import android.graphics.Point;
import com.crayonlion.daogenerator.Conexion;

public abstract interface MapDataSource
{
  public abstract int getColorLinea(int paramInt);

  public abstract Conexion getConexion(int paramInt);

  public abstract int getIconEstacion(int paramInt);

  public abstract Point getLocationEstacion(int paramInt);

  public abstract Point getLocationEstacionMapa(int paramInt);

  public abstract String getNameEstacion(int paramInt);

  public abstract int getNumConexiones();

  public abstract int getNumEstaciones();

  public abstract int getNumLineas();
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.controller.interfaces.MapDataSource
 * JD-Core Version:    0.6.0
 */