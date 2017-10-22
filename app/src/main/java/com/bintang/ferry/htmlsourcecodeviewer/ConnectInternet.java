package com.bintang.ferry.htmlsourcecodeviewer;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ferry on 21/10/2017.
 */

public class ConnectInternet extends AsyncTask<String,Void,String> {

    Context ctx;

    public ConnectInternet(Context ct){
        ctx = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String s1 = strings[0];
        InputStream in;
        try {
            URL myURL = new URL(s1);
            HttpURLConnection myConn = (HttpURLConnection)myURL.openConnection();
            myConn.setReadTimeout(10000);
            myConn.setConnectTimeout(20000);
            myConn.setRequestMethod("GET");
            myConn.connect();

            in = myConn.getInputStream();

            BufferedReader myBuff = new BufferedReader(new InputStreamReader(in));

            StringBuilder st = new StringBuilder();
            String line = "";
            while ((line=myBuff.readLine())!=null) {
                st.append(line+" \n");
            }
            myBuff.close();
            in.close();
            return st.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        MainActivity.mytext.setText(s);
    }
}
