package org.slf4j.spi;

import org.slf4j.Logger;
import org.slf4j.Marker;

public abstract interface LocationAwareLogger extends Logger
{
  public static final int DEBUG_INT = 10;
  public static final int ERROR_INT = 40;
  public static final int INFO_INT = 20;
  public static final int TRACE_INT = 0;
  public static final int WARN_INT = 30;

  public abstract void log(Marker paramMarker, String paramString1, int paramInt, String paramString2, Throwable paramThrowable);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.spi.LocationAwareLogger
 * JD-Core Version:    0.6.0
 */