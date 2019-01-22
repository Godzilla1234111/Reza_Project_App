package model;

import java.io.Serializable;

public class Food implements Serializable {
    //used to keep food items with all of its states and behaviors frozen
    //to send as an extra between intents
    private static final long serialVersionUID = 10L;
    //items that will be held in database
    private String foodName;
    private int calories;
    private int foodID;
    private String recordDate;
    //Constructor
    public Food( String food, int cals, int id, String date ){
        foodName = food;
        calories= cals;
        foodID = id;
        recordDate = date;
    }
    //when food object is instantiated and items are not needed
    public Food (){

    }
    //getters and setters
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }
}

