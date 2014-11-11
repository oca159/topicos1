package com.crayonlion.metro.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;
import com.crayonlion.daogenerator.Conexion;
import com.crayonlion.daogenerator.Estacion;
import com.crayonlion.metro.controller.interfaces.MapCustomListener;
import com.crayonlion.metro.controller.interfaces.MapDataSource;
import com.crayonlion.metro.model.Point3;
import com.crayonlion.metro.model.Ruta;
import com.crayonlion.metro.model.Tupla;
import com.crayonlion.metro.utils.Console;
import com.crayonlion.metro.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;
import org.osmdroid.views.overlay.Overlay;

public class MetroOverlay extends Overlay
{
  private static final double[] GRADOS;
  private static final int ICON_DELTA = 26;
  private static final int ICON_SIZE = 52;
  private static final float LINE_EXT_WIDTH = 10.0F;
  private static final float LINE_INT_WIDTH = 8.0F;
  private static final float LINE_ROUTE_INT_WIDTH = 8.0F;
  private static final int NUMERO = 147;
  private static final int PIN_SIZE = 15;
  private static final float PIN_WIDTH = 2.0F;
  private static final int POS = 3;
  private static final float SEGMENT_FIN = 2.0F;
  private static final float SEGMENT_INIT = 1.0F;
  private static final float SEGMENT_INIT_TEXT = 2.5F;
  private static final float SEGMENT_POINT_RADIUS = 0.2F;
  private static final float SEGMENT_WIDTH = 5.0F;
  private static final float TEXT_GAP = 1.5F;
  private static final float TEXT_SIZE = 1.0F;
  private static final int TOUCH_RADIUS = 2500;
  private static final int ZOOM_FACTOR = 15;
  private static final int ZOOM_MIN = 12;
  private static final int ZOOM_STEP = 5;
  int actualZoom;
  private AssetManager assetManager;
  Rect auxRect;
  Rect auxRectE6;
  BoundingBoxE6 boundingBox;
  Rect boundingRect;
  ArrayList<Point3> conexion;
  int conexionesActivas;
  boolean drawIcon;
  boolean drawPin;
  boolean drawRuta;
  PointF[] estacionTextDimention;
  PointF[] estacionTextPos;
  String[] estacionesName;
  Tupla<PointF>[] estacionesSegmento;
  int i;
  Bitmap[] icons;
  Point[] locStation;
  Point[] locStationReuse;
  private Context localContext;
  private MapDataSource mapDataSource;
  private MapCustomListener mapListener;
  Paint paintCirculoEstacionExt;
  Paint paintCirculoEstacionInt;
  Paint paintLineasInt;
  Paint paintPinFin;
  Paint paintPinInit;
  Paint paintPinTransborde;
  Paint paintRutaExt;
  Paint paintRutaInt;
  Paint paintSegmento;
  Paint paintSegmentoPunto;
  Paint paintText;
  boolean pinFinalActive;
  int pinFinalId;
  boolean pinInitActive;
  int pinInitId;
  Bitmap[] pins;
  MapView.Projection pj;
  Random randy = new Random();
  Ruta ruta;
  Rect textBounds;
  int totalNumConexiones;
  int totalNumEstaciones;

  static
  {
    double[] arrayOfDouble = new double[8];
    arrayOfDouble[0] = Math.toRadians(360.0D);
    arrayOfDouble[1] = Math.toRadians(45.0D);
    arrayOfDouble[2] = Math.toRadians(90.0D);
    arrayOfDouble[3] = Math.toRadians(135.0D);
    arrayOfDouble[4] = Math.toRadians(180.0D);
    arrayOfDouble[5] = Math.toRadians(225.0D);
    arrayOfDouble[6] = Math.toRadians(270.0D);
    arrayOfDouble[7] = Math.toRadians(315.0D);
    GRADOS = arrayOfDouble;
  }

