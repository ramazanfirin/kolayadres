package com.example.kolayadres;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    int mCorePoolSize = 60;
    int mMaximumPoolSize = 80;
    int mKeepAliveTime = 10;
    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(mMaximumPoolSize);
    Executor mCustomThreadPoolExecutor = new ThreadPoolExecutor(mCorePoolSize, mMaximumPoolSize, mKeepAliveTime, TimeUnit.SECONDS, workQueue);

    Spinner ilceSpinner;
    Spinner mahalleSpinner;
    Spinner sokakSpinner;
    Spinner binaSpinner;
    Button showCoordinate;

    private ArrayAdapter<KeyValueDTO> dataAdapterForIlce;
    private ArrayAdapter<KeyValueDTO> dataAdapterForMahalle;
    private ArrayAdapter<KeyValueDTO> dataAdapterForSokak;
    private ArrayAdapter<KeyValueDTO> dataAdapterForBina;

    String destinationLat;
    String destinationLng;
    String binaId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Activity mainAcrivity = this;


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




        ilceSpinner = (Spinner) findViewById(R.id.ilceSpinner);
        mahalleSpinner = (Spinner) findViewById(R.id.mahalleSpinner);
        sokakSpinner = (Spinner) findViewById(R.id.sokakSpinner);
        binaSpinner = (Spinner) findViewById(R.id.binaSpinner);
        showCoordinate = (Button)findViewById(R.id.buttonGoster);


        KeyValueDTO[] ilceList =ManuelSelectionUtil.getIlceList();
        dataAdapterForIlce = new ArrayAdapter<KeyValueDTO>(this, android.R.layout.simple_spinner_item,ilceList);
        dataAdapterForIlce.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilceSpinner.setAdapter(dataAdapterForIlce);

        ilceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                System.out.println("geldi");
                if (pos==0)
                    return ;
                KeyValueDTO dto = (KeyValueDTO)parent.getItemAtPosition(pos);

       //         new MahalleTask(mainAcrivity).executeOnExecutor(mCustomThreadPoolExecutor,dto.getKey());
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mahalleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                System.out.println("geldi");
                if (pos==0)
                    return ;
                KeyValueDTO dto = (KeyValueDTO)parent.getItemAtPosition(pos);
         //       new SokakTask(mainAcrivity).executeOnExecutor(mCustomThreadPoolExecutor,dto.getKey());
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        sokakSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                System.out.println("geldi");
                if (pos==0)
                    return ;
                KeyValueDTO dto = (KeyValueDTO)parent.getItemAtPosition(pos);
           //     new BinaTask(mainAcrivity).executeOnExecutor(mCustomThreadPoolExecutor,dto.getKey());
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        binaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                System.out.println("geldi");
                if (pos==0)
                    return ;
                KeyValueDTO dto = (KeyValueDTO)parent.getItemAtPosition(pos);
                //new SokakTask().execute(dto.getKey());
                binaId = dto.getKey();
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });



        showCoordinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("Seciniz".equals(binaId) || binaId == null || binaId.equals(""))
                    return;
                //new GetCoordinateTask().executeOnExecutor(mCustomThreadPoolExecutor,binaId.toString());


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

