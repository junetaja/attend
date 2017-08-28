package id.co.uti.utiattendance.helper.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import java.util.Timer;

/**
 * Created by DELL on 8/24/2017.
 */

public class TimerService extends Service {

    private static Timer timer = new Timer();
    private Context context;
    long timeStart, timeEnd;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
////        return super.onStartCommand(intent, flags, startId);
//        return timeStart = SystemClock.currentThreadTimeMillis();
//    }


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        setTime();
    }

    private void setTime() {
        timeStart = SystemClock.elapsedRealtime();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timeEnd = SystemClock.elapsedRealtime();
    }
}
