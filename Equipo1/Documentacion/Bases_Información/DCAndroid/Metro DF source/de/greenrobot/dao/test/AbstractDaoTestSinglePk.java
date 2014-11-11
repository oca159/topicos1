package de.greenrobot.dao.test;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoLog;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.SqlUtils;
import de.greenrobot.dao.UnitTestDaoAccess;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class AbstractDaoTestSinglePk<D extends AbstractDao<T, K>, T, K> extends AbstractDaoTest<D, T, K>
{
  private Property pkColumn;
  protected Set<K> usedPks = new HashSet();

  public AbstractDaoTestSinglePk(Class<D> paramClass)
  {
    super(paramClass);
  }

  protected abstract T createEntity(K paramK);

  protected T createEntityWithRandomPk()
  {
    return createEntity(createRandomPk());
  }

  protected abstract K createRandomPk();

  protected K nextPk()
  {
    for (int i = 0; ; i++)
    {
      if (i >= 100000)
        throw new IllegalStateException("Could not find a new PK");
      Object localObject = createRandomPk();
      if (this.usedPks.add(localObject))
        return localObject;
    }
  }

  protected Cursor queryWithDummyColumnsInFront(int paramInt, String paramString, K paramK)
  {
    StringBuilder localStringBuilder = new StringBuilder("SELECT ");
    int i = 0;
    while (true)
    {
      Cursor localCursor;
      int j;
      if (i >= paramInt)
      {
        SqlUtils.appendColumns(localStringBuilder, "T", this.dao.getAllColumns()).append(" FROM ");
        localStringBuilder.append(this.dao.getTablename()).append(" T");
        if (paramK != null)
        {
          localStringBuilder.append(" WHERE ");
          assertEquals(1, this.dao.getPkColumns().length);
          localStringBuilder.append(this.dao.getPkColumns()[0]).append("=");
          DatabaseUtils.appendValueToSql(localStringBuilder, paramK);
        }
        String str = localStringBuilder.toString();
        localCursor = this.db.rawQuery(str, null);
        assertTrue(localCursor.moveToFirst());
        j = 0;
        if ((j >= paramInt) && (paramK == null));
      }
      try
      {
        assertEquals(1, localCursor.getCount());
        return localCursor;
        localStringBuilder.append(paramString).append(",");
        i++;
        continue;
        assertEquals(paramString, localCursor.getString(j));
        j++;
      }
      catch (RuntimeException localRuntimeException)
      {
        localCursor.close();
      }
    }
    throw localRuntimeException;
  }

  protected void runLoadPkTest(int paramInt)
  {
    Object localObject1 = nextPk();
    Object localObject2 = createEntity(localObject1);
    this.dao.insert(localObject2);
    Cursor localCursor = queryWithDummyColumnsInFront(paramInt, "42", localObject1);
    try
    {
      assertEquals(localObject1, this.daoAccess.readKey(localCursor, paramInt));
      return;
    }
    finally
    {
      localCursor.close();
    }
    throw localObject3;
  }

  protected void setUp()
  {
    super.setUp();
    Property[] arrayOfProperty = this.daoAccess.getProperties();
    int i = arrayOfProperty.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        if (this.pkColumn != null)
          break;
        throw new RuntimeException("Test does not work without a PK column");
      }
      Property localProperty = arrayOfProperty[j];
      if (!localProperty.primaryKey)
        continue;
      if (this.pkColumn != null)
        throw new RuntimeException("Test does not work with multiple PK columns");
      this.pkColumn = localProperty;
    }
  }

  public void testAssignPk()
  {
    if (this.daoAccess.isEntityUpdateable())
    {
      Object localObject1 = createEntity(null);
      if (localObject1 != null)
      {
        Object localObject2 = createEntity(null);
        this.dao.insert(localObject1);
        this.dao.insert(localObject2);
        Object localObject3 = this.daoAccess.getKey(localObject1);
        Object localObject4 = this.daoAccess.getKey(localObject2);
        assertFalse(localObject3.equals(localObject4));
        assertNotNull(this.dao.load(localObject3));
        assertNotNull(this.dao.load(localObject4));
        return;
      }
      DaoLog.d("Skipping testAssignPk for " + this.daoClass + " (createEntity returned null for null key)");
      return;
    }
    DaoLog.d("Skipping testAssignPk for not updateable " + this.daoClass);
  }

  public void testCount()
  {
    this.dao.deleteAll();
    assertEquals(0L, this.dao.count());
    this.dao.insert(createEntityWithRandomPk());
    assertEquals(1L, this.dao.count());
    this.dao.insert(createEntityWithRandomPk());
    assertEquals(2L, this.dao.count());
  }

  public void testDelete()
  {
    Object localObject1 = nextPk();
    this.dao.deleteByKey(localObject1);
    Object localObject2 = createEntity(localObject1);
    this.dao.insert(localObject2);
    assertNotNull(this.dao.load(localObject1));
    this.dao.deleteByKey(localObject1);
    assertNull(this.dao.load(localObject1));
  }

  public void testDeleteAll()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    Iterator localIterator;
    if (i >= 10)
    {
      this.dao.insertInTx(localArrayList);
      this.dao.deleteAll();
      assertEquals(0L, this.dao.count());
      localIterator = localArrayList.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return;
        localArrayList.add(createEntityWithRandomPk());
        i++;
        break;
      }
      Object localObject1 = (Object)localIterator.next();
      Object localObject2 = this.daoAccess.getKey(localObject1);
      assertNotNull(localObject2);
      assertNull(this.dao.load(localObject2));
    }
  }

  public void testInsertAndLoad()
  {
    Object localObject1 = nextPk();
    Object localObject2 = createEntity(localObject1);
    this.dao.insert(localObject2);
    Object localObject3 = this.dao.load(localObject1);
    assertNotNull(localObject3);
    assertEquals(this.daoAccess.getKey(localObject2), this.daoAccess.getKey(localObject3));
  }

  public void testInsertInTx()
  {
    this.dao.deleteAll();
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; ; i++)
    {
      if (i >= 20)
      {
        this.dao.insertInTx(localArrayList);
        assertEquals(localArrayList.size(), this.dao.count());
        return;
      }
      localArrayList.add(createEntityWithRandomPk());
    }
  }

  public void testInsertOrReplaceTwice()
  {
    Object localObject = createEntityWithRandomPk();
    assertEquals(this.dao.insert(localObject), this.dao.insertOrReplace(localObject));
  }

  public void testInsertTwice()
  {
    Object localObject = createEntity(nextPk());
    this.dao.insert(localObject);
    try
    {
      this.dao.insert(localObject);
      fail("Inserting twice should not work");
      return;
    }
    catch (SQLException localSQLException)
    {
    }
  }

  public void testLoadAll()
  {
    this.dao.deleteAll();
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; ; i++)
    {
      if (i >= 15)
      {
        this.dao.insertInTx(localArrayList);
        List localList = this.dao.loadAll();
        assertEquals(localArrayList.size(), localList.size());
        return;
      }
      localArrayList.add(createEntity(nextPk()));
    }
  }

  public void testLoadPk()
  {
    runLoadPkTest(0);
  }

  public void testLoadPkWithOffset()
  {
    runLoadPkTest(10);
  }

  public void testQuery()
  {
    this.dao.insert(createEntityWithRandomPk());
    Object localObject = nextPk();
    this.dao.insert(createEntity(localObject));
    this.dao.insert(createEntityWithRandomPk());
    String str = "WHERE " + this.dao.getPkColumns()[0] + "=?";
    AbstractDao localAbstractDao = this.dao;
    String[] arrayOfString = new String[1];
    arrayOfString[0] = localObject.toString();
    List localList = localAbstractDao.queryRaw(str, arrayOfString);
    assertEquals(1, localList.size());
    assertEquals(localObject, this.daoAccess.getKey(localList.get(0)));
  }

  public void testReadWithOffset()
  {
    Object localObject1 = nextPk();
    Object localObject2 = createEntity(localObject1);
    this.dao.insert(localObject2);
    Cursor localCursor = queryWithDummyColumnsInFront(5, "42", localObject1);
    try
    {
      Object localObject4 = this.daoAccess.readEntity(localCursor, 5);
      assertEquals(localObject1, this.daoAccess.getKey(localObject4));
      return;
    }
    finally
    {
      localCursor.close();
    }
    throw localObject3;
  }

  public void testRowId()
  {
    Object localObject1 = createEntityWithRandomPk();
    Object localObject2 = createEntityWithRandomPk();
    if (this.dao.insert(localObject1) != this.dao.insert(localObject2));
    for (boolean bool = true; ; bool = false)
    {
      assertTrue(bool);
      return;
    }
  }

  public void testUpdate()
  {
    this.dao.deleteAll();
    Object localObject = createEntityWithRandomPk();
    this.dao.insert(localObject);
    this.dao.update(localObject);
    assertEquals(1L, this.dao.count());
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.test.AbstractDaoTestSinglePk
 * JD-Core Version:    0.6.0
 */