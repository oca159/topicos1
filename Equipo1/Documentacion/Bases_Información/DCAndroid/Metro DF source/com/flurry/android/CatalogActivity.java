package com.flurry.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends Activity
  implements View.OnClickListener
{
  private static volatile String a = "<html><body><table height='100%' width='100%' border='0'><tr><td style='vertical-align:middle;text-align:center'>No recommendations available<p><button type='input' onClick='activity.finish()'>Back</button></td></tr></table></body></html>";
  private WebView b;
  private x c;
  private List d = new ArrayList();
  private v e;
  private p f;

  private void a(y paramy)
  {
    try
    {
      this.b.loadUrl(paramy.a);
      this.c.a(paramy.b);
      return;
    }
    catch (Exception localException)
    {
      ai.a("FlurryAgent", "Error loading url: " + paramy.a);
    }
  }

  public void finish()
  {
    super.finish();
  }

  public void onClick(View paramView)
  {
    if ((paramView instanceof z))
    {
      y localy1 = new y();
      localy1.a = this.b.getUrl();
      localy1.b = new ArrayList(this.c.b());
      this.d.add(localy1);
      if (this.d.size() > 5)
        this.d.remove(0);
      y localy2 = new y();
      z localz = (z)paramView;
      this.f = this.e.b(localz.a());
      localz.a(this.f);
      String str = this.e.a(localz.a());
      localy2.a = (this.e.i() + str);
      localy2.b = this.c.a(paramView.getContext());
      a(localy2);
      return;
    }
    if (paramView.getId() == 10000)
    {
      finish();
      return;
    }
    if (paramView.getId() == 10002)
    {
      this.c.a();
      return;
    }
    if (this.d.isEmpty())
    {
      finish();
      return;
    }
    a((y)this.d.remove(-1 + this.d.size()));
  }

  protected void onCreate(Bundle paramBundle)
  {
    setTheme(16973839);
    super.onCreate(paramBundle);
    this.e = FlurryAgent.b();
    Intent localIntent = getIntent();
    if (localIntent.getExtras() != null)
    {
      Long localLong = Long.valueOf(localIntent.getExtras().getLong("o"));
      if (localLong != null)
        this.f = this.e.b(localLong.longValue());
    }
    ac localac = new ac(this, this);
    localac.setId(1);
    localac.setBackgroundColor(-16777216);
    this.b = new WebView(this);
    this.b.setId(2);
    this.b.setScrollBarStyle(0);
    this.b.setBackgroundColor(-1);
    if (this.f != null)
      this.b.setWebViewClient(new q(this));
    this.b.getSettings().setJavaScriptEnabled(true);
    this.b.addJavascriptInterface(this, "activity");
    this.c = new x(this, this);
    this.c.setId(3);
    RelativeLayout localRelativeLayout = new RelativeLayout(this);
    localRelativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams1.addRule(10, localac.getId());
    localRelativeLayout.addView(localac, localLayoutParams1);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams2.addRule(3, localac.getId());
    localLayoutParams2.addRule(2, this.c.getId());
    localRelativeLayout.addView(this.b, localLayoutParams2);
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams3.addRule(12, localac.getId());
    localRelativeLayout.addView(this.c, localLayoutParams3);
    Bundle localBundle = getIntent().getExtras();
    String str;
    if (localBundle == null)
    {
      str = null;
      if (str != null)
        break label381;
      this.b.loadDataWithBaseURL(null, a, "text/html", "utf-8", null);
    }
    while (true)
    {
      setContentView(localRelativeLayout);
      return;
      str = localBundle.getString("u");
      break;
      label381: this.b.loadUrl(str);
    }
  }

  protected void onDestroy()
  {
    this.e.g();
    super.onDestroy();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.CatalogActivity
 * JD-Core Version:    0.6.0
 */