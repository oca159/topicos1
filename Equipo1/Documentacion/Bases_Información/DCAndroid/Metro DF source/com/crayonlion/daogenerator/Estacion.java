package com.crayonlion.daogenerator;

public class Estacion
{
  private Integer IdEstacionAuxiliar;
  private Integer Posmapx;
  private Integer Posmapy;
  private Integer afluenciaDiaHabil;
  private Integer afluenciaDomingoYFestivo;
  private Integer afluenciaSabado;
  private Integer afluenciaTotal;
  private Boolean esTransbordo;
  private Long id;
  private String info;
  private Float latitud;
  private Float longitud;
  private String nombreEstacion;
  private Integer numeroAntena;

  public Estacion()
  {
  }

  public Estacion(Long paramLong)
  {
    this.id = paramLong;
  }

  public Estacion(Long paramLong, Integer paramInteger1, String paramString1, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4, Integer paramInteger5, Integer paramInteger6, String paramString2, Float paramFloat1, Float paramFloat2, Integer paramInteger7, Integer paramInteger8, Boolean paramBoolean)
  {
    this.id = paramLong;
    this.IdEstacionAuxiliar = paramInteger1;
    this.nombreEstacion = paramString1;
    this.afluenciaTotal = paramInteger2;
    this.afluenciaDiaHabil = paramInteger3;
    this.afluenciaSabado = paramInteger4;
    this.afluenciaDomingoYFestivo = paramInteger5;
    this.numeroAntena = paramInteger6;
    this.info = paramString2;
    this.latitud = paramFloat1;
    this.longitud = paramFloat2;
    this.Posmapx = paramInteger7;
    this.Posmapy = paramInteger8;
    this.esTransbordo = paramBoolean;
  }

  public Integer getAfluenciaDiaHabil()
  {
    return this.afluenciaDiaHabil;
  }

  public Integer getAfluenciaDomingoYFestivo()
  {
    return this.afluenciaDomingoYFestivo;
  }

  public Integer getAfluenciaSabado()
  {
    return this.afluenciaSabado;
  }

  public Integer getAfluenciaTotal()
  {
    return this.afluenciaTotal;
  }

  public Boolean getEsTransbordo()
  {
    return this.esTransbordo;
  }

  public Long getId()
  {
    return this.id;
  }

  public Integer getIdEstacionAuxiliar()
  {
    return this.IdEstacionAuxiliar;
  }

  public String getInfo()
  {
    return this.info;
  }

  public Float getLatitud()
  {
    return this.latitud;
  }

  public Float getLongitud()
  {
    return this.longitud;
  }

  public String getNombreEstacion()
  {
    return this.nombreEstacion;
  }

  public Integer getNumeroAntena()
  {
    return this.numeroAntena;
  }

  public Integer getPosmapx()
  {
    return this.Posmapx;
  }

  public Integer getPosmapy()
  {
    return this.Posmapy;
  }

  public void setAfluenciaDiaHabil(Integer paramInteger)
  {
    this.afluenciaDiaHabil = paramInteger;
  }

  public void setAfluenciaDomingoYFestivo(Integer paramInteger)
  {
    this.afluenciaDomingoYFestivo = paramInteger;
  }

  public void setAfluenciaSabado(Integer paramInteger)
  {
    this.afluenciaSabado = paramInteger;
  }

  public void setAfluenciaTotal(Integer paramInteger)
  {
    this.afluenciaTotal = paramInteger;
  }

  public void setEsTransbordo(Boolean paramBoolean)
  {
    this.esTransbordo = paramBoolean;
  }

  public void setId(Long paramLong)
  {
    this.id = paramLong;
  }

  public void setIdEstacionAuxiliar(Integer paramInteger)
  {
    this.IdEstacionAuxiliar = paramInteger;
  }

  public void setInfo(String paramString)
  {
    this.info = paramString;
  }

  public void setLatitud(Float paramFloat)
  {
    this.latitud = paramFloat;
  }

  public void setLongitud(Float paramFloat)
  {
    this.longitud = paramFloat;
  }

  public void setNombreEstacion(String paramString)
  {
    this.nombreEstacion = paramString;
  }

  public void setNumeroAntena(Integer paramInteger)
  {
    this.numeroAntena = paramInteger;
  }

  public void setPosmapx(Integer paramInteger)
  {
    this.Posmapx = paramInteger;
  }

  public void setPosmapy(Integer paramInteger)
  {
    this.Posmapy = paramInteger;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.daogenerator.Estacion
 * JD-Core Version:    0.6.0
 */