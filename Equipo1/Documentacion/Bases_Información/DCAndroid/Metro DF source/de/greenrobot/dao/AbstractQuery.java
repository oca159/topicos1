package de.greenrobot.dao;

import java.util.Collection;
import java.util.Iterator;

abstract class AbstractQuery<T>
{
  protected final AbstractDao<T, ?> dao;
  protected final String[] parameters;
  protected final String sql;

  protected AbstractQuery(AbstractDao<T, ?> paramAbstractDao, String paramString, Collection<Object> paramCollection)
  {
    this.dao = paramAbstractDao;
    this.sql = paramString;
    this.parameters = new String[paramCollection.size()];
    int i = 0;
    Iterator localIterator = paramCollection.iterator();
    if (!localIterator.hasNext())
      return;
    Object localObject = localIterator.next();
    if (localObject != null)
      this.parameters[i] = localObject.toString();
    while (true)
    {
      i++;
      break;
      this.parameters[i] = null;
    }
  }

  public void setParameter(int paramInt, Object paramObject)
  {
    if (paramObject != null)
    {
      this.parameters[paramInt] = paramObject.toString();
      return;
    }
    this.parameters[paramInt] = null;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.AbstractQuery
 * JD-Core Version:    0.6.0
 */