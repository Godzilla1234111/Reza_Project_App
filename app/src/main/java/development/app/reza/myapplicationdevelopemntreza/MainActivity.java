package development.app.reza.myapplicationdevelopemntreza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //created variables//
    private static final String TAG = "MainActivity";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");
        ImageView imageView1 = findViewById(R.id.imageView1);

        //Making the image appear on the screen in the image view//
        int imageResource = getResources().getIdentifier("@drawable/image", null, this.getPackageName());
        imageView1.setImageResource(imageResource);

        //button that will open step counter//
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStepCounter();
            }
        });

        //button that will open calorie counter//
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalorieCounter();
            }
        });

        //button that will open water counter//
        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWaterCounter();
            }
        });
    }

    //going back to previous screen //
    public void openStepCounter() {
        Intent intent = new Intent(this, StepCounter.class);
        startActivity(intent);
    }

    //going back to previous screen //
    public void openWaterCounter() {
        Intent intent = new Intent(this, WaterCounter.class);
        startActivity(intent);
    }

    //going back to previous screen //
    public void openCalorieCounter() {
        Intent intent = new Intent(this, CalorieCounter.class);
        startActivity(intent);
    }

}





