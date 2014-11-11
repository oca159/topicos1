package org.osmdroid.tileprovider.modules;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipFileArchive
  implements IArchiveFile
{
  private static final Logger logger = LoggerFactory.getLogger(ZipFileArchive.class);
  private final ZipFile mZipFile;

  private ZipFileArchive(ZipFile paramZipFile)
  {
    this.mZipFile = paramZipFile;
  }

  public static ZipFileArchive getZipFileArchive(File paramFile)
    throws ZipException, IOException
  {
    return new ZipFileArchive(new ZipFile(paramFile));
  }

  public InputStream getInputStream(ITileSource paramITileSource, MapTile paramMapTile)
  {
    String str = paramITileSource.getTileRelativeFilenameString(paramMapTile);
    try
    {
      ZipEntry localZipEntry = this.mZipFile.getEntry(str);
      if (localZipEntry != null)
      {
        InputStream localInputStream = this.mZipFile.getInputStream(localZipEntry);
        return localInputStream;
      }
    }
    catch (IOException localIOException)
    {
      logger.warn("Error getting zip stream: " + paramMapTile, localIOException);
    }
    return null;
  }

  public String toString()
  {
    return "ZipFileArchive [mZipFile=" + this.mZipFile.getName() + "]";
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.modules.ZipFileArchive
 * JD-Core Version:    0.6.0
 */