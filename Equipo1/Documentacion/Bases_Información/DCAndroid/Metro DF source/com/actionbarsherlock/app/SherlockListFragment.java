package com.actionbarsherlock.app;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import com.actionbarsherlock.internal.view.menu.MenuItemMule;
import com.actionbarsherlock.internal.view.menu.MenuMule;

public class SherlockListFragment extends ListFragment
{
  private static final String TAG = "SherlockListFragment";
  private SherlockFragmentActivity mActivity;

  public SherlockFragmentActivity getSherlockActivity()
  {
    return this.mActivity;
  }

  public void onAttach(Activity paramActivity)
  {
    if (!(paramActivity instanceof SherlockFragmentActivity))
      throw new IllegalStateException("SherlockListFragment must be attached to a SherlockFragmentActivity.");
    this.mActivity = ((SherlockFragmentActivity)paramActivity);
    super.onAttach(paramActivity);
  }

  public final void onCreateOptionsMenu(android.view.Menu paramMenu, android.view.MenuInflater paramMenuInflater)
  {
    if ((paramMenu instanceof MenuMule))
    {
      MenuMule localMenuMule = (MenuMule)paramMenu;
      localMenuMule.mDispatchShow = true;
      onCreateOptionsMenu(localMenuMule.unwrap(), this.mActivity.getSupportMenuInflater());
    }
  }

  public void onCreateOptionsMenu(com.actionbarsherlock.view.Menu paramMenu, com.actionbarsherlock.view.MenuInflater paramMenuInflater)
  {
  }

  public final boolean onOptionsItemSelected(android.view.MenuItem paramMenuItem)
  {
    if ((paramMenuItem instanceof MenuItemMule))
      return onOptionsItemSelected(((MenuItemMule)paramMenuItem).unwrap());
    return false;
  }

  public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem paramMenuItem)
  {
    return false;
  }

  public final void onPrepareOptionsMenu(android.view.Menu paramMenu)
  {
    if ((paramMenu instanceof MenuMule))
    {
      MenuMule localMenuMule = (MenuMule)paramMenu;
      localMenuMule.mDispatchShow = true;
      onPrepareOptionsMenu(localMenuMule.unwrap());
    }
  }

  public void onPrepareOptionsMenu(com.actionbarsherlock.view.Menu paramMenu)
  {
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.app.SherlockListFragment
 * JD-Core Version:    0.6.0
 */