package com.actionbarsherlock.internal.view.menu;

import android.content.ComponentName;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;

public class MenuMule
  implements android.view.Menu
{
  private static final String ERROR = "Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.";
  public boolean mDispatchShow = false;
  private final com.actionbarsherlock.view.Menu mMenu;

  public MenuMule(com.actionbarsherlock.view.Menu paramMenu)
  {
    this.mMenu = paramMenu;
  }

  public MenuItem add(int paramInt)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public MenuItem add(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public MenuItem add(int paramInt1, int paramInt2, int paramInt3, CharSequence paramCharSequence)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public MenuItem add(CharSequence paramCharSequence)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public int addIntentOptions(int paramInt1, int paramInt2, int paramInt3, ComponentName paramComponentName, Intent[] paramArrayOfIntent, Intent paramIntent, int paramInt4, MenuItem[] paramArrayOfMenuItem)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public SubMenu addSubMenu(int paramInt)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public SubMenu addSubMenu(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public SubMenu addSubMenu(int paramInt1, int paramInt2, int paramInt3, CharSequence paramCharSequence)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public SubMenu addSubMenu(CharSequence paramCharSequence)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public void clear()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public void close()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public MenuItem findItem(int paramInt)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public MenuItem getItem(int paramInt)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public boolean hasVisibleItems()
  {
    return this.mMenu.hasVisibleItems();
  }

  public boolean isShortcutKey(int paramInt, KeyEvent paramKeyEvent)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public boolean performIdentifierAction(int paramInt1, int paramInt2)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public boolean performShortcut(int paramInt1, KeyEvent paramKeyEvent, int paramInt2)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public void removeGroup(int paramInt)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public void removeItem(int paramInt)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public void setGroupCheckable(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public void setGroupEnabled(int paramInt, boolean paramBoolean)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public void setGroupVisible(int paramInt, boolean paramBoolean)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public void setQwertyMode(boolean paramBoolean)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public int size()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public com.actionbarsherlock.view.Menu unwrap()
  {
    return this.mMenu;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.internal.view.menu.MenuMule
 * JD-Core Version:    0.6.0
 */