package org.osmdroid.contributor.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import org.osmdroid.contributor.util.constants.OpenStreetMapContributorConstants;

public class RecordedRouteGPXFormatter
  implements OpenStreetMapContributorConstants
{
  private static final String GPX_TAG = "<gpx version=\"1.1\" creator=\"%s\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.topografix.com/GPX/1/1\" xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\">";
  private static final String GPX_TAG_CLOSE = "</gpx>";
  private static final String GPX_TAG_TIME = "<time>%s</time>";
  private static final String GPX_TAG_TRACK = "<trk>";
  private static final String GPX_TAG_TRACK_CLOSE = "</trk>";
  private static final String GPX_TAG_TRACK_NAME = "<name>%s</name>";
  private static final String GPX_TAG_TRACK_SEGMENT = "<trkseg>";
  private static final String GPX_TAG_TRACK_SEGMENT_CLOSE = "</trkseg>";
  public static final String GPX_TAG_TRACK_SEGMENT_POINT = "<trkpt lat=\"%f\" lon=\"%f\">";
  public static final String GPX_TAG_TRACK_SEGMENT_POINT_CLOSE = "</trkpt>";
  public static final String GPX_TAG_TRACK_SEGMENT_POINT_ELE = "<ele>%d</ele>";
  public static final String GPX_TAG_TRACK_SEGMENT_POINT_SAT = "<sat>%d</sat>";
  public static final String GPX_TAG_TRACK_SEGMENT_POINT_TIME = "<time>%s</time>";
  private static final String GPX_VERSION = "1.1";
  private static final String XML_VERSION = "<?xml version=\"1.0\"?>";
  private static final SimpleDateFormat formatterCompleteDateTime = new SimpleDateFormat("yyyyMMdd'_'HHmmss");

  public static String create(List<RecordedGeoPoint> paramList)
    throws IllegalArgumentException
  {
    if (paramList == null)
      throw new IllegalArgumentException("Records may not be null.");
    if (paramList.size() == 0)
      throw new IllegalArgumentException("Records size == 0");
    StringBuilder localStringBuilder = new StringBuilder();
    Formatter localFormatter = new Formatter(localStringBuilder);
    localStringBuilder.append("<?xml version=\"1.0\"?>");
    localFormatter.format("<gpx version=\"1.1\" creator=\"%s\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.topografix.com/GPX/1/1\" xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\">", new Object[] { "AndNav - http://www.andnav.org - Android Navigation System" });
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Util.convertTimestampToUTCString(System.currentTimeMillis());
    localFormatter.format("<time>%s</time>", arrayOfObject1);
    localStringBuilder.append("<trk>");
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = ("PUT_YOUR_USERNAME_HERE--" + formatterCompleteDateTime.format(Long.valueOf(new Date(((RecordedGeoPoint)paramList.get(0)).getTimeStamp()).getTime())) + "-" + formatterCompleteDateTime.format(Long.valueOf(new Date(((RecordedGeoPoint)paramList.get(-1 + paramList.size())).getTimeStamp()).getTime())));
    localFormatter.format("<name>%s</name>", arrayOfObject2);
    localStringBuilder.append("<trkseg>");
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localStringBuilder.append("</trkseg>").append("</trk>").append("</gpx>");
        return localStringBuilder.toString();
      }
      RecordedGeoPoint localRecordedGeoPoint = (RecordedGeoPoint)localIterator.next();
      Object[] arrayOfObject3 = new Object[2];
      arrayOfObject3[0] = Double.valueOf(localRecordedGeoPoint.getLatitudeAsDouble());
      arrayOfObject3[1] = Double.valueOf(localRecordedGeoPoint.getLongitudeAsDouble());
      localFormatter.format("<trkpt lat=\"%f\" lon=\"%f\">", arrayOfObject3);
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = Util.convertTimestampToUTCString(localRecordedGeoPoint.getTimeStamp());
      localFormatter.format("<time>%s</time>", arrayOfObject4);
      if (localRecordedGeoPoint.mNumSatellites != -2147483648)
      {
        Object[] arrayOfObject5 = new Object[1];
        arrayOfObject5[0] = Integer.valueOf(localRecordedGeoPoint.mNumSatellites);
        localFormatter.format("<sat>%d</sat>", arrayOfObject5);
      }
      localStringBuilder.append("</trkpt>");
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.contributor.util.RecordedRouteGPXFormatter
 * JD-Core Version:    0.6.0
 */