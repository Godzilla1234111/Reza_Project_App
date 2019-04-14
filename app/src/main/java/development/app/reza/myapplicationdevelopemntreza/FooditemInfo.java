package development.app.reza.myapplicationdevelopemntreza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Food;

public class FooditemInfo extends AppCompatActivity {

    private TextView foodName, calories, dateTaken;
    private int foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting the view to activity_fooditem_info
        setContentView(R.layout.activity_fooditem_info);
        //defining the text view with the correct id and variables
        foodName = (TextView) findViewById(R.id.detsFoodName);
        calories = (TextView) findViewById(R.id.detsCaloriesValue);
        dateTaken = (TextView) findViewById(R.id.detsDateText);

        //HAS FROZEN STATE OF FOOD OBJECT
        Food food = (Food) getIntent().getSerializableExtra("userOBJ");

        //GETTING THE ITEMS FROM OBJECT
        foodName.setText(food.getFoodName());
        calories.setText(String.valueOf(food.getCalories()));
        dateTaken.setText(food.getRecordDate());

        foodId = food.getFoodID();
    }
    //Method for deleting items in custom list view
    public void buttonOnClick(View v){

        DatabaseHandler dba = new DatabaseHandler(getApplicationContext());

        dba.deleteFood(foodId);
        //message th tell the user that hte item has been deleted
        Toast.makeText(this, "Food Item Deleted", Toast.LENGTH_SHORT).show();
        // brings hte user back th the calorie counter view
        startActivity(new Intent(FooditemInfo.this, CalorieCounter.class));
        FooditemInfo.this.finish();
        finish();
    }
}
