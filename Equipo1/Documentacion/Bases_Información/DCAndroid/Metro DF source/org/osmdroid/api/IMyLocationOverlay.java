package org.osmdroid.api;

import android.location.Location;
import android.os.Bundle;

public abstract interface IMyLocationOverlay
{
  public abstract void disableCompass();

  public abstract void disableMyLocation();

  public abstract boolean enableCompass();

  public abstract boolean enableMyLocation();

  public abstract Location getLastFix();

  public abstract boolean isCompassEnabled();

  public abstract boolean isMyLocationEnabled();

  public abstract void onStatusChanged(String paramString, int paramInt, Bundle paramBundle);

  public abstract boolean runOnFirstFix(Runnable paramRunnable);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.api.IMyLocationOverlay
 * JD-Core Version:    0.6.0
 */