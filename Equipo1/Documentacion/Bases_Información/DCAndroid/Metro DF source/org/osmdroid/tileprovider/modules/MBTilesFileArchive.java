package org.osmdroid.tileprovider.modules;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MBTilesFileArchive
  implements IArchiveFile
{
  public static final String COL_TILES_TILE_COLUMN = "tile_column";
  public static final String COL_TILES_TILE_DATA = "tile_data";
  public static final String COL_TILES_TILE_ROW = "tile_row";
  public static final String COL_TILES_ZOOM_LEVEL = "zoom_level";
  private static final String CREATE_TILES = "CREATE TABLE tiles( zoom_level INTEGER, tile_column INTEGER, tile_row INTEGER, tile_data BLOB)";
  public static final String TABLE_TILES = "tiles";
  private static final Logger logger = LoggerFactory.getLogger(MBTilesFileArchive.class);
  private final SQLiteDatabase mDatabase;

  private MBTilesFileArchive(SQLiteDatabase paramSQLiteDatabase)
  {
    this.mDatabase = paramSQLiteDatabase;
  }

  public static MBTilesFileArchive getDatabaseFileArchive(File paramFile)
    throws SQLiteException
  {
    return new MBTilesFileArchive(SQLiteDatabase.openDatabase(paramFile.getAbsolutePath(), null, 1));
  }

  public InputStream getInputStream(ITileSource paramITileSource, MapTile paramMapTile)
  {
    try
    {
      String[] arrayOfString1 = { "tile_data" };
      String[] arrayOfString2 = new String[3];
      arrayOfString2[0] = Integer.toString(paramMapTile.getX());
      arrayOfString2[1] = Double.toString(Math.pow(2.0D, paramMapTile.getZoomLevel()) - paramMapTile.getY() - 1.0D);
      arrayOfString2[2] = Integer.toString(paramMapTile.getZoomLevel());
      Cursor localCursor = this.mDatabase.query("tiles", arrayOfString1, "tile_column=? and tile_row=? and zoom_level=?", arrayOfString2, null, null, null);
      int i = localCursor.getCount();
      ByteArrayInputStream localByteArrayInputStream = null;
      if (i != 0)
      {
        localCursor.moveToFirst();
        localByteArrayInputStream = new ByteArrayInputStream(localCursor.getBlob(0));
      }
      localCursor.close();
      if (localByteArrayInputStream != null)
        return localByteArrayInputStream;
    }
    catch (Throwable localThrowable)
    {
      logger.warn("Error getting db stream: " + paramMapTile, localThrowable);
    }
    return null;
  }

  public String toString()
  {
    return "DatabaseFileArchive [mDatabase=" + this.mDatabase.getPath() + "]";
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.modules.MBTilesFileArchive
 * JD-Core Version:    0.6.0
 */