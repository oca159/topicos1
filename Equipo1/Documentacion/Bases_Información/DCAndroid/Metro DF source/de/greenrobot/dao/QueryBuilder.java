package de.greenrobot.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class QueryBuilder<T>
{
  public static boolean LOG_SQL;
  public static boolean LOG_VALUES;
  private final AbstractDao<T, ?> dao;
  private StringBuilder joinBuilder;
  private Integer limit;
  private Integer offset;
  private StringBuilder orderBuilder;
  private final String tablePrefix;
  private final List<Object> values;
  private final List<WhereCondition> whereConditions;

  protected QueryBuilder(AbstractDao<T, ?> paramAbstractDao)
  {
    this(paramAbstractDao, "T");
  }

  protected QueryBuilder(AbstractDao<T, ?> paramAbstractDao, String paramString)
  {
    this.dao = paramAbstractDao;
    this.tablePrefix = paramString;
    this.values = new ArrayList();
    this.whereConditions = new ArrayList();
  }

  private void appendWhereClause(StringBuilder paramStringBuilder, String paramString)
  {
    this.values.clear();
    ListIterator localListIterator;
    if (!this.whereConditions.isEmpty())
    {
      paramStringBuilder.append(" WHERE ");
      localListIterator = this.whereConditions.listIterator();
    }
    while (true)
    {
      if (!localListIterator.hasNext())
        return;
      if (localListIterator.hasPrevious())
        paramStringBuilder.append(" AND ");
      WhereCondition localWhereCondition = (WhereCondition)localListIterator.next();
      localWhereCondition.appendTo(paramStringBuilder, paramString);
      localWhereCondition.appendValuesTo(this.values);
    }
  }

  private void checkOrderBuilder()
  {
    if (this.orderBuilder == null)
      this.orderBuilder = new StringBuilder();
    do
      return;
    while (this.orderBuilder.length() <= 0);
    this.orderBuilder.append(",");
  }

  private void orderAscOrDesc(String paramString, Property[] paramArrayOfProperty)
  {
    int i = paramArrayOfProperty.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      Property localProperty = paramArrayOfProperty[j];
      checkOrderBuilder();
      append(this.orderBuilder, localProperty);
      if (String.class.equals(localProperty.type))
        this.orderBuilder.append(" COLLATE LOCALIZED");
      this.orderBuilder.append(paramString);
    }
  }

  protected void addCondition(StringBuilder paramStringBuilder, List<Object> paramList, WhereCondition paramWhereCondition)
  {
    checkCondition(paramWhereCondition);
    paramWhereCondition.appendTo(paramStringBuilder, this.tablePrefix);
    paramWhereCondition.appendValuesTo(paramList);
  }

  public WhereCondition and(WhereCondition paramWhereCondition1, WhereCondition paramWhereCondition2, WhereCondition[] paramArrayOfWhereCondition)
  {
    return combineWhereConditions(" AND ", paramWhereCondition1, paramWhereCondition2, paramArrayOfWhereCondition);
  }

  protected StringBuilder append(StringBuilder paramStringBuilder, Property paramProperty)
  {
    checkProperty(paramProperty);
    paramStringBuilder.append(this.tablePrefix).append('.').append('\'').append(paramProperty.columnName).append('\'');
    return paramStringBuilder;
  }

  public Query<T> build()
  {
    String str1;
    if ((this.joinBuilder == null) || (this.joinBuilder.length() == 0))
      str1 = this.dao.getStatements().getSelectAll();
    StringBuilder localStringBuilder;
    int i;
    int j;
    while (true)
    {
      localStringBuilder = new StringBuilder(str1);
      appendWhereClause(localStringBuilder, this.tablePrefix);
      if ((this.orderBuilder != null) && (this.orderBuilder.length() > 0))
        localStringBuilder.append(" ORDER BY ").append(this.orderBuilder);
      i = -1;
      if (this.limit != null)
      {
        localStringBuilder.append(" LIMIT ?");
        this.values.add(this.limit);
        i = -1 + this.values.size();
      }
      j = -1;
      if (this.offset == null)
        break;
      if (this.limit == null)
      {
        throw new IllegalStateException("Offset cannot be set without limit");
        str1 = SqlUtils.createSqlSelect(this.dao.getTablename(), this.tablePrefix, this.dao.getAllColumns());
        continue;
      }
      localStringBuilder.append(" OFFSET ?");
      this.values.add(this.offset);
      j = -1 + this.values.size();
    }
    String str2 = localStringBuilder.toString();
    if (LOG_SQL)
      DaoLog.d("Built SQL for query: " + str2);
    if (LOG_VALUES)
      DaoLog.d("Values for query: " + this.values);
    Query localQuery = new Query(this.dao, str2, this.values);
    if (i != -1)
      localQuery.setLimitPosition(i);
    if (j != -1)
      localQuery.setOffsetPosition(j);
    return localQuery;
  }

  public DeleteQuery<T> buildDelete()
  {
    String str1 = this.dao.getTablename();
    StringBuilder localStringBuilder = new StringBuilder(SqlUtils.createSqlDelete(str1, null));
    appendWhereClause(localStringBuilder, str1);
    String str2 = localStringBuilder.toString();
    if (LOG_SQL)
      DaoLog.d("Built SQL for delete query: " + str2);
    if (LOG_VALUES)
      DaoLog.d("Values for delete query: " + this.values);
    return new DeleteQuery(this.dao, str2, this.values);
  }

  protected void checkCondition(WhereCondition paramWhereCondition)
  {
    if ((paramWhereCondition instanceof WhereCondition.PropertyCondition))
      checkProperty(((WhereCondition.PropertyCondition)paramWhereCondition).property);
  }

  protected void checkProperty(Property paramProperty)
  {
    if (this.dao != null)
    {
      Property[] arrayOfProperty = this.dao.getProperties();
      int i = arrayOfProperty.length;
      for (int j = 0; ; j++)
      {
        int k = 0;
        if (j >= i);
        while (true)
        {
          if (k != 0)
            return;
          throw new DaoException("Property '" + paramProperty.name + "' is not part of " + this.dao);
          if (paramProperty != arrayOfProperty[j])
            break;
          k = 1;
        }
      }
    }
  }

  protected WhereCondition combineWhereConditions(String paramString, WhereCondition paramWhereCondition1, WhereCondition paramWhereCondition2, WhereCondition[] paramArrayOfWhereCondition)
  {
    StringBuilder localStringBuilder = new StringBuilder("(");
    ArrayList localArrayList = new ArrayList();
    addCondition(localStringBuilder, localArrayList, paramWhereCondition1);
    localStringBuilder.append(paramString);
    addCondition(localStringBuilder, localArrayList, paramWhereCondition2);
    int i = paramArrayOfWhereCondition.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        localStringBuilder.append(')');
        return new WhereCondition.StringCondition(localStringBuilder.toString(), localArrayList.toArray());
      }
      WhereCondition localWhereCondition = paramArrayOfWhereCondition[j];
      localStringBuilder.append(paramString);
      addCondition(localStringBuilder, localArrayList, localWhereCondition);
    }
  }

  public <J> QueryBuilder<J> join(Class<J> paramClass, Property paramProperty)
  {
    throw new UnsupportedOperationException();
  }

  public <J> QueryBuilder<J> joinToMany(Class<J> paramClass, Property paramProperty)
  {
    throw new UnsupportedOperationException();
  }

  public QueryBuilder<T> limit(int paramInt)
  {
    this.limit = Integer.valueOf(paramInt);
    return this;
  }

  public List<T> list()
  {
    return build().list();
  }

  public CloseableListIterator<T> listIterator()
  {
    return build().listIterator();
  }

  public LazyList<T> listLazy()
  {
    return build().listLazy();
  }

  public LazyList<T> listLazyUncached()
  {
    return build().listLazyUncached();
  }

  public QueryBuilder<T> offset(int paramInt)
  {
    this.offset = Integer.valueOf(paramInt);
    return this;
  }

  public WhereCondition or(WhereCondition paramWhereCondition1, WhereCondition paramWhereCondition2, WhereCondition[] paramArrayOfWhereCondition)
  {
    return combineWhereConditions(" OR ", paramWhereCondition1, paramWhereCondition2, paramArrayOfWhereCondition);
  }

  public QueryBuilder<T> orderAsc(Property[] paramArrayOfProperty)
  {
    orderAscOrDesc(" ASC", paramArrayOfProperty);
    return this;
  }

  public QueryBuilder<T> orderCustom(Property paramProperty, String paramString)
  {
    checkOrderBuilder();
    append(this.orderBuilder, paramProperty).append(' ');
    this.orderBuilder.append(paramString);
    return this;
  }

  public QueryBuilder<T> orderDesc(Property[] paramArrayOfProperty)
  {
    orderAscOrDesc(" DESC", paramArrayOfProperty);
    return this;
  }

  public QueryBuilder<T> orderRaw(String paramString)
  {
    checkOrderBuilder();
    this.orderBuilder.append(paramString);
    return this;
  }

  public T unique()
  {
    return build().unique();
  }

  public T uniqueOrThrow()
  {
    return build().uniqueOrThrow();
  }

  public QueryBuilder<T> where(WhereCondition paramWhereCondition, WhereCondition[] paramArrayOfWhereCondition)
  {
    this.whereConditions.add(paramWhereCondition);
    int i = paramArrayOfWhereCondition.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return this;
      WhereCondition localWhereCondition = paramArrayOfWhereCondition[j];
      checkCondition(localWhereCondition);
      this.whereConditions.add(localWhereCondition);
    }
  }

  public QueryBuilder<T> whereOr(WhereCondition paramWhereCondition1, WhereCondition paramWhereCondition2, WhereCondition[] paramArrayOfWhereCondition)
  {
    this.whereConditions.add(or(paramWhereCondition1, paramWhereCondition2, paramArrayOfWhereCondition));
    return this;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.QueryBuilder
 * JD-Core Version:    0.6.0
 */