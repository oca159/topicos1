package org.osmdroid.tileprovider.util;

import android.os.Handler;
import android.os.Message;
import android.view.View;

public class SimpleInvalidationHandler extends Handler
{
  private final View mView;

  public SimpleInvalidationHandler(View paramView)
  {
    this.mView = paramView;
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      return;
    case 0:
    }
    this.mView.invalidate();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.util.SimpleInvalidationHandler
 * JD-Core Version:    0.6.0
 */