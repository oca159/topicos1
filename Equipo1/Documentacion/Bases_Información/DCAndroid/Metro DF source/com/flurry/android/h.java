package com.flurry.android;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

final class h extends LinkedHashMap
{
  h(ag paramag, int paramInt, float paramFloat)
  {
    super(paramInt, paramFloat, true);
  }

  protected final boolean removeEldestEntry(Map.Entry paramEntry)
  {
    return size() > ag.a(this.a);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.h
 * JD-Core Version:    0.6.0
 */