package net.wigle.wigleandroid;

import android.view.MotionEvent;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZoomButtonsController
{
  private static Class LISTENER_CLASS;
  private static Class ZOOM_CLASS;
  private static Method isVisible;
  private static final Logger logger;
  private static Method onTouch;
  private static Method setOnZoomListener;
  private static Method setVisible;
  private static Method setZoomInEnabled;
  private static Method setZoomOutEnabled;
  private Object controller;

  static
  {
    int i = 0;
    logger = LoggerFactory.getLogger(ZoomButtonsController.class);
    try
    {
      ZOOM_CLASS = Class.forName("android.widget.ZoomButtonsController");
      Class[] arrayOfClass1 = ZOOM_CLASS.getDeclaredClasses();
      int j = arrayOfClass1.length;
      while (true)
      {
        if (i >= j)
        {
          Class localClass1 = ZOOM_CLASS;
          Class[] arrayOfClass2 = new Class[1];
          arrayOfClass2[0] = LISTENER_CLASS;
          setOnZoomListener = localClass1.getMethod("setOnZoomListener", arrayOfClass2);
          Class localClass2 = ZOOM_CLASS;
          Class[] arrayOfClass3 = new Class[1];
          arrayOfClass3[0] = Boolean.TYPE;
          setVisible = localClass2.getMethod("setVisible", arrayOfClass3);
          Class localClass3 = ZOOM_CLASS;
          Class[] arrayOfClass4 = new Class[1];
          arrayOfClass4[0] = Boolean.TYPE;
          setZoomInEnabled = localClass3.getMethod("setZoomInEnabled", arrayOfClass4);
          Class localClass4 = ZOOM_CLASS;
          Class[] arrayOfClass5 = new Class[1];
          arrayOfClass5[0] = Boolean.TYPE;
          setZoomOutEnabled = localClass4.getMethod("setZoomOutEnabled", arrayOfClass5);
          onTouch = ZOOM_CLASS.getMethod("onTouch", new Class[] { View.class, MotionEvent.class });
          isVisible = ZOOM_CLASS.getMethod("isVisible", new Class[0]);
          return;
        }
        Class localClass5 = arrayOfClass1[i];
        if ("OnZoomListener".equals(localClass5.getSimpleName()))
          LISTENER_CLASS = localClass5;
        i++;
      }
    }
    catch (Exception localException)
    {
      logger.info("no zoom buttons: " + localException);
    }
  }

  public ZoomButtonsController(View paramView)
  {
    if (ZOOM_CLASS != null);
    try
    {
      this.controller = ZOOM_CLASS.getConstructor(new Class[] { View.class }).newInstance(new Object[] { paramView });
      return;
    }
    catch (Exception localException)
    {
      logger.error("exception instantiating: " + localException);
    }
  }

  public boolean isVisible()
  {
    if (this.controller != null)
      try
      {
        boolean bool = ((Boolean)isVisible.invoke(this.controller, new Object[0])).booleanValue();
        return bool;
      }
      catch (Exception localException)
      {
        logger.error("isVisible exception: " + localException);
      }
    return false;
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (this.controller != null)
      try
      {
        boolean bool = ((Boolean)onTouch.invoke(this.controller, new Object[] { paramView, paramMotionEvent })).booleanValue();
        return bool;
      }
      catch (Exception localException)
      {
        logger.error("onTouch exception: " + localException);
      }
    return false;
  }

  public void setOnZoomListener(OnZoomListener paramOnZoomListener)
  {
    if (this.controller != null);
    try
    {
      1 local1 = new InvocationHandler(paramOnZoomListener)
      {
        public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
        {
          ZoomButtonsController.logger.info("invoke: " + paramMethod.getName() + " listener: " + this.val$listener);
          if ("onZoom".equals(paramMethod.getName()))
            this.val$listener.onZoom(((Boolean)paramArrayOfObject[0]).booleanValue());
          while (true)
          {
            return null;
            if ("onVisibilityChanged".equals(paramMethod.getName()))
            {
              this.val$listener.onVisibilityChanged(((Boolean)paramArrayOfObject[0]).booleanValue());
              continue;
            }
            ZoomButtonsController.logger.info("unhandled listener method: " + paramMethod);
          }
        }
      };
      ClassLoader localClassLoader = LISTENER_CLASS.getClassLoader();
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = LISTENER_CLASS;
      Object localObject = Proxy.newProxyInstance(localClassLoader, arrayOfClass, local1);
      setOnZoomListener.invoke(this.controller, new Object[] { localObject });
      return;
    }
    catch (Exception localException)
    {
      logger.error("setOnZoomListener exception: " + localException);
    }
  }

  public void setVisible(boolean paramBoolean)
  {
    if (this.controller != null);
    try
    {
      Method localMethod = setVisible;
      Object localObject = this.controller;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(paramBoolean);
      localMethod.invoke(localObject, arrayOfObject);
      return;
    }
    catch (Exception localException)
    {
      logger.error("setVisible exception: " + localException);
    }
  }

  public void setZoomInEnabled(boolean paramBoolean)
  {
    if (this.controller != null);
    try
    {
      Method localMethod = setZoomInEnabled;
      Object localObject = this.controller;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(paramBoolean);
      localMethod.invoke(localObject, arrayOfObject);
      return;
    }
    catch (Exception localException)
    {
      logger.error("setZoomInEnabled exception: " + localException);
    }
  }

  public void setZoomOutEnabled(boolean paramBoolean)
  {
    if (this.controller != null);
    try
    {
      Method localMethod = setZoomOutEnabled;
      Object localObject = this.controller;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(paramBoolean);
      localMethod.invoke(localObject, arrayOfObject);
      return;
    }
    catch (Exception localException)
    {
      logger.error("setZoomOutEnabled exception: " + localException);
    }
  }

  public static abstract interface OnZoomListener
  {
    public abstract void onVisibilityChanged(boolean paramBoolean);

    public abstract void onZoom(boolean paramBoolean);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     net.wigle.wigleandroid.ZoomButtonsController
 * JD-Core Version:    0.6.0
 */