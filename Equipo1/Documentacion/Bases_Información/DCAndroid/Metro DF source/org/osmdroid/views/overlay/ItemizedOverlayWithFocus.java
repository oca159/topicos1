package org.osmdroid.views.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import java.util.List;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.ResourceProxy.bitmap;
import org.osmdroid.ResourceProxy.string;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;

public class ItemizedOverlayWithFocus<Item extends OverlayItem> extends ItemizedIconOverlay<Item>
{
  protected static final int DEFAULTMARKER_BACKGROUNDCOLOR = 0;
  public static final int DESCRIPTION_BOX_CORNERWIDTH = 3;
  public static final int DESCRIPTION_BOX_PADDING = 3;
  public static final int DESCRIPTION_LINE_HEIGHT = 12;
  protected static final int DESCRIPTION_MAXWIDTH = 200;
  public static final int DESCRIPTION_TITLE_EXTRA_LINE_HEIGHT = 2;
  private final String UNKNOWN = this.mResourceProxy.getString(ResourceProxy.string.unknown);
  protected final Paint mDescriptionPaint;
  protected boolean mFocusItemsOnTap;
  protected int mFocusedItemIndex;
  private final Point mFocusedScreenCoords = new Point();
  protected final Paint mMarkerBackgroundPaint;
  protected final int mMarkerFocusedBackgroundColor;
  protected Drawable mMarkerFocusedBase;
  private final Rect mRect = new Rect();
  protected final Paint mTitlePaint;

  public ItemizedOverlayWithFocus(Context paramContext, List<Item> paramList, ItemizedIconOverlay.OnItemGestureListener<Item> paramOnItemGestureListener)
  {
    this(paramList, paramOnItemGestureListener, new DefaultResourceProxyImpl(paramContext));
  }

  public ItemizedOverlayWithFocus(List<Item> paramList, Drawable paramDrawable1, Drawable paramDrawable2, int paramInt, ItemizedIconOverlay.OnItemGestureListener<Item> paramOnItemGestureListener, ResourceProxy paramResourceProxy)
  {
    super(paramList, paramDrawable1, paramOnItemGestureListener, paramResourceProxy);
    if (paramDrawable2 == null)
    {
      this.mMarkerFocusedBase = boundToHotspot(this.mResourceProxy.getDrawable(ResourceProxy.bitmap.marker_default_focused_base), OverlayItem.HotspotPlace.BOTTOM_CENTER);
      if (paramInt == -2147483648)
        break label158;
    }
    while (true)
    {
      this.mMarkerFocusedBackgroundColor = paramInt;
      this.mMarkerBackgroundPaint = new Paint();
      this.mDescriptionPaint = new Paint();
      this.mDescriptionPaint.setAntiAlias(true);
      this.mTitlePaint = new Paint();
      this.mTitlePaint.setFakeBoldText(true);
      this.mTitlePaint.setAntiAlias(true);
      unSetFocusedItem();
      return;
      this.mMarkerFocusedBase = paramDrawable2;
      break;
      label158: paramInt = DEFAULTMARKER_BACKGROUNDCOLOR;
    }
  }

  public ItemizedOverlayWithFocus(List<Item> paramList, ItemizedIconOverlay.OnItemGestureListener<Item> paramOnItemGestureListener, ResourceProxy paramResourceProxy)
  {
    this(paramList, paramResourceProxy.getDrawable(ResourceProxy.bitmap.marker_default), null, -2147483648, paramOnItemGestureListener, paramResourceProxy);
  }

