package org.slf4j.spi;

import org.slf4j.IMarkerFactory;

public abstract interface MarkerFactoryBinder
{
  public abstract IMarkerFactory getMarkerFactory();

  public abstract String getMarkerFactoryClassStr();
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.spi.MarkerFactoryBinder
 * JD-Core Version:    0.6.0
 */