package com.flurry.android;

final class m
  implements Runnable
{
  m(al paramal, String paramString)
  {
  }

  public final void run()
  {
    if (this.a != null)
    {
      v.a(this.b.d, this.b.b, this.a);
      this.b.c.a(new f(8, this.b.d.j()));
      return;
    }
    String str = "Unable to launch in app market: " + this.b.a;
    ai.d(v.a, str);
    v.b(this.b.d, str);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.m
 * JD-Core Version:    0.6.0
 */