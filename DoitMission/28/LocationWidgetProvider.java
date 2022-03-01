package dev_allgot.understand.doitmission_28;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.widget.RemoteViews;

public class LocationWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int appWidgetId:appWidgetIds) {
            updateWidget(context, appWidgetManager, appWidgetId);
        }
    }

    public void updateWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.location_widget);

        Intent smsActivityIntent = new Intent(context, SmsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 101, smsActivityIntent, PendingIntent.FLAG_IMMUTABLE);
        views.setOnClickPendingIntent(R.id.runButton, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}
