package org.osmdroid.views.overlay;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.osmdroid.views.MapView;

public class OverlayManager extends AbstractList<Overlay>
{
  private final CopyOnWriteArrayList<Overlay> mOverlayList;
  private TilesOverlay mTilesOverlay;

  public OverlayManager(TilesOverlay paramTilesOverlay)
  {
    setTilesOverlay(paramTilesOverlay);
    this.mOverlayList = new CopyOnWriteArrayList();
  }

  public void add(int paramInt, Overlay paramOverlay)
  {
    this.mOverlayList.add(paramInt, paramOverlay);
  }

  public Overlay get(int paramInt)
  {
    return (Overlay)this.mOverlayList.get(paramInt);
  }

  public TilesOverlay getTilesOverlay()
  {
    return this.mTilesOverlay;
  }

  public boolean onCreateOptionsMenu(Menu paramMenu, int paramInt, MapView paramMapView)
  {
    boolean bool = true;
    Iterator localIterator = overlaysReversed().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if ((this.mTilesOverlay != null) && ((this.mTilesOverlay instanceof IOverlayMenuProvider)) && (this.mTilesOverlay.isOptionsMenuEnabled()))
          bool &= this.mTilesOverlay.onCreateOptionsMenu(paramMenu, paramInt, paramMapView);
        return bool;
      }
      Overlay localOverlay = (Overlay)localIterator.next();
      if ((!(localOverlay instanceof IOverlayMenuProvider)) || (!((IOverlayMenuProvider)localOverlay).isOptionsMenuEnabled()))
        continue;
      bool &= ((IOverlayMenuProvider)localOverlay).onCreateOptionsMenu(paramMenu, paramInt, paramMapView);
    }
  }

  public void onDetach(MapView paramMapView)
  {
    if (this.mTilesOverlay != null)
      this.mTilesOverlay.onDetach(paramMapView);
    Iterator localIterator = overlaysReversed().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((Overlay)localIterator.next()).onDetach(paramMapView);
    }
  }

  public boolean onDoubleTap(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Overlay)localIterator.next()).onDoubleTap(paramMotionEvent, paramMapView));
    return true;
  }

  public boolean onDoubleTapEvent(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Overlay)localIterator.next()).onDoubleTapEvent(paramMotionEvent, paramMapView));
    return true;
  }

  public boolean onDown(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Overlay)localIterator.next()).onDown(paramMotionEvent, paramMapView));
    return true;
  }

  public void onDraw(Canvas paramCanvas, MapView paramMapView)
  {
    if ((this.mTilesOverlay != null) && (this.mTilesOverlay.isEnabled()))
      this.mTilesOverlay.draw(paramCanvas, paramMapView, true);
    Iterator localIterator1 = this.mOverlayList.iterator();
    Iterator localIterator2;
    if (!localIterator1.hasNext())
    {
      if ((this.mTilesOverlay != null) && (this.mTilesOverlay.isEnabled()))
        this.mTilesOverlay.draw(paramCanvas, paramMapView, false);
      localIterator2 = this.mOverlayList.iterator();
    }
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        return;
        Overlay localOverlay1 = (Overlay)localIterator1.next();
        if (!localOverlay1.isEnabled())
          break;
        localOverlay1.draw(paramCanvas, paramMapView, true);
        break;
      }
      Overlay localOverlay2 = (Overlay)localIterator2.next();
      if (!localOverlay2.isEnabled())
        continue;
      localOverlay2.draw(paramCanvas, paramMapView, false);
    }
  }

  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Overlay)localIterator.next()).onFling(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2, paramMapView));
    return true;
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Overlay)localIterator.next()).onKeyDown(paramInt, paramKeyEvent, paramMapView));
    return true;
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Overlay)localIterator.next()).onKeyUp(paramInt, paramKeyEvent, paramMapView));
    return true;
  }

  public boolean onLongPress(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Overlay)localIterator.next()).onLongPress(paramMotionEvent, paramMapView));
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem, int paramInt, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    while (true)
      if (!localIterator.hasNext())
      {
        if ((this.mTilesOverlay == null) || (!(this.mTilesOverlay instanceof IOverlayMenuProvider)) || (!this.mTilesOverlay.isOptionsMenuEnabled()) || (!this.mTilesOverlay.onOptionsItemSelected(paramMenuItem, paramInt, paramMapView)))
          break;
        return true;
      }
      else
      {
        Overlay localOverlay = (Overlay)localIterator.next();
        if (((localOverlay instanceof IOverlayMenuProvider)) && (((IOverlayMenuProvider)localOverlay).isOptionsMenuEnabled()) && (((IOverlayMenuProvider)localOverlay).onOptionsItemSelected(paramMenuItem, paramInt, paramMapView)))
          return true;
      }
    return false;
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu, int paramInt, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if ((this.mTilesOverlay != null) && ((this.mTilesOverlay instanceof IOverlayMenuProvider)) && (this.mTilesOverlay.isOptionsMenuEnabled()))
          this.mTilesOverlay.onPrepareOptionsMenu(paramMenu, paramInt, paramMapView);
        return true;
      }
      Overlay localOverlay = (Overlay)localIterator.next();
      if ((!(localOverlay instanceof IOverlayMenuProvider)) || (!((IOverlayMenuProvider)localOverlay).isOptionsMenuEnabled()))
        continue;
      ((IOverlayMenuProvider)localOverlay).onPrepareOptionsMenu(paramMenu, paramInt, paramMapView);
    }
  }

  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Overlay)localIterator.next()).onScroll(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2, paramMapView));
    return true;
  }

  public void onShowPress(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((Overlay)localIterator.next()).onShowPress(paramMotionEvent, paramMapView);
    }
  }

  public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Overlay)localIterator.next()).onSingleTapConfirmed(paramMotionEvent, paramMapView));
    return true;
  }

  public boolean onSingleTapUp(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Overlay)localIterator.next()).onSingleTapUp(paramMotionEvent, paramMapView));
    return true;
  }

  public boolean onSnapToItem(int paramInt1, int paramInt2, Point paramPoint, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    Overlay localOverlay;
    do
    {
      if (!localIterator.hasNext())
        return false;
      localOverlay = (Overlay)localIterator.next();
    }
    while ((!(localOverlay instanceof Overlay.Snappable)) || (!((Overlay.Snappable)localOverlay).onSnapToItem(paramInt1, paramInt2, paramPoint, paramMapView)));
    return true;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Overlay)localIterator.next()).onTouchEvent(paramMotionEvent, paramMapView));
    return true;
  }

  public boolean onTrackballEvent(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    Iterator localIterator = overlaysReversed().iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Overlay)localIterator.next()).onTrackballEvent(paramMotionEvent, paramMapView));
    return true;
  }

  public Iterable<Overlay> overlaysReversed()
  {
    return new Iterable()
    {
      public Iterator<Overlay> iterator()
      {
        return new Iterator(OverlayManager.this.mOverlayList.listIterator(OverlayManager.this.mOverlayList.size()))
        {
          public boolean hasNext()
          {
            return this.val$i.hasPrevious();
          }

          public Overlay next()
          {
            return (Overlay)this.val$i.previous();
          }

          public void remove()
          {
            this.val$i.remove();
          }
        };
      }
    };
  }

  public Overlay remove(int paramInt)
  {
    return (Overlay)this.mOverlayList.remove(paramInt);
  }

  public Overlay set(int paramInt, Overlay paramOverlay)
  {
    return (Overlay)this.mOverlayList.set(paramInt, paramOverlay);
  }

  public void setOptionsMenusEnabled(boolean paramBoolean)
  {
    Iterator localIterator = this.mOverlayList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Overlay localOverlay = (Overlay)localIterator.next();
      if ((!(localOverlay instanceof IOverlayMenuProvider)) || (!((IOverlayMenuProvider)localOverlay).isOptionsMenuEnabled()))
        continue;
      ((IOverlayMenuProvider)localOverlay).setOptionsMenuEnabled(paramBoolean);
    }
  }

  public void setTilesOverlay(TilesOverlay paramTilesOverlay)
  {
    this.mTilesOverlay = paramTilesOverlay;
  }

  public int size()
  {
    return this.mOverlayList.size();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.overlay.OverlayManager
 * JD-Core Version:    0.6.0
 */