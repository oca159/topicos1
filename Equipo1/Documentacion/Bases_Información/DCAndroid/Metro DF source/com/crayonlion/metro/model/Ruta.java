package com.crayonlion.metro.model;

import com.crayonlion.daogenerator.Conexion;
import com.crayonlion.daogenerator.Estacion;
import com.crayonlion.metro.metroApi.MetroManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Ruta
{
  ArrayList<String> direcciones = new ArrayList();
  float distanciaDeViaje;
  ArrayList<Estacion> estacionTransborde = new ArrayList();
  ArrayList<Estacion> estaciones = new ArrayList();
  int estacionesSize;
  ArrayList<Integer> lineas = new ArrayList();
  int tiempoDeViaje;
  ArrayList<Integer> tiempos = new ArrayList();

  private void calculateDistanciaDeViaje()
  {
    this.distanciaDeViaje = 0.0F;
    if (this.estacionesSize == 1);
    while (true)
    {
      return;
      for (int i = -1 + this.estacionesSize; i > 0; i--)
        this.distanciaDeViaje += MetroManager.getConexionByEstacionVecino(((Estacion)this.estaciones.get(i)).getIdEstacionAuxiliar().intValue(), ((Estacion)this.estaciones.get(i - 1)).getIdEstacionAuxiliar().intValue()).getDistanciaRecorridoMetros().floatValue();
    }
  }

  private void calculateTiempoDeViaje()
  {
    this.tiempoDeViaje = 0;
    if (this.estacionesSize == 1);
    while (true)
    {
      return;
      for (int i = -1 + this.estacionesSize; i > 0; i--)
      {
        Conexion localConexion = MetroManager.getConexionByEstacionVecino(((Estacion)this.estaciones.get(i)).getIdEstacionAuxiliar().intValue(), ((Estacion)this.estaciones.get(i - 1)).getIdEstacionAuxiliar().intValue());
        this.tiempoDeViaje += localConexion.getTiempoRecorridoSegundos().intValue() + localConexion.getTiempoTransbordoSegundos().intValue() + localConexion.getTiempoParadaSegundos().intValue();
      }
    }
  }

  private void calculateTransbordesId()
  {
    int i = 0;
    String str1 = null;
    if (this.estacionesSize == 1)
      return;
    for (int j = 0; j < -2 + this.estacionesSize; j++)
    {
      int k = MetroManager.getLineaByEstacionVecino(((Estacion)this.estaciones.get(j)).getIdEstacionAuxiliar().intValue(), ((Estacion)this.estaciones.get(j + 1)).getIdEstacionAuxiliar().intValue());
      i = MetroManager.getLineaByEstacionVecino(((Estacion)this.estaciones.get(j + 1)).getIdEstacionAuxiliar().intValue(), ((Estacion)this.estaciones.get(j + 2)).getIdEstacionAuxiliar().intValue());
      if (i == k)
        continue;
      this.estacionTransborde.add(this.estaciones.get(j + 1));
      this.lineas.add(Integer.valueOf(k));
      str1 = getDireccion(((Estacion)this.estaciones.get(j + 2)).getIdEstacionAuxiliar().intValue(), ((Estacion)this.estaciones.get(j + 1)).getIdEstacionAuxiliar().intValue());
      String str2 = getDireccion(((Estacion)this.estaciones.get(j + 1)).getIdEstacionAuxiliar().intValue(), ((Estacion)this.estaciones.get(j)).getIdEstacionAuxiliar().intValue());
      this.direcciones.add(str2);
    }
    this.lineas.add(Integer.valueOf(i));
    this.direcciones.add(str1);
    if (this.estacionTransborde.size() == 0)
    {
      this.direcciones.add(getDireccion(((Estacion)this.estaciones.get(1)).getIdEstacionAuxiliar().intValue(), ((Estacion)this.estaciones.get(0)).getIdEstacionAuxiliar().intValue()));
      this.lineas.add(Integer.valueOf(MetroManager.getLineaByEstacionVecino(((Estacion)this.estaciones.get(0)).getIdEstacionAuxiliar().intValue(), ((Estacion)this.estaciones.get(1)).getIdEstacionAuxiliar().intValue())));
    }
    Collections.reverse(this.lineas);
    Collections.reverse(this.direcciones);
    Collections.reverse(this.estacionTransborde);
  }

  private String getDireccion(int paramInt1, int paramInt2)
  {
    int i = MetroManager.getLineaByEstacionVecino(paramInt1, paramInt2);
    ArrayList localArrayList1 = new ArrayList();
    Stack localStack = new Stack();
    localArrayList1.add(Integer.valueOf(paramInt1));
    localStack.add(Integer.valueOf(paramInt2));
    ArrayList localArrayList2 = new ArrayList();
    while (!localStack.isEmpty())
    {
      int j = ((Integer)localStack.pop()).intValue();
      localArrayList2.clear();
      localArrayList2.addAll(MetroManager.getVecinosByEstacionLinea(j, i));
      int k = localArrayList2.size();
      for (int m = 0; m < k; m++)
      {
        int n = ((Conexion)localArrayList2.get(m)).getIdVecino().intValue();
        if (localArrayList1.contains(Integer.valueOf(n)))
          continue;
        localStack.add(Integer.valueOf(n));
      }
      localArrayList1.add(Integer.valueOf(j));
    }
    return MetroManager.getEstacionById(((Integer)localArrayList1.get(-1 + localArrayList1.size())).intValue()).getNombreEstacion();
  }

  public void addEstacion(Estacion paramEstacion)
  {
    this.estaciones.add(paramEstacion);
  }

  public void clear()
  {
    this.estaciones.clear();
    this.estaciones.clear();
    this.estacionTransborde.clear();
    this.lineas.clear();
    this.direcciones.clear();
    this.tiempos.clear();
    this.estacionesSize = 0;
    this.tiempoDeViaje = 0;
    this.distanciaDeViaje = 0.0F;
  }

  public ArrayList<String> getDirecciones()
  {
    return this.direcciones;
  }

  public float getDistanciaDeViaje()
  {
    return this.distanciaDeViaje;
  }

  public ArrayList<Estacion> getEstacionTransborde()
  {
    return this.estacionTransborde;
  }

  public ArrayList<Estacion> getEstaciones()
  {
    return this.estaciones;
  }

  public ArrayList<Integer> getLineas()
  {
    return this.lineas;
  }

  public int getTiempoDeViaje()
  {
    return this.tiempoDeViaje;
  }

  public ArrayList<Integer> getTiempos()
  {
    return this.tiempos;
  }

  public void init()
  {
    this.estacionesSize = this.estaciones.size();
    if (this.estacionesSize == 0)
      return;
    calculateDistanciaDeViaje();
    calculateTiempoDeViaje();
    calculateTransbordesId();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    for (int i = 0; i < this.estaciones.size(); i++)
    {
      localStringBuilder.append(((Estacion)this.estaciones.get(i)).getNombreEstacion());
      localStringBuilder.append(",");
    }
    localStringBuilder.append("], ");
    localStringBuilder.append(this.distanciaDeViaje);
    localStringBuilder.append(", ");
    localStringBuilder.append(this.tiempoDeViaje);
    localStringBuilder.append(".");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.model.Ruta
 * JD-Core Version:    0.6.0
 */