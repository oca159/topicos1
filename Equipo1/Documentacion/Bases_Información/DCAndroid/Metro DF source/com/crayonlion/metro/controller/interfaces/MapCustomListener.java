package com.crayonlion.metro.controller.interfaces;

public abstract interface MapCustomListener
{
  public abstract void onDrawRuta(boolean paramBoolean);

  public abstract void onPinDestinoChanged(int paramInt);

  public abstract void onPinOrigenChanged(int paramInt);

  public abstract void onStationSelected(int paramInt);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.controller.interfaces.MapCustomListener
 * JD-Core Version:    0.6.0
 */