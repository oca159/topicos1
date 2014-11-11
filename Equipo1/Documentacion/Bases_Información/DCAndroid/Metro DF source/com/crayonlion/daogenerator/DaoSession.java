package com.crayonlion.daogenerator;

import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.IdentityScope;
import de.greenrobot.dao.IdentityScopeType;
import java.util.Map;

public class DaoSession extends AbstractDaoSession
{
  private final ConexionDao conexionDao;
  private final DaoConfig conexionDaoConfig;
  private final EstacionDao estacionDao;
  private final DaoConfig estacionDaoConfig;
  private final LineaDao lineaDao;
  private final DaoConfig lineaDaoConfig;

  public DaoSession(SQLiteDatabase paramSQLiteDatabase, IdentityScopeType paramIdentityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> paramMap)
  {
    super(paramSQLiteDatabase);
    this.estacionDaoConfig = ((DaoConfig)paramMap.get(EstacionDao.class)).clone();
    this.estacionDaoConfig.initIdentityScope(paramIdentityScopeType);
    this.lineaDaoConfig = ((DaoConfig)paramMap.get(LineaDao.class)).clone();
    this.lineaDaoConfig.initIdentityScope(paramIdentityScopeType);
    this.conexionDaoConfig = ((DaoConfig)paramMap.get(ConexionDao.class)).clone();
    this.conexionDaoConfig.initIdentityScope(paramIdentityScopeType);
    this.estacionDao = new EstacionDao(this.estacionDaoConfig, this);
    this.lineaDao = new LineaDao(this.lineaDaoConfig, this);
    this.conexionDao = new ConexionDao(this.conexionDaoConfig, this);
    registerDao(Estacion.class, this.estacionDao);
    registerDao(Linea.class, this.lineaDao);
    registerDao(Conexion.class, this.conexionDao);
  }

  public void clear()
  {
    this.estacionDaoConfig.getIdentityScope().clear();
    this.lineaDaoConfig.getIdentityScope().clear();
    this.conexionDaoConfig.getIdentityScope().clear();
  }

  public ConexionDao getConexionDao()
  {
    return this.conexionDao;
  }

  public EstacionDao getEstacionDao()
  {
    return this.estacionDao;
  }

  public LineaDao getLineaDao()
  {
    return this.lineaDao;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.daogenerator.DaoSession
 * JD-Core Version:    0.6.0
 */