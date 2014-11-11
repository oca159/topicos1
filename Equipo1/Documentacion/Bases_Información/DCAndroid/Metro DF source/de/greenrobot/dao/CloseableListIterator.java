package de.greenrobot.dao;

import java.io.Closeable;
import java.util.ListIterator;

public abstract interface CloseableListIterator<T> extends ListIterator<T>, Closeable
{
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.dao.CloseableListIterator
 * JD-Core Version:    0.6.0
 */