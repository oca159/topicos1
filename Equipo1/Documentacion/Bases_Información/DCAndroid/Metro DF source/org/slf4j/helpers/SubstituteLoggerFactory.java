package org.slf4j.helpers;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class SubstituteLoggerFactory
  implements ILoggerFactory
{
  final List loggerNameList = new ArrayList();

  public Logger getLogger(String paramString)
  {
    this.loggerNameList.add(paramString);
    return NOPLogger.NOP_LOGGER;
  }

  public List getLoggerNameList()
  {
    return this.loggerNameList;
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.helpers.SubstituteLoggerFactory
 * JD-Core Version:    0.6.0
 */