package com.google.gson.internal;

import com.google.gson.stream.JsonReader;
import java.io.IOException;

public abstract class JsonReaderInternalAccess
{
  public static JsonReaderInternalAccess INSTANCE;

  public abstract void promoteNameToValue(JsonReader paramJsonReader)
    throws IOException;
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.internal.JsonReaderInternalAccess
 * JD-Core Version:    0.6.0
 */