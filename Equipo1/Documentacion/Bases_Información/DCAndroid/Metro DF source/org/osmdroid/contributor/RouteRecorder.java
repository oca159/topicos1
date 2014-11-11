package org.osmdroid.contributor;

import android.location.Location;
import java.util.ArrayList;
import org.osmdroid.contributor.util.RecordedGeoPoint;
import org.osmdroid.util.GeoPoint;

public class RouteRecorder
{
  protected final ArrayList<RecordedGeoPoint> mRecords = new ArrayList();

  public void add(Location paramLocation, int paramInt)
  {
    this.mRecords.add(new RecordedGeoPoint((int)(1000000.0D * paramLocation.getLatitude()), (int)(1000000.0D * paramLocation.getLongitude()), System.currentTimeMillis(), paramInt));
  }

  public void add(GeoPoint paramGeoPoint, int paramInt)
  {
    this.mRecords.add(new RecordedGeoPoint(paramGeoPoint.getLatitudeE6(), paramGeoPoint.getLongitudeE6(), System.currentTimeMillis(), paramInt));
  }

  public ArrayList<RecordedGeoPoint> getRecordedGeoPoints()
  {
    return this.mRecords;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.contributor.RouteRecorder
 * JD-Core Version:    0.6.0
 */