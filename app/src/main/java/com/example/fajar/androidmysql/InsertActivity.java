package com.example.fajar.androidmysql;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class InsertActivity extends Activity {
    private EditText txtNama;
    private EditText txtHarga;
    private Button btnSimpan;
    // Seusuaikan url dengan nama domain yang anda gunakan
    private String url = "http://192.168.0.104/android/tambah.php";
    /**
     * Method yang dipanggil pada saat aplikaasi dijalankan
     * */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah);
        txtNama = (EditText) findViewById(R.id.txtNama);
        txtHarga = (EditText) findViewById(R.id.txtHarga);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnSimpan.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String nama = URLEncoder.encode(txtNama.getText()
                            .toString(), "utf-8");
                    String harga = URLEncoder.encode(txtHarga.getText()
                            .toString(), "utf-8");
                    url += "?nama=" + nama + "&harga=" + harga;
                    getRequest(url);
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Method untuk Mengirimkan data kes erver event by button login diklik
     *
     */
    public void getRequest(String Url) {
        Toast.makeText(this, "Tambah Data " + Url + " ", Toast.LENGTH_LONG).show();
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            Toast.makeText(this, "Tambah Data " + request(response) + " ",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(this, "Tambah Data Gagal !", Toast.LENGTH_LONG)
                    .show();
        }
    }
    /**
     * Method untuk Menenrima data dari server
     *
     * @param response
     * @return
     */
    public static String request(HttpResponse response) {
        String result = "";
        try {
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        } catch (Exception ex) {
            result = "Error";
        }
        return result;
    }
}