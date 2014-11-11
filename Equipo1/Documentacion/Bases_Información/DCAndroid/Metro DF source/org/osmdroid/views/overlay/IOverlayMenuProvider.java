package org.osmdroid.views.overlay;

import android.view.Menu;
import android.view.MenuItem;
import org.osmdroid.views.MapView;

public abstract interface IOverlayMenuProvider
{
  public abstract boolean isOptionsMenuEnabled();

  public abstract boolean onCreateOptionsMenu(Menu paramMenu, int paramInt, MapView paramMapView);

  public abstract boolean onOptionsItemSelected(MenuItem paramMenuItem, int paramInt, MapView paramMapView);

  public abstract boolean onPrepareOptionsMenu(Menu paramMenu, int paramInt, MapView paramMapView);

  public abstract void setOptionsMenuEnabled(boolean paramBoolean);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.overlay.IOverlayMenuProvider
 * JD-Core Version:    0.6.0
 */