package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public final class ConstructorConstructor
{
  private final Map<Type, InstanceCreator<?>> instanceCreators;

  public ConstructorConstructor()
  {
    this(Collections.emptyMap());
  }

  public ConstructorConstructor(Map<Type, InstanceCreator<?>> paramMap)
  {
    this.instanceCreators = paramMap;
  }

  private <T> ObjectConstructor<T> newDefaultConstructor(Class<? super T> paramClass)
  {
    try
    {
      Constructor localConstructor = paramClass.getDeclaredConstructor(new Class[0]);
      if (!localConstructor.isAccessible())
        localConstructor.setAccessible(true);
      2 local2 = new ObjectConstructor(localConstructor)
      {
        public T construct()
        {
          try
          {
            Object localObject = this.val$constructor.newInstance(null);
            return localObject;
          }
          catch (InstantiationException localInstantiationException)
          {
            throw new RuntimeException("Failed to invoke " + this.val$constructor + " with no args", localInstantiationException);
          }
          catch (InvocationTargetException localInvocationTargetException)
          {
            throw new RuntimeException("Failed to invoke " + this.val$constructor + " with no args", localInvocationTargetException.getTargetException());
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
          }
          throw new AssertionError(localIllegalAccessException);
        }
      };
      return local2;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
    }
    return null;
  }

  private <T> ObjectConstructor<T> newDefaultImplementationConstructor(Class<? super T> paramClass)
  {
    if (Collection.class.isAssignableFrom(paramClass))
    {
      if (SortedSet.class.isAssignableFrom(paramClass))
        return new ObjectConstructor()
        {
          public T construct()
          {
            return new TreeSet();
          }
        };
      if (Set.class.isAssignableFrom(paramClass))
        return new ObjectConstructor()
        {
          public T construct()
          {
            return new LinkedHashSet();
          }
        };
      if (Queue.class.isAssignableFrom(paramClass))
        return new ObjectConstructor()
        {
          public T construct()
          {
            return new LinkedList();
          }
        };
      return new ObjectConstructor()
      {
        public T construct()
        {
          return new ArrayList();
        }
      };
    }
    if (Map.class.isAssignableFrom(paramClass))
      return new ObjectConstructor()
      {
        public T construct()
        {
          return new LinkedHashMap();
        }
      };
    return null;
  }

  private <T> ObjectConstructor<T> newUnsafeAllocator(Type paramType, Class<? super T> paramClass)
  {
    return new ObjectConstructor(paramClass, paramType)
    {
      private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();

      public T construct()
      {
        try
        {
          Object localObject = this.unsafeAllocator.newInstance(this.val$rawType);
          return localObject;
        }
        catch (Exception localException)
        {
        }
        throw new RuntimeException("Unable to invoke no-args constructor for " + this.val$type + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", localException);
      }
    };
  }

  public <T> ObjectConstructor<T> getConstructor(TypeToken<T> paramTypeToken)
  {
    Type localType = paramTypeToken.getType();
    Class localClass = paramTypeToken.getRawType();
    InstanceCreator localInstanceCreator = (InstanceCreator)this.instanceCreators.get(localType);
    Object localObject;
    if (localInstanceCreator != null)
      localObject = new ObjectConstructor(localInstanceCreator, localType)
      {
        public T construct()
        {
          return this.val$creator.createInstance(this.val$type);
        }
      };
    do
    {
      return localObject;
      localObject = newDefaultConstructor(localClass);
    }
    while (localObject != null);
    ObjectConstructor localObjectConstructor = newDefaultImplementationConstructor(localClass);
    if (localObjectConstructor != null)
      return localObjectConstructor;
    return (ObjectConstructor<T>)newUnsafeAllocator(localType, localClass);
  }

  public String toString()
  {
    return this.instanceCreators.toString();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.internal.ConstructorConstructor
 * JD-Core Version:    0.6.0
 */