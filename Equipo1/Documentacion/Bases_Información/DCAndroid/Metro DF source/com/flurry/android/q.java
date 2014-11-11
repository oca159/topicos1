package com.flurry.android;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.List;

final class q extends WebViewClient
{
  q(CatalogActivity paramCatalogActivity)
  {
  }

  public final void onPageFinished(WebView paramWebView, String paramString)
  {
    try
    {
      p localp = CatalogActivity.a(this.a);
      f localf = new f(5, CatalogActivity.b(this.a));
      long l = CatalogActivity.a(this.a).d;
      localp.e.add(localf);
      localp.d = l;
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public final void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    ai.c("FlurryAgent", "Failed to load url: " + paramString2 + " with an errorCode of " + paramInt);
    paramWebView.loadData("Cannot find Android Market information. <p>Please check your network", "text/html", "UTF-8");
  }

  public final boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    if (paramString == null)
      return false;
    if (CatalogActivity.a(this.a) != null)
      CatalogActivity.a(this.a).a(new f(6, CatalogActivity.b(this.a)));
    CatalogActivity.c(this.a).a(paramWebView.getContext(), CatalogActivity.a(this.a), paramString);
    return true;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.q
 * JD-Core Version:    0.6.0
 */