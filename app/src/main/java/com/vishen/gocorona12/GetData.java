package com.vishen.gocorona12;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetData extends AsyncTask<String,Void,Data> {
    Data d=new Data();
    String result="";
    String cases;
    String deaths;
    String active;
    String recovered;
    @Override
    protected Data doInBackground(String... urls) {
        URL url;
        HttpURLConnection httpURLConnection;
        try{
            url=new URL(urls[0]);
            httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            int data=inputStreamReader.read();
            while (data!=-1){
                result+=(char)data;
                data=inputStreamReader.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Log.i("Result",result);
        try {
            JSONObject jsonObject=new JSONObject(result);
          //  JSONArray jsonArray=jsonObject.getJSONArray("Countries");
            //JSONObject india=new JSONObject();
            //india=jsonArray.getJSONObject(96);
           // Log.i("India Data",india.toString());
            JSONObject jsonDATAObject=jsonObject.getJSONObject("data");
            Log.i("DATA",jsonDATAObject.toString());
            JSONObject jsonSummaryObject=jsonDATAObject.getJSONObject("summary");
            cases=jsonSummaryObject.getString("total");
            deaths=jsonSummaryObject.getString("deaths");
            recovered=jsonSummaryObject.getString("discharged");
            int activeInt=(Integer.parseInt(cases)-(Integer.parseInt(deaths)+Integer.parseInt(recovered)));
            active=Integer.toString(activeInt);
            Log.i("Active",active);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return d;
    }


    @Override
    protected void onPostExecute(Data mdata) {
        super.onPostExecute(mdata);
        mdata.setActive(active);
        mdata.setCases(cases);
        mdata.setDeaths(deaths);
        mdata.setRecovered(recovered);
        mdata.setName("India");
    }
}
