package com.example.choudhary.stack_exchange;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.widget.Toast.makeText;

public class GetDataAsync extends AsyncTask<String, Void, String> {
    private WeakReference<MainActivity> conRef;
    private ListViewAdapter lViewAdapter;

    ArrayList<String> title = new ArrayList<String>() ;
    ArrayList<String> askerName = new ArrayList<String>() ;
    ArrayList<String> viewCount = new ArrayList<String>() ;
    ArrayList<String> score = new ArrayList<String>() ;
    ArrayList<String> creationDate = new ArrayList<String>() ;
    ArrayList<String> lastEditDate = new ArrayList<String>() ;

    GetDataAsync(MainActivity con) {
        this.conRef = new WeakReference<>(con);;
    }
    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(in));
            StringBuilder total = new StringBuilder();
            for (String line; (line = r.readLine()) != null; ) {
                total.append(line).append('\n');
            }
            urlConnection.disconnect();
            JSONObject obj = new JSONObject(total.toString());

            for (int i=0; i<10;i++) {
                title.add(obj.getJSONArray("items").getJSONObject(i).getString("title"));
                askerName.add(obj.getJSONArray("items").getJSONObject(i).getJSONObject("owner").getString("display_name"));
                viewCount.add(obj.getJSONArray("items").getJSONObject(i).getString("view_count"));
                score.add(obj.getJSONArray("items").getJSONObject(i).getString("score"));
                creationDate.add(obj.getJSONArray("items").getJSONObject(i).getString("creation_date"));
                lastEditDate.add(obj.getJSONArray("items").getJSONObject(i).getString("last_edit_date"));
            }
            return "OKAY";
        } catch(Exception e){
            Log.d("Alpha", e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s == null) return;
        MainActivity con = this.conRef.get();

        lViewAdapter = new ListViewAdapter(con,title,askerName,viewCount, score, creationDate, lastEditDate);
        ListView listView = con.findViewById(R.id.list_view);
        listView.setAdapter(lViewAdapter);
        }
}
