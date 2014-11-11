package com.crayonlion.daogenerator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.Property;

public class EstacionDao extends AbstractDao<Estacion, Long>
{
  public static final String TABLENAME = "ESTACION";

  public EstacionDao(DaoConfig paramDaoConfig)
  {
    super(paramDaoConfig);
  }

  public EstacionDao(DaoConfig paramDaoConfig, DaoSession paramDaoSession)
  {
    super(paramDaoConfig, paramDaoSession);
  }

  public static void createTable(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS 'ESTACION' ('ID_ESTACION' INTEGER PRIMARY KEY ,'ID_ESTACION_AUXILIAR' INTEGER,'NOMBRE_ESTACION' TEXT,'AFLUENCIA_TOTAL' INTEGER,'AFLUENCIA_DIA_HABIL' INTEGER,'AFLUENCIA_SABADO' INTEGER,'AFLUENCIA_DOMINGO_YFESTIVO' INTEGER,'NUMERO_ANTENA' INTEGER,'INFO' TEXT,'LATITUD' REAL,'LONGITUD' REAL,'POSMAPX' INTEGER,'POSMAPY' INTEGER,'ES_TRANSBORDO' INTEGER);");
  }

  public static void dropTable(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("DROP TABLE ");
    if (paramBoolean);
    for (String str = "IF EXISTS "; ; str = "")
    {
      paramSQLiteDatabase.execSQL(str + "'ESTACION'");
      return;
    }
  }

  protected void bindValues(SQLiteStatement paramSQLiteStatement, Estacion paramEstacion)
  {
    paramSQLiteStatement.clearBindings();
    Long localLong = paramEstacion.getId();
    if (localLong != null)
      paramSQLiteStatement.bindLong(1, localLong.longValue());
    Integer localInteger1 = paramEstacion.getIdEstacionAuxiliar();
    if (localInteger1 != null)
      paramSQLiteStatement.bindLong(2, localInteger1.intValue());
    String str1 = paramEstacion.getNombreEstacion();
    if (str1 != null)
      paramSQLiteStatement.bindString(3, str1);
    Integer localInteger2 = paramEstacion.getAfluenciaTotal();
    if (localInteger2 != null)
      paramSQLiteStatement.bindLong(4, localInteger2.intValue());
    Integer localInteger3 = paramEstacion.getAfluenciaDiaHabil();
    if (localInteger3 != null)
      paramSQLiteStatement.bindLong(5, localInteger3.intValue());
    Integer localInteger4 = paramEstacion.getAfluenciaSabado();
    if (localInteger4 != null)
      paramSQLiteStatement.bindLong(6, localInteger4.intValue());
    Integer localInteger5 = paramEstacion.getAfluenciaDomingoYFestivo();
    if (localInteger5 != null)
      paramSQLiteStatement.bindLong(7, localInteger5.intValue());
    Integer localInteger6 = paramEstacion.getNumeroAntena();
    if (localInteger6 != null)
      paramSQLiteStatement.bindLong(8, localInteger6.intValue());
    String str2 = paramEstacion.getInfo();
    if (str2 != null)
      paramSQLiteStatement.bindString(9, str2);
    Float localFloat1 = paramEstacion.getLatitud();
    if (localFloat1 != null)
      paramSQLiteStatement.bindDouble(10, localFloat1.floatValue());
    Float localFloat2 = paramEstacion.getLongitud();
    if (localFloat2 != null)
      paramSQLiteStatement.bindDouble(11, localFloat2.floatValue());
    Integer localInteger7 = paramEstacion.getPosmapx();
    if (localInteger7 != null)
      paramSQLiteStatement.bindLong(12, localInteger7.intValue());
    Integer localInteger8 = paramEstacion.getPosmapy();
    if (localInteger8 != null)
      paramSQLiteStatement.bindLong(13, localInteger8.intValue());
    Boolean localBoolean = paramEstacion.getEsTransbordo();
    long l;
    if (localBoolean != null)
    {
      if (!localBoolean.booleanValue())
        break label317;
      l = 1L;
    }
    while (true)
    {
      paramSQLiteStatement.bindLong(14, l);
      return;
      label317: l = 0L;
    }
  }

  public Long getKey(Estacion paramEstacion)
  {
    if (paramEstacion != null)
      return paramEstacion.getId();
    return null;
  }

  protected boolean isEntityUpdateable()
  {
    return true;
  }

