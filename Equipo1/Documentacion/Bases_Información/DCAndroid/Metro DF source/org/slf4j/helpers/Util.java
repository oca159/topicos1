package org.slf4j.helpers;

import java.io.PrintStream;

public class Util
{
  public static final void reportFailure(String paramString)
  {
    System.err.println("SLF4J: " + paramString);
  }

  public static final void reportFailure(String paramString, Throwable paramThrowable)
  {
    System.err.println(paramString);
    System.err.println("Reported exception:");
    paramThrowable.printStackTrace();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.helpers.Util
 * JD-Core Version:    0.6.0
 */