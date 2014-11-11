package org.osmdroid.util;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import org.osmdroid.views.util.constants.MapViewConstants;

public class BoundingBoxE6
  implements Parcelable, Serializable, MapViewConstants
{
  public static final Parcelable.Creator<BoundingBoxE6> CREATOR = new Parcelable.Creator()
  {
    public BoundingBoxE6 createFromParcel(Parcel paramParcel)
    {
      return BoundingBoxE6.access$0(paramParcel);
    }

    public BoundingBoxE6[] newArray(int paramInt)
    {
      return new BoundingBoxE6[paramInt];
    }
  };
  static final long serialVersionUID = 2L;
  protected final int mLatNorthE6;
  protected final int mLatSouthE6;
  protected final int mLonEastE6;
  protected final int mLonWestE6;

  public BoundingBoxE6(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this.mLatNorthE6 = (int)(paramDouble1 * 1000000.0D);
    this.mLonEastE6 = (int)(paramDouble2 * 1000000.0D);
    this.mLatSouthE6 = (int)(paramDouble3 * 1000000.0D);
    this.mLonWestE6 = (int)(paramDouble4 * 1000000.0D);
  }

  public BoundingBoxE6(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mLatNorthE6 = paramInt1;
    this.mLonEastE6 = paramInt2;
    this.mLatSouthE6 = paramInt3;
    this.mLonWestE6 = paramInt4;
  }

  public static BoundingBoxE6 fromGeoPoints(ArrayList<? extends GeoPoint> paramArrayList)
  {
    int i = 2147483647;
    int j = 2147483647;
    int k = -2147483648;
    int m = -2147483648;
    Iterator localIterator = paramArrayList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return new BoundingBoxE6(i, j, k, m);
      GeoPoint localGeoPoint = (GeoPoint)localIterator.next();
      int n = localGeoPoint.getLatitudeE6();
      int i1 = localGeoPoint.getLongitudeE6();
      i = Math.min(i, n);
      j = Math.min(j, i1);
      k = Math.max(k, n);
      m = Math.max(m, i1);
    }
  }

  private static BoundingBoxE6 readFromParcel(Parcel paramParcel)
  {
    return new BoundingBoxE6(paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt());
  }

  public GeoPoint bringToBoundingBox(int paramInt1, int paramInt2)
  {
    return new GeoPoint(Math.max(this.mLatSouthE6, Math.min(this.mLatNorthE6, paramInt1)), Math.max(this.mLonWestE6, Math.min(this.mLonEastE6, paramInt2)));
  }

  public boolean contains(int paramInt1, int paramInt2)
  {
    return (paramInt1 < this.mLatNorthE6) && (paramInt1 > this.mLatSouthE6) && (paramInt2 < this.mLonEastE6) && (paramInt2 > this.mLonWestE6);
  }

  public boolean contains(GeoPoint paramGeoPoint)
  {
    return contains(paramGeoPoint.getLatitudeE6(), paramGeoPoint.getLongitudeE6());
  }

  public int describeContents()
  {
    return 0;
  }

  public GeoPoint getCenter()
  {
    return new GeoPoint((this.mLatNorthE6 + this.mLatSouthE6) / 2, (this.mLonEastE6 + this.mLonWestE6) / 2);
  }

  public int getDiagonalLengthInMeters()
  {
    return new GeoPoint(this.mLatNorthE6, this.mLonWestE6).distanceTo(new GeoPoint(this.mLatSouthE6, this.mLonEastE6));
  }

  public GeoPoint getGeoPointOfRelativePositionWithExactGudermannInterpolation(float paramFloat1, float paramFloat2)
  {
    double d1 = MyMath.gudermannInverse(this.mLatNorthE6 / 1000000.0D);
    double d2 = MyMath.gudermannInverse(this.mLatSouthE6 / 1000000.0D);
    int i = (int)(1000000.0D * MyMath.gudermann(d2 + (1.0F - paramFloat2) * (d1 - d2)));
    int j = (int)(this.mLonWestE6 + paramFloat1 * getLongitudeSpanE6());
    if (i <= 90500000)
    {
      label72: if (i < -90500000)
        break label115;
      label79: if (j > 180000000)
        break label125;
    }
    while (true)
    {
      if (j >= -180000000)
      {
        return new GeoPoint(i, j);
        i -= 90500000;
        break;
        label115: i += 90500000;
        break label72;
        label125: j -= 180000000;
        break label79;
      }
      j += 180000000;
    }
  }

  public GeoPoint getGeoPointOfRelativePositionWithLinearInterpolation(float paramFloat1, float paramFloat2)
  {
    int i = (int)(this.mLatNorthE6 - paramFloat2 * getLatitudeSpanE6());
    int j = (int)(this.mLonWestE6 + paramFloat1 * getLongitudeSpanE6());
    if (i <= 90500000)
    {
      label37: if (i < -90500000)
        break label76;
      label43: if (j > 180000000)
        break label84;
    }
    while (true)
    {
      if (j >= -180000000)
      {
        return new GeoPoint(i, j);
        i -= 90500000;
        break;
        label76: i += 90500000;
        break label37;
        label84: j -= 180000000;
        break label43;
      }
      j += 180000000;
    }
  }

  public int getLatNorthE6()
  {
    return this.mLatNorthE6;
  }

  public int getLatSouthE6()
  {
    return this.mLatSouthE6;
  }

  public int getLatitudeSpanE6()
  {
    return Math.abs(this.mLatNorthE6 - this.mLatSouthE6);
  }

  public int getLonEastE6()
  {
    return this.mLonEastE6;
  }

  public int getLonWestE6()
  {
    return this.mLonWestE6;
  }

  public int getLongitudeSpanE6()
  {
    return Math.abs(this.mLonEastE6 - this.mLonWestE6);
  }

  public PointF getRelativePositionOfGeoPointInBoundingBoxWithExactGudermannInterpolation(int paramInt1, int paramInt2, PointF paramPointF)
  {
    if (paramPointF != null);
    for (PointF localPointF = paramPointF; ; localPointF = new PointF())
    {
      float f = (float)((MyMath.gudermannInverse(this.mLatNorthE6 / 1000000.0D) - MyMath.gudermannInverse(paramInt1 / 1000000.0D)) / (MyMath.gudermannInverse(this.mLatNorthE6 / 1000000.0D) - MyMath.gudermannInverse(this.mLatSouthE6 / 1000000.0D)));
      localPointF.set(1.0F - (this.mLonEastE6 - paramInt2) / getLongitudeSpanE6(), f);
      return localPointF;
    }
  }

  public PointF getRelativePositionOfGeoPointInBoundingBoxWithLinearInterpolation(int paramInt1, int paramInt2, PointF paramPointF)
  {
    if (paramPointF != null);
    for (PointF localPointF = paramPointF; ; localPointF = new PointF())
    {
      float f = (this.mLatNorthE6 - paramInt1) / getLatitudeSpanE6();
      localPointF.set(1.0F - (this.mLonEastE6 - paramInt2) / getLongitudeSpanE6(), f);
      return localPointF;
    }
  }

  public BoundingBoxE6 increaseByScale(float paramFloat)
  {
    GeoPoint localGeoPoint = getCenter();
    int i = (int)(paramFloat * getLatitudeSpanE6() / 2.0F);
    int j = (int)(paramFloat * getLongitudeSpanE6() / 2.0F);
    return new BoundingBoxE6(i + localGeoPoint.getLatitudeE6(), j + localGeoPoint.getLongitudeE6(), localGeoPoint.getLatitudeE6() - i, localGeoPoint.getLongitudeE6() - j);
  }

  public String toString()
  {
    return "N:" + this.mLatNorthE6 + "; E:" + this.mLonEastE6 + "; S:" + this.mLatSouthE6 + "; W:" + this.mLonWestE6;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.mLatNorthE6);
    paramParcel.writeInt(this.mLonEastE6);
    paramParcel.writeInt(this.mLatSouthE6);
    paramParcel.writeInt(this.mLonWestE6);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.util.BoundingBoxE6
 * JD-Core Version:    0.6.0
 */