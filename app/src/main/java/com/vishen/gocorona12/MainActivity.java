package com.vishen.gocorona12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView cases=findViewById(R.id.cases);
        TextView active=findViewById(R.id.active);
        TextView deaths=findViewById(R.id.deaths);
        TextView recovered=findViewById(R.id.recovered);
        Data mData=new Data();
        GetData getData=new GetData();
       // String url="https://api.covid19api.com/summary";
        String url="https://api.rootnet.in/covid19-in/stats/latest";
        try {
            getData.execute(url).get();
            getData.onPostExecute(mData);
            cases.setText(mData.getCases());
            active.setText(mData.getActive());
            deaths.setText(mData.getDeaths());
            recovered.setText(mData.getRecovered());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
