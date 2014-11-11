package de.greenrobot.dao;

import android.database.sqlite.SQLiteDatabase;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class DaoConfig
  implements Cloneable
{
  final String[] allColumns;
  final SQLiteDatabase db;
  private IdentityScope<?, ?> identityScope;
  final boolean keyIsNumeric;
  final String[] nonPkColumns;
  final String[] pkColumns;
  final Property pkProperty;
  final Property[] properties;
  final TableStatements statements;
  final String tablename;

  DaoConfig(SQLiteDatabase paramSQLiteDatabase, Class<? extends AbstractDao<?, ?>> paramClass)
  {
    this.db = paramSQLiteDatabase;
    while (true)
    {
      int i;
      try
      {
        this.tablename = ((String)paramClass.getField("TABLENAME").get(null));
        Property[] arrayOfProperty = reflectProperties(paramClass);
        this.properties = arrayOfProperty;
        this.allColumns = new String[arrayOfProperty.length];
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        localObject = null;
        i = 0;
        if (i < arrayOfProperty.length)
          continue;
        this.nonPkColumns = ((String[])localArrayList2.toArray(new String[localArrayList2.size()]));
        this.pkColumns = ((String[])localArrayList1.toArray(new String[localArrayList1.size()]));
        if (this.pkColumns.length == 1)
        {
          this.pkProperty = localObject;
          this.statements = new TableStatements(paramSQLiteDatabase, this.tablename, this.allColumns, this.pkColumns);
          if (this.pkProperty == null)
            break label362;
          Class localClass = this.pkProperty.type;
          if ((localClass.equals(Long.TYPE)) || (localClass.equals(Long.class)) || (localClass.equals(Integer.TYPE)) || (localClass.equals(Integer.class)) || (localClass.equals(Short.TYPE)) || (localClass.equals(Short.class)) || (localClass.equals(Byte.TYPE)) || (localClass.equals(Byte.class)))
            break label356;
          bool = false;
          this.keyIsNumeric = bool;
          return;
          Property localProperty = arrayOfProperty[i];
          String str = localProperty.columnName;
          this.allColumns[i] = str;
          if (!localProperty.primaryKey)
            continue;
          localArrayList1.add(str);
          localObject = localProperty;
          break label368;
          localArrayList2.add(str);
        }
      }
      catch (Exception localException)
      {
        throw new DaoException("Could not init DAOConfig", localException);
      }
      Object localObject = null;
      continue;
      label356: boolean bool = true;
      continue;
      label362: this.keyIsNumeric = false;
      return;
      label368: i++;
    }
  }

  DaoConfig(DaoConfig paramDaoConfig)
  {
    this.db = paramDaoConfig.db;
    this.tablename = paramDaoConfig.tablename;
    this.properties = paramDaoConfig.properties;
    this.allColumns = paramDaoConfig.allColumns;
    this.pkColumns = paramDaoConfig.pkColumns;
    this.nonPkColumns = paramDaoConfig.nonPkColumns;
    this.pkProperty = paramDaoConfig.pkProperty;
    this.statements = paramDaoConfig.statements;
    this.keyIsNumeric = paramDaoConfig.keyIsNumeric;
  }

  private static Property[] reflectProperties(Class<? extends AbstractDao<?, ?>> paramClass)
    throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException
  {
    Field[] arrayOfField = Class.forName(paramClass.getName() + "$Properties").getDeclaredFields();
    Property[] arrayOfProperty = new Property[arrayOfField.length];
    int i = arrayOfField.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return arrayOfProperty;
      Property localProperty = (Property)arrayOfField[j].get(null);
      if (arrayOfProperty[localProperty.oridinal] != null)
        throw new DaoException("Duplicate property ordinals");
      arrayOfProperty[localProperty.oridinal] = localProperty;
    }
  }

  public DaoConfig clone()
  {
    return new DaoConfig(this);
  }

  public IdentityScope<?, ?> getIdentityScope()
  {
    return this.identityScope;
  }

  public void initIdentityScope(IdentityScopeType paramIdentityScopeType)
  {
    if (paramIdentityScopeType == IdentityScopeType.None)
    {
      this.identityScope = null;
      return;
    }
    if (paramIdentityScopeType == IdentityScopeType.Session)
    {
      if (this.keyIsNumeric)
      {
        this.identityScope = new IdentityScopeLong();
        return;
      }
      this.identityScope = new IdentityScopeObject();
      return;
    }
    throw new IllegalArgumentException("Unsupported type: " + paramIdentityScopeType);
  }

  void setIdentityScope(IdentityScope<?, ?> paramIdentityScope)
  {
    this.identityScope = paramIdentityScope;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.DaoConfig
 * JD-Core Version:    0.6.0
 */