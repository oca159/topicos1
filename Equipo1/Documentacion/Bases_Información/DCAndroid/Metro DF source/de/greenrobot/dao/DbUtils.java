package de.greenrobot.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DbUtils
{
  public static int copyAllBytes(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    int i = 0;
    byte[] arrayOfByte = new byte[4096];
    while (true)
    {
      int j = paramInputStream.read(arrayOfByte);
      if (j == -1)
        return i;
      paramOutputStream.write(arrayOfByte, 0, j);
      i += j;
    }
  }

  public static int executeSqlScript(Context paramContext, SQLiteDatabase paramSQLiteDatabase, String paramString)
    throws IOException
  {
    return executeSqlScript(paramContext, paramSQLiteDatabase, paramString, true);
  }

  public static int executeSqlScript(Context paramContext, SQLiteDatabase paramSQLiteDatabase, String paramString, boolean paramBoolean)
    throws IOException
  {
    String[] arrayOfString = new String(readAsset(paramContext, paramString), "UTF-8").split(";(\\s)*[\n\r]");
    if (paramBoolean);
    for (int i = executeSqlStatementsInTx(paramSQLiteDatabase, arrayOfString); ; i = executeSqlStatements(paramSQLiteDatabase, arrayOfString))
    {
      DaoLog.i("Executed " + i + " statements from SQL script '" + paramString + "'");
      return i;
    }
  }

  public static int executeSqlStatements(SQLiteDatabase paramSQLiteDatabase, String[] paramArrayOfString)
  {
    int i = 0;
    int j = paramArrayOfString.length;
    for (int k = 0; ; k++)
    {
      if (k >= j)
        return i;
      String str = paramArrayOfString[k].trim();
      if (str.length() <= 0)
        continue;
      paramSQLiteDatabase.execSQL(str);
      i++;
    }
  }

  public static int executeSqlStatementsInTx(SQLiteDatabase paramSQLiteDatabase, String[] paramArrayOfString)
  {
    paramSQLiteDatabase.beginTransaction();
    try
    {
      int i = executeSqlStatements(paramSQLiteDatabase, paramArrayOfString);
      paramSQLiteDatabase.setTransactionSuccessful();
      return i;
    }
    finally
    {
      paramSQLiteDatabase.endTransaction();
    }
    throw localObject;
  }

  public static byte[] readAllBytes(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    copyAllBytes(paramInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static byte[] readAsset(Context paramContext, String paramString)
    throws IOException
  {
    InputStream localInputStream = paramContext.getResources().getAssets().open(paramString);
    try
    {
      byte[] arrayOfByte = readAllBytes(localInputStream);
      return arrayOfByte;
    }
    finally
    {
      localInputStream.close();
    }
    throw localObject;
  }

  public static void vacuum(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("VACUUM");
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.DbUtils
 * JD-Core Version:    0.6.0
 */