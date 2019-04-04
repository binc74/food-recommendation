package edu.osu.cse5914.ibmi.foodrecommendation.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.osu.cse5914.ibmi.foodrecommendation.R;
import edu.osu.cse5914.ibmi.foodrecommendation.model.Meal;

public class HistoryListAdapter extends ArrayAdapter<Meal> {
    private static final String TAG = "HistoryListAdapter";

    private Context mContext;
    private List<Meal> mealList;

    public HistoryListAdapter(Context context, ArrayList<Meal> objects) {
        super(context, 0, objects);
        mContext = context;
        mealList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.meal_item ,parent, false);

        if (position < mealList.size()) {
            Meal currMeal = mealList.get(position);

            TextView date = listItem.findViewById(R.id.date);
            Date d = currMeal.getTime();
            String time = "";
            String hour = "" + d.getHours();
            String min = "" + d.getMinutes();
            if (hour.length() == 1) {
                hour = "0" + hour;
            }
            if (min.length() == 1) {
                min = "0" + min;
            }

            time += d.getMonth() + "/" + d.getDay() + "/" + (d.getYear() + 1900) + "  " + hour + ":" + min;

            date.setText("Date: " + time);
            date.setTextColor(Color.GREEN);

            TextView calorie = listItem.findViewById(R.id.calorie);
            calorie.setText("Total Calorie: " + Float.toString(currMeal.getCalorie()));
            calorie.setTextColor(Color.BLUE);

            // Add the food list
            ArrayList<String> foodList = currMeal.getFood();
            LinearLayout foodContainer = listItem.findViewById(R.id.foodList);

            if (foodContainer.getChildCount() == 0) {
                for (String food : foodList) {
                    TextView tv = new TextView(mContext);
                    tv.setText(food);
                    foodContainer.addView(tv);
                }
            }
        }

        return listItem;
    }

    @Override
    public int getCount() {
        return mealList.size();
    }
}
