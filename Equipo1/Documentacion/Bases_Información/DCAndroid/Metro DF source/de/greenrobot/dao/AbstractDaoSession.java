package de.greenrobot.dao;

import android.database.sqlite.SQLiteDatabase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class AbstractDaoSession
{
  private final SQLiteDatabase db;
  private final Map<Class<?>, AbstractDao<?, ?>> entityToDao;

  public AbstractDaoSession(SQLiteDatabase paramSQLiteDatabase)
  {
    this.db = paramSQLiteDatabase;
    this.entityToDao = new HashMap();
  }

  public <V> V callInTx(Callable<V> paramCallable)
    throws Exception
  {
    this.db.beginTransaction();
    try
    {
      Object localObject2 = paramCallable.call();
      this.db.setTransactionSuccessful();
      return localObject2;
    }
    finally
    {
      this.db.endTransaction();
    }
    throw localObject1;
  }

  public <T> void delete(T paramT)
  {
    getDao(paramT.getClass()).delete(paramT);
  }

  public <T> void deleteAll(Class<T> paramClass)
  {
    getDao(paramClass).deleteAll();
  }

  public AbstractDao<?, ?> getDao(Class<? extends Object> paramClass)
  {
    AbstractDao localAbstractDao = (AbstractDao)this.entityToDao.get(paramClass);
    if (localAbstractDao == null)
      throw new DaoException("No DAO registered for " + paramClass);
    return localAbstractDao;
  }

  public <T> long insert(T paramT)
  {
    return getDao(paramT.getClass()).insert(paramT);
  }

  public <T> long insertOrReplace(T paramT)
  {
    return getDao(paramT.getClass()).insertOrReplace(paramT);
  }

  public <T, K> T load(Class<T> paramClass, K paramK)
  {
    return getDao(paramClass).load(paramK);
  }

  public <T, K> List<T> loadAll(Class<T> paramClass)
  {
    return getDao(paramClass).loadAll();
  }

  public <T> QueryBuilder<T> queryBuilder(Class<T> paramClass)
  {
    return getDao(paramClass).queryBuilder();
  }

  public <T, K> List<T> queryRaw(Class<T> paramClass, String paramString, String[] paramArrayOfString)
  {
    return getDao(paramClass).queryRaw(paramString, paramArrayOfString);
  }

  public <T> void refresh(T paramT)
  {
    getDao(paramT.getClass()).refresh(paramT);
  }

  protected <T> void registerDao(Class<T> paramClass, AbstractDao<T, ?> paramAbstractDao)
  {
    this.entityToDao.put(paramClass, paramAbstractDao);
  }

  public void runInTx(Runnable paramRunnable)
  {
    this.db.beginTransaction();
    try
    {
      paramRunnable.run();
      this.db.setTransactionSuccessful();
      return;
    }
    finally
    {
      this.db.endTransaction();
    }
    throw localObject;
  }

  public <T> void update(T paramT)
  {
    getDao(paramT.getClass()).update(paramT);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.AbstractDaoSession
 * JD-Core Version:    0.6.0
 */