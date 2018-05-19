package com.example.fajar.androidmysql;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends  ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        String[] menu = new String[] { "Tambah Data", "Tampilkan Data", "Exit" };
        this.setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menu));
    }
    @Override

    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Object o = this.getListAdapter().getItem(position);
        String pilihan = o.toString();
        tampilkanPilihan(pilihan);
    }
    protected void tampilkanPilihan(String pilihan) {
        try {
            Intent i = null;
            if (pilihan.equals("Tambah Data")) {
                i = new Intent(this, InsertActivity.class);
            } else if (pilihan.equals("Tampilkan Data")) {
                i = new Intent(this, JSONActivity.class);
            } else if (pilihan.equals("Exit")) {
                finish();
            } else {
                Toast.makeText(this,"Anda Memilih: " + pilihan + " , " +
                "Actionnya belum dibuat", Toast.LENGTH_LONG).show();
            }
            startActivity(i);
        }
        catch (Exception e) {
            e.printStackTrace();

                }

             }
        }
