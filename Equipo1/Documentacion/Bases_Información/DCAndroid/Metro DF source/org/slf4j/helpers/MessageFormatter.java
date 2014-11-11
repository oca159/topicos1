package org.slf4j.helpers;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public final class MessageFormatter
{
  static final char DELIM_START = '{';
  static final char DELIM_STOP = '}';
  static final String DELIM_STR = "{}";
  private static final char ESCAPE_CHAR = '\\';

  public static final String arrayFormat(String paramString, Object[] paramArrayOfObject)
  {
    if (paramString == null)
      paramString = null;
    int i;
    StringBuffer localStringBuffer;
    int j;
    int k;
    while (true)
    {
      return paramString;
      if (paramArrayOfObject == null)
        continue;
      i = 0;
      localStringBuffer = new StringBuffer(50 + paramString.length());
      j = 0;
      if (j >= paramArrayOfObject.length)
        break label202;
      k = paramString.indexOf("{}", i);
      if (k != -1)
        break;
      if (i == 0)
        continue;
      localStringBuffer.append(paramString.substring(i, paramString.length()));
      return localStringBuffer.toString();
    }
    if (isEscapedDelimeter(paramString, k))
      if (!isDoubleEscaped(paramString, k))
      {
        j--;
        localStringBuffer.append(paramString.substring(i, k - 1));
        localStringBuffer.append('{');
        i = k + 1;
      }
    while (true)
    {
      j++;
      break;
      localStringBuffer.append(paramString.substring(i, k - 1));
      deeplyAppendParameter(localStringBuffer, paramArrayOfObject[j], new HashMap());
      i = k + 2;
      continue;
      localStringBuffer.append(paramString.substring(i, k));
      deeplyAppendParameter(localStringBuffer, paramArrayOfObject[j], new HashMap());
      i = k + 2;
    }
    label202: localStringBuffer.append(paramString.substring(i, paramString.length()));
    return localStringBuffer.toString();
  }

  private static void booleanArrayAppend(StringBuffer paramStringBuffer, boolean[] paramArrayOfBoolean)
  {
    paramStringBuffer.append('[');
    int i = paramArrayOfBoolean.length;
    for (int j = 0; j < i; j++)
    {
      paramStringBuffer.append(paramArrayOfBoolean[j]);
      if (j == i - 1)
        continue;
      paramStringBuffer.append(", ");
    }
    paramStringBuffer.append(']');
  }

  private static void byteArrayAppend(StringBuffer paramStringBuffer, byte[] paramArrayOfByte)
  {
    paramStringBuffer.append('[');
    int i = paramArrayOfByte.length;
    for (int j = 0; j < i; j++)
    {
      paramStringBuffer.append(paramArrayOfByte[j]);
      if (j == i - 1)
        continue;
      paramStringBuffer.append(", ");
    }
    paramStringBuffer.append(']');
  }

  private static void charArrayAppend(StringBuffer paramStringBuffer, char[] paramArrayOfChar)
  {
    paramStringBuffer.append('[');
    int i = paramArrayOfChar.length;
    for (int j = 0; j < i; j++)
    {
      paramStringBuffer.append(paramArrayOfChar[j]);
      if (j == i - 1)
        continue;
      paramStringBuffer.append(", ");
    }
    paramStringBuffer.append(']');
  }

  private static void deeplyAppendParameter(StringBuffer paramStringBuffer, Object paramObject, Map paramMap)
  {
    if (paramObject == null)
    {
      paramStringBuffer.append("null");
      return;
    }
    if (!paramObject.getClass().isArray())
    {
      safeObjectAppend(paramStringBuffer, paramObject);
      return;
    }
    if ((paramObject instanceof boolean[]))
    {
      booleanArrayAppend(paramStringBuffer, (boolean[])(boolean[])paramObject);
      return;
    }
    if ((paramObject instanceof byte[]))
    {
      byteArrayAppend(paramStringBuffer, (byte[])(byte[])paramObject);
      return;
    }
    if ((paramObject instanceof char[]))
    {
      charArrayAppend(paramStringBuffer, (char[])(char[])paramObject);
      return;
    }
    if ((paramObject instanceof short[]))
    {
      shortArrayAppend(paramStringBuffer, (short[])(short[])paramObject);
      return;
    }
    if ((paramObject instanceof int[]))
    {
      intArrayAppend(paramStringBuffer, (int[])(int[])paramObject);
      return;
    }
    if ((paramObject instanceof long[]))
    {
      longArrayAppend(paramStringBuffer, (long[])(long[])paramObject);
      return;
    }
    if ((paramObject instanceof float[]))
    {
      floatArrayAppend(paramStringBuffer, (float[])(float[])paramObject);
      return;
    }
    if ((paramObject instanceof double[]))
    {
      doubleArrayAppend(paramStringBuffer, (double[])(double[])paramObject);
      return;
    }
    objectArrayAppend(paramStringBuffer, (Object[])(Object[])paramObject, paramMap);
  }

  private static void doubleArrayAppend(StringBuffer paramStringBuffer, double[] paramArrayOfDouble)
  {
    paramStringBuffer.append('[');
    int i = paramArrayOfDouble.length;
    for (int j = 0; j < i; j++)
    {
      paramStringBuffer.append(paramArrayOfDouble[j]);
      if (j == i - 1)
        continue;
      paramStringBuffer.append(", ");
    }
    paramStringBuffer.append(']');
  }

  private static void floatArrayAppend(StringBuffer paramStringBuffer, float[] paramArrayOfFloat)
  {
    paramStringBuffer.append('[');
    int i = paramArrayOfFloat.length;
    for (int j = 0; j < i; j++)
    {
      paramStringBuffer.append(paramArrayOfFloat[j]);
      if (j == i - 1)
        continue;
      paramStringBuffer.append(", ");
    }
    paramStringBuffer.append(']');
  }

  public static final String format(String paramString, Object paramObject)
  {
    return arrayFormat(paramString, new Object[] { paramObject });
  }

  public static final String format(String paramString, Object paramObject1, Object paramObject2)
  {
    return arrayFormat(paramString, new Object[] { paramObject1, paramObject2 });
  }

  private static void intArrayAppend(StringBuffer paramStringBuffer, int[] paramArrayOfInt)
  {
    paramStringBuffer.append('[');
    int i = paramArrayOfInt.length;
    for (int j = 0; j < i; j++)
    {
      paramStringBuffer.append(paramArrayOfInt[j]);
      if (j == i - 1)
        continue;
      paramStringBuffer.append(", ");
    }
    paramStringBuffer.append(']');
  }

  static final boolean isDoubleEscaped(String paramString, int paramInt)
  {
    return (paramInt >= 2) && (paramString.charAt(paramInt - 2) == '\\');
  }

  static final boolean isEscapedDelimeter(String paramString, int paramInt)
  {
    if (paramInt == 0);
    do
      return false;
    while (paramString.charAt(paramInt - 1) != '\\');
    return true;
  }

  private static void longArrayAppend(StringBuffer paramStringBuffer, long[] paramArrayOfLong)
  {
    paramStringBuffer.append('[');
    int i = paramArrayOfLong.length;
    for (int j = 0; j < i; j++)
    {
      paramStringBuffer.append(paramArrayOfLong[j]);
      if (j == i - 1)
        continue;
      paramStringBuffer.append(", ");
    }
    paramStringBuffer.append(']');
  }

  private static void objectArrayAppend(StringBuffer paramStringBuffer, Object[] paramArrayOfObject, Map paramMap)
  {
    paramStringBuffer.append('[');
    if (!paramMap.containsKey(paramArrayOfObject))
    {
      paramMap.put(paramArrayOfObject, null);
      int i = paramArrayOfObject.length;
      for (int j = 0; j < i; j++)
      {
        deeplyAppendParameter(paramStringBuffer, paramArrayOfObject[j], paramMap);
        if (j == i - 1)
          continue;
        paramStringBuffer.append(", ");
      }
      paramMap.remove(paramArrayOfObject);
    }
    while (true)
    {
      paramStringBuffer.append(']');
      return;
      paramStringBuffer.append("...");
    }
  }

  private static void safeObjectAppend(StringBuffer paramStringBuffer, Object paramObject)
  {
    try
    {
      paramStringBuffer.append(paramObject.toString());
      return;
    }
    catch (Throwable localThrowable)
    {
      System.err.println("SLF4J: Failed toString() invocation on an object of type [" + paramObject.getClass().getName() + "]");
      localThrowable.printStackTrace();
      paramStringBuffer.append("[FAILED toString()]");
    }
  }

  private static void shortArrayAppend(StringBuffer paramStringBuffer, short[] paramArrayOfShort)
  {
    paramStringBuffer.append('[');
    int i = paramArrayOfShort.length;
    for (int j = 0; j < i; j++)
    {
      paramStringBuffer.append(paramArrayOfShort[j]);
      if (j == i - 1)
        continue;
      paramStringBuffer.append(", ");
    }
    paramStringBuffer.append(']');
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.helpers.MessageFormatter
 * JD-Core Version:    0.6.0
 */