  public MetroOverlay(Context paramContext, MapDataSource paramMapDataSource, MapCustomListener paramMapCustomListener, int paramInt)
  {
    super(new DefaultResourceProxyImpl(paramContext));
    this.localContext = paramContext;
    this.assetManager = this.localContext.getAssets();
    this.mapDataSource = paramMapDataSource;
    this.mapListener = paramMapCustomListener;
    this.paintLineasInt = new Paint();
    this.paintLineasInt.setStyle(Paint.Style.STROKE);
    this.paintLineasInt.setStrokeCap(Paint.Cap.ROUND);
    this.paintLineasInt.setStrokeJoin(Paint.Join.BEVEL);
    this.paintLineasInt.setStrokeWidth(8.0F);
    this.paintLineasInt.setAntiAlias(true);
    this.paintRutaInt = new Paint();
    this.paintRutaInt.setStyle(Paint.Style.STROKE);
    this.paintRutaInt.setStrokeCap(Paint.Cap.ROUND);
    this.paintRutaInt.setARGB(255, 255, 255, 255);
    this.paintRutaInt.setStrokeJoin(Paint.Join.ROUND);
    this.paintRutaInt.setStrokeWidth(8.0F);
    this.paintRutaInt.setAntiAlias(true);
    this.paintRutaExt = new Paint(this.paintRutaInt);
    this.paintRutaExt.setStrokeWidth(10.0F);
    this.paintRutaExt.setARGB(255, 0, 0, 0);
    this.paintPinInit = new Paint();
    this.paintPinInit.setStyle(Paint.Style.FILL_AND_STROKE);
    this.paintPinInit.setARGB(130, 0, 255, 0);
    this.paintPinInit.setStrokeWidth(2.0F);
    this.paintPinInit.setAntiAlias(true);
    this.paintPinFin = new Paint(this.paintPinInit);
    this.paintPinFin.setARGB(130, 255, 0, 0);
    this.paintPinTransborde = new Paint(this.paintPinInit);
    this.paintPinTransborde.setARGB(130, 255, 0, 255);
    this.paintCirculoEstacionExt = new Paint();
    this.paintCirculoEstacionExt.setStyle(Paint.Style.FILL);
    this.paintCirculoEstacionExt.setAntiAlias(true);
    this.paintCirculoEstacionExt.setColor(-16777216);
    this.paintCirculoEstacionInt = new Paint(this.paintCirculoEstacionExt);
    this.paintCirculoEstacionInt.setColor(-1);
    this.paintSegmento = new Paint();
    this.paintSegmento.setAntiAlias(true);
    this.paintSegmento.setColor(-16777216);
    this.paintSegmentoPunto = new Paint(this.paintSegmento);
    this.paintSegmentoPunto.setStyle(Paint.Style.FILL);
    this.paintText = new Paint(this.paintSegmento);
    this.paintText.setTextSize(1.0F);
    this.ruta = new Ruta();
    this.auxRect = new Rect();
    this.auxRectE6 = new Rect();
    this.boundingRect = new Rect();
    this.textBounds = new Rect();
    this.boundingBox = new BoundingBoxE6(0, 0, 0, 0);
    this.totalNumEstaciones = this.mapDataSource.getNumEstaciones();
    this.totalNumConexiones = this.mapDataSource.getNumConexiones();
    loadEstacionInfo();
    loadConexion();
  }

  private int calculateZoomFactor(int paramInt)
  {
    return paramInt * (15 + fixedZoom(paramInt)) / 26;
  }

  private void drawCircles(Canvas paramCanvas, int paramInt)
  {
    for (int j = 1; j < this.totalNumEstaciones; j++)
    {
      this.auxRect.set(this.locStationReuse[j].x - paramInt, this.locStationReuse[j].y - paramInt, paramInt + this.locStationReuse[j].x, paramInt + this.locStationReuse[j].y);
      if (!this.boundingRect.contains(this.locStation[j].y, this.locStation[j].x))
        continue;
      paramCanvas.drawCircle(this.locStationReuse[j].x, this.locStationReuse[j].y, paramInt, this.paintCirculoEstacionExt);
      paramCanvas.drawCircle(this.locStationReuse[j].x, this.locStationReuse[j].y, paramInt - 0.2F * paramInt, this.paintCirculoEstacionInt);
    }
  }

