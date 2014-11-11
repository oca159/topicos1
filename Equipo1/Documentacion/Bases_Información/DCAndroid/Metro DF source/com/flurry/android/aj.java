package com.flurry.android;

import java.net.Socket;
import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

final class aj extends org.apache.http.conn.ssl.SSLSocketFactory
{
  private SSLContext a = SSLContext.getInstance("TLS");

  public aj(FlurryAgent paramFlurryAgent, KeyStore paramKeyStore)
  {
    super(paramKeyStore);
    n localn = new n();
    this.a.init(null, new TrustManager[] { localn }, null);
  }

  public final Socket createSocket()
  {
    return this.a.getSocketFactory().createSocket();
  }

  public final Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
  {
    return this.a.getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.flurry.android.aj
 * JD-Core Version:    0.6.0
 */