package org.osmdroid.util;

import org.osmdroid.views.util.constants.MathConstants;

public class MyMath
  implements MathConstants
{
  public static double gudermann(double paramDouble)
  {
    return 57.295780181884766D * Math.atan(Math.sinh(paramDouble));
  }

  public static double gudermannInverse(double paramDouble)
  {
    return Math.log(Math.tan(0.7853981852531433D + 0.0174532923847437D * paramDouble / 2.0D));
  }

  public static int mod(int paramInt1, int paramInt2)
  {
    if (paramInt1 > 0)
      return paramInt1 % paramInt2;
    do
      paramInt1 += paramInt2;
    while (paramInt1 < 0);
    return paramInt1;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.util.MyMath
 * JD-Core Version:    0.6.0
 */