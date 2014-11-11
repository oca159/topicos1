package com.crayonlion.metro.view;

import android.content.Context;
import android.location.Location;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ZoomControls;
import com.crayonlion.metro.controller.interfaces.MapCustomListener;
import com.crayonlion.metro.controller.interfaces.MapDataSource;
import com.crayonlion.metro.model.Point3;
import com.crayonlion.metro.model.Ruta;
import com.crayonlion.metro.utils.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.ResourceProxy.string;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.tileprovider.MapTileProviderArray;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.tileprovider.modules.ArchiveFileFactory;
import org.osmdroid.tileprovider.modules.IArchiveFile;
import org.osmdroid.tileprovider.modules.MapTileFileArchiveProvider;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.tileprovider.util.SimpleRegisterReceiver;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.SimpleLocationOverlay;
import org.osmdroid.views.overlay.TilesOverlay;

public class OfflineMapView
{
  private static final int BACKGROUND_COLOR = -2236963;
  private static final String EXTERNAL_PATH = Environment.getExternalStorageDirectory() + "/metro/";
  private static final String MAP_NAME = "metro.zip";
  private XYTileSource MBTILESRENDER;
  private GeoPoint center;
  ArrayList<Point3> conexion;
  private TextView loadingText;
  private Context localContext;
  private GeoPoint location;
  private SimpleLocationOverlay locationOverlay;
  private CustomMapView mOsmv;
  private MapController mOsmvCtrl;
  private MapTileProviderArray mProvider;
  private ResourceProxy mResourceProxy;
  private BoundingBoxE6 mapBounds;
  private MapDataSource mapDataSource;
  private MapCustomListener mapListener;
  private MetroOverlay metroOverlay;
  private RelativeLayout rl;
  private View v;
  private ZoomControls zoomControls;

  public OfflineMapView(Context paramContext, MapDataSource paramMapDataSource, MapCustomListener paramMapCustomListener)
  {
    this.localContext = paramContext;
    this.mapDataSource = paramMapDataSource;
    this.mapListener = paramMapCustomListener;
    this.mapBounds = new BoundingBoxE6(19.530000000000001D, -98.950000000000003D, 19.260000000000002D, -99.230000000000004D);
    this.MBTILESRENDER = new XYTileSource("metro", ResourceProxy.string.offline_mode, 13, 14, 256, ".png", new String[] { "http://i.dont.care.org/" });
    this.center = new GeoPoint(19.432694000000001D, -99.133155000000002D);
    this.location = new GeoPoint(this.center);
    mapBeginConfig();
    loadUI();
    mapEndConfig();
  }

