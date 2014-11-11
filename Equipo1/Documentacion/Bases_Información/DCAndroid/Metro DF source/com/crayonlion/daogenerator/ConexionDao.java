package com.crayonlion.daogenerator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.IdentityScope;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.SqlUtils;
import java.util.ArrayList;
import java.util.List;

public class ConexionDao extends AbstractDao<Conexion, Long>
{
  public static final String TABLENAME = "CONEXION";
  private DaoSession daoSession;
  private String selectDeep;

  public ConexionDao(DaoConfig paramDaoConfig)
  {
    super(paramDaoConfig);
  }

  public ConexionDao(DaoConfig paramDaoConfig, DaoSession paramDaoSession)
  {
    super(paramDaoConfig, paramDaoSession);
    this.daoSession = paramDaoSession;
  }

  public static void createTable(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS 'CONEXION' ('ID_CONEXION' INTEGER PRIMARY KEY ,'ID_CONEXION_AUXILIAR' INTEGER,'ID_VECINO' INTEGER,'TIEMPO_RECORRIDO_SEGUNDOS' INTEGER,'DISTANCIA_RECORRIDO_METROS' REAL,'TIEMPO_PARADA_SEGUNDOS' INTEGER,'TIEMPO_TRANSBORDO_SEGUNDOS' INTEGER,'ID_ESTACION' INTEGER,'ID_LINEA' INTEGER);");
  }

  public static void dropTable(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("DROP TABLE ");
    if (paramBoolean);
    for (String str = "IF EXISTS "; ; str = "")
    {
      paramSQLiteDatabase.execSQL(str + "'CONEXION'");
      return;
    }
  }

  protected void attachEntity(Conexion paramConexion)
  {
    super.attachEntity(paramConexion);
    paramConexion.__setDaoSession(this.daoSession);
  }

  protected void bindValues(SQLiteStatement paramSQLiteStatement, Conexion paramConexion)
  {
    paramSQLiteStatement.clearBindings();
    Long localLong1 = paramConexion.getId();
    if (localLong1 != null)
      paramSQLiteStatement.bindLong(1, localLong1.longValue());
    Integer localInteger1 = paramConexion.getIdConexionAuxiliar();
    if (localInteger1 != null)
      paramSQLiteStatement.bindLong(2, localInteger1.intValue());
    Integer localInteger2 = paramConexion.getIdVecino();
    if (localInteger2 != null)
      paramSQLiteStatement.bindLong(3, localInteger2.intValue());
    Integer localInteger3 = paramConexion.getTiempoRecorridoSegundos();
    if (localInteger3 != null)
      paramSQLiteStatement.bindLong(4, localInteger3.intValue());
    Float localFloat = paramConexion.getDistanciaRecorridoMetros();
    if (localFloat != null)
      paramSQLiteStatement.bindDouble(5, localFloat.floatValue());
    Integer localInteger4 = paramConexion.getTiempoParadaSegundos();
    if (localInteger4 != null)
      paramSQLiteStatement.bindLong(6, localInteger4.intValue());
    Integer localInteger5 = paramConexion.getTiempoTransbordoSegundos();
    if (localInteger5 != null)
      paramSQLiteStatement.bindLong(7, localInteger5.intValue());
    Long localLong2 = paramConexion.getIdEstacion();
    if (localLong2 != null)
      paramSQLiteStatement.bindLong(8, localLong2.longValue());
    Long localLong3 = paramConexion.getIdLinea();
    if (localLong3 != null)
      paramSQLiteStatement.bindLong(9, localLong3.longValue());
  }

  public Long getKey(Conexion paramConexion)
  {
    if (paramConexion != null)
      return paramConexion.getId();
    return null;
  }

  protected String getSelectDeep()
  {
    if (this.selectDeep == null)
    {
      StringBuilder localStringBuilder = new StringBuilder("SELECT ");
      SqlUtils.appendColumns(localStringBuilder, "T", getAllColumns());
      localStringBuilder.append(',');
      SqlUtils.appendColumns(localStringBuilder, "T0", this.daoSession.getEstacionDao().getAllColumns());
      localStringBuilder.append(',');
      SqlUtils.appendColumns(localStringBuilder, "T1", this.daoSession.getLineaDao().getAllColumns());
      localStringBuilder.append(" FROM CONEXION T");
      localStringBuilder.append(" LEFT JOIN ESTACION T0 ON T.'ID_ESTACION'=T0.'ID_ESTACION'");
      localStringBuilder.append(" LEFT JOIN LINEA T1 ON T.'ID_LINEA'=T1.'ID_LINEA'");
      localStringBuilder.append(' ');
      this.selectDeep = localStringBuilder.toString();
    }
    return this.selectDeep;
  }

  protected boolean isEntityUpdateable()
  {
    return true;
  }

