package org.slf4j.helpers;

import java.util.Map;
import org.slf4j.spi.MDCAdapter;

public class NOPMakerAdapter
  implements MDCAdapter
{
  public void clear()
  {
  }

  public String get(String paramString)
  {
    return null;
  }

  public Map getCopyOfContextMap()
  {
    return null;
  }

  public void put(String paramString1, String paramString2)
  {
  }

  public void remove(String paramString)
  {
  }

  public void setContextMap(Map paramMap)
  {
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.helpers.NOPMakerAdapter
 * JD-Core Version:    0.6.0
 */