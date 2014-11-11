package com.crayonlion.metro.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
{
  private static final String DATE_FORMAT = "dd-mm-yyyy";
  public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+");

  public static String capitalize(String paramString)
  {
    for (int i = 0; i < paramString.length(); i++)
    {
      if (i == 0)
      {
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = Character.valueOf(Character.toUpperCase(paramString.charAt(0)));
        arrayOfObject2[1] = paramString.substring(1);
        paramString = String.format("%s%s", arrayOfObject2);
      }
      if ((Character.isLetterOrDigit(paramString.charAt(i))) || (i + 1 >= paramString.length()))
        continue;
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = paramString.subSequence(0, i + 1);
      arrayOfObject1[1] = Character.valueOf(Character.toUpperCase(paramString.charAt(i + 1)));
      arrayOfObject1[2] = paramString.substring(i + 2);
      paramString = String.format("%s%s%s", arrayOfObject1);
    }
    return paramString;
  }

  public static boolean checkEmail(String paramString)
  {
    return EMAIL_ADDRESS_PATTERN.matcher(paramString).matches();
  }

  public static String formatDate(Date paramDate)
  {
    if (paramDate != null)
      return new SimpleDateFormat("dd-mm-yyyy").format(paramDate);
    return "";
  }

  public static String formatLongText(String paramString)
  {
    int i = 20;
    if (isBlank(paramString))
      paramString = "";
    do
      return paramString;
    while (paramString.length() <= i);
    if (paramString.length() > i)
    {
      int j = paramString.indexOf("\n");
      if (j != -1)
        return paramString.substring(0, j);
      StringBuilder localStringBuilder2 = new StringBuilder();
      if (paramString.length() > i);
      while (true)
      {
        return paramString.substring(0, i) + "...";
        i = paramString.length();
      }
    }
    StringBuilder localStringBuilder1 = new StringBuilder();
    if (paramString.length() > i);
    while (true)
    {
      return paramString.substring(0, i) + "...";
      i = paramString.length();
    }
  }

  public static String formatLongText(String paramString, int paramInt)
  {
    if (isBlank(paramString))
      paramString = "";
    do
      return paramString;
    while (paramString.length() <= paramInt);
    return paramString.substring(0, paramInt).concat("�");
  }

  public static String formatTextMap(String paramString)
  {
    String str = "";
    String[] arrayOfString = paramString.split(" ");
    int i = arrayOfString.length;
    int j = 0;
    int k = 0;
    if (k < i)
    {
      j += arrayOfString[k].length();
      if (j >= 7)
      {
        str = str + arrayOfString[k] + "\n";
        j = 0;
      }
      while (true)
      {
        k++;
        break;
        str = str + arrayOfString[k] + " ";
      }
    }
    return capitalize(str).trim();
  }

  public static String intsToDate(int paramInt1, int paramInt2, int paramInt3)
  {
    return String.valueOf(paramInt1) + "-" + String.valueOf(paramInt2) + "-" + String.valueOf(paramInt3);
  }

  public static boolean isBlank(String paramString)
  {
    return (paramString == null) || ((paramString != null) && (paramString.trim().equals("")));
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.crayonlion.metro.utils.StringUtils
 * JD-Core Version:    0.6.0
 */