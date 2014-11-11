package com.flurry.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build.VERSION;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

final class z extends RelativeLayout
{
  private v a;
  private p b;
  private int c;

  public z(Context paramContext, v paramv, p paramp, e parame, int paramInt, boolean paramBoolean)
  {
    super(paramContext);
    this.a = paramv;
    this.b = paramp;
    w localw = paramp.c;
    this.c = paramInt;
    switch (this.c)
    {
    default:
    case 2:
    case 1:
    }
    while (true)
    {
      setFocusable(true);
      return;
      if (paramBoolean)
        a(paramContext, parame, localw, false);
      while (true)
      {
        if (!paramBoolean)
          break label104;
        a(paramContext, parame, localw, false);
        break;
        a(paramContext, parame, localw, true);
      }
      label104: a(paramContext, parame, localw, true);
    }
  }

  private void a(Context paramContext, e parame, w paramw, boolean paramBoolean)
  {
    setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    c localc = parame.d;
    ImageView localImageView = new ImageView(paramContext);
    localImageView.setId(1);
    AdImage localAdImage1 = paramw.h;
    Bitmap localBitmap3;
    Bitmap localBitmap4;
    if (localAdImage1 != null)
    {
      byte[] arrayOfByte2 = localAdImage1.e;
      Bitmap localBitmap2 = BitmapFactory.decodeByteArray(arrayOfByte2, 0, arrayOfByte2.length);
      if (localBitmap2 == null)
        ai.a("FlurryAgent", "Ad with bad image: " + paramw.d + ", data: " + arrayOfByte2);
      if (localBitmap2 != null)
      {
        localBitmap3 = Bitmap.createBitmap(localBitmap2.getWidth(), localBitmap2.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap3);
        Paint localPaint1 = new Paint();
        Rect localRect = new Rect(0, 0, localBitmap2.getWidth(), localBitmap2.getHeight());
        RectF localRectF = new RectF(localRect);
        float f = r.a(paramContext, 8);
        localPaint1.setAntiAlias(true);
        localCanvas.drawARGB(0, 0, 0, 0);
        localPaint1.setColor(-16777216);
        localCanvas.drawRoundRect(localRectF, f, f, localPaint1);
        localPaint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        localCanvas.drawBitmap(localBitmap2, localRect, localRect, localPaint1);
        if (Integer.parseInt(Build.VERSION.SDK) <= 4)
          break label969;
        BlurMaskFilter localBlurMaskFilter = new BlurMaskFilter(3.0F, BlurMaskFilter.Blur.OUTER);
        Paint localPaint2 = new Paint();
        localPaint2.setMaskFilter(localBlurMaskFilter);
        int[] arrayOfInt = new int[2];
        localBitmap4 = localBitmap3.extractAlpha(localPaint2, arrayOfInt).copy(Bitmap.Config.ARGB_8888, true);
        new Canvas(localBitmap4).drawBitmap(localBitmap3, -arrayOfInt[0], -arrayOfInt[1], null);
      }
    }
    while (true)
    {
      localImageView.setImageBitmap(localBitmap4);
      r.a(paramContext, localImageView, r.a(paramContext, localc.m), r.a(paramContext, localc.n));
      localImageView.setScaleType(ImageView.ScaleType.FIT_XY);
      AdImage localAdImage2 = this.a.a(localc.c);
      Bitmap localBitmap1;
      Object localObject;
      TextView localTextView1;
      TextView localTextView2;
      if (localAdImage2 != null)
      {
        byte[] arrayOfByte1 = localAdImage2.e;
        localBitmap1 = BitmapFactory.decodeByteArray(arrayOfByte1, 0, arrayOfByte1.length);
        if (NinePatch.isNinePatchChunk(localBitmap1.getNinePatchChunk()))
        {
          localObject = new NinePatchDrawable(localBitmap1, localBitmap1.getNinePatchChunk(), new Rect(0, 0, 0, 0), null);
          setBackgroundDrawable((Drawable)localObject);
        }
      }
      else
      {
        localTextView1 = new TextView(paramContext);
        localTextView1.setId(5);
        localTextView1.setPadding(0, 0, 0, 0);
        localTextView2 = new TextView(paramContext);
        localTextView2.setId(3);
        localTextView2.setPadding(0, 0, 0, 0);
        if (!paramBoolean)
          break label866;
        localTextView1.setTextColor(localc.f);
        localTextView1.setTextSize(localc.e);
        localTextView1.setText(new String("• " + localc.b));
        localTextView1.setTypeface(Typeface.create(localc.d, 0));
        localTextView2.setTextColor(localc.i);
        localTextView2.setTextSize(localc.h);
        localTextView2.setTypeface(Typeface.create(localc.g, 0));
        localTextView2.setText(paramw.d);
      }
      while (true)
      {
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
        addView(new ImageView(paramContext), localLayoutParams1);
        int i = localc.q - (localc.o << 1) - localc.m;
        RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams2.addRule(9);
        localLayoutParams2.setMargins(localc.o, localc.p, i, 0);
        addView(localImageView, localLayoutParams2);
        RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams3.addRule(6, localImageView.getId());
        localLayoutParams3.addRule(1, localImageView.getId());
        localLayoutParams3.setMargins(0, 0, 0, 0);
        addView(localTextView1, localLayoutParams3);
        RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams4.addRule(1, localImageView.getId());
        localLayoutParams4.addRule(3, localTextView1.getId());
        localLayoutParams4.setMargins(0, -2, 0, 0);
        addView(localTextView2, localLayoutParams4);
        return;
        localObject = new BitmapDrawable(localBitmap1);
        break;
        label866: localTextView1.setId(3);
        localTextView1.setText(paramw.d);
        localTextView1.setTextColor(localc.i);
        localTextView1.setTextSize(localc.h);
        localTextView1.setTypeface(Typeface.create(localc.g, 0));
        localTextView2.setId(4);
        localTextView2.setText(paramw.c);
        localTextView2.setTextColor(localc.l);
        localTextView2.setTextSize(localc.k);
        localTextView2.setTypeface(Typeface.create(localc.j, 0));
      }
      label969: localBitmap4 = localBitmap3;
    }
  }

  final p a()
  {
    return this.b;
  }

  final void a(p paramp)
  {
    this.b = paramp;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.z
 * JD-Core Version:    0.6.0
 */