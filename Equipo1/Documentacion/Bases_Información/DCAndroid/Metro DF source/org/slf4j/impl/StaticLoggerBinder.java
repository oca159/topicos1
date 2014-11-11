package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

public class StaticLoggerBinder
  implements LoggerFactoryBinder
{
  public static String REQUESTED_API_VERSION;
  private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
  private static final String loggerFactoryClassStr;
  private final ILoggerFactory loggerFactory = new AndroidLoggerFactory();

  static
  {
    REQUESTED_API_VERSION = "1.5.8";
    loggerFactoryClassStr = AndroidLoggerFactory.class.getName();
  }

  public static final StaticLoggerBinder getSingleton()
  {
    return SINGLETON;
  }

  public ILoggerFactory getLoggerFactory()
  {
    return this.loggerFactory;
  }

  public String getLoggerFactoryClassStr()
  {
    return loggerFactoryClassStr;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.impl.StaticLoggerBinder
 * JD-Core Version:    0.6.0
 */