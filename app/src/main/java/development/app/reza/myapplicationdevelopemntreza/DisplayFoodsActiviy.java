package development.app.reza.myapplicationdevelopemntreza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import data.CustomListViewAdapter;
import model.*;
import java.util.ArrayList;

import data.DatabaseHandler;
import util.Utils;

public class DisplayFoodsActiviy extends AppCompatActivity {
    //creating the variables
    private DatabaseHandler dba;
    //making a new aerialist of food
    private ArrayList<Food> dbFoods = new ArrayList<>();
    private CustomListViewAdapter foodAdapter;
    private ListView listView;

    private Food myFood;
    private TextView totalCals, totalFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_foods_activiy);
        //setting up the list view with appropriate id
        listView = (ListView) findViewById(R.id.List);
        totalCals = (TextView) findViewById(R.id.totalAmountTextView);
        totalFoods = (TextView) findViewById(R.id.totalItemTextView);
        //method used to refresh the data
        refreshData();
    }
    //everything needed to populate list view is in this method
    private void refreshData() {
        //clear residual data from db
        dbFoods.clear();
        //create new database handler from getApplicationContext
        dba = new DatabaseHandler(getApplicationContext());
        //creating an internal aerialist to call get foods method
        ArrayList<Food> foodsFromDB = dba.getFoods();
        //gets the calories and items from the database
        int calsValue = dba.totalCalories();
        int totalItems = dba.getTotalItems();
        // using the utils format to add comma
        // when number goes over certain digests
        String formattedValue = Utils.formatNumber(calsValue);
        String formattedItems = Utils.formatNumber(totalItems);
        //setting the text-view to show total items in list view
        //and total calorie in list view
        totalCals.setText("Total Calories: " + formattedValue);
        totalFoods.setText( "Number Of Items: " + formattedItems);
        //will loop through all the database items
        for (int i = 0; i < foodsFromDB.size(); i++){
            //looping through these items
            String name = foodsFromDB.get(i).getFoodName();
            String dateText = foodsFromDB.get(i).getRecordDate();
            int cals = foodsFromDB.get(i).getCalories();
            int foodID = foodsFromDB.get(i).getFoodID();
            //to log the event in the console for testing
            Log.v("Food id: ", String.valueOf(foodID));
            //expatiating myFood and setting the correct values the text view
            myFood = new Food();
            myFood.setFoodName(name);
            myFood.setRecordDate(dateText);
            myFood.setCalories(cals);
            myFood.setFoodID(foodID);
            //adds all to the myFood object
            dbFoods.add(myFood);
        }
        //closes the database
        dba.close();

        //adapter setup and expatiated it
        // and inhaling the custom row
        //take in the data for dbFoods
        foodAdapter = new CustomListViewAdapter(DisplayFoodsActiviy.this, R.layout.list_row, dbFoods);
        //sets the data in food adapter
        listView.setAdapter(foodAdapter);
        //recycle the adapter
        foodAdapter.notifyDataSetChanged();
    }
}
