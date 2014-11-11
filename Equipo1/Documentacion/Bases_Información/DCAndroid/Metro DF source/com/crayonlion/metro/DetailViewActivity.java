package com.crayonlion.metro;

import android.os.Bundle;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class DetailViewActivity extends EasyFragmentActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903067);
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.findItem(2130968664).setVisible(false);
    paramMenu.findItem(2130968663).setVisible(false);
    paramMenu.findItem(2130968661).setVisible(false);
    return true;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.DetailViewActivity
 * JD-Core Version:    0.6.0
 */