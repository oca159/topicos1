package com.actionbarsherlock.internal.view.menu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

public class MenuItemMule
  implements android.view.MenuItem
{
  private static final String ERROR = "Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.";
  private final com.actionbarsherlock.view.MenuItem mItem;

  public MenuItemMule(com.actionbarsherlock.view.MenuItem paramMenuItem)
  {
    this.mItem = paramMenuItem;
  }

  public boolean collapseActionView()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public boolean expandActionView()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public ActionProvider getActionProvider()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public View getActionView()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public char getAlphabeticShortcut()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public int getGroupId()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public Drawable getIcon()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public Intent getIntent()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public int getItemId()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public ContextMenu.ContextMenuInfo getMenuInfo()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public char getNumericShortcut()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public int getOrder()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public SubMenu getSubMenu()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public CharSequence getTitle()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public CharSequence getTitleCondensed()
  {
    return this.mItem.getTitleCondensed();
  }

  public boolean hasSubMenu()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public boolean isActionViewExpanded()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public boolean isCheckable()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public boolean isChecked()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public boolean isEnabled()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public boolean isVisible()
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setActionProvider(ActionProvider paramActionProvider)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setActionView(int paramInt)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setActionView(View paramView)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setAlphabeticShortcut(char paramChar)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setCheckable(boolean paramBoolean)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setChecked(boolean paramBoolean)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setEnabled(boolean paramBoolean)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setIcon(int paramInt)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setIcon(Drawable paramDrawable)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setIntent(Intent paramIntent)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setNumericShortcut(char paramChar)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener paramOnActionExpandListener)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setShortcut(char paramChar1, char paramChar2)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public void setShowAsAction(int paramInt)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setShowAsActionFlags(int paramInt)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setTitle(int paramInt)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setTitle(CharSequence paramCharSequence)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setTitleCondensed(CharSequence paramCharSequence)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public android.view.MenuItem setVisible(boolean paramBoolean)
  {
    throw new IllegalStateException("Cannot interact with object designed for temporary instance passing. Make sure you using both SherlockFragmentActivity and SherlockFragment.");
  }

  public com.actionbarsherlock.view.MenuItem unwrap()
  {
    return this.mItem;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.internal.view.menu.MenuItemMule
 * JD-Core Version:    0.6.0
 */