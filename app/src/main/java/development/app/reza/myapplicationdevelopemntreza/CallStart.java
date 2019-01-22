package development.app.reza.myapplicationdevelopemntreza;

import android.app.Application;
import android.os.SystemClock;

public class CallStart extends Application {

   @Override
   public void onCreate() {
        super.onCreate();
        SystemClock.sleep(200);
   }
}

