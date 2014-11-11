package de.greenrobot.dao;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.ReentrantLock;

public class IdentityScopeLong<T>
  implements IdentityScope<Long, T>
{
  private final ReentrantLock lock = new ReentrantLock();
  private final LongHashMap<Reference<T>> map = new LongHashMap();

  public void clear()
  {
    this.lock.lock();
    try
    {
      this.map.clear();
      return;
    }
    finally
    {
      this.lock.unlock();
    }
    throw localObject;
  }

  public boolean detach(Long paramLong, T paramT)
  {
    this.lock.lock();
    try
    {
      if ((get(paramLong) == paramT) && (paramT != null))
      {
        remove(paramLong);
        return true;
      }
      return false;
    }
    finally
    {
      this.lock.unlock();
    }
    throw localObject;
  }

  public T get(Long paramLong)
  {
    return get2(paramLong.longValue());
  }

  public T get2(long paramLong)
  {
    this.lock.lock();
    try
    {
      Reference localReference = (Reference)this.map.get(paramLong);
      this.lock.unlock();
      if (localReference != null)
        return localReference.get();
    }
    finally
    {
      this.lock.unlock();
    }
    return null;
  }

  public T get2NoLock(long paramLong)
  {
    Reference localReference = (Reference)this.map.get(paramLong);
    if (localReference != null)
      return localReference.get();
    return null;
  }

  public T getNoLock(Long paramLong)
  {
    return get2NoLock(paramLong.longValue());
  }

  public void lock()
  {
    this.lock.lock();
  }

  public void put(Long paramLong, T paramT)
  {
    put2(paramLong.longValue(), paramT);
  }

  public void put2(long paramLong, T paramT)
  {
    this.lock.lock();
    try
    {
      this.map.put(paramLong, new WeakReference(paramT));
      return;
    }
    finally
    {
      this.lock.unlock();
    }
    throw localObject;
  }

  public void put2NoLock(long paramLong, T paramT)
  {
    this.map.put(paramLong, new WeakReference(paramT));
  }

  public void putNoLock(Long paramLong, T paramT)
  {
    put2NoLock(paramLong.longValue(), paramT);
  }

  public void remove(Long paramLong)
  {
    this.lock.lock();
    try
    {
      this.map.remove(paramLong.longValue());
      return;
    }
    finally
    {
      this.lock.unlock();
    }
    throw localObject;
  }

  public void reserveRoom(int paramInt)
  {
    this.map.reserveRoom(paramInt);
  }

  public void unlock()
  {
    this.lock.unlock();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.IdentityScopeLong
 * JD-Core Version:    0.6.0
 */