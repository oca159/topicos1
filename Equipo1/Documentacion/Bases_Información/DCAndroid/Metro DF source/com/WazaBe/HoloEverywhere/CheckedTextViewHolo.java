package com.WazaBe.HoloEverywhere;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

public class CheckedTextViewHolo extends CheckedTextView
{
  public CheckedTextViewHolo(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setTypeface(Typeface.createFromAsset(paramContext.getAssets(), "Roboto-Regular.ttf"));
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.WazaBe.HoloEverywhere.CheckedTextViewHolo
 * JD-Core Version:    0.6.0
 */