package com.flurry.android;

import android.os.Handler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class ah
{
  private List a = new ArrayList();
  private Handler b;
  private Handler c;
  private int d;
  private Runnable e;

  ah(Handler paramHandler, int paramInt)
  {
    this.b = paramHandler;
    this.c = new Handler();
    this.d = paramInt;
    this.e = new k(this);
    b();
  }

  private void a()
  {
    monitorenter;
    ArrayList localArrayList;
    try
    {
      localArrayList = new ArrayList();
      Iterator localIterator = this.a.iterator();
      while (localIterator.hasNext())
      {
        o localo = (o)((WeakReference)localIterator.next()).get();
        if (localo == null)
          continue;
        localArrayList.add(localo);
      }
    }
    finally
    {
      monitorexit;
    }
    this.c.post(new j(localArrayList));
    b();
    monitorexit;
  }

  private void b()
  {
    monitorenter;
    try
    {
      Iterator localIterator = this.a.iterator();
      while (localIterator.hasNext())
      {
        if (((WeakReference)localIterator.next()).get() != null)
          continue;
        localIterator.remove();
      }
    }
    finally
    {
      monitorexit;
    }
    this.b.removeCallbacks(this.e);
    this.b.postDelayed(this.e, this.d);
    monitorexit;
  }

  final void a(o paramo)
  {
    monitorenter;
    try
    {
      paramo.a();
      this.a.add(new WeakReference(paramo));
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.ah
 * JD-Core Version:    0.6.0
 */