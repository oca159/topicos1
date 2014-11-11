package com.flurry.android;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class x extends LinearLayout
{
  private View a;
  private List b = new ArrayList();
  private boolean c = true;

  public x(CatalogActivity paramCatalogActivity, Context paramContext)
  {
    super(paramContext);
    setOrientation(1);
    setGravity(48);
    this.a = new s(paramCatalogActivity, paramContext);
    this.a.setId(10002);
    this.a.setOnClickListener(paramCatalogActivity);
    a(a(paramContext), this.c);
  }

  private void a(List paramList, boolean paramBoolean)
  {
    removeAllViews();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    localLayoutParams.setMargins(0, 0, 0, 0);
    addView(this.a, localLayoutParams);
    if (paramList != null)
    {
      this.b.clear();
      this.b.addAll(paramList);
    }
    if (paramBoolean)
    {
      Iterator localIterator = this.b.iterator();
      while (localIterator.hasNext())
      {
        z localz = (z)localIterator.next();
        addView(localz, localLayoutParams);
        localz.a().a(new f(3, CatalogActivity.b(this.d)));
      }
    }
    refreshDrawableState();
  }

  final List a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 1; i <= 3; i++)
      localArrayList.add("Flurry_Canvas_Hook_" + i);
    w localw;
    Long localLong;
    if (CatalogActivity.a(this.d) == null)
    {
      localw = null;
      localLong = null;
      if (localw != null)
        break label140;
    }
    List localList;
    while (true)
    {
      localList = CatalogActivity.c(this.d).a(paramContext, localArrayList, localLong, 1, true);
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
        ((z)localIterator.next()).setOnClickListener(this.d);
      localw = CatalogActivity.a(this.d).c;
      break;
      label140: localLong = Long.valueOf(localw.a);
    }
    return localList;
  }

  final void a()
  {
    if (!this.c);
    for (boolean bool = true; ; bool = false)
    {
      this.c = bool;
      a(null, this.c);
      return;
    }
  }

  final void a(List paramList)
  {
    a(paramList, this.c);
  }

  final List b()
  {
    return this.b;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.x
 * JD-Core Version:    0.6.0
 */