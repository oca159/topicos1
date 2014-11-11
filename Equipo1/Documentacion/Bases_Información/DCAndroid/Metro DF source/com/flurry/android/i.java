package com.flurry.android;

import java.util.Map;

final class i
{
  private int a = FlurryAgent.f();
  private String b;
  private Map c;
  private long d;
  private boolean e;
  private long f;

  public i(String paramString, Map paramMap, long paramLong, boolean paramBoolean)
  {
    this.b = paramString;
    this.c = paramMap;
    this.d = paramLong;
    this.e = paramBoolean;
  }

  public final void a(long paramLong)
  {
    this.f = (paramLong - this.d);
    ai.a("FlurryAgent", "Ended event '" + this.b + "' (" + this.d + ") after " + this.f + "ms");
  }

  public final boolean a(String paramString)
  {
    return (this.e) && (this.f == 0L) && (this.b.equals(paramString));
  }

  // ERROR //
  public final byte[] a()
  {
    // Byte code:
    //   0: new 79	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 80	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore_1
    //   8: new 82	java/io/DataOutputStream
    //   11: dup
    //   12: aload_1
    //   13: invokespecial 85	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   16: astore_2
    //   17: aload_2
    //   18: aload_0
    //   19: getfield 27	com/flurry/android/i:a	I
    //   22: invokevirtual 89	java/io/DataOutputStream:writeShort	(I)V
    //   25: aload_2
    //   26: aload_0
    //   27: getfield 29	com/flurry/android/i:b	Ljava/lang/String;
    //   30: invokevirtual 93	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   33: aload_0
    //   34: getfield 31	com/flurry/android/i:c	Ljava/util/Map;
    //   37: ifnonnull +41 -> 78
    //   40: aload_2
    //   41: iconst_0
    //   42: invokevirtual 89	java/io/DataOutputStream:writeShort	(I)V
    //   45: aload_2
    //   46: aload_0
    //   47: getfield 33	com/flurry/android/i:d	J
    //   50: invokevirtual 96	java/io/DataOutputStream:writeLong	(J)V
    //   53: aload_2
    //   54: aload_0
    //   55: getfield 38	com/flurry/android/i:f	J
    //   58: invokevirtual 96	java/io/DataOutputStream:writeLong	(J)V
    //   61: aload_2
    //   62: invokevirtual 99	java/io/DataOutputStream:flush	()V
    //   65: aload_1
    //   66: invokevirtual 102	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   69: astore 9
    //   71: aload_2
    //   72: invokestatic 107	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   75: aload 9
    //   77: areturn
    //   78: aload_2
    //   79: aload_0
    //   80: getfield 31	com/flurry/android/i:c	Ljava/util/Map;
    //   83: invokeinterface 112 1 0
    //   88: invokevirtual 89	java/io/DataOutputStream:writeShort	(I)V
    //   91: aload_0
    //   92: getfield 31	com/flurry/android/i:c	Ljava/util/Map;
    //   95: invokeinterface 116 1 0
    //   100: invokeinterface 122 1 0
    //   105: astore 7
    //   107: aload 7
    //   109: invokeinterface 128 1 0
    //   114: ifeq -69 -> 45
    //   117: aload 7
    //   119: invokeinterface 132 1 0
    //   124: checkcast 134	java/util/Map$Entry
    //   127: astore 8
    //   129: aload_2
    //   130: aload 8
    //   132: invokeinterface 137 1 0
    //   137: checkcast 70	java/lang/String
    //   140: sipush 255
    //   143: invokestatic 140	com/flurry/android/r:a	(Ljava/lang/String;I)Ljava/lang/String;
    //   146: invokevirtual 93	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   149: aload_2
    //   150: aload 8
    //   152: invokeinterface 143 1 0
    //   157: checkcast 70	java/lang/String
    //   160: sipush 255
    //   163: invokestatic 140	com/flurry/android/r:a	(Ljava/lang/String;I)Ljava/lang/String;
    //   166: invokevirtual 93	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   169: goto -62 -> 107
    //   172: astore 4
    //   174: aload_2
    //   175: astore 5
    //   177: iconst_0
    //   178: newarray byte
    //   180: astore 6
    //   182: aload 5
    //   184: invokestatic 107	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   187: aload 6
    //   189: areturn
    //   190: astore 11
    //   192: aconst_null
    //   193: astore_2
    //   194: aload 11
    //   196: astore_3
    //   197: aload_2
    //   198: invokestatic 107	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   201: aload_3
    //   202: athrow
    //   203: astore_3
    //   204: goto -7 -> 197
    //   207: astore_3
    //   208: aload 5
    //   210: astore_2
    //   211: goto -14 -> 197
    //   214: astore 10
    //   216: aconst_null
    //   217: astore 5
    //   219: goto -42 -> 177
    //
    // Exception table:
    //   from	to	target	type
    //   17	45	172	java/io/IOException
    //   45	71	172	java/io/IOException
    //   78	107	172	java/io/IOException
    //   107	169	172	java/io/IOException
    //   0	17	190	finally
    //   17	45	203	finally
    //   45	71	203	finally
    //   78	107	203	finally
    //   107	169	203	finally
    //   177	182	207	finally
    //   0	17	214	java/io/IOException
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.i
 * JD-Core Version:    0.6.0
 */