package com.crayonlion.daogenerator;

public class Linea
{
  private Integer IdLineaAuxiliar;
  private String color;
  private Float distanciaTotalLineaMetros;
  private Long id;
  private String nombreLinea;
  private String terminal1;
  private String terminal2;
  private Integer tiempoEsperaTerminal1Segundos;
  private Integer tiempoEsperaTerminal2Segundos;
  private Integer tiempoRecorridoVia1Segundos;
  private Integer tiempoRecorridoVia2Segundos;
  private String tipoTransporte;

  public Linea()
  {
  }

  public Linea(Long paramLong)
  {
    this.id = paramLong;
  }

  public Linea(Long paramLong, String paramString1, String paramString2, Integer paramInteger1, Float paramFloat, Integer paramInteger2, Integer paramInteger3, Integer paramInteger4, Integer paramInteger5, String paramString3, String paramString4, String paramString5)
  {
    this.id = paramLong;
    this.nombreLinea = paramString1;
    this.tipoTransporte = paramString2;
    this.IdLineaAuxiliar = paramInteger1;
    this.distanciaTotalLineaMetros = paramFloat;
    this.tiempoRecorridoVia1Segundos = paramInteger2;
    this.tiempoRecorridoVia2Segundos = paramInteger3;
    this.tiempoEsperaTerminal1Segundos = paramInteger4;
    this.tiempoEsperaTerminal2Segundos = paramInteger5;
    this.color = paramString3;
    this.terminal1 = paramString4;
    this.terminal2 = paramString5;
  }

  public String getColor()
  {
    return this.color;
  }

  public Float getDistanciaTotalLineaMetros()
  {
    return this.distanciaTotalLineaMetros;
  }

  public Long getId()
  {
    return this.id;
  }

  public Integer getIdLineaAuxiliar()
  {
    return this.IdLineaAuxiliar;
  }

  public String getNombreLinea()
  {
    return this.nombreLinea;
  }

  public String getTerminal1()
  {
    return this.terminal1;
  }

  public String getTerminal2()
  {
    return this.terminal2;
  }

  public Integer getTiempoEsperaTerminal1Segundos()
  {
    return this.tiempoEsperaTerminal1Segundos;
  }

  public Integer getTiempoEsperaTerminal2Segundos()
  {
    return this.tiempoEsperaTerminal2Segundos;
  }

  public Integer getTiempoRecorridoVia1Segundos()
  {
    return this.tiempoRecorridoVia1Segundos;
  }

  public Integer getTiempoRecorridoVia2Segundos()
  {
    return this.tiempoRecorridoVia2Segundos;
  }

  public String getTipoTransporte()
  {
    return this.tipoTransporte;
  }

  public void setColor(String paramString)
  {
    this.color = paramString;
  }

  public void setDistanciaTotalLineaMetros(Float paramFloat)
  {
    this.distanciaTotalLineaMetros = paramFloat;
  }

  public void setId(Long paramLong)
  {
    this.id = paramLong;
  }

  public void setIdLineaAuxiliar(Integer paramInteger)
  {
    this.IdLineaAuxiliar = paramInteger;
  }

  public void setNombreLinea(String paramString)
  {
    this.nombreLinea = paramString;
  }

  public void setTerminal1(String paramString)
  {
    this.terminal1 = paramString;
  }

  public void setTerminal2(String paramString)
  {
    this.terminal2 = paramString;
  }

  public void setTiempoEsperaTerminal1Segundos(Integer paramInteger)
  {
    this.tiempoEsperaTerminal1Segundos = paramInteger;
  }

  public void setTiempoEsperaTerminal2Segundos(Integer paramInteger)
  {
    this.tiempoEsperaTerminal2Segundos = paramInteger;
  }

  public void setTiempoRecorridoVia1Segundos(Integer paramInteger)
  {
    this.tiempoRecorridoVia1Segundos = paramInteger;
  }

  public void setTiempoRecorridoVia2Segundos(Integer paramInteger)
  {
    this.tiempoRecorridoVia2Segundos = paramInteger;
  }

  public void setTipoTransporte(String paramString)
  {
    this.tipoTransporte = paramString;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.daogenerator.Linea
 * JD-Core Version:    0.6.0
 */