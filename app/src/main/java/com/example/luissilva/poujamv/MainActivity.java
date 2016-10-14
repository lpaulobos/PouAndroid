package com.example.luissilva.poujamv;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar drinkBar;
    private ProgressBar foodBar;
    private ImageView img;
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

    }
    public void waterPlease(View v)
    {
        drinkBar.setProgress(drinkBar.getProgress() + 25);
        switch (drinkBar.getProgress())
        {
            case 0:
                img.setImageDrawable(getDrawable(R.drawable.trigerred_esponja));
                break;
            case 25:
                img.setImageDrawable(getDrawable(R.drawable.ok_esponja));
                break;
        }
    }
    public void foodPlease(View v)
    {
        foodBar.setProgress(foodBar.getProgress()+25);
    }
}
