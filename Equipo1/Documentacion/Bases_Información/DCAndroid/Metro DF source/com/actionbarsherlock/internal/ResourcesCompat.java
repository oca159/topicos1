package com.actionbarsherlock.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.actionbarsherlock.R.bool;
import com.actionbarsherlock.R.integer;

public final class ResourcesCompat
{
  public static boolean getResources_getBoolean(Context paramContext, int paramInt)
  {
    boolean bool = true;
    if (Build.VERSION.SDK_INT >= 13)
      bool = paramContext.getResources().getBoolean(paramInt);
    while (true)
    {
      return bool;
      DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
      float f1 = localDisplayMetrics.widthPixels / localDisplayMetrics.density;
      float f2 = localDisplayMetrics.heightPixels / localDisplayMetrics.density;
      float f3;
      if (f1 < f2)
        f3 = f1;
      while (true)
      {
        if (paramInt != R.bool.abs__action_bar_embed_tabs)
          break label89;
        if (f1 >= 480.0F)
          break;
        return false;
        f3 = f2;
      }
      label89: if (paramInt == R.bool.abs__split_action_bar_is_narrow)
        if (f1 >= 480.0F)
          return false;
      if (paramInt == R.bool.abs__action_bar_expanded_action_views_exclusive)
        if (f3 >= 600.0F)
          return false;
      if (paramInt != R.bool.abs__config_allowActionMenuItemTextWithIcon)
        break;
      if (f1 < 480.0F)
        return false;
    }
    throw new IllegalArgumentException("Unknown boolean resource ID " + paramInt);
  }

  public static int getResources_getInteger(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 13)
      return paramContext.getResources().getInteger(paramInt);
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    float f = localDisplayMetrics.widthPixels / localDisplayMetrics.density;
    if (paramInt == R.integer.abs__max_action_buttons)
    {
      if (f >= 600.0F)
        return 5;
      if (f >= 500.0F)
        return 4;
      if (f >= 360.0F)
        return 3;
      return 2;
    }
    throw new IllegalArgumentException("Unknown integer resource ID " + paramInt);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.internal.ResourcesCompat
 * JD-Core Version:    0.6.0
 */