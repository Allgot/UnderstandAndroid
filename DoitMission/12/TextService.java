package dev_allgot.understand.doitmission_12;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class TextService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getStringExtra("input") != null) {
            Log.d("TextService", "onStartCommand() called");
            String input = intent.getStringExtra("input");

            Intent broadcastIntent = new Intent("dev_allgot.understand.doitmission_12.TEXT_NOTIFICATION");
            broadcastIntent.setPackage(getPackageName());
            broadcastIntent.putExtra("output", input);
            sendBroadcast(broadcastIntent);
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
