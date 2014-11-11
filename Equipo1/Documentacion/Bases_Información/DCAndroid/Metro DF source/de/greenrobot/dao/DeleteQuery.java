package de.greenrobot.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.util.Collection;

public class DeleteQuery<T> extends AbstractQuery<T>
{
  private SQLiteStatement compiledStatement;

  public DeleteQuery(AbstractDao<T, ?> paramAbstractDao, String paramString, Collection<Object> paramCollection)
  {
    super(paramAbstractDao, paramString, paramCollection);
  }

  public void executeDeleteWithoutDetachingEntities()
  {
    monitorenter;
    while (true)
    {
      int i;
      try
      {
        if (this.compiledStatement == null)
          continue;
        this.compiledStatement.clearBindings();
        break label102;
        if (i >= this.parameters.length)
        {
          this.compiledStatement.execute();
          return;
          this.compiledStatement = this.dao.db.compileStatement(this.sql);
        }
      }
      finally
      {
        monitorexit;
      }
      String str = this.parameters[i];
      if (str != null)
      {
        this.compiledStatement.bindString(i + 1, str);
      }
      else
      {
        this.compiledStatement.bindNull(i + 1);
        break label107;
        label102: i = 0;
        continue;
      }
      label107: i++;
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.DeleteQuery
 * JD-Core Version:    0.6.0
 */