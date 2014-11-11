package com.flurry.android;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

final class s extends LinearLayout
{
  public s(CatalogActivity paramCatalogActivity, Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-1);
    AdImage localAdImage = CatalogActivity.c(paramCatalogActivity).l();
    if (localAdImage != null)
    {
      ImageView localImageView = new ImageView(paramContext);
      localImageView.setId(10000);
      byte[] arrayOfByte = localAdImage.e;
      if (arrayOfByte != null)
        localImageView.setImageBitmap(BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length));
      r.a(paramContext, localImageView, r.a(paramContext, localAdImage.b), r.a(paramContext, localAdImage.c));
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams.setMargins(0, 0, 0, -3);
      setGravity(3);
      addView(localImageView, localLayoutParams);
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.s
 * JD-Core Version:    0.6.0
 */