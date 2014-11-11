package org.osmdroid.tileprovider.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloudmadeUtil
  implements OpenStreetMapTileProviderConstants
{
  private static final String CLOUDMADE_ID = "CLOUDMADE_ID";
  private static final String CLOUDMADE_KEY = "CLOUDMADE_KEY";
  private static final String CLOUDMADE_TOKEN = "CLOUDMADE_TOKEN";
  private static final Logger logger = LoggerFactory.getLogger(CloudmadeUtil.class);
  private static String mAndroidId = "android_id";
  private static String mKey = "";
  private static SharedPreferences.Editor mPreferenceEditor;
  private static String mToken = "";

  public static String getCloudmadeKey()
  {
    return mKey;
  }

  public static String getCloudmadeToken()
  {
    if (mToken.length() == 0);
    synchronized (mToken)
    {
      DefaultHttpClient localDefaultHttpClient;
      HttpPost localHttpPost;
      if (mToken.length() == 0)
      {
        String str2 = "http://auth.cloudmade.com/token/" + mKey + "?userid=" + mAndroidId;
        localDefaultHttpClient = new DefaultHttpClient();
        localHttpPost = new HttpPost(str2);
      }
      try
      {
        HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
        if (localHttpResponse.getStatusLine().getStatusCode() == 200)
        {
          mToken = new BufferedReader(new InputStreamReader(localHttpResponse.getEntity().getContent()), 8192).readLine().trim();
          if (mToken.length() <= 0)
            break label180;
          mPreferenceEditor.putString("CLOUDMADE_TOKEN", mToken);
          mPreferenceEditor.commit();
          mPreferenceEditor = null;
        }
        while (true)
        {
          return mToken;
          label180: logger.error("No authorization token received from Cloudmade");
        }
      }
      catch (IOException localIOException)
      {
        while (true)
          logger.error("No authorization token received from Cloudmade: " + localIOException);
      }
    }
  }

  public static void retrieveCloudmadeKey(Context paramContext)
  {
    mAndroidId = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 128);
      if (localApplicationInfo.metaData == null)
        logger.info("Cloudmade key not found in manifest");
      while (true)
      {
        SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
        mPreferenceEditor = localSharedPreferences.edit();
        if (!localSharedPreferences.getString("CLOUDMADE_ID", "").equals(mAndroidId))
          break;
        mToken = localSharedPreferences.getString("CLOUDMADE_TOKEN", "");
        if (mToken.length() > 0)
          mPreferenceEditor = null;
        return;
        String str = localApplicationInfo.metaData.getString("CLOUDMADE_KEY");
        if (str == null)
          continue;
        mKey = str.trim();
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        logger.info("Cloudmade key not found in manifest", localNameNotFoundException);
      mPreferenceEditor.putString("CLOUDMADE_ID", mAndroidId);
      mPreferenceEditor.commit();
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.util.CloudmadeUtil
 * JD-Core Version:    0.6.0
 */