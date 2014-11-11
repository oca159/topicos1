package com.flurry.android;

import android.content.Context;

final class d
  implements Runnable
{
  d(FlurryAgent paramFlurryAgent, Context paramContext, boolean paramBoolean)
  {
  }

  public final void run()
  {
    if (!FlurryAgent.a(this.c))
      FlurryAgent.a(this.c, this.a);
    FlurryAgent.a(this.c, this.a, this.b);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.d
 * JD-Core Version:    0.6.0
 */