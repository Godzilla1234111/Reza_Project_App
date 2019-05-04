package development.app.reza.myapplicationdevelopemntreza;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class Widget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, CalorieCounter.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
            views.setOnClickPendingIntent(R.id.Wbtn1, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);

        }
        for (int appWidgetId : appWidgetIds) {
            Intent intent2 = new Intent(context, WaterCounter.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent2, 0);
            RemoteViews views2 = new RemoteViews(context.getPackageName(), R.layout.widget);
            views2.setOnClickPendingIntent(R.id.Wbtn2, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views2);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

