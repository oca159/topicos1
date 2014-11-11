package com.flurry.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

final class v
  implements View.OnClickListener
{
  private static volatile long A;
  static String a;
  static String b;
  private static volatile String c = "market://";
  private static volatile String d = "market://details?id=";
  private static volatile String e = "https://market.android.com/details?id=";
  private static String f = "com.flurry.android.ACTION_CATALOG";
  private static int g;
  private String h;
  private String i;
  private String j;
  private long k;
  private long l;
  private long m;
  private long n;
  private aa o = new aa();
  private boolean p = true;
  private volatile boolean q;
  private String r;
  private Map s = new HashMap();
  private Handler t;
  private boolean u;
  private transient Map v = new HashMap();
  private ah w;
  private List x = new ArrayList();
  private Map y = new HashMap();
  private AppCircleCallback z;

  static
  {
    a = "FlurryAgent";
    new Random(System.currentTimeMillis());
    g = 5000;
    b = "";
    A = 0L;
  }

  private Offer a(String paramString, w paramw)
  {
    p localp = new p(paramString, 3, j());
    c(localp);
    localp.a(new f(2, j()));
    localp.c = paramw;
    am localam = this.o.a(paramw.a);
    String str;
    if (localam == null)
    {
      str = "";
      if (localam != null)
        break label173;
    }
    label173: for (int i1 = 0; ; i1 = localam.c)
    {
      long l1 = 1L + A;
      A = l1;
      u localu = new u(l1, localp, paramw.h, paramw.d, str, i1);
      this.y.put(Long.valueOf(localu.a), localu);
      Offer localOffer = new Offer(localu.a, localu.f, localu.c, localu.d, localu.e);
      return localOffer;
      str = localam.a;
      break;
    }
  }

  private static String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i1 = 0;
    if (i1 < paramArrayOfByte.length)
    {
      int i2 = 0xF & paramArrayOfByte[i1] >> 4;
      label41: int i3;
      if (i2 < 10)
      {
        localStringBuilder.append((char)(i2 + 48));
        i3 = 0xF & paramArrayOfByte[i1];
        if (i3 >= 10)
          break label89;
        localStringBuilder.append((char)(i3 + 48));
      }
      while (true)
      {
        i1++;
        break;
        localStringBuilder.append((char)(-10 + (i2 + 65)));
        break label41;
        label89: localStringBuilder.append((char)(-10 + (i3 + 65)));
      }
    }
    return localStringBuilder.toString();
  }

  private List a(List paramList, Long paramLong)
  {
    if ((paramList == null) || (paramList.isEmpty()) || (!this.o.b()))
      return Collections.emptyList();
    w[] arrayOfw = this.o.a((String)paramList.get(0));
    if ((arrayOfw != null) && (arrayOfw.length > 0))
    {
      ArrayList localArrayList = new ArrayList(Arrays.asList(arrayOfw));
      Collections.shuffle(localArrayList);
      if (paramLong != null)
      {
        Iterator localIterator = localArrayList.iterator();
        while (localIterator.hasNext())
        {
          if (((w)localIterator.next()).a != paramLong.longValue())
            continue;
          localIterator.remove();
        }
      }
      return localArrayList.subList(0, Math.min(localArrayList.size(), paramList.size()));
    }
    return Collections.emptyList();
  }

  private static void a(Runnable paramRunnable)
  {
    new Handler().post(paramRunnable);
  }

  private void b(Context paramContext, p paramp, String paramString)
  {
    Intent localIntent = new Intent(o());
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.putExtra("u", paramString);
    if (paramp != null)
      localIntent.putExtra("o", paramp.a());
    paramContext.startActivity(localIntent);
  }

  private void c(p paramp)
  {
    if (this.x.size() < 32767)
    {
      this.x.add(paramp);
      this.v.put(Long.valueOf(paramp.a()), paramp);
    }
  }

  private String d(String paramString)
  {
    try
    {
      if (!paramString.startsWith(c))
      {
        HttpGet localHttpGet = new HttpGet(paramString);
        HttpResponse localHttpResponse = new DefaultHttpClient().execute(localHttpGet);
        int i1 = localHttpResponse.getStatusLine().getStatusCode();
        if (i1 == 200)
        {
          paramString = EntityUtils.toString(localHttpResponse.getEntity());
          if (!paramString.startsWith(c))
            return d(paramString);
        }
        else
        {
          ai.c(a, "Cannot process with responseCode " + i1);
          e("Error when fetching application's android market ID, responseCode " + i1);
          return paramString;
        }
      }
    }
    catch (UnknownHostException localUnknownHostException)
    {
      ai.c(a, "Unknown host: " + localUnknownHostException.getMessage());
      if (this.z != null)
        e("Unknown host: " + localUnknownHostException.getMessage());
      return null;
    }
    catch (Exception localException)
    {
      ai.c(a, "Failed on url: " + paramString, localException);
      paramString = null;
    }
    return paramString;
  }

  private void e(String paramString)
  {
    a(new af(this, paramString));
  }

  private AdImage n()
  {
    monitorenter;
    try
    {
      boolean bool = p();
      if (!bool);
      AdImage localAdImage;
      for (Object localObject2 = null; ; localObject2 = localAdImage)
      {
        return localObject2;
        localAdImage = this.o.a(1);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  private static String o()
  {
    if (FlurryAgent.a != null)
      return FlurryAgent.a;
    return f;
  }

  private boolean p()
  {
    if (!this.q)
      ai.d(a, "AppCircle is not initialized");
    if (this.r == null)
      ai.d(a, "Cannot identify UDID.");
    return this.q;
  }

  final View a(Context paramContext, String paramString, int paramInt)
  {
    monitorenter;
    try
    {
      boolean bool = p();
      o localo;
      if (!bool)
        localo = null;
      while (true)
      {
        return localo;
        localo = new o(this, paramContext, paramString, paramInt);
        this.w.a(localo);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  final AdImage a(long paramLong)
  {
    monitorenter;
    try
    {
      boolean bool = p();
      if (!bool);
      AdImage localAdImage;
      for (Object localObject2 = null; ; localObject2 = localAdImage)
      {
        return localObject2;
        localAdImage = this.o.b(paramLong);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  final String a(p paramp)
  {
    monitorenter;
    StringBuilder localStringBuilder;
    try
    {
      w localw = paramp.c;
      localStringBuilder = new StringBuilder().append("?apik=").append(this.j).append("&cid=").append(localw.e).append("&adid=").append(localw.a).append("&pid=").append(this.r).append("&iid=").append(this.k).append("&sid=").append(this.l).append("&lid=").append(paramp.b).append("&aso=").append(((f)paramp.e.get(-1 + paramp.e.size())).b).append("&hid=").append(r.a(paramp.a)).append("&ac=").append(a(localw.g));
      if ((this.s != null) && (!this.s.isEmpty()))
      {
        Iterator localIterator = this.s.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          String str2 = "c_" + r.a((String)localEntry.getKey());
          String str3 = r.a((String)localEntry.getValue());
          localStringBuilder.append("&").append(str2).append("=").append(str3);
        }
      }
    }
    finally
    {
      monitorexit;
    }
    localStringBuilder.append("&ats=").append(System.currentTimeMillis());
    String str1 = localStringBuilder.toString();
    monitorexit;
    return str1;
  }

  final List a(Context paramContext, List paramList, Long paramLong, int paramInt, boolean paramBoolean)
  {
    monitorenter;
    while (true)
    {
      ArrayList localArrayList;
      int i2;
      try
      {
        if (p())
          continue;
        List localList3 = Collections.emptyList();
        localObject2 = localList3;
        return localObject2;
        if ((!this.o.b()) || (paramList == null))
          break label261;
        List localList1 = a(paramList, paramLong);
        int i1 = Math.min(paramList.size(), localList1.size());
        localArrayList = new ArrayList();
        i2 = 0;
        if (i2 < i1)
        {
          String str = (String)paramList.get(i2);
          e locale = this.o.b(str);
          if (locale == null)
            continue;
          p localp = new p((String)paramList.get(i2), 1, j());
          c(localp);
          if (i2 >= localList1.size())
            break label273;
          localp.c = ((w)localList1.get(i2));
          localp.a(new f(2, j()));
          localArrayList.add(new z(paramContext, this, localp, locale, paramInt, paramBoolean));
          break label273;
          ai.d(a, "Cannot find hook: " + str);
        }
      }
      finally
      {
        monitorexit;
      }
      Object localObject2 = localArrayList;
      continue;
      label261: List localList2 = Collections.emptyList();
      localObject2 = localList2;
      continue;
      label273: i2++;
    }
  }

  final void a(int paramInt)
  {
    monitorenter;
    try
    {
      if (this.z != null)
        a(new ae(this, paramInt));
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

  final void a(long paramLong1, long paramLong2)
  {
    monitorenter;
    try
    {
      this.l = paramLong1;
      this.m = paramLong2;
      this.n = 0L;
      this.x.clear();
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

  final void a(Context paramContext, long paramLong)
  {
    monitorenter;
    while (true)
    {
      u localu;
      try
      {
        boolean bool = p();
        if (!bool)
          return;
        localu = (u)this.y.get(Long.valueOf(paramLong));
        if (localu == null)
        {
          ai.b(a, "Cannot find offer " + paramLong);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      p localp = b(localu.b);
      localu.b = localp;
      String str = FlurryAgent.c() + a(localp);
      ai.a(a, "Offer " + localu.a + " accepted. Sent with cookies: " + this.s);
      a(paramContext, localp, str);
    }
  }

  final void a(Context paramContext, a parama)
  {
    boolean bool = true;
    monitorenter;
    try
    {
      if (!this.q)
      {
        this.h = parama.c;
        this.i = parama.d;
        this.j = parama.a;
        this.k = parama.b;
        this.t = parama.e;
        this.w = new ah(this.t, g);
        paramContext.getResources().getDisplayMetrics();
        this.y.clear();
        this.v.clear();
        this.o.a(paramContext, this, parama);
        this.s.clear();
        PackageManager localPackageManager = paramContext.getPackageManager();
        String str1 = paramContext.getPackageName();
        String str2 = d + str1;
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse(str2));
        if (localPackageManager.queryIntentActivities(localIntent, 65536).size() <= 0)
          break label203;
      }
      while (true)
      {
        this.p = bool;
        this.q = true;
        return;
        label203: bool = false;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  final void a(Context paramContext, p paramp, String paramString)
  {
    monitorenter;
    try
    {
      boolean bool = p();
      if (!bool);
      while (true)
      {
        return;
        this.t.post(new al(this, paramString, paramContext, paramp));
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  final void a(Context paramContext, String paramString)
  {
    monitorenter;
    while (true)
    {
      try
      {
        boolean bool = p();
        if (!bool)
          return;
        try
        {
          List localList = a(Arrays.asList(new String[] { paramString }), null);
          if ((localList == null) || (localList.isEmpty()))
            break label161;
          p localp = new p(paramString, 2, j());
          localp.c = ((w)localList.get(0));
          c(localp);
          b(paramContext, localp, this.h + a(localp));
        }
        catch (Exception localException)
        {
          ai.d(a, "Failed to launch promotional canvas for hook: " + paramString, localException);
        }
        continue;
      }
      finally
      {
        monitorexit;
      }
      label161: Intent localIntent = new Intent(o());
      localIntent.addCategory("android.intent.category.DEFAULT");
      paramContext.startActivity(localIntent);
    }
  }

  final void a(AppCircleCallback paramAppCircleCallback)
  {
    this.z = paramAppCircleCallback;
  }

  final void a(String paramString)
  {
    this.r = paramString;
  }

  final void a(String paramString1, String paramString2)
  {
    monitorenter;
    try
    {
      this.s.put(paramString1, paramString2);
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

  final void a(List paramList)
  {
    monitorenter;
    try
    {
      boolean bool = p();
      if (!bool);
      while (true)
      {
        return;
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
        {
          Long localLong = (Long)localIterator.next();
          this.y.remove(localLong);
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  final void a(Map paramMap1, Map paramMap2, Map paramMap3, Map paramMap4, Map paramMap5, Map paramMap6)
  {
    monitorenter;
    try
    {
      boolean bool = p();
      if (!bool);
      while (true)
      {
        return;
        this.o.a(paramMap1, paramMap2, paramMap3, paramMap4, paramMap5, paramMap6);
        Log.i("FlurryAgent", this.o.toString());
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  final void a(boolean paramBoolean)
  {
    this.u = paramBoolean;
  }

  final boolean a()
  {
    return this.q;
  }

  final Offer b(String paramString)
  {
    monitorenter;
    try
    {
      boolean bool1 = p();
      Offer localOffer = null;
      if (!bool1);
      while (true)
      {
        return localOffer;
        List localList = a(Arrays.asList(new String[] { paramString }), null);
        localOffer = null;
        if (localList == null)
          continue;
        boolean bool2 = localList.isEmpty();
        localOffer = null;
        if (bool2)
          continue;
        localOffer = a(paramString, (w)localList.get(0));
        ai.a(a, "Impression for offer with ID " + localOffer.getId());
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  final p b(long paramLong)
  {
    monitorenter;
    try
    {
      p localp = (p)this.v.get(Long.valueOf(paramLong));
      monitorexit;
      return localp;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  final p b(p paramp)
  {
    monitorenter;
    try
    {
      if (!this.x.contains(paramp))
      {
        p localp = new p(paramp, j());
        this.x.add(localp);
        paramp = localp;
      }
      paramp.a(new f(4, j()));
      return paramp;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  final void b()
  {
    monitorenter;
    try
    {
      boolean bool = p();
      if (!bool);
      while (true)
      {
        return;
        this.o.d();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  final List c(String paramString)
  {
    monitorenter;
    try
    {
      Object localObject2;
      if (!p())
      {
        List localList = Collections.emptyList();
        localObject2 = localList;
      }
      while (true)
      {
        return localObject2;
        if (!this.o.b())
        {
          localObject2 = Collections.emptyList();
          continue;
        }
        w[] arrayOfw = this.o.a(paramString);
        localObject2 = new ArrayList();
        if ((arrayOfw != null) && (arrayOfw.length > 0))
        {
          int i1 = arrayOfw.length;
          for (int i2 = 0; i2 < i1; i2++)
            ((List)localObject2).add(a(paramString, arrayOfw[i2]));
        }
        ai.a(a, "Impressions for " + ((List)localObject2).size() + " offers.");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  final void c()
  {
    monitorenter;
    try
    {
      boolean bool = p();
      if (!bool);
      while (true)
      {
        return;
        this.o.e();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  final long d()
  {
    monitorenter;
    try
    {
      boolean bool = p();
      long l2;
      if (!bool)
        l2 = 0L;
      while (true)
      {
        return l2;
        long l1 = this.o.c();
        l2 = l1;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  final Set e()
  {
    monitorenter;
    try
    {
      Set localSet2;
      if (!p())
        localSet2 = Collections.emptySet();
      Set localSet1;
      for (Object localObject2 = localSet2; ; localObject2 = localSet1)
      {
        return localObject2;
        localSet1 = this.o.a();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  final List f()
  {
    monitorenter;
    try
    {
      List localList = this.x;
      monitorexit;
      return localList;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  final void g()
  {
    monitorenter;
    try
    {
      this.v.clear();
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

  final boolean h()
  {
    return this.u;
  }

  final String i()
  {
    return this.h;
  }

  final long j()
  {
    monitorenter;
    try
    {
      long l1 = SystemClock.elapsedRealtime() - this.m;
      if (l1 > this.n);
      while (true)
      {
        this.n = l1;
        long l2 = this.n;
        return l2;
        l1 = 1L + this.n;
        this.n = l1;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  final void k()
  {
    monitorenter;
    try
    {
      this.s.clear();
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

  final AdImage l()
  {
    monitorenter;
    try
    {
      boolean bool = p();
      if (!bool);
      AdImage localAdImage;
      for (Object localObject2 = null; ; localObject2 = localAdImage)
      {
        return localObject2;
        localAdImage = n();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  final boolean m()
  {
    monitorenter;
    try
    {
      boolean bool1 = p();
      if (!bool1);
      boolean bool2;
      for (int i1 = 0; ; i1 = bool2)
      {
        return i1;
        bool2 = this.o.b();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final void onClick(View paramView)
  {
    monitorenter;
    try
    {
      z localz = (z)paramView;
      p localp = b(localz.a());
      localz.a(localp);
      String str = a(localp);
      if (this.u)
        b(paramView.getContext(), localp, this.h + str);
      while (true)
      {
        return;
        a(paramView.getContext(), localp, this.i + str);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[adLogs=").append(this.x).append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.v
 * JD-Core Version:    0.6.0
 */