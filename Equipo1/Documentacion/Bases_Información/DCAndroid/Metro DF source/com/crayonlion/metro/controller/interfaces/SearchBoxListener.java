package com.crayonlion.metro.controller.interfaces;

public abstract interface SearchBoxListener
{
  public abstract void onInfoButtonClicked();

  public abstract void onNewRouteRequested(String paramString1, String paramString2, int paramInt);

  public abstract void onRequestLocalization();
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.controller.interfaces.SearchBoxListener
 * JD-Core Version:    0.6.0
 */