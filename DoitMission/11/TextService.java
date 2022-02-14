package dev_allgot.understand.doitmission_11;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TextService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input;
        if(intent.getStringExtra("input") != null) {
            input = intent.getStringExtra("input");
            Intent activityIntent = new Intent(getApplicationContext(), MainActivity.class);

            activityIntent.putExtra("output", input);
            activityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(activityIntent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
