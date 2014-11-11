package com.flurry.android;

import java.io.DataInput;

final class e extends ak
{
  String a;
  byte b;
  byte c;
  c d;

  e()
  {
  }

  e(DataInput paramDataInput)
  {
    this.a = paramDataInput.readUTF();
    this.b = paramDataInput.readByte();
    this.c = paramDataInput.readByte();
  }

  public final String toString()
  {
    return "{name: " + this.a + ", blockId: " + this.b + ", themeId: " + this.c;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.e
 * JD-Core Version:    0.6.0
 */