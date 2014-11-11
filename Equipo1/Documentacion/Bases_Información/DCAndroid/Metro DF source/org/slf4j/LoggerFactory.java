package org.slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import org.slf4j.helpers.SubstituteLoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticLoggerBinder;

public final class LoggerFactory
{
  private static final String[] API_COMPATIBILITY_LIST;
  static final int FAILED_INITILIZATION = 2;
  static final int GET_SINGLETON_EXISTS = 2;
  static final int GET_SINGLETON_INEXISTENT = 1;
  static int GET_SINGLETON_METHOD = 0;
  static int INITIALIZATION_STATE = 0;
  static final String MULTIPLE_BINDINGS_URL = "http://www.slf4j.org/codes.html#multiple_bindings";
  static final String NO_STATICLOGGERBINDER_URL = "http://www.slf4j.org/codes.html#StaticLoggerBinder";
  static final String NULL_LF_URL = "http://www.slf4j.org/codes.html#null_LF";
  static final int ONGOING_INITILIZATION = 1;
  private static String STATIC_LOGGER_BINDER_PATH;
  static final String SUBSTITUTE_LOGGER_URL = "http://www.slf4j.org/codes.html#substituteLogger";
  static final int SUCCESSFUL_INITILIZATION = 3;
  static SubstituteLoggerFactory TEMP_FACTORY;
  static final int UNINITIALIZED = 0;
  static final String UNSUCCESSFUL_INIT_MSG = "org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit";
  static final String UNSUCCESSFUL_INIT_URL = "http://www.slf4j.org/codes.html#unsuccessfulInit";
  static final String VERSION_MISMATCH = "http://www.slf4j.org/codes.html#version_mismatch";

  static
  {
    GET_SINGLETON_METHOD = 0;
    TEMP_FACTORY = new SubstituteLoggerFactory();
    API_COMPATIBILITY_LIST = new String[] { "1.5.5", "1.5.6", "1.5.7", "1.5.8" };
    STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
  }

  private static final void bind()
  {
    try
    {
      getSingleton();
      INITIALIZATION_STATE = 3;
      emitSubstitureLoggerWarning();
      return;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      INITIALIZATION_STATE = 2;
      String str = localNoClassDefFoundError.getMessage();
      if ((str != null) && (str.indexOf("org/slf4j/impl/StaticLoggerBinder") != -1))
      {
        Util.reportFailure("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
        Util.reportFailure("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
      }
      throw localNoClassDefFoundError;
    }
    catch (Exception localException)
    {
      INITIALIZATION_STATE = 2;
      Util.reportFailure("Failed to instantiate logger [" + getSingleton().getLoggerFactoryClassStr() + "]", localException);
    }
  }

  private static final void emitSubstitureLoggerWarning()
  {
    List localList = TEMP_FACTORY.getLoggerNameList();
    if (localList.size() == 0);
    while (true)
    {
      return;
      Util.reportFailure("The following loggers will not work becasue they were created");
      Util.reportFailure("during the default configuration phase of the underlying logging system.");
      Util.reportFailure("See also http://www.slf4j.org/codes.html#substituteLogger");
      for (int i = 0; i < localList.size(); i++)
        Util.reportFailure((String)localList.get(i));
    }
  }

  public static ILoggerFactory getILoggerFactory()
  {
    if (INITIALIZATION_STATE == 0)
    {
      INITIALIZATION_STATE = 1;
      performInitialization();
    }
    switch (INITIALIZATION_STATE)
    {
    default:
      throw new IllegalStateException("Unreachable code");
    case 3:
      return getSingleton().getLoggerFactory();
    case 2:
      throw new IllegalStateException("org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
    case 1:
    }
    return TEMP_FACTORY;
  }

  public static Logger getLogger(Class paramClass)
  {
    return getLogger(paramClass.getName());
  }

  public static Logger getLogger(String paramString)
  {
    return getILoggerFactory().getLogger(paramString);
  }

  private static final StaticLoggerBinder getSingleton()
  {
    return StaticLoggerBinder.getSingleton();
  }

  private static final void performInitialization()
  {
    bind();
    versionSanityCheck();
    singleImplementationSanityCheck();
  }

  static void reset()
  {
    INITIALIZATION_STATE = 0;
    GET_SINGLETON_METHOD = 0;
    TEMP_FACTORY = new SubstituteLoggerFactory();
  }

  private static void singleImplementationSanityCheck()
  {
    ArrayList localArrayList;
    do
      try
      {
        Enumeration localEnumeration = LoggerFactory.class.getClassLoader().getResources(STATIC_LOGGER_BINDER_PATH);
        localArrayList = new ArrayList();
        while (localEnumeration.hasMoreElements())
          localArrayList.add((URL)localEnumeration.nextElement());
      }
      catch (IOException localIOException)
      {
        Util.reportFailure("Error getting resources from path", localIOException);
        return;
      }
    while (localArrayList.size() <= 1);
    Util.reportFailure("Class path contains multiple SLF4J bindings.");
    for (int i = 0; i < localArrayList.size(); i++)
      Util.reportFailure("Found binding in [" + localArrayList.get(i) + "]");
    Util.reportFailure("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
  }

  private static final void versionSanityCheck()
  {
    while (true)
    {
      int j;
      try
      {
        String str = StaticLoggerBinder.REQUESTED_API_VERSION;
        int i = 0;
        j = 0;
        if (j >= API_COMPATIBILITY_LIST.length)
          continue;
        if (API_COMPATIBILITY_LIST[j].equals(str))
        {
          i = 1;
          break label95;
          if (i != 0)
            continue;
          Util.reportFailure("The requested version " + str + " by your slf4j binding is not compatible with " + Arrays.asList(API_COMPATIBILITY_LIST).toString());
          Util.reportFailure("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        Util.reportFailure("Unexpected problem occured during version sanity check", localThrowable);
        return;
      }
      catch (NoSuchFieldError localNoSuchFieldError)
      {
        return;
      }
      label95: j++;
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.slf4j.LoggerFactory
 * JD-Core Version:    0.6.0
 */