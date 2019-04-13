package data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import development.app.reza.myapplicationdevelopemntreza.FooditemInfo;
import development.app.reza.myapplicationdevelopemntreza.R;
import model.Food;
//CustomListViewAdapter will be taking Food Objects
public class CustomListViewAdapter extends ArrayAdapter<Food> {

    private int layoutRes;
    private Activity activity;
    //to hold food items
    private ArrayList<Food> foodList = new ArrayList<>();
    //Constructor for custom list view
    public CustomListViewAdapter(Activity act, int resource, ArrayList<Food> data) {
        super(act, resource, data);
        layoutRes = resource;
        activity = act;
        foodList = data;
        notifyDataSetChanged();
    }
    //getters in custom list view
    @Override
    public int getCount() {

        return foodList.size();
    }

    @Override
    public Food getItem(int position) {

        return foodList.get(position);
    }

    @Override
    public int getPosition(Food item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {

        return super.getItemId(position);
    }
    //inflating the view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //transferring converted view in the row
        View row = convertView;
        ViewHolder holder = null;
        //Creating a row if row is empty or tag is empty
        if (row == null || (row.getTag() == null)){
            //expatiating the object inflater from activity
            LayoutInflater inflater = LayoutInflater.from(activity);
            //inflating te layout resource witch has been passed
            row = inflater.inflate(layoutRes, null);
            //expatiating the holder class
            holder = new ViewHolder();
            //row is holding the list row with the appropriate id
            //and expatiating hte text views in view-holder
            holder.foodName = (TextView) row.findViewById(R.id.name);
            holder.foodDate = (TextView) row.findViewById(R.id.dateText);
            holder.foodCalories =(TextView) row.findViewById(R.id.calories);

            row.setTag(holder);

        }else{

            holder = (ViewHolder) row.getTag();
        }
        //accessing the current food object
        holder.food = getItem(position);
        //setting data to the holder
        holder.foodName.setText(holder.food.getFoodName());
        holder.foodDate.setText(holder.food.getRecordDate());
        holder.foodCalories.setText(String.valueOf(holder.food.getCalories()));

        final ViewHolder finalHolder = holder;
        //When row is pressed go to another screen
        row.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //creating an intel to reach FooditemInfo
                Intent i = new Intent(activity, FooditemInfo.class);
                //creating a new bundle
                Bundle mBundle = new Bundle();
                //holding the serializable stat of object in food using the holder
                //by freezing all of its content
                mBundle.putSerializable("userOBJ", finalHolder.food);
                i.putExtras(mBundle);

                activity.startActivity(i);

            }

        });
        return row;
    }
    //holds the items that are in list view
    public class ViewHolder {
        Food food;
        TextView foodName;
        TextView foodCalories;
        TextView foodDate;
    }
}
