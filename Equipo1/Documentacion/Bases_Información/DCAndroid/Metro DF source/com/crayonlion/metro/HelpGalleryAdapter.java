package com.crayonlion.metro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import com.crayonlion.metro.utils.Utils;
import java.io.IOException;

public class HelpGalleryAdapter extends BaseAdapter
{
  Activity context;
  ImageView imagen;
  int origen;
  ProgressDialog pd;

  public HelpGalleryAdapter(Activity paramActivity, int paramInt)
  {
    this.context = paramActivity;
    this.origen = paramInt;
  }

  public void destroy()
  {
    if (this.imagen != null)
    {
      ((BitmapDrawable)this.imagen.getDrawable()).getBitmap().recycle();
      this.imagen.setImageDrawable(null);
    }
  }

  public int getCount()
  {
    return 5;
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = LayoutInflater.from(this.context).inflate(2130903070, paramViewGroup, false);
    this.imagen = ((ImageView)localView.findViewById(2130968646));
    Button localButton = (Button)localView.findViewById(2130968647);
    AssetManager localAssetManager = this.context.getAssets();
    switch (paramInt)
    {
    default:
      return localView;
    case 0:
      try
      {
        this.imagen.setImageBitmap(Utils.decodeFileSimple(localAssetManager.openFd("uno.png")));
        localButton.setText("Saltar");
        localButton.setVisibility(0);
        localButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            HelpGalleryAdapter.this.pd = ProgressDialog.show(HelpGalleryAdapter.this.context, "Espera", "Cargando!");
            if (HelpGalleryAdapter.this.origen == 1)
            {
              HelpGalleryAdapter.this.context.finish();
              return;
            }
            HelpGalleryAdapter.this.context.startActivity(new Intent(HelpGalleryAdapter.this.context, MetroDFActivity.class));
          }
        });
        return localView;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        return localView;
      }
    case 1:
      this.imagen.setImageBitmap(Utils.decodeFileSimple(localAssetManager.openFd("dos.png")));
      localButton.setVisibility(8);
      return localView;
    case 2:
      this.imagen.setImageBitmap(Utils.decodeFileSimple(localAssetManager.openFd("tres.png")));
      localButton.setVisibility(8);
      return localView;
    case 3:
      this.imagen.setImageBitmap(Utils.decodeFileSimple(localAssetManager.openFd("cuatro.png")));
      localButton.setVisibility(8);
      return localView;
    case 4:
    }
    this.imagen.setImageBitmap(Utils.decodeFileSimple(localAssetManager.openFd("cinco.png")));
    localButton.setText("¡Empezar a viajar!");
    localButton.setVisibility(0);
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        HelpGalleryAdapter.this.pd = ProgressDialog.show(HelpGalleryAdapter.this.context, "Espera", "Cargando!");
        if (HelpGalleryAdapter.this.origen == 1)
        {
          HelpGalleryAdapter.this.context.finish();
          return;
        }
        HelpGalleryAdapter.this.context.startActivity(new Intent(HelpGalleryAdapter.this.context, MetroDFActivity.class));
      }
    });
    return localView;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.HelpGalleryAdapter
 * JD-Core Version:    0.6.0
 */