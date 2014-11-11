package de.greenrobot.dao.test;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;
import java.util.Random;

public abstract class DbTest<T extends Application> extends ApplicationTestCase<T>
{
  protected SQLiteDatabase db;
  protected final boolean inMemory;
  protected Random random;

  public DbTest()
  {
    this(true);
  }

  public DbTest(Class<T> paramClass, boolean paramBoolean)
  {
    super(paramClass);
    this.inMemory = paramBoolean;
    this.random = new Random();
  }

  public DbTest(boolean paramBoolean)
  {
    this(Application.class, paramBoolean);
  }

  protected void setUp()
  {
    createApplication();
    setUpDb();
  }

  protected void setUpDb()
  {
    if (this.inMemory)
    {
      this.db = SQLiteDatabase.create(null);
      return;
    }
    getApplication().deleteDatabase("test-db");
    this.db = getApplication().openOrCreateDatabase("test-db", 0, null);
  }

  protected void tearDown()
    throws Exception
  {
    this.db.close();
    if (!this.inMemory)
      getApplication().deleteDatabase("test-db");
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.test.DbTest
 * JD-Core Version:    0.6.0
 */