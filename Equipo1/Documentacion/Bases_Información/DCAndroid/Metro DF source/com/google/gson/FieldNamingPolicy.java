package com.google.gson;

import java.lang.reflect.Field;

public enum FieldNamingPolicy
  implements FieldNamingStrategy
{
  static
  {
    LOWER_CASE_WITH_UNDERSCORES = new FieldNamingPolicy("LOWER_CASE_WITH_UNDERSCORES", 3)
    {
      public String translateName(Field paramField)
      {
        return FieldNamingPolicy.access$200(paramField.getName(), "_").toLowerCase();
      }
    };
    LOWER_CASE_WITH_DASHES = new FieldNamingPolicy("LOWER_CASE_WITH_DASHES", 4)
    {
      public String translateName(Field paramField)
      {
        return FieldNamingPolicy.access$200(paramField.getName(), "-").toLowerCase();
      }
    };
    FieldNamingPolicy[] arrayOfFieldNamingPolicy = new FieldNamingPolicy[5];
    arrayOfFieldNamingPolicy[0] = IDENTITY;
    arrayOfFieldNamingPolicy[1] = UPPER_CAMEL_CASE;
    arrayOfFieldNamingPolicy[2] = UPPER_CAMEL_CASE_WITH_SPACES;
    arrayOfFieldNamingPolicy[3] = LOWER_CASE_WITH_UNDERSCORES;
    arrayOfFieldNamingPolicy[4] = LOWER_CASE_WITH_DASHES;
    $VALUES = arrayOfFieldNamingPolicy;
  }

  private static String modifyString(char paramChar, String paramString, int paramInt)
  {
    if (paramInt < paramString.length())
      return paramChar + paramString.substring(paramInt);
    return String.valueOf(paramChar);
  }

  private static String separateCamelCase(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramString1.length(); i++)
    {
      char c = paramString1.charAt(i);
      if ((Character.isUpperCase(c)) && (localStringBuilder.length() != 0))
        localStringBuilder.append(paramString2);
      localStringBuilder.append(c);
    }
    return localStringBuilder.toString();
  }

  private static String upperCaseFirstLetter(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    char c = paramString.charAt(0);
    if ((i >= -1 + paramString.length()) || (Character.isLetter(c)))
    {
      if (i != paramString.length())
        break label66;
      paramString = localStringBuilder.toString();
    }
    label66: 
    do
    {
      return paramString;
      localStringBuilder.append(c);
      i++;
      c = paramString.charAt(i);
      break;
    }
    while (Character.isUpperCase(c));
    return modifyString(Character.toUpperCase(c), paramString, i + 1);
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.FieldNamingPolicy
 * JD-Core Version:    0.6.0
 */