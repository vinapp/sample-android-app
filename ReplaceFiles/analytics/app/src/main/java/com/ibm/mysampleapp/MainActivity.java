package com.ibm.mysampleapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ibm.mobilefirstplatform.clientsdk.android.core.api.BMSClient;

import com.ibm.mobilefirstplatform.clientsdk.android.analytics.api.Analytics;
import com.ibm.mobilefirstplatform.clientsdk.android.logger.api.Logger;






public class MainActivity extends AppCompatActivity {

    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        

        // Core SDK must be initialized to interact with Bluemix Mobile services.
        BMSClient.getInstance().initialize(getApplicationContext(), BMSClient.REGION_SYDNEY);

        // In this code example, Analytics is configured to record lifecycle events.
        Analytics.init(getApplication(), getString(R.string.app_name), getString(R.string.analyticsApiKey), false, Analytics.DeviceEvent.LIFECYCLE);

        // Enable Logger (disabled by default), and set level to ERROR (DEBUG by default).
        Logger.storeLogs(true);
        Logger.setLogLevel(Logger.LEVEL.ERROR);

        Toast.makeText(getBaseContext(),"App is connected to IBM Cloud Analytics",Toast.LENGTH_LONG).show();
        
    }

    @Override
    public void onResume() {
        super.onResume();
        
        // Sends analytics data to the Mobile Analytics service. Your analytics data will only show in the Analytics dashboard after this call.
        Analytics.send();
        // Sends Logger data to the Mobile Analytics service. Your Logger data will only show in the Analytics dashboard after this call.
        Logger.send();
    }

    @Override
    public void onPause() {
        super.onPause();
        
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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
