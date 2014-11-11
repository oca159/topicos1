package org.osmdroid.tileprovider.modules;

import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapTileDownloader extends MapTileModuleProviderBase
{
  private static final Logger logger = LoggerFactory.getLogger(MapTileDownloader.class);
  private final IFilesystemCache mFilesystemCache;
  private final INetworkAvailablityCheck mNetworkAvailablityCheck;
  private OnlineTileSourceBase mTileSource;

  public MapTileDownloader(ITileSource paramITileSource)
  {
    this(paramITileSource, null, null);
  }

  public MapTileDownloader(ITileSource paramITileSource, IFilesystemCache paramIFilesystemCache)
  {
    this(paramITileSource, paramIFilesystemCache, null);
  }

  public MapTileDownloader(ITileSource paramITileSource, IFilesystemCache paramIFilesystemCache, INetworkAvailablityCheck paramINetworkAvailablityCheck)
  {
    super(2, 40);
    this.mFilesystemCache = paramIFilesystemCache;
    this.mNetworkAvailablityCheck = paramINetworkAvailablityCheck;
    setTileSource(paramITileSource);
  }

  public int getMaximumZoomLevel()
  {
    if (this.mTileSource != null)
      return this.mTileSource.getMaximumZoomLevel();
    return 0;
  }

  public int getMinimumZoomLevel()
  {
    if (this.mTileSource != null)
      return this.mTileSource.getMinimumZoomLevel();
    return 22;
  }

  protected String getName()
  {
    return "Online Tile Download Provider";
  }

  protected String getThreadGroupName()
  {
    return "downloader";
  }

  protected Runnable getTileLoader()
  {
    return new TileLoader(null);
  }

  public ITileSource getTileSource()
  {
    return this.mTileSource;
  }

  public boolean getUsesDataConnection()
  {
    return true;
  }

  public void setTileSource(ITileSource paramITileSource)
  {
    if ((paramITileSource instanceof OnlineTileSourceBase))
    {
      this.mTileSource = ((OnlineTileSourceBase)paramITileSource);
      return;
    }
    this.mTileSource = null;
  }

  private class TileLoader extends MapTileModuleProviderBase.TileLoader
  {
    private TileLoader()
    {
      super();
    }

    // ERROR //
    public android.graphics.drawable.Drawable loadTile(org.osmdroid.tileprovider.MapTileRequestState paramMapTileRequestState)
      throws MapTileModuleProviderBase.CantContinueException
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 10	org/osmdroid/tileprovider/modules/MapTileDownloader$TileLoader:this$0	Lorg/osmdroid/tileprovider/modules/MapTileDownloader;
      //   4: invokestatic 36	org/osmdroid/tileprovider/modules/MapTileDownloader:access$0	(Lorg/osmdroid/tileprovider/modules/MapTileDownloader;)Lorg/osmdroid/tileprovider/tilesource/OnlineTileSourceBase;
      //   7: ifnonnull +5 -> 12
      //   10: aconst_null
      //   11: areturn
      //   12: aconst_null
      //   13: astore_2
      //   14: aconst_null
      //   15: astore_3
      //   16: aload_1
      //   17: invokevirtual 42	org/osmdroid/tileprovider/MapTileRequestState:getMapTile	()Lorg/osmdroid/tileprovider/MapTile;
      //   20: astore 4
      //   22: aload_0
      //   23: getfield 10	org/osmdroid/tileprovider/modules/MapTileDownloader$TileLoader:this$0	Lorg/osmdroid/tileprovider/modules/MapTileDownloader;
      //   26: invokestatic 46	org/osmdroid/tileprovider/modules/MapTileDownloader:access$1	(Lorg/osmdroid/tileprovider/modules/MapTileDownloader;)Lorg/osmdroid/tileprovider/modules/INetworkAvailablityCheck;
      //   29: astore 11
      //   31: aconst_null
      //   32: astore_2
      //   33: aconst_null
      //   34: astore_3
      //   35: aload 11
      //   37: ifnull +32 -> 69
      //   40: aload_0
      //   41: getfield 10	org/osmdroid/tileprovider/modules/MapTileDownloader$TileLoader:this$0	Lorg/osmdroid/tileprovider/modules/MapTileDownloader;
      //   44: invokestatic 46	org/osmdroid/tileprovider/modules/MapTileDownloader:access$1	(Lorg/osmdroid/tileprovider/modules/MapTileDownloader;)Lorg/osmdroid/tileprovider/modules/INetworkAvailablityCheck;
      //   47: invokeinterface 52 1 0
      //   52: istore 12
      //   54: iload 12
      //   56: ifne +13 -> 69
      //   59: aconst_null
      //   60: invokestatic 58	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
      //   63: aconst_null
      //   64: invokestatic 58	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
      //   67: aconst_null
      //   68: areturn
      //   69: new 60	java/io/BufferedInputStream
      //   72: dup
      //   73: new 62	java/net/URL
      //   76: dup
      //   77: aload_0
      //   78: getfield 10	org/osmdroid/tileprovider/modules/MapTileDownloader$TileLoader:this$0	Lorg/osmdroid/tileprovider/modules/MapTileDownloader;
      //   81: invokestatic 36	org/osmdroid/tileprovider/modules/MapTileDownloader:access$0	(Lorg/osmdroid/tileprovider/modules/MapTileDownloader;)Lorg/osmdroid/tileprovider/tilesource/OnlineTileSourceBase;
      //   84: aload 4
      //   86: invokevirtual 68	org/osmdroid/tileprovider/tilesource/OnlineTileSourceBase:getTileURLString	(Lorg/osmdroid/tileprovider/MapTile;)Ljava/lang/String;
      //   89: invokespecial 71	java/net/URL:<init>	(Ljava/lang/String;)V
      //   92: invokevirtual 75	java/net/URL:openStream	()Ljava/io/InputStream;
      //   95: sipush 8192
      //   98: invokespecial 78	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;I)V
      //   101: astore 13
      //   103: new 80	java/io/ByteArrayOutputStream
      //   106: dup
      //   107: invokespecial 83	java/io/ByteArrayOutputStream:<init>	()V
      //   110: astore 14
      //   112: new 85	java/io/BufferedOutputStream
      //   115: dup
      //   116: aload 14
      //   118: sipush 8192
      //   121: invokespecial 88	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
      //   124: astore 15
      //   126: aload 13
      //   128: aload 15
      //   130: invokestatic 92	org/osmdroid/tileprovider/util/StreamUtils:copy	(Ljava/io/InputStream;Ljava/io/OutputStream;)J
      //   133: pop2
      //   134: aload 15
      //   136: invokevirtual 97	java/io/OutputStream:flush	()V
      //   139: new 99	java/io/ByteArrayInputStream
      //   142: dup
      //   143: aload 14
      //   145: invokevirtual 103	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   148: invokespecial 106	java/io/ByteArrayInputStream:<init>	([B)V
      //   151: astore 18
      //   153: aload_0
      //   154: getfield 10	org/osmdroid/tileprovider/modules/MapTileDownloader$TileLoader:this$0	Lorg/osmdroid/tileprovider/modules/MapTileDownloader;
      //   157: invokestatic 110	org/osmdroid/tileprovider/modules/MapTileDownloader:access$2	(Lorg/osmdroid/tileprovider/modules/MapTileDownloader;)Lorg/osmdroid/tileprovider/modules/IFilesystemCache;
      //   160: ifnull +32 -> 192
      //   163: aload_0
      //   164: getfield 10	org/osmdroid/tileprovider/modules/MapTileDownloader$TileLoader:this$0	Lorg/osmdroid/tileprovider/modules/MapTileDownloader;
      //   167: invokestatic 110	org/osmdroid/tileprovider/modules/MapTileDownloader:access$2	(Lorg/osmdroid/tileprovider/modules/MapTileDownloader;)Lorg/osmdroid/tileprovider/modules/IFilesystemCache;
      //   170: aload_0
      //   171: getfield 10	org/osmdroid/tileprovider/modules/MapTileDownloader$TileLoader:this$0	Lorg/osmdroid/tileprovider/modules/MapTileDownloader;
      //   174: invokestatic 36	org/osmdroid/tileprovider/modules/MapTileDownloader:access$0	(Lorg/osmdroid/tileprovider/modules/MapTileDownloader;)Lorg/osmdroid/tileprovider/tilesource/OnlineTileSourceBase;
      //   177: aload 4
      //   179: aload 18
      //   181: invokeinterface 116 4 0
      //   186: pop
      //   187: aload 18
      //   189: invokevirtual 119	java/io/ByteArrayInputStream:reset	()V
      //   192: aload_0
      //   193: getfield 10	org/osmdroid/tileprovider/modules/MapTileDownloader$TileLoader:this$0	Lorg/osmdroid/tileprovider/modules/MapTileDownloader;
      //   196: invokestatic 36	org/osmdroid/tileprovider/modules/MapTileDownloader:access$0	(Lorg/osmdroid/tileprovider/modules/MapTileDownloader;)Lorg/osmdroid/tileprovider/tilesource/OnlineTileSourceBase;
      //   199: aload 18
      //   201: invokevirtual 123	org/osmdroid/tileprovider/tilesource/OnlineTileSourceBase:getDrawable	(Ljava/io/InputStream;)Landroid/graphics/drawable/Drawable;
      //   204: astore 19
      //   206: aload 13
      //   208: invokestatic 58	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
      //   211: aload 15
      //   213: invokestatic 58	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
      //   216: aload 19
      //   218: areturn
      //   219: astore 10
      //   221: invokestatic 127	org/osmdroid/tileprovider/modules/MapTileDownloader:access$3	()Lorg/slf4j/Logger;
      //   224: new 129	java/lang/StringBuilder
      //   227: dup
      //   228: ldc 131
      //   230: invokespecial 132	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   233: aload 4
      //   235: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   238: ldc 138
      //   240: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   243: aload 10
      //   245: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   248: invokevirtual 145	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   251: invokeinterface 150 2 0
      //   256: new 20	org/osmdroid/tileprovider/modules/MapTileModuleProviderBase$CantContinueException
      //   259: dup
      //   260: aload_0
      //   261: getfield 10	org/osmdroid/tileprovider/modules/MapTileDownloader$TileLoader:this$0	Lorg/osmdroid/tileprovider/modules/MapTileDownloader;
      //   264: aload 10
      //   266: invokespecial 153	org/osmdroid/tileprovider/modules/MapTileModuleProviderBase$CantContinueException:<init>	(Lorg/osmdroid/tileprovider/modules/MapTileModuleProviderBase;Ljava/lang/Throwable;)V
      //   269: athrow
      //   270: astore 6
      //   272: aload_2
      //   273: invokestatic 58	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
      //   276: aload_3
      //   277: invokestatic 58	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
      //   280: aload 6
      //   282: athrow
      //   283: astore 9
      //   285: invokestatic 127	org/osmdroid/tileprovider/modules/MapTileDownloader:access$3	()Lorg/slf4j/Logger;
      //   288: new 129	java/lang/StringBuilder
      //   291: dup
      //   292: ldc 155
      //   294: invokespecial 132	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   297: aload 4
      //   299: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   302: ldc 138
      //   304: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   307: aload 9
      //   309: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   312: invokevirtual 145	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   315: invokeinterface 150 2 0
      //   320: new 20	org/osmdroid/tileprovider/modules/MapTileModuleProviderBase$CantContinueException
      //   323: dup
      //   324: aload_0
      //   325: getfield 10	org/osmdroid/tileprovider/modules/MapTileDownloader$TileLoader:this$0	Lorg/osmdroid/tileprovider/modules/MapTileDownloader;
      //   328: aload 9
      //   330: invokespecial 153	org/osmdroid/tileprovider/modules/MapTileModuleProviderBase$CantContinueException:<init>	(Lorg/osmdroid/tileprovider/modules/MapTileModuleProviderBase;Ljava/lang/Throwable;)V
      //   333: athrow
      //   334: invokestatic 127	org/osmdroid/tileprovider/modules/MapTileDownloader:access$3	()Lorg/slf4j/Logger;
      //   337: new 129	java/lang/StringBuilder
      //   340: dup
      //   341: ldc 157
      //   343: invokespecial 132	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   346: aload 4
      //   348: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   351: ldc 138
      //   353: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   356: aload 8
      //   358: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   361: invokevirtual 145	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   364: invokeinterface 150 2 0
      //   369: aload_2
      //   370: invokestatic 58	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
      //   373: aload_3
      //   374: invokestatic 58	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
      //   377: aconst_null
      //   378: areturn
      //   379: astore 7
      //   381: invokestatic 127	org/osmdroid/tileprovider/modules/MapTileDownloader:access$3	()Lorg/slf4j/Logger;
      //   384: new 129	java/lang/StringBuilder
      //   387: dup
      //   388: ldc 159
      //   390: invokespecial 132	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   393: aload 4
      //   395: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   398: ldc 138
      //   400: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   403: aload 7
      //   405: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   408: invokevirtual 145	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   411: invokeinterface 150 2 0
      //   416: aload_2
      //   417: invokestatic 58	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
      //   420: aload_3
      //   421: invokestatic 58	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
      //   424: aconst_null
      //   425: areturn
      //   426: astore 5
      //   428: invokestatic 127	org/osmdroid/tileprovider/modules/MapTileDownloader:access$3	()Lorg/slf4j/Logger;
      //   431: new 129	java/lang/StringBuilder
      //   434: dup
      //   435: ldc 161
      //   437: invokespecial 132	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   440: aload 4
      //   442: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   445: invokevirtual 145	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   448: aload 5
      //   450: invokeinterface 165 3 0
      //   455: aload_2
      //   456: invokestatic 58	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
      //   459: aload_3
      //   460: invokestatic 58	org/osmdroid/tileprovider/util/StreamUtils:closeStream	(Ljava/io/Closeable;)V
      //   463: aconst_null
      //   464: areturn
      //   465: astore 6
      //   467: aload 13
      //   469: astore_2
      //   470: aconst_null
      //   471: astore_3
      //   472: goto -200 -> 272
      //   475: astore 6
      //   477: aload 15
      //   479: astore_3
      //   480: aload 13
      //   482: astore_2
      //   483: goto -211 -> 272
      //   486: astore 5
      //   488: aload 13
      //   490: astore_2
      //   491: aconst_null
      //   492: astore_3
      //   493: goto -65 -> 428
      //   496: astore 5
      //   498: aload 15
      //   500: astore_3
      //   501: aload 13
      //   503: astore_2
      //   504: goto -76 -> 428
      //   507: astore 7
      //   509: aload 13
      //   511: astore_2
      //   512: aconst_null
      //   513: astore_3
      //   514: goto -133 -> 381
      //   517: astore 7
      //   519: aload 15
      //   521: astore_3
      //   522: aload 13
      //   524: astore_2
      //   525: goto -144 -> 381
      //   528: astore 8
      //   530: aload 13
      //   532: astore_2
      //   533: aconst_null
      //   534: astore_3
      //   535: goto -201 -> 334
      //   538: astore 8
      //   540: aload 15
      //   542: astore_3
      //   543: aload 13
      //   545: astore_2
      //   546: goto -212 -> 334
      //   549: astore 9
      //   551: aload 13
      //   553: astore_2
      //   554: aconst_null
      //   555: astore_3
      //   556: goto -271 -> 285
      //   559: astore 9
      //   561: aload 15
      //   563: astore_3
      //   564: aload 13
      //   566: astore_2
      //   567: goto -282 -> 285
      //   570: astore 10
      //   572: aload 13
      //   574: astore_2
      //   575: aconst_null
      //   576: astore_3
      //   577: goto -356 -> 221
      //   580: astore 10
      //   582: aload 15
      //   584: astore_3
      //   585: aload 13
      //   587: astore_2
      //   588: goto -367 -> 221
      //   591: astore 8
      //   593: aconst_null
      //   594: astore_2
      //   595: aconst_null
      //   596: astore_3
      //   597: goto -263 -> 334
      //
      // Exception table:
      //   from	to	target	type
      //   22	31	219	java/net/UnknownHostException
      //   40	54	219	java/net/UnknownHostException
      //   69	103	219	java/net/UnknownHostException
      //   22	31	270	finally
      //   40	54	270	finally
      //   69	103	270	finally
      //   221	270	270	finally
      //   285	334	270	finally
      //   334	369	270	finally
      //   381	416	270	finally
      //   428	455	270	finally
      //   22	31	283	org/osmdroid/tileprovider/tilesource/BitmapTileSourceBase$LowMemoryException
      //   40	54	283	org/osmdroid/tileprovider/tilesource/BitmapTileSourceBase$LowMemoryException
      //   69	103	283	org/osmdroid/tileprovider/tilesource/BitmapTileSourceBase$LowMemoryException
      //   22	31	379	java/io/IOException
      //   40	54	379	java/io/IOException
      //   69	103	379	java/io/IOException
      //   22	31	426	java/lang/Throwable
      //   40	54	426	java/lang/Throwable
      //   69	103	426	java/lang/Throwable
      //   103	126	465	finally
      //   126	192	475	finally
      //   192	206	475	finally
      //   103	126	486	java/lang/Throwable
      //   126	192	496	java/lang/Throwable
      //   192	206	496	java/lang/Throwable
      //   103	126	507	java/io/IOException
      //   126	192	517	java/io/IOException
      //   192	206	517	java/io/IOException
      //   103	126	528	java/io/FileNotFoundException
      //   126	192	538	java/io/FileNotFoundException
      //   192	206	538	java/io/FileNotFoundException
      //   103	126	549	org/osmdroid/tileprovider/tilesource/BitmapTileSourceBase$LowMemoryException
      //   126	192	559	org/osmdroid/tileprovider/tilesource/BitmapTileSourceBase$LowMemoryException
      //   192	206	559	org/osmdroid/tileprovider/tilesource/BitmapTileSourceBase$LowMemoryException
      //   103	126	570	java/net/UnknownHostException
      //   126	192	580	java/net/UnknownHostException
      //   192	206	580	java/net/UnknownHostException
      //   22	31	591	java/io/FileNotFoundException
      //   40	54	591	java/io/FileNotFoundException
      //   69	103	591	java/io/FileNotFoundException
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.modules.MapTileDownloader
 * JD-Core Version:    0.6.0
 */