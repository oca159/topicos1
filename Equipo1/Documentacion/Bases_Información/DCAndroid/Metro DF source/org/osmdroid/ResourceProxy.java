package org.osmdroid;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public abstract interface ResourceProxy
{
  public abstract Bitmap getBitmap(bitmap parambitmap);

  public abstract float getDisplayMetricsDensity();

  public abstract Drawable getDrawable(bitmap parambitmap);

  public abstract String getString(string paramstring);

  public abstract String getString(string paramstring, Object[] paramArrayOfObject);

  public static enum bitmap
  {
    static
    {
      center = new bitmap("center", 1);
      direction_arrow = new bitmap("direction_arrow", 2);
      marker_default = new bitmap("marker_default", 3);
      marker_default_focused_base = new bitmap("marker_default_focused_base", 4);
      navto_small = new bitmap("navto_small", 5);
      next = new bitmap("next", 6);
      previous = new bitmap("previous", 7);
      person = new bitmap("person", 8);
      ic_menu_offline = new bitmap("ic_menu_offline", 9);
      ic_menu_mylocation = new bitmap("ic_menu_mylocation", 10);
      ic_menu_compass = new bitmap("ic_menu_compass", 11);
      ic_menu_mapmode = new bitmap("ic_menu_mapmode", 12);
      bitmap[] arrayOfbitmap = new bitmap[13];
      arrayOfbitmap[0] = unknown;
      arrayOfbitmap[1] = center;
      arrayOfbitmap[2] = direction_arrow;
      arrayOfbitmap[3] = marker_default;
      arrayOfbitmap[4] = marker_default_focused_base;
      arrayOfbitmap[5] = navto_small;
      arrayOfbitmap[6] = next;
      arrayOfbitmap[7] = previous;
      arrayOfbitmap[8] = person;
      arrayOfbitmap[9] = ic_menu_offline;
      arrayOfbitmap[10] = ic_menu_mylocation;
      arrayOfbitmap[11] = ic_menu_compass;
      arrayOfbitmap[12] = ic_menu_mapmode;
      ENUM$VALUES = arrayOfbitmap;
    }
  }

  public static enum string
  {
    static
    {
      mapnik = new string("mapnik", 1);
      cyclemap = new string("cyclemap", 2);
      public_transport = new string("public_transport", 3);
      base = new string("base", 4);
      topo = new string("topo", 5);
      hills = new string("hills", 6);
      cloudmade_small = new string("cloudmade_small", 7);
      cloudmade_standard = new string("cloudmade_standard", 8);
      mapquest_osm = new string("mapquest_osm", 9);
      fiets_nl = new string("fiets_nl", 10);
      base_nl = new string("base_nl", 11);
      roads_nl = new string("roads_nl", 12);
      unknown = new string("unknown", 13);
      format_distance_meters = new string("format_distance_meters", 14);
      format_distance_kilometers = new string("format_distance_kilometers", 15);
      format_distance_miles = new string("format_distance_miles", 16);
      format_distance_nautical_miles = new string("format_distance_nautical_miles", 17);
      format_distance_feet = new string("format_distance_feet", 18);
      online_mode = new string("online_mode", 19);
      offline_mode = new string("offline_mode", 20);
      my_location = new string("my_location", 21);
      compass = new string("compass", 22);
      map_mode = new string("map_mode", 23);
      string[] arrayOfstring = new string[24];
      arrayOfstring[0] = osmarender;
      arrayOfstring[1] = mapnik;
      arrayOfstring[2] = cyclemap;
      arrayOfstring[3] = public_transport;
      arrayOfstring[4] = base;
      arrayOfstring[5] = topo;
      arrayOfstring[6] = hills;
      arrayOfstring[7] = cloudmade_small;
      arrayOfstring[8] = cloudmade_standard;
      arrayOfstring[9] = mapquest_osm;
      arrayOfstring[10] = fiets_nl;
      arrayOfstring[11] = base_nl;
      arrayOfstring[12] = roads_nl;
      arrayOfstring[13] = unknown;
      arrayOfstring[14] = format_distance_meters;
      arrayOfstring[15] = format_distance_kilometers;
      arrayOfstring[16] = format_distance_miles;
      arrayOfstring[17] = format_distance_nautical_miles;
      arrayOfstring[18] = format_distance_feet;
      arrayOfstring[19] = online_mode;
      arrayOfstring[20] = offline_mode;
      arrayOfstring[21] = my_location;
      arrayOfstring[22] = compass;
      arrayOfstring[23] = map_mode;
      ENUM$VALUES = arrayOfstring;
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.ResourceProxy
 * JD-Core Version:    0.6.0
 */