  public List<Conexion> loadAllDeepFromCursor(Cursor paramCursor)
  {
    int i = paramCursor.getCount();
    ArrayList localArrayList = new ArrayList(i);
    if (paramCursor.moveToFirst())
      if (this.identityScope != null)
      {
        this.identityScope.lock();
        this.identityScope.reserveRoom(i);
      }
    try
    {
      boolean bool;
      do
      {
        localArrayList.add(loadCurrentDeep(paramCursor, false));
        bool = paramCursor.moveToNext();
      }
      while (bool);
      return localArrayList;
    }
    finally
    {
      if (this.identityScope != null)
        this.identityScope.unlock();
    }
    throw localObject;
  }

  protected Conexion loadCurrentDeep(Cursor paramCursor, boolean paramBoolean)
  {
    Conexion localConexion = (Conexion)loadCurrent(paramCursor, 0, paramBoolean);
    int i = getAllColumns().length;
    localConexion.setEstacion((Estacion)loadCurrentOther(this.daoSession.getEstacionDao(), paramCursor, i));
    int j = i + this.daoSession.getEstacionDao().getAllColumns().length;
    localConexion.setLinea((Linea)loadCurrentOther(this.daoSession.getLineaDao(), paramCursor, j));
    return localConexion;
  }

  public Conexion loadDeep(Long paramLong)
  {
    assertSinglePk();
    if (paramLong == null)
      return null;
    StringBuilder localStringBuilder = new StringBuilder(getSelectDeep());
    localStringBuilder.append("WHERE ");
    SqlUtils.appendColumnsEqValue(localStringBuilder, "T", getPkColumns());
    String str = localStringBuilder.toString();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramLong.toString();
    Cursor localCursor = this.db.rawQuery(str, arrayOfString);
    try
    {
      boolean bool = localCursor.moveToFirst();
      if (!bool)
        return null;
      if (!localCursor.isLast())
        throw new IllegalStateException("Expected unique result, but count was " + localCursor.getCount());
    }
    finally
    {
      localCursor.close();
    }
    Conexion localConexion = loadCurrentDeep(localCursor, true);
    localCursor.close();
    return localConexion;
  }

  protected List<Conexion> loadDeepAllAndCloseCursor(Cursor paramCursor)
  {
    try
    {
      List localList = loadAllDeepFromCursor(paramCursor);
      return localList;
    }
    finally
    {
      paramCursor.close();
    }
    throw localObject;
  }

  public List<Conexion> queryDeep(String paramString, String[] paramArrayOfString)
  {
    return loadDeepAllAndCloseCursor(this.db.rawQuery(getSelectDeep() + paramString, paramArrayOfString));
  }

  public Conexion readEntity(Cursor paramCursor, int paramInt)
  {
    Long localLong1;
    Integer localInteger1;
    label29: Integer localInteger2;
    label44: Integer localInteger3;
    label59: Float localFloat;
    label74: Integer localInteger4;
    label89: Integer localInteger5;
    label105: Long localLong2;
    label121: Long localLong3;
    if (paramCursor.isNull(paramInt + 0))
    {
      localLong1 = null;
      if (!paramCursor.isNull(paramInt + 1))
        break label182;
      localInteger1 = null;
      if (!paramCursor.isNull(paramInt + 2))
        break label199;
      localInteger2 = null;
      if (!paramCursor.isNull(paramInt + 3))
        break label216;
      localInteger3 = null;
      if (!paramCursor.isNull(paramInt + 4))
        break label233;
      localFloat = null;
      if (!paramCursor.isNull(paramInt + 5))
        break label250;
      localInteger4 = null;
      if (!paramCursor.isNull(paramInt + 6))
        break label267;
      localInteger5 = null;
      if (!paramCursor.isNull(paramInt + 7))
        break label285;
      localLong2 = null;
      boolean bool = paramCursor.isNull(paramInt + 8);
      localLong3 = null;
      if (!bool)
        break label303;
    }
    while (true)
    {
      return new Conexion(localLong1, localInteger1, localInteger2, localInteger3, localFloat, localInteger4, localInteger5, localLong2, localLong3);
      localLong1 = Long.valueOf(paramCursor.getLong(paramInt + 0));
      break;
      label182: localInteger1 = Integer.valueOf(paramCursor.getInt(paramInt + 1));
      break label29;
      label199: localInteger2 = Integer.valueOf(paramCursor.getInt(paramInt + 2));
      break label44;
      label216: localInteger3 = Integer.valueOf(paramCursor.getInt(paramInt + 3));
      break label59;
      label233: localFloat = Float.valueOf(paramCursor.getFloat(paramInt + 4));
      break label74;
      label250: localInteger4 = Integer.valueOf(paramCursor.getInt(paramInt + 5));
      break label89;
      label267: localInteger5 = Integer.valueOf(paramCursor.getInt(paramInt + 6));
      break label105;
      label285: localLong2 = Long.valueOf(paramCursor.getLong(paramInt + 7));
      break label121;
      label303: localLong3 = Long.valueOf(paramCursor.getLong(paramInt + 8));
    }
  }

