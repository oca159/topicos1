package com.google.gson;

import com.google.gson.reflect.TypeToken;

public abstract interface TypeAdapterFactory
{
  public abstract <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken);
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.TypeAdapterFactory
 * JD-Core Version:    0.6.0
 */