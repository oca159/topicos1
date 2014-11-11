package de.greenrobot.dao.test;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoLog;
import de.greenrobot.dao.IdentityScope;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.UnitTestDaoAccess;
import java.lang.reflect.Method;

public abstract class AbstractDaoTest<D extends AbstractDao<T, K>, T, K> extends DbTest<Application>
{
  protected D dao;
  protected UnitTestDaoAccess<T, K> daoAccess;
  protected final Class<D> daoClass;
  protected IdentityScope<K, T> identityScopeForDao;
  protected Property pkColumn;

  public AbstractDaoTest(Class<D> paramClass)
  {
    this(paramClass, true);
  }

  public AbstractDaoTest(Class<D> paramClass, boolean paramBoolean)
  {
    super(paramBoolean);
    this.daoClass = paramClass;
  }

  protected void clearIdentityScopeIfAny()
  {
    if (this.identityScopeForDao != null)
    {
      this.identityScopeForDao.clear();
      DaoLog.d("Identity scope cleared");
      return;
    }
    DaoLog.d("No identity scope to clear");
  }

  public void setIdentityScopeBeforeSetUp(IdentityScope<K, T> paramIdentityScope)
  {
    this.identityScopeForDao = paramIdentityScope;
  }

  protected void setUp()
  {
    super.setUp();
    try
    {
      setUpTableForDao();
      this.daoAccess = new UnitTestDaoAccess(this.db, this.daoClass, this.identityScopeForDao);
      this.dao = this.daoAccess.getDao();
      return;
    }
    catch (Exception localException)
    {
    }
    throw new RuntimeException("Could not prepare DAO Test", localException);
  }

  protected void setUpTableForDao()
    throws Exception
  {
    Class localClass = this.daoClass;
    Class[] arrayOfClass = new Class[2];
    arrayOfClass[0] = SQLiteDatabase.class;
    arrayOfClass[1] = Boolean.TYPE;
    Method localMethod = localClass.getMethod("createTable", arrayOfClass);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.db;
    arrayOfObject[1] = Boolean.valueOf(false);
    localMethod.invoke(null, arrayOfObject);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.test.AbstractDaoTest
 * JD-Core Version:    0.6.0
 */