package org.slf4j.impl;

import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.spi.MarkerFactoryBinder;

public class StaticMarkerBinder
  implements MarkerFactoryBinder
{
  public static final StaticMarkerBinder SINGLETON = new StaticMarkerBinder();
  private final IMarkerFactory markerFactory = new BasicMarkerFactory();

  public IMarkerFactory getMarkerFactory()
  {
    return this.markerFactory;
  }

  public String getMarkerFactoryClassStr()
  {
    return BasicMarkerFactory.class.getName();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.impl.StaticMarkerBinder
 * JD-Core Version:    0.6.0
 */