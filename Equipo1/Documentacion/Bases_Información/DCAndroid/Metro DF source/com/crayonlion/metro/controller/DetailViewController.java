package com.crayonlion.metro.controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.crayonlion.daogenerator.Estacion;
import com.crayonlion.metro.EasyFragment;
import com.crayonlion.metro.controller.interfaces.DetailViewListener;
import com.crayonlion.metro.model.Ruta;
import com.crayonlion.metro.model.Segmento;
import com.crayonlion.metro.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DetailViewController extends EasyFragment
  implements AdapterView.OnItemClickListener
{
  DetailViewAdapter adapter;
  private AssetManager assetManager;
  LinearLayout barraInutil;
  Context context;
  Estacion finalDeRuta;
  ImageView imagenFin;
  ImageView imagenInicio;
  Estacion inicioDeRuta;
  ListView listaDeRuta;
  private DetailViewListener listener;
  Ruta rutaActual;
  ArrayList<Segmento> segmentosDeRuta;
  TextView tiempoTotalText;

  public DetailViewController(Context paramContext)
  {
    this.context = paramContext;
    this.assetManager = this.context.getAssets();
  }

  private ArrayList<Segmento> separarRuta(Ruta paramRuta)
  {
    ArrayList localArrayList1 = paramRuta.getEstaciones();
    Collections.reverse(localArrayList1);
    ArrayList localArrayList2 = new ArrayList();
    if (paramRuta.getEstacionTransborde().size() == 0)
    {
      Segmento localSegmento1 = new Segmento();
      localSegmento1.setInicio((Estacion)localArrayList1.get(0));
      localSegmento1.setFin((Estacion)localArrayList1.get(-1 + localArrayList1.size()));
      localSegmento1.setEtapa(3);
      localSegmento1.setLinea((Integer)paramRuta.getLineas().get(0));
      localSegmento1.setDireccion((String)paramRuta.getDirecciones().get(0));
      localArrayList2.add(localSegmento1);
      return localArrayList2;
    }
    Segmento localSegmento2 = new Segmento();
    localSegmento2.setEtapa(0);
    localSegmento2.setInicio((Estacion)localArrayList1.get(0));
    localSegmento2.setFin((Estacion)paramRuta.getEstacionTransborde().get(0));
    localSegmento2.setDireccion((String)paramRuta.getDirecciones().get(0));
    localSegmento2.setLinea((Integer)paramRuta.getLineas().get(0));
    Estacion localEstacion = localSegmento2.getFin();
    localArrayList2.add(localSegmento2);
    int i = 0;
    label207: Segmento localSegmento3;
    if (i < paramRuta.getEstacionTransborde().size())
    {
      localSegmento3 = new Segmento();
      if (i != -1 + paramRuta.getEstacionTransborde().size())
        break label334;
      localSegmento3.setEtapa(2);
      localSegmento3.setInicio(localEstacion);
      localSegmento3.setFin((Estacion)localArrayList1.get(-1 + localArrayList1.size()));
      localSegmento3.setDireccion((String)paramRuta.getDirecciones().get(-1 + paramRuta.getDirecciones().size()));
      localSegmento3.setLinea((Integer)paramRuta.getLineas().get(-1 + paramRuta.getLineas().size()));
      localArrayList2.add(localSegmento3);
    }
    while (true)
    {
      i++;
      break label207;
      break;
      label334: localSegmento3.setEtapa(1);
      localSegmento3.setInicio(localEstacion);
      localSegmento3.setFin((Estacion)paramRuta.getEstacionTransborde().get(i + 1));
      localSegmento3.setDireccion((String)paramRuta.getDirecciones().get(i + 1));
      localSegmento3.setLinea((Integer)paramRuta.getLineas().get(i + 1));
      localEstacion = localSegmento3.getFin();
      localArrayList2.add(localSegmento3);
    }
  }

  public void calcularTiempoSegmento()
  {
  }

  public void invalidateRuta()
  {
    this.segmentosDeRuta = separarRuta(this.rutaActual);
    this.adapter = new DetailViewAdapter(this.context, 2130903067, this.segmentosDeRuta);
    this.listaDeRuta.setAdapter(this.adapter);
    this.adapter.notifyDataSetChanged();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903069, null);
    if (this.listener != null)
      this.listener.onDetailViewDisplay();
    this.listaDeRuta = ((ListView)localView.findViewById(2130968643));
    this.listaDeRuta.setDivider(null);
    this.listaDeRuta.setOnItemClickListener(this);
    this.barraInutil = ((LinearLayout)localView.findViewById(2130968644));
    this.barraInutil.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
      }
    });
    if (this.rutaActual != null)
    {
      invalidateRuta();
      this.imagenInicio = ((ImageView)localView.findViewById(2130968640));
      this.imagenFin = ((ImageView)localView.findViewById(2130968642));
      this.tiempoTotalText = ((TextView)localView.findViewById(2130968645));
    }
    return localView;
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
  }

  public void onPause()
  {
    super.onPause();
    if (this.imagenInicio != null)
    {
      ((BitmapDrawable)this.imagenInicio.getDrawable()).getBitmap().recycle();
      this.imagenInicio.setImageDrawable(null);
    }
    if (this.imagenFin != null)
    {
      ((BitmapDrawable)this.imagenFin.getDrawable()).getBitmap().recycle();
      this.imagenFin.setImageDrawable(null);
    }
  }

  public void onResume()
  {
    super.onResume();
    getSherlockActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    if (this.listener != null)
      this.listener.onDetailViewDisplay();
    float f = this.rutaActual.getTiempoDeViaje() / 60.0F;
    this.tiempoTotalText.setText(String.valueOf((int)Math.ceil(f)));
    try
    {
      this.imagenInicio.setImageBitmap(Utils.decodeFileSimple(this.assetManager.openFd("icons/z" + ((Estacion)this.rutaActual.getEstaciones().get(0)).getId() + ".jpg")));
      this.imagenFin.setImageBitmap(Utils.decodeFileSimple(this.assetManager.openFd("icons/z" + ((Estacion)this.rutaActual.getEstaciones().get(-1 + this.rutaActual.getEstaciones().size())).getId() + ".jpg")));
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  public void setDetailViewListener(DetailViewListener paramDetailViewListener)
  {
    this.listener = paramDetailViewListener;
  }

  public void setNewRuta(Ruta paramRuta)
  {
    this.rutaActual = paramRuta;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.controller.DetailViewController
 * JD-Core Version:    0.6.0
 */