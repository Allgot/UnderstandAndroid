package dev_allgot.understand.doitmission_12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TextReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TextReceiver", "onReceive() called");
        if(intent.getAction().equals("dev_allgot.understand.doitmission_12.TEXT_NOTIFICATION")) {
            if(intent.getStringExtra("output") != null) {
                String output = intent.getStringExtra("output");
                Intent activityIntent = new Intent(context, MainActivity.class).putExtra("output", output);
                activityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(activityIntent);
            }
        }
    }
}
