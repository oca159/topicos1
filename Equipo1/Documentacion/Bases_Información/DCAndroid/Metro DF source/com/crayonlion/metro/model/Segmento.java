package com.crayonlion.metro.model;

import com.crayonlion.daogenerator.Estacion;

public class Segmento
{
  public static final int FINAL = 2;
  public static final int INICIO = 0;
  public static final int INTERMEDIO = 1;
  public static final int UNICO = 3;
  String direccion;
  int etapa;
  Estacion fin;
  Estacion inicio;
  Integer linea;
  Integer tiempo;

  public String getDireccion()
  {
    return this.direccion;
  }

  public int getEtapa()
  {
    return this.etapa;
  }

  public Estacion getFin()
  {
    return this.fin;
  }

  public Estacion getInicio()
  {
    return this.inicio;
  }

  public Integer getLinea()
  {
    return this.linea;
  }

  public Integer getTiempo()
  {
    return this.tiempo;
  }

  public void setDireccion(String paramString)
  {
    this.direccion = paramString;
  }

  public void setEtapa(int paramInt)
  {
    this.etapa = paramInt;
  }

  public void setFin(Estacion paramEstacion)
  {
    this.fin = paramEstacion;
  }

  public void setInicio(Estacion paramEstacion)
  {
    this.inicio = paramEstacion;
  }

  public void setLinea(Integer paramInteger)
  {
    this.linea = paramInteger;
  }

  public void setTiempo(Integer paramInteger)
  {
    this.tiempo = paramInteger;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.model.Segmento
 * JD-Core Version:    0.6.0
 */