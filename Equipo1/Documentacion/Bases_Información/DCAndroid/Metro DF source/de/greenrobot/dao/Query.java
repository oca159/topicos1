package de.greenrobot.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.Collection;
import java.util.List;

public class Query<T> extends AbstractQuery<T>
{
  private int limitPosition = -1;
  private int offsetPosition = -1;

  Query(AbstractDao<T, ?> paramAbstractDao, String paramString, Collection<Object> paramCollection)
  {
    super(paramAbstractDao, paramString, paramCollection);
  }

  public List<T> list()
  {
    Cursor localCursor = this.dao.db.rawQuery(this.sql, this.parameters);
    return this.dao.loadAllAndCloseCursor(localCursor);
  }

  public CloseableListIterator<T> listIterator()
  {
    return listLazyUncached().listIteratorAutoClose();
  }

  public LazyList<T> listLazy()
  {
    Cursor localCursor = this.dao.db.rawQuery(this.sql, this.parameters);
    return new LazyList(this.dao, localCursor, true);
  }

  public LazyList<T> listLazyUncached()
  {
    Cursor localCursor = this.dao.db.rawQuery(this.sql, this.parameters);
    return new LazyList(this.dao, localCursor, false);
  }

  public void setLimit(int paramInt)
  {
    if (this.limitPosition == -1)
      throw new IllegalStateException("Limit must be set with QueryBuilder before it can be used here");
    this.parameters[this.limitPosition] = Integer.toString(paramInt);
  }

  void setLimitPosition(int paramInt)
  {
    this.limitPosition = paramInt;
  }

  public void setOffset(int paramInt)
  {
    if (this.offsetPosition == -1)
      throw new IllegalStateException("Offset must be set with QueryBuilder before it can be used here");
    this.parameters[this.offsetPosition] = Integer.toString(paramInt);
  }

  void setOffsetPosition(int paramInt)
  {
    this.offsetPosition = paramInt;
  }

  public void setParameter(int paramInt, Object paramObject)
  {
    if ((paramInt >= 0) && ((paramInt == this.limitPosition) || (paramInt == this.offsetPosition)))
      throw new IllegalArgumentException("Illegal parameter index: " + paramInt);
    if (paramObject != null)
    {
      this.parameters[paramInt] = paramObject.toString();
      return;
    }
    this.parameters[paramInt] = null;
  }

  public T unique()
  {
    Cursor localCursor = this.dao.db.rawQuery(this.sql, this.parameters);
    return this.dao.loadUniqueAndCloseCursor(localCursor);
  }

  public T uniqueOrThrow()
  {
    Object localObject = unique();
    if (localObject == null)
      throw new DaoException("No entity found for query");
    return localObject;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.Query
 * JD-Core Version:    0.6.0
 */