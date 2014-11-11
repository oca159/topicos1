package com.crayonlion.daogenerator;

import de.greenrobot.dao.DaoException;

public class Conexion
{
  private Integer IdConexionAuxiliar;
  private DaoSession daoSession;
  private Float distanciaRecorridoMetros;
  private Estacion estacion;
  private Long estacion__resolvedKey;
  private Long id;
  private Long idEstacion;
  private Long idLinea;
  private Integer idVecino;
  private Linea linea;
  private Long linea__resolvedKey;
  private ConexionDao myDao;
  private Integer tiempoParadaSegundos;
  private Integer tiempoRecorridoSegundos;
  private Integer tiempoTransbordoSegundos;

  public Conexion()
  {
  }

  public Conexion(Long paramLong)
  {
    this.id = paramLong;
  }

  public Conexion(Long paramLong1, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Float paramFloat, Integer paramInteger4, Integer paramInteger5, Long paramLong2, Long paramLong3)
  {
    this.id = paramLong1;
    this.IdConexionAuxiliar = paramInteger1;
    this.idVecino = paramInteger2;
    this.tiempoRecorridoSegundos = paramInteger3;
    this.distanciaRecorridoMetros = paramFloat;
    this.tiempoParadaSegundos = paramInteger4;
    this.tiempoTransbordoSegundos = paramInteger5;
    this.idEstacion = paramLong2;
    this.idLinea = paramLong3;
  }

  public void __setDaoSession(DaoSession paramDaoSession)
  {
    this.daoSession = paramDaoSession;
    if (paramDaoSession != null);
    for (ConexionDao localConexionDao = paramDaoSession.getConexionDao(); ; localConexionDao = null)
    {
      this.myDao = localConexionDao;
      return;
    }
  }

  public void delete()
  {
    if (this.myDao == null)
      throw new DaoException("Entity is detached from DAO context");
    this.myDao.delete(this);
  }

  public Float getDistanciaRecorridoMetros()
  {
    return this.distanciaRecorridoMetros;
  }

  public Estacion getEstacion()
  {
    if ((this.estacion__resolvedKey == null) || (!this.estacion__resolvedKey.equals(this.idEstacion)))
    {
      if (this.daoSession == null)
        throw new DaoException("Entity is detached from DAO context");
      this.estacion = ((Estacion)this.daoSession.getEstacionDao().load(this.idEstacion));
      this.estacion__resolvedKey = this.idEstacion;
    }
    return this.estacion;
  }

  public Long getId()
  {
    return this.id;
  }

  public Integer getIdConexionAuxiliar()
  {
    return this.IdConexionAuxiliar;
  }

  public Long getIdEstacion()
  {
    return this.idEstacion;
  }

  public Long getIdLinea()
  {
    return this.idLinea;
  }

  public Integer getIdVecino()
  {
    return this.idVecino;
  }

  public Linea getLinea()
  {
    if ((this.linea__resolvedKey == null) || (!this.linea__resolvedKey.equals(this.idLinea)))
    {
      if (this.daoSession == null)
        throw new DaoException("Entity is detached from DAO context");
      this.linea = ((Linea)this.daoSession.getLineaDao().load(this.idLinea));
      this.linea__resolvedKey = this.idLinea;
    }
    return this.linea;
  }

  public Integer getTiempoParadaSegundos()
  {
    return this.tiempoParadaSegundos;
  }

  public Integer getTiempoRecorridoSegundos()
  {
    return this.tiempoRecorridoSegundos;
  }

  public Integer getTiempoTransbordoSegundos()
  {
    return this.tiempoTransbordoSegundos;
  }

  public void refresh()
  {
    if (this.myDao == null)
      throw new DaoException("Entity is detached from DAO context");
    this.myDao.refresh(this);
  }

  public void setDistanciaRecorridoMetros(Float paramFloat)
  {
    this.distanciaRecorridoMetros = paramFloat;
  }

  public void setEstacion(Estacion paramEstacion)
  {
    this.estacion = paramEstacion;
    if (paramEstacion == null);
    for (Long localLong = null; ; localLong = paramEstacion.getId())
    {
      this.idEstacion = localLong;
      this.estacion__resolvedKey = this.idEstacion;
      return;
    }
  }

  public void setId(Long paramLong)
  {
    this.id = paramLong;
  }

  public void setIdConexionAuxiliar(Integer paramInteger)
  {
    this.IdConexionAuxiliar = paramInteger;
  }

  public void setIdEstacion(Long paramLong)
  {
    this.idEstacion = paramLong;
  }

  public void setIdLinea(Long paramLong)
  {
    this.idLinea = paramLong;
  }

  public void setIdVecino(Integer paramInteger)
  {
    this.idVecino = paramInteger;
  }

  public void setLinea(Linea paramLinea)
  {
    this.linea = paramLinea;
    if (paramLinea == null);
    for (Long localLong = null; ; localLong = paramLinea.getId())
    {
      this.idLinea = localLong;
      this.linea__resolvedKey = this.idLinea;
      return;
    }
  }

  public void setTiempoParadaSegundos(Integer paramInteger)
  {
    this.tiempoParadaSegundos = paramInteger;
  }

  public void setTiempoRecorridoSegundos(Integer paramInteger)
  {
    this.tiempoRecorridoSegundos = paramInteger;
  }

  public void setTiempoTransbordoSegundos(Integer paramInteger)
  {
    this.tiempoTransbordoSegundos = paramInteger;
  }

  public void update()
  {
    if (this.myDao == null)
      throw new DaoException("Entity is detached from DAO context");
    this.myDao.update(this);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.daogenerator.Conexion
 * JD-Core Version:    0.6.0
 */