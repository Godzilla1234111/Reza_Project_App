package data;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import development.app.reza.myapplicationdevelopemntreza.R;

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave,btnDelete;
    private EditText editable_val;
    DatabaceHandler2 dbHandler;
    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_data_layout);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_val = (EditText) findViewById(R.id.editable_item);
        dbHandler = new DatabaceHandler2(this);

        //get intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //get itemID passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1);

        //now get name passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        editable_val.setText(selectedName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editable_val.getText().toString();
                if(!item.equals("")){
                    toastMessage("item has been saved");
                    dbHandler.updateWater(item,selectedID,selectedName);
                }else{
                    toastMessage("You must enter a name");
                }

            }
        });
        //button click listener
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete water id and name from db
                dbHandler.deleteWater(selectedID,selectedName);
                //sets the text to black
                editable_val.setText("");
                //message on phone to tell that item has been removed
                //from db
                toastMessage("item has been removed");
            }
        });
    }
    //to create custom message's
    private void toastMessage (String message){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }



