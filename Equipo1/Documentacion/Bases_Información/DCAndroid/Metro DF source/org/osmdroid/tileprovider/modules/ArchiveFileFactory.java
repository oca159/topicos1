package org.osmdroid.tileprovider.modules;

import android.database.sqlite.SQLiteException;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArchiveFileFactory
{
  private static final Logger logger = LoggerFactory.getLogger(ArchiveFileFactory.class);

  public static IArchiveFile getArchiveFile(File paramFile)
  {
    if (paramFile.getName().endsWith(".zip"))
      try
      {
        ZipFileArchive localZipFileArchive = ZipFileArchive.getZipFileArchive(paramFile);
        return localZipFileArchive;
      }
      catch (IOException localIOException2)
      {
        logger.error("Error opening ZIP file", localIOException2);
      }
    if (paramFile.getName().endsWith(".sqlite"))
      try
      {
        DatabaseFileArchive localDatabaseFileArchive = DatabaseFileArchive.getDatabaseFileArchive(paramFile);
        return localDatabaseFileArchive;
      }
      catch (SQLiteException localSQLiteException2)
      {
        logger.error("Error opening SQL file", localSQLiteException2);
      }
    if (paramFile.getName().endsWith(".mbtiles"))
      try
      {
        MBTilesFileArchive localMBTilesFileArchive = MBTilesFileArchive.getDatabaseFileArchive(paramFile);
        return localMBTilesFileArchive;
      }
      catch (SQLiteException localSQLiteException1)
      {
        logger.error("Error opening MBTiles SQLite file", localSQLiteException1);
      }
    if (paramFile.getName().endsWith(".gemf"))
      try
      {
        GEMFFileArchive localGEMFFileArchive = GEMFFileArchive.getGEMFFileArchive(paramFile);
        return localGEMFFileArchive;
      }
      catch (IOException localIOException1)
      {
        logger.error("Error opening GEMF file", localIOException1);
      }
    return null;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.modules.ArchiveFileFactory
 * JD-Core Version:    0.6.0
 */