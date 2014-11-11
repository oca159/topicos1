package org.slf4j.impl;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.ILoggerFactory;

public class AndroidLoggerFactory
  implements ILoggerFactory
{
  private final Map<String, AndroidLogger> loggerMap = new HashMap();

  public AndroidLogger getLogger(String paramString)
  {
    monitorenter;
    try
    {
      Object localObject4 = (AndroidLogger)this.loggerMap.get(paramString);
      AndroidLogger localAndroidLogger;
      if (localObject4 == null)
        localAndroidLogger = new AndroidLogger(paramString);
      try
      {
        this.loggerMap.put(paramString, localAndroidLogger);
        localObject4 = localAndroidLogger;
        monitorexit;
        return localObject4;
        label50: monitorexit;
        Object localObject1;
        throw localObject1;
      }
      finally
      {
      }
    }
    finally
    {
      break label50;
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.impl.AndroidLoggerFactory
 * JD-Core Version:    0.6.0
 */