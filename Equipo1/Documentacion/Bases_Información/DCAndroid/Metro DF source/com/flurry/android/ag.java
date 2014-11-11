package com.flurry.android;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

final class ag
{
  private LinkedHashMap a = new h(this, 1 + (int)Math.ceil(100 / 0.75F), 0.75F);
  private int b = 100;

  ag(int paramInt)
  {
  }

  final int a()
  {
    monitorenter;
    try
    {
      int i = this.a.size();
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  final Object a(Object paramObject)
  {
    monitorenter;
    try
    {
      Object localObject2 = this.a.get(paramObject);
      monitorexit;
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      monitorexit;
    }
    throw localObject1;
  }

  final void a(Object paramObject1, Object paramObject2)
  {
    monitorenter;
    try
    {
      this.a.put(paramObject1, paramObject2);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  final List b()
  {
    monitorenter;
    try
    {
      ArrayList localArrayList = new ArrayList(this.a.entrySet());
      monitorexit;
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  final Set c()
  {
    monitorenter;
    try
    {
      Set localSet = this.a.keySet();
      monitorexit;
      return localSet;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.ag
 * JD-Core Version:    0.6.0
 */