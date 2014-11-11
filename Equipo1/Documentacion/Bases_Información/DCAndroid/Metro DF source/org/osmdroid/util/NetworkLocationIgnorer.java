package org.osmdroid.util;

import org.osmdroid.util.constants.UtilConstants;

public class NetworkLocationIgnorer
  implements UtilConstants
{
  private long mLastGps = 0L;

  public boolean shouldIgnore(String paramString, long paramLong)
  {
    if ("gps".equals(paramString))
      this.mLastGps = paramLong;
    do
      return false;
    while (paramLong >= 20000L + this.mLastGps);
    return true;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.util.NetworkLocationIgnorer
 * JD-Core Version:    0.6.0
 */