  private void drawCirclesWithText(Canvas paramCanvas, int paramInt)
  {
    for (int j = 1; j < this.totalNumEstaciones; j++)
    {
      this.auxRect.set(this.locStationReuse[j].x - paramInt, this.locStationReuse[j].y - paramInt, paramInt + this.locStationReuse[j].x, paramInt + this.locStationReuse[j].y);
      if (!this.boundingRect.contains(this.locStation[j].y, this.locStation[j].x))
        continue;
      float f1 = this.locStationReuse[j].x + ((PointF)this.estacionesSegmento[j].b).x * paramInt;
      float f2 = this.locStationReuse[j].y + ((PointF)this.estacionesSegmento[j].b).y * paramInt;
      paramCanvas.drawCircle(this.locStationReuse[j].x, this.locStationReuse[j].y, paramInt, this.paintCirculoEstacionExt);
      paramCanvas.drawCircle(this.locStationReuse[j].x, this.locStationReuse[j].y, paramInt - 0.2F * paramInt, this.paintCirculoEstacionInt);
      paramCanvas.drawLine(this.locStationReuse[j].x + ((PointF)this.estacionesSegmento[j].a).x * paramInt, this.locStationReuse[j].y + ((PointF)this.estacionesSegmento[j].a).y * paramInt, f1, f2, this.paintSegmento);
      paramCanvas.drawCircle(f1, f2, 0.2F * paramInt, this.paintSegmentoPunto);
      drawMultiLineText(paramCanvas, this.estacionesName[j], this.locStationReuse[j].x + this.estacionTextPos[j].x * paramInt, this.locStationReuse[j].y + this.estacionTextPos[j].y * paramInt, this.estacionTextDimention[j].x * paramInt, this.estacionTextDimention[j].y * paramInt);
    }
  }

  private void drawIcons(Canvas paramCanvas, int paramInt)
  {
    for (int j = 1; j < this.totalNumEstaciones; j++)
    {
      this.auxRect.set(this.locStationReuse[j].x - paramInt, this.locStationReuse[j].y - paramInt, paramInt + this.locStationReuse[j].x, paramInt + this.locStationReuse[j].y);
      if (!this.boundingRect.contains(this.locStation[j].y, this.locStation[j].x))
        continue;
      this.icons[j].prepareToDraw();
      paramCanvas.drawBitmap(this.icons[j], null, this.auxRect, null);
    }
  }

  private void drawIconsWithText(Canvas paramCanvas, int paramInt)
  {
    for (int j = 1; j < this.totalNumEstaciones; j++)
    {
      this.auxRect.set(this.locStationReuse[j].x - paramInt, this.locStationReuse[j].y - paramInt, paramInt + this.locStationReuse[j].x, paramInt + this.locStationReuse[j].y);
      if (!this.boundingRect.contains(this.locStation[j].y, this.locStation[j].x))
        continue;
      this.icons[j].prepareToDraw();
      paramCanvas.drawBitmap(this.icons[j], null, this.auxRect, null);
      float f1 = this.locStationReuse[j].x + ((PointF)this.estacionesSegmento[j].b).x * paramInt;
      float f2 = this.locStationReuse[j].y + ((PointF)this.estacionesSegmento[j].b).y * paramInt;
      paramCanvas.drawLine(this.locStationReuse[j].x + ((PointF)this.estacionesSegmento[j].a).x * paramInt, this.locStationReuse[j].y + ((PointF)this.estacionesSegmento[j].a).y * paramInt, f1, f2, this.paintSegmento);
      paramCanvas.drawCircle(f1, f2, 0.2F * paramInt, this.paintSegmentoPunto);
      drawMultiLineText(paramCanvas, this.estacionesName[j], this.locStationReuse[j].x + this.estacionTextPos[j].x * paramInt, this.locStationReuse[j].y + this.estacionTextPos[j].y * paramInt, this.estacionTextDimention[j].x * paramInt, this.estacionTextDimention[j].y * paramInt);
    }
  }

