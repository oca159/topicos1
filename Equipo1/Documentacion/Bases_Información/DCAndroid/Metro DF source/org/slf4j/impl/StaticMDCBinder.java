package org.slf4j.impl;

import org.slf4j.helpers.NOPMakerAdapter;
import org.slf4j.spi.MDCAdapter;

public class StaticMDCBinder
{
  public static final StaticMDCBinder SINGLETON = new StaticMDCBinder();

  public MDCAdapter getMDCA()
  {
    return new NOPMakerAdapter();
  }

  public String getMDCAdapterClassStr()
  {
    return NOPMakerAdapter.class.getName();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.impl.StaticMDCBinder
 * JD-Core Version:    0.6.0
 */