package org.osmdroid.tileprovider.tilesource;

import org.osmdroid.ResourceProxy.string;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.util.CloudmadeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloudmadeTileSource extends OnlineTileSourceBase
  implements IStyledTileSource<Integer>
{
  private static final Logger logger = LoggerFactory.getLogger(CloudmadeTileSource.class);
  private Integer mStyle = Integer.valueOf(1);

  public CloudmadeTileSource(String paramString1, ResourceProxy.string paramstring, int paramInt1, int paramInt2, int paramInt3, String paramString2, String[] paramArrayOfString)
  {
    super(paramString1, paramstring, paramInt1, paramInt2, paramInt3, paramString2, paramArrayOfString);
  }

  public Integer getStyle()
  {
    return this.mStyle;
  }

  public String getTileURLString(MapTile paramMapTile)
  {
    String str1 = CloudmadeUtil.getCloudmadeKey();
    if (str1.length() == 0)
      logger.error("CloudMade key is not set. You should enter it in the manifest and call CloudmadeUtil.retrieveCloudmadeKey()");
    String str2 = CloudmadeUtil.getCloudmadeToken();
    String str3 = getBaseUrl();
    Object[] arrayOfObject = new Object[8];
    arrayOfObject[0] = str1;
    arrayOfObject[1] = this.mStyle;
    arrayOfObject[2] = Integer.valueOf(getTileSizePixels());
    arrayOfObject[3] = Integer.valueOf(paramMapTile.getZoomLevel());
    arrayOfObject[4] = Integer.valueOf(paramMapTile.getX());
    arrayOfObject[5] = Integer.valueOf(paramMapTile.getY());
    arrayOfObject[6] = this.mImageFilenameEnding;
    arrayOfObject[7] = str2;
    return String.format(str3, arrayOfObject);
  }

  public String pathBase()
  {
    if ((this.mStyle == null) || (this.mStyle.intValue() <= 1))
      return this.mName;
    return this.mName + this.mStyle;
  }

  public void setStyle(Integer paramInteger)
  {
    this.mStyle = paramInteger;
  }

  public void setStyle(String paramString)
  {
    try
    {
      this.mStyle = Integer.valueOf(Integer.parseInt(paramString));
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      logger.warn("Error setting integer style: " + paramString);
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.tileprovider.tilesource.CloudmadeTileSource
 * JD-Core Version:    0.6.0
 */