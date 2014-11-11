package com.flurry.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.view.View;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;

public final class FlurryAgent
  implements LocationListener
{
  static String a;
  private static final String[] b = { "9774d56d682e549c", "dead00beef" };
  private static volatile String c = null;
  private static volatile String d;
  private static volatile String e;
  private static volatile String f;
  private static volatile String g;
  private static final FlurryAgent h;
  private static long i;
  private static boolean j;
  private static boolean k;
  private static volatile String kInsecureReportUrl = "http://data.flurry.com/aap.do";
  private static volatile String kSecureReportUrl = "https://data.flurry.com/aap.do";
  private static boolean l;
  private static boolean m;
  private static Criteria n;
  private static boolean o;
  private static AppCircle p;
  private static AtomicInteger q;
  private static AtomicInteger r;
  private String A;
  private String B;
  private boolean C = true;
  private List D;
  private LocationManager E;
  private String F;
  private boolean G;
  private long H;
  private byte[] I;
  private List J = new ArrayList();
  private long K;
  private long L;
  private long M;
  private String N = "";
  private String O = "";
  private byte P = -1;
  private String Q = "";
  private byte R = -1;
  private Long S;
  private int T;
  private Location U;
  private Map V = new HashMap();
  private List W = new ArrayList();
  private boolean X;
  private int Y;
  private List Z = new ArrayList();
  private int aa;
  private v ab = new v();
  private final Handler s;
  private File t;
  private File u = null;
  private volatile boolean v = false;
  private volatile boolean w = false;
  private long x;
  private Map y = new WeakHashMap();
  private String z;

  static
  {
    d = null;
    e = "http://ad.flurry.com/getCanvas.do";
    f = null;
    g = "http://ad.flurry.com/getAndroidApp.do";
    h = new FlurryAgent();
    i = 10000L;
    j = true;
    k = false;
    l = false;
    m = true;
    n = null;
    o = false;
    p = new AppCircle();
    q = new AtomicInteger(0);
    r = new AtomicInteger(0);
  }

  private FlurryAgent()
  {
    HandlerThread localHandlerThread = new HandlerThread("FlurryAgent");
    localHandlerThread.start();
    this.s = new Handler(localHandlerThread.getLooper());
  }

  private static double a(double paramDouble)
  {
    return Math.round(paramDouble * 1000.0D) / 1000.0D;
  }

  static View a(Context paramContext, String paramString, int paramInt)
  {
    if (!o)
      return null;
    try
    {
      View localView = h.ab.a(paramContext, paramString, paramInt);
      return localView;
    }
    catch (Throwable localThrowable)
    {
      ai.b("FlurryAgent", "", localThrowable);
    }
    return null;
  }

  static Offer a(String paramString)
  {
    if (!o)
      return null;
    return h.ab.b(paramString);
  }

  private HttpClient a(HttpParams paramHttpParams)
  {
    try
    {
      KeyStore localKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      localKeyStore.load(null, null);
      aj localaj = new aj(this, localKeyStore);
      SchemeRegistry localSchemeRegistry = new SchemeRegistry();
      localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      localSchemeRegistry.register(new Scheme("https", localaj, 443));
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(paramHttpParams, localSchemeRegistry), paramHttpParams);
      return localDefaultHttpClient;
    }
    catch (Exception localException)
    {
    }
    return new DefaultHttpClient(paramHttpParams);
  }

  // ERROR //
  private void a(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: aload_1
    //   5: invokespecial 293	com/flurry/android/FlurryAgent:b	(Landroid/content/Context;)Ljava/lang/String;
    //   8: putfield 295	com/flurry/android/FlurryAgent:F	Ljava/lang/String;
    //   11: aload_0
    //   12: getfield 147	com/flurry/android/FlurryAgent:u	Ljava/io/File;
    //   15: invokevirtual 301	java/io/File:exists	()Z
    //   18: ifeq +326 -> 344
    //   21: ldc 193
    //   23: new 303	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 304	java/lang/StringBuilder:<init>	()V
    //   30: ldc_w 306
    //   33: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: aload_0
    //   37: getfield 147	com/flurry/android/FlurryAgent:u	Ljava/io/File;
    //   40: invokevirtual 313	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   43: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: invokevirtual 316	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: invokestatic 319	com/flurry/android/ai:c	(Ljava/lang/String;Ljava/lang/String;)I
    //   52: pop
    //   53: new 321	java/io/DataInputStream
    //   56: dup
    //   57: new 323	java/io/FileInputStream
    //   60: dup
    //   61: aload_0
    //   62: getfield 147	com/flurry/android/FlurryAgent:u	Ljava/io/File;
    //   65: invokespecial 326	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   68: invokespecial 329	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   71: astore 8
    //   73: aload 8
    //   75: invokevirtual 333	java/io/DataInputStream:readUnsignedShort	()I
    //   78: ldc_w 334
    //   81: if_icmpne +185 -> 266
    //   84: aload_0
    //   85: aload 8
    //   87: invokespecial 337	com/flurry/android/FlurryAgent:b	(Ljava/io/DataInputStream;)V
    //   90: aload 8
    //   92: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   95: aload_0
    //   96: getfield 151	com/flurry/android/FlurryAgent:w	Z
    //   99: ifne +22 -> 121
    //   102: aload_0
    //   103: getfield 147	com/flurry/android/FlurryAgent:u	Ljava/io/File;
    //   106: invokevirtual 345	java/io/File:delete	()Z
    //   109: ifeq +208 -> 317
    //   112: ldc 193
    //   114: ldc_w 347
    //   117: invokestatic 349	com/flurry/android/ai:a	(Ljava/lang/String;Ljava/lang/String;)I
    //   120: pop
    //   121: aload_0
    //   122: getfield 151	com/flurry/android/FlurryAgent:w	Z
    //   125: ifne +21 -> 146
    //   128: aload_0
    //   129: iconst_0
    //   130: putfield 351	com/flurry/android/FlurryAgent:G	Z
    //   133: aload_0
    //   134: aload_0
    //   135: getfield 353	com/flurry/android/FlurryAgent:K	J
    //   138: putfield 355	com/flurry/android/FlurryAgent:H	J
    //   141: aload_0
    //   142: iconst_1
    //   143: putfield 151	com/flurry/android/FlurryAgent:w	Z
    //   146: aload_0
    //   147: getfield 295	com/flurry/android/FlurryAgent:F	Ljava/lang/String;
    //   150: ifnonnull +70 -> 220
    //   153: invokestatic 359	java/lang/Math:random	()D
    //   156: invokestatic 364	java/lang/Double:doubleToLongBits	(D)J
    //   159: ldc2_w 365
    //   162: invokestatic 372	java/lang/System:nanoTime	()J
    //   165: bipush 37
    //   167: aload_0
    //   168: getfield 374	com/flurry/android/FlurryAgent:z	Ljava/lang/String;
    //   171: invokevirtual 377	java/lang/String:hashCode	()I
    //   174: imul
    //   175: i2l
    //   176: ladd
    //   177: lmul
    //   178: ladd
    //   179: lstore 4
    //   181: aload_0
    //   182: new 303	java/lang/StringBuilder
    //   185: dup
    //   186: invokespecial 304	java/lang/StringBuilder:<init>	()V
    //   189: ldc_w 379
    //   192: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: lload 4
    //   197: bipush 16
    //   199: invokestatic 384	java/lang/Long:toString	(JI)Ljava/lang/String;
    //   202: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: invokevirtual 316	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   208: putfield 295	com/flurry/android/FlurryAgent:F	Ljava/lang/String;
    //   211: ldc 193
    //   213: ldc_w 386
    //   216: invokestatic 319	com/flurry/android/ai:c	(Ljava/lang/String;Ljava/lang/String;)I
    //   219: pop
    //   220: aload_0
    //   221: getfield 189	com/flurry/android/FlurryAgent:ab	Lcom/flurry/android/v;
    //   224: aload_0
    //   225: getfield 295	com/flurry/android/FlurryAgent:F	Ljava/lang/String;
    //   228: invokevirtual 388	com/flurry/android/v:a	(Ljava/lang/String;)V
    //   231: aload_0
    //   232: getfield 295	com/flurry/android/FlurryAgent:F	Ljava/lang/String;
    //   235: ldc_w 390
    //   238: invokevirtual 394	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   241: ifne +22 -> 263
    //   244: aload_0
    //   245: getfield 396	com/flurry/android/FlurryAgent:t	Ljava/io/File;
    //   248: invokevirtual 301	java/io/File:exists	()Z
    //   251: ifne +12 -> 263
    //   254: aload_0
    //   255: aload_1
    //   256: aload_0
    //   257: getfield 295	com/flurry/android/FlurryAgent:F	Ljava/lang/String;
    //   260: invokespecial 399	com/flurry/android/FlurryAgent:c	(Landroid/content/Context;Ljava/lang/String;)V
    //   263: aload_0
    //   264: monitorexit
    //   265: return
    //   266: ldc 193
    //   268: ldc_w 401
    //   271: invokestatic 349	com/flurry/android/ai:a	(Ljava/lang/String;Ljava/lang/String;)I
    //   274: pop
    //   275: goto -185 -> 90
    //   278: astore 10
    //   280: ldc 193
    //   282: ldc_w 403
    //   285: aload 10
    //   287: invokestatic 229	com/flurry/android/ai:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   290: pop
    //   291: aload 8
    //   293: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   296: goto -201 -> 95
    //   299: astore_2
    //   300: aload_0
    //   301: monitorexit
    //   302: aload_2
    //   303: athrow
    //   304: astore 9
    //   306: aconst_null
    //   307: astore 8
    //   309: aload 8
    //   311: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   314: aload 9
    //   316: athrow
    //   317: ldc 193
    //   319: ldc_w 405
    //   322: invokestatic 407	com/flurry/android/ai:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   325: pop
    //   326: goto -205 -> 121
    //   329: astore 12
    //   331: ldc 193
    //   333: ldc 165
    //   335: aload 12
    //   337: invokestatic 229	com/flurry/android/ai:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   340: pop
    //   341: goto -220 -> 121
    //   344: ldc 193
    //   346: ldc_w 409
    //   349: invokestatic 319	com/flurry/android/ai:c	(Ljava/lang/String;Ljava/lang/String;)I
    //   352: pop
    //   353: goto -232 -> 121
    //   356: astore 9
    //   358: goto -49 -> 309
    //   361: astore 10
    //   363: aconst_null
    //   364: astore 8
    //   366: goto -86 -> 280
    //
    // Exception table:
    //   from	to	target	type
    //   73	90	278	java/lang/Throwable
    //   266	275	278	java/lang/Throwable
    //   2	53	299	finally
    //   90	95	299	finally
    //   95	121	299	finally
    //   121	146	299	finally
    //   146	220	299	finally
    //   220	263	299	finally
    //   291	296	299	finally
    //   309	317	299	finally
    //   317	326	299	finally
    //   331	341	299	finally
    //   344	353	299	finally
    //   53	73	304	finally
    //   95	121	329	java/lang/Throwable
    //   317	326	329	java/lang/Throwable
    //   73	90	356	finally
    //   266	275	356	finally
    //   280	291	356	finally
    //   53	73	361	java/lang/Throwable
  }

  static void a(Context paramContext, long paramLong)
  {
    if (!o)
      ai.d("FlurryAgent", "Cannot accept Offer. AppCircle is not enabled");
    h.ab.a(paramContext, paramLong);
  }

  static void a(Context paramContext, String paramString)
  {
    if (!o)
      return;
    h.ab.a(paramContext, paramString);
  }

  private void a(Context paramContext, boolean paramBoolean)
  {
    monitorenter;
    if (paramContext != null);
    try
    {
      if ((Context)this.y.remove(paramContext) == null)
        ai.d("FlurryAgent", "onEndSession called without context from corresponding onStartSession");
      if ((this.v) && (this.y.isEmpty()))
      {
        ai.a("FlurryAgent", "Ending session");
        o();
        if (paramContext != null)
          break label194;
      }
      label194: Context localContext;
      for (Object localObject2 = null; ; localObject2 = localContext)
      {
        if (paramContext != null)
        {
          String str = ((Context)localObject2).getPackageName();
          if (!this.A.equals(str))
            ai.b("FlurryAgent", "onEndSession called from different application package, expected: " + this.A + " actual: " + str);
        }
        long l1 = SystemClock.elapsedRealtime();
        this.x = l1;
        this.M = (l1 - this.L);
        if (this.F == null)
          ai.b("FlurryAgent", "Not creating report because of bad Android ID or generated ID is null");
        a(new b(this, paramBoolean, (Context)localObject2));
        this.v = false;
        return;
        localContext = paramContext.getApplicationContext();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  static void a(AppCircleCallback paramAppCircleCallback)
  {
    h.ab.a(paramAppCircleCallback);
  }

  private void a(DataInputStream paramDataInputStream)
  {
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap3 = new HashMap();
    HashMap localHashMap4 = new HashMap();
    HashMap localHashMap5 = new HashMap();
    HashMap localHashMap6 = new HashMap();
    int i1 = paramDataInputStream.readUnsignedShort();
    int i2 = paramDataInputStream.readInt();
    switch (i1)
    {
    case 260:
    case 261:
    case 265:
    case 267:
    default:
      ai.a("FlurryAgent", "Unknown chunkType: " + i1);
      paramDataInputStream.skipBytes(i2);
    case 264:
    case 258:
    case 259:
    case 262:
    case 263:
    case 270:
    case 266:
    case 271:
    case 268:
    case 272:
    case 269:
    case 273:
    }
    while (i1 == 264)
    {
      if (o)
      {
        if (localHashMap1.isEmpty())
          ai.a("FlurryAgent", "No ads from server");
        this.ab.a(localHashMap1, localHashMap4, localHashMap5, localHashMap2, localHashMap3, localHashMap6);
      }
      return;
      paramDataInputStream.readInt();
      continue;
      byte b1 = paramDataInputStream.readByte();
      int i13 = paramDataInputStream.readUnsignedShort();
      w[] arrayOfw = new w[i13];
      for (int i14 = 0; i14 < i13; i14++)
        arrayOfw[i14] = new w(paramDataInputStream);
      localHashMap1.put(Byte.valueOf(b1), arrayOfw);
      continue;
      int i11 = paramDataInputStream.readUnsignedShort();
      for (int i12 = 0; i12 < i11; i12++)
      {
        AdImage localAdImage = new AdImage(paramDataInputStream);
        localHashMap2.put(Long.valueOf(localAdImage.a), localAdImage);
      }
      int i9 = paramDataInputStream.readInt();
      for (int i10 = 0; i10 < i9; i10++)
      {
        e locale = new e(paramDataInputStream);
        localHashMap4.put(locale.a, locale);
        ai.a("FlurryAgent", "Parsed hook: " + locale);
      }
      paramDataInputStream.skipBytes(i2);
      continue;
      int i7 = paramDataInputStream.readByte();
      for (int i8 = 0; i8 < i7; i8++)
      {
        c localc2 = new c(paramDataInputStream);
        localHashMap5.put(Byte.valueOf(localc2.a), localc2);
      }
      int i5 = paramDataInputStream.readByte();
      for (int i6 = 0; i6 < i5; i6++)
      {
        c localc1 = (c)localHashMap5.get(Byte.valueOf(paramDataInputStream.readByte()));
        if (localc1 == null)
          continue;
        localc1.a(paramDataInputStream);
      }
      int i3 = paramDataInputStream.readInt();
      for (int i4 = 0; i4 < i3; i4++)
      {
        long l2 = paramDataInputStream.readLong();
        localHashMap6.put(Short.valueOf(paramDataInputStream.readShort()), Long.valueOf(l2));
      }
      long l1 = paramDataInputStream.readLong();
      am localam = (am)localHashMap3.get(Long.valueOf(l1));
      if (localam == null)
        localam = new am();
      localam.a = paramDataInputStream.readUTF();
      localam.c = paramDataInputStream.readInt();
      localHashMap3.put(Long.valueOf(l1), localam);
      continue;
      paramDataInputStream.skipBytes(i2);
      continue;
      paramDataInputStream.skipBytes(i2);
    }
  }

  private void a(Runnable paramRunnable)
  {
    this.s.post(paramRunnable);
  }

  private void a(String paramString1, String paramString2, String paramString3)
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (this.Z != null)
          continue;
        ai.b("FlurryAgent", "onError called before onStartSession.  Error: " + paramString1);
        return;
        this.T = (1 + this.T);
        if (this.Z.size() < 10)
        {
          ab localab = new ab();
          localab.b = System.currentTimeMillis();
          localab.c = r.a(paramString1, 255);
          localab.d = r.a(paramString2, 512);
          localab.e = r.a(paramString3, 255);
          this.Z.add(localab);
          ai.a("FlurryAgent", "Error logged: " + localab.c);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      ai.a("FlurryAgent", "Max errors logged. No more errors logged.");
    }
  }

  private void a(String paramString, Map paramMap, boolean paramBoolean)
  {
    monitorenter;
    while (true)
    {
      long l1;
      String str;
      g localg1;
      try
      {
        if (this.W != null)
          continue;
        ai.b("FlurryAgent", "onEvent called before onStartSession.  Event: " + paramString);
        return;
        l1 = SystemClock.elapsedRealtime() - this.L;
        str = r.a(paramString, 255);
        if (str.length() == 0)
          continue;
        localg1 = (g)this.V.get(str);
        if (localg1 != null)
          break label296;
        if (this.V.size() < 100)
        {
          g localg2 = new g();
          localg2.a = 1;
          this.V.put(str, localg2);
          ai.a("FlurryAgent", "Event count incremented: " + str);
          if ((!j) || (this.W.size() >= 200) || (this.Y >= 16000))
            break label452;
          if (paramMap != null)
            break label460;
          localMap = Collections.emptyMap();
          if (localMap.size() <= 10)
            break label338;
          if (!ai.a("FlurryAgent"))
            continue;
          ai.a("FlurryAgent", "MaxEventParams exceeded: " + localMap.size());
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      if (!ai.a("FlurryAgent"))
        continue;
      ai.a("FlurryAgent", "Too many different events. Event not counted: " + str);
      continue;
      label296: localg1.a = (1 + localg1.a);
      ai.a("FlurryAgent", "Event count incremented: " + str);
      continue;
      label338: i locali = new i(str, localMap, l1, paramBoolean);
      if (locali.a().length + this.Y <= 16000)
      {
        this.W.add(locali);
        this.Y += locali.a().length;
        ai.a("FlurryAgent", "Logged event: " + str);
        continue;
      }
      this.Y = 16000;
      this.X = false;
      ai.a("FlurryAgent", "Event Log size exceeded. No more event details logged.");
      continue;
      label452: this.X = false;
      continue;
      label460: Map localMap = paramMap;
    }
  }

  static void a(List paramList)
  {
    if (!o)
      return;
    h.ab.a(paramList);
  }

  static void a(boolean paramBoolean)
  {
    if (!o)
      return;
    h.ab.a(paramBoolean);
  }

  static boolean a()
  {
    return h.ab.h();
  }

  private static boolean a(File paramFile)
  {
    File localFile = paramFile.getParentFile();
    if ((!localFile.mkdirs()) && (!localFile.exists()))
    {
      ai.b("FlurryAgent", "Unable to create persistent dir: " + localFile);
      return false;
    }
    return true;
  }

  private boolean a(byte[] paramArrayOfByte)
  {
    String str1 = m();
    int i1;
    if (str1 == null)
      i1 = 0;
    while (true)
    {
      return i1;
      try
      {
        boolean bool2 = a(paramArrayOfByte, str1);
        i1 = bool2;
        if ((i1 != 0) || (c != null) || (!k) || (l))
          continue;
      }
      catch (Exception localException1)
      {
        String str2;
        synchronized (h)
        {
          while (true)
          {
            l = true;
            str2 = m();
            if (str2 != null)
              break;
            return false;
            localException1 = localException1;
            ai.a("FlurryAgent", "Sending report exception: " + localException1.getMessage());
            i1 = 0;
          }
        }
        try
        {
          boolean bool1 = a(paramArrayOfByte, str2);
          return bool1;
          localObject = finally;
          monitorexit;
          throw localObject;
        }
        catch (Exception localException2)
        {
        }
      }
    }
    return i1;
  }

  // ERROR //
  private boolean a(byte[] paramArrayOfByte, String paramString)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: ldc_w 691
    //   5: aload_2
    //   6: invokevirtual 445	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   9: ifeq +5 -> 14
    //   12: iload_3
    //   13: ireturn
    //   14: ldc 193
    //   16: new 303	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 304	java/lang/StringBuilder:<init>	()V
    //   23: ldc_w 693
    //   26: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: aload_2
    //   30: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: invokevirtual 316	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   36: invokestatic 349	com/flurry/android/ai:a	(Ljava/lang/String;Ljava/lang/String;)I
    //   39: pop
    //   40: new 695	org/apache/http/entity/ByteArrayEntity
    //   43: dup
    //   44: aload_1
    //   45: invokespecial 698	org/apache/http/entity/ByteArrayEntity:<init>	([B)V
    //   48: astore 5
    //   50: aload 5
    //   52: ldc_w 700
    //   55: invokevirtual 703	org/apache/http/entity/ByteArrayEntity:setContentType	(Ljava/lang/String;)V
    //   58: new 705	org/apache/http/client/methods/HttpPost
    //   61: dup
    //   62: aload_2
    //   63: invokespecial 706	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   66: astore 6
    //   68: aload 6
    //   70: aload 5
    //   72: invokevirtual 710	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   75: new 712	org/apache/http/params/BasicHttpParams
    //   78: dup
    //   79: invokespecial 713	org/apache/http/params/BasicHttpParams:<init>	()V
    //   82: astore 7
    //   84: aload 7
    //   86: sipush 10000
    //   89: invokestatic 719	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   92: aload 7
    //   94: sipush 15000
    //   97: invokestatic 722	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   100: aload 6
    //   102: invokevirtual 726	org/apache/http/client/methods/HttpPost:getParams	()Lorg/apache/http/params/HttpParams;
    //   105: ldc_w 728
    //   108: iconst_0
    //   109: invokeinterface 734 3 0
    //   114: pop
    //   115: aload_0
    //   116: aload 7
    //   118: invokespecial 736	com/flurry/android/FlurryAgent:a	(Lorg/apache/http/params/HttpParams;)Lorg/apache/http/client/HttpClient;
    //   121: aload 6
    //   123: invokeinterface 742 2 0
    //   128: astore 9
    //   130: aload 9
    //   132: invokeinterface 748 1 0
    //   137: invokeinterface 753 1 0
    //   142: istore 10
    //   144: aload_0
    //   145: monitorenter
    //   146: iload 10
    //   148: sipush 200
    //   151: if_icmpne +123 -> 274
    //   154: ldc 193
    //   156: ldc_w 755
    //   159: invokestatic 349	com/flurry/android/ai:a	(Ljava/lang/String;Ljava/lang/String;)I
    //   162: pop
    //   163: aload_0
    //   164: iconst_1
    //   165: putfield 351	com/flurry/android/FlurryAgent:G	Z
    //   168: aload_0
    //   169: getfield 163	com/flurry/android/FlurryAgent:J	Ljava/util/List;
    //   172: aload_0
    //   173: getfield 757	com/flurry/android/FlurryAgent:D	Ljava/util/List;
    //   176: invokeinterface 761 2 0
    //   181: pop
    //   182: aload 9
    //   184: invokeinterface 765 1 0
    //   189: astore 15
    //   191: ldc 193
    //   193: ldc_w 767
    //   196: invokestatic 349	com/flurry/android/ai:a	(Ljava/lang/String;Ljava/lang/String;)I
    //   199: pop
    //   200: aload 15
    //   202: ifnull +44 -> 246
    //   205: aload 15
    //   207: invokeinterface 772 1 0
    //   212: lstore 17
    //   214: lload 17
    //   216: lconst_0
    //   217: lcmp
    //   218: ifeq +28 -> 246
    //   221: aload_0
    //   222: new 321	java/io/DataInputStream
    //   225: dup
    //   226: aload 15
    //   228: invokeinterface 776 1 0
    //   233: invokespecial 329	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   236: invokespecial 778	com/flurry/android/FlurryAgent:a	(Ljava/io/DataInputStream;)V
    //   239: aload 15
    //   241: invokeinterface 781 1 0
    //   246: aload_0
    //   247: aconst_null
    //   248: putfield 757	com/flurry/android/FlurryAgent:D	Ljava/util/List;
    //   251: aload_0
    //   252: monitorexit
    //   253: iload_3
    //   254: ireturn
    //   255: astore 12
    //   257: aload_0
    //   258: monitorexit
    //   259: aload 12
    //   261: athrow
    //   262: astore 19
    //   264: aload 15
    //   266: invokeinterface 781 1 0
    //   271: aload 19
    //   273: athrow
    //   274: ldc 193
    //   276: new 303	java/lang/StringBuilder
    //   279: dup
    //   280: invokespecial 304	java/lang/StringBuilder:<init>	()V
    //   283: ldc_w 783
    //   286: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: iload 10
    //   291: invokevirtual 509	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   294: invokevirtual 316	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   297: invokestatic 349	com/flurry/android/ai:a	(Ljava/lang/String;Ljava/lang/String;)I
    //   300: pop
    //   301: iconst_0
    //   302: istore_3
    //   303: goto -57 -> 246
    //
    // Exception table:
    //   from	to	target	type
    //   154	200	255	finally
    //   205	214	255	finally
    //   239	246	255	finally
    //   246	253	255	finally
    //   257	259	255	finally
    //   264	274	255	finally
    //   274	301	255	finally
    //   221	239	262	finally
  }

  public static void addUserCookie(String paramString1, String paramString2)
  {
    if (!o)
      return;
    h.ab.a(paramString1, paramString2);
  }

  static v b()
  {
    return h.ab;
  }

  // ERROR //
  private String b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 295	com/flurry/android/FlurryAgent:F	Ljava/lang/String;
    //   4: ifnull +12 -> 16
    //   7: aload_0
    //   8: getfield 295	com/flurry/android/FlurryAgent:F	Ljava/lang/String;
    //   11: astore 6
    //   13: aload 6
    //   15: areturn
    //   16: aload_1
    //   17: invokevirtual 792	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: ldc_w 794
    //   23: invokestatic 800	android/provider/Settings$System:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   26: astore_2
    //   27: iconst_0
    //   28: istore_3
    //   29: aload_2
    //   30: ifnull +32 -> 62
    //   33: aload_2
    //   34: invokevirtual 624	java/lang/String:length	()I
    //   37: istore 14
    //   39: iconst_0
    //   40: istore_3
    //   41: iload 14
    //   43: ifle +19 -> 62
    //   46: aload_2
    //   47: ldc_w 802
    //   50: invokevirtual 445	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   53: istore 15
    //   55: iconst_0
    //   56: istore_3
    //   57: iload 15
    //   59: ifeq +28 -> 87
    //   62: iload_3
    //   63: ifeq +73 -> 136
    //   66: new 303	java/lang/StringBuilder
    //   69: dup
    //   70: invokespecial 304	java/lang/StringBuilder:<init>	()V
    //   73: ldc_w 390
    //   76: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: aload_2
    //   80: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: invokevirtual 316	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: areturn
    //   87: getstatic 87	com/flurry/android/FlurryAgent:b	[Ljava/lang/String;
    //   90: astore 16
    //   92: aload 16
    //   94: arraylength
    //   95: istore 17
    //   97: iconst_0
    //   98: istore 18
    //   100: iload 18
    //   102: iload 17
    //   104: if_icmpge +27 -> 131
    //   107: aload_2
    //   108: aload 16
    //   110: iload 18
    //   112: aaload
    //   113: invokevirtual 445	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   116: istore 19
    //   118: iconst_0
    //   119: istore_3
    //   120: iload 19
    //   122: ifne -60 -> 62
    //   125: iinc 18 1
    //   128: goto -28 -> 100
    //   131: iconst_1
    //   132: istore_3
    //   133: goto -71 -> 62
    //   136: aload_1
    //   137: ldc_w 804
    //   140: invokevirtual 808	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   143: astore 4
    //   145: aload 4
    //   147: invokevirtual 301	java/io/File:exists	()Z
    //   150: istore 5
    //   152: aconst_null
    //   153: astore 6
    //   155: iload 5
    //   157: ifeq -144 -> 13
    //   160: new 321	java/io/DataInputStream
    //   163: dup
    //   164: new 323	java/io/FileInputStream
    //   167: dup
    //   168: aload 4
    //   170: invokespecial 326	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   173: invokespecial 329	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   176: astore 7
    //   178: aload 7
    //   180: invokevirtual 504	java/io/DataInputStream:readInt	()I
    //   183: pop
    //   184: aload 7
    //   186: invokevirtual 580	java/io/DataInputStream:readUTF	()Ljava/lang/String;
    //   189: astore 12
    //   191: aload 7
    //   193: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   196: aload 12
    //   198: areturn
    //   199: astore 8
    //   201: aconst_null
    //   202: astore 7
    //   204: ldc 193
    //   206: ldc_w 810
    //   209: aload 8
    //   211: invokestatic 229	com/flurry/android/ai:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   214: pop
    //   215: aload 7
    //   217: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   220: aconst_null
    //   221: areturn
    //   222: astore 13
    //   224: aconst_null
    //   225: astore 7
    //   227: aload 13
    //   229: astore 9
    //   231: aload 7
    //   233: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   236: aload 9
    //   238: athrow
    //   239: astore 9
    //   241: goto -10 -> 231
    //   244: astore 8
    //   246: goto -42 -> 204
    //
    // Exception table:
    //   from	to	target	type
    //   160	178	199	java/lang/Throwable
    //   160	178	222	finally
    //   178	191	239	finally
    //   204	215	239	finally
    //   178	191	244	java/lang/Throwable
  }

  static List b(String paramString)
  {
    if (!o)
      return null;
    return h.ab.c(paramString);
  }

  private void b(Context paramContext, String paramString)
  {
    monitorenter;
    try
    {
      if ((this.z != null) && (!this.z.equals(paramString)))
        ai.b("FlurryAgent", "onStartSession called with different api keys: " + this.z + " and " + paramString);
      if ((Context)this.y.put(paramContext, paramContext) != null)
        ai.d("FlurryAgent", "onStartSession called with duplicate context, use a specific Activity or Service as context instead of using a global context");
      String str2;
      if (!this.v)
      {
        ai.a("FlurryAgent", "Initializing Flurry session");
        q.set(0);
        r.set(0);
        this.z = paramString;
        this.u = paramContext.getFileStreamPath(".flurryagent." + Integer.toString(this.z.hashCode(), 16));
        this.t = paramContext.getFileStreamPath(".flurryb.");
        if (m)
          Thread.setDefaultUncaughtExceptionHandler(new FlurryAgent.FlurryDefaultExceptionHandler());
        Context localContext = paramContext.getApplicationContext();
        if (this.B == null)
          this.B = c(localContext);
        String str1 = localContext.getPackageName();
        if ((this.A != null) && (!this.A.equals(str1)))
          ai.b("FlurryAgent", "onStartSession called from different application packages: " + this.A + " and " + str1);
        this.A = str1;
        long l1 = SystemClock.elapsedRealtime();
        if (l1 - this.x <= i)
          break label595;
        ai.a("FlurryAgent", "New session");
        this.K = System.currentTimeMillis();
        this.L = l1;
        this.M = -1L;
        this.Q = "";
        this.T = 0;
        this.U = null;
        this.O = TimeZone.getDefault().getID();
        this.N = (Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry());
        this.V = new HashMap();
        this.W = new ArrayList();
        this.X = true;
        this.Z = new ArrayList();
        this.Y = 0;
        this.aa = 0;
        if (o)
        {
          if (!this.ab.a())
          {
            ai.a("FlurryAgent", "Initializing AppCircle");
            a locala = new a();
            locala.a = this.z;
            locala.b = this.H;
            if (d == null)
              break label587;
            str2 = d;
            locala.c = str2;
            locala.d = c();
            locala.e = this.s;
            this.ab.a(paramContext, locala);
            ai.a("FlurryAgent", "AppCircle initialized");
          }
          this.ab.a(this.K, this.L);
        }
        a(new d(this, localContext, this.C));
      }
      while (true)
      {
        this.v = true;
        return;
        label587: str2 = e;
        break;
        label595: ai.a("FlurryAgent", "Continuing previous session");
        if (this.J.isEmpty())
          continue;
        this.J.remove(-1 + this.J.size());
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void b(DataInputStream paramDataInputStream)
  {
    int i1 = 0;
    monitorenter;
    int i2;
    try
    {
      i2 = paramDataInputStream.readUnsignedShort();
      if (i2 > 2)
      {
        ai.b("FlurryAgent", "Unknown agent file version: " + i2);
        throw new IOException("Unknown agent file version: " + i2);
      }
    }
    finally
    {
      monitorexit;
    }
    String str1;
    if (i2 >= 2)
    {
      str1 = paramDataInputStream.readUTF();
      ai.a("FlurryAgent", "Loading API key: " + d(this.z));
      if (str1.equals(this.z))
      {
        String str2 = paramDataInputStream.readUTF();
        if (this.F == null)
          ai.a("FlurryAgent", "Loading phoneId: " + str2);
        this.F = str2;
        this.G = paramDataInputStream.readBoolean();
        this.H = paramDataInputStream.readLong();
        ai.a("FlurryAgent", "Loading session reports");
        while (true)
        {
          int i3 = paramDataInputStream.readUnsignedShort();
          if (i3 == 0)
            break;
          byte[] arrayOfByte = new byte[i3];
          paramDataInputStream.readFully(arrayOfByte);
          this.J.add(0, arrayOfByte);
          StringBuilder localStringBuilder = new StringBuilder().append("Session report added: ");
          i1++;
          ai.a("FlurryAgent", i1);
        }
        ai.a("FlurryAgent", "Persistent file loaded");
        this.w = true;
      }
    }
    while (true)
    {
      monitorexit;
      return;
      ai.a("FlurryAgent", "Api keys do not match, old: " + d(str1) + ", new: " + d(this.z));
      continue;
      ai.d("FlurryAgent", "Deleting old file version: " + i2);
    }
  }

  // ERROR //
  private byte[] b(boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: new 946	com/flurry/android/CrcMessageDigest
    //   7: dup
    //   8: invokespecial 947	com/flurry/android/CrcMessageDigest:<init>	()V
    //   11: astore_3
    //   12: new 949	java/io/ByteArrayOutputStream
    //   15: dup
    //   16: invokespecial 950	java/io/ByteArrayOutputStream:<init>	()V
    //   19: astore 4
    //   21: new 952	java/security/DigestOutputStream
    //   24: dup
    //   25: aload 4
    //   27: aload_3
    //   28: invokespecial 955	java/security/DigestOutputStream:<init>	(Ljava/io/OutputStream;Ljava/security/MessageDigest;)V
    //   31: astore 5
    //   33: new 957	java/io/DataOutputStream
    //   36: dup
    //   37: aload 5
    //   39: invokespecial 960	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   42: astore 6
    //   44: aload 6
    //   46: bipush 22
    //   48: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   51: getstatic 130	com/flurry/android/FlurryAgent:o	Z
    //   54: ifeq +128 -> 182
    //   57: iload_1
    //   58: ifeq +124 -> 182
    //   61: aload 6
    //   63: iconst_1
    //   64: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   67: getstatic 130	com/flurry/android/FlurryAgent:o	Z
    //   70: ifeq +138 -> 208
    //   73: aload 6
    //   75: aload_0
    //   76: getfield 189	com/flurry/android/FlurryAgent:ab	Lcom/flurry/android/v;
    //   79: invokevirtual 965	com/flurry/android/v:d	()J
    //   82: invokevirtual 969	java/io/DataOutputStream:writeLong	(J)V
    //   85: aload_0
    //   86: getfield 189	com/flurry/android/FlurryAgent:ab	Lcom/flurry/android/v;
    //   89: invokevirtual 972	com/flurry/android/v:e	()Ljava/util/Set;
    //   92: astore 16
    //   94: aload 6
    //   96: aload 16
    //   98: invokeinterface 975 1 0
    //   103: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   106: aload 16
    //   108: invokeinterface 979 1 0
    //   113: astore 17
    //   115: aload 17
    //   117: invokeinterface 984 1 0
    //   122: ifeq +98 -> 220
    //   125: aload 17
    //   127: invokeinterface 988 1 0
    //   132: checkcast 381	java/lang/Long
    //   135: invokevirtual 991	java/lang/Long:longValue	()J
    //   138: lstore 18
    //   140: aload 6
    //   142: iconst_1
    //   143: invokevirtual 994	java/io/DataOutputStream:writeByte	(I)V
    //   146: aload 6
    //   148: lload 18
    //   150: invokevirtual 969	java/io/DataOutputStream:writeLong	(J)V
    //   153: goto -38 -> 115
    //   156: astore 9
    //   158: ldc 193
    //   160: ldc_w 996
    //   163: aload 9
    //   165: invokestatic 229	com/flurry/android/ai:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   168: pop
    //   169: aload 6
    //   171: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   174: aconst_null
    //   175: astore 11
    //   177: aload_0
    //   178: monitorexit
    //   179: aload 11
    //   181: areturn
    //   182: aload 6
    //   184: iconst_0
    //   185: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   188: goto -121 -> 67
    //   191: astore 7
    //   193: aload 6
    //   195: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   198: aload 7
    //   200: athrow
    //   201: astore 8
    //   203: aload_0
    //   204: monitorexit
    //   205: aload 8
    //   207: athrow
    //   208: aload 6
    //   210: lconst_0
    //   211: invokevirtual 969	java/io/DataOutputStream:writeLong	(J)V
    //   214: aload 6
    //   216: iconst_0
    //   217: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   220: aload 6
    //   222: iconst_3
    //   223: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   226: aload 6
    //   228: bipush 122
    //   230: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   233: aload 6
    //   235: invokestatic 603	java/lang/System:currentTimeMillis	()J
    //   238: invokevirtual 969	java/io/DataOutputStream:writeLong	(J)V
    //   241: aload 6
    //   243: aload_0
    //   244: getfield 374	com/flurry/android/FlurryAgent:z	Ljava/lang/String;
    //   247: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   250: aload 6
    //   252: aload_0
    //   253: getfield 842	com/flurry/android/FlurryAgent:B	Ljava/lang/String;
    //   256: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   259: aload_0
    //   260: getfield 493	com/flurry/android/FlurryAgent:I	[B
    //   263: ifnonnull +336 -> 599
    //   266: iconst_1
    //   267: istore 12
    //   269: aload 6
    //   271: iload 12
    //   273: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   276: aload 6
    //   278: iconst_0
    //   279: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   282: aload 6
    //   284: aload_0
    //   285: getfield 295	com/flurry/android/FlurryAgent:F	Ljava/lang/String;
    //   288: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   291: iload 12
    //   293: iconst_1
    //   294: if_icmple +60 -> 354
    //   297: aload 6
    //   299: iconst_5
    //   300: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   303: aload 6
    //   305: aload_0
    //   306: getfield 493	com/flurry/android/FlurryAgent:I	[B
    //   309: arraylength
    //   310: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   313: aload 6
    //   315: aload_0
    //   316: getfield 493	com/flurry/android/FlurryAgent:I	[B
    //   319: invokevirtual 1002	java/io/DataOutputStream:write	([B)V
    //   322: ldc 193
    //   324: new 303	java/lang/StringBuilder
    //   327: dup
    //   328: invokespecial 304	java/lang/StringBuilder:<init>	()V
    //   331: ldc_w 1004
    //   334: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: aload_0
    //   338: getfield 493	com/flurry/android/FlurryAgent:I	[B
    //   341: invokestatic 1009	java/util/Arrays:toString	([B)Ljava/lang/String;
    //   344: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   347: invokevirtual 316	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   350: invokestatic 319	com/flurry/android/ai:c	(Ljava/lang/String;Ljava/lang/String;)I
    //   353: pop
    //   354: aload 6
    //   356: iconst_0
    //   357: invokevirtual 1011	java/io/DataOutputStream:write	(I)V
    //   360: aload 6
    //   362: aload_0
    //   363: getfield 355	com/flurry/android/FlurryAgent:H	J
    //   366: invokevirtual 969	java/io/DataOutputStream:writeLong	(J)V
    //   369: aload 6
    //   371: aload_0
    //   372: getfield 353	com/flurry/android/FlurryAgent:K	J
    //   375: invokevirtual 969	java/io/DataOutputStream:writeLong	(J)V
    //   378: aload 6
    //   380: bipush 6
    //   382: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   385: aload 6
    //   387: ldc_w 1013
    //   390: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   393: aload 6
    //   395: getstatic 1018	android/os/Build:MODEL	Ljava/lang/String;
    //   398: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   401: aload 6
    //   403: ldc_w 1020
    //   406: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   409: aload 6
    //   411: getstatic 1023	android/os/Build:BRAND	Ljava/lang/String;
    //   414: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   417: aload 6
    //   419: ldc_w 1025
    //   422: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   425: aload 6
    //   427: getstatic 1027	android/os/Build:ID	Ljava/lang/String;
    //   430: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   433: aload 6
    //   435: ldc_w 1029
    //   438: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   441: aload 6
    //   443: getstatic 1034	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   446: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   449: aload 6
    //   451: ldc_w 1036
    //   454: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   457: aload 6
    //   459: getstatic 1039	android/os/Build:DEVICE	Ljava/lang/String;
    //   462: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   465: aload 6
    //   467: ldc_w 1041
    //   470: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   473: aload 6
    //   475: getstatic 1044	android/os/Build:PRODUCT	Ljava/lang/String;
    //   478: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   481: aload_0
    //   482: getfield 163	com/flurry/android/FlurryAgent:J	Ljava/util/List;
    //   485: invokeinterface 597 1 0
    //   490: istore 14
    //   492: aload 6
    //   494: iload 14
    //   496: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   499: iload_2
    //   500: iload 14
    //   502: if_icmpge +27 -> 529
    //   505: aload 6
    //   507: aload_0
    //   508: getfield 163	com/flurry/android/FlurryAgent:J	Ljava/util/List;
    //   511: iload_2
    //   512: invokeinterface 1046 2 0
    //   517: checkcast 1047	[B
    //   520: invokevirtual 1002	java/io/DataOutputStream:write	([B)V
    //   523: iinc 2 1
    //   526: goto -27 -> 499
    //   529: aload_0
    //   530: new 160	java/util/ArrayList
    //   533: dup
    //   534: aload_0
    //   535: getfield 163	com/flurry/android/FlurryAgent:J	Ljava/util/List;
    //   538: invokespecial 1050	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   541: putfield 757	com/flurry/android/FlurryAgent:D	Ljava/util/List;
    //   544: aload 5
    //   546: iconst_0
    //   547: invokevirtual 1053	java/security/DigestOutputStream:on	(Z)V
    //   550: aload 6
    //   552: aload_3
    //   553: invokevirtual 1056	com/flurry/android/CrcMessageDigest:getDigest	()[B
    //   556: invokevirtual 1002	java/io/DataOutputStream:write	([B)V
    //   559: aload 6
    //   561: invokevirtual 1059	java/io/DataOutputStream:close	()V
    //   564: aload 4
    //   566: invokevirtual 1062	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   569: astore 15
    //   571: aload 15
    //   573: astore 11
    //   575: aload 6
    //   577: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   580: goto -403 -> 177
    //   583: astore 7
    //   585: aconst_null
    //   586: astore 6
    //   588: goto -395 -> 193
    //   591: astore 9
    //   593: aconst_null
    //   594: astore 6
    //   596: goto -438 -> 158
    //   599: iconst_2
    //   600: istore 12
    //   602: goto -333 -> 269
    //
    // Exception table:
    //   from	to	target	type
    //   44	57	156	java/lang/Throwable
    //   61	67	156	java/lang/Throwable
    //   67	115	156	java/lang/Throwable
    //   115	153	156	java/lang/Throwable
    //   182	188	156	java/lang/Throwable
    //   208	220	156	java/lang/Throwable
    //   220	266	156	java/lang/Throwable
    //   269	291	156	java/lang/Throwable
    //   297	354	156	java/lang/Throwable
    //   354	499	156	java/lang/Throwable
    //   505	523	156	java/lang/Throwable
    //   529	571	156	java/lang/Throwable
    //   44	57	191	finally
    //   61	67	191	finally
    //   67	115	191	finally
    //   115	153	191	finally
    //   158	169	191	finally
    //   182	188	191	finally
    //   208	220	191	finally
    //   220	266	191	finally
    //   269	291	191	finally
    //   297	354	191	finally
    //   354	499	191	finally
    //   505	523	191	finally
    //   529	571	191	finally
    //   169	174	201	finally
    //   193	201	201	finally
    //   575	580	201	finally
    //   4	44	583	finally
    //   4	44	591	java/lang/Throwable
  }

  static String c()
  {
    if (f != null)
      return f;
    return g;
  }

  private static String c(Context paramContext)
  {
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      if (localPackageInfo.versionName != null)
        return localPackageInfo.versionName;
      if (localPackageInfo.versionCode != 0)
      {
        String str = Integer.toString(localPackageInfo.versionCode);
        return str;
      }
    }
    catch (Throwable localThrowable)
    {
      ai.b("FlurryAgent", "", localThrowable);
    }
    return "Unknown";
  }

  // ERROR //
  private void c(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: ldc_w 804
    //   7: invokevirtual 808	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
    //   10: putfield 396	com/flurry/android/FlurryAgent:t	Ljava/io/File;
    //   13: aload_0
    //   14: getfield 396	com/flurry/android/FlurryAgent:t	Ljava/io/File;
    //   17: invokestatic 1087	com/flurry/android/FlurryAgent:a	(Ljava/io/File;)Z
    //   20: istore 4
    //   22: iload 4
    //   24: ifne +6 -> 30
    //   27: aload_0
    //   28: monitorexit
    //   29: return
    //   30: new 957	java/io/DataOutputStream
    //   33: dup
    //   34: new 1089	java/io/FileOutputStream
    //   37: dup
    //   38: aload_0
    //   39: getfield 396	com/flurry/android/FlurryAgent:t	Ljava/io/File;
    //   42: invokespecial 1090	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   45: invokespecial 960	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   48: astore 5
    //   50: aload 5
    //   52: iconst_1
    //   53: invokevirtual 1093	java/io/DataOutputStream:writeInt	(I)V
    //   56: aload 5
    //   58: aload_2
    //   59: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   62: aload 5
    //   64: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   67: goto -40 -> 27
    //   70: astore_3
    //   71: aload_0
    //   72: monitorexit
    //   73: aload_3
    //   74: athrow
    //   75: astore 6
    //   77: aconst_null
    //   78: astore 5
    //   80: ldc 193
    //   82: ldc_w 1095
    //   85: aload 6
    //   87: invokestatic 229	com/flurry/android/ai:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   90: pop
    //   91: aload 5
    //   93: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   96: goto -69 -> 27
    //   99: aload 5
    //   101: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   104: aload 7
    //   106: athrow
    //   107: astore 7
    //   109: goto -10 -> 99
    //   112: astore 6
    //   114: goto -34 -> 80
    //   117: astore 7
    //   119: aconst_null
    //   120: astore 5
    //   122: goto -23 -> 99
    //
    // Exception table:
    //   from	to	target	type
    //   2	22	70	finally
    //   62	67	70	finally
    //   91	96	70	finally
    //   99	107	70	finally
    //   30	50	75	java/lang/Throwable
    //   50	62	107	finally
    //   80	91	107	finally
    //   50	62	112	java/lang/Throwable
    //   30	50	117	finally
  }

  private void c(String paramString)
  {
    monitorenter;
    try
    {
      Iterator localIterator = this.W.iterator();
      while (localIterator.hasNext())
      {
        i locali = (i)localIterator.next();
        if (!locali.a(paramString))
          continue;
        locali.a(SystemClock.elapsedRealtime() - this.L);
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void c(boolean paramBoolean)
  {
    while (true)
    {
      try
      {
        ai.a("FlurryAgent", "generating report");
        byte[] arrayOfByte = b(paramBoolean);
        if (arrayOfByte == null)
          continue;
        if (a(arrayOfByte))
        {
          StringBuilder localStringBuilder = new StringBuilder().append("Done sending ");
          if (!this.v)
            break label118;
          str = "initial ";
          ai.a("FlurryAgent", str + "agent report");
          n();
          return;
          ai.a("FlurryAgent", "Error generating report");
          return;
        }
      }
      catch (IOException localIOException)
      {
        ai.a("FlurryAgent", "", localIOException);
        return;
      }
      catch (Throwable localThrowable)
      {
        ai.b("FlurryAgent", "", localThrowable);
      }
      return;
      label118: String str = "";
    }
  }

  public static void clearUserCookies()
  {
    if (!o)
      return;
    h.ab.k();
  }

  private Location d(Context paramContext)
  {
    if ((paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) || (paramContext.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0))
    {
      LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
      monitorenter;
      try
      {
        if (this.E == null)
          this.E = localLocationManager;
        while (true)
        {
          monitorexit;
          Criteria localCriteria = n;
          if (localCriteria == null)
            localCriteria = new Criteria();
          String str = localLocationManager.getBestProvider(localCriteria, true);
          if (str == null)
            break;
          localLocationManager.requestLocationUpdates(str, 0L, 0.0F, this, Looper.getMainLooper());
          return localLocationManager.getLastKnownLocation(str);
          localLocationManager = this.E;
        }
      }
      finally
      {
        monitorexit;
      }
    }
    return null;
  }

  private static String d(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 4))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      for (int i1 = 0; i1 < -4 + paramString.length(); i1++)
        localStringBuilder.append('*');
      localStringBuilder.append(paramString.substring(-4 + paramString.length()));
      paramString = localStringBuilder.toString();
    }
    return paramString;
  }

  static boolean d()
  {
    if (!o)
      return false;
    return h.ab.m();
  }

  static String e()
  {
    return h.z;
  }

  private static byte[] e(Context paramContext)
  {
    String str;
    if (paramContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0)
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      if (localTelephonyManager != null)
      {
        str = localTelephonyManager.getDeviceId();
        if (str == null);
      }
    }
    try
    {
      byte[] arrayOfByte = r.b(str);
      if ((arrayOfByte != null) && (arrayOfByte.length == 20))
        return arrayOfByte;
      ai.b("FlurryAgent", "sha1 is not 20 bytes long: " + Arrays.toString(arrayOfByte));
      label86: return null;
    }
    catch (Exception localException)
    {
      break label86;
    }
  }

  public static void enableAppCircle()
  {
    o = true;
  }

  public static void endTimedEvent(String paramString)
  {
    try
    {
      h.c(paramString);
      return;
    }
    catch (Throwable localThrowable)
    {
      ai.b("FlurryAgent", "Failed to signify the end of event: " + paramString, localThrowable);
    }
  }

  static int f()
  {
    return q.incrementAndGet();
  }

  static int g()
  {
    return r.incrementAndGet();
  }

  public static int getAgentVersion()
  {
    return 122;
  }

  public static AppCircle getAppCircle()
  {
    return p;
  }

  public static boolean getForbidPlaintextFallback()
  {
    return false;
  }

  public static String getPhoneId()
  {
    return h.p();
  }

  protected static boolean isCaptureUncaughtExceptions()
  {
    return m;
  }

  // ERROR //
  private void k()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 949	java/io/ByteArrayOutputStream
    //   5: dup
    //   6: invokespecial 950	java/io/ByteArrayOutputStream:<init>	()V
    //   9: astore_1
    //   10: new 957	java/io/DataOutputStream
    //   13: dup
    //   14: aload_1
    //   15: invokespecial 960	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   18: astore_2
    //   19: aload_2
    //   20: iconst_1
    //   21: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   24: aload_2
    //   25: aload_0
    //   26: getfield 842	com/flurry/android/FlurryAgent:B	Ljava/lang/String;
    //   29: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   32: aload_2
    //   33: aload_0
    //   34: getfield 353	com/flurry/android/FlurryAgent:K	J
    //   37: invokevirtual 969	java/io/DataOutputStream:writeLong	(J)V
    //   40: aload_2
    //   41: aload_0
    //   42: getfield 460	com/flurry/android/FlurryAgent:M	J
    //   45: invokevirtual 969	java/io/DataOutputStream:writeLong	(J)V
    //   48: aload_2
    //   49: lconst_0
    //   50: invokevirtual 969	java/io/DataOutputStream:writeLong	(J)V
    //   53: aload_2
    //   54: aload_0
    //   55: getfield 167	com/flurry/android/FlurryAgent:N	Ljava/lang/String;
    //   58: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   61: aload_2
    //   62: aload_0
    //   63: getfield 169	com/flurry/android/FlurryAgent:O	Ljava/lang/String;
    //   66: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   69: aload_2
    //   70: aload_0
    //   71: getfield 171	com/flurry/android/FlurryAgent:P	B
    //   74: invokevirtual 994	java/io/DataOutputStream:writeByte	(I)V
    //   77: aload_0
    //   78: getfield 173	com/flurry/android/FlurryAgent:Q	Ljava/lang/String;
    //   81: ifnonnull +171 -> 252
    //   84: ldc 165
    //   86: astore 8
    //   88: aload_2
    //   89: aload 8
    //   91: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   94: aload_0
    //   95: getfield 486	com/flurry/android/FlurryAgent:U	Landroid/location/Location;
    //   98: ifnonnull +163 -> 261
    //   101: aload_2
    //   102: iconst_0
    //   103: invokevirtual 1202	java/io/DataOutputStream:writeBoolean	(Z)V
    //   106: aload_2
    //   107: aload_0
    //   108: getfield 874	com/flurry/android/FlurryAgent:aa	I
    //   111: invokevirtual 1093	java/io/DataOutputStream:writeInt	(I)V
    //   114: aload_2
    //   115: iconst_m1
    //   116: invokevirtual 994	java/io/DataOutputStream:writeByte	(I)V
    //   119: aload_2
    //   120: iconst_m1
    //   121: invokevirtual 994	java/io/DataOutputStream:writeByte	(I)V
    //   124: aload_2
    //   125: aload_0
    //   126: getfield 175	com/flurry/android/FlurryAgent:R	B
    //   129: invokevirtual 994	java/io/DataOutputStream:writeByte	(I)V
    //   132: aload_0
    //   133: getfield 1204	com/flurry/android/FlurryAgent:S	Ljava/lang/Long;
    //   136: ifnonnull +186 -> 322
    //   139: aload_2
    //   140: iconst_0
    //   141: invokevirtual 1202	java/io/DataOutputStream:writeBoolean	(Z)V
    //   144: aload_2
    //   145: aload_0
    //   146: getfield 180	com/flurry/android/FlurryAgent:V	Ljava/util/Map;
    //   149: invokeinterface 627 1 0
    //   154: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   157: aload_0
    //   158: getfield 180	com/flurry/android/FlurryAgent:V	Ljava/util/Map;
    //   161: invokeinterface 1207 1 0
    //   166: invokeinterface 979 1 0
    //   171: astore 9
    //   173: aload 9
    //   175: invokeinterface 984 1 0
    //   180: ifeq +161 -> 341
    //   183: aload 9
    //   185: invokeinterface 988 1 0
    //   190: checkcast 1209	java/util/Map$Entry
    //   193: astore 16
    //   195: aload_2
    //   196: aload 16
    //   198: invokeinterface 1212 1 0
    //   203: checkcast 81	java/lang/String
    //   206: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   209: aload_2
    //   210: aload 16
    //   212: invokeinterface 1215 1 0
    //   217: checkcast 626	com/flurry/android/g
    //   220: getfield 630	com/flurry/android/g:a	I
    //   223: invokevirtual 1093	java/io/DataOutputStream:writeInt	(I)V
    //   226: goto -53 -> 173
    //   229: astore 5
    //   231: aload_2
    //   232: astore 6
    //   234: ldc 193
    //   236: ldc 165
    //   238: aload 5
    //   240: invokestatic 229	com/flurry/android/ai:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   243: pop
    //   244: aload 6
    //   246: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   249: aload_0
    //   250: monitorexit
    //   251: return
    //   252: aload_0
    //   253: getfield 173	com/flurry/android/FlurryAgent:Q	Ljava/lang/String;
    //   256: astore 8
    //   258: goto -170 -> 88
    //   261: aload_2
    //   262: iconst_1
    //   263: invokevirtual 1202	java/io/DataOutputStream:writeBoolean	(Z)V
    //   266: aload_2
    //   267: aload_0
    //   268: getfield 486	com/flurry/android/FlurryAgent:U	Landroid/location/Location;
    //   271: invokevirtual 1220	android/location/Location:getLatitude	()D
    //   274: invokestatic 1222	com/flurry/android/FlurryAgent:a	(D)D
    //   277: invokevirtual 1226	java/io/DataOutputStream:writeDouble	(D)V
    //   280: aload_2
    //   281: aload_0
    //   282: getfield 486	com/flurry/android/FlurryAgent:U	Landroid/location/Location;
    //   285: invokevirtual 1229	android/location/Location:getLongitude	()D
    //   288: invokestatic 1222	com/flurry/android/FlurryAgent:a	(D)D
    //   291: invokevirtual 1226	java/io/DataOutputStream:writeDouble	(D)V
    //   294: aload_2
    //   295: aload_0
    //   296: getfield 486	com/flurry/android/FlurryAgent:U	Landroid/location/Location;
    //   299: invokevirtual 1233	android/location/Location:getAccuracy	()F
    //   302: invokevirtual 1237	java/io/DataOutputStream:writeFloat	(F)V
    //   305: goto -199 -> 106
    //   308: astore_3
    //   309: aload_2
    //   310: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   313: aload_3
    //   314: athrow
    //   315: astore 4
    //   317: aload_0
    //   318: monitorexit
    //   319: aload 4
    //   321: athrow
    //   322: aload_2
    //   323: iconst_1
    //   324: invokevirtual 1202	java/io/DataOutputStream:writeBoolean	(Z)V
    //   327: aload_2
    //   328: aload_0
    //   329: getfield 1204	com/flurry/android/FlurryAgent:S	Ljava/lang/Long;
    //   332: invokevirtual 991	java/lang/Long:longValue	()J
    //   335: invokevirtual 969	java/io/DataOutputStream:writeLong	(J)V
    //   338: goto -194 -> 144
    //   341: aload_2
    //   342: aload_0
    //   343: getfield 182	com/flurry/android/FlurryAgent:W	Ljava/util/List;
    //   346: invokeinterface 597 1 0
    //   351: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   354: aload_0
    //   355: getfield 182	com/flurry/android/FlurryAgent:W	Ljava/util/List;
    //   358: invokeinterface 1098 1 0
    //   363: astore 10
    //   365: aload 10
    //   367: invokeinterface 984 1 0
    //   372: ifeq +23 -> 395
    //   375: aload_2
    //   376: aload 10
    //   378: invokeinterface 988 1 0
    //   383: checkcast 648	com/flurry/android/i
    //   386: invokevirtual 654	com/flurry/android/i:a	()[B
    //   389: invokevirtual 1002	java/io/DataOutputStream:write	([B)V
    //   392: goto -27 -> 365
    //   395: aload_2
    //   396: aload_0
    //   397: getfield 658	com/flurry/android/FlurryAgent:X	Z
    //   400: invokevirtual 1202	java/io/DataOutputStream:writeBoolean	(Z)V
    //   403: aload_2
    //   404: aload_0
    //   405: getfield 592	com/flurry/android/FlurryAgent:T	I
    //   408: invokevirtual 1093	java/io/DataOutputStream:writeInt	(I)V
    //   411: aload_2
    //   412: aload_0
    //   413: getfield 184	com/flurry/android/FlurryAgent:Z	Ljava/util/List;
    //   416: invokeinterface 597 1 0
    //   421: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   424: aload_0
    //   425: getfield 184	com/flurry/android/FlurryAgent:Z	Ljava/util/List;
    //   428: invokeinterface 1098 1 0
    //   433: astore 11
    //   435: aload 11
    //   437: invokeinterface 984 1 0
    //   442: ifeq +63 -> 505
    //   445: aload 11
    //   447: invokeinterface 988 1 0
    //   452: checkcast 599	com/flurry/android/ab
    //   455: astore 15
    //   457: aload_2
    //   458: aload 15
    //   460: getfield 1238	com/flurry/android/ab:a	I
    //   463: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   466: aload_2
    //   467: aload 15
    //   469: getfield 605	com/flurry/android/ab:b	J
    //   472: invokevirtual 969	java/io/DataOutputStream:writeLong	(J)V
    //   475: aload_2
    //   476: aload 15
    //   478: getfield 609	com/flurry/android/ab:c	Ljava/lang/String;
    //   481: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   484: aload_2
    //   485: aload 15
    //   487: getfield 610	com/flurry/android/ab:d	Ljava/lang/String;
    //   490: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   493: aload_2
    //   494: aload 15
    //   496: getfield 611	com/flurry/android/ab:e	Ljava/lang/String;
    //   499: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   502: goto -67 -> 435
    //   505: getstatic 130	com/flurry/android/FlurryAgent:o	Z
    //   508: ifeq +59 -> 567
    //   511: aload_0
    //   512: getfield 189	com/flurry/android/FlurryAgent:ab	Lcom/flurry/android/v;
    //   515: invokevirtual 1241	com/flurry/android/v:f	()Ljava/util/List;
    //   518: astore 13
    //   520: aload_2
    //   521: aload 13
    //   523: invokeinterface 597 1 0
    //   528: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   531: aload 13
    //   533: invokeinterface 1098 1 0
    //   538: astore 14
    //   540: aload 14
    //   542: invokeinterface 984 1 0
    //   547: ifeq +25 -> 572
    //   550: aload 14
    //   552: invokeinterface 988 1 0
    //   557: checkcast 1243	com/flurry/android/p
    //   560: aload_2
    //   561: invokevirtual 1246	com/flurry/android/p:a	(Ljava/io/DataOutput;)V
    //   564: goto -24 -> 540
    //   567: aload_2
    //   568: iconst_0
    //   569: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   572: aload_2
    //   573: iconst_0
    //   574: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   577: aload_0
    //   578: getfield 163	com/flurry/android/FlurryAgent:J	Ljava/util/List;
    //   581: aload_1
    //   582: invokevirtual 1062	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   585: invokeinterface 614 2 0
    //   590: pop
    //   591: aload_2
    //   592: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   595: goto -346 -> 249
    //   598: astore_3
    //   599: aconst_null
    //   600: astore_2
    //   601: goto -292 -> 309
    //   604: astore_3
    //   605: aload 6
    //   607: astore_2
    //   608: goto -299 -> 309
    //   611: astore 5
    //   613: aconst_null
    //   614: astore 6
    //   616: goto -382 -> 234
    //
    // Exception table:
    //   from	to	target	type
    //   19	84	229	java/io/IOException
    //   88	106	229	java/io/IOException
    //   106	144	229	java/io/IOException
    //   144	173	229	java/io/IOException
    //   173	226	229	java/io/IOException
    //   252	258	229	java/io/IOException
    //   261	305	229	java/io/IOException
    //   322	338	229	java/io/IOException
    //   341	365	229	java/io/IOException
    //   365	392	229	java/io/IOException
    //   395	435	229	java/io/IOException
    //   435	502	229	java/io/IOException
    //   505	540	229	java/io/IOException
    //   540	564	229	java/io/IOException
    //   567	572	229	java/io/IOException
    //   572	591	229	java/io/IOException
    //   19	84	308	finally
    //   88	106	308	finally
    //   106	144	308	finally
    //   144	173	308	finally
    //   173	226	308	finally
    //   252	258	308	finally
    //   261	305	308	finally
    //   322	338	308	finally
    //   341	365	308	finally
    //   365	392	308	finally
    //   395	435	308	finally
    //   435	502	308	finally
    //   505	540	308	finally
    //   540	564	308	finally
    //   567	572	308	finally
    //   572	591	308	finally
    //   244	249	315	finally
    //   309	315	315	finally
    //   591	595	315	finally
    //   2	19	598	finally
    //   234	244	604	finally
    //   2	19	611	java/io/IOException
  }

  private void l()
  {
    monitorenter;
    try
    {
      this.aa = (1 + this.aa);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static void logEvent(String paramString)
  {
    try
    {
      h.a(paramString, null, false);
      return;
    }
    catch (Throwable localThrowable)
    {
      ai.b("FlurryAgent", "Failed to log event: " + paramString, localThrowable);
    }
  }

  public static void logEvent(String paramString, Map paramMap)
  {
    try
    {
      h.a(paramString, paramMap, false);
      return;
    }
    catch (Throwable localThrowable)
    {
      ai.b("FlurryAgent", "Failed to log event: " + paramString, localThrowable);
    }
  }

  public static void logEvent(String paramString, Map paramMap, boolean paramBoolean)
  {
    try
    {
      h.a(paramString, paramMap, paramBoolean);
      return;
    }
    catch (Throwable localThrowable)
    {
      ai.b("FlurryAgent", "Failed to log event: " + paramString, localThrowable);
    }
  }

  public static void logEvent(String paramString, boolean paramBoolean)
  {
    try
    {
      h.a(paramString, null, paramBoolean);
      return;
    }
    catch (Throwable localThrowable)
    {
      ai.b("FlurryAgent", "Failed to log event: " + paramString, localThrowable);
    }
  }

  private static String m()
  {
    if (c != null)
      return c;
    if (l)
      return kInsecureReportUrl;
    if (k)
      return kSecureReportUrl;
    return kInsecureReportUrl;
  }

  // ERROR //
  private void n()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 147	com/flurry/android/FlurryAgent:u	Ljava/io/File;
    //   6: invokestatic 1087	com/flurry/android/FlurryAgent:a	(Ljava/io/File;)Z
    //   9: istore 6
    //   11: iload 6
    //   13: ifne +10 -> 23
    //   16: aconst_null
    //   17: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: new 957	java/io/DataOutputStream
    //   26: dup
    //   27: new 1089	java/io/FileOutputStream
    //   30: dup
    //   31: aload_0
    //   32: getfield 147	com/flurry/android/FlurryAgent:u	Ljava/io/File;
    //   35: invokespecial 1090	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   38: invokespecial 960	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   41: astore_2
    //   42: aload_2
    //   43: ldc_w 334
    //   46: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   49: aload_2
    //   50: iconst_2
    //   51: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   54: aload_2
    //   55: aload_0
    //   56: getfield 374	com/flurry/android/FlurryAgent:z	Ljava/lang/String;
    //   59: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   62: aload_2
    //   63: aload_0
    //   64: getfield 295	com/flurry/android/FlurryAgent:F	Ljava/lang/String;
    //   67: invokevirtual 999	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   70: aload_2
    //   71: aload_0
    //   72: getfield 351	com/flurry/android/FlurryAgent:G	Z
    //   75: invokevirtual 1202	java/io/DataOutputStream:writeBoolean	(Z)V
    //   78: aload_2
    //   79: aload_0
    //   80: getfield 355	com/flurry/android/FlurryAgent:H	J
    //   83: invokevirtual 969	java/io/DataOutputStream:writeLong	(J)V
    //   86: iconst_m1
    //   87: aload_0
    //   88: getfield 163	com/flurry/android/FlurryAgent:J	Ljava/util/List;
    //   91: invokeinterface 597 1 0
    //   96: iadd
    //   97: istore 7
    //   99: iload 7
    //   101: iflt +66 -> 167
    //   104: aload_0
    //   105: getfield 163	com/flurry/android/FlurryAgent:J	Ljava/util/List;
    //   108: iload 7
    //   110: invokeinterface 1046 2 0
    //   115: checkcast 1047	[B
    //   118: astore 8
    //   120: aload 8
    //   122: arraylength
    //   123: istore 9
    //   125: iload 9
    //   127: iconst_2
    //   128: iadd
    //   129: aload_2
    //   130: invokevirtual 1254	java/io/DataOutputStream:size	()I
    //   133: iadd
    //   134: ldc_w 1255
    //   137: if_icmple +47 -> 184
    //   140: ldc 193
    //   142: new 303	java/lang/StringBuilder
    //   145: dup
    //   146: invokespecial 304	java/lang/StringBuilder:<init>	()V
    //   149: ldc_w 1257
    //   152: invokevirtual 310	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: iload 7
    //   157: invokevirtual 509	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   160: invokevirtual 316	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokestatic 349	com/flurry/android/ai:a	(Ljava/lang/String;Ljava/lang/String;)I
    //   166: pop
    //   167: aload_2
    //   168: iconst_0
    //   169: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   172: aload_2
    //   173: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   176: goto -156 -> 20
    //   179: astore_3
    //   180: aload_0
    //   181: monitorexit
    //   182: aload_3
    //   183: athrow
    //   184: aload_2
    //   185: iload 9
    //   187: invokevirtual 963	java/io/DataOutputStream:writeShort	(I)V
    //   190: aload_2
    //   191: aload 8
    //   193: invokevirtual 1002	java/io/DataOutputStream:write	([B)V
    //   196: iinc 7 255
    //   199: goto -100 -> 99
    //   202: astore 4
    //   204: aconst_null
    //   205: astore_2
    //   206: ldc 193
    //   208: ldc 165
    //   210: aload 4
    //   212: invokestatic 229	com/flurry/android/ai:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   215: pop
    //   216: aload_2
    //   217: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   220: goto -200 -> 20
    //   223: aload_2
    //   224: invokestatic 342	com/flurry/android/r:a	(Ljava/io/Closeable;)V
    //   227: aload_1
    //   228: athrow
    //   229: astore_1
    //   230: goto -7 -> 223
    //   233: astore 4
    //   235: goto -29 -> 206
    //   238: astore_1
    //   239: aconst_null
    //   240: astore_2
    //   241: goto -18 -> 223
    //
    // Exception table:
    //   from	to	target	type
    //   16	20	179	finally
    //   172	176	179	finally
    //   216	220	179	finally
    //   223	229	179	finally
    //   2	11	202	java/lang/Throwable
    //   23	42	202	java/lang/Throwable
    //   42	99	229	finally
    //   104	167	229	finally
    //   167	172	229	finally
    //   184	196	229	finally
    //   206	216	229	finally
    //   42	99	233	java/lang/Throwable
    //   104	167	233	java/lang/Throwable
    //   167	172	233	java/lang/Throwable
    //   184	196	233	java/lang/Throwable
    //   2	11	238	finally
    //   23	42	238	finally
  }

  private void o()
  {
    monitorenter;
    try
    {
      if (this.E != null)
        this.E.removeUpdates(this);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static void onEndSession(Context paramContext)
  {
    if (paramContext == null)
      throw new NullPointerException("Null context");
    try
    {
      h.a(paramContext, false);
      return;
    }
    catch (Throwable localThrowable)
    {
      ai.b("FlurryAgent", "", localThrowable);
    }
  }

  public static void onError(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      h.a(paramString1, paramString2, paramString3);
      return;
    }
    catch (Throwable localThrowable)
    {
      ai.b("FlurryAgent", "", localThrowable);
    }
  }

  public static void onEvent(String paramString)
  {
    try
    {
      h.a(paramString, null, false);
      return;
    }
    catch (Throwable localThrowable)
    {
      ai.b("FlurryAgent", "", localThrowable);
    }
  }

  public static void onEvent(String paramString, Map paramMap)
  {
    try
    {
      h.a(paramString, paramMap, false);
      return;
    }
    catch (Throwable localThrowable)
    {
      ai.b("FlurryAgent", "", localThrowable);
    }
  }

  public static void onPageView()
  {
    try
    {
      h.l();
      return;
    }
    catch (Throwable localThrowable)
    {
      ai.b("FlurryAgent", "", localThrowable);
    }
  }

  public static void onStartSession(Context paramContext, String paramString)
  {
    if (paramContext == null)
      throw new NullPointerException("Null context");
    if ((paramString == null) || (paramString.length() == 0))
      throw new IllegalArgumentException("Api key not specified");
    try
    {
      h.b(paramContext, paramString);
      return;
    }
    catch (Throwable localThrowable)
    {
      ai.b("FlurryAgent", "", localThrowable);
    }
  }

  private String p()
  {
    monitorenter;
    try
    {
      String str = this.F;
      monitorexit;
      return str;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static void setAge(int paramInt)
  {
    if ((paramInt > 0) && (paramInt < 110))
    {
      Date localDate = new Date(new Date(System.currentTimeMillis() - 31449600000L * paramInt).getYear(), 1, 1);
      h.S = Long.valueOf(localDate.getTime());
    }
  }

  public static void setCanvasUrl(String paramString)
  {
    d = paramString;
  }

  public static void setCaptureUncaughtExceptions(boolean paramBoolean)
  {
    synchronized (h)
    {
      if (h.v)
      {
        ai.b("FlurryAgent", "Cannot setCaptureUncaughtExceptions after onSessionStart");
        return;
      }
      m = paramBoolean;
      return;
    }
  }

  public static void setCatalogIntentName(String paramString)
  {
    a = paramString;
  }

  public static void setContinueSessionMillis(long paramLong)
  {
    if (paramLong < 5000L)
    {
      ai.b("FlurryAgent", "Invalid time set for session resumption: " + paramLong);
      return;
    }
    synchronized (h)
    {
      i = paramLong;
      return;
    }
  }

  public static void setDefaultNoAdsMessage(String paramString)
  {
    if (!o)
      return;
    if (paramString == null)
      paramString = "";
    v.b = paramString;
  }

  public static void setGender(byte paramByte)
  {
    switch (paramByte)
    {
    default:
      h.R = -1;
      return;
    case 0:
    case 1:
    }
    h.R = paramByte;
  }

  public static void setGetAppUrl(String paramString)
  {
    f = paramString;
  }

  public static void setLocationCriteria(Criteria paramCriteria)
  {
    synchronized (h)
    {
      n = paramCriteria;
      return;
    }
  }

  public static void setLogEnabled(boolean paramBoolean)
  {
    FlurryAgent localFlurryAgent = h;
    monitorenter;
    if (paramBoolean);
    try
    {
      ai.b();
      while (true)
      {
        return;
        ai.a();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void setLogEvents(boolean paramBoolean)
  {
    synchronized (h)
    {
      j = paramBoolean;
      return;
    }
  }

  public static void setLogLevel(int paramInt)
  {
    synchronized (h)
    {
      ai.a(paramInt);
      return;
    }
  }

  public static void setReportLocation(boolean paramBoolean)
  {
    synchronized (h)
    {
      h.C = paramBoolean;
      return;
    }
  }

  public static void setReportUrl(String paramString)
  {
    c = paramString;
  }

  public static void setUseHttps(boolean paramBoolean)
  {
    k = paramBoolean;
  }

  public static void setUserId(String paramString)
  {
    synchronized (h)
    {
      h.Q = r.a(paramString, 255);
      return;
    }
  }

  public static void setVersionName(String paramString)
  {
    synchronized (h)
    {
      h.B = paramString;
      return;
    }
  }

  final void a(Throwable paramThrowable)
  {
    paramThrowable.printStackTrace();
    String str = "";
    StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
    if ((arrayOfStackTraceElement != null) && (arrayOfStackTraceElement.length > 0))
    {
      StackTraceElement localStackTraceElement = arrayOfStackTraceElement[0];
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(localStackTraceElement.getClassName()).append(".").append(localStackTraceElement.getMethodName()).append(":").append(localStackTraceElement.getLineNumber());
      if (paramThrowable.getMessage() != null)
        localStringBuilder.append(" (" + paramThrowable.getMessage() + ")");
      str = localStringBuilder.toString();
    }
    while (true)
    {
      onError("uncaught", str, paramThrowable.getClass().toString());
      this.y.clear();
      a(null, true);
      return;
      if (paramThrowable.getMessage() == null)
        continue;
      str = paramThrowable.getMessage();
    }
  }

  public final void onLocationChanged(Location paramLocation)
  {
    monitorenter;
    try
    {
      this.U = paramLocation;
      o();
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        ai.b("FlurryAgent", "", localThrowable);
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final void onProviderDisabled(String paramString)
  {
  }

  public final void onProviderEnabled(String paramString)
  {
  }

  public final void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.FlurryAgent
 * JD-Core Version:    0.6.0
 */