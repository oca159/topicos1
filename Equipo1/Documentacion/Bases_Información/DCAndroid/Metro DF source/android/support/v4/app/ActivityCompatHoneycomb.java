package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import java.io.FileDescriptor;
import java.io.PrintWriter;

class ActivityCompatHoneycomb
{
  static void dump(Activity paramActivity, String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramActivity.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }

  static void invalidateOptionsMenu(Activity paramActivity)
  {
    paramActivity.invalidateOptionsMenu();
  }

  static void startActivities(Activity paramActivity, Intent[] paramArrayOfIntent)
  {
    paramActivity.startActivities(paramArrayOfIntent);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.app.ActivityCompatHoneycomb
 * JD-Core Version:    0.6.0
 */