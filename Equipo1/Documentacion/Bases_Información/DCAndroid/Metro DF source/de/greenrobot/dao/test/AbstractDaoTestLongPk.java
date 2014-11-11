package de.greenrobot.dao.test;

import de.greenrobot.dao.AbstractDao;
import java.util.Random;

public abstract class AbstractDaoTestLongPk<D extends AbstractDao<T, Long>, T> extends AbstractDaoTestSinglePk<D, T, Long>
{
  public AbstractDaoTestLongPk(Class<D> paramClass)
  {
    super(paramClass);
  }

  protected Long createRandomPk()
  {
    return Long.valueOf(this.random.nextLong());
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.test.AbstractDaoTestLongPk
 * JD-Core Version:    0.6.0
 */