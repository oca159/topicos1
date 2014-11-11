package com.flurry.android;

import java.io.DataInput;

final class w extends ak
{
  long a;
  long b;
  String c;
  String d;
  long e;
  Long f;
  byte[] g;
  AdImage h;

  w()
  {
  }

  w(DataInput paramDataInput)
  {
    b(paramDataInput);
  }

  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < paramArrayOfByte.length)
    {
      int j = 0xF & paramArrayOfByte[i] >> 4;
      label41: int k;
      if (j < 10)
      {
        localStringBuilder.append((char)(j + 48));
        k = 0xF & paramArrayOfByte[i];
        if (k >= 10)
          break label89;
        localStringBuilder.append((char)(k + 48));
      }
      while (true)
      {
        i++;
        break;
        localStringBuilder.append((char)(-10 + (j + 65)));
        break label41;
        label89: localStringBuilder.append((char)(-10 + (k + 65)));
      }
    }
    return localStringBuilder.toString();
  }

  private void b(DataInput paramDataInput)
  {
    this.a = paramDataInput.readLong();
    this.b = paramDataInput.readLong();
    this.d = paramDataInput.readUTF();
    this.c = paramDataInput.readUTF();
    this.e = paramDataInput.readLong();
    this.f = Long.valueOf(paramDataInput.readLong());
    this.g = new byte[paramDataInput.readUnsignedByte()];
    paramDataInput.readFully(this.g);
  }

  final void a(DataInput paramDataInput)
  {
    b(paramDataInput);
  }

  public final String toString()
  {
    return "ad {id=" + this.a + ", name='" + this.d + "', cookie: '" + a(this.g) + "'}";
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.w
 * JD-Core Version:    0.6.0
 */