package org.slf4j.helpers;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.slf4j.Marker;

public class BasicMarker
  implements Marker
{
  private static String CLOSE;
  private static String OPEN = "[ ";
  private static String SEP;
  private static final long serialVersionUID = 1803952589649545191L;
  private final String name;
  private List refereceList;

  static
  {
    CLOSE = " ]";
    SEP = ", ";
  }

  BasicMarker(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("A merker name cannot be null");
    this.name = paramString;
  }

  public void add(Marker paramMarker)
  {
    monitorenter;
    if (paramMarker == null)
      try
      {
        throw new IllegalArgumentException("A null value cannot be added to a Marker as reference.");
      }
      finally
      {
        monitorexit;
      }
    boolean bool = contains(paramMarker);
    if (bool);
    while (true)
    {
      monitorexit;
      return;
      if (paramMarker.contains(this))
        continue;
      if (this.refereceList == null)
        this.refereceList = new Vector();
      this.refereceList.add(paramMarker);
    }
  }

  public boolean contains(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Other cannot be null");
    if (this.name.equals(paramString))
      return true;
    if (hasReferences())
      for (int i = 0; ; i++)
      {
        if (i >= this.refereceList.size())
          break label77;
        if (((Marker)this.refereceList.get(i)).contains(paramString))
          break;
      }
    label77: return false;
  }

  public boolean contains(Marker paramMarker)
  {
    if (paramMarker == null)
      throw new IllegalArgumentException("Other cannot be null");
    if (equals(paramMarker))
      return true;
    if (hasReferences())
      for (int i = 0; ; i++)
      {
        if (i >= this.refereceList.size())
          break label74;
        if (((Marker)this.refereceList.get(i)).contains(paramMarker))
          break;
      }
    label74: return false;
  }

  public boolean equals(Object paramObject)
  {
    int i;
    if (this == paramObject)
      i = 1;
    boolean bool;
    do
    {
      do
      {
        return i;
        i = 0;
      }
      while (paramObject == null);
      bool = paramObject instanceof Marker;
      i = 0;
    }
    while (!bool);
    Marker localMarker = (Marker)paramObject;
    return this.name.equals(localMarker.getName());
  }

  public String getName()
  {
    return this.name;
  }

  public boolean hasChildren()
  {
    return hasReferences();
  }

  public boolean hasReferences()
  {
    monitorenter;
    try
    {
      if (this.refereceList != null)
      {
        int j = this.refereceList.size();
        if (j > 0)
        {
          i = 1;
          return i;
        }
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public int hashCode()
  {
    return this.name.hashCode();
  }

  public Iterator iterator()
  {
    monitorenter;
    try
    {
      Iterator localIterator2;
      if (this.refereceList != null)
        localIterator2 = this.refereceList.iterator();
      Iterator localIterator1;
      for (Object localObject2 = localIterator2; ; localObject2 = localIterator1)
      {
        return localObject2;
        localIterator1 = Collections.EMPTY_LIST.iterator();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public boolean remove(Marker paramMarker)
  {
    monitorenter;
    try
    {
      List localList = this.refereceList;
      int i = 0;
      if (localList == null)
        return i;
      int j = this.refereceList.size();
      for (int k = 0; ; k++)
      {
        i = 0;
        if (k >= j)
          break;
        if (!paramMarker.equals((Marker)this.refereceList.get(k)))
          continue;
        this.refereceList.remove(k);
        i = 1;
        break;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public String toString()
  {
    if (!hasReferences())
      return getName();
    Iterator localIterator = iterator();
    StringBuffer localStringBuffer = new StringBuffer(getName());
    localStringBuffer.append(' ').append(OPEN);
    while (localIterator.hasNext())
    {
      localStringBuffer.append(((Marker)localIterator.next()).getName());
      if (!localIterator.hasNext())
        continue;
      localStringBuffer.append(SEP);
    }
    localStringBuffer.append(CLOSE);
    return localStringBuffer.toString();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.helpers.BasicMarker
 * JD-Core Version:    0.6.0
 */