  private void drawLines(Canvas paramCanvas)
  {
    for (this.i = 1; this.i < this.conexionesActivas; this.i = (1 + this.i))
    {
      this.paintLineasInt.setColor(((Point3)this.conexion.get(this.i)).z);
      paramCanvas.drawLine(this.locStationReuse[((Point3)this.conexion.get(this.i)).x].x, this.locStationReuse[((Point3)this.conexion.get(this.i)).x].y, this.locStationReuse[((Point3)this.conexion.get(this.i)).y].x, this.locStationReuse[((Point3)this.conexion.get(this.i)).y].y, this.paintLineasInt);
    }
  }

  private void drawMultiLineText(Canvas paramCanvas, String paramString, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    float f1 = paramFloat1 - paramFloat3 / 2.0F;
    float f2 = paramFloat2 + paramFloat4 / 2.0F;
    String[] arrayOfString = paramString.split("\n");
    int j = arrayOfString.length;
    Paint localPaint = new Paint();
    localPaint.setARGB(180, 255, 0, 0);
    localPaint.setStyle(Paint.Style.FILL);
    paramCanvas.drawRect(paramFloat1 - paramFloat3 / 2.0F, paramFloat2 + paramFloat4 / 2.0F, paramFloat1 + paramFloat3 / 2.0F, paramFloat2 - paramFloat4 / 2.0F, localPaint);
    for (this.i = (j - 1); this.i >= 0; this.i = (-1 + this.i))
    {
      paramCanvas.drawText(arrayOfString[this.i], f1, f2, this.paintText);
      f2 += 1.5F * (this.paintText.ascent() + this.paintText.descent());
    }
  }

  private void drawRoute(Canvas paramCanvas)
  {
    int j = -1 + this.ruta.getEstaciones().size();
    for (this.i = 0; this.i < j; this.i = (1 + this.i))
    {
      int k = ((Estacion)this.ruta.getEstaciones().get(this.i)).getIdEstacionAuxiliar().intValue();
      int m = ((Estacion)this.ruta.getEstaciones().get(1 + this.i)).getIdEstacionAuxiliar().intValue();
      paramCanvas.drawLine(this.locStationReuse[k].x, this.locStationReuse[k].y, this.locStationReuse[m].x, this.locStationReuse[m].y, this.paintRutaExt);
      paramCanvas.drawLine(this.locStationReuse[k].x, this.locStationReuse[k].y, this.locStationReuse[m].x, this.locStationReuse[m].y, this.paintRutaInt);
    }
  }

  private int fixedZoom(int paramInt)
  {
    int j = 12;
    int k = 0;
    while (j <= paramInt)
    {
      k += 5 * (j - 12);
      j++;
    }
    return k;
  }

  private boolean insideCircle(int paramInt1, int paramInt2, int paramInt3)
  {
    return (Math.abs(paramInt1 - this.locStation[paramInt3].x) <= 2500) && (Math.abs(paramInt2 - this.locStation[paramInt3].y) <= 2500);
  }

  private void loadConexion()
  {
    this.conexion = new ArrayList();
    for (int j = 1; j < this.totalNumConexiones; j++)
    {
      long l = this.mapDataSource.getConexion(j).getIdEstacion().longValue();
      int k = this.mapDataSource.getConexion(j).getIdVecino().intValue();
      int m = (int)this.mapDataSource.getConexion(j).getIdLinea().longValue();
      if (k <= l)
        continue;
      this.conexion.add(new Point3((int)l, k, this.mapDataSource.getColorLinea(m)));
    }
    this.conexion.add(0, new Point3(0, 0, 0));
    this.conexionesActivas = this.conexion.size();
  }

