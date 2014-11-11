package com.crayonlion.daogenerator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.Property;

public class LineaDao extends AbstractDao<Linea, Long>
{
  public static final String TABLENAME = "LINEA";

  public LineaDao(DaoConfig paramDaoConfig)
  {
    super(paramDaoConfig);
  }

  public LineaDao(DaoConfig paramDaoConfig, DaoSession paramDaoSession)
  {
    super(paramDaoConfig, paramDaoSession);
  }

  public static void createTable(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS 'LINEA' ('ID_LINEA' INTEGER PRIMARY KEY ,'NOMBRE_LINEA' TEXT,'TIPO_TRANSPORTE' TEXT,'ID_LINEA_AUXILIAR' INTEGER,'DISTANCIA_TOTAL_LINEA_METROS' REAL,'TIEMPO_RECORRIDO_VIA1_SEGUNDOS' INTEGER,'TIEMPO_RECORRIDO_VIA2_SEGUNDOS' INTEGER,'TIEMPO_ESPERA_TERMINAL1_SEGUNDOS' INTEGER,'TIEMPO_ESPERA_TERMINAL2_SEGUNDOS' INTEGER,'COLOR' TEXT,'TERMINAL1' TEXT,'TERMINAL2' TEXT);");
  }

  public static void dropTable(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("DROP TABLE ");
    if (paramBoolean);
    for (String str = "IF EXISTS "; ; str = "")
    {
      paramSQLiteDatabase.execSQL(str + "'LINEA'");
      return;
    }
  }

  protected void bindValues(SQLiteStatement paramSQLiteStatement, Linea paramLinea)
  {
    paramSQLiteStatement.clearBindings();
    Long localLong = paramLinea.getId();
    if (localLong != null)
      paramSQLiteStatement.bindLong(1, localLong.longValue());
    String str1 = paramLinea.getNombreLinea();
    if (str1 != null)
      paramSQLiteStatement.bindString(2, str1);
    String str2 = paramLinea.getTipoTransporte();
    if (str2 != null)
      paramSQLiteStatement.bindString(3, str2);
    Integer localInteger1 = paramLinea.getIdLineaAuxiliar();
    if (localInteger1 != null)
      paramSQLiteStatement.bindLong(4, localInteger1.intValue());
    Float localFloat = paramLinea.getDistanciaTotalLineaMetros();
    if (localFloat != null)
      paramSQLiteStatement.bindDouble(5, localFloat.floatValue());
    Integer localInteger2 = paramLinea.getTiempoRecorridoVia1Segundos();
    if (localInteger2 != null)
      paramSQLiteStatement.bindLong(6, localInteger2.intValue());
    Integer localInteger3 = paramLinea.getTiempoRecorridoVia2Segundos();
    if (localInteger3 != null)
      paramSQLiteStatement.bindLong(7, localInteger3.intValue());
    Integer localInteger4 = paramLinea.getTiempoEsperaTerminal1Segundos();
    if (localInteger4 != null)
      paramSQLiteStatement.bindLong(8, localInteger4.intValue());
    Integer localInteger5 = paramLinea.getTiempoEsperaTerminal2Segundos();
    if (localInteger5 != null)
      paramSQLiteStatement.bindLong(9, localInteger5.intValue());
    String str3 = paramLinea.getColor();
    if (str3 != null)
      paramSQLiteStatement.bindString(10, str3);
    String str4 = paramLinea.getTerminal1();
    if (str4 != null)
      paramSQLiteStatement.bindString(11, str4);
    String str5 = paramLinea.getTerminal2();
    if (str5 != null)
      paramSQLiteStatement.bindString(12, str5);
  }

  public Long getKey(Linea paramLinea)
  {
    if (paramLinea != null)
      return paramLinea.getId();
    return null;
  }

  protected boolean isEntityUpdateable()
  {
    return true;
  }

