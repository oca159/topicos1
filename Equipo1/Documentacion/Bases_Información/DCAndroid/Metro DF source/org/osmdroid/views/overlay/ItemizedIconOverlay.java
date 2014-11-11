package org.osmdroid.views.overlay;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import java.util.List;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.ResourceProxy.bitmap;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;

public class ItemizedIconOverlay<Item extends OverlayItem> extends ItemizedOverlay<Item>
{
  private int mDrawnItemsLimit = 2147483647;
  protected final List<Item> mItemList;
  private final Point mItemPoint = new Point();
  protected OnItemGestureListener<Item> mOnItemGestureListener;
  private final Point mTouchScreenPoint = new Point();

  public ItemizedIconOverlay(Context paramContext, List<Item> paramList, OnItemGestureListener<Item> paramOnItemGestureListener)
  {
    this(paramList, new DefaultResourceProxyImpl(paramContext).getDrawable(ResourceProxy.bitmap.marker_default), paramOnItemGestureListener, new DefaultResourceProxyImpl(paramContext));
  }

  public ItemizedIconOverlay(List<Item> paramList, Drawable paramDrawable, OnItemGestureListener<Item> paramOnItemGestureListener, ResourceProxy paramResourceProxy)
  {
    super(paramDrawable, paramResourceProxy);
    this.mItemList = paramList;
    this.mOnItemGestureListener = paramOnItemGestureListener;
    populate();
  }

  public ItemizedIconOverlay(List<Item> paramList, OnItemGestureListener<Item> paramOnItemGestureListener, ResourceProxy paramResourceProxy)
  {
    this(paramList, paramResourceProxy.getDrawable(ResourceProxy.bitmap.marker_default), paramOnItemGestureListener, paramResourceProxy);
  }

  private boolean activateSelectedItems(MotionEvent paramMotionEvent, MapView paramMapView, ActiveItem paramActiveItem)
  {
    MapView.Projection localProjection = paramMapView.getProjection();
    localProjection.fromMapPixels((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY(), this.mTouchScreenPoint);
    for (int i = 0; ; i++)
    {
      if (i >= this.mItemList.size())
        return false;
      OverlayItem localOverlayItem = getItem(i);
      if (localOverlayItem.getMarker(0) == null);
      for (Drawable localDrawable = this.mDefaultMarker; ; localDrawable = localOverlayItem.getMarker(0))
      {
        localProjection.toPixels(localOverlayItem.getPoint(), this.mItemPoint);
        if ((!hitTest(localOverlayItem, localDrawable, this.mTouchScreenPoint.x - this.mItemPoint.x, this.mTouchScreenPoint.y - this.mItemPoint.y)) || (!paramActiveItem.run(i)))
          break;
        return true;
      }
    }
  }

  public void addItem(int paramInt, Item paramItem)
  {
    this.mItemList.add(paramInt, paramItem);
  }

  public boolean addItem(Item paramItem)
  {
    boolean bool = this.mItemList.add(paramItem);
    populate();
    return bool;
  }

  protected Item createItem(int paramInt)
  {
    return (OverlayItem)this.mItemList.get(paramInt);
  }

  public int getDrawnItemsLimit()
  {
    return this.mDrawnItemsLimit;
  }

  public boolean onLongPress(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    if (activateSelectedItems(paramMotionEvent, paramMapView, new ActiveItem()
    {
      public boolean run(int paramInt)
      {
        if (ItemizedIconOverlay.this.mOnItemGestureListener == null)
          return false;
        return ItemizedIconOverlay.this.onLongPressHelper(paramInt, ItemizedIconOverlay.this.getItem(paramInt));
      }
    }))
      return true;
    return super.onLongPress(paramMotionEvent, paramMapView);
  }

  protected boolean onLongPressHelper(int paramInt, Item paramItem)
  {
    return this.mOnItemGestureListener.onItemLongPress(paramInt, paramItem);
  }

  public boolean onSingleTapUp(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    if (activateSelectedItems(paramMotionEvent, paramMapView, new ActiveItem(paramMapView)
    {
      public boolean run(int paramInt)
      {
        ItemizedIconOverlay localItemizedIconOverlay = ItemizedIconOverlay.this;
        if (localItemizedIconOverlay.mOnItemGestureListener == null)
          return false;
        return ItemizedIconOverlay.this.onSingleTapUpHelper(paramInt, (OverlayItem)localItemizedIconOverlay.mItemList.get(paramInt), this.val$mapView);
      }
    }))
      return true;
    return super.onSingleTapUp(paramMotionEvent, paramMapView);
  }

  protected boolean onSingleTapUpHelper(int paramInt, Item paramItem, MapView paramMapView)
  {
    return this.mOnItemGestureListener.onItemSingleTapUp(paramInt, paramItem);
  }

  public boolean onSnapToItem(int paramInt1, int paramInt2, Point paramPoint, MapView paramMapView)
  {
    return false;
  }

  public Item removeItem(int paramInt)
  {
    OverlayItem localOverlayItem = (OverlayItem)this.mItemList.remove(paramInt);
    populate();
    return localOverlayItem;
  }

  public boolean removeItem(Item paramItem)
  {
    boolean bool = this.mItemList.remove(paramItem);
    populate();
    return bool;
  }

  public void setDrawnItemsLimit(int paramInt)
  {
    this.mDrawnItemsLimit = paramInt;
  }

  public int size()
  {
    return Math.min(this.mItemList.size(), this.mDrawnItemsLimit);
  }

  public static abstract interface ActiveItem
  {
    public abstract boolean run(int paramInt);
  }

  public static abstract interface OnItemGestureListener<T>
  {
    public abstract boolean onItemLongPress(int paramInt, T paramT);

    public abstract boolean onItemSingleTapUp(int paramInt, T paramT);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.overlay.ItemizedIconOverlay
 * JD-Core Version:    0.6.0
 */