  public Estacion readEntity(Cursor paramCursor, int paramInt)
  {
    Long localLong;
    Integer localInteger1;
    label29: String str1;
    label44: Integer localInteger2;
    label59: Integer localInteger3;
    label74: Integer localInteger4;
    label89: Integer localInteger5;
    label105: Integer localInteger6;
    label121: String str2;
    label137: Float localFloat1;
    label153: Float localFloat2;
    label169: Integer localInteger7;
    if (paramCursor.isNull(paramInt + 0))
    {
      localLong = null;
      if (!paramCursor.isNull(paramInt + 1))
        break label268;
      localInteger1 = null;
      if (!paramCursor.isNull(paramInt + 2))
        break label285;
      str1 = null;
      if (!paramCursor.isNull(paramInt + 3))
        break label299;
      localInteger2 = null;
      if (!paramCursor.isNull(paramInt + 4))
        break label316;
      localInteger3 = null;
      if (!paramCursor.isNull(paramInt + 5))
        break label333;
      localInteger4 = null;
      if (!paramCursor.isNull(paramInt + 6))
        break label350;
      localInteger5 = null;
      if (!paramCursor.isNull(paramInt + 7))
        break label368;
      localInteger6 = null;
      if (!paramCursor.isNull(paramInt + 8))
        break label386;
      str2 = null;
      if (!paramCursor.isNull(paramInt + 9))
        break label401;
      localFloat1 = null;
      if (!paramCursor.isNull(paramInt + 10))
        break label419;
      localFloat2 = null;
      if (!paramCursor.isNull(paramInt + 11))
        break label437;
      localInteger7 = null;
      label185: if (!paramCursor.isNull(paramInt + 12))
        break label455;
    }
    Boolean localBoolean;
    label268: label285: label299: label316: label455: for (Integer localInteger8 = null; ; localInteger8 = Integer.valueOf(paramCursor.getInt(paramInt + 12)))
    {
      if (!paramCursor.isNull(paramInt + 13))
        break label473;
      localBoolean = null;
      return new Estacion(localLong, localInteger1, str1, localInteger2, localInteger3, localInteger4, localInteger5, localInteger6, str2, localFloat1, localFloat2, localInteger7, localInteger8, localBoolean);
      localLong = Long.valueOf(paramCursor.getLong(paramInt + 0));
      break;
      localInteger1 = Integer.valueOf(paramCursor.getInt(paramInt + 1));
      break label29;
      str1 = paramCursor.getString(paramInt + 2);
      break label44;
      localInteger2 = Integer.valueOf(paramCursor.getInt(paramInt + 3));
      break label59;
      localInteger3 = Integer.valueOf(paramCursor.getInt(paramInt + 4));
      break label74;
      label333: localInteger4 = Integer.valueOf(paramCursor.getInt(paramInt + 5));
      break label89;
      label350: localInteger5 = Integer.valueOf(paramCursor.getInt(paramInt + 6));
      break label105;
      label368: localInteger6 = Integer.valueOf(paramCursor.getInt(paramInt + 7));
      break label121;
      str2 = paramCursor.getString(paramInt + 8);
      break label137;
      localFloat1 = Float.valueOf(paramCursor.getFloat(paramInt + 9));
      break label153;
      localFloat2 = Float.valueOf(paramCursor.getFloat(paramInt + 10));
      break label169;
      localInteger7 = Integer.valueOf(paramCursor.getInt(paramInt + 11));
      break label185;
    }
    label386: label401: label419: label437: label473: if (paramCursor.getShort(paramInt + 13) != 0);
    for (boolean bool = true; ; bool = false)
    {
      localBoolean = Boolean.valueOf(bool);
      break;
    }
  }

