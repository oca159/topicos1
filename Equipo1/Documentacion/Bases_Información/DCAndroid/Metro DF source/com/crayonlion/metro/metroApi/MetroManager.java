package com.crayonlion.metro.metroApi;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.crayonlion.daogenerator.Conexion;
import com.crayonlion.daogenerator.ConexionDao;
import com.crayonlion.daogenerator.ConexionDao.Properties;
import com.crayonlion.daogenerator.DaoMaster;
import com.crayonlion.daogenerator.DaoMaster.DevOpenHelper;
import com.crayonlion.daogenerator.DaoSession;
import com.crayonlion.daogenerator.Estacion;
import com.crayonlion.daogenerator.EstacionDao;
import com.crayonlion.daogenerator.EstacionDao.Properties;
import com.crayonlion.daogenerator.Linea;
import com.crayonlion.daogenerator.LineaDao;
import com.crayonlion.metro.model.Ruta;
import com.crayonlion.metro.utils.Console;
import com.google.gson.Gson;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.QueryBuilder;
import de.greenrobot.dao.WhereCondition;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MetroManager
{
  public static final int BUSQUEDA_POR_DISTANCIA = 3;
  public static final int BUSQUEDA_POR_ESTACIONES = 1;
  public static final int BUSQUEDA_POR_TIEMPO = 2;
  public static final int BUSQUEDA_POR_TRANSBORDOS = 0;
  public static final String DBMAP = "metro.mbtiles";
  private static final String DBNAME = "metro-db";
  public static final String DBPATH = "/data/data/com.crayonlion.metro/databases/";
  private static ConexionDao conexionDao;
  public static List<Conexion> conexiones;
  private static EstacionDao estacionDao;
  public static List<Estacion> estaciones;
  private static LineaDao lineaDao;
  public static List<Linea> lineas;

  public static Ruta buscaRuta(int paramInt1, int paramInt2, int paramInt3)
  {
    switch (paramInt3)
    {
    default:
      return null;
    case 1:
      return busquedaPorEstaciones(paramInt1, paramInt2);
    case 0:
      return busquedaPorTransbordosPro(paramInt1, paramInt2);
    case 3:
      return busquedaPorDistancia(paramInt1, paramInt2);
    case 2:
    }
    return busquedaPorTiempo(paramInt1, paramInt2);
  }

  private static Ruta busquedaPorDistancia(int paramInt1, int paramInt2)
  {
    Ruta localRuta = new Ruta();
    if (paramInt1 == paramInt2)
    {
      localRuta.addEstacion(getEstacionById(paramInt2));
      return localRuta;
    }
    int i = 0;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    ArrayList localArrayList4 = new ArrayList();
    ArrayList localArrayList5 = new ArrayList();
    localArrayList1.add(Integer.valueOf(paramInt1));
    localArrayList2.add(Integer.valueOf(-1));
    localArrayList5.add(Float.valueOf(0.0F));
    ArrayList localArrayList6 = new ArrayList();
    while (!localArrayList1.isEmpty())
    {
      int j = localArrayList1.size();
      int k = ((Integer)localArrayList1.remove(j - 1)).intValue();
      int m = ((Integer)localArrayList2.remove(j - 1)).intValue();
      float f1 = ((Float)localArrayList5.remove(j - 1)).floatValue();
      int n = localArrayList1.size();
      if (k == paramInt2)
      {
        int i5 = m;
        localRuta.addEstacion(getEstacionById(paramInt2));
        while (i5 != -1)
        {
          localRuta.addEstacion(getEstacionById(((Integer)localArrayList3.get(i5)).intValue()));
          i5 = ((Integer)localArrayList4.get(i5)).intValue();
        }
      }
      if (localArrayList3.contains(Integer.valueOf(k)))
        continue;
      localArrayList3.add(Integer.valueOf(k));
      localArrayList4.add(Integer.valueOf(m));
      localArrayList6.clear();
      localArrayList6.addAll(getVecinos(k));
      int i1 = localArrayList6.size();
      int i2 = 0;
      if (i2 < i1)
      {
        int i3 = ((Conexion)localArrayList6.get(i2)).getIdVecino().intValue();
        float f2;
        if (!localArrayList3.contains(Integer.valueOf(i3)))
          f2 = f1 + ((Conexion)localArrayList6.get(i2)).getDistanciaRecorridoMetros().floatValue();
        for (int i4 = 0; ; i4++)
        {
          if ((i4 < n) && (f2 <= ((Float)localArrayList5.get(i4)).floatValue()))
            continue;
          localArrayList1.add(i4, Integer.valueOf(i3));
          localArrayList5.add(i4, Float.valueOf(f2));
          localArrayList2.add(i4, Integer.valueOf(i));
          i2++;
          break;
        }
      }
      i++;
    }
    localArrayList1.clear();
    localArrayList2.clear();
    localArrayList3.clear();
    localArrayList4.clear();
    localArrayList5.clear();
    return localRuta;
  }

  private static Ruta busquedaPorEstaciones(int paramInt1, int paramInt2)
  {
    Ruta localRuta = new Ruta();
    if (paramInt1 == paramInt2)
    {
      localRuta.addEstacion(getEstacionById(paramInt2));
      return localRuta;
    }
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    ArrayList localArrayList4 = new ArrayList();
    localArrayList1.add(Integer.valueOf(paramInt1));
    localArrayList2.add(Integer.valueOf(-1));
    int i = 0;
    ArrayList localArrayList5 = new ArrayList();
    while (!localArrayList1.isEmpty())
    {
      int j = ((Integer)localArrayList1.remove(0)).intValue();
      int k = ((Integer)localArrayList2.remove(0)).intValue();
      if (j == paramInt2)
      {
        int i1 = k;
        localRuta.addEstacion(getEstacionById(paramInt2));
        while (i1 != -1)
        {
          localRuta.addEstacion(getEstacionById(((Integer)localArrayList3.get(i1)).intValue()));
          i1 = ((Integer)localArrayList4.get(i1)).intValue();
        }
      }
      if (localArrayList3.contains(Integer.valueOf(j)))
        continue;
      localArrayList4.add(Integer.valueOf(k));
      localArrayList3.add(Integer.valueOf(j));
      localArrayList5.clear();
      localArrayList5.addAll(getVecinos(j));
      int m = localArrayList5.size();
      for (int n = 0; n < m; n++)
      {
        if (localArrayList3.contains(((Conexion)localArrayList5.get(n)).getIdVecino()))
          continue;
        localArrayList1.add(((Conexion)localArrayList5.get(n)).getIdVecino());
        localArrayList2.add(Integer.valueOf(i));
      }
      i++;
    }
    localArrayList1.clear();
    localArrayList2.clear();
    localArrayList3.clear();
    localArrayList4.clear();
    return localRuta;
  }

  private static Ruta busquedaPorTiempo(int paramInt1, int paramInt2)
  {
    Ruta localRuta = new Ruta();
    if (paramInt1 == paramInt2)
    {
      localRuta.addEstacion(getEstacionById(paramInt2));
      return localRuta;
    }
    int i = 0;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    ArrayList localArrayList4 = new ArrayList();
    ArrayList localArrayList5 = new ArrayList();
    new ArrayList();
    localArrayList1.add(Integer.valueOf(paramInt1));
    localArrayList2.add(Integer.valueOf(-1));
    localArrayList5.add(Integer.valueOf(0));
    ArrayList localArrayList6 = new ArrayList();
    while (!localArrayList1.isEmpty())
    {
      int j = localArrayList1.size();
      int k = ((Integer)localArrayList1.remove(j - 1)).intValue();
      int m = ((Integer)localArrayList2.remove(j - 1)).intValue();
      int n = ((Integer)localArrayList5.remove(j - 1)).intValue();
      int i1 = localArrayList1.size();
      if (k == paramInt2)
      {
        int i7 = m;
        localRuta.addEstacion(getEstacionById(paramInt2));
        while (i7 != -1)
        {
          localRuta.addEstacion(getEstacionById(((Integer)localArrayList3.get(i7)).intValue()));
          i7 = ((Integer)localArrayList4.get(i7)).intValue();
        }
      }
      if (localArrayList3.contains(Integer.valueOf(k)))
        continue;
      localArrayList3.add(Integer.valueOf(k));
      localArrayList4.add(Integer.valueOf(m));
      localArrayList6.clear();
      localArrayList6.addAll(getVecinos(k));
      int i2 = localArrayList6.size();
      int i3 = 0;
      if (i3 < i2)
      {
        int i4;
        int i5;
        if (!localArrayList3.contains(((Conexion)localArrayList6.get(i3)).getIdVecino()))
        {
          i4 = ((Conexion)localArrayList6.get(i3)).getIdVecino().intValue();
          i5 = n + ((Conexion)localArrayList6.get(i3)).getTiempoRecorridoSegundos().intValue() + ((Conexion)localArrayList6.get(i3)).getTiempoTransbordoSegundos().intValue() + ((Conexion)localArrayList6.get(i3)).getTiempoParadaSegundos().intValue();
        }
        for (int i6 = 0; ; i6++)
        {
          if ((i6 < i1) && (i5 <= ((Integer)localArrayList5.get(i6)).intValue()))
            continue;
          localArrayList1.add(i6, Integer.valueOf(i4));
          localArrayList5.add(i6, Integer.valueOf(i5));
          localArrayList2.add(i6, Integer.valueOf(i));
          i3++;
          break;
        }
      }
      i++;
    }
    localArrayList1.clear();
    localArrayList2.clear();
    localArrayList3.clear();
    localArrayList4.clear();
    localArrayList5.clear();
    return localRuta;
  }

  private static Ruta busquedaPorTransbordosPro(int paramInt1, int paramInt2)
  {
    Ruta localRuta = new Ruta();
    if (paramInt1 == paramInt2)
      localRuta.addEstacion(getEstacionById(paramInt2));
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    ArrayList localArrayList3;
    ArrayList localArrayList4;
    ArrayList localArrayList5;
    ArrayList localArrayList6;
    ArrayList localArrayList7;
    ArrayList localArrayList8;
    ArrayList localArrayList9;
    ArrayList localArrayList11;
    ArrayList localArrayList12;
    int i;
    int k;
    int n;
    int i1;
    int i2;
    float f1;
    int i3;
    int i5;
    label420: int i7;
    label622: int i8;
    while (true)
    {
      ArrayList localArrayList10;
      int m;
      while (true)
      {
        return localRuta;
        localArrayList1 = new ArrayList();
        localArrayList2 = new ArrayList();
        localArrayList3 = new ArrayList();
        localArrayList4 = new ArrayList();
        localArrayList5 = new ArrayList();
        localArrayList6 = new ArrayList();
        localArrayList7 = new ArrayList();
        localArrayList8 = new ArrayList();
        localArrayList9 = new ArrayList();
        localArrayList10 = new ArrayList();
        localArrayList11 = new ArrayList();
        localArrayList12 = new ArrayList();
        localArrayList1.add(Integer.valueOf(paramInt1));
        localArrayList2.add(Integer.valueOf(-1));
        localArrayList3.add(Integer.valueOf(0));
        localArrayList4.add(Integer.valueOf(paramInt1));
        localArrayList5.add(Integer.valueOf(0));
        localArrayList6.add(Float.valueOf(0.0F));
        i = -1;
        if (localArrayList1.isEmpty())
          continue;
        int j = localArrayList1.size();
        k = ((Integer)localArrayList1.remove(j - 1)).intValue();
        m = ((Integer)localArrayList2.remove(j - 1)).intValue();
        n = ((Integer)localArrayList3.remove(j - 1)).intValue();
        i1 = ((Integer)localArrayList4.remove(j - 1)).intValue();
        i2 = ((Integer)localArrayList5.remove(j - 1)).intValue();
        f1 = ((Float)localArrayList6.remove(j - 1)).floatValue();
        i3 = localArrayList1.size();
        if (k != paramInt2)
          break;
        int i17 = m;
        localRuta.addEstacion(getEstacionById(paramInt2));
        while (i17 != -1)
        {
          localRuta.addEstacion(getEstacionById(((Integer)localArrayList7.get(i17)).intValue()));
          i17 = ((Integer)localArrayList8.get(i17)).intValue();
        }
      }
      int i4 = localArrayList7.size();
      i5 = 0;
      if ((i5 >= i4) || ((k == ((Integer)localArrayList7.get(i5)).intValue()) && (n == ((Integer)localArrayList9.get(i5)).intValue()) && (i2 == ((Integer)localArrayList11.get(i5)).intValue()) && (m == ((Integer)localArrayList8.get(i5)).intValue())))
      {
        if (i5 != i4)
          continue;
        i++;
        localArrayList7.add(Integer.valueOf(k));
        localArrayList8.add(Integer.valueOf(m));
        localArrayList9.add(Integer.valueOf(n));
        localArrayList10.add(Integer.valueOf(i1));
        localArrayList11.add(Integer.valueOf(i2));
        localArrayList7.size();
        localArrayList12.clear();
        localArrayList12.addAll(getVecinos(k));
        int i6 = localArrayList12.size();
        i7 = 0;
        if (i7 >= i6)
          break;
        i8 = ((Conexion)localArrayList12.get(i7)).getIdVecino().intValue();
        if (i8 != i1)
          break label668;
      }
    }
    label668: int i9;
    int i10;
    float f2;
    int i11;
    int i12;
    label733: 
    do
    {
      i7++;
      break label622;
      break;
      i5++;
      break label420;
      i9 = n;
      i10 = i2 + 1;
      f2 = f1 + ((Conexion)localArrayList12.get(i7)).getDistanciaRecorridoMetros().floatValue();
      if (getLineaByEstacionVecino(i1, k) != getLineaByEstacionVecino(k, i8))
        i9++;
      i11 = localArrayList7.size();
      i12 = 0;
      if (i12 >= i11)
        continue;
      if (i8 != ((Integer)localArrayList7.get(i12)).intValue())
        break label975;
      int i16 = ((Integer)localArrayList9.get(i12)).intValue();
      if ((i9 != i16) || (i10 != ((Integer)localArrayList11.get(i12)).intValue()) || (k != ((Integer)localArrayList8.get(i12)).intValue()))
        break label975;
    }
    while (i12 != i11);
    for (int i13 = 0; ; i13++)
    {
      if (i13 < i3)
      {
        int i14 = ((Integer)localArrayList3.get(i13)).intValue();
        if (i9 <= i14)
          break label981;
      }
      label975: label981: int i15;
      do
      {
        Integer localInteger1 = Integer.valueOf(i8);
        localArrayList1.add(i13, localInteger1);
        Integer localInteger2 = Integer.valueOf(i);
        localArrayList2.add(i13, localInteger2);
        Integer localInteger3 = Integer.valueOf(i9);
        localArrayList3.add(i13, localInteger3);
        Integer localInteger4 = Integer.valueOf(k);
        localArrayList4.add(i13, localInteger4);
        Integer localInteger5 = Integer.valueOf(i10);
        localArrayList5.add(i13, localInteger5);
        Float localFloat = Float.valueOf(f2);
        localArrayList6.add(i13, localFloat);
        break;
        i12++;
        break label733;
        i15 = ((Integer)localArrayList3.get(i13)).intValue();
      }
      while ((i9 == i15) && (f2 > ((Float)localArrayList6.get(i13)).floatValue()));
    }
  }

  public static void clean()
  {
    estaciones.clear();
    conexiones.clear();
    lineas.clear();
    estaciones = null;
    conexiones = null;
    lineas = null;
  }

  public static void copyDataBase(Context paramContext)
    throws IOException
  {
    Console.log("Creando base de datos");
    InputStream localInputStream = paramContext.getAssets().open("metro-db");
    new File("/data/data/com.crayonlion.metro/databases/", "metro-db").mkdir();
    FileOutputStream localFileOutputStream = new FileOutputStream("/data/data/com.crayonlion.metro/databases/metro-db");
    byte[] arrayOfByte = new byte[1024];
    while (true)
    {
      int i = localInputStream.read(arrayOfByte);
      if (i <= 0)
        break;
      localFileOutputStream.write(arrayOfByte, 0, i);
    }
    localFileOutputStream.flush();
    localFileOutputStream.close();
    localInputStream.close();
    Console.log("Terminado con Base de Datos");
  }

  public static void copyMap(Context paramContext)
    throws IOException
  {
    Console.log("Mapa");
    InputStream localInputStream = paramContext.getAssets().open("metro.mbtiles");
    FileOutputStream localFileOutputStream = new FileOutputStream("/data/data/com.crayonlion.metro/databases/metro.mbtiles");
    byte[] arrayOfByte = new byte[1024];
    while (true)
    {
      int i = localInputStream.read(arrayOfByte);
      if (i <= 0)
        break;
      localFileOutputStream.write(arrayOfByte, 0, i);
    }
    localFileOutputStream.flush();
    localFileOutputStream.close();
    localInputStream.close();
    Console.log("Terminado con Mapa");
  }

  public static Ruta deserializarRuta(String paramString)
  {
    return (Ruta)new Gson().fromJson(paramString, Ruta.class);
  }

  public static List<Conexion> getAllConexiones()
  {
    return conexiones;
  }

  public static List<Estacion> getAllEstaciones()
  {
    return estaciones;
  }

  public static String[] getAllEstacionesNames()
  {
    String[] arrayOfString = new String[-1 + estaciones.size()];
    for (int i = 0; i < arrayOfString.length; i++)
      arrayOfString[i] = ((Estacion)estaciones.get(i + 1)).getNombreEstacion();
    return arrayOfString;
  }

  public static List<Linea> getAllLineas()
  {
    return lineas;
  }

  public static List<Linea> getColorLineaById(int paramInt)
  {
    return lineaDao.queryBuilder().where(ConexionDao.Properties.IdLinea.eq(Integer.valueOf(paramInt)), new WhereCondition[0]).list();
  }

  public static Conexion getConexionByEstacionVecino(int paramInt1, int paramInt2)
  {
    if (paramInt1 == paramInt2)
      return new Conexion(Long.valueOf(-1L), Integer.valueOf(-1), Integer.valueOf(paramInt1), Integer.valueOf(0), Float.valueOf(0.0F), Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(paramInt1), Long.valueOf(-1L));
    QueryBuilder localQueryBuilder = conexionDao.queryBuilder();
    WhereCondition localWhereCondition = ConexionDao.Properties.IdEstacion.eq(Integer.valueOf(paramInt1));
    WhereCondition[] arrayOfWhereCondition = new WhereCondition[1];
    arrayOfWhereCondition[0] = ConexionDao.Properties.IdVecino.eq(Integer.valueOf(paramInt2));
    return (Conexion)localQueryBuilder.where(localWhereCondition, arrayOfWhereCondition).list().get(0);
  }

  public static Conexion getConexionById(int paramInt)
  {
    return (Conexion)conexiones.get(paramInt);
  }

  public static Estacion getEstacionById(int paramInt)
  {
    return (Estacion)estaciones.get(paramInt);
  }

  public static Estacion getEstacionByName(String paramString)
  {
    return (Estacion)estacionDao.queryBuilder().where(EstacionDao.Properties.NombreEstacion.eq(paramString.toLowerCase()), new WhereCondition[0]).list().get(0);
  }

  public static int getLineaByEstacionVecino(int paramInt1, int paramInt2)
  {
    if (paramInt1 == paramInt2)
      return -1;
    QueryBuilder localQueryBuilder = conexionDao.queryBuilder();
    WhereCondition localWhereCondition = ConexionDao.Properties.IdEstacion.eq(Integer.valueOf(paramInt1));
    WhereCondition[] arrayOfWhereCondition = new WhereCondition[1];
    arrayOfWhereCondition[0] = ConexionDao.Properties.IdVecino.eq(Integer.valueOf(paramInt2));
    return (int)((Conexion)localQueryBuilder.where(localWhereCondition, arrayOfWhereCondition).list().get(0)).getIdLinea().longValue();
  }

  public static Linea getLineaById(int paramInt)
  {
    return (Linea)lineas.get(paramInt);
  }

  private static ArrayList<Integer> getLineasByEstacionId(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = conexionDao.queryBuilder().where(ConexionDao.Properties.IdEstacion.eq(Integer.valueOf(paramInt)), new WhereCondition[0]).list();
    for (int i = 0; i < localList.size(); i++)
      localArrayList.add(Integer.valueOf((int)((Conexion)localList.get(i)).getIdLinea().longValue()));
    return localArrayList;
  }

  private static List<Conexion> getVecinos(int paramInt)
  {
    return conexionDao.queryBuilder().where(ConexionDao.Properties.IdEstacion.eq(Integer.valueOf(paramInt)), new WhereCondition[0]).list();
  }

  private static ArrayList<Integer> getVecinosByEstacionId(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = conexionDao.queryBuilder().where(ConexionDao.Properties.IdEstacion.eq(Integer.valueOf(paramInt)), new WhereCondition[0]).list();
    for (int i = 0; i < localList.size(); i++)
      localArrayList.add(((Conexion)localList.get(i)).getIdVecino());
    return localArrayList;
  }

  public static List<Conexion> getVecinosByEstacionLinea(int paramInt1, int paramInt2)
  {
    QueryBuilder localQueryBuilder = conexionDao.queryBuilder();
    WhereCondition localWhereCondition = ConexionDao.Properties.IdEstacion.eq(Integer.valueOf(paramInt1));
    WhereCondition[] arrayOfWhereCondition = new WhereCondition[1];
    arrayOfWhereCondition[0] = ConexionDao.Properties.IdLinea.eq(Integer.valueOf(paramInt2));
    return localQueryBuilder.where(localWhereCondition, arrayOfWhereCondition).list();
  }

  public static void initMetroManager(Context paramContext)
  {
    SQLiteDatabase localSQLiteDatabase;
    if (estaciones == null)
      localSQLiteDatabase = new DaoMaster.DevOpenHelper(paramContext, "metro-db", null).getWritableDatabase();
    try
    {
      copyDataBase(paramContext);
      copyMap(paramContext);
      DaoSession localDaoSession = new DaoMaster(localSQLiteDatabase).newSession();
      estacionDao = localDaoSession.getEstacionDao();
      conexionDao = localDaoSession.getConexionDao();
      lineaDao = localDaoSession.getLineaDao();
      estaciones = estacionDao.loadAll();
      conexiones = conexionDao.loadAll();
      lineas = lineaDao.loadAll();
      estaciones.add(0, new Estacion());
      conexiones.add(0, new Conexion());
      lineas.add(0, new Linea());
      Console.log("Todo cargado " + estaciones.size());
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        Log.v("metro", "No se pudo copiar la base de datos");
    }
  }

  public static String serializarRuta(Ruta paramRuta)
  {
    return new Gson().toJson(paramRuta);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.metroApi.MetroManager
 * JD-Core Version:    0.6.0
 */