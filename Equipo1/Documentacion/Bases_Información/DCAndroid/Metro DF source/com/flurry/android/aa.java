package com.flurry.android;

import android.content.Context;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class aa
{
  private Context a;
  private v b;
  private a c;
  private volatile long d;
  private ag e = new ag(100);
  private ag f = new ag(100);
  private Map g = new HashMap();
  private Map h = new HashMap();
  private Map i = new HashMap();
  private Map j = new HashMap();
  private volatile boolean k;

  private c a(byte paramByte)
  {
    monitorenter;
    try
    {
      c localc = (c)this.i.get(Byte.valueOf(paramByte));
      monitorexit;
      return localc;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private void a(int paramInt)
  {
    if (!this.g.isEmpty());
    for (boolean bool = true; ; bool = false)
    {
      this.k = bool;
      if (this.k)
        this.b.a(paramInt);
      return;
    }
  }

  private void a(DataInputStream paramDataInputStream)
  {
    int m = 0;
    ai.a("FlurryAgent", "Reading cache");
    if (paramDataInputStream.readUnsignedShort() != 2)
      return;
    this.d = paramDataInputStream.readLong();
    int n = paramDataInputStream.readUnsignedShort();
    this.e = new ag(100);
    for (int i1 = 0; i1 < n; i1++)
    {
      long l3 = paramDataInputStream.readLong();
      AdImage localAdImage = new AdImage();
      localAdImage.a(paramDataInputStream);
      this.e.a(Long.valueOf(l3), localAdImage);
    }
    int i2 = paramDataInputStream.readUnsignedShort();
    this.f = new ag(100);
    for (int i3 = 0; i3 < i2; i3++)
    {
      long l2 = paramDataInputStream.readLong();
      am localam = new am();
      if (paramDataInputStream.readBoolean())
        localam.a = paramDataInputStream.readUTF();
      if (paramDataInputStream.readBoolean())
        localam.b = paramDataInputStream.readUTF();
      localam.c = paramDataInputStream.readInt();
      this.f.a(Long.valueOf(l2), localam);
    }
    int i4 = paramDataInputStream.readUnsignedShort();
    this.h = new HashMap(i4);
    for (int i5 = 0; i5 < i4; i5++)
    {
      String str2 = paramDataInputStream.readUTF();
      e locale = new e(paramDataInputStream);
      this.h.put(str2, locale);
    }
    int i6 = paramDataInputStream.readUnsignedShort();
    this.g = new HashMap(i6);
    for (int i7 = 0; i7 < i6; i7++)
    {
      String str1 = paramDataInputStream.readUTF();
      int i11 = paramDataInputStream.readUnsignedShort();
      w[] arrayOfw = new w[i11];
      for (int i12 = 0; i12 < i11; i12++)
      {
        w localw = new w();
        localw.a(paramDataInputStream);
        arrayOfw[i12] = localw;
      }
      this.g.put(str1, arrayOfw);
    }
    int i8 = paramDataInputStream.readUnsignedShort();
    this.i = new HashMap();
    for (int i9 = 0; i9 < i8; i9++)
    {
      byte b1 = paramDataInputStream.readByte();
      c localc = new c();
      localc.b(paramDataInputStream);
      this.i.put(Byte.valueOf(b1), localc);
    }
    int i10 = paramDataInputStream.readUnsignedShort();
    this.j = new HashMap(i10);
    while (m < i10)
    {
      short s = paramDataInputStream.readShort();
      long l1 = paramDataInputStream.readLong();
      this.j.put(Short.valueOf(s), Long.valueOf(l1));
      m++;
    }
    f();
    ai.a("FlurryAgent", "Cache read, num images: " + this.e.a());
  }

  private void a(DataOutputStream paramDataOutputStream)
  {
    paramDataOutputStream.writeShort(2);
    paramDataOutputStream.writeLong(this.d);
    List localList1 = this.e.b();
    paramDataOutputStream.writeShort(localList1.size());
    Iterator localIterator1 = localList1.iterator();
    while (localIterator1.hasNext())
    {
      Map.Entry localEntry6 = (Map.Entry)localIterator1.next();
      paramDataOutputStream.writeLong(((Long)localEntry6.getKey()).longValue());
      AdImage localAdImage = (AdImage)localEntry6.getValue();
      paramDataOutputStream.writeLong(localAdImage.a);
      paramDataOutputStream.writeInt(localAdImage.b);
      paramDataOutputStream.writeInt(localAdImage.c);
      paramDataOutputStream.writeUTF(localAdImage.d);
      paramDataOutputStream.writeInt(localAdImage.e.length);
      paramDataOutputStream.write(localAdImage.e);
    }
    List localList2 = this.f.b();
    paramDataOutputStream.writeShort(localList2.size());
    Iterator localIterator2 = localList2.iterator();
    if (localIterator2.hasNext())
    {
      Map.Entry localEntry5 = (Map.Entry)localIterator2.next();
      paramDataOutputStream.writeLong(((Long)localEntry5.getKey()).longValue());
      am localam = (am)localEntry5.getValue();
      boolean bool1;
      if (localam.a != null)
      {
        bool1 = true;
        label248: paramDataOutputStream.writeBoolean(bool1);
        if (bool1)
          paramDataOutputStream.writeUTF(localam.a);
        if (localam.b == null)
          break label327;
      }
      label327: for (boolean bool2 = true; ; bool2 = false)
      {
        paramDataOutputStream.writeBoolean(bool2);
        if (bool2)
          paramDataOutputStream.writeUTF(localam.b);
        paramDataOutputStream.writeInt(localam.c);
        break;
        bool1 = false;
        break label248;
      }
    }
    paramDataOutputStream.writeShort(this.h.size());
    Iterator localIterator3 = this.h.entrySet().iterator();
    while (localIterator3.hasNext())
    {
      Map.Entry localEntry4 = (Map.Entry)localIterator3.next();
      paramDataOutputStream.writeUTF((String)localEntry4.getKey());
      e locale = (e)localEntry4.getValue();
      paramDataOutputStream.writeUTF(locale.a);
      paramDataOutputStream.writeByte(locale.b);
      paramDataOutputStream.writeByte(locale.c);
    }
    paramDataOutputStream.writeShort(this.g.size());
    Iterator localIterator4 = this.g.entrySet().iterator();
    if (localIterator4.hasNext())
    {
      Map.Entry localEntry3 = (Map.Entry)localIterator4.next();
      paramDataOutputStream.writeUTF((String)localEntry3.getKey());
      w[] arrayOfw = (w[])localEntry3.getValue();
      if (arrayOfw == null);
      for (int m = 0; ; m = arrayOfw.length)
      {
        paramDataOutputStream.writeShort(m);
        for (int n = 0; n < m; n++)
        {
          w localw = arrayOfw[n];
          paramDataOutputStream.writeLong(localw.a);
          paramDataOutputStream.writeLong(localw.b);
          paramDataOutputStream.writeUTF(localw.d);
          paramDataOutputStream.writeUTF(localw.c);
          paramDataOutputStream.writeLong(localw.e);
          paramDataOutputStream.writeLong(localw.f.longValue());
          paramDataOutputStream.writeByte(localw.g.length);
          paramDataOutputStream.write(localw.g);
        }
        break;
      }
    }
    paramDataOutputStream.writeShort(this.i.size());
    Iterator localIterator5 = this.i.entrySet().iterator();
    while (localIterator5.hasNext())
    {
      Map.Entry localEntry2 = (Map.Entry)localIterator5.next();
      paramDataOutputStream.writeByte(((Byte)localEntry2.getKey()).byteValue());
      ((c)localEntry2.getValue()).a(paramDataOutputStream);
    }
    paramDataOutputStream.writeShort(this.j.size());
    Iterator localIterator6 = this.j.entrySet().iterator();
    while (localIterator6.hasNext())
    {
      Map.Entry localEntry1 = (Map.Entry)localIterator6.next();
      paramDataOutputStream.writeShort(((Short)localEntry1.getKey()).shortValue());
      paramDataOutputStream.writeLong(((Long)localEntry1.getValue()).longValue());
    }
  }

  private static void a(File paramFile)
  {
    if (!paramFile.delete())
      ai.b("FlurryAgent", "Cannot delete cached ads");
  }

  private void f()
  {
    Iterator localIterator1 = this.i.values().iterator();
    while (localIterator1.hasNext())
      localIterator1.next();
    Iterator localIterator2 = this.g.values().iterator();
    while (localIterator2.hasNext())
    {
      w[] arrayOfw = (w[])localIterator2.next();
      if (arrayOfw == null)
        continue;
      int m = arrayOfw.length;
      for (int n = 0; n < m; n++)
      {
        w localw = arrayOfw[n];
        localw.h = b(localw.f.longValue());
        if (localw.h == null)
          ai.b("FlurryAgent", "Ad " + localw.d + " has no image");
        if (a(localw.a) != null)
          continue;
        ai.b("FlurryAgent", "Ad " + localw.d + " has no pricing");
      }
    }
    Iterator localIterator3 = this.h.values().iterator();
    while (localIterator3.hasNext())
    {
      e locale = (e)localIterator3.next();
      locale.d = a(locale.c);
      if (locale.d != null)
        continue;
      ai.d("FlurryAgent", "No ad theme found for " + locale.c);
    }
  }

  private String g()
  {
    return ".flurryappcircle." + Integer.toString(this.c.a.hashCode(), 16);
  }

  final AdImage a(short paramShort)
  {
    monitorenter;
    try
    {
      Long localLong = (Long)this.j.get(Short.valueOf(1));
      if (localLong == null);
      AdImage localAdImage;
      for (Object localObject2 = null; ; localObject2 = localAdImage)
      {
        return localObject2;
        localAdImage = b(localLong.longValue());
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  final am a(long paramLong)
  {
    monitorenter;
    try
    {
      am localam = (am)this.f.a(Long.valueOf(paramLong));
      monitorexit;
      return localam;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  final Set a()
  {
    monitorenter;
    try
    {
      Set localSet = this.e.c();
      monitorexit;
      return localSet;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  final void a(Context paramContext, v paramv, a parama)
  {
    this.a = paramContext;
    this.b = paramv;
    this.c = parama;
  }

  final void a(Map paramMap1, Map paramMap2, Map paramMap3, Map paramMap4, Map paramMap5, Map paramMap6)
  {
    monitorenter;
    try
    {
      this.d = System.currentTimeMillis();
      Iterator localIterator1 = paramMap4.entrySet().iterator();
      while (localIterator1.hasNext())
      {
        Map.Entry localEntry3 = (Map.Entry)localIterator1.next();
        if (localEntry3.getValue() == null)
          continue;
        this.e.a(localEntry3.getKey(), localEntry3.getValue());
      }
    }
    finally
    {
      monitorexit;
    }
    Iterator localIterator2 = paramMap5.entrySet().iterator();
    while (localIterator2.hasNext())
    {
      Map.Entry localEntry2 = (Map.Entry)localIterator2.next();
      if (localEntry2.getValue() == null)
        continue;
      this.f.a(localEntry2.getKey(), localEntry2.getValue());
    }
    if ((paramMap2 != null) && (!paramMap2.isEmpty()))
      this.h = paramMap2;
    if ((paramMap3 != null) && (!paramMap3.isEmpty()))
      this.i = paramMap3;
    if ((paramMap6 != null) && (!paramMap6.isEmpty()))
      this.j = paramMap6;
    this.g = new HashMap();
    Iterator localIterator3 = paramMap2.entrySet().iterator();
    while (localIterator3.hasNext())
    {
      Map.Entry localEntry1 = (Map.Entry)localIterator3.next();
      e locale = (e)localEntry1.getValue();
      w[] arrayOfw = (w[])paramMap1.get(Byte.valueOf(locale.b));
      if (arrayOfw != null)
        this.g.put(localEntry1.getKey(), arrayOfw);
      c localc = (c)paramMap3.get(Byte.valueOf(locale.c));
      if (localc == null)
        continue;
      locale.d = localc;
    }
    f();
    a(202);
    monitorexit;
  }

  final w[] a(String paramString)
  {
    monitorenter;
    try
    {
      w[] arrayOfw = (w[])this.g.get(paramString);
      if (arrayOfw == null)
        arrayOfw = (w[])this.g.get("");
      return arrayOfw;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  final AdImage b(long paramLong)
  {
    monitorenter;
    try
    {
      AdImage localAdImage = (AdImage)this.e.a(Long.valueOf(paramLong));
      monitorexit;
      return localAdImage;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  final e b(String paramString)
  {
    monitorenter;
    try
    {
      e locale = (e)this.h.get(paramString);
      if (locale == null)
        locale = (e)this.h.get("");
      return locale;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  final boolean b()
  {
    return this.k;
  }

  final long c()
  {
    return this.d;
  }

  // ERROR //
  final void d()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 360	com/flurry/android/aa:a	Landroid/content/Context;
    //   6: aload_0
    //   7: invokespecial 375	com/flurry/android/aa:g	()Ljava/lang/String;
    //   10: invokevirtual 381	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   13: astore_2
    //   14: aload_2
    //   15: invokevirtual 384	java/io/File:exists	()Z
    //   18: istore_3
    //   19: iload_3
    //   20: ifeq +102 -> 122
    //   23: new 85	java/io/DataInputStream
    //   26: dup
    //   27: new 386	java/io/FileInputStream
    //   30: dup
    //   31: aload_2
    //   32: invokespecial 388	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   35: invokespecial 391	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   38: astore 5
    //   40: aload 5
    //   42: invokevirtual 89	java/io/DataInputStream:readUnsignedShort	()I
    //   45: ldc_w 392
    //   48: if_icmpne +24 -> 72
    //   51: aload_0
    //   52: aload 5
    //   54: invokespecial 394	com/flurry/android/aa:a	(Ljava/io/DataInputStream;)V
    //   57: aload_0
    //   58: sipush 201
    //   61: invokespecial 367	com/flurry/android/aa:a	(I)V
    //   64: aload 5
    //   66: invokestatic 399	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   69: aload_0
    //   70: monitorexit
    //   71: return
    //   72: aload_2
    //   73: invokestatic 401	com/flurry/android/aa:a	(Ljava/io/File;)V
    //   76: goto -12 -> 64
    //   79: astore 7
    //   81: ldc 76
    //   83: ldc_w 403
    //   86: aload 7
    //   88: invokestatic 406	com/flurry/android/ai:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   91: pop
    //   92: aload_2
    //   93: invokestatic 401	com/flurry/android/aa:a	(Ljava/io/File;)V
    //   96: aload 5
    //   98: invokestatic 399	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   101: goto -32 -> 69
    //   104: astore_1
    //   105: aload_0
    //   106: monitorexit
    //   107: aload_1
    //   108: athrow
    //   109: astore 6
    //   111: aconst_null
    //   112: astore 5
    //   114: aload 5
    //   116: invokestatic 399	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   119: aload 6
    //   121: athrow
    //   122: ldc 76
    //   124: new 166	java/lang/StringBuilder
    //   127: dup
    //   128: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   131: ldc_w 408
    //   134: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: aload_2
    //   138: invokevirtual 411	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   141: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   147: invokestatic 413	com/flurry/android/ai:c	(Ljava/lang/String;Ljava/lang/String;)I
    //   150: pop
    //   151: goto -82 -> 69
    //   154: astore 6
    //   156: goto -42 -> 114
    //   159: astore 7
    //   161: aconst_null
    //   162: astore 5
    //   164: goto -83 -> 81
    //
    // Exception table:
    //   from	to	target	type
    //   40	64	79	java/lang/Throwable
    //   72	76	79	java/lang/Throwable
    //   2	19	104	finally
    //   64	69	104	finally
    //   96	101	104	finally
    //   114	122	104	finally
    //   122	151	104	finally
    //   23	40	109	finally
    //   40	64	154	finally
    //   72	76	154	finally
    //   81	96	154	finally
    //   23	40	159	java/lang/Throwable
  }

  // ERROR //
  final void e()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 360	com/flurry/android/aa:a	Landroid/content/Context;
    //   8: aload_0
    //   9: invokespecial 375	com/flurry/android/aa:g	()Ljava/lang/String;
    //   12: invokevirtual 381	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   15: astore 6
    //   17: aload 6
    //   19: invokevirtual 417	java/io/File:getParentFile	()Ljava/io/File;
    //   22: astore 7
    //   24: aload 7
    //   26: invokevirtual 420	java/io/File:mkdirs	()Z
    //   29: istore 8
    //   31: aconst_null
    //   32: astore_1
    //   33: iload 8
    //   35: ifne +45 -> 80
    //   38: aload 7
    //   40: invokevirtual 384	java/io/File:exists	()Z
    //   43: ifne +37 -> 80
    //   46: ldc 76
    //   48: new 166	java/lang/StringBuilder
    //   51: dup
    //   52: invokespecial 167	java/lang/StringBuilder:<init>	()V
    //   55: ldc_w 422
    //   58: invokevirtual 173	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: aload 7
    //   63: invokevirtual 425	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   66: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   69: invokestatic 306	com/flurry/android/ai:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   72: pop
    //   73: aconst_null
    //   74: invokestatic 399	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   77: aload_0
    //   78: monitorexit
    //   79: return
    //   80: new 184	java/io/DataOutputStream
    //   83: dup
    //   84: new 427	java/io/FileOutputStream
    //   87: dup
    //   88: aload 6
    //   90: invokespecial 428	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   93: invokespecial 431	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   96: astore 9
    //   98: aload 9
    //   100: ldc_w 392
    //   103: invokevirtual 187	java/io/DataOutputStream:writeShort	(I)V
    //   106: aload_0
    //   107: aload 9
    //   109: invokespecial 433	com/flurry/android/aa:a	(Ljava/io/DataOutputStream;)V
    //   112: aload 9
    //   114: invokestatic 399	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   117: goto -40 -> 77
    //   120: astore_3
    //   121: aload_0
    //   122: monitorexit
    //   123: aload_3
    //   124: athrow
    //   125: astore 4
    //   127: ldc 76
    //   129: ldc_w 370
    //   132: aload 4
    //   134: invokestatic 435	com/flurry/android/ai:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   137: pop
    //   138: aload_1
    //   139: invokestatic 399	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   142: goto -65 -> 77
    //   145: aload_1
    //   146: invokestatic 399	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   149: aload_2
    //   150: athrow
    //   151: astore_2
    //   152: aload 9
    //   154: astore_1
    //   155: goto -10 -> 145
    //   158: astore 4
    //   160: aload 9
    //   162: astore_1
    //   163: goto -36 -> 127
    //   166: astore_2
    //   167: goto -22 -> 145
    //
    // Exception table:
    //   from	to	target	type
    //   73	77	120	finally
    //   112	117	120	finally
    //   138	142	120	finally
    //   145	151	120	finally
    //   4	31	125	java/lang/Throwable
    //   38	73	125	java/lang/Throwable
    //   80	98	125	java/lang/Throwable
    //   98	112	151	finally
    //   98	112	158	java/lang/Throwable
    //   4	31	166	finally
    //   38	73	166	finally
    //   80	98	166	finally
    //   127	138	166	finally
  }

  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("{");
    localStringBuilder.append("adImages (" + this.e.b().size() + "),\n");
    localStringBuilder.append("adBlock (" + this.g.size() + "):").append(",\n");
    Iterator localIterator = this.g.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuilder.append("\t" + (String)localEntry.getKey() + ": " + Arrays.toString((Object[])localEntry.getValue()));
    }
    localStringBuilder.append("adHooks (" + this.h.size() + "):" + this.h).append(",\n");
    localStringBuilder.append("adThemes (" + this.i.size() + "):" + this.i).append(",\n");
    localStringBuilder.append("auxMap (" + this.j.size() + "):" + this.j).append(",\n");
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.aa
 * JD-Core Version:    0.6.0
 */