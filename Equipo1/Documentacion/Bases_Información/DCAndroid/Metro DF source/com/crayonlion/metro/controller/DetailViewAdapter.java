package com.crayonlion.metro.controller;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.crayonlion.daogenerator.Estacion;
import com.crayonlion.daogenerator.Linea;
import com.crayonlion.metro.metroApi.MetroManager;
import com.crayonlion.metro.model.Segmento;
import com.crayonlion.metro.utils.StringUtils;
import java.util.List;

public class DetailViewAdapter extends ArrayAdapter<Segmento>
{
  private Context context;
  private List<Segmento> segmentos;

  public DetailViewAdapter(Context paramContext, int paramInt, List<Segmento> paramList)
  {
    super(paramContext, paramInt, paramList);
    this.segmentos = paramList;
    this.context = paramContext;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if (localView == null)
      localView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130903067, paramViewGroup, false);
    Segmento localSegmento = (Segmento)this.segmentos.get(paramInt);
    TextView localTextView1 = (TextView)localView.findViewById(2130968636);
    TextView localTextView2 = (TextView)localView.findViewById(2130968634);
    TextView localTextView3 = (TextView)localView.findViewById(2130968638);
    TextView localTextView4 = (TextView)localView.findViewById(2130968637);
    ImageView localImageView1 = (ImageView)localView.findViewById(2130968631);
    ImageView localImageView2 = (ImageView)localView.findViewById(2130968633);
    CheckBox localCheckBox = (CheckBox)localView.findViewById(2130968639);
    LinearLayout localLinearLayout1 = (LinearLayout)localView.findViewById(2130968632);
    LinearLayout localLinearLayout2 = (LinearLayout)localView.findViewById(2130968635);
    localTextView1.setText("DESDE: " + StringUtils.capitalize(localSegmento.getInicio().getNombreEstacion()));
    localTextView2.setText("LINEA " + localSegmento.getLinea().toString());
    localTextView3.setText("DIRECCIÓN: " + StringUtils.capitalize(localSegmento.getDireccion()));
    localTextView4.setText("HASTA: " + StringUtils.capitalize(localSegmento.getFin().getNombreEstacion()));
    localLinearLayout1.setBackgroundColor(Color.parseColor(((Linea)MetroManager.getColorLineaById(localSegmento.getLinea().intValue()).get(0)).getColor()));
    localLinearLayout2.setBackgroundColor(Color.parseColor(((Linea)MetroManager.getColorLineaById(localSegmento.getLinea().intValue()).get(0)).getColor()));
    switch (localSegmento.getEtapa())
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(localSegmento)
      {
        public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
        {
          if (paramBoolean)
            Log.v("metro", "alarma seteada al segmento:" + this.val$actual.getInicio().getNombreEstacion() + " a " + this.val$actual.getFin().getNombreEstacion());
          for (String str = "La alarma sonará al llegar a " + this.val$actual.getFin().getNombreEstacion(); ; str = "La alarma para la estación " + this.val$actual.getFin().getNombreEstacion() + " se ha desactivado")
          {
            Toast.makeText(DetailViewAdapter.this.context, str, 0).show();
            return;
            Log.v("metro", "alarma borrada al segmento:" + this.val$actual.getInicio().getNombreEstacion() + " a " + this.val$actual.getFin().getNombreEstacion());
          }
        }
      });
      return localView;
      localImageView1.setImageDrawable(this.context.getResources().getDrawable(2130837681));
      localImageView1.setScaleType(ImageView.ScaleType.FIT_CENTER);
      localImageView2.setImageDrawable(this.context.getResources().getDrawable(2130837776));
      localImageView2.setScaleType(ImageView.ScaleType.FIT_END);
      continue;
      localImageView1.setScaleType(ImageView.ScaleType.FIT_START);
      localImageView1.setImageDrawable(this.context.getResources().getDrawable(2130837775));
      localImageView2.setImageDrawable(this.context.getResources().getDrawable(2130837776));
      localImageView2.setScaleType(ImageView.ScaleType.FIT_END);
      continue;
      localImageView1.setScaleType(ImageView.ScaleType.FIT_START);
      localImageView1.setImageDrawable(this.context.getResources().getDrawable(2130837775));
      localImageView2.setImageDrawable(this.context.getResources().getDrawable(2130837676));
      localImageView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
      continue;
      localImageView1.setImageResource(2130837681);
      localImageView2.setImageResource(2130837676);
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.controller.DetailViewAdapter
 * JD-Core Version:    0.6.0
 */