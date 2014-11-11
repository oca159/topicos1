package de.greenrobot.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.lang.reflect.Constructor;

public class UnitTestDaoAccess<T, K>
{
  private final AbstractDao<T, K> dao;

  public UnitTestDaoAccess(SQLiteDatabase paramSQLiteDatabase, Class<AbstractDao<T, K>> paramClass, IdentityScope<?, ?> paramIdentityScope)
    throws Exception
  {
    DaoConfig localDaoConfig = new DaoConfig(paramSQLiteDatabase, paramClass);
    localDaoConfig.setIdentityScope(paramIdentityScope);
    this.dao = ((AbstractDao)paramClass.getConstructor(new Class[] { DaoConfig.class }).newInstance(new Object[] { localDaoConfig }));
  }

  public AbstractDao<T, K> getDao()
  {
    return this.dao;
  }

  public K getKey(T paramT)
  {
    return this.dao.getKey(paramT);
  }

  public Property[] getProperties()
  {
    return this.dao.getProperties();
  }

  public boolean isEntityUpdateable()
  {
    return this.dao.isEntityUpdateable();
  }

  public T readEntity(Cursor paramCursor, int paramInt)
  {
    return this.dao.readEntity(paramCursor, paramInt);
  }

  public K readKey(Cursor paramCursor, int paramInt)
  {
    return this.dao.readKey(paramCursor, paramInt);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.UnitTestDaoAccess
 * JD-Core Version:    0.6.0
 */