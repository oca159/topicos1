package org.osmdroid.views;

import org.osmdroid.ResourceProxy;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.views.overlay.TilesOverlay;

public abstract interface TilesOverlayFactory
{
  public abstract TilesOverlay createTileOverlay(MapTileProviderBase paramMapTileProviderBase, ResourceProxy paramResourceProxy);

  public static class DefaultTilesOverlayFactory
    implements TilesOverlayFactory
  {
    public TilesOverlay createTileOverlay(MapTileProviderBase paramMapTileProviderBase, ResourceProxy paramResourceProxy)
    {
      return new TilesOverlay(paramMapTileProviderBase, paramResourceProxy);
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.TilesOverlayFactory
 * JD-Core Version:    0.6.0
 */