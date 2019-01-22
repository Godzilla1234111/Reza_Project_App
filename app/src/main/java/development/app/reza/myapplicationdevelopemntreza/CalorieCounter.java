package development.app.reza.myapplicationdevelopemntreza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import data.DatabaseHandler;
import model.Food;


public class CalorieCounter extends AppCompatActivity {
    //creating variables
    private EditText foodName, foodCals;
    private Button submitButton, viewbutton;
    private DatabaseHandler dba = new DatabaseHandler(CalorieCounter.this);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //telling it too look for the id in activity_calorie_counter
        setContentView(R.layout.activity_calorie_counter);
        //matching variables to the correct edit text and button
        foodName = (EditText) findViewById(R.id.foodEditText);
        foodCals = (EditText) findViewById(R.id.caloriesEditText);
        submitButton = (Button) findViewById(R.id.submitButton);
        viewbutton = (Button) findViewById(R.id.ViewFoodItems);
        //button click will save data to the database
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveToDB();
            }
        });
        //button click will open the custom list view
        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemView();
            }
        });
    }
    //method with an intent to DisplayFoodsActiviy
    public void openItemView() {

        Intent intent = new Intent(this, DisplayFoodsActiviy.class);
        startActivity(intent);
    }
    //method to dave data to database
    private void saveToDB() {
        //getting food to make a new food in list from model folder
        Food food = new Food();
        //getting values and converting them
        String name = foodName.getText().toString().trim();
        String calsString = foodCals.getText().toString().trim();
        //convening cls sting to an integer
        int cals = Integer.parseInt(calsString);
            //will set food and calories inputted
            food.setFoodName(name);
            food.setCalories(cals);
            //add food items to db then close db
            dba.addFood(food);
            dba.close();
            //clear the text to an empty sting
            foodName.setText("");
            foodCals.setText("");

            //TAKE USER TO NEXT SCREEN
            startActivity(new Intent(CalorieCounter.this, DisplayFoodsActiviy.class));
        }

    }

