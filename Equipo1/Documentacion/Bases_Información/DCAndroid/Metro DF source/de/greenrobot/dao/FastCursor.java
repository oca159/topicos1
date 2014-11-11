package de.greenrobot.dao;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;

final class FastCursor
  implements Cursor
{
  private final int count;
  private int position;
  private final CursorWindow window;

  FastCursor(CursorWindow paramCursorWindow)
  {
    this.window = paramCursorWindow;
    this.count = paramCursorWindow.getNumRows();
  }

  public void close()
  {
    throw new UnsupportedOperationException();
  }

  public void copyStringToBuffer(int paramInt, CharArrayBuffer paramCharArrayBuffer)
  {
    throw new UnsupportedOperationException();
  }

  public void deactivate()
  {
    throw new UnsupportedOperationException();
  }

  public byte[] getBlob(int paramInt)
  {
    return this.window.getBlob(this.position, paramInt);
  }

  public int getColumnCount()
  {
    throw new UnsupportedOperationException();
  }

  public int getColumnIndex(String paramString)
  {
    throw new UnsupportedOperationException();
  }

  public int getColumnIndexOrThrow(String paramString)
    throws IllegalArgumentException
  {
    throw new UnsupportedOperationException();
  }

  public String getColumnName(int paramInt)
  {
    throw new UnsupportedOperationException();
  }

  public String[] getColumnNames()
  {
    throw new UnsupportedOperationException();
  }

  public int getCount()
  {
    return this.window.getNumRows();
  }

  public double getDouble(int paramInt)
  {
    return this.window.getDouble(this.position, paramInt);
  }

  public Bundle getExtras()
  {
    throw new UnsupportedOperationException();
  }

  public float getFloat(int paramInt)
  {
    return this.window.getFloat(this.position, paramInt);
  }

  public int getInt(int paramInt)
  {
    return this.window.getInt(this.position, paramInt);
  }

  public long getLong(int paramInt)
  {
    return this.window.getLong(this.position, paramInt);
  }

  public int getPosition()
  {
    return this.position;
  }

  public short getShort(int paramInt)
  {
    return this.window.getShort(this.position, paramInt);
  }

  public String getString(int paramInt)
  {
    return this.window.getString(this.position, paramInt);
  }

  public int getType(int paramInt)
  {
    throw new UnsupportedOperationException();
  }

  public boolean getWantsAllOnMoveCalls()
  {
    throw new UnsupportedOperationException();
  }

  public boolean isAfterLast()
  {
    throw new UnsupportedOperationException();
  }

  public boolean isBeforeFirst()
  {
    throw new UnsupportedOperationException();
  }

  public boolean isClosed()
  {
    throw new UnsupportedOperationException();
  }

  public boolean isFirst()
  {
    return this.position == 0;
  }

  public boolean isLast()
  {
    return this.position == -1 + this.count;
  }

  public boolean isNull(int paramInt)
  {
    return this.window.isNull(this.position, paramInt);
  }

  public boolean move(int paramInt)
  {
    return moveToPosition(paramInt + this.position);
  }

  public boolean moveToFirst()
  {
    this.position = 0;
    int i = this.count;
    int j = 0;
    if (i > 0)
      j = 1;
    return j;
  }

  public boolean moveToLast()
  {
    if (this.count > 0)
    {
      this.position = (-1 + this.count);
      return true;
    }
    return false;
  }

  public boolean moveToNext()
  {
    if (this.position < -1 + this.count)
    {
      this.position = (1 + this.position);
      return true;
    }
    return false;
  }

  public boolean moveToPosition(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.count))
    {
      this.position = paramInt;
      return true;
    }
    return false;
  }

  public boolean moveToPrevious()
  {
    if (this.position > 0)
    {
      this.position = (-1 + this.position);
      return true;
    }
    return false;
  }

  public void registerContentObserver(ContentObserver paramContentObserver)
  {
    throw new UnsupportedOperationException();
  }

  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    throw new UnsupportedOperationException();
  }

  public boolean requery()
  {
    throw new UnsupportedOperationException();
  }

  public Bundle respond(Bundle paramBundle)
  {
    throw new UnsupportedOperationException();
  }

  public void setNotificationUri(ContentResolver paramContentResolver, Uri paramUri)
  {
    throw new UnsupportedOperationException();
  }

  public void unregisterContentObserver(ContentObserver paramContentObserver)
  {
    throw new UnsupportedOperationException();
  }

  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.FastCursor
 * JD-Core Version:    0.6.0
 */