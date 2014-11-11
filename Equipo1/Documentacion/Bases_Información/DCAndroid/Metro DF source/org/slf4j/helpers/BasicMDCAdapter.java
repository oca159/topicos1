package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.spi.MDCAdapter;

public class BasicMDCAdapter
  implements MDCAdapter
{
  private InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();

  public void clear()
  {
    HashMap localHashMap = (HashMap)this.inheritableThreadLocal.get();
    if (localHashMap != null)
    {
      localHashMap.clear();
      this.inheritableThreadLocal.remove();
    }
  }

  public String get(String paramString)
  {
    HashMap localHashMap = (HashMap)this.inheritableThreadLocal.get();
    if ((localHashMap != null) && (paramString != null))
      return (String)localHashMap.get(paramString);
    return null;
  }

  public Map getCopyOfContextMap()
  {
    HashMap localHashMap = (HashMap)this.inheritableThreadLocal.get();
    if (localHashMap != null)
      return new HashMap(localHashMap);
    return null;
  }

  public Set getKeys()
  {
    HashMap localHashMap = (HashMap)this.inheritableThreadLocal.get();
    if (localHashMap != null)
      return localHashMap.keySet();
    return null;
  }

  public void put(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("key cannot be null");
    HashMap localHashMap = (HashMap)this.inheritableThreadLocal.get();
    if (localHashMap == null)
    {
      localHashMap = new HashMap();
      this.inheritableThreadLocal.set(localHashMap);
    }
    localHashMap.put(paramString1, paramString2);
  }

  public void remove(String paramString)
  {
    HashMap localHashMap = (HashMap)this.inheritableThreadLocal.get();
    if (localHashMap != null)
      localHashMap.remove(paramString);
  }

  public void setContextMap(Map paramMap)
  {
    HashMap localHashMap1 = (HashMap)this.inheritableThreadLocal.get();
    if (localHashMap1 != null)
    {
      localHashMap1.clear();
      localHashMap1.putAll(paramMap);
      return;
    }
    HashMap localHashMap2 = new HashMap(paramMap);
    this.inheritableThreadLocal.set(localHashMap2);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.helpers.BasicMDCAdapter
 * JD-Core Version:    0.6.0
 */