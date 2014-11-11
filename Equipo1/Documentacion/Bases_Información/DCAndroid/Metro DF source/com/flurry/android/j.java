package com.flurry.android;

import java.util.Iterator;
import java.util.List;

final class j
  implements Runnable
{
  j(List paramList)
  {
  }

  public final void run()
  {
    Iterator localIterator = this.a.iterator();
    while (localIterator.hasNext())
      ((o)localIterator.next()).a();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.j
 * JD-Core Version:    0.6.0
 */