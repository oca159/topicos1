package com.crayonlion.metro.view;

import android.content.Context;
import android.view.View;
import java.util.zip.Inflater;

public class DetailView
{
  private Context localContext;

  public DetailView(Context paramContext)
  {
    this.localContext = paramContext;
  }

  public View createView()
  {
    ((Inflater)this.localContext.getSystemService("layout_inflater"));
    return null;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.view.DetailView
 * JD-Core Version:    0.6.0
 */