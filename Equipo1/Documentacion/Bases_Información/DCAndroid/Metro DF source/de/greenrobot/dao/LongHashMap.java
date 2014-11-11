package de.greenrobot.dao;

import java.util.Arrays;

public final class LongHashMap<T>
{
  private int capacity;
  private int size;
  private Entry<T>[] table;
  private int threshold;

  public LongHashMap()
  {
    this(16);
  }

  public LongHashMap(int paramInt)
  {
    this.capacity = paramInt;
    this.threshold = (paramInt * 4 / 3);
    this.table = new Entry[paramInt];
  }

  public void clear()
  {
    this.size = 0;
    Arrays.fill(this.table, null);
  }

  public boolean containsKey(long paramLong)
  {
    int i = (0x7FFFFFFF & ((int)(paramLong >>> 32) ^ (int)paramLong)) % this.capacity;
    for (Entry localEntry = this.table[i]; ; localEntry = localEntry.next)
    {
      if (localEntry == null)
        return false;
      if (localEntry.key == paramLong)
        return true;
    }
  }

  public T get(long paramLong)
  {
    int i = (0x7FFFFFFF & ((int)(paramLong >>> 32) ^ (int)paramLong)) % this.capacity;
    for (Entry localEntry = this.table[i]; ; localEntry = localEntry.next)
    {
      if (localEntry == null)
        return null;
      if (localEntry.key == paramLong)
        return localEntry.value;
    }
  }

  public void logStats()
  {
    int i = 0;
    Entry[] arrayOfEntry = this.table;
    int j = arrayOfEntry.length;
    int k = 0;
    if (k >= j)
    {
      DaoLog.d("load: " + this.size / this.capacity + ", size: " + this.size + ", capa: " + this.capacity + ", collisions: " + i + ", collision ratio: " + i / this.size);
      return;
    }
    for (Entry localEntry = arrayOfEntry[k]; ; localEntry = localEntry.next)
    {
      if ((localEntry == null) || (localEntry.next == null))
      {
        k++;
        break;
      }
      i++;
    }
  }

  public T put(long paramLong, T paramT)
  {
    int i = (0x7FFFFFFF & ((int)(paramLong >>> 32) ^ (int)paramLong)) % this.capacity;
    Entry localEntry1 = this.table[i];
    for (Entry localEntry2 = localEntry1; ; localEntry2 = localEntry2.next)
    {
      if (localEntry2 == null)
      {
        this.table[i] = new Entry(paramLong, paramT, localEntry1);
        this.size = (1 + this.size);
        if (this.size > this.threshold)
          setCapacity(2 * this.capacity);
        return null;
      }
      if (localEntry2.key != paramLong)
        continue;
      Object localObject = localEntry2.value;
      localEntry2.value = paramT;
      return localObject;
    }
  }

  public T remove(long paramLong)
  {
    int i = (0x7FFFFFFF & ((int)(paramLong >>> 32) ^ (int)paramLong)) % this.capacity;
    Object localObject1 = null;
    Entry localEntry;
    for (Object localObject2 = this.table[i]; ; localObject2 = localEntry)
    {
      if (localObject2 == null)
        return null;
      localEntry = ((Entry)localObject2).next;
      if (((Entry)localObject2).key == paramLong)
      {
        if (localObject1 == null)
          this.table[i] = localEntry;
        while (true)
        {
          this.size = (-1 + this.size);
          return ((Entry)localObject2).value;
          localObject1.next = localEntry;
        }
      }
      localObject1 = localObject2;
    }
  }

  public void reserveRoom(int paramInt)
  {
    setCapacity(paramInt * 5 / 3);
  }

  public void setCapacity(int paramInt)
  {
    Entry[] arrayOfEntry = new Entry[paramInt];
    int i = this.table.length;
    int j = 0;
    if (j >= i)
    {
      this.table = arrayOfEntry;
      this.capacity = paramInt;
      this.threshold = (paramInt * 4 / 3);
      return;
    }
    Entry localEntry;
    for (Object localObject = this.table[j]; ; localObject = localEntry)
    {
      if (localObject == null)
      {
        j++;
        break;
      }
      long l = ((Entry)localObject).key;
      int k = (0x7FFFFFFF & ((int)(l >>> 32) ^ (int)l)) % paramInt;
      localEntry = ((Entry)localObject).next;
      ((Entry)localObject).next = arrayOfEntry[k];
      arrayOfEntry[k] = localObject;
    }
  }

  public int size()
  {
    return this.size;
  }

  static final class Entry<T>
  {
    final long key;
    Entry<T> next;
    T value;

    Entry(long paramLong, T paramT, Entry<T> paramEntry)
    {
      this.key = paramLong;
      this.value = paramT;
      this.next = paramEntry;
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.LongHashMap
 * JD-Core Version:    0.6.0
 */