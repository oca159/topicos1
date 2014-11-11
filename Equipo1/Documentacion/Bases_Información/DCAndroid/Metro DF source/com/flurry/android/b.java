package com.flurry.android;

import android.content.Context;
import android.os.Handler;

final class b
  implements Runnable
{
  b(FlurryAgent paramFlurryAgent, boolean paramBoolean, Context paramContext)
  {
  }

  public final void run()
  {
    FlurryAgent.b(this.b);
    FlurryAgent.c(this.b);
    if (!this.c)
      FlurryAgent.d(this.b).postDelayed(new l(this), FlurryAgent.i());
    if (FlurryAgent.j())
      FlurryAgent.e(this.b).c();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.b
 * JD-Core Version:    0.6.0
 */