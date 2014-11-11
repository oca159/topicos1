package com.flurry.android;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;

final class o extends RelativeLayout
{
  private v a;
  private Context b;
  private String c;
  private int d;
  private boolean e;
  private boolean f;

  o(v paramv, Context paramContext, String paramString, int paramInt)
  {
    super(paramContext);
    this.a = paramv;
    this.b = paramContext;
    this.c = paramString;
    this.d = paramInt;
  }

  private static RelativeLayout.LayoutParams b()
  {
    return new RelativeLayout.LayoutParams(-1, -1);
  }

  private z c()
  {
    monitorenter;
    while (true)
    {
      try
      {
        v localv = this.a;
        Context localContext = this.b;
        String[] arrayOfString = new String[1];
        arrayOfString[0] = this.c;
        List localList = localv.a(localContext, Arrays.asList(arrayOfString), null, this.d, false);
        if (!localList.isEmpty())
        {
          localz = (z)localList.get(0);
          localz.setOnClickListener(this.a);
          monitorexit;
          return localz;
        }
      }
      finally
      {
        localObject = finally;
        monitorexit;
        throw localObject;
      }
      z localz = null;
    }
  }

  final void a()
  {
    if (!this.e)
    {
      z localz = c();
      if (localz == null)
        break label61;
      removeAllViews();
      addView(localz, b());
      localz.a().a(new f(3, this.a.j()));
      this.e = true;
    }
    while (true)
    {
      this.f = true;
      return;
      label61: if (this.f)
        continue;
      TextView localTextView = new TextView(this.b);
      localTextView.setText(v.b);
      localTextView.setTextSize(1, 20.0F);
      addView(localTextView, b());
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.o
 * JD-Core Version:    0.6.0
 */