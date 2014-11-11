package de.greenrobot.dao.test;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.AbstractDaoSession;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public abstract class AbstractDaoSessionTest<A extends Application, T extends AbstractDaoMaster, S extends AbstractDaoSession> extends DbTest<A>
{
  protected T daoMaster;
  private final Class<T> daoMasterClass;
  protected S daoSession;

  public AbstractDaoSessionTest(Class<T> paramClass)
  {
    this(paramClass, true);
  }

  public AbstractDaoSessionTest(Class<A> paramClass, Class<T> paramClass1, boolean paramBoolean)
  {
    super(paramClass, paramBoolean);
    this.daoMasterClass = paramClass1;
  }

  public AbstractDaoSessionTest(Class<T> paramClass, boolean paramBoolean)
  {
    super(paramBoolean);
    this.daoMasterClass = paramClass;
  }

  protected void setUp()
  {
    super.setUp();
    try
    {
      Constructor localConstructor = this.daoMasterClass.getConstructor(new Class[] { SQLiteDatabase.class });
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = this.db;
      this.daoMaster = ((AbstractDaoMaster)localConstructor.newInstance(arrayOfObject1));
      Class localClass = this.daoMasterClass;
      Class[] arrayOfClass = new Class[2];
      arrayOfClass[0] = SQLiteDatabase.class;
      arrayOfClass[1] = Boolean.TYPE;
      Method localMethod = localClass.getMethod("createAllTables", arrayOfClass);
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = this.db;
      arrayOfObject2[1] = Boolean.valueOf(false);
      localMethod.invoke(null, arrayOfObject2);
      this.daoSession = this.daoMaster.newSession();
      return;
    }
    catch (Exception localException)
    {
    }
    throw new RuntimeException("Could not prepare DAO session test", localException);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.test.AbstractDaoSessionTest
 * JD-Core Version:    0.6.0
 */