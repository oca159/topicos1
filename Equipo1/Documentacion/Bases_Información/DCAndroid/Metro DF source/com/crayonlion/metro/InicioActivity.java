package com.crayonlion.metro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;

public class InicioActivity extends SherlockActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    SharedPreferences localSharedPreferences = getSharedPreferences("PrimeraVez", 0);
    if (localSharedPreferences.getBoolean("yaIniciada", false) == true)
    {
      startActivity(new Intent(this, MetroDFActivity.class));
      finish();
      return;
    }
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    localEditor.putBoolean("yaIniciada", true);
    localEditor.commit();
    startActivity(new Intent(this, TutorialActivity.class).putExtra("origen", 0));
    finish();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.InicioActivity
 * JD-Core Version:    0.6.0
 */