  private void loadEstacionInfo()
  {
    this.locStation = new Point[this.totalNumEstaciones];
    this.locStationReuse = new Point[this.totalNumEstaciones];
    this.estacionesName = new String[this.totalNumEstaciones];
    this.estacionesSegmento = new Tupla[this.totalNumEstaciones];
    this.estacionTextPos = new PointF[this.totalNumEstaciones];
    this.estacionTextDimention = new PointF[this.totalNumEstaciones];
    for (int j = 1; j < this.totalNumEstaciones; j++)
    {
      this.locStation[j] = new Point(this.mapDataSource.getLocationEstacionMapa(j));
      this.locStationReuse[j] = new Point();
      this.estacionesName[j] = this.mapDataSource.getNameEstacion(j);
      float f1 = 0.0F;
      float f2 = 0.0F;
      String[] arrayOfString = this.estacionesName[j].split("\n");
      int k = arrayOfString.length;
      for (int m = 0; m < k; m++)
      {
        float f4 = arrayOfString[m].length();
        if (f4 > f1)
          f1 = f4;
        f2 -= 1.5F * (this.paintText.ascent() + this.paintText.descent());
      }
      float f3 = f1 / 2.0F;
      this.estacionTextDimention[j] = new PointF(f3, f2);
    }
  }

  private void loadIcons()
  {
    if (this.icons != null)
      return;
    this.drawPin = true;
    this.drawIcon = true;
    this.pins = new Bitmap[3];
    this.icons = new Bitmap[this.totalNumEstaciones];
    Console.log("Total " + this.totalNumEstaciones);
    int j = this.pins.length;
    int k = this.icons.length;
    int m = 0;
    label77: if (m < j);
    while (true)
    {
      try
      {
        this.pins[m] = Utils.decodeFileSimple(this.assetManager.openFd("pin/dot_" + m + ".png"));
        m++;
        break label77;
        if (n >= k)
          break;
        this.icons[n] = Utils.decodeFileSimple(this.assetManager.openFd("icons/z" + n + ".jpg"));
        n++;
        continue;
      }
      catch (IOException localIOException)
      {
        Console.log("Error al cargar la imagen");
        return;
      }
      int n = 1;
    }
  }

  private int sign(float paramFloat)
  {
    if ((paramFloat > -1.0E-006D) && (paramFloat < 1.0E-006D))
      return 0;
    if (paramFloat > 1.0E-006D)
      return 1;
    return -1;
  }

  public void clear()
  {
    this.drawRuta = false;
    this.ruta.clear();
    this.pinFinalActive = false;
    this.pinInitActive = false;
  }

  public void destroy()
  {
    if (this.icons == null)
      return;
    this.drawIcon = false;
    this.drawPin = false;
    for (int j = 1; j < this.totalNumEstaciones; j++)
    {
      this.icons[j].recycle();
      this.icons[j] = null;
    }
    this.icons = null;
    for (int k = 0; k < this.pins.length; k++)
    {
      this.pins[k].recycle();
      this.pins[k] = null;
    }
    this.pins = null;
    System.gc();
    Runtime.getRuntime().gc();
  }

