package de.greenrobot.dao;

public abstract interface IdentityScope<K, T>
{
  public abstract void clear();

  public abstract boolean detach(K paramK, T paramT);

  public abstract T get(K paramK);

  public abstract T getNoLock(K paramK);

  public abstract void lock();

  public abstract void put(K paramK, T paramT);

  public abstract void putNoLock(K paramK, T paramT);

  public abstract void remove(K paramK);

  public abstract void reserveRoom(int paramInt);

  public abstract void unlock();
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.IdentityScope
 * JD-Core Version:    0.6.0
 */