  public void readEntity(Cursor paramCursor, Conexion paramConexion, int paramInt)
  {
    Long localLong1;
    Integer localInteger1;
    label36: Integer localInteger2;
    label57: Integer localInteger3;
    label78: Float localFloat;
    label99: Integer localInteger4;
    label120: Integer localInteger5;
    label142: Long localLong2;
    label164: Long localLong3;
    if (paramCursor.isNull(paramInt + 0))
    {
      localLong1 = null;
      paramConexion.setId(localLong1);
      if (!paramCursor.isNull(paramInt + 1))
        break label214;
      localInteger1 = null;
      paramConexion.setIdConexionAuxiliar(localInteger1);
      if (!paramCursor.isNull(paramInt + 2))
        break label231;
      localInteger2 = null;
      paramConexion.setIdVecino(localInteger2);
      if (!paramCursor.isNull(paramInt + 3))
        break label248;
      localInteger3 = null;
      paramConexion.setTiempoRecorridoSegundos(localInteger3);
      if (!paramCursor.isNull(paramInt + 4))
        break label265;
      localFloat = null;
      paramConexion.setDistanciaRecorridoMetros(localFloat);
      if (!paramCursor.isNull(paramInt + 5))
        break label282;
      localInteger4 = null;
      paramConexion.setTiempoParadaSegundos(localInteger4);
      if (!paramCursor.isNull(paramInt + 6))
        break label299;
      localInteger5 = null;
      paramConexion.setTiempoTransbordoSegundos(localInteger5);
      if (!paramCursor.isNull(paramInt + 7))
        break label317;
      localLong2 = null;
      paramConexion.setIdEstacion(localLong2);
      boolean bool = paramCursor.isNull(paramInt + 8);
      localLong3 = null;
      if (!bool)
        break label335;
    }
    while (true)
    {
      paramConexion.setIdLinea(localLong3);
      return;
      localLong1 = Long.valueOf(paramCursor.getLong(paramInt + 0));
      break;
      label214: localInteger1 = Integer.valueOf(paramCursor.getInt(paramInt + 1));
      break label36;
      label231: localInteger2 = Integer.valueOf(paramCursor.getInt(paramInt + 2));
      break label57;
      label248: localInteger3 = Integer.valueOf(paramCursor.getInt(paramInt + 3));
      break label78;
      label265: localFloat = Float.valueOf(paramCursor.getFloat(paramInt + 4));
      break label99;
      label282: localInteger4 = Integer.valueOf(paramCursor.getInt(paramInt + 5));
      break label120;
      label299: localInteger5 = Integer.valueOf(paramCursor.getInt(paramInt + 6));
      break label142;
      label317: localLong2 = Long.valueOf(paramCursor.getLong(paramInt + 7));
      break label164;
      label335: localLong3 = Long.valueOf(paramCursor.getLong(paramInt + 8));
    }
  }

  public Long readKey(Cursor paramCursor, int paramInt)
  {
    if (paramCursor.isNull(paramInt + 0))
      return null;
    return Long.valueOf(paramCursor.getLong(paramInt + 0));
  }

  protected Long updateKeyAfterInsert(Conexion paramConexion, long paramLong)
  {
    paramConexion.setId(Long.valueOf(paramLong));
    return Long.valueOf(paramLong);
  }

  public static class Properties
  {
    public static final Property DistanciaRecorridoMetros;
    public static final Property Id = new Property(0, Long.class, "id", true, "ID_CONEXION");
    public static final Property IdConexionAuxiliar = new Property(1, Integer.class, "IdConexionAuxiliar", false, "ID_CONEXION_AUXILIAR");
    public static final Property IdEstacion;
    public static final Property IdLinea;
    public static final Property IdVecino = new Property(2, Integer.class, "idVecino", false, "ID_VECINO");
    public static final Property TiempoParadaSegundos;
    public static final Property TiempoRecorridoSegundos = new Property(3, Integer.class, "tiempoRecorridoSegundos", false, "TIEMPO_RECORRIDO_SEGUNDOS");
    public static final Property TiempoTransbordoSegundos;

    static
    {
      DistanciaRecorridoMetros = new Property(4, Float.class, "distanciaRecorridoMetros", false, "DISTANCIA_RECORRIDO_METROS");
      TiempoParadaSegundos = new Property(5, Integer.class, "tiempoParadaSegundos", false, "TIEMPO_PARADA_SEGUNDOS");
      TiempoTransbordoSegundos = new Property(6, Integer.class, "tiempoTransbordoSegundos", false, "TIEMPO_TRANSBORDO_SEGUNDOS");
      IdEstacion = new Property(7, Long.class, "idEstacion", false, "ID_ESTACION");
      IdLinea = new Property(8, Long.class, "idLinea", false, "ID_LINEA");
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.daogenerator.ConexionDao
 * JD-Core Version:    0.6.0
 */