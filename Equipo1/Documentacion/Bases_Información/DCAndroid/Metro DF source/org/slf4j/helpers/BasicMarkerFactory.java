package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.IMarkerFactory;
import org.slf4j.Marker;

public class BasicMarkerFactory
  implements IMarkerFactory
{
  Map markerMap = new HashMap();

  public boolean detachMarker(String paramString)
  {
    if (paramString == null);
    do
      return false;
    while (this.markerMap.remove(paramString) == null);
    return true;
  }

  public boolean exists(String paramString)
  {
    monitorenter;
    int i;
    if (paramString == null)
      i = 0;
    while (true)
    {
      monitorexit;
      return i;
      try
      {
        boolean bool = this.markerMap.containsKey(paramString);
        i = bool;
      }
      finally
      {
        monitorexit;
      }
    }
  }

  public Marker getDetachedMarker(String paramString)
  {
    return new BasicMarker(paramString);
  }

  public Marker getMarker(String paramString)
  {
    monitorenter;
    if (paramString == null)
      try
      {
        throw new IllegalArgumentException("Marker name cannot be null");
      }
      finally
      {
        monitorexit;
      }
    Object localObject1 = (Marker)this.markerMap.get(paramString);
    if (localObject1 == null)
    {
      localObject1 = new BasicMarker(paramString);
      this.markerMap.put(paramString, localObject1);
    }
    monitorexit;
    return (Marker)localObject1;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.helpers.BasicMarkerFactory
 * JD-Core Version:    0.6.0
 */