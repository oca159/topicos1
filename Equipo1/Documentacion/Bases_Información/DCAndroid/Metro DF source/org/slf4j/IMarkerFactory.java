package org.slf4j;

public abstract interface IMarkerFactory
{
  public abstract boolean detachMarker(String paramString);

  public abstract boolean exists(String paramString);

  public abstract Marker getDetachedMarker(String paramString);

  public abstract Marker getMarker(String paramString);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.IMarkerFactory
 * JD-Core Version:    0.6.0
 */