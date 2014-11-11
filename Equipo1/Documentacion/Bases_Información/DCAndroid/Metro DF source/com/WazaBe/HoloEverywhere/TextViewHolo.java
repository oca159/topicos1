package com.WazaBe.HoloEverywhere;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewHolo extends TextView
{
  public TextViewHolo(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setTypeface(Typeface.createFromAsset(paramContext.getAssets(), "Roboto-Regular.ttf"));
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.WazaBe.HoloEverywhere.TextViewHolo
 * JD-Core Version:    0.6.0
 */