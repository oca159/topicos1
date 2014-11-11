package org.slf4j.spi;

import org.slf4j.ILoggerFactory;

public abstract interface LoggerFactoryBinder
{
  public abstract ILoggerFactory getLoggerFactory();

  public abstract String getLoggerFactoryClassStr();
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.spi.LoggerFactoryBinder
 * JD-Core Version:    0.6.0
 */