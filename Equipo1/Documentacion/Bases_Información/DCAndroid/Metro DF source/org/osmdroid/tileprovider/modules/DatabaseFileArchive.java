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

public class DatabaseFileArchive
  implements IArchiveFile
{
  private static final Logger logger = LoggerFactory.getLogger(DatabaseFileArchive.class);
  private final SQLiteDatabase mDatabase;

  private DatabaseFileArchive(SQLiteDatabase paramSQLiteDatabase)
  {
    this.mDatabase = paramSQLiteDatabase;
  }

  public static DatabaseFileArchive getDatabaseFileArchive(File paramFile)
    throws SQLiteException
  {
    return new DatabaseFileArchive(SQLiteDatabase.openOrCreateDatabase(paramFile, null));
  }

  public InputStream getInputStream(ITileSource paramITileSource, MapTile paramMapTile)
  {
    try
    {
      String[] arrayOfString = { "tile" };
      long l1 = paramMapTile.getX();
      long l2 = paramMapTile.getY();
      long l3 = paramMapTile.getZoomLevel();
      long l4 = l2 + (l1 + (l3 << (int)l3) << (int)l3);
      Cursor localCursor = this.mDatabase.query("tiles", arrayOfString, "key = " + l4 + " and provider = '" + paramITileSource.name() + "'", null, null, null, null);
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
 * Qualified Name:     org.osmdroid.tileprovider.modules.DatabaseFileArchive
 * JD-Core Version:    0.6.0
 */