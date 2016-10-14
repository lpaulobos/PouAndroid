package com.example.luissilva.poujamv;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements ServiceConnection{

    private ProgressBar drinkBar;
    private ProgressBar foodBar;
    private ImageView img;
    private Counter counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drinkBar = (ProgressBar) findViewById(R.id.progressBar);
        foodBar = (ProgressBar) findViewById(R.id.progressBar2);
        img = (ImageView) findViewById(R.id.imageView);
        int a = drinkBar.getProgress();
        int b = foodBar.getProgress();
        drinkBar.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
        foodBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        img.setImageDrawable(getDrawable(R.drawable.trigerred_esponja));

    /*if()
    {
     startService(MyService);
    }*/
    }

    @Override
    protected void onResume() {
        super.onResume();


        //bindService();
    }

    public void waterPlease(View v)
    {
        drinkBar.setProgress(drinkBar.getProgress() + 25);
       /* int sum = drinkBar.getProgress()+ foodBar.getProgress();
        switch ()
        {
            case sum > 0:
                img.setImageDrawable(getDrawable(R.drawable.trigerred_esponja));
                break;
            case 25:
                img.setImageDrawable(getDrawable(R.drawable.ok_esponja));
                break;
            case 50:
                img.setImageDrawable(getDrawable(R.drawable.cuttie_esponja));
                break;
            case 75:
                img.setImageDrawable(getDrawable(R.drawable.happy_esponja));
                break;
        }*/
    }
    public void foodPlease(View v)
    {
        foodBar.setProgress(foodBar.getProgress()+25);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MyService.LocalBinder binder = (MyService.LocalBinder) service;
        counter = binder.getService();

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
