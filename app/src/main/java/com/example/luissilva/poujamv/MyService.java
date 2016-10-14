package com.example.luissilva.poujamv;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service implements Runnable, Counter{
    protected int count;
    private boolean active;
    private final LocalBinder connection = new LocalBinder();

    public MyService() {
    }
    public class LocalBinder extends Binder
    {
        public Counter getService() { return MyService.this; }
    }

    @Override
    public int Count() { return count; }

    public void onCreate()
    {
        super.onCreate();

        Log.d("SERVICE SAMPLE", "SERVICE SAMPLE onCreate()");
        active = true;
        new Thread(this).start();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d("SERVICE SAMPLE", "SERVICE SAMPLE onStart()");
        Toast.makeText(this,"BobEsponjamv está vivo e quer interagir",Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        Log.d("SERVICE SAMPLE", "SERVICE SAMPLE onDestroy()");
        active = false;
    }

    @Override
    public IBinder onBind(Intent intent) { return connection; }

    @Override
    public void run()
    {
      while(active)
        {
            Log.d("SERVICE SAMPLE", "EXECUTING SERVICE: " + count);
            count++;

            SetInterval();
        }

        count = 0;
        Log.d("SERVICE SAMPLE", "SERVICE SAMPLE: FIM");

        stopSelf();
    }
    private void SetInterval()
    {
        try { Thread.sleep(1000); }
        catch(InterruptedException e) { e.printStackTrace(); }
    }

}