  private void loadUI()
  {
    this.v = ((LayoutInflater)this.localContext.getSystemService("layout_inflater")).inflate(2130903072, null);
    this.rl = ((RelativeLayout)this.v.findViewById(2130968650));
    this.mOsmv.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    this.loadingText = new TextView(this.localContext);
    this.loadingText.setTextSize(18.0F);
    this.loadingText.setText("Planeando ruta!");
    this.loadingText.setTextColor(-1);
    this.loadingText.setVisibility(8);
    this.loadingText.setBackgroundColor(-1426551294);
    this.loadingText.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
    this.loadingText.setGravity(17);
    this.rl.addView(this.mOsmv);
    this.rl.addView(this.loadingText);
    new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (paramView.getId() == OfflineMapView.this.zoomControls.getChildAt(1).getId())
        {
          OfflineMapView.this.zoomControls.getChildAt(0).setEnabled(true);
          OfflineMapView.this.mOsmvCtrl.zoomIn();
          if (OfflineMapView.this.mOsmv.getZoomLevel() >= 14)
            OfflineMapView.this.zoomControls.getChildAt(1).setEnabled(false);
        }
        do
        {
          do
            return;
          while (paramView.getId() != OfflineMapView.this.zoomControls.getChildAt(0).getId());
          OfflineMapView.this.zoomControls.getChildAt(1).setEnabled(true);
          OfflineMapView.this.mOsmvCtrl.zoomOut();
        }
        while (OfflineMapView.this.mOsmv.getZoomLevel() > 13);
        OfflineMapView.this.zoomControls.getChildAt(0).setEnabled(false);
      }
    };
  }

  private void mapBeginConfig()
  {
    Utils.copyAssetToFileSystem(this.localContext, "metro.zip");
    File localFile = Utils.getSystemFile(this.localContext, "metro.zip");
    IArchiveFile[] arrayOfIArchiveFile = new IArchiveFile[1];
    arrayOfIArchiveFile[0] = ArchiveFileFactory.getArchiveFile(localFile);
    MapTileFileArchiveProvider localMapTileFileArchiveProvider = new MapTileFileArchiveProvider(new SimpleRegisterReceiver(this.localContext), this.MBTILESRENDER, arrayOfIArchiveFile);
    this.mResourceProxy = new DefaultResourceProxyImpl(this.localContext);
    this.mProvider = new MapTileProviderArray(this.MBTILESRENDER, null, new MapTileModuleProviderBase[] { localMapTileFileArchiveProvider });
    this.mProvider.setUseDataConnection(false);
    this.mOsmv = new CustomMapView(this.localContext, 256, this.mResourceProxy, this.mProvider);
    this.mOsmv.setUseDataConnection(false);
    this.mOsmvCtrl = this.mOsmv.getController();
  }

  private void mapEndConfig()
  {
    TilesOverlay localTilesOverlay = this.mOsmv.getOverlayManager().getTilesOverlay();
    localTilesOverlay.setLoadingBackgroundColor(-2236963);
    localTilesOverlay.setLoadingLineColor(-2236963);
    this.mOsmv.setMultiTouchControls(true);
    this.mOsmv.setScrollableAreaLimit(this.mapBounds);
    this.mOsmvCtrl.setZoom(13);
    this.mOsmvCtrl.setCenter(this.center);
    this.metroOverlay = new MetroOverlay(this.localContext, this.mapDataSource, this.mapListener, this.mOsmv.getZoomLevel());
    this.locationOverlay = new SimpleLocationOverlay(this.localContext);
    this.mOsmv.getOverlays().add(this.metroOverlay);
  }

  public void clear()
  {
    this.metroOverlay.clear();
    this.mOsmv.invalidate();
  }

  public void destroy()
  {
    this.mProvider.detach();
    this.mProvider.clearTileCache();
    this.mOsmv.getTileProvider().detach();
    this.mOsmv.getTileProvider().clearTileCache();
    this.metroOverlay.destroy();
    System.gc();
    Runtime.getRuntime().gc();
  }

  public void focusPoint(IGeoPoint paramIGeoPoint)
  {
    this.mOsmvCtrl.animateTo(paramIGeoPoint);
  }

  public boolean getPinFinal()
  {
    return this.metroOverlay.getPinInit();
  }

  public int getPinFinalId()
  {
    return this.metroOverlay.getPinFinalId();
  }

  public boolean getPinInit()
  {
    return this.metroOverlay.getPinInit();
  }

  public int getPinInitId()
  {
    return this.metroOverlay.getPinInitId();
  }

  public Ruta getRuta()
  {
    return this.metroOverlay.getRuta();
  }

  public View getView()
  {
    return this.v;
  }

  public void hideTextLoading()
  {
    this.loadingText.setVisibility(8);
  }

  public boolean isDrawn()
  {
    return this.metroOverlay.isDrawn();
  }

  public void resume()
  {
    this.metroOverlay.resume();
    System.gc();
    Runtime.getRuntime().gc();
  }

  public void setDrawRuta(boolean paramBoolean)
  {
    this.metroOverlay.setDrawRuta(paramBoolean);
    this.mapListener.onDrawRuta(paramBoolean);
  }

  public void setPinFinalId(int paramInt)
  {
    this.metroOverlay.setPinFinalId(paramInt);
    this.mOsmv.invalidate();
  }

  public void setPinInitId(int paramInt)
  {
    this.metroOverlay.setPinInitId(paramInt);
    this.mOsmv.invalidate();
  }

  public void setRuta(Ruta paramRuta)
  {
    this.metroOverlay.setRuta(paramRuta);
    this.mapListener.onDrawRuta(this.metroOverlay.isDrawn());
    this.mOsmv.invalidate();
  }

  public void showTextLoading(String paramString)
  {
    this.loadingText.setVisibility(0);
    this.loadingText.setText(paramString);
  }

  public void updateLocation()
  {
  }

  public void updateLocation(Location paramLocation)
  {
    GeoPoint localGeoPoint = new GeoPoint(paramLocation.getLatitude(), paramLocation.getLongitude());
    if (!this.mapBounds.contains(localGeoPoint))
      return;
    this.locationOverlay.setLocation(localGeoPoint);
    focusPoint(localGeoPoint);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.view.OfflineMapView
 * JD-Core Version:    0.6.0
 */