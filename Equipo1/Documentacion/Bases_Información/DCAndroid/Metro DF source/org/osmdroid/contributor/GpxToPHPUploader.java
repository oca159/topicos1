package org.osmdroid.contributor;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.osmdroid.contributor.util.RecordedGeoPoint;
import org.osmdroid.contributor.util.RecordedRouteGPXFormatter;
import org.osmdroid.contributor.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GpxToPHPUploader
{
  protected static final String UPLOADSCRIPT_URL = "http://www.PLACEYOURDOMAINHERE.com/anyfolder/gpxuploader/upload.php";
  private static final Logger logger = LoggerFactory.getLogger(GpxToPHPUploader.class);

  public static void uploadAsync(ArrayList<RecordedGeoPoint> paramArrayList)
  {
    new Thread(new Runnable(paramArrayList)
    {
      public void run()
      {
        try
        {
          if (!Util.isSufficienDataForUpload(GpxToPHPUploader.this))
            return;
          ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(RecordedRouteGPXFormatter.create(GpxToPHPUploader.this).getBytes());
          DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
          HttpPost localHttpPost = new HttpPost("http://www.PLACEYOURDOMAINHERE.com/anyfolder/gpxuploader/upload.php");
          MultipartEntity localMultipartEntity = new MultipartEntity();
          localMultipartEntity.addPart("gpxfile", new InputStreamBody(localByteArrayInputStream, System.currentTimeMillis() + ".gpx"));
          localDefaultHttpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
          localHttpPost.setEntity(localMultipartEntity);
          HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
          if (localHttpResponse.getStatusLine().getStatusCode() != 200)
          {
            GpxToPHPUploader.logger.error("GPXUploader", "status != HttpStatus.SC_OK");
            return;
          }
          InputStreamReader localInputStreamReader = new InputStreamReader(new BufferedInputStream(localHttpResponse.getEntity().getContent()));
          char[] arrayOfChar = new char[8192];
          StringBuilder localStringBuilder = new StringBuilder();
          while (true)
          {
            int i = localInputStreamReader.read(arrayOfChar);
            if (i == -1)
            {
              GpxToPHPUploader.logger.debug("GPXUploader", "Response: " + localStringBuilder.toString());
              return;
            }
            localStringBuilder.append(arrayOfChar, 0, i);
          }
        }
        catch (Exception localException)
        {
        }
      }
    }).start();
  }
}

/* Location:           C:\DCAndroid\classes-dex2jar.jar
 * Qualified Name:     org.osmdroid.contributor.GpxToPHPUploader
 * JD-Core Version:    0.6.0
 */