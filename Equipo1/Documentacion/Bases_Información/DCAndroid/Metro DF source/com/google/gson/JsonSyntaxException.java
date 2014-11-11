package com.google.gson;

public final class JsonSyntaxException extends JsonParseException
{
  private static final long serialVersionUID = 1L;

  public JsonSyntaxException(String paramString)
  {
    super(paramString);
  }

  public JsonSyntaxException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }

  public JsonSyntaxException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.JsonSyntaxException
 * JD-Core Version:    0.6.0
 */