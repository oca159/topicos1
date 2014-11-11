package org.osmdroid.views.util;

public class MyMath
{
  public static int getNextSquareNumberAbove(float paramFloat)
  {
    int i = 0;
    int j = 1;
    for (int k = 1; ; k++)
    {
      if (j > paramFloat)
        return i;
      i = k;
      j *= 2;
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.views.util.MyMath
 * JD-Core Version:    0.6.0
 */