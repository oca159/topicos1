package org.osmdroid.views.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.ArrayList;
import microsoft.mappoint.TileSystem;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.ResourceProxy.bitmap;
import org.osmdroid.ResourceProxy.string;
import org.osmdroid.tileprovider.MapTile;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.MyMath;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TilesOverlay extends Overlay
  implements IOverlayMenuProvider
{
  public static final int MENU_MAP_MODE;
  public static final int MENU_OFFLINE;
  public static final int MENU_TILE_SOURCE_STARTING_ID;
  private static final Logger logger = LoggerFactory.getLogger(TilesOverlay.class);
  private int mLoadingBackgroundColor = Color.rgb(216, 208, 208);
  private int mLoadingLineColor = Color.rgb(200, 192, 192);
  private BitmapDrawable mLoadingTile = null;
  private boolean mOptionsMenuEnabled = true;
  protected final Paint mPaint = new Paint();
  protected final MapTileProviderBase mTileProvider;
  private final Rect mTileRect = new Rect();
  private final Rect mViewPort = new Rect();
  private int mWorldSize_2;

  static
  {
    MENU_MAP_MODE = getSafeMenuId();
    MENU_TILE_SOURCE_STARTING_ID = getSafeMenuIdSequence(TileSourceFactory.getTileSources().size());
    MENU_OFFLINE = getSafeMenuId();
  }

  public TilesOverlay(MapTileProviderBase paramMapTileProviderBase, Context paramContext)
  {
    this(paramMapTileProviderBase, new DefaultResourceProxyImpl(paramContext));
  }

  public TilesOverlay(MapTileProviderBase paramMapTileProviderBase, ResourceProxy paramResourceProxy)
  {
    super(paramResourceProxy);
    if (paramMapTileProviderBase == null)
      throw new IllegalArgumentException("You must pass a valid tile provider to the tiles overlay.");
    this.mTileProvider = paramMapTileProviderBase;
  }

  private void clearLoadingTile()
  {
    BitmapDrawable localBitmapDrawable = this.mLoadingTile;
    this.mLoadingTile = null;
    if (localBitmapDrawable != null)
      localBitmapDrawable.getBitmap().recycle();
  }

  private Drawable getLoadingTile()
  {
    if ((this.mLoadingTile == null) && (this.mLoadingBackgroundColor != 0));
    try
    {
      int i;
      Bitmap localBitmap;
      Canvas localCanvas;
      Paint localPaint;
      int j;
      int k;
      if (this.mTileProvider.getTileSource() != null)
      {
        i = this.mTileProvider.getTileSource().getTileSizePixels();
        localBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        localCanvas = new Canvas(localBitmap);
        localPaint = new Paint();
        localCanvas.drawColor(this.mLoadingBackgroundColor);
        localPaint.setColor(this.mLoadingLineColor);
        localPaint.setStrokeWidth(0.0F);
        j = i / 16;
        k = 0;
      }
      while (true)
      {
        if (k >= i)
        {
          this.mLoadingTile = new BitmapDrawable(localBitmap);
          return this.mLoadingTile;
          i = 256;
          break;
        }
        float f1 = k;
        float f2 = i;
        float f3 = k;
        localCanvas.drawLine(0.0F, f1, f2, f3, localPaint);
        localCanvas.drawLine(k, 0.0F, k, i, localPaint);
        int m;
        k += j;
      }
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      while (true)
      {
        logger.error("OutOfMemoryError getting loading tile");
        System.gc();
      }
    }
  }

  protected void draw(Canvas paramCanvas, MapView paramMapView, boolean paramBoolean)
  {
    if (paramBoolean)
      return;
    MapView.Projection localProjection = paramMapView.getProjection();
    this.mWorldSize_2 = (TileSystem.MapSize(localProjection.getZoomLevel()) / 2);
    this.mViewPort.set(localProjection.getScreenRect());
    this.mViewPort.offset(this.mWorldSize_2, this.mWorldSize_2);
    drawTiles(paramCanvas, localProjection.getZoomLevel(), TileSystem.getTileSize(), this.mViewPort);
  }

  public void drawTiles(Canvas paramCanvas, int paramInt1, int paramInt2, Rect paramRect)
  {
    Point localPoint1 = TileSystem.PixelXYToTileXY(paramRect.left, paramRect.top, null);
    localPoint1.offset(-1, -1);
    Point localPoint2 = TileSystem.PixelXYToTileXY(paramRect.right, paramRect.bottom, null);
    int i = 1 << paramInt1;
    int j = (1 + (localPoint2.y - localPoint1.y)) * (1 + (localPoint2.x - localPoint1.x));
    this.mTileProvider.ensureCapacity(j);
    int k = localPoint1.y;
    if (k > localPoint2.y)
      return;
    for (int m = localPoint1.x; ; m++)
    {
      if (m > localPoint2.x)
      {
        k++;
        break;
      }
      int n = MyMath.mod(k, i);
      MapTile localMapTile = new MapTile(paramInt1, MyMath.mod(m, i), n);
      Drawable localDrawable = this.mTileProvider.getMapTile(localMapTile);
      if (localDrawable == null)
        localDrawable = getLoadingTile();
      if (localDrawable == null)
        continue;
      this.mTileRect.set(m * paramInt2, k * paramInt2, paramInt2 + m * paramInt2, paramInt2 + k * paramInt2);
      onTileReadyToDraw(paramCanvas, localDrawable, this.mTileRect);
    }
  }

  public int getLoadingBackgroundColor()
  {
    return this.mLoadingBackgroundColor;
  }

  public int getLoadingLineColor()
  {
    return this.mLoadingLineColor;
  }

  public int getMaximumZoomLevel()
  {
    return this.mTileProvider.getMaximumZoomLevel();
  }

  public int getMinimumZoomLevel()
  {
    return this.mTileProvider.getMinimumZoomLevel();
  }

  public boolean isOptionsMenuEnabled()
  {
    return this.mOptionsMenuEnabled;
  }

  public boolean onCreateOptionsMenu(Menu paramMenu, int paramInt, MapView paramMapView)
  {
    SubMenu localSubMenu = paramMenu.addSubMenu(0, paramInt + MENU_MAP_MODE, 0, this.mResourceProxy.getString(ResourceProxy.string.map_mode)).setIcon(this.mResourceProxy.getDrawable(ResourceProxy.bitmap.ic_menu_mapmode));
    int i = 0;
    ResourceProxy localResourceProxy;
    if (i >= TileSourceFactory.getTileSources().size())
    {
      localSubMenu.setGroupCheckable(paramInt + MENU_MAP_MODE, true, true);
      localResourceProxy = paramMapView.getResourceProxy();
      if (!paramMapView.useDataConnection())
        break label192;
    }
    label192: for (ResourceProxy.string localstring = ResourceProxy.string.offline_mode; ; localstring = ResourceProxy.string.online_mode)
    {
      String str = localResourceProxy.getString(localstring);
      Drawable localDrawable = paramMapView.getResourceProxy().getDrawable(ResourceProxy.bitmap.ic_menu_offline);
      paramMenu.add(0, paramInt + MENU_OFFLINE, 0, str).setIcon(localDrawable);
      return true;
      ITileSource localITileSource = (ITileSource)TileSourceFactory.getTileSources().get(i);
      localSubMenu.add(paramInt + MENU_MAP_MODE, paramInt + (i + MENU_TILE_SOURCE_STARTING_ID), 0, localITileSource.localizedName(this.mResourceProxy));
      i++;
      break;
    }
  }

  public void onDetach(MapView paramMapView)
  {
    this.mTileProvider.detach();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem, int paramInt, MapView paramMapView)
  {
    int i = paramMenuItem.getItemId() - paramInt;
    if ((i >= MENU_TILE_SOURCE_STARTING_ID) && (i < MENU_TILE_SOURCE_STARTING_ID + TileSourceFactory.getTileSources().size()))
    {
      paramMapView.setTileSource((ITileSource)TileSourceFactory.getTileSources().get(i - MENU_TILE_SOURCE_STARTING_ID));
      return true;
    }
    if (i == MENU_OFFLINE)
    {
      boolean bool1 = paramMapView.useDataConnection();
      boolean bool2 = false;
      if (bool1);
      while (true)
      {
        paramMapView.setUseDataConnection(bool2);
        return true;
        bool2 = true;
      }
    }
    return false;
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu, int paramInt, MapView paramMapView)
  {
    int i = TileSourceFactory.getTileSources().indexOf(paramMapView.getTileProvider().getTileSource());
    if (i >= 0)
      paramMenu.findItem(paramInt + (i + MENU_TILE_SOURCE_STARTING_ID)).setChecked(true);
    MenuItem localMenuItem = paramMenu.findItem(paramInt + MENU_OFFLINE);
    ResourceProxy localResourceProxy = paramMapView.getResourceProxy();
    if (paramMapView.useDataConnection());
    for (ResourceProxy.string localstring = ResourceProxy.string.offline_mode; ; localstring = ResourceProxy.string.online_mode)
    {
      localMenuItem.setTitle(localResourceProxy.getString(localstring));
      return true;
    }
  }

  protected void onTileReadyToDraw(Canvas paramCanvas, Drawable paramDrawable, Rect paramRect)
  {
    paramRect.offset(-this.mWorldSize_2, -this.mWorldSize_2);
    paramDrawable.setBounds(paramRect);
    paramDrawable.draw(paramCanvas);
  }

  public void setAlpha(int paramInt)
  {
    this.mPaint.setAlpha(paramInt);
  }

  public void setLoadingBackgroundColor(int paramInt)
  {
    if (this.mLoadingBackgroundColor != paramInt)
    {
      this.mLoadingBackgroundColor = paramInt;
      clearLoadingTile();
    }
  }

  public void setLoadingLineColor(int paramInt)
  {
    if (this.mLoadingLineColor != paramInt)
    {
      this.mLoadingLineColor = paramInt;
      clearLoadingTile();
    }
  }

  public void setOptionsMenuEnabled(boolean paramBoolean)
  {
    this.mOptionsMenuEnabled = paramBoolean;
  }

  public void setUseDataConnection(boolean paramBoolean)
  {
    this.mTileProvider.setUseDataConnection(paramBoolean);
  }

  public boolean useDataConnection()
  {
    return this.mTileProvider.useDataConnection();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.overlay.TilesOverlay
 * JD-Core Version:    0.6.0
 */