  public Linea readEntity(Cursor paramCursor, int paramInt)
  {
    Long localLong;
    String str1;
    label29: String str2;
    label44: Integer localInteger1;
    label59: Float localFloat;
    label74: Integer localInteger2;
    label89: Integer localInteger3;
    label105: Integer localInteger4;
    label121: Integer localInteger5;
    label137: String str3;
    label153: String str4;
    if (paramCursor.isNull(paramInt + 0))
    {
      localLong = null;
      if (!paramCursor.isNull(paramInt + 1))
        break label232;
      str1 = null;
      if (!paramCursor.isNull(paramInt + 2))
        break label246;
      str2 = null;
      if (!paramCursor.isNull(paramInt + 3))
        break label260;
      localInteger1 = null;
      if (!paramCursor.isNull(paramInt + 4))
        break label277;
      localFloat = null;
      if (!paramCursor.isNull(paramInt + 5))
        break label294;
      localInteger2 = null;
      if (!paramCursor.isNull(paramInt + 6))
        break label311;
      localInteger3 = null;
      if (!paramCursor.isNull(paramInt + 7))
        break label329;
      localInteger4 = null;
      if (!paramCursor.isNull(paramInt + 8))
        break label347;
      localInteger5 = null;
      if (!paramCursor.isNull(paramInt + 9))
        break label365;
      str3 = null;
      if (!paramCursor.isNull(paramInt + 10))
        break label380;
      str4 = null;
      label169: if (!paramCursor.isNull(paramInt + 11))
        break label395;
    }
    label260: label395: for (String str5 = null; ; str5 = paramCursor.getString(paramInt + 11))
    {
      return new Linea(localLong, str1, str2, localInteger1, localFloat, localInteger2, localInteger3, localInteger4, localInteger5, str3, str4, str5);
      localLong = Long.valueOf(paramCursor.getLong(paramInt + 0));
      break;
      label232: str1 = paramCursor.getString(paramInt + 1);
      break label29;
      label246: str2 = paramCursor.getString(paramInt + 2);
      break label44;
      localInteger1 = Integer.valueOf(paramCursor.getInt(paramInt + 3));
      break label59;
      label277: localFloat = Float.valueOf(paramCursor.getFloat(paramInt + 4));
      break label74;
      label294: localInteger2 = Integer.valueOf(paramCursor.getInt(paramInt + 5));
      break label89;
      label311: localInteger3 = Integer.valueOf(paramCursor.getInt(paramInt + 6));
      break label105;
      label329: localInteger4 = Integer.valueOf(paramCursor.getInt(paramInt + 7));
      break label121;
      label347: localInteger5 = Integer.valueOf(paramCursor.getInt(paramInt + 8));
      break label137;
      label365: str3 = paramCursor.getString(paramInt + 9);
      break label153;
      label380: str4 = paramCursor.getString(paramInt + 10);
      break label169;
    }
  }

  public void readEntity(Cursor paramCursor, Linea paramLinea, int paramInt)
  {
    Long localLong;
    String str1;
    label36: String str2;
    label57: Integer localInteger1;
    label78: Float localFloat;
    label99: Integer localInteger2;
    label120: Integer localInteger3;
    label142: Integer localInteger4;
    label164: Integer localInteger5;
    label186: String str3;
    label208: String str4;
    label230: String str5;
    if (paramCursor.isNull(paramInt + 0))
    {
      localLong = null;
      paramLinea.setId(localLong);
      if (!paramCursor.isNull(paramInt + 1))
        break label280;
      str1 = null;
      paramLinea.setNombreLinea(str1);
      if (!paramCursor.isNull(paramInt + 2))
        break label294;
      str2 = null;
      paramLinea.setTipoTransporte(str2);
      if (!paramCursor.isNull(paramInt + 3))
        break label308;
      localInteger1 = null;
      paramLinea.setIdLineaAuxiliar(localInteger1);
      if (!paramCursor.isNull(paramInt + 4))
        break label325;
      localFloat = null;
      paramLinea.setDistanciaTotalLineaMetros(localFloat);
      if (!paramCursor.isNull(paramInt + 5))
        break label342;
      localInteger2 = null;
      paramLinea.setTiempoRecorridoVia1Segundos(localInteger2);
      if (!paramCursor.isNull(paramInt + 6))
        break label359;
      localInteger3 = null;
      paramLinea.setTiempoRecorridoVia2Segundos(localInteger3);
      if (!paramCursor.isNull(paramInt + 7))
        break label377;
      localInteger4 = null;
      paramLinea.setTiempoEsperaTerminal1Segundos(localInteger4);
      if (!paramCursor.isNull(paramInt + 8))
        break label395;
      localInteger5 = null;
      paramLinea.setTiempoEsperaTerminal2Segundos(localInteger5);
      if (!paramCursor.isNull(paramInt + 9))
        break label413;
      str3 = null;
      paramLinea.setColor(str3);
      if (!paramCursor.isNull(paramInt + 10))
        break label428;
      str4 = null;
      paramLinea.setTerminal1(str4);
      boolean bool = paramCursor.isNull(paramInt + 11);
      str5 = null;
      if (!bool)
        break label443;
    }
    while (true)
    {
      paramLinea.setTerminal2(str5);
      return;
      localLong = Long.valueOf(paramCursor.getLong(paramInt + 0));
      break;
      label280: str1 = paramCursor.getString(paramInt + 1);
      break label36;
      label294: str2 = paramCursor.getString(paramInt + 2);
      break label57;
      label308: localInteger1 = Integer.valueOf(paramCursor.getInt(paramInt + 3));
      break label78;
      label325: localFloat = Float.valueOf(paramCursor.getFloat(paramInt + 4));
      break label99;
      label342: localInteger2 = Integer.valueOf(paramCursor.getInt(paramInt + 5));
      break label120;
      label359: localInteger3 = Integer.valueOf(paramCursor.getInt(paramInt + 6));
      break label142;
      label377: localInteger4 = Integer.valueOf(paramCursor.getInt(paramInt + 7));
      break label164;
      label395: localInteger5 = Integer.valueOf(paramCursor.getInt(paramInt + 8));
      break label186;
      label413: str3 = paramCursor.getString(paramInt + 9);
      break label208;
      label428: str4 = paramCursor.getString(paramInt + 10);
      break label230;
      label443: str5 = paramCursor.getString(paramInt + 11);
    }
  }

