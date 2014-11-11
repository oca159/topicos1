package com.flurry.android;

final class ae
  implements Runnable
{
  ae(v paramv, int paramInt)
  {
  }

  public final void run()
  {
    CallbackEvent localCallbackEvent = new CallbackEvent(this.a);
    v.a(this.b).onAdsUpdated(localCallbackEvent);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.ae
 * JD-Core Version:    0.6.0
 */