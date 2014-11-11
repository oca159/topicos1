package de.greenrobot.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class TableStatements
{
  private final String[] allColumns;
  private final SQLiteDatabase db;
  private SQLiteStatement deleteStatement;
  private SQLiteStatement insertOrReplaceStatement;
  private SQLiteStatement insertStatement;
  private final String[] pkColumns;
  private volatile String selectAll;
  private volatile String selectByKey;
  private volatile String selectByRowId;
  private final String tablename;
  private SQLiteStatement updateStatement;

  protected TableStatements(SQLiteDatabase paramSQLiteDatabase, String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    this.db = paramSQLiteDatabase;
    this.tablename = paramString;
    this.allColumns = paramArrayOfString1;
    this.pkColumns = paramArrayOfString2;
  }

  protected SQLiteStatement getDeleteStatement()
  {
    if (this.deleteStatement == null)
    {
      String str = SqlUtils.createSqlDelete(this.tablename, this.pkColumns);
      this.deleteStatement = this.db.compileStatement(str);
    }
    return this.deleteStatement;
  }

  protected SQLiteStatement getInsertOrReplaceStatement()
  {
    if (this.insertOrReplaceStatement == null)
    {
      String str = SqlUtils.createSqlInsert("INSERT OR REPLACE INTO ", this.tablename, this.allColumns);
      this.insertOrReplaceStatement = this.db.compileStatement(str);
    }
    return this.insertOrReplaceStatement;
  }

  protected SQLiteStatement getInsertStatement()
  {
    if (this.insertStatement == null)
    {
      String str = SqlUtils.createSqlInsert("INSERT INTO ", this.tablename, this.allColumns);
      this.insertStatement = this.db.compileStatement(str);
    }
    return this.insertStatement;
  }

  protected String getSelectAll()
  {
    if (this.selectAll == null)
      this.selectAll = SqlUtils.createSqlSelect(this.tablename, "T", this.allColumns);
    return this.selectAll;
  }

  protected String getSelectByKey()
  {
    if (this.selectByKey == null)
    {
      StringBuilder localStringBuilder = new StringBuilder(getSelectAll());
      localStringBuilder.append("WHERE ");
      SqlUtils.appendColumnsEqValue(localStringBuilder, "T", this.pkColumns);
      this.selectByKey = localStringBuilder.toString();
    }
    return this.selectByKey;
  }

  public String getSelectByRowId()
  {
    if (this.selectByRowId == null)
      this.selectByRowId = (getSelectAll() + "WHERE ROWID=?");
    return this.selectByRowId;
  }

  protected SQLiteStatement getUpdateStatement()
  {
    if (this.updateStatement == null)
    {
      String str = SqlUtils.createSqlUpdate(this.tablename, this.allColumns, this.pkColumns);
      this.updateStatement = this.db.compileStatement(str);
    }
    return this.updateStatement;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.TableStatements
 * JD-Core Version:    0.6.0
 */