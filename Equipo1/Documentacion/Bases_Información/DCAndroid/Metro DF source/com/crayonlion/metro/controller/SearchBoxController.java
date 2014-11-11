package com.crayonlion.metro.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.WazaBe.HoloEverywhere.R.layout;
import com.crayonlion.metro.EasyFragment;
import com.crayonlion.metro.controller.interfaces.SearchBoxListener;
import com.crayonlion.metro.metroApi.MetroManager;
import com.crayonlion.metro.utils.StringUtils;

public class SearchBoxController extends EasyFragment
{
  private Button buscarRutaButton;
  private String destino;
  private AutoCompleteTextView destinoText;
  private LinearLayout dialogoExpandido;
  private boolean expandido = false;
  private LinearLayout iconosLayout;
  private ImageView info;
  private ImageView localizacion;
  private ImageView lupa;
  private Spinner metodos;
  private String[] nombresEstaciones;
  private String origen;
  private AutoCompleteTextView origenText;
  private SearchBoxListener searchBoxListener;
  private int tipoBusqueda;
  private View v;

  private boolean datosValidos()
  {
    String str1 = this.origenText.getText().toString();
    String str2 = this.destinoText.getText().toString();
    return (!StringUtils.isBlank(str1)) && (!StringUtils.isBlank(str2));
  }

  private void leerCampos()
  {
    this.origen = this.origenText.getText().toString();
    this.destino = this.destinoText.getText().toString();
    this.tipoBusqueda = (int)this.metodos.getSelectedItemId();
  }

  private void setListeners(View paramView)
  {
    FocusChangeListener localFocusChangeListener = new FocusChangeListener(null);
    TextResponder localTextResponder = new TextResponder(null);
    this.origenText.setOnFocusChangeListener(localFocusChangeListener);
    this.destinoText.setOnFocusChangeListener(localFocusChangeListener);
    this.lupa.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        SearchBoxController.this.mostrarSearchBox();
        Log.v("Metro", "lupa press");
      }
    });
    this.info.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        SearchBoxController.this.searchBoxListener.onInfoButtonClicked();
        Log.v("Metro", "info press");
      }
    });
    this.localizacion.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        SearchBoxController.this.searchBoxListener.onRequestLocalization();
      }
    });
    this.origenText.addTextChangedListener(localTextResponder);
    this.destinoText.addTextChangedListener(localTextResponder);
    this.buscarRutaButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        SearchBoxController.this.leerCampos();
        int i = 0;
        int j = 0;
        for (int k = 0; k < SearchBoxController.this.nombresEstaciones.length; k++)
        {
          if (SearchBoxController.this.nombresEstaciones[k].equalsIgnoreCase(SearchBoxController.this.origen))
            i = 1;
          if (!SearchBoxController.this.nombresEstaciones[k].equalsIgnoreCase(SearchBoxController.this.destino))
            continue;
          j = 1;
        }
        if ((i != 0) && (j != 0))
        {
          SearchBoxController.this.ocultarSearchBox();
          SearchBoxController.this.searchBoxListener.onNewRouteRequested(SearchBoxController.this.origen, SearchBoxController.this.destino, SearchBoxController.this.tipoBusqueda);
          ((InputMethodManager)SearchBoxController.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(SearchBoxController.this.destinoText.getWindowToken(), 0);
          return;
        }
        String str1 = null;
        if (j == 0)
          str1 = SearchBoxController.this.destino;
        if (i == 0)
          str1 = SearchBoxController.this.origen;
        String str2 = "La estación " + str1 + " no existe o está mal escrita. Intentalo de nuevo por favor.";
        Toast.makeText(SearchBoxController.this.getActivity(), str2, 1).show();
      }
    });
  }

  public void hideInfoButton()
  {
    this.info.setVisibility(8);
  }

  public boolean isExpandido()
  {
    return this.expandido;
  }

  public void mostrarSearchBox()
  {
    this.lupa.setVisibility(8);
    this.info.setVisibility(8);
    this.iconosLayout.setVisibility(8);
    this.dialogoExpandido.setVisibility(0);
    this.expandido = true;
  }

  public void ocultarSearchBox()
  {
    this.dialogoExpandido.setVisibility(8);
    this.lupa.setVisibility(0);
    this.iconosLayout.setVisibility(0);
    this.expandido = false;
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.v = paramLayoutInflater.inflate(2130903080, null);
    this.dialogoExpandido = ((LinearLayout)this.v.findViewById(2130968654));
    this.origenText = ((AutoCompleteTextView)this.v.findViewById(2130968655));
    this.destinoText = ((AutoCompleteTextView)this.v.findViewById(2130968656));
    this.buscarRutaButton = ((Button)this.v.findViewById(2130968660));
    this.info = ((ImageView)this.v.findViewById(2130968653));
    this.lupa = ((ImageView)this.v.findViewById(2130968652));
    this.localizacion = ((ImageView)this.v.findViewById(2130968657));
    this.metodos = ((Spinner)this.v.findViewById(2130968659));
    this.iconosLayout = ((LinearLayout)this.v.findViewById(2130968651));
    ArrayAdapter localArrayAdapter1 = ArrayAdapter.createFromResource(getActivity(), 2131427329, R.layout.sherlock_spinner_dropdown_item);
    this.metodos.setAdapter(localArrayAdapter1);
    setListeners(this.v);
    this.nombresEstaciones = MetroManager.getAllEstacionesNames();
    this.origenText = ((AutoCompleteTextView)this.v.findViewById(2130968655));
    this.destinoText = ((AutoCompleteTextView)this.v.findViewById(2130968656));
    ArrayAdapter localArrayAdapter2 = new ArrayAdapter(getActivity(), R.layout.autocomplete_list_item, this.nombresEstaciones);
    this.origenText.setAdapter(localArrayAdapter2);
    this.destinoText.setAdapter(localArrayAdapter2);
    return this.v;
  }

  public void setDestinoText(String paramString)
  {
    this.destinoText.setText(paramString);
  }

  public void setOnNewRouteListener(SearchBoxListener paramSearchBoxListener)
  {
    this.searchBoxListener = paramSearchBoxListener;
  }

  public void setOrigenText(String paramString)
  {
    this.origenText.setText(paramString);
  }

  public void showInfoButton()
  {
    this.info.setVisibility(0);
  }

  private class FocusChangeListener
    implements View.OnFocusChangeListener
  {
    private FocusChangeListener()
    {
    }

    public void onFocusChange(View paramView, boolean paramBoolean)
    {
      if (SearchBoxController.this.datosValidos())
      {
        SearchBoxController.this.buscarRutaButton.setEnabled(true);
        return;
      }
      SearchBoxController.this.buscarRutaButton.setEnabled(false);
    }
  }

  private class TextResponder
    implements TextWatcher
  {
    private TextResponder()
    {
    }

    public void afterTextChanged(Editable paramEditable)
    {
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
      if (SearchBoxController.this.datosValidos())
        SearchBoxController.this.buscarRutaButton.setEnabled(true);
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.controller.SearchBoxController
 * JD-Core Version:    0.6.0
 */