  public Long readKey(Cursor paramCursor, int paramInt)
  {
    if (paramCursor.isNull(paramInt + 0))
      return null;
    return Long.valueOf(paramCursor.getLong(paramInt + 0));
  }

  protected Long updateKeyAfterInsert(Linea paramLinea, long paramLong)
  {
    paramLinea.setId(Long.valueOf(paramLong));
    return Long.valueOf(paramLong);
  }

  public static class Properties
  {
    public static final Property Color;
    public static final Property DistanciaTotalLineaMetros;
    public static final Property Id = new Property(0, Long.class, "id", true, "ID_LINEA");
    public static final Property IdLineaAuxiliar;
    public static final Property NombreLinea = new Property(1, String.class, "nombreLinea", false, "NOMBRE_LINEA");
    public static final Property Terminal1;
    public static final Property Terminal2;
    public static final Property TiempoEsperaTerminal1Segundos;
    public static final Property TiempoEsperaTerminal2Segundos;
    public static final Property TiempoRecorridoVia1Segundos;
    public static final Property TiempoRecorridoVia2Segundos;
    public static final Property TipoTransporte = new Property(2, String.class, "tipoTransporte", false, "TIPO_TRANSPORTE");

    static
    {
      IdLineaAuxiliar = new Property(3, Integer.class, "IdLineaAuxiliar", false, "ID_LINEA_AUXILIAR");
      DistanciaTotalLineaMetros = new Property(4, Float.class, "distanciaTotalLineaMetros", false, "DISTANCIA_TOTAL_LINEA_METROS");
      TiempoRecorridoVia1Segundos = new Property(5, Integer.class, "tiempoRecorridoVia1Segundos", false, "TIEMPO_RECORRIDO_VIA1_SEGUNDOS");
      TiempoRecorridoVia2Segundos = new Property(6, Integer.class, "tiempoRecorridoVia2Segundos", false, "TIEMPO_RECORRIDO_VIA2_SEGUNDOS");
      TiempoEsperaTerminal1Segundos = new Property(7, Integer.class, "tiempoEsperaTerminal1Segundos", false, "TIEMPO_ESPERA_TERMINAL1_SEGUNDOS");
      TiempoEsperaTerminal2Segundos = new Property(8, Integer.class, "tiempoEsperaTerminal2Segundos", false, "TIEMPO_ESPERA_TERMINAL2_SEGUNDOS");
      Color = new Property(9, String.class, "color", false, "COLOR");
      Terminal1 = new Property(10, String.class, "terminal1", false, "TERMINAL1");
      Terminal2 = new Property(11, String.class, "terminal2", false, "TERMINAL2");
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.daogenerator.LineaDao
 * JD-Core Version:    0.6.0
 */