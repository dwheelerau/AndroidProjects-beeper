package com.dwheelerau.beeper;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.List;
import java.util.ArrayList;

import org.w3c.dom.Text;

public class Beeper_activity extends AppCompatActivity {

    private int milliSeconds = 0;
    private boolean running;
    //beep vars
    //private List<int> level = new ArrayList();
    //private List<int> beepInterval = new ArrayList();
    //private List<int> timeSwitch = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beeper_activity);
        if (savedInstanceState != null){
            milliSeconds = savedInstanceState.getInt("milliSeconds");
            running = savedInstanceState.getBoolean("running");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        runTimer();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //save progress during redraw
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("milliSeconds",milliSeconds);
        savedInstanceState.putBoolean("running",running);
    }

    //function for start button
    public void onClickStart(View view){
        running = true;
    }

    //function for stop button
    public void onClickStop(View view){
        running = false;
    }

    //function for reset
    public void onClickReset(View view){
        running = false;
        milliSeconds = 0;
    }

    private void runTimer(){
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int min = milliSeconds/600;
                int secs = (milliSeconds/10)%60;
                int msecs = milliSeconds%10; // 1/10 sec
                String time = String.format("%02d:%02d.%d",min, secs, msecs);
                timeView.setText(time);
                //clock ticks over now
                if (running) {
                    milliSeconds++;
                    }
                handler.postDelayed(this, 100);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beeper_activity, menu);
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
