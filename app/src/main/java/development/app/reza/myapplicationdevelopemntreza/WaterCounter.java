package development.app.reza.myapplicationdevelopemntreza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import data.DatabaceHandler2;
import me.itangqi.waveloadingview.WaveLoadingView;
import util.Utils;

public class WaterCounter extends AppCompatActivity {

    private static final String TAG = "WaterCounter";
    //defining variables
    DatabaceHandler2 dbHandler;
    private Button btnAdd;
    private Button btnDel;
    private EditText editText;
    private TextView totalWater;
    private WaveLoadingView prg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_counter);
        //setting the correct text edit, text view and button ot the correct id
        editText = (EditText) findViewById(R.id.WaterInput);
        btnAdd = (Button) findViewById(R.id.AddWater);
        btnDel = (Button) findViewById(R.id.reset_water);
        totalWater = (TextView) findViewById(R.id.WaterTotal);
        prg =(WaveLoadingView) findViewById(R.id.waveLoadingView);
        //linking the correct database handler for water counter
        dbHandler = new DatabaceHandler2(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newEntry = editText.getText().toString();
                //loop will determine if their is an empty field
                if (editText.length() != 0) {
                    AddData(newEntry);
                    prg.setProgressValue(50);
                    editText.setText("");
                }
                else {
                    //bring up message to fill in the field
                    toastMessage("put something in text field ");
                }
            }

        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHandler.DelData();
                editText.setText("0");
                prg.setProgressValue(0);
                deldata();
            }
        });
    }



    //updates the total water count
    private void refreshWater() {
        //new database from getApplicationContext
        dbHandler= new DatabaceHandler2(getApplicationContext());
        //making a water value in db
        int WaterValue = dbHandler.totalWater();
        //adding a format to the number of total water
        String formattedValue = Utils.formatNumber(WaterValue);
        //setting the water total amount on the text view
        //with formatted value of water total
        totalWater.setText("Total water: "+formattedValue+"ML");
    }
    //adds data to the db
    public void AddData(String newEntry) {
        //creating a new entry sa a boolean to insert data
        boolean insertData = dbHandler.addData(newEntry);
        //checks if data is inserted or not in db
        if (insertData) {
            //if yes water has been added
            toastMessage("Water has been added!");
            refreshWater();
        } else {
            //if no error message
            toastMessage("error try again");
        }
    }
    public void deldata()
    {
            toastMessage("Counter has been reset and new water to see changes");
            refreshWater();
    }

    //toast message method for onscreen messages on phone
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
