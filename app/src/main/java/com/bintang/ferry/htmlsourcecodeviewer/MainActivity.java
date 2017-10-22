package com.bintang.ferry.htmlsourcecodeviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.AdapterView;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConnectInternet ci;
    static TextView mytext;
    static EditText inputweb;
    static ScrollView sv;
    private Spinner sp;
    String protokol = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mytext = (TextView)findViewById(R.id.txt_result);
        inputweb = (EditText)findViewById(R.id.txt_input);
        sv = (ScrollView)findViewById(R.id.scroll_view);
        sp = (Spinner)findViewById(R.id.spinner);
        sp.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        String firstItem = String.valueOf(sp.getSelectedItem());
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(sp.getSelectedItem()))) {
                // ToDo when first item is selected
            }
            else {
                Toast.makeText(parent.getContext(),"Protocol yang dipilih " + parent.getItemAtPosition(pos).toString(),Toast.LENGTH_LONG).show();
                protokol = parent.getItemAtPosition(pos).toString();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public boolean isOnline()
    {
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo=cm.getActiveNetworkInfo();
        if(netinfo!= null && netinfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }

    public void doSomething(View view) {
        if(isOnline()==true) {
            if (protokol.toString().equals("")&&inputweb.getText().toString().equals("")) {
                Toast.makeText(this,"Pilih Protocol dan Masukan Alamat Websitenya !!!",Toast.LENGTH_SHORT).show();
            }
            else if(inputweb.getText().toString().equals("")) {
                Toast.makeText(this,"Masukan Alamat Websitenya !!!",Toast.LENGTH_SHORT).show();
            }
            else if(protokol.toString().equals("")) {
                Toast.makeText(this,"Pilih Protocolnya !!!",Toast.LENGTH_SHORT).show();
            }
            else {
                ci=new ConnectInternet(this);
                ci.execute(protokol+inputweb.getText().toString());
            }
        }
        else {
            Toast.makeText(this,"Cek Koneksi Anda !!!",Toast.LENGTH_SHORT).show();
        }
    }
}
