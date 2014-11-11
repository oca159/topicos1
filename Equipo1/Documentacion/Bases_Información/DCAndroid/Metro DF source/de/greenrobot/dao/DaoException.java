package de.greenrobot.dao;

import android.database.SQLException;

public class DaoException extends SQLException
{
  private static final long serialVersionUID = -5877937327907457779L;

  public DaoException()
  {
  }

  public DaoException(String paramString)
  {
    super(paramString);
  }

  public DaoException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    safeInitCause(paramThrowable);
  }

  public DaoException(Throwable paramThrowable)
  {
    safeInitCause(paramThrowable);
  }

  protected void safeInitCause(Throwable paramThrowable)
  {
    try
    {
      initCause(paramThrowable);
      return;
    }
    catch (Throwable localThrowable)
    {
      DaoLog.e("Could not set initial cause", localThrowable);
      DaoLog.e("Initial cause is:", paramThrowable);
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.DaoException
 * JD-Core Version:    0.6.0
 */