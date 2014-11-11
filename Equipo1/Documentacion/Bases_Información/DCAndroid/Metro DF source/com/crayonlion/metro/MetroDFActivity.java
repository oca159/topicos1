package com.crayonlion.metro;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.crayonlion.daogenerator.Estacion;
import com.crayonlion.metro.controller.DetailViewController;
import com.crayonlion.metro.controller.SearchBoxController;
import com.crayonlion.metro.controller.SimpleMapController;
import com.crayonlion.metro.controller.SimpleMapControllerEventListener;
import com.crayonlion.metro.controller.interfaces.DetailViewListener;
import com.crayonlion.metro.controller.interfaces.EstacionMasCercanaListener;
import com.crayonlion.metro.controller.interfaces.SearchBoxListener;
import com.crayonlion.metro.metroApi.EstacionMasCercana;
import com.crayonlion.metro.metroApi.MetroManager;
import com.crayonlion.metro.model.Ruta;
import com.crayonlion.metro.utils.Console;
import com.crayonlion.metro.utils.StringUtils;
import com.crayonlion.metro.utils.Utils;
import com.flurry.android.FlurryAgent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MetroDFActivity extends EasyFragmentActivity
  implements SearchBoxListener, EstacionMasCercanaListener, SimpleMapControllerEventListener, DetailViewListener
{
  FrameLayout barraFragment;
  FrameLayout contentFragment;
  DetailViewController detailViewController;
  EstacionMasCercana estacionMasCercana;
  MenuItem estacionMasCercanaMenu;
  boolean isDetailViewDisplayed = false;
  SimpleMapController mapa;
  String masCerca;
  ProgressDialog pd;
  Ruta rutaActual;
  SearchBoxController searchBox;

  private void activarGPS()
  {
    if (this.estacionMasCercana != null)
      this.estacionMasCercana.startGeoUpdates();
  }

  private Intent createTwitterIntent()
  {
    List localList1 = Utils.createListFromArray(getResources().getStringArray(2131427331));
    String[] arrayOfString = getResources().getStringArray(2131427332);
    Random localRandom = new Random();
    String str1 = arrayOfString[localRandom.nextInt(arrayOfString.length)] + " " + getString(2131296276) + " " + getString(2131296277);
    Intent localIntent1 = new Intent("android.intent.action.SEND");
    localIntent1.setType("text/plain");
    List localList2 = getPackageManager().queryIntentActivities(localIntent1, 0);
    int i = 0;
    int j = localList2.size();
    ComponentName localComponentName = null;
    if (i < j)
    {
      ActivityInfo localActivityInfo = ((ResolveInfo)localList2.get(i)).activityInfo;
      if (localList1.contains(localActivityInfo.name))
        localComponentName = new ComponentName(localActivityInfo.applicationInfo.packageName, localActivityInfo.name);
    }
    else
    {
      if (localComponentName == null)
        break label263;
      if (!localComponentName.getClassName().contains("com.twidroid"))
        break label256;
    }
    label256: for (String str2 = "application/twitter"; ; str2 = "text/plain")
    {
      Intent localIntent3 = new Intent("android.intent.action.SEND");
      localIntent3.setComponent(localComponentName);
      localIntent3.setType(str2);
      localIntent3.putExtra("android.intent.extra.TEXT", str1);
      return localIntent3;
      i++;
      break;
    }
    label263: Intent localIntent2 = new Intent("android.intent.action.VIEW");
    localIntent2.setData(Uri.parse(getString(2131296275) + str1));
    return Intent.createChooser(localIntent2, "Compartir...");
  }

  private void desactivarGPS()
  {
    if (this.estacionMasCercana != null)
      this.estacionMasCercana.stopGeoUpdates();
  }

  public void initThreadBusqueda()
  {
    if (!this.searchBox.isExpandido())
      this.searchBox.hideInfoButton();
  }

  public void mostrarToast(String paramString, int paramInt)
  {
    Toast.makeText(getApplicationContext(), paramString, paramInt).show();
  }

  public void onBackPressed()
  {
    if (this.searchBox.isExpandido())
    {
      this.searchBox.ocultarSearchBox();
      if (this.mapa.hayRuta())
      {
        this.searchBox.showInfoButton();
        return;
      }
      this.searchBox.hideInfoButton();
      return;
    }
    if (this.isDetailViewDisplayed)
    {
      this.mapa.resume();
      getSupportFragmentManager().beginTransaction().remove(this.detailViewController).commit();
      getSupportActionBar().setDisplayHomeAsUpEnabled(false);
      this.isDetailViewDisplayed = false;
      return;
    }
    setResult(1);
    finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    MetroManager.initMetroManager(this);
    setContentView(2130903071);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    this.mapa = ((SimpleMapController)getSupportFragmentManager().findFragmentById(2130968648));
    this.searchBox = ((SearchBoxController)getSupportFragmentManager().findFragmentById(2130968649));
    this.searchBox.setOnNewRouteListener(this);
    this.estacionMasCercana = new EstacionMasCercana(this);
    this.estacionMasCercana.setOnEstacionMasCercanaListener(this);
    this.mapa.setSimpleMapControllerEventListener(this);
    activarGPS();
    this.detailViewController = new DetailViewController(this);
    this.detailViewController.setDetailViewListener(this);
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getSupportMenuInflater().inflate(2131492864, paramMenu);
    return true;
  }

  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return super.onCreateView(paramView, paramString, paramContext, paramAttributeSet);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    MetroManager.clean();
    Console.log("Finalizando");
  }

  public void onDetailViewDisplay()
  {
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    this.isDetailViewDisplayed = true;
  }

  public void onEstacionMasCercanaFound(Estacion paramEstacion, boolean paramBoolean)
  {
    if (paramEstacion != null)
    {
      this.masCerca = paramEstacion.getNombreEstacion();
      if (!paramBoolean)
        desactivarGPS();
      invalidateOptionsMenu();
      HashMap localHashMap = new HashMap();
      localHashMap.put("Estacion", this.masCerca);
      FlurryAgent.logEvent("estacionMasCercanaFound", localHashMap);
      mostrarToast("La estación más cercana es " + StringUtils.capitalize(this.masCerca), 0);
    }
  }

  public void onGeoFix(Location paramLocation)
  {
  }

  public void onInfoButtonClicked()
  {
    if (!this.mapa.canSelect())
      return;
    this.mapa.destroy();
    this.detailViewController = new DetailViewController(this);
    getSupportFragmentManager().beginTransaction().add(16908290, this.detailViewController).setTransition(17432579).commit();
    this.detailViewController.setNewRuta(this.rutaActual);
    this.isDetailViewDisplayed = true;
  }

  public boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
      return true;
    case 2130968662:
      startActivity(createTwitterIntent());
      return true;
    case 2130968661:
      this.searchBox.setOrigenText(this.masCerca);
      this.searchBox.mostrarSearchBox();
      return true;
    case 2130968663:
      activarGPS();
      return true;
    case 2130968664:
      this.mapa.clearMap();
      this.searchBox.hideInfoButton();
      return true;
    case 2130968665:
      startActivity(new Intent(this, TutorialActivity.class).putExtra("origen", 1));
      return true;
    case 16908332:
    }
    this.mapa.resume();
    getSupportFragmentManager().beginTransaction().remove(this.detailViewController).commit();
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    this.isDetailViewDisplayed = false;
    return true;
  }

  public void onNewRouteRequested(String paramString1, String paramString2, int paramInt)
  {
    int i = MetroManager.getEstacionByName(paramString1).getIdEstacionAuxiliar().intValue();
    int j = MetroManager.getEstacionByName(paramString2).getIdEstacionAuxiliar().intValue();
    this.mapa.setCurrentSearchMethod(paramInt);
    this.mapa.onStationSelected(i);
    this.mapa.onStationSelected(j);
    this.mapa.focusEstacion(MetroManager.getEstacionById(i));
    HashMap localHashMap = new HashMap();
    localHashMap.put("Ruta", paramString1 + " a " + paramString2);
    localHashMap.put("TipoBusqueda", String.valueOf(paramInt));
    FlurryAgent.logEvent("nuevaRutaSearchBox", localHashMap);
  }

  protected void onPause()
  {
    desactivarGPS();
    super.onPause();
    this.mapa.onPause();
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    if (this.masCerca != null)
      paramMenu.findItem(2130968661).setTitle(StringUtils.formatLongText(this.masCerca, 16));
    return true;
  }

  public void onRequestLocalization()
  {
    if (this.masCerca != null)
    {
      this.searchBox.setOrigenText(this.masCerca.toLowerCase());
      this.mapa.focusEstacion(MetroManager.getEstacionByName(this.masCerca.toLowerCase()));
      return;
    }
    mostrarToast("La ubicación no está disponible", 0);
  }

  protected void onResume()
  {
    super.onResume();
    activarGPS();
    this.mapa.onResume();
  }

  public void onRutaDrawn(boolean paramBoolean)
  {
    this.rutaActual = this.mapa.getRuta();
    if (!this.searchBox.isExpandido())
    {
      if ((this.rutaActual.getEstaciones().size() > 1) && (paramBoolean))
      {
        this.searchBox.showInfoButton();
        return;
      }
      this.searchBox.hideInfoButton();
      return;
    }
    this.searchBox.hideInfoButton();
  }

  public void onStart()
  {
    super.onStart();
    FlurryAgent.onStartSession(this, "APWZXHWIT439MB2W1D8V");
    activarGPS();
  }

  public void onStop()
  {
    super.onStop();
    FlurryAgent.onEndSession(this);
  }

  public void setDestinoSearchBox(int paramInt)
  {
    this.searchBox.setDestinoText(((Estacion)MetroManager.estaciones.get(paramInt)).getNombreEstacion());
  }

  public void setOrigenSearchBox(int paramInt)
  {
    this.searchBox.setOrigenText(((Estacion)MetroManager.estaciones.get(paramInt)).getNombreEstacion());
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.MetroDFActivity
 * JD-Core Version:    0.6.0
 */