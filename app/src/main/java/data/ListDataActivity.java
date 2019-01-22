package data;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import development.app.reza.myapplicationdevelopemntreza.R;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";
    DatabaceHandler2 dbHandler;
    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_row2);
        myListView = (ListView) findViewById(R.id.ListViewWater);
        dbHandler = new DatabaceHandler2(this);

        populateListView();
    }

    private void populateListView() {
        //logs when data is displayed in list view
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get data and append to a list
        Cursor data = dbHandler.getWater();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //add it to the ArrayList from database col 1
            listData.add(data.getString(1));
        }
        //create a list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        myListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                //logs if this event happens
                Log.d(TAG, "onItemClick: You Clicked on " + name);
                //get the id associated with that name
                Cursor data = dbHandler.getWaterid(name);
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                //looks for a match of item id
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("name",name);
                    startActivity(editScreenIntent);
                }
                else{
                    //if id not available this message will show
                    toastMessage("No ID is with this name");
                }
            }
        });

    }
    //to create custom message's
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}

