package com.flurry.android;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.Html;
import android.text.SpannedString;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

final class ac extends RelativeLayout
{
  private final SpannedString a = new SpannedString(Html.fromHtml("<html><div='style:font-size:7px'>&lt; Previous</div></html>"));
  private final SpannedString b = new SpannedString(Html.fromHtml("<html><div='style:font-size:7px;color:#ffA500'>&lt; Previous</div></html>"));

  public ac(CatalogActivity paramCatalogActivity, Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-16777216);
    TextView localTextView = new TextView(paramContext);
    localTextView.setTextColor(ColorStateList.valueOf(-1));
    localTextView.setId(10001);
    localTextView.setText(this.a);
    localTextView.setPadding(5, 2, 5, 2);
    localTextView.setFocusable(true);
    localTextView.setOnFocusChangeListener(new ad(this, localTextView));
    localTextView.setOnClickListener(paramCatalogActivity);
    localTextView.setEnabled(true);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams1.setMargins(0, 0, 0, 0);
    setLayoutParams(localLayoutParams1);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams2.setMargins(2, 0, 0, 0);
    localLayoutParams2.addRule(4);
    addView(localTextView, localLayoutParams2);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.ac
 * JD-Core Version:    0.6.0
 */