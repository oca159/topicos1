package de.greenrobot.dao;

import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractDao<T, K>
{
  protected final DaoConfig config;
  protected final SQLiteDatabase db;
  protected IdentityScope<K, T> identityScope;
  protected IdentityScopeLong<T> identityScopeLong;
  protected final int pkOridinal;
  protected final AbstractDaoSession session;
  protected TableStatements statements;

  public AbstractDao(DaoConfig paramDaoConfig)
  {
    this(paramDaoConfig, null);
  }

  public AbstractDao(DaoConfig paramDaoConfig, AbstractDaoSession paramAbstractDaoSession)
  {
    this.config = paramDaoConfig;
    this.session = paramAbstractDaoSession;
    this.db = paramDaoConfig.db;
    this.identityScope = paramDaoConfig.getIdentityScope();
    if ((this.identityScope instanceof IdentityScopeLong))
      this.identityScopeLong = ((IdentityScopeLong)this.identityScope);
    this.statements = paramDaoConfig.statements;
    if (paramDaoConfig.pkProperty != null);
    for (int i = paramDaoConfig.pkProperty.oridinal; ; i = -1)
    {
      this.pkOridinal = i;
      return;
    }
  }

  protected void assertSinglePk()
  {
    if (this.config.pkColumns.length != 1)
      throw new DaoException(this + " (" + this.config.tablename + ") does not have a single-column primary key");
  }

  protected void attachEntity(T paramT)
  {
  }

  protected final void attachEntity(K paramK, T paramT, boolean paramBoolean)
  {
    if ((this.identityScope != null) && (paramK != null))
    {
      if (!paramBoolean)
        break label32;
      this.identityScope.put(paramK, paramT);
    }
    while (true)
    {
      attachEntity(paramT);
      return;
      label32: this.identityScope.putNoLock(paramK, paramT);
    }
  }

  protected abstract void bindValues(SQLiteStatement paramSQLiteStatement, T paramT);

  public long count()
  {
    return DatabaseUtils.queryNumEntries(this.db, '\'' + this.config.tablename + '\'');
  }

  public void delete(T paramT)
  {
    assertSinglePk();
    Object localObject = getKeyVerified(paramT);
    deleteByKey(localObject);
    if (this.identityScope != null)
      this.identityScope.remove(localObject);
  }

  public void deleteAll()
  {
    this.db.execSQL("DELETE FROM '" + this.config.tablename + "'");
    if (this.identityScope != null)
      this.identityScope.clear();
  }

  public void deleteByKey(K paramK)
  {
    assertSinglePk();
    synchronized (this.statements.getDeleteStatement())
    {
      if ((paramK instanceof Long))
      {
        ???.bindLong(1, ((Long)paramK).longValue());
        ???.execute();
        if (this.identityScope != null)
          this.identityScope.remove(paramK);
        return;
      }
      ???.bindString(1, paramK.toString());
    }
  }

  public boolean detach(T paramT)
  {
    if (this.identityScope != null)
    {
      Object localObject = getKeyVerified(paramT);
      return this.identityScope.detach(localObject, paramT);
    }
    return false;
  }

  public String[] getAllColumns()
  {
    return this.config.allColumns;
  }

  protected abstract K getKey(T paramT);

  protected K getKeyVerified(T paramT)
  {
    Object localObject = getKey(paramT);
    if (localObject == null)
    {
      if (paramT == null)
        throw new NullPointerException("Entity may not be null");
      throw new DaoException("Entity has no key");
    }
    return localObject;
  }

  public String[] getNonPkColumns()
  {
    return this.config.nonPkColumns;
  }

  public String[] getPkColumns()
  {
    return this.config.pkColumns;
  }

  public Property getPkProperty()
  {
    return this.config.pkProperty;
  }

  public Property[] getProperties()
  {
    return this.config.properties;
  }

  public AbstractDaoSession getSession()
  {
    return this.session;
  }

  TableStatements getStatements()
  {
    return this.config.statements;
  }

  public String getTablename()
  {
    return this.config.tablename;
  }

  public long insert(T paramT)
  {
    synchronized (this.statements.getInsertStatement())
    {
      bindValues(???, paramT);
      long l = ???.executeInsert();
      updateKeyAfterInsertAndAttach(paramT, l, true);
      return l;
    }
  }

  public void insertInTx(Iterable<T> paramIterable)
  {
    insertInTx(paramIterable, isEntityUpdateable());
  }

  public void insertInTx(Iterable<T> paramIterable, boolean paramBoolean)
  {
    while (true)
    {
      synchronized (this.statements.getInsertStatement())
      {
        this.db.beginTransaction();
        try
        {
          if (this.identityScope == null)
            continue;
          this.identityScope.lock();
          try
          {
            Iterator localIterator = paramIterable.iterator();
            boolean bool = localIterator.hasNext();
            if (bool)
              continue;
            if (this.identityScope == null)
              continue;
            this.identityScope.unlock();
            this.db.setTransactionSuccessful();
            this.db.endTransaction();
            return;
            Object localObject4 = (Object)localIterator.next();
            bindValues(???, localObject4);
            if (paramBoolean)
            {
              updateKeyAfterInsertAndAttach(localObject4, ???.executeInsert(), false);
              continue;
            }
          }
          finally
          {
            if (this.identityScope == null)
              continue;
            this.identityScope.unlock();
          }
        }
        finally
        {
          this.db.endTransaction();
        }
      }
      ???.execute();
    }
  }

  public void insertInTx(T[] paramArrayOfT)
  {
    insertInTx(Arrays.asList(paramArrayOfT), isEntityUpdateable());
  }

  public long insertOrReplace(T paramT)
  {
    synchronized (this.statements.getInsertOrReplaceStatement())
    {
      bindValues(???, paramT);
      long l = ???.executeInsert();
      updateKeyAfterInsertAndAttach(paramT, l, true);
      return l;
    }
  }

  public void insertWithoutSettingPk(T paramT)
  {
    synchronized (this.statements.getInsertStatement())
    {
      bindValues(???, paramT);
      ???.execute();
      return;
    }
  }

  protected abstract boolean isEntityUpdateable();

  public T load(K paramK)
  {
    assertSinglePk();
    Object localObject;
    if (paramK == null)
      localObject = null;
    do
    {
      return localObject;
      if (this.identityScope == null)
        break;
      localObject = this.identityScope.get(paramK);
    }
    while (localObject != null);
    String str = this.statements.getSelectByKey();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramK.toString();
    return loadUniqueAndCloseCursor(this.db.rawQuery(str, arrayOfString));
  }

  public List<T> loadAll()
  {
    return loadAllAndCloseCursor(this.db.rawQuery(this.statements.getSelectAll(), null));
  }

  protected List<T> loadAllAndCloseCursor(Cursor paramCursor)
  {
    try
    {
      List localList = loadAllFromCursor(paramCursor);
      return localList;
    }
    finally
    {
      paramCursor.close();
    }
    throw localObject;
  }

  protected List<T> loadAllFromCursor(Cursor paramCursor)
  {
    int i = paramCursor.getCount();
    ArrayList localArrayList = new ArrayList(i);
    CursorWindow localCursorWindow;
    if ((paramCursor instanceof CrossProcessCursor))
    {
      localCursorWindow = ((CrossProcessCursor)paramCursor).getWindow();
      if (localCursorWindow != null)
      {
        if (localCursorWindow.getNumRows() != i)
          break label138;
        paramCursor = new FastCursor(localCursorWindow);
      }
    }
    while (true)
    {
      if (paramCursor.moveToFirst())
        if (this.identityScope != null)
        {
          this.identityScope.lock();
          this.identityScope.reserveRoom(i);
        }
      try
      {
        boolean bool;
        do
        {
          localArrayList.add(loadCurrent(paramCursor, 0, false));
          bool = paramCursor.moveToNext();
        }
        while (bool);
        return localArrayList;
        label138: DaoLog.d("Window vs. result size: " + localCursorWindow.getNumRows() + "/" + i);
      }
      finally
      {
        if (this.identityScope != null)
          this.identityScope.unlock();
      }
    }
  }

  public T loadByRowId(long paramLong)
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = Long.toString(paramLong);
    return loadUniqueAndCloseCursor(this.db.rawQuery(this.statements.getSelectByRowId(), arrayOfString));
  }

  protected final T loadCurrent(Cursor paramCursor, int paramInt, boolean paramBoolean)
  {
    Object localObject3;
    if (this.identityScopeLong != null)
      if (paramInt != 0)
      {
        boolean bool = paramCursor.isNull(paramInt + this.pkOridinal);
        localObject3 = null;
        if (!bool);
      }
    label65: label114: label116: Object localObject2;
    do
    {
      while (true)
      {
        return localObject3;
        long l = paramCursor.getLong(paramInt + this.pkOridinal);
        Object localObject6;
        if (paramBoolean)
        {
          localObject3 = this.identityScopeLong.get2(l);
          if (localObject3 != null)
            break label114;
          localObject6 = readEntity(paramCursor, paramInt);
          if (!paramBoolean)
            break label116;
          this.identityScopeLong.put2(l, localObject6);
        }
        while (true)
        {
          attachEntity(localObject6);
          return localObject6;
          localObject3 = this.identityScopeLong.get2NoLock(l);
          break label65;
          break;
          this.identityScopeLong.put2NoLock(l, localObject6);
        }
        if (this.identityScope == null)
          break;
        Object localObject4 = readKey(paramCursor, paramInt);
        if (paramInt != 0)
        {
          localObject3 = null;
          if (localObject4 == null)
            continue;
        }
        if (paramBoolean);
        for (localObject3 = this.identityScope.get(localObject4); localObject3 == null; localObject3 = this.identityScope.getNoLock(localObject4))
        {
          Object localObject5 = readEntity(paramCursor, paramInt);
          attachEntity(localObject4, localObject5, paramBoolean);
          return localObject5;
        }
      }
      if (paramInt == 0)
        break;
      localObject2 = readKey(paramCursor, paramInt);
      localObject3 = null;
    }
    while (localObject2 == null);
    Object localObject1 = readEntity(paramCursor, paramInt);
    attachEntity(localObject1);
    return localObject1;
  }

  protected final <O> O loadCurrentOther(AbstractDao<O, ?> paramAbstractDao, Cursor paramCursor, int paramInt)
  {
    return paramAbstractDao.loadCurrent(paramCursor, paramInt, true);
  }

  protected T loadUnique(Cursor paramCursor)
  {
    if (!paramCursor.moveToFirst())
      return null;
    if (!paramCursor.isLast())
      throw new DaoException("Expected unique result, but count was " + paramCursor.getCount());
    return loadCurrent(paramCursor, 0, true);
  }

  protected T loadUniqueAndCloseCursor(Cursor paramCursor)
  {
    try
    {
      Object localObject2 = loadUnique(paramCursor);
      return localObject2;
    }
    finally
    {
      paramCursor.close();
    }
    throw localObject1;
  }

  public List<T> query(String paramString1, String[] paramArrayOfString, String paramString2, String paramString3, String paramString4)
  {
    return loadAllAndCloseCursor(this.db.query(this.config.tablename, getAllColumns(), paramString1, paramArrayOfString, paramString2, paramString3, paramString4));
  }

  public QueryBuilder<T> queryBuilder()
  {
    return new QueryBuilder(this);
  }

  public List<T> queryRaw(String paramString, String[] paramArrayOfString)
  {
    return loadAllAndCloseCursor(this.db.rawQuery(this.statements.getSelectAll() + paramString, paramArrayOfString));
  }

  protected abstract T readEntity(Cursor paramCursor, int paramInt);

  protected abstract void readEntity(Cursor paramCursor, T paramT, int paramInt);

  protected abstract K readKey(Cursor paramCursor, int paramInt);

  public void refresh(T paramT)
  {
    assertSinglePk();
    Object localObject1 = getKeyVerified(paramT);
    String str = this.statements.getSelectByKey();
    String[] arrayOfString = new String[1];
    arrayOfString[0] = localObject1.toString();
    Cursor localCursor = this.db.rawQuery(str, arrayOfString);
    try
    {
      if (!localCursor.moveToFirst())
        throw new DaoException("Entity does not exist in the database anymore: " + paramT.getClass() + " with key " + localObject1);
    }
    finally
    {
      localCursor.close();
    }
    if (!localCursor.isLast())
      throw new DaoException("Expected unique result, but count was " + localCursor.getCount());
    readEntity(localCursor, paramT, 0);
    attachEntity(localObject1, paramT, true);
    localCursor.close();
  }

  public void update(T paramT)
  {
    assertSinglePk();
    synchronized (this.statements.getUpdateStatement())
    {
      updateInsideSynchronized(paramT, ???, true);
      return;
    }
  }

  public void updateInTx(Iterable<T> paramIterable)
  {
    synchronized (this.statements.getUpdateStatement())
    {
      this.db.beginTransaction();
      try
      {
        if (this.identityScope != null)
          this.identityScope.lock();
        try
        {
          Iterator localIterator = paramIterable.iterator();
          while (true)
          {
            boolean bool = localIterator.hasNext();
            if (!bool)
            {
              if (this.identityScope != null)
                this.identityScope.unlock();
              this.db.setTransactionSuccessful();
              this.db.endTransaction();
              return;
            }
            updateInsideSynchronized((Object)localIterator.next(), ???, false);
          }
        }
        finally
        {
          if (this.identityScope != null)
            this.identityScope.unlock();
        }
      }
      finally
      {
        this.db.endTransaction();
      }
    }
  }

  protected void updateInsideSynchronized(T paramT, SQLiteStatement paramSQLiteStatement, boolean paramBoolean)
  {
    bindValues(paramSQLiteStatement, paramT);
    int i = 1 + this.config.allColumns.length;
    Object localObject = getKey(paramT);
    if ((localObject instanceof Long))
      paramSQLiteStatement.bindLong(i, ((Long)localObject).longValue());
    while (true)
    {
      paramSQLiteStatement.execute();
      attachEntity(localObject, paramT, paramBoolean);
      return;
      paramSQLiteStatement.bindString(i, localObject.toString());
    }
  }

  protected abstract K updateKeyAfterInsert(T paramT, long paramLong);

  protected void updateKeyAfterInsertAndAttach(T paramT, long paramLong, boolean paramBoolean)
  {
    attachEntity(updateKeyAfterInsert(paramT, paramLong), paramT, paramBoolean);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.AbstractDao
 * JD-Core Version:    0.6.0
 */