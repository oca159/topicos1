package com.crayonlion.metro.controller;

public abstract interface SimpleMapControllerEventListener
{
  public abstract void initThreadBusqueda();

  public abstract void onRutaDrawn(boolean paramBoolean);

  public abstract void setDestinoSearchBox(int paramInt);

  public abstract void setOrigenSearchBox(int paramInt);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.controller.SimpleMapControllerEventListener
 * JD-Core Version:    0.6.0
 */