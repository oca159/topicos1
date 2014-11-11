package com.crayonlion.daogenerator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.IdentityScopeType;

public class DaoMaster extends AbstractDaoMaster
{
  public static final int SCHEMA_VERSION = 1;

  public DaoMaster(SQLiteDatabase paramSQLiteDatabase)
  {
    super(paramSQLiteDatabase, 1);
    registerDaoClass(EstacionDao.class);
    registerDaoClass(LineaDao.class);
    registerDaoClass(ConexionDao.class);
  }

  public static void createAllTables(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean)
  {
    EstacionDao.createTable(paramSQLiteDatabase, paramBoolean);
    LineaDao.createTable(paramSQLiteDatabase, paramBoolean);
    ConexionDao.createTable(paramSQLiteDatabase, paramBoolean);
  }

  public static void dropAllTables(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean)
  {
    EstacionDao.dropTable(paramSQLiteDatabase, paramBoolean);
    LineaDao.dropTable(paramSQLiteDatabase, paramBoolean);
    ConexionDao.dropTable(paramSQLiteDatabase, paramBoolean);
  }

  public DaoSession newSession()
  {
    return new DaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
  }

  public DaoSession newSession(IdentityScopeType paramIdentityScopeType)
  {
    return new DaoSession(this.db, paramIdentityScopeType, this.daoConfigMap);
  }

  public static class DevOpenHelper extends DaoMaster.OpenHelper
  {
    public DevOpenHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory)
    {
      super(paramString, paramCursorFactory);
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      Log.i("greenDAO", "Upgrading schema from version " + paramInt1 + " to " + paramInt2 + " by dropping all tables");
      DaoMaster.dropAllTables(paramSQLiteDatabase, true);
      onCreate(paramSQLiteDatabase);
    }
  }

  public static abstract class OpenHelper extends SQLiteOpenHelper
  {
    public OpenHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory)
    {
      super(paramString, paramCursorFactory, 1);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      Log.i("greenDAO", "Creating tables for schema version 1");
      DaoMaster.createAllTables(paramSQLiteDatabase, false);
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.daogenerator.DaoMaster
 * JD-Core Version:    0.6.0
 */