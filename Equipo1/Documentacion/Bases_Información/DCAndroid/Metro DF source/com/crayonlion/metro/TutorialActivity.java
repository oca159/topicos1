package com.crayonlion.metro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Gallery;
import com.actionbarsherlock.app.SherlockActivity;

public class TutorialActivity extends SherlockActivity
{
  HelpGalleryAdapter adapter;
  Gallery gallery;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903065);
    int i = getIntent().getExtras().getInt("origen");
    this.gallery = ((Gallery)findViewById(2130968630));
    this.adapter = new HelpGalleryAdapter(this, i);
    this.gallery.setAdapter(this.adapter);
  }

  protected void onPause()
  {
    super.onPause();
    this.adapter.destroy();
    finish();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.TutorialActivity
 * JD-Core Version:    0.6.0
 */