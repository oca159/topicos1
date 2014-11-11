package org.osmdroid.views.overlay;

import android.graphics.Point;
import android.graphics.drawable.Drawable;
import org.osmdroid.util.GeoPoint;

public class OverlayItem
{
  protected static final Point DEFAULT_MARKER_SIZE = new Point(26, 94);
  public static final int ITEM_STATE_FOCUSED_MASK = 4;
  public static final int ITEM_STATE_PRESSED_MASK = 1;
  public static final int ITEM_STATE_SELECTED_MASK = 2;
  public final String mDescription;
  public final GeoPoint mGeoPoint;
  protected HotspotPlace mHotspotPlace;
  protected Drawable mMarker;
  public final String mTitle;
  public final String mUid;

  public OverlayItem(String paramString1, String paramString2, String paramString3, GeoPoint paramGeoPoint)
  {
    this.mTitle = paramString2;
    this.mDescription = paramString3;
    this.mGeoPoint = paramGeoPoint;
    this.mUid = paramString1;
  }

  public OverlayItem(String paramString1, String paramString2, GeoPoint paramGeoPoint)
  {
    this(null, paramString1, paramString2, paramGeoPoint);
  }

  public static void setState(Drawable paramDrawable, int paramInt)
  {
    int[] arrayOfInt = new int[3];
    int i;
    if ((paramInt & 0x1) > 0)
    {
      i = 0 + 1;
      arrayOfInt[0] = 16842919;
    }
    while (true)
    {
      if ((paramInt & 0x2) > 0)
      {
        int j = i + 1;
        arrayOfInt[i] = 16842913;
        i = j;
      }
      if ((paramInt & 0x4) > 0)
      {
        (i + 1);
        arrayOfInt[i] = 16842908;
      }
      while (true)
      {
        paramDrawable.setState(arrayOfInt);
        return;
      }
      i = 0;
    }
  }

  public Drawable getDrawable()
  {
    return this.mMarker;
  }

  public int getHeight()
  {
    return this.mMarker.getIntrinsicHeight();
  }

  public Drawable getMarker(int paramInt)
  {
    if (this.mMarker == null)
      return null;
    setState(this.mMarker, paramInt);
    return this.mMarker;
  }

  public HotspotPlace getMarkerHotspot()
  {
    return this.mHotspotPlace;
  }

  public GeoPoint getPoint()
  {
    return this.mGeoPoint;
  }

  public String getSnippet()
  {
    return this.mDescription;
  }

  public String getTitle()
  {
    return this.mTitle;
  }

  public String getUid()
  {
    return this.mUid;
  }

  public int getWidth()
  {
    return this.mMarker.getIntrinsicWidth();
  }

  public void setMarker(Drawable paramDrawable)
  {
    this.mMarker = paramDrawable;
  }

  public void setMarkerHotspot(HotspotPlace paramHotspotPlace)
  {
    if (paramHotspotPlace == null)
      paramHotspotPlace = HotspotPlace.BOTTOM_CENTER;
    this.mHotspotPlace = paramHotspotPlace;
  }

  public static enum HotspotPlace
  {
    static
    {
      CENTER = new HotspotPlace("CENTER", 1);
      BOTTOM_CENTER = new HotspotPlace("BOTTOM_CENTER", 2);
      TOP_CENTER = new HotspotPlace("TOP_CENTER", 3);
      RIGHT_CENTER = new HotspotPlace("RIGHT_CENTER", 4);
      LEFT_CENTER = new HotspotPlace("LEFT_CENTER", 5);
      UPPER_RIGHT_CORNER = new HotspotPlace("UPPER_RIGHT_CORNER", 6);
      LOWER_RIGHT_CORNER = new HotspotPlace("LOWER_RIGHT_CORNER", 7);
      UPPER_LEFT_CORNER = new HotspotPlace("UPPER_LEFT_CORNER", 8);
      LOWER_LEFT_CORNER = new HotspotPlace("LOWER_LEFT_CORNER", 9);
      HotspotPlace[] arrayOfHotspotPlace = new HotspotPlace[10];
      arrayOfHotspotPlace[0] = NONE;
      arrayOfHotspotPlace[1] = CENTER;
      arrayOfHotspotPlace[2] = BOTTOM_CENTER;
      arrayOfHotspotPlace[3] = TOP_CENTER;
      arrayOfHotspotPlace[4] = RIGHT_CENTER;
      arrayOfHotspotPlace[5] = LEFT_CENTER;
      arrayOfHotspotPlace[6] = UPPER_RIGHT_CORNER;
      arrayOfHotspotPlace[7] = LOWER_RIGHT_CORNER;
      arrayOfHotspotPlace[8] = UPPER_LEFT_CORNER;
      arrayOfHotspotPlace[9] = LOWER_LEFT_CORNER;
      ENUM$VALUES = arrayOfHotspotPlace;
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.overlay.OverlayItem
 * JD-Core Version:    0.6.0
 */