  public void readEntity(Cursor paramCursor, Estacion paramEstacion, int paramInt)
  {
    Long localLong;
    Integer localInteger1;
    label36: String str1;
    label57: Integer localInteger2;
    label78: Integer localInteger3;
    label99: Integer localInteger4;
    label120: Integer localInteger5;
    label142: Integer localInteger6;
    label164: String str2;
    label186: Float localFloat1;
    label208: Float localFloat2;
    label230: Integer localInteger7;
    if (paramCursor.isNull(paramInt + 0))
    {
      localLong = null;
      paramEstacion.setId(localLong);
      if (!paramCursor.isNull(paramInt + 1))
        break label324;
      localInteger1 = null;
      paramEstacion.setIdEstacionAuxiliar(localInteger1);
      if (!paramCursor.isNull(paramInt + 2))
        break label341;
      str1 = null;
      paramEstacion.setNombreEstacion(str1);
      if (!paramCursor.isNull(paramInt + 3))
        break label355;
      localInteger2 = null;
      paramEstacion.setAfluenciaTotal(localInteger2);
      if (!paramCursor.isNull(paramInt + 4))
        break label372;
      localInteger3 = null;
      paramEstacion.setAfluenciaDiaHabil(localInteger3);
      if (!paramCursor.isNull(paramInt + 5))
        break label389;
      localInteger4 = null;
      paramEstacion.setAfluenciaSabado(localInteger4);
      if (!paramCursor.isNull(paramInt + 6))
        break label406;
      localInteger5 = null;
      paramEstacion.setAfluenciaDomingoYFestivo(localInteger5);
      if (!paramCursor.isNull(paramInt + 7))
        break label424;
      localInteger6 = null;
      paramEstacion.setNumeroAntena(localInteger6);
      if (!paramCursor.isNull(paramInt + 8))
        break label442;
      str2 = null;
      paramEstacion.setInfo(str2);
      if (!paramCursor.isNull(paramInt + 9))
        break label457;
      localFloat1 = null;
      paramEstacion.setLatitud(localFloat1);
      if (!paramCursor.isNull(paramInt + 10))
        break label475;
      localFloat2 = null;
      paramEstacion.setLongitud(localFloat2);
      if (!paramCursor.isNull(paramInt + 11))
        break label493;
      localInteger7 = null;
      label252: paramEstacion.setPosmapx(localInteger7);
      if (!paramCursor.isNull(paramInt + 12))
        break label511;
    }
    Boolean localBoolean;
    label324: label341: label355: label372: label511: for (Integer localInteger8 = null; ; localInteger8 = Integer.valueOf(paramCursor.getInt(paramInt + 12)))
    {
      paramEstacion.setPosmapy(localInteger8);
      boolean bool1 = paramCursor.isNull(paramInt + 13);
      localBoolean = null;
      if (!bool1)
        break label529;
      paramEstacion.setEsTransbordo(localBoolean);
      return;
      localLong = Long.valueOf(paramCursor.getLong(paramInt + 0));
      break;
      localInteger1 = Integer.valueOf(paramCursor.getInt(paramInt + 1));
      break label36;
      str1 = paramCursor.getString(paramInt + 2);
      break label57;
      localInteger2 = Integer.valueOf(paramCursor.getInt(paramInt + 3));
      break label78;
      localInteger3 = Integer.valueOf(paramCursor.getInt(paramInt + 4));
      break label99;
      localInteger4 = Integer.valueOf(paramCursor.getInt(paramInt + 5));
      break label120;
      localInteger5 = Integer.valueOf(paramCursor.getInt(paramInt + 6));
      break label142;
      localInteger6 = Integer.valueOf(paramCursor.getInt(paramInt + 7));
      break label164;
      str2 = paramCursor.getString(paramInt + 8);
      break label186;
      localFloat1 = Float.valueOf(paramCursor.getFloat(paramInt + 9));
      break label208;
      localFloat2 = Float.valueOf(paramCursor.getFloat(paramInt + 10));
      break label230;
      localInteger7 = Integer.valueOf(paramCursor.getInt(paramInt + 11));
      break label252;
    }
    label389: label406: label424: label442: label457: label475: label493: if (paramCursor.getShort(paramInt + 13) != 0);
    label529: for (boolean bool2 = true; ; bool2 = false)
    {
      localBoolean = Boolean.valueOf(bool2);
      break;
    }
  }

  public Long readKey(Cursor paramCursor, int paramInt)
  {
    if (paramCursor.isNull(paramInt + 0))
      return null;
    return Long.valueOf(paramCursor.getLong(paramInt + 0));
  }

  protected Long updateKeyAfterInsert(Estacion paramEstacion, long paramLong)
  {
    paramEstacion.setId(Long.valueOf(paramLong));
    return Long.valueOf(paramLong);
  }

  public static class Properties
  {
    public static final Property AfluenciaDiaHabil;
    public static final Property AfluenciaDomingoYFestivo;
    public static final Property AfluenciaSabado;
    public static final Property AfluenciaTotal;
    public static final Property EsTransbordo;
    public static final Property Id = new Property(0, Long.class, "id", true, "ID_ESTACION");
    public static final Property IdEstacionAuxiliar = new Property(1, Integer.class, "IdEstacionAuxiliar", false, "ID_ESTACION_AUXILIAR");
    public static final Property Info;
    public static final Property Latitud;
    public static final Property Longitud;
    public static final Property NombreEstacion = new Property(2, String.class, "nombreEstacion", false, "NOMBRE_ESTACION");
    public static final Property NumeroAntena;
    public static final Property Posmapx;
    public static final Property Posmapy;

    static
    {
      AfluenciaTotal = new Property(3, Integer.class, "afluenciaTotal", false, "AFLUENCIA_TOTAL");
      AfluenciaDiaHabil = new Property(4, Integer.class, "afluenciaDiaHabil", false, "AFLUENCIA_DIA_HABIL");
      AfluenciaSabado = new Property(5, Integer.class, "afluenciaSabado", false, "AFLUENCIA_SABADO");
      AfluenciaDomingoYFestivo = new Property(6, Integer.class, "afluenciaDomingoYFestivo", false, "AFLUENCIA_DOMINGO_YFESTIVO");
      NumeroAntena = new Property(7, Integer.class, "numeroAntena", false, "NUMERO_ANTENA");
      Info = new Property(8, String.class, "info", false, "INFO");
      Latitud = new Property(9, Float.class, "latitud", false, "LATITUD");
      Longitud = new Property(10, Float.class, "longitud", false, "LONGITUD");
      Posmapx = new Property(11, Integer.class, "Posmapx", false, "POSMAPX");
      Posmapy = new Property(12, Integer.class, "Posmapy", false, "POSMAPY");
      EsTransbordo = new Property(13, Boolean.class, "esTransbordo", false, "ES_TRANSBORDO");
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.daogenerator.EstacionDao
 * JD-Core Version:    0.6.0
 */