package com.flurry.android;

public class FlurryAgent$FlurryDefaultExceptionHandler
  implements Thread.UncaughtExceptionHandler
{
  private Thread.UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();

  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    try
    {
      FlurryAgent.h().a(paramThrowable);
      if (this.a != null)
        this.a.uncaughtException(paramThread, paramThrowable);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        ai.b("FlurryAgent", "", localThrowable);
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.FlurryAgent.FlurryDefaultExceptionHandler
 * JD-Core Version:    0.6.0
 */