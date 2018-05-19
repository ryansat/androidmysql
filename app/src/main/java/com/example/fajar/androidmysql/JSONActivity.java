package com.example.fajar.androidmysql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;
public class JSONActivity extends Activity {
    private JSONObject jObject;
    private String xResult ="";
    //Seusuaikan url dengan nama domain yang anda gunakan
    private String url = "http://10.0.2.2/android/daftarmakanan.php";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftarmakanan);
        TextView txtResult = (TextView)findViewById(R.id.TextViewResult);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection a = new Connection();
        URL as;
        xResult = getRequest(url);

    }

    public class Connection extends AsyncTask<URL, Integer, Long> {
        protected Void doInBackground(Void arg0) {
            try {
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet("http://192.168.0.104/android/daftarmakanan.php");
                HttpResponse response = client.execute(request);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected Long doInBackground(URL... urls) {
            return null;
        }
    }
    private void parse(TextView txtResult) throws Exception {
        jObject = new JSONObject(xResult);
        JSONArray menuitemArray = jObject.getJSONArray("makanan");
        String sret="";
        for (int i = 0; i < menuitemArray.length(); i++) {
            sret +=menuitemArray.getJSONObject(i)
                    .getString("nama_makanan").toString()+" : ";
            System.out.println(menuitemArray.getJSONObject(i)
                    .getString("nama_makanan").toString());
            System.out.println(menuitemArray.getJSONObject(i).getString(
                    "harga").toString());
            sret +=menuitemArray.getJSONObject(i).getString(
                    "harga").toString()+"\n";
        }
        txtResult.setText(sret);
    }
    /**
     * Method untuk Mengirimkan data kes erver
     * event by button login diklik
     *

     */
    public String getRequest(String Url){
        String sret="";
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(Url);
        try{
            HttpResponse response = client.execute(request);
            sret =request(response);
        }catch(Exception ex){
            Toast.makeText(this,"Gagal "+ex, Toast.LENGTH_LONG).show();
        }
        return sret;
    }
    /**
     * Method untuk Menenrima data dari server
     * @param response
     * @return
     */
    public static String request(HttpResponse response){
        String result = "";
        try{
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        }catch(Exception ex){
            result = "Error";
        }
        return result;
    }
}