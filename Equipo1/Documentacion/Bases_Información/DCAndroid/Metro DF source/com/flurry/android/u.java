package com.flurry.android;

final class u
{
  long a;
  p b;
  String c;
  String d;
  int e;
  AdImage f;

  u(long paramLong, p paramp, AdImage paramAdImage, String paramString1, String paramString2, int paramInt)
  {
    this.a = paramLong;
    this.b = paramp;
    this.c = paramString1;
    this.f = paramAdImage;
    this.d = paramString2;
    this.e = paramInt;
  }

  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[id=" + this.a).append(",name=" + this.c + "]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.u
 * JD-Core Version:    0.6.0
 */