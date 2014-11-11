package org.osmdroid.contributor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.osmdroid.contributor.util.RecordedGeoPoint;
import org.osmdroid.contributor.util.RecordedRouteGPXFormatter;
import org.osmdroid.contributor.util.Util;
import org.osmdroid.contributor.util.constants.OpenStreetMapContributorConstants;

public class OSMUploader
  implements OpenStreetMapContributorConstants
{
  public static final String API_VERSION = "0.5";
  private static final String BASE64_ENC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
  private static final String BOUNDARY = "----------------------------d10f7aa230e8";
  private static final int BUFFER_SIZE = 65535;
  private static final String DEFAULT_DESCRIPTION = "AndNav - automatically created route.";
  private static final String DEFAULT_TAGS = "AndNav";
  private static final String LINE_END = "\r\n";
  private static final SimpleDateFormat autoTagFormat;
  public static final SimpleDateFormat pseudoFileNameFormat = new SimpleDateFormat("yyyyMMdd'_'HHmmss'_'SSS");

  static
  {
    autoTagFormat = new SimpleDateFormat("MMMM yyyy");
  }

  private static String encodeBase64(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i >= (2 + paramString.length()) / 3)
      return localStringBuilder.toString();
    int j = Math.min(3, paramString.length() - i * 3);
    String str = paramString.substring(i * 3, j + i * 3);
    localStringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(str.charAt(0) >> '\002'));
    int k = (0x3 & str.charAt(0)) << '\004';
    int m;
    label92: int i1;
    label133: char c1;
    if (j == 1)
    {
      m = 0;
      localStringBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(m | k));
      if (j <= 1)
        break label221;
      int n = (0xF & str.charAt(1)) << '\002';
      if (j != 2)
        break label203;
      i1 = 0;
      c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(i1 | n);
      label145: localStringBuilder.append(c1);
      if (j <= 2)
        break label228;
    }
    label203: label221: label228: for (char c2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(0x3F & str.charAt(2)); ; c2 = '=')
    {
      localStringBuilder.append(c2);
      i++;
      break;
      m = (0xF0 & str.charAt(1)) >> '\004';
      break label92;
      i1 = (0xC0 & str.charAt(2)) >> '\006';
      break label133;
      c1 = '=';
      break label145;
    }
  }

  public static void upload(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, ArrayList<RecordedGeoPoint> paramArrayList, String paramString5)
    throws IOException
  {
    uploadAsync(paramString1, paramString2, paramString3, paramString4, paramBoolean, paramArrayList, paramString5);
  }

  public static void uploadAsync(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, ArrayList<RecordedGeoPoint> paramArrayList, String paramString5)
  {
    if ((paramString1 == null) || (paramString1.length() == 0));
    do
      return;
    while ((paramString2 == null) || (paramString2.length() == 0) || (paramString3 == null) || (paramString3.length() == 0) || (paramString4 == null) || (paramString4.length() == 0) || (paramString5 == null) || (paramString5.endsWith(".gpx")));
    new Thread(new Runnable(paramArrayList, paramString4, paramBoolean, paramString3, paramString1, paramString2, paramString5)
    {
      public void run()
      {
        if (!Util.isSufficienDataForUpload(OSMUploader.this))
          return;
        ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(RecordedRouteGPXFormatter.create(OSMUploader.this).getBytes());
        String str1 = this.val$tags;
        if ((this.val$addDateTags) || (str1 == null))
        {
          if (str1 != null)
            break label380;
          str1 = OSMUploader.autoTagFormat.format(new GregorianCalendar().getTime());
        }
        while (true)
        {
          HttpURLConnection localHttpURLConnection;
          DataOutputStream localDataOutputStream;
          try
          {
            if (this.val$description != null)
              break label422;
            str2 = "AndNav - automatically created route.";
            break label462;
            localHttpURLConnection = (HttpURLConnection)new URL("http://www.openstreetmap.org/api/0.5/gpx/create").openConnection();
            localHttpURLConnection.setConnectTimeout(15000);
            localHttpURLConnection.setRequestMethod("POST");
            localHttpURLConnection.setDoOutput(true);
            localHttpURLConnection.addRequestProperty("Authorization", "Basic " + OSMUploader.access$1(new StringBuilder(String.valueOf(this.val$username)).append(":").append(this.val$password).toString()));
            localHttpURLConnection.addRequestProperty("Content-Type", "multipart/form-data; boundary=----------------------------d10f7aa230e8");
            localHttpURLConnection.addRequestProperty("Connection", "close");
            localHttpURLConnection.addRequestProperty("Expect", "");
            localHttpURLConnection.connect();
            localDataOutputStream = new DataOutputStream(new BufferedOutputStream(localHttpURLConnection.getOutputStream()));
            OSMUploader.access$2(localDataOutputStream, "file", localByteArrayInputStream, this.val$pseudoFileName);
            OSMUploader.access$3(localDataOutputStream, "description", str2);
            OSMUploader.access$3(localDataOutputStream, "tags", str3);
            OSMUploader.access$3(localDataOutputStream, "public", "1");
            localDataOutputStream.writeBytes("------------------------------d10f7aa230e8--\r\n");
            localDataOutputStream.flush();
            int i = localHttpURLConnection.getResponseCode();
            String str4 = localHttpURLConnection.getResponseMessage();
            if (i == 200)
              break label451;
            if (localHttpURLConnection.getHeaderField("Error") == null)
              continue;
            str4 = str4 + "\n" + localHttpURLConnection.getHeaderField("Error");
            localHttpURLConnection.disconnect();
            throw new RuntimeException(i + " " + str4);
          }
          catch (Exception localException)
          {
            return;
          }
          label380: str1 = str1 + " " + OSMUploader.autoTagFormat.format(new GregorianCalendar().getTime());
          continue;
          label422: String str2 = this.val$description.replaceAll("\\.;&?,/", "_");
          label451: label462: 
          while (str1 != null)
          {
            str3 = str1.replaceAll("\\\\.;&?,/", "_");
            break;
            localDataOutputStream.close();
            localHttpURLConnection.disconnect();
            return;
          }
          String str3 = "AndNav";
        }
      }
    }
    , "OSMUpload-Thread").start();
  }

  public static void uploadAsync(String paramString1, String paramString2, boolean paramBoolean, ArrayList<RecordedGeoPoint> paramArrayList)
  {
    uploadAsync("PUT_YOUR_USERNAME_HERE", "PUT_YOUR_PASSWORD_HERE", paramString1, paramString2, paramBoolean, paramArrayList, pseudoFileNameFormat.format(new GregorianCalendar().getTime()) + "_" + "PUT_YOUR_USERNAME_HERE" + ".gpx");
  }

  public static void uploadAsync(ArrayList<RecordedGeoPoint> paramArrayList)
  {
    uploadAsync("AndNav - automatically created route.", "AndNav", true, paramArrayList);
  }

  private static void writeContentDisposition(DataOutputStream paramDataOutputStream, String paramString1, String paramString2)
    throws IOException
  {
    paramDataOutputStream.writeBytes("------------------------------d10f7aa230e8\r\n");
    paramDataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + paramString1 + "\"" + "\r\n");
    paramDataOutputStream.writeBytes("\r\n");
    paramDataOutputStream.writeBytes(paramString2 + "\r\n");
  }

  private static void writeContentDispositionFile(DataOutputStream paramDataOutputStream, String paramString1, InputStream paramInputStream, String paramString2)
    throws IOException
  {
    paramDataOutputStream.writeBytes("------------------------------d10f7aa230e8\r\n");
    paramDataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + paramString1 + "\"; filename=\"" + paramString2 + "\"" + "\r\n");
    paramDataOutputStream.writeBytes("Content-Type: application/octet-stream\r\n");
    paramDataOutputStream.writeBytes("\r\n");
    byte[] arrayOfByte = new byte[65535];
    int i = 0;
    BufferedInputStream localBufferedInputStream = new BufferedInputStream(paramInputStream);
    while (true)
    {
      int j = localBufferedInputStream.read(arrayOfByte);
      if (j < 0)
      {
        localBufferedInputStream.close();
        paramDataOutputStream.writeBytes("\r\n");
        return;
      }
      paramDataOutputStream.write(arrayOfByte, 0, j);
      paramDataOutputStream.flush();
      i += j;
    }
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.contributor.OSMUploader
 * JD-Core Version:    0.6.0
 */