  public void draw(Canvas paramCanvas, MapView paramMapView, boolean paramBoolean)
  {
    super.draw(paramCanvas, paramMapView, paramBoolean);
    if (paramBoolean);
    do
      return;
    while (this.mFocusedItemIndex == -2147483648);
    OverlayItem localOverlayItem = (OverlayItem)this.mItemList.get(this.mFocusedItemIndex);
    Drawable localDrawable = localOverlayItem.getMarker(4);
    if (localDrawable == null)
      localDrawable = this.mMarkerFocusedBase;
    paramMapView.getProjection().toMapPixels(localOverlayItem.mGeoPoint, this.mFocusedScreenCoords);
    Rect localRect = this.mRect;
    localDrawable.copyBounds(localRect);
    this.mRect.offset(this.mFocusedScreenCoords.x, this.mFocusedScreenCoords.y);
    String str1;
    String str2;
    label137: float[] arrayOfFloat;
    StringBuilder localStringBuilder;
    int i;
    int j;
    int k;
    int m;
    int n;
    String[] arrayOfString;
    int i4;
    int i5;
    int i8;
    int i9;
    if (localOverlayItem.mTitle == null)
    {
      str1 = this.UNKNOWN;
      if (localOverlayItem.mDescription != null)
        break label552;
      str2 = this.UNKNOWN;
      arrayOfFloat = new float[str2.length()];
      this.mDescriptionPaint.getTextWidths(str2, arrayOfFloat);
      localStringBuilder = new StringBuilder();
      i = 0;
      j = 0;
      k = 0;
      m = 0;
      n = 0;
      int i1 = arrayOfFloat.length;
      if (n < i1)
        break label562;
      if (n != k)
      {
        String str3 = str2.substring(k, n);
        int i13 = (int)this.mDescriptionPaint.measureText(str3);
        i = Math.max(i, i13);
        localStringBuilder.append(str3);
      }
      arrayOfString = localStringBuilder.toString().split("\n");
      int i2 = (int)this.mDescriptionPaint.measureText(str1);
      int i3 = Math.min(Math.max(i, i2), 200);
      i4 = -3 + (this.mRect.left - i3 / 2) + this.mRect.width() / 2;
      i5 = 6 + (i4 + i3);
      int i6 = this.mRect.top;
      int i7 = -6 + (i6 - 2 - 12 * (1 + arrayOfString.length));
      this.mMarkerBackgroundPaint.setColor(-16777216);
      paramCanvas.drawRoundRect(new RectF(i4 - 1, i7 - 1, i5 + 1, i6 + 1), 3.0F, 3.0F, this.mDescriptionPaint);
      this.mMarkerBackgroundPaint.setColor(this.mMarkerFocusedBackgroundColor);
      paramCanvas.drawRoundRect(new RectF(i4, i7, i5, i6), 3.0F, 3.0F, this.mMarkerBackgroundPaint);
      i8 = i4 + 3;
      i9 = i6 - 3;
    }
    for (int i10 = -1 + arrayOfString.length; ; i10--)
    {
      if (i10 < 0)
      {
        float f2 = i8;
        float f3 = i9 - 2;
        Paint localPaint = this.mTitlePaint;
        paramCanvas.drawText(str1, f2, f3, localPaint);
        paramCanvas.drawLine(i4, i9, i5, i9, this.mDescriptionPaint);
        int i11 = this.mFocusedScreenCoords.x;
        int i12 = this.mFocusedScreenCoords.y;
        Overlay.drawAt(paramCanvas, localDrawable, i11, i12, false);
        return;
        str1 = localOverlayItem.mTitle;
        break;
        label552: str2 = localOverlayItem.mDescription;
        break label137;
        label562: if (!Character.isLetter(str2.charAt(n)))
          m = n;
        float f1 = arrayOfFloat[n];
        if (f1 + j > 200.0F)
        {
          if (k != m)
            break label663;
          n--;
        }
        while (true)
        {
          localStringBuilder.append(str2.subSequence(k, n));
          localStringBuilder.append('\n');
          k = n;
          i = Math.max(i, j);
          j = 0;
          j = (int)(f1 + j);
          n++;
          break;
          label663: n = m;
        }
      }
      paramCanvas.drawText(arrayOfString[i10].trim(), i8, i9, this.mDescriptionPaint);
      i9 -= 12;
    }
  }

  public Item getFocusedItem()
  {
    if (this.mFocusedItemIndex == -2147483648)
      return null;
    return (OverlayItem)this.mItemList.get(this.mFocusedItemIndex);
  }

  protected boolean onSingleTapUpHelper(int paramInt, Item paramItem, MapView paramMapView)
  {
    if (this.mFocusItemsOnTap)
    {
      this.mFocusedItemIndex = paramInt;
      paramMapView.postInvalidate();
    }
    return this.mOnItemGestureListener.onItemSingleTapUp(paramInt, paramItem);
  }

  public void setFocusItemsOnTap(boolean paramBoolean)
  {
    this.mFocusItemsOnTap = paramBoolean;
  }

  public void setFocusedItem(int paramInt)
  {
    this.mFocusedItemIndex = paramInt;
  }

  public void setFocusedItem(Item paramItem)
  {
    int i = this.mItemList.indexOf(paramItem);
    if (i < 0)
      throw new IllegalArgumentException();
    setFocusedItem(i);
  }

  public void unSetFocusedItem()
  {
    this.mFocusedItemIndex = -2147483648;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.overlay.ItemizedOverlayWithFocus
 * JD-Core Version:    0.6.0
 */