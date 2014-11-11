package com.flurry.android;

import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class p
{
  final String a;
  int b = this.g.incrementAndGet();
  w c;
  long d;
  List e = new ArrayList();
  private byte f;
  private AtomicInteger g = new AtomicInteger(0);

  p(p paramp, long paramLong)
  {
    this(paramp.a, paramp.f, paramLong);
    this.c = paramp.c;
    this.d = paramp.d;
  }

  p(String paramString, byte paramByte, long paramLong)
  {
    this.a = paramString;
    this.f = paramByte;
    this.e.add(new f(1, paramLong));
  }

  final long a()
  {
    return ((f)this.e.get(0)).b;
  }

  final void a(f paramf)
  {
    this.e.add(paramf);
  }

  final void a(DataOutput paramDataOutput)
  {
    paramDataOutput.writeShort(this.b);
    paramDataOutput.writeUTF(this.a);
    paramDataOutput.writeByte(this.f);
    if (this.c == null)
    {
      paramDataOutput.writeLong(0L);
      paramDataOutput.writeLong(0L);
      paramDataOutput.writeByte(0);
    }
    while (true)
    {
      paramDataOutput.writeShort(this.e.size());
      Iterator localIterator = this.e.iterator();
      while (localIterator.hasNext())
      {
        f localf = (f)localIterator.next();
        paramDataOutput.writeByte(localf.a);
        paramDataOutput.writeLong(localf.b);
      }
      paramDataOutput.writeLong(this.c.a);
      paramDataOutput.writeLong(this.c.e);
      byte[] arrayOfByte = this.c.g;
      paramDataOutput.writeByte(arrayOfByte.length);
      paramDataOutput.write(arrayOfByte);
    }
  }

  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("{hook: " + this.a + ", ad: " + this.c.d + ", transitions: [");
    Iterator localIterator = this.e.iterator();
    while (localIterator.hasNext())
    {
      localStringBuilder.append((f)localIterator.next());
      localStringBuilder.append(",");
    }
    localStringBuilder.append("]}");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.p
 * JD-Core Version:    0.6.0
 */