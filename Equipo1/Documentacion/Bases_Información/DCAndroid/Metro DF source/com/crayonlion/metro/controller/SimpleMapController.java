package com.crayonlion.metro.controller;

import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.crayonlion.daogenerator.Conexion;
import com.crayonlion.daogenerator.Estacion;
import com.crayonlion.daogenerator.Linea;
import com.crayonlion.metro.EasyFragment;
import com.crayonlion.metro.controller.interfaces.EstacionMasCercanaListener;
import com.crayonlion.metro.controller.interfaces.MapCustomListener;
import com.crayonlion.metro.controller.interfaces.MapDataSource;
import com.crayonlion.metro.metroApi.EstacionMasCercana;
import com.crayonlion.metro.metroApi.MetroManager;
import com.crayonlion.metro.model.Ruta;
import com.crayonlion.metro.utils.StringUtils;
import com.crayonlion.metro.view.OfflineMapView;
import com.flurry.android.FlurryAgent;
import java.util.HashMap;
import java.util.List;
import org.osmdroid.util.GeoPoint;

public class SimpleMapController extends EasyFragment
  implements MapCustomListener, MapDataSource, EstacionMasCercanaListener
{
  boolean canSelect = true;
  int currentSearchMethod = 0;
  EstacionMasCercana estacionCercana;
  boolean hayRuta = false;
  SimpleMapControllerEventListener listener;
  OfflineMapView mapView;
  int pinSelector = 0;

  private void initSearch(int paramInt1, int paramInt2, int paramInt3)
  {
    this.mapView.showTextLoading("Planeando ruta...");
    this.mapView.setDrawRuta(false);
    this.listener.initThreadBusqueda();
    AsyncSearch localAsyncSearch = new AsyncSearch(null);
    Integer[] arrayOfInteger = new Integer[3];
    arrayOfInteger[0] = Integer.valueOf(paramInt1);
    arrayOfInteger[1] = Integer.valueOf(paramInt2);
    arrayOfInteger[2] = Integer.valueOf(paramInt3);
    localAsyncSearch.execute(arrayOfInteger);
    HashMap localHashMap = new HashMap();
    localHashMap.put("Ruta", MetroManager.getEstacionById(this.mapView.getPinInitId()).getNombreEstacion() + " a " + MetroManager.getEstacionById(this.mapView.getPinFinalId()).getNombreEstacion());
    localHashMap.put("TipoBusqueda", String.valueOf(this.currentSearchMethod));
    FlurryAgent.logEvent("nuevaRutaMapa", localHashMap);
  }

  private void startGeoUpdates()
  {
    if (this.estacionCercana != null)
      this.estacionCercana.startGeoUpdates();
  }

  private void stopGeoUpdates()
  {
    if (this.estacionCercana != null)
      this.estacionCercana.stopGeoUpdates();
  }

  public boolean canSelect()
  {
    return this.canSelect;
  }

  public void clearMap()
  {
    this.pinSelector = 0;
    this.mapView.setDrawRuta(false);
    this.mapView.clear();
  }

  public void destroy()
  {
    this.mapView.destroy();
    stopGeoUpdates();
  }

  public void focusEstacion(Estacion paramEstacion)
  {
    GeoPoint localGeoPoint = new GeoPoint(paramEstacion.getLatitud().floatValue(), paramEstacion.getLongitud().floatValue());
    this.mapView.focusPoint(localGeoPoint);
  }

  public int getColorLinea(int paramInt)
  {
    return Color.parseColor(MetroManager.getLineaById(paramInt).getColor());
  }

  public Conexion getConexion(int paramInt)
  {
    return MetroManager.getConexionById(paramInt);
  }

  public int getCurrentSearchMethod()
  {
    return this.currentSearchMethod;
  }

  public int getIconEstacion(int paramInt)
  {
    return 0;
  }

  public Point getLocationEstacion(int paramInt)
  {
    return new Point(MetroManager.getEstacionById(paramInt).getPosmapx().intValue(), MetroManager.getEstacionById(paramInt).getPosmapy().intValue());
  }

  public Point getLocationEstacionMapa(int paramInt)
  {
    return new Point((int)(1000000.0D * MetroManager.getEstacionById(paramInt).getLatitud().floatValue()), (int)(1000000.0D * MetroManager.getEstacionById(paramInt).getLongitud().floatValue()));
  }

  public String getNameEstacion(int paramInt)
  {
    return StringUtils.formatTextMap(MetroManager.getEstacionById(paramInt).getNombreEstacion());
  }

  public int getNumConexiones()
  {
    return MetroManager.getAllConexiones().size();
  }

  public int getNumEstaciones()
  {
    return MetroManager.getAllEstaciones().size();
  }

  public int getNumLineas()
  {
    return MetroManager.getAllLineas().size();
  }

  public Ruta getRuta()
  {
    return this.mapView.getRuta();
  }

  public boolean hayRuta()
  {
    return this.hayRuta;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.estacionCercana = new EstacionMasCercana(getSherlockActivity());
    this.estacionCercana.setOnEstacionMasCercanaListener(this);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mapView = new OfflineMapView(getActivity(), this, this);
    return this.mapView.getView();
  }

  public void onDrawRuta(boolean paramBoolean)
  {
    Log.v("metro", "isDrawn:" + paramBoolean);
    this.listener.onRutaDrawn(paramBoolean);
    this.hayRuta = paramBoolean;
  }

  public void onEstacionMasCercanaFound(Estacion paramEstacion, boolean paramBoolean)
  {
  }

  public void onGeoFix(Location paramLocation)
  {
    this.mapView.updateLocation(paramLocation);
  }

  public void onPause()
  {
    super.onPause();
    destroy();
  }

  public void onPinDestinoChanged(int paramInt)
  {
    this.listener.setDestinoSearchBox(paramInt);
  }

  public void onPinOrigenChanged(int paramInt)
  {
    this.listener.setOrigenSearchBox(paramInt);
  }

  public void onResume()
  {
    super.onResume();
    resume();
  }

  public void onStationSelected(int paramInt)
  {
    if (!this.canSelect);
    while (true)
    {
      return;
      this.pinSelector = (1 + this.pinSelector);
      if (this.pinSelector % 2 == 1)
      {
        clearMap();
        this.pinSelector = (1 + this.pinSelector);
        this.mapView.setPinInitId(paramInt);
      }
      while ((this.pinSelector % 2 == 0) && (this.pinSelector != 0))
      {
        initSearch(this.mapView.getPinInitId(), this.mapView.getPinFinalId(), this.currentSearchMethod);
        return;
        this.mapView.setPinFinalId(paramInt);
      }
    }
  }

  public void resume()
  {
    this.mapView.resume();
    startGeoUpdates();
  }

  public void setCurrentSearchMethod(int paramInt)
  {
    this.currentSearchMethod = paramInt;
  }

  public void setRuta(Ruta paramRuta)
  {
    this.mapView.setRuta(paramRuta);
  }

  public void setSimpleMapControllerEventListener(SimpleMapControllerEventListener paramSimpleMapControllerEventListener)
  {
    this.listener = paramSimpleMapControllerEventListener;
  }

  private class AsyncSearch extends AsyncTask<Integer, Void, Ruta>
  {
    private AsyncSearch()
    {
    }

    protected Ruta doInBackground(Integer[] paramArrayOfInteger)
    {
      SimpleMapController.this.canSelect = false;
      return MetroManager.buscaRuta(paramArrayOfInteger[0].intValue(), paramArrayOfInteger[1].intValue(), paramArrayOfInteger[2].intValue());
    }

    protected void onPostExecute(Ruta paramRuta)
    {
      SimpleMapController.this.mapView.setRuta(paramRuta);
      SimpleMapController.this.mapView.hideTextLoading();
      SimpleMapController.this.mapView.setDrawRuta(true);
      SimpleMapController.this.canSelect = true;
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.controller.SimpleMapController
 * JD-Core Version:    0.6.0
 */