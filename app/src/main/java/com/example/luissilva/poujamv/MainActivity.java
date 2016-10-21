package com.example.luissilva.poujamv;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ServiceConnection{

    public static ProgressBar drinkBar;
    public static ProgressBar foodBar;
    private ImageView img;
    private Counter counter;
    final ServiceConnection connection = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drinkBar = (ProgressBar) findViewById(R.id.progressBar);
        drinkBar.setProgress(70);
        drinkBar.setProgressTintList(ColorStateList.valueOf(Color.BLUE));

        foodBar = (ProgressBar) findViewById(R.id.progressBar2);
        foodBar.setProgress(70);
        foodBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));

        img = (ImageView) findViewById(R.id.imageView);
        img.setImageDrawable(getDrawable(R.drawable.trigerred_esponja));



        if(!IsMyServiceRunning(MyService.class))
        {
            startService(new Intent(this, MyService.class));
            Log.e("Service","has been started");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(bindService(new Intent(MainActivity.this, MyService.class), connection, Context.BIND_AUTO_CREATE))
            Toast.makeText(MainActivity.this, "Binded", Toast.LENGTH_SHORT).show();;
    }

    public void waterPlease(View v)
    {
        drinkBar.setProgress(drinkBar.getProgress() + 10);
    }
    public void foodPlease(View v)
    {
        foodBar.setProgress(foodBar.getProgress()+10);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        UnbindConnection();
    }

    private void UnbindConnection()
    {
        if(counter != null)
        {
            Log.d("BIND SERVICE SAMPLE", "STOP BIND SERVICE");
            counter = null;
            unbindService(connection);
        } else Log.d("BIND SERVICE SAMPLE", "THE SERVICE ISN'T CONNECTED");
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MyService.LocalBinder binder = (MyService.LocalBinder) service;
        counter = binder.getService();

    }


    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.d("BIND SERVICE SAMPLE", "SERVICE DISCONNECTED");
        counter = null;
    }
    private boolean IsMyServiceRunning(Class<MyService> myServiceClass)
    {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (MyService.class.getName().equals(service.service.getClassName())) {
                Log.d("If", "true");
                return true;
            }
            Log.d("If", "false");
        } return false ;
    }





}