  protected void draw(Canvas paramCanvas, MapView paramMapView, boolean paramBoolean)
  {
    this.pj = paramMapView.getProjection();
    this.boundingRect.set(this.pj.getBoundingBox().getLonWestE6(), this.pj.getBoundingBox().getLatSouthE6(), this.pj.getBoundingBox().getLonEastE6(), this.pj.getBoundingBox().getLatNorthE6());
    int j = calculateZoomFactor(this.pj.getZoomLevel());
    this.paintSegmento.setStrokeWidth(j / 5.0F);
    this.paintText.setTextSize(1.0F * j);
    for (int k = 1; k < this.totalNumEstaciones; k++)
    {
      this.pj.toMapPixelsProjected(this.locStation[k].x, this.locStation[k].y, this.locStationReuse[k]);
      this.pj.toMapPixelsTranslated(this.locStationReuse[k], this.locStationReuse[k]);
    }
    drawLines(paramCanvas);
    if (this.drawRuta)
      drawRoute(paramCanvas);
    if (this.drawIcon)
      drawIcons(paramCanvas, j);
    if (this.drawPin)
    {
      if (this.pinInitActive)
      {
        this.auxRect.set(this.locStationReuse[this.pinInitId].x - j, this.locStationReuse[this.pinInitId].y - j, j + this.locStationReuse[this.pinInitId].x, j + this.locStationReuse[this.pinInitId].y);
        paramCanvas.drawRect(this.auxRect, this.paintPinInit);
      }
      if (this.pinFinalActive)
      {
        this.auxRect.set(this.locStationReuse[this.pinFinalId].x - j, this.locStationReuse[this.pinFinalId].y - j, j + this.locStationReuse[this.pinFinalId].x, j + this.locStationReuse[this.pinFinalId].y);
        paramCanvas.drawRect(this.auxRect, this.paintPinFin);
      }
    }
    if (this.drawRuta)
    {
      int m = this.ruta.getEstacionTransborde().size();
      for (int n = 0; n < m; n++)
      {
        int i1 = ((Estacion)this.ruta.getEstacionTransborde().get(n)).getIdEstacionAuxiliar().intValue();
        this.auxRect.set(this.locStationReuse[i1].x - j, this.locStationReuse[i1].y - j, j + this.locStationReuse[i1].x, j + this.locStationReuse[i1].y);
        paramCanvas.drawRect(this.auxRect, this.paintPinTransborde);
      }
    }
  }

  public boolean getPinFinal()
  {
    return this.pinFinalActive;
  }

  public int getPinFinalId()
  {
    return this.pinFinalId;
  }

  public boolean getPinInit()
  {
    return this.pinInitActive;
  }

  public int getPinInitId()
  {
    return this.pinInitId;
  }

  public Ruta getRuta()
  {
    return this.ruta;
  }

  public boolean isDrawn()
  {
    return this.drawRuta;
  }

  public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent, MapView paramMapView)
  {
    this.pj = paramMapView.getProjection();
    GeoPoint localGeoPoint = this.pj.fromPixels(paramMotionEvent.getX(), paramMotionEvent.getY());
    for (int j = 1; ; j++)
    {
      if (j < this.totalNumEstaciones)
      {
        if (!insideCircle(localGeoPoint.getLatitudeE6(), localGeoPoint.getLongitudeE6(), j))
          continue;
        this.mapListener.onStationSelected(j);
      }
      return super.onSingleTapConfirmed(paramMotionEvent, paramMapView);
    }
  }

  public void resume()
  {
    destroy();
    loadIcons();
  }

  public void setDrawRuta(boolean paramBoolean)
  {
    this.drawRuta = paramBoolean;
  }

  public void setPinFinalId(int paramInt)
  {
    this.pinFinalActive = true;
    this.pinFinalId = paramInt;
    this.mapListener.onPinDestinoChanged(this.pinFinalId);
  }

  public void setPinInitId(int paramInt)
  {
    this.pinInitActive = true;
    this.pinInitId = paramInt;
    this.mapListener.onPinOrigenChanged(this.pinInitId);
  }

  public void setRuta(Ruta paramRuta)
  {
    this.drawRuta = true;
    this.ruta = paramRuta;
    this.ruta.init();
    this.pinFinalActive = true;
    this.pinInitActive = true;
    this.pinFinalId = ((Estacion)this.ruta.getEstaciones().get(0)).getIdEstacionAuxiliar().intValue();
    this.pinInitId = ((Estacion)this.ruta.getEstaciones().get(-1 + this.ruta.getEstaciones().size())).getIdEstacionAuxiliar().intValue();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.view.MetroOverlay
 * JD-Core Version:    0.6.0
 */