package org.osmdroid.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GEMFFile
{
  private static final int FILE_COPY_BUFFER_SIZE = 1024;
  private static final long FILE_SIZE_LIMIT = 1073741824L;
  private static final int TILE_SIZE = 256;
  private static final int U32_SIZE = 4;
  private static final int U64_SIZE = 8;
  private static final int VERSION = 4;
  private int mCurrentSource = 0;
  private List<String> mFileNames = new ArrayList();
  private List<Long> mFileSizes = new ArrayList();
  private List<RandomAccessFile> mFiles = new ArrayList();
  private String mLocation;
  private List<GEMFRange> mRangeData = new ArrayList();
  private boolean mSourceLimited = false;
  private LinkedHashMap<Integer, String> mSources = new LinkedHashMap();

  public GEMFFile(File paramFile)
    throws FileNotFoundException, IOException
  {
    this(paramFile.getAbsolutePath());
  }

  public GEMFFile(String paramString)
    throws FileNotFoundException, IOException
  {
    this.mLocation = paramString;
    openFiles();
    readHeader();
  }

  public GEMFFile(String paramString, List<File> paramList)
    throws FileNotFoundException, IOException
  {
    this.mLocation = paramString;
    LinkedHashMap localLinkedHashMap1 = new LinkedHashMap();
    Iterator localIterator1 = paramList.iterator();
    LinkedHashMap localLinkedHashMap2;
    LinkedHashMap localLinkedHashMap3;
    int i;
    Iterator localIterator2;
    label132: ArrayList localArrayList1;
    Iterator localIterator3;
    label162: int m;
    Iterator localIterator9;
    label187: long l1;
    Iterator localIterator10;
    label227: long l2;
    RandomAccessFile localRandomAccessFile;
    Iterator localIterator11;
    label289: Iterator localIterator12;
    label320: Iterator localIterator13;
    if (!localIterator1.hasNext())
    {
      localLinkedHashMap2 = new LinkedHashMap();
      localLinkedHashMap3 = new LinkedHashMap();
      i = 0;
      localIterator2 = localLinkedHashMap1.keySet().iterator();
      if (localIterator2.hasNext())
        break label687;
      localArrayList1 = new ArrayList();
      localIterator3 = localLinkedHashMap1.keySet().iterator();
      if (localIterator3.hasNext())
        break label747;
      m = 0;
      localIterator9 = localLinkedHashMap2.keySet().iterator();
      if (localIterator9.hasNext())
        break label1550;
      l1 = 4 + (m + 12 + 32 * localArrayList1.size());
      localIterator10 = localArrayList1.iterator();
      if (localIterator10.hasNext())
        break label1574;
      l2 = l1;
      localRandomAccessFile = new RandomAccessFile(paramString, "rw");
      localRandomAccessFile.writeInt(4);
      localRandomAccessFile.writeInt(256);
      localRandomAccessFile.writeInt(localLinkedHashMap2.size());
      localIterator11 = localLinkedHashMap2.keySet().iterator();
      if (localIterator11.hasNext())
        break label1674;
      localRandomAccessFile.writeInt(localArrayList1.size());
      localIterator12 = localArrayList1.iterator();
      if (localIterator12.hasNext())
        break label1727;
      localIterator13 = localArrayList1.iterator();
    }
    byte[] arrayOfByte;
    long l4;
    int i8;
    Iterator localIterator14;
    label520: label676: label687: label747: label1395: GEMFRange localGEMFRange4;
    label1315: label1574: label1727: int i4;
    label1550: int i5;
    label1674: 
    do
    {
      if (!localIterator13.hasNext())
      {
        arrayOfByte = new byte[1024];
        l4 = l2;
        i8 = 0;
        localIterator14 = localArrayList1.iterator();
        if (localIterator14.hasNext())
          break label1992;
        localRandomAccessFile.close();
        openFiles();
        readHeader();
        return;
        File localFile1 = (File)localIterator1.next();
        LinkedHashMap localLinkedHashMap6 = new LinkedHashMap();
        File[] arrayOfFile1 = localFile1.listFiles();
        int i14 = arrayOfFile1.length;
        int i15 = 0;
        LinkedHashMap localLinkedHashMap7;
        int i17;
        File localFile3;
        while (true)
        {
          if (i15 >= i14)
          {
            localLinkedHashMap1.put(localFile1.getName(), localLinkedHashMap6);
            break;
          }
          File localFile2 = arrayOfFile1[i15];
          try
          {
            Integer.parseInt(localFile2.getName());
            localLinkedHashMap7 = new LinkedHashMap();
            arrayOfFile2 = localFile2.listFiles();
            int i16 = arrayOfFile2.length;
            i17 = 0;
            if (i17 >= i16)
            {
              localLinkedHashMap6.put(Integer.valueOf(Integer.parseInt(localFile2.getName())), localLinkedHashMap7);
              i15++;
            }
          }
          catch (NumberFormatException localNumberFormatException1)
          {
            File[] arrayOfFile2;
            break label520;
            localFile3 = arrayOfFile2[i17];
          }
        }
        while (true)
        {
          LinkedHashMap localLinkedHashMap8;
          int i19;
          File localFile4;
          try
          {
            Integer.parseInt(localFile3.getName());
            localLinkedHashMap8 = new LinkedHashMap();
            arrayOfFile3 = localFile3.listFiles();
            int i18 = arrayOfFile3.length;
            i19 = 0;
            if (i19 < i18)
              continue;
            Integer localInteger16 = new Integer(localFile3.getName());
            localLinkedHashMap7.put(localInteger16, localLinkedHashMap8);
            i17++;
          }
          catch (NumberFormatException localNumberFormatException2)
          {
            File[] arrayOfFile3;
            continue;
            localFile4 = arrayOfFile3[i19];
          }
          try
          {
            Integer.parseInt(localFile4.getName().substring(0, localFile4.getName().indexOf('.')));
            localLinkedHashMap8.put(Integer.valueOf(Integer.parseInt(localFile4.getName().substring(0, localFile4.getName().indexOf('.')))), localFile4);
            i19++;
          }
          catch (NumberFormatException localNumberFormatException3)
          {
            break label676;
          }
        }
        String str3 = (String)localIterator2.next();
        Integer localInteger14 = new Integer(i);
        localLinkedHashMap2.put(str3, localInteger14);
        Integer localInteger15 = new Integer(i);
        localLinkedHashMap3.put(localInteger15, str3);
        i++;
        break label132;
        String str1 = (String)localIterator3.next();
        Iterator localIterator4 = ((LinkedHashMap)localLinkedHashMap1.get(str1)).keySet().iterator();
        if (!localIterator4.hasNext())
          break label162;
        Integer localInteger1 = (Integer)localIterator4.next();
        LinkedHashMap localLinkedHashMap4 = new LinkedHashMap();
        TreeSet localTreeSet1 = new TreeSet(((LinkedHashMap)((LinkedHashMap)localLinkedHashMap1.get(str1)).get(localInteger1)).keySet());
        Iterator localIterator5 = localTreeSet1.iterator();
        LinkedHashMap localLinkedHashMap5;
        Iterator localIterator6;
        if (!localIterator5.hasNext())
        {
          localLinkedHashMap5 = new LinkedHashMap();
          localIterator6 = localLinkedHashMap4.keySet().iterator();
        }
        TreeSet localTreeSet3;
        TreeSet localTreeSet4;
        GEMFRange localGEMFRange1;
        int k;
        List localList1;
        TreeSet localTreeSet2;
        ArrayList localArrayList2;
        int j;
        while (true)
        {
          if (!localIterator6.hasNext())
          {
            Iterator localIterator7 = localLinkedHashMap5.keySet().iterator();
            while (localIterator7.hasNext())
            {
              List localList2 = (List)localIterator7.next();
              localTreeSet3 = new TreeSet(localList2);
              localTreeSet4 = new TreeSet((Collection)localLinkedHashMap4.get(localList2));
              localGEMFRange1 = new GEMFRange(null);
              localGEMFRange1.zoom = localInteger1;
              Integer localInteger4 = (Integer)localLinkedHashMap2.get(str1);
              localGEMFRange1.sourceIndex = localInteger4;
              Integer localInteger5 = (Integer)localTreeSet4.first();
              localGEMFRange1.xMin = localInteger5;
              Integer localInteger6 = (Integer)localTreeSet4.last();
              localGEMFRange1.xMax = localInteger6;
              k = ((Integer)localTreeSet3.first()).intValue();
              if (k < 1 + ((Integer)localTreeSet3.last()).intValue())
                break label1395;
              if (localGEMFRange1.yMin == null)
                continue;
              localArrayList1.add(localGEMFRange1);
            }
            break;
            Integer localInteger13 = (Integer)localIterator5.next();
            ArrayList localArrayList3 = new ArrayList();
            Iterator localIterator8 = ((LinkedHashMap)((LinkedHashMap)((LinkedHashMap)localLinkedHashMap1.get(str1)).get(localInteger1)).get(localInteger13)).keySet().iterator();
            while (true)
            {
              if (!localIterator8.hasNext())
              {
                if (localArrayList3.size() == 0)
                  break;
                Collections.sort(localArrayList3);
                if (!localLinkedHashMap4.containsKey(localArrayList3))
                  localLinkedHashMap4.put(localArrayList3, new ArrayList());
                ((List)localLinkedHashMap4.get(localArrayList3)).add(localInteger13);
                break;
              }
              localArrayList3.add((Integer)localIterator8.next());
            }
          }
          localList1 = (List)localIterator6.next();
          localTreeSet2 = new TreeSet((Collection)localLinkedHashMap4.get(localList1));
          localArrayList2 = new ArrayList();
          j = ((Integer)localTreeSet2.first()).intValue();
          if (j < 1 + ((Integer)localTreeSet2.last()).intValue())
            break label1315;
          if (localArrayList2.size() <= 0)
            continue;
          localLinkedHashMap5.put(localList1, localArrayList2);
        }
        Integer localInteger2 = new Integer(j);
        if (localTreeSet2.contains(localInteger2))
        {
          Integer localInteger3 = new Integer(j);
          localArrayList2.add(localInteger3);
        }
        while (true)
        {
          j++;
          break;
          if (localArrayList2.size() <= 0)
            continue;
          localLinkedHashMap5.put(localList1, localArrayList2);
          localArrayList2 = new ArrayList();
        }
        Integer localInteger7 = new Integer(k);
        if (localTreeSet3.contains(localInteger7))
        {
          if (localGEMFRange1.yMin == null)
          {
            Integer localInteger12 = Integer.valueOf(k);
            localGEMFRange1.yMin = localInteger12;
          }
          Integer localInteger11 = Integer.valueOf(k);
          localGEMFRange1.yMax = localInteger11;
        }
        while (true)
        {
          k++;
          break;
          if (localGEMFRange1.yMin == null)
            continue;
          localArrayList1.add(localGEMFRange1);
          localGEMFRange1 = new GEMFRange(null);
          localGEMFRange1.zoom = localInteger1;
          Integer localInteger8 = (Integer)localLinkedHashMap2.get(str1);
          localGEMFRange1.sourceIndex = localInteger8;
          Integer localInteger9 = (Integer)localTreeSet4.first();
          localGEMFRange1.xMin = localInteger9;
          Integer localInteger10 = (Integer)localTreeSet4.last();
          localGEMFRange1.xMax = localInteger10;
        }
        m += 8 + ((String)localIterator9.next()).length();
        break label187;
        GEMFRange localGEMFRange2 = (GEMFRange)localIterator10.next();
        localGEMFRange2.offset = Long.valueOf(l1);
        int n = localGEMFRange2.xMin.intValue();
        int i1 = 1 + localGEMFRange2.xMax.intValue();
        if (n >= i1)
          break label227;
        for (int i2 = localGEMFRange2.yMin.intValue(); ; i2++)
        {
          int i3 = 1 + localGEMFRange2.yMax.intValue();
          if (i2 >= i3)
          {
            n++;
            break;
          }
          l1 += 12L;
        }
        String str2 = (String)localIterator11.next();
        localRandomAccessFile.writeInt(((Integer)localLinkedHashMap2.get(str2)).intValue());
        localRandomAccessFile.writeInt(str2.length());
        localRandomAccessFile.write(str2.getBytes());
        break label289;
        GEMFRange localGEMFRange3 = (GEMFRange)localIterator12.next();
        localRandomAccessFile.writeInt(localGEMFRange3.zoom.intValue());
        localRandomAccessFile.writeInt(localGEMFRange3.xMin.intValue());
        localRandomAccessFile.writeInt(localGEMFRange3.xMax.intValue());
        localRandomAccessFile.writeInt(localGEMFRange3.yMin.intValue());
        localRandomAccessFile.writeInt(localGEMFRange3.yMax.intValue());
        localRandomAccessFile.writeInt(localGEMFRange3.sourceIndex.intValue());
        localRandomAccessFile.writeLong(localGEMFRange3.offset.longValue());
        break label320;
      }
      localGEMFRange4 = (GEMFRange)localIterator13.next();
      i4 = localGEMFRange4.xMin.intValue();
      i5 = 1 + localGEMFRange4.xMax.intValue();
    }
    while (i4 >= i5);
    for (int i6 = localGEMFRange4.yMin.intValue(); ; i6++)
    {
      int i7 = 1 + localGEMFRange4.yMax.intValue();
      if (i6 >= i7)
      {
        i4++;
        break;
      }
      localRandomAccessFile.writeLong(l1);
      long l3 = ((File)((LinkedHashMap)((LinkedHashMap)((LinkedHashMap)localLinkedHashMap1.get(localLinkedHashMap3.get(localGEMFRange4.sourceIndex))).get(localGEMFRange4.zoom)).get(Integer.valueOf(i4))).get(Integer.valueOf(i6))).length();
      localRandomAccessFile.writeInt((int)l3);
      l1 += l3;
    }
    label1992: GEMFRange localGEMFRange5 = (GEMFRange)localIterator14.next();
    int i11;
    for (int i9 = localGEMFRange5.xMin.intValue(); ; i9++)
    {
      int i10 = 1 + localGEMFRange5.xMax.intValue();
      if (i9 >= i10)
        break;
      i11 = localGEMFRange5.yMin.intValue();
      int i12 = 1 + localGEMFRange5.yMax.intValue();
      if (i11 < i12)
        break label2068;
    }
    label2068: long l5 = ((File)((LinkedHashMap)((LinkedHashMap)((LinkedHashMap)localLinkedHashMap1.get(localLinkedHashMap3.get(localGEMFRange5.sourceIndex))).get(localGEMFRange5.zoom)).get(Integer.valueOf(i9))).get(Integer.valueOf(i11))).length();
    label2186: FileInputStream localFileInputStream;
    if (l4 + l5 > 1073741824L)
    {
      localRandomAccessFile.close();
      i8++;
      StringBuilder localStringBuilder = new StringBuilder(String.valueOf(paramString));
      localRandomAccessFile = new RandomAccessFile("-" + i8, "rw");
      l4 = 0L;
      localFileInputStream = new FileInputStream((File)((LinkedHashMap)((LinkedHashMap)((LinkedHashMap)localLinkedHashMap1.get(localLinkedHashMap3.get(localGEMFRange5.sourceIndex))).get(localGEMFRange5.zoom)).get(Integer.valueOf(i9))).get(Integer.valueOf(i11)));
    }
    for (int i13 = localFileInputStream.read(arrayOfByte, 0, 1024); ; i13 = localFileInputStream.read(arrayOfByte, 0, 1024))
    {
      if (i13 == -1)
      {
        localFileInputStream.close();
        i11++;
        break;
        l4 += l5;
        break label2186;
      }
      localRandomAccessFile.write(arrayOfByte, 0, i13);
    }
  }

  private void openFiles()
    throws FileNotFoundException
  {
    File localFile1 = new File(this.mLocation);
    this.mFiles.add(new RandomAccessFile(localFile1, "r"));
    this.mFileNames.add(localFile1.getPath());
    int i = 0;
    while (true)
    {
      i++;
      File localFile2 = new File(this.mLocation + "-" + i);
      if (!localFile2.exists())
        break;
      this.mFiles.add(new RandomAccessFile(localFile2, "r"));
      this.mFileNames.add(localFile2.getPath());
    }
  }

  private void readHeader()
    throws IOException
  {
    RandomAccessFile localRandomAccessFile1 = (RandomAccessFile)this.mFiles.get(0);
    Iterator localIterator = this.mFiles.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        int i = localRandomAccessFile1.readInt();
        if (i == 4)
          break;
        throw new IOException("Bad file version: " + i);
      }
      RandomAccessFile localRandomAccessFile2 = (RandomAccessFile)localIterator.next();
      this.mFileSizes.add(Long.valueOf(localRandomAccessFile2.length()));
    }
    int j = localRandomAccessFile1.readInt();
    if (j != 256)
      throw new IOException("Bad tile size: " + j);
    int k = localRandomAccessFile1.readInt();
    int m = 0;
    int i2;
    if (m >= k)
      i2 = localRandomAccessFile1.readInt();
    for (int i3 = 0; ; i3++)
    {
      if (i3 >= i2)
      {
        return;
        int n = localRandomAccessFile1.readInt();
        int i1 = localRandomAccessFile1.readInt();
        byte[] arrayOfByte = new byte[i1];
        localRandomAccessFile1.read(arrayOfByte, 0, i1);
        String str = new String(arrayOfByte);
        this.mSources.put(new Integer(n), str);
        m++;
        break;
      }
      GEMFRange localGEMFRange = new GEMFRange(null);
      localGEMFRange.zoom = Integer.valueOf(localRandomAccessFile1.readInt());
      localGEMFRange.xMin = Integer.valueOf(localRandomAccessFile1.readInt());
      localGEMFRange.xMax = Integer.valueOf(localRandomAccessFile1.readInt());
      localGEMFRange.yMin = Integer.valueOf(localRandomAccessFile1.readInt());
      localGEMFRange.yMax = Integer.valueOf(localRandomAccessFile1.readInt());
      localGEMFRange.sourceIndex = Integer.valueOf(localRandomAccessFile1.readInt());
      localGEMFRange.offset = Long.valueOf(localRandomAccessFile1.readLong());
      this.mRangeData.add(localGEMFRange);
    }
  }

  public void acceptAnySource()
  {
    this.mSourceLimited = false;
  }

  public void close()
    throws IOException
  {
    Iterator localIterator = this.mFiles.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((RandomAccessFile)localIterator.next()).close();
    }
  }

  public InputStream getInputStream(int paramInt1, int paramInt2, int paramInt3)
  {
    Iterator localIterator = this.mRangeData.iterator();
    boolean bool1 = localIterator.hasNext();
    Object localObject = null;
    if (!bool1);
    while (true)
    {
      if (localObject != null)
        break label136;
      return null;
      GEMFRange localGEMFRange = (GEMFRange)localIterator.next();
      if ((paramInt3 != localGEMFRange.zoom.intValue()) || (paramInt1 < localGEMFRange.xMin.intValue()) || (paramInt1 > localGEMFRange.xMax.intValue()) || (paramInt2 < localGEMFRange.yMin.intValue()) || (paramInt2 > localGEMFRange.yMax.intValue()) || ((this.mSourceLimited) && (localGEMFRange.sourceIndex.intValue() != this.mCurrentSource)))
        break;
      localObject = localGEMFRange;
    }
    try
    {
      label136: int i = 1 + localObject.yMax.intValue() - localObject.yMin.intValue();
      int j = paramInt1 - localObject.xMin.intValue();
      long l1 = 12L * (paramInt2 - localObject.yMin.intValue() + j * i) + localObject.offset.longValue();
      RandomAccessFile localRandomAccessFile1 = (RandomAccessFile)this.mFiles.get(0);
      localRandomAccessFile1.seek(l1);
      long l2 = localRandomAccessFile1.readLong();
      int k = localRandomAccessFile1.readInt();
      RandomAccessFile localRandomAccessFile2 = (RandomAccessFile)this.mFiles.get(0);
      boolean bool2 = l2 < ((Long)this.mFileSizes.get(0)).longValue();
      int m = 0;
      int n;
      if (bool2)
        n = this.mFileSizes.size();
      while (true)
      {
        if ((m >= n - 1) || (l2 <= ((Long)this.mFileSizes.get(m)).longValue()))
        {
          localRandomAccessFile2 = (RandomAccessFile)this.mFiles.get(m);
          localRandomAccessFile2.seek(l2);
          return new GEMFInputStream((String)this.mFileNames.get(m), l2, k);
        }
        long l3 = ((Long)this.mFileSizes.get(m)).longValue();
        l2 -= l3;
        m++;
      }
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  public String getName()
  {
    return this.mLocation;
  }

  public LinkedHashMap<Integer, String> getSources()
  {
    return this.mSources;
  }

  public Set<Integer> getZoomLevels()
  {
    TreeSet localTreeSet = new TreeSet();
    Iterator localIterator = this.mRangeData.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localTreeSet;
      localTreeSet.add(((GEMFRange)localIterator.next()).zoom);
    }
  }

  public void selectSource(int paramInt)
  {
    if (this.mSources.containsKey(new Integer(paramInt)))
    {
      this.mSourceLimited = true;
      this.mCurrentSource = paramInt;
    }
  }

  class GEMFInputStream extends InputStream
  {
    RandomAccessFile raf;
    int remainingBytes;

    GEMFInputStream(String paramLong, long arg3, int arg5)
      throws IOException
    {
      this.raf = new RandomAccessFile(paramLong, "r");
      this.raf.seek(???);
      int i;
      this.remainingBytes = i;
    }

    public int available()
    {
      return this.remainingBytes;
    }

    public void close()
      throws IOException
    {
      this.raf.close();
    }

    public boolean markSupported()
    {
      return false;
    }

    public int read()
      throws IOException
    {
      if (this.remainingBytes > 0)
      {
        this.remainingBytes = (-1 + this.remainingBytes);
        return this.raf.read();
      }
      throw new IOException("End of stream");
    }

    public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      RandomAccessFile localRandomAccessFile = this.raf;
      if (paramInt2 > this.remainingBytes)
        paramInt2 = this.remainingBytes;
      int i = localRandomAccessFile.read(paramArrayOfByte, paramInt1, paramInt2);
      this.remainingBytes -= i;
      return i;
    }

    public long skip(long paramLong)
    {
      return 0L;
    }
  }

  private class GEMFRange
  {
    Long offset;
    Integer sourceIndex;
    Integer xMax;
    Integer xMin;
    Integer yMax;
    Integer yMin;
    Integer zoom;

    private GEMFRange()
    {
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[7];
      arrayOfObject[0] = this.sourceIndex;
      arrayOfObject[1] = this.zoom;
      arrayOfObject[2] = this.xMin;
      arrayOfObject[3] = this.xMax;
      arrayOfObject[4] = this.yMin;
      arrayOfObject[5] = this.yMax;
      arrayOfObject[6] = this.offset;
      return String.format("GEMF Range: source=%d, zoom=%d, x=%d-%d, y=%d-%d, offset=0x%08X", arrayOfObject);
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.util.GEMFFile
 * JD-Core Version:    0.6.0
 */