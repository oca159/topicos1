package org.osmdroid.views.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import java.util.ArrayList;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;

public class PathOverlay extends Overlay
{
  private final Rect mLineBounds = new Rect();
  protected Paint mPaint = new Paint();
  private final Path mPath = new Path();
  private ArrayList<Point> mPoints;
  private int mPointsPrecomputed;
  private final Point mTempPoint1 = new Point();
  private final Point mTempPoint2 = new Point();

  public PathOverlay(int paramInt, Context paramContext)
  {
    this(paramInt, new DefaultResourceProxyImpl(paramContext));
  }

  public PathOverlay(int paramInt, ResourceProxy paramResourceProxy)
  {
    super(paramResourceProxy);
    this.mPaint.setColor(paramInt);
    this.mPaint.setStrokeWidth(2.0F);
    this.mPaint.setStyle(Paint.Style.STROKE);
    clearPath();
  }

  public void addPoint(int paramInt1, int paramInt2)
  {
    this.mPoints.add(new Point(paramInt1, paramInt2));
  }

  public void addPoint(GeoPoint paramGeoPoint)
  {
    addPoint(paramGeoPoint.getLatitudeE6(), paramGeoPoint.getLongitudeE6());
  }

  public void clearPath()
  {
    this.mPoints = new ArrayList();
    this.mPointsPrecomputed = 0;
  }

  protected void draw(Canvas paramCanvas, MapView paramMapView, boolean paramBoolean)
  {
    if (paramBoolean);
    do
      return;
    while (this.mPoints.size() < 2);
    MapView.Projection localProjection = paramMapView.getProjection();
    int i = this.mPoints.size();
    Point localPoint2;
    Rect localRect;
    Object localObject;
    int j;
    while (true)
    {
      if (this.mPointsPrecomputed >= i)
      {
        localPoint2 = null;
        localRect = localProjection.fromPixelsToProjected(localProjection.getScreenRect());
        this.mPath.rewind();
        localObject = (Point)this.mPoints.get(i - 1);
        this.mLineBounds.set(((Point)localObject).x, ((Point)localObject).y, ((Point)localObject).x, ((Point)localObject).y);
        j = i - 2;
        if (j >= 0)
          break;
        paramCanvas.drawPath(this.mPath, this.mPaint);
        return;
      }
      Point localPoint1 = (Point)this.mPoints.get(this.mPointsPrecomputed);
      localProjection.toMapPixelsProjected(localPoint1.x, localPoint1.y, localPoint1);
      this.mPointsPrecomputed = (1 + this.mPointsPrecomputed);
    }
    Point localPoint3 = (Point)this.mPoints.get(j);
    this.mLineBounds.union(localPoint3.x, localPoint3.y);
    if (!Rect.intersects(localRect, this.mLineBounds))
    {
      localObject = localPoint3;
      localPoint2 = null;
    }
    while (true)
    {
      j--;
      break;
      if (localPoint2 == null)
      {
        localPoint2 = localProjection.toMapPixelsTranslated((Point)localObject, this.mTempPoint1);
        this.mPath.moveTo(localPoint2.x, localPoint2.y);
      }
      Point localPoint4 = localProjection.toMapPixelsTranslated(localPoint3, this.mTempPoint2);
      if (Math.abs(localPoint4.x - localPoint2.x) + Math.abs(localPoint4.y - localPoint2.y) <= 1)
        continue;
      this.mPath.lineTo(localPoint4.x, localPoint4.y);
      localObject = localPoint3;
      localPoint2.x = localPoint4.x;
      localPoint2.y = localPoint4.y;
      this.mLineBounds.set(((Point)localObject).x, ((Point)localObject).y, ((Point)localObject).x, ((Point)localObject).y);
    }
  }

  public int getNumberOfPoints()
  {
    return this.mPoints.size();
  }

  public Paint getPaint()
  {
    return this.mPaint;
  }

  public void setAlpha(int paramInt)
  {
    this.mPaint.setAlpha(paramInt);
  }

  public void setColor(int paramInt)
  {
    this.mPaint.setColor(paramInt);
  }

  public void setPaint(Paint paramPaint)
  {
    if (paramPaint == null)
      throw new IllegalArgumentException("pPaint argument cannot be null");
    this.mPaint = paramPaint;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.overlay.PathOverlay
 * JD-Core Version:    0.6.0
 */