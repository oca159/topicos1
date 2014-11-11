package org.osmdroid.views.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.MotionEvent;
import microsoft.mappoint.TileSystem;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;

public class MinimapOverlay extends TilesOverlay
{
  private int mHeight = 100;
  private final Rect mIntersectionRect = new Rect();
  private final Rect mMiniMapCanvasRect = new Rect();
  private int mPadding = 10;
  private final Paint mPaint;
  private final Rect mTileArea = new Rect();
  private final Rect mViewportRect = new Rect();
  private int mWidth = 100;
  private int mWorldSize_2;
  private int mZoomDifference;

  public MinimapOverlay(Context paramContext, Handler paramHandler)
  {
    this(paramContext, paramHandler, new MapTileProviderBasic(paramContext));
  }

  public MinimapOverlay(Context paramContext, Handler paramHandler, MapTileProviderBase paramMapTileProviderBase)
  {
    this(paramContext, paramHandler, paramMapTileProviderBase, 3);
  }

  public MinimapOverlay(Context paramContext, Handler paramHandler, MapTileProviderBase paramMapTileProviderBase, int paramInt)
  {
    super(paramMapTileProviderBase, paramContext);
    setZoomDifference(paramInt);
    this.mTileProvider.setTileRequestCompleteHandler(paramHandler);
    setLoadingLineColor(getLoadingBackgroundColor());
    this.mPaint = new Paint();
    this.mPaint.setColor(-7829368);
    this.mPaint.setStyle(Paint.Style.FILL);
    this.mPaint.setStrokeWidth(2.0F);
  }

  protected void draw(Canvas paramCanvas, MapView paramMapView, boolean paramBoolean)
  {
    if (paramBoolean);
    do
      return;
    while (paramMapView.isAnimating());
    MapView.Projection localProjection = paramMapView.getProjection();
    int i = localProjection.getZoomLevel();
    this.mWorldSize_2 = (TileSystem.MapSize(i) / 2);
    this.mViewportRect.set(localProjection.getScreenRect());
    this.mViewportRect.offset(this.mWorldSize_2, this.mWorldSize_2);
    this.mTileArea.set(this.mViewportRect);
    int j = getZoomDifference();
    if (i - getZoomDifference() < this.mTileProvider.getMinimumZoomLevel())
      j += i - getZoomDifference() - this.mTileProvider.getMinimumZoomLevel();
    this.mTileArea.set(this.mTileArea.left >> j, this.mTileArea.top >> j, this.mTileArea.right >> j, this.mTileArea.bottom >> j);
    this.mTileArea.set(this.mTileArea.centerX() - getWidth() / 2, this.mTileArea.centerY() - getHeight() / 2, this.mTileArea.centerX() + getWidth() / 2, this.mTileArea.centerY() + getHeight() / 2);
    this.mMiniMapCanvasRect.set(this.mViewportRect.right - getPadding() - getWidth(), this.mViewportRect.bottom - getPadding() - getHeight(), this.mViewportRect.right - getPadding(), this.mViewportRect.bottom - getPadding());
    this.mMiniMapCanvasRect.offset(-this.mWorldSize_2, -this.mWorldSize_2);
    paramCanvas.drawRect(-2 + this.mMiniMapCanvasRect.left, -2 + this.mMiniMapCanvasRect.top, 2 + this.mMiniMapCanvasRect.right, 2 + this.mMiniMapCanvasRect.bottom, this.mPaint);
    super.drawTiles(paramCanvas, localProjection.getZoomLevel() - j, localProjection.getTileSizePixels(), this.mTileArea);
  }

  public int getHeight()
  {
    return this.mHeight;
  }

  public int getPadding()
  {
    return this.mPadding;
  }

  public int getWidth()
  {
    return this.mWidth;
  }

  public int getZoomDifference()
  {
    return this.mZoomDifference;
  }

  public boolean onDoubleTap(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    return this.mMiniMapCanvasRect.contains((int)paramMotionEvent.getX() + this.mViewportRect.left - this.mWorldSize_2, (int)paramMotionEvent.getY() + this.mViewportRect.top - this.mWorldSize_2);
  }

  public boolean onLongPress(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    return this.mMiniMapCanvasRect.contains((int)paramMotionEvent.getX() + this.mViewportRect.left - this.mWorldSize_2, (int)paramMotionEvent.getY() + this.mViewportRect.top - this.mWorldSize_2);
  }

  public boolean onSingleTapUp(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    return this.mMiniMapCanvasRect.contains((int)paramMotionEvent.getX() + this.mViewportRect.left - this.mWorldSize_2, (int)paramMotionEvent.getY() + this.mViewportRect.top - this.mWorldSize_2);
  }

  protected void onTileReadyToDraw(Canvas paramCanvas, Drawable paramDrawable, Rect paramRect)
  {
    int i = paramRect.left - this.mTileArea.left + this.mMiniMapCanvasRect.left;
    int j = paramRect.top - this.mTileArea.top + this.mMiniMapCanvasRect.top;
    paramDrawable.setBounds(i, j, i + paramRect.width(), j + paramRect.height());
    Rect localRect = paramCanvas.getClipBounds();
    if (this.mIntersectionRect.setIntersect(localRect, this.mMiniMapCanvasRect))
    {
      paramCanvas.clipRect(this.mIntersectionRect);
      paramDrawable.draw(paramCanvas);
      paramCanvas.clipRect(localRect);
    }
  }

  public void setHeight(int paramInt)
  {
    this.mHeight = paramInt;
  }

  public void setPadding(int paramInt)
  {
    this.mPadding = paramInt;
  }

  public void setTileSource(ITileSource paramITileSource)
  {
    this.mTileProvider.setTileSource(paramITileSource);
  }

  public void setWidth(int paramInt)
  {
    this.mWidth = paramInt;
  }

  public void setZoomDifference(int paramInt)
  {
    this.mZoomDifference = paramInt;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.overlay.MinimapOverlay
 * JD-Core Version:    0.6.0
 */