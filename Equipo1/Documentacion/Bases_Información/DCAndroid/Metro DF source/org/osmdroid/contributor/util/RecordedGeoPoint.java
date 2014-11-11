package org.osmdroid.contributor.util;

import org.osmdroid.contributor.util.constants.OpenStreetMapContributorConstants;
import org.osmdroid.util.GeoPoint;

public class RecordedGeoPoint extends GeoPoint
  implements OpenStreetMapContributorConstants
{
  private static final long serialVersionUID = 7304941424576720318L;
  protected final int mNumSatellites;
  protected final long mTimeStamp;

  public RecordedGeoPoint(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, System.currentTimeMillis(), -2147483648);
  }

  public RecordedGeoPoint(int paramInt1, int paramInt2, long paramLong, int paramInt3)
  {
    super(paramInt1, paramInt2);
    this.mTimeStamp = paramLong;
    this.mNumSatellites = paramInt3;
  }

  public double getLatitudeAsDouble()
  {
    return getLatitudeE6() / 1000000.0D;
  }

  public double getLongitudeAsDouble()
  {
    return getLongitudeE6() / 1000000.0D;
  }

  public int getNumSatellites()
  {
    return this.mNumSatellites;
  }

  public long getTimeStamp()
  {
    return this.mTimeStamp;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.contributor.util.RecordedGeoPoint
 * JD-Core Version:    0.6.0
 */