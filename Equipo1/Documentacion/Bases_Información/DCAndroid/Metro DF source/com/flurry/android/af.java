package com.flurry.android;

final class af
  implements Runnable
{
  af(v paramv, String paramString)
  {
  }

  public final void run()
  {
    CallbackEvent localCallbackEvent = new CallbackEvent(101);
    localCallbackEvent.setMessage(this.a);
    if (v.a(this.b) != null)
      v.a(this.b).onMarketAppLaunchError(localCallbackEvent);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.af
 * JD-Core Version:    0.6.0
 */