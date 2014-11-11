package com.WazaBe.HoloEverywhere;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class RadioButtonHolo extends RadioButton
{
  public RadioButtonHolo(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setTypeface(Typeface.createFromAsset(paramContext.getAssets(), "Roboto-Regular.ttf"));
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.WazaBe.HoloEverywhere.RadioButtonHolo
 * JD-Core Version:    0.6.0
 */