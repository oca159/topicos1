package org.osmdroid.tileprovider.modules;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TileWriter
  implements IFilesystemCache, OpenStreetMapTileProviderConstants
{
  private static final Logger logger = LoggerFactory.getLogger(TileWriter.class);
  private static long mUsedCacheSpace;

  public TileWriter()
  {
    1 local1 = new Thread()
    {
      public void run()
      {
        TileWriter.this.calculateDirectorySize(TileWriter.TILE_PATH_BASE);
        if (TileWriter.mUsedCacheSpace > 629145600L)
          TileWriter.this.cutCurrentCache();
      }
    };
    local1.setPriority(1);
    local1.start();
  }

  private void calculateDirectorySize(File paramFile)
  {
    File[] arrayOfFile = paramFile.listFiles();
    int i;
    if (arrayOfFile != null)
      i = arrayOfFile.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      File localFile = arrayOfFile[j];
      if (localFile.isFile())
        mUsedCacheSpace += localFile.length();
      if ((!localFile.isDirectory()) || (isSymbolicDirectoryLink(paramFile, localFile)))
        continue;
      calculateDirectorySize(localFile);
    }
  }

  private boolean createFolderAndCheckIfExists(File paramFile)
  {
    if (paramFile.mkdirs());
    while (true)
    {
      return true;
      try
      {
        Thread.sleep(500L);
        label15: if (paramFile.exists())
          continue;
        return false;
      }
      catch (InterruptedException localInterruptedException)
      {
        break label15;
      }
    }
  }

  private void cutCurrentCache()
  {
    int i;
    int j;
    synchronized (TILE_PATH_BASE)
    {
      File[] arrayOfFile;
      if (mUsedCacheSpace > 524288000L)
      {
        logger.info("Trimming tile cache from " + mUsedCacheSpace + " to " + 524288000L);
        arrayOfFile = (File[])getDirectoryFileList(TILE_PATH_BASE).toArray(new File[0]);
        Arrays.sort(arrayOfFile, new Comparator()
        {
          public int compare(File paramFile1, File paramFile2)
          {
            return Long.valueOf(paramFile1.lastModified()).compareTo(Long.valueOf(paramFile2.lastModified()));
          }
        });
        i = arrayOfFile.length;
        j = 0;
      }
      else
      {
        label108: File localFile2;
        do
        {
          logger.info("Finished trimming tile cache");
          return;
          localFile2 = arrayOfFile[j];
        }
        while (mUsedCacheSpace <= 524288000L);
        long l = localFile2.length();
        if (!localFile2.delete())
          break label166;
        mUsedCacheSpace -= l;
      }
    }
    while (true)
    {
      if (j < i)
        break label108;
      break;
      label166: j++;
    }
  }

  private List<File> getDirectoryFileList(File paramFile)
  {
    ArrayList localArrayList = new ArrayList();
    File[] arrayOfFile = paramFile.listFiles();
    int i;
    if (arrayOfFile != null)
      i = arrayOfFile.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localArrayList;
      File localFile = arrayOfFile[j];
      if (localFile.isFile())
        localArrayList.add(localFile);
      if (!localFile.isDirectory())
        continue;
      localArrayList.addAll(getDirectoryFileList(localFile));
    }
  }

  public static long getUsedCacheSpace()
  {
    return mUsedCacheSpace;
  }

  private boolean isSymbolicDirectoryLink(File paramFile1, File paramFile2)
  {
    try
    {
      boolean bool = paramFile1.getCanonicalPath().equals(paramFile2.getCanonicalFile().getParent());
      return !bool;
    }
    catch (IOException localIOException)
    {
    }
    return false;
  }

  // ERROR //
  public boolean saveFile(org.osmdroid.tileprovider.tilesource.ITileSource paramITileSource, org.osmdroid.tileprovider.MapTile paramMapTile, java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 55	java/io/File
    //   3: dup
    //   4: getstatic 93	org/osmdroid/tileprovider/modules/TileWriter:TILE_PATH_BASE	Ljava/io/File;
    //   7: new 97	java/lang/StringBuilder
    //   10: dup
    //   11: aload_1
    //   12: aload_2
    //   13: invokeinterface 183 2 0
    //   18: invokestatic 187	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   21: invokespecial 102	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   24: ldc 189
    //   26: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: invokevirtual 115	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   32: invokespecial 192	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   35: astore 4
    //   37: aload 4
    //   39: invokevirtual 195	java/io/File:getParentFile	()Ljava/io/File;
    //   42: astore 5
    //   44: aload 5
    //   46: invokevirtual 89	java/io/File:exists	()Z
    //   49: ifne +14 -> 63
    //   52: aload_0
    //   53: aload 5
    //   55: invokespecial 197	org/osmdroid/tileprovider/modules/TileWriter:createFolderAndCheckIfExists	(Ljava/io/File;)Z
    //   58: ifne +5 -> 63
    //   61: iconst_0
    //   62: ireturn
    //   63: aconst_null
    //   64: astore 6
    //   66: new 199	java/io/BufferedOutputStream
    //   69: dup
    //   70: new 201	java/io/FileOutputStream
    //   73: dup
    //   74: aload 4
    //   76: invokevirtual 204	java/io/File:getPath	()Ljava/lang/String;
    //   79: invokespecial 205	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   82: sipush 8192
    //   85: invokespecial 208	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   88: astore 7
    //   90: aload_3
    //   91: aload 7
    //   93: invokestatic 214	org/osmdroid/tileprovider/util/StreamUtils:copy	(Ljava/io/InputStream;Ljava/io/OutputStream;)J
    //   96: getstatic 49	org/osmdroid/tileprovider/modules/TileWriter:mUsedCacheSpace	J
    //   99: ladd
    //   100: putstatic 49	org/osmdroid/tileprovider/modules/TileWriter:mUsedCacheSpace	J
    //   103: getstatic 49	org/osmdroid/tileprovider/modules/TileWriter:mUsedCacheSpace	J
    //   106: ldc2_w 215
    //   109: lcmp
    //   110: ifle +7 -> 117
    //   113: aload_0
    //   114: invokespecial 53	org/osmdroid/tileprovider/modules/TileWriter:cutCurrentCache	()V
    //   117: aload 7
    //   119: ifnull +8 -> 127
    //   122: aload 7
    //   124: invokestatic 220	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
    //   127: iconst_1
    //   128: ireturn
    //   129: astore 10
    //   131: aload 6
    //   133: ifnull -72 -> 61
    //   136: aload 6
    //   138: invokestatic 220	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
    //   141: iconst_0
    //   142: ireturn
    //   143: astore 9
    //   145: aload 6
    //   147: ifnull +8 -> 155
    //   150: aload 6
    //   152: invokestatic 220	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
    //   155: aload 9
    //   157: athrow
    //   158: astore 9
    //   160: aload 7
    //   162: astore 6
    //   164: goto -19 -> 145
    //   167: astore 8
    //   169: aload 7
    //   171: astore 6
    //   173: goto -42 -> 131
    //
    // Exception table:
    //   from	to	target	type
    //   66	90	129	java/io/IOException
    //   66	90	143	finally
    //   90	117	158	finally
    //   90	117	167	java/io/IOException
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.modules.TileWriter
 * JD-Core Version:    0.6.0
 */