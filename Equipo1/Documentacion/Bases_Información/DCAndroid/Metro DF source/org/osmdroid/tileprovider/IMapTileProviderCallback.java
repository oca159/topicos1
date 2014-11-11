package org.osmdroid.tileprovider;

import android.graphics.drawable.Drawable;

public abstract interface IMapTileProviderCallback
{
  public abstract void mapTileRequestCandidate(MapTileRequestState paramMapTileRequestState, Drawable paramDrawable);

  public abstract void mapTileRequestCompleted(MapTileRequestState paramMapTileRequestState, Drawable paramDrawable);

  public abstract void mapTileRequestFailed(MapTileRequestState paramMapTileRequestState);

  public abstract boolean useDataConnection();
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.IMapTileProviderCallback
 * JD-Core Version:    0.6.0
 */