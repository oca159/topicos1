package de.greenrobot.dao;

import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDaoMaster
{
  protected final Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> daoConfigMap;
  protected final SQLiteDatabase db;
  protected final int schemaVersion;

  public AbstractDaoMaster(SQLiteDatabase paramSQLiteDatabase, int paramInt)
  {
    this.db = paramSQLiteDatabase;
    this.schemaVersion = paramInt;
    this.daoConfigMap = new HashMap();
  }

  public int getSchemaVersion()
  {
    return this.schemaVersion;
  }

  public abstract AbstractDaoSession newSession();

  public abstract AbstractDaoSession newSession(IdentityScopeType paramIdentityScopeType);

  protected void registerDaoClass(Class<? extends AbstractDao<?, ?>> paramClass)
  {
    DaoConfig localDaoConfig = new DaoConfig(this.db, paramClass);
    this.daoConfigMap.put(paramClass, localDaoConfig);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.AbstractDaoMaster
 * JD-Core Version:    0.6.0
 */