package com.equipo1.appmaps;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
	static String srv = "http://equipo1.azurewebsites.net/ServiceRutas.svc/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    
        final Button trazar = (Button) findViewById(R.id.bCalcular);
        trazar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, DisplayMapaActivity.class);
				startActivity(i);
			}
		});
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private static HttpEntity getEntity(String uri){
        HttpClient httpClient = new DefaultHttpClient();
        HttpEntity entity = null;
        try {
            HttpGet httpget = new HttpGet(uri);
            HttpResponse response = httpClient.execute(httpget);
            entity = response.getEntity();
        } catch (IOException ex) {
            //Hacer algo
        } 
        return entity;
    }
    
    public static Ruta[] getRutas(){
        Ruta rt = null;
        try {
            
            HttpEntity entity = getEntity(srv + "/GetEstudiante/1");

            if (entity != null) {
                InputStream instream = entity.getContent();
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                    String str = reader.readLine();
                    
                    Gson gson = new Gson();
                    
                    estudiante = gson.fromJson(str, Estudiante.class);
                } catch (IOException ex) {
                    throw ex;
                 }finally {
                    instream.close();
                }
            }
        }catch(Exception e1){}
        return estudiante;
    }
    
}
