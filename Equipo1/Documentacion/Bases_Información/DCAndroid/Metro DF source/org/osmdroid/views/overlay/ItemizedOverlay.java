package org.osmdroid.views.overlay;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import org.osmdroid.ResourceProxy;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;

public abstract class ItemizedOverlay<Item extends OverlayItem> extends Overlay
  implements Overlay.Snappable
{
  private final Point mCurScreenCoords = new Point();
  protected final Drawable mDefaultMarker;
  protected boolean mDrawFocusedItem = true;
  private Item mFocusedItem;
  private final ArrayList<Item> mInternalItemList;
  private final Rect mRect = new Rect();

  public ItemizedOverlay(Drawable paramDrawable, ResourceProxy paramResourceProxy)
  {
    super(paramResourceProxy);
    if (paramDrawable == null)
      throw new IllegalArgumentException("You must pass a default marker to ItemizedOverlay.");
    this.mDefaultMarker = paramDrawable;
    this.mInternalItemList = new ArrayList();
  }

  private Drawable getDefaultMarker(int paramInt)
  {
    OverlayItem.setState(this.mDefaultMarker, paramInt);
    return this.mDefaultMarker;
  }

  protected Drawable boundToHotspot(Drawable paramDrawable, OverlayItem.HotspotPlace paramHotspotPlace)
  {
    monitorenter;
    while (true)
    {
      int i;
      int j;
      try
      {
        i = (int)(paramDrawable.getIntrinsicWidth() * this.mScale);
        j = (int)(paramDrawable.getIntrinsicHeight() * this.mScale);
        this.mRect.set(0, 0, i + 0, j + 0);
        if (paramHotspotPlace != null)
          continue;
        paramHotspotPlace = OverlayItem.HotspotPlace.BOTTOM_CENTER;
        switch ($SWITCH_TABLE$org$osmdroid$views$overlay$OverlayItem$HotspotPlace()[paramHotspotPlace.ordinal()])
        {
        default:
          paramDrawable.setBounds(this.mRect);
          return paramDrawable;
        case 2:
          this.mRect.offset(-i / 2, -j / 2);
          continue;
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        case 10:
        }
      }
      finally
      {
        monitorexit;
      }
      this.mRect.offset(-i / 2, -j);
      continue;
      this.mRect.offset(-i / 2, 0);
      continue;
      this.mRect.offset(-i, -j / 2);
      continue;
      this.mRect.offset(0, -j / 2);
      continue;
      this.mRect.offset(-i, 0);
      continue;
      this.mRect.offset(-i, -j);
      continue;
      this.mRect.offset(0, 0);
      continue;
      this.mRect.offset(0, j);
    }
  }

  protected abstract Item createItem(int paramInt);

  public void draw(Canvas paramCanvas, MapView paramMapView, boolean paramBoolean)
  {
    if (paramBoolean);
    while (true)
    {
      return;
      MapView.Projection localProjection = paramMapView.getProjection();
      for (int i = -1 + this.mInternalItemList.size(); i >= 0; i--)
      {
        OverlayItem localOverlayItem = getItem(i);
        localProjection.toMapPixels(localOverlayItem.mGeoPoint, this.mCurScreenCoords);
        onDrawItem(paramCanvas, localOverlayItem, this.mCurScreenCoords);
      }
    }
  }

  public Item getFocus()
  {
    return this.mFocusedItem;
  }

  public final Item getItem(int paramInt)
  {
    return (OverlayItem)this.mInternalItemList.get(paramInt);
  }

  protected boolean hitTest(Item paramItem, Drawable paramDrawable, int paramInt1, int paramInt2)
  {
    return paramDrawable.getBounds().contains(paramInt1, paramInt2);
  }

  protected void onDrawItem(Canvas paramCanvas, Item paramItem, Point paramPoint)
  {
    int i;
    if ((this.mDrawFocusedItem) && (this.mFocusedItem == paramItem))
    {
      i = 4;
      if (paramItem.getMarker(i) != null)
        break label68;
    }
    label68: for (Drawable localDrawable = getDefaultMarker(i); ; localDrawable = paramItem.getMarker(i))
    {
      boundToHotspot(localDrawable, paramItem.getMarkerHotspot());
      Overlay.drawAt(paramCanvas, localDrawable, paramPoint.x, paramPoint.y, false);
      return;
      i = 0;
      break;
    }
  }

  protected final void populate()
  {
    int i = size();
    this.mInternalItemList.clear();
    this.mInternalItemList.ensureCapacity(i);
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      this.mInternalItemList.add(createItem(j));
    }
  }

  public void setDrawFocusedItem(boolean paramBoolean)
  {
    this.mDrawFocusedItem = paramBoolean;
  }

  public void setFocus(Item paramItem)
  {
    this.mFocusedItem = paramItem;
  }

  public abstract int size();
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.overlay.ItemizedOverlay
 * JD-Core Version:    0.6.0
 */