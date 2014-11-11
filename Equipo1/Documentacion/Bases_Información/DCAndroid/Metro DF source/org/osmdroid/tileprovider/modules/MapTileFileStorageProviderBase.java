package org.osmdroid.tileprovider.modules;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import org.osmdroid.tileprovider.IRegisterReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MapTileFileStorageProviderBase extends MapTileModuleProviderBase
{
  private static final Logger logger = LoggerFactory.getLogger(MapTileFileStorageProviderBase.class);
  private MyBroadcastReceiver mBroadcastReceiver;
  private final IRegisterReceiver mRegisterReceiver;
  private boolean mSdCardAvailable = true;

  public MapTileFileStorageProviderBase(IRegisterReceiver paramIRegisterReceiver, int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    checkSdCard();
    this.mRegisterReceiver = paramIRegisterReceiver;
    this.mBroadcastReceiver = new MyBroadcastReceiver(null);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
    localIntentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
    localIntentFilter.addDataScheme("file");
    paramIRegisterReceiver.registerReceiver(this.mBroadcastReceiver, localIntentFilter);
  }

  private void checkSdCard()
  {
    String str = Environment.getExternalStorageState();
    logger.info("sdcard state: " + str);
    this.mSdCardAvailable = "mounted".equals(str);
  }

  public void detach()
  {
    if (this.mBroadcastReceiver != null)
    {
      this.mRegisterReceiver.unregisterReceiver(this.mBroadcastReceiver);
      this.mBroadcastReceiver = null;
    }
    super.detach();
  }

  protected boolean getSdCardAvailable()
  {
    return this.mSdCardAvailable;
  }

  protected void onMediaMounted()
  {
  }

  protected void onMediaUnmounted()
  {
  }

  private class MyBroadcastReceiver extends BroadcastReceiver
  {
    private MyBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      String str = paramIntent.getAction();
      MapTileFileStorageProviderBase.this.checkSdCard();
      if ("android.intent.action.MEDIA_MOUNTED".equals(str))
        MapTileFileStorageProviderBase.this.onMediaMounted();
      do
        return;
      while (!"android.intent.action.MEDIA_UNMOUNTED".equals(str));
      MapTileFileStorageProviderBase.this.onMediaUnmounted();
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.modules.MapTileFileStorageProviderBase
 * JD-Core Version:    0.6.0
 */