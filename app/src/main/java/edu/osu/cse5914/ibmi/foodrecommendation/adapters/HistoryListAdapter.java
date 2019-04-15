package edu.osu.cse5914.ibmi.foodrecommendation.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
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
            date.setTypeface(null, Typeface.BOLD);
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

            time += (d.getMonth() + 1) + "/" + d.getDate() + "/" + (d.getYear() + 1900) + "  " + hour + ":" + min;

            String timeStr = "Date: " + time;
            date.setText(timeStr);
            date.setTextColor(Color.RED);

            TextView calorie = listItem.findViewById(R.id.calorie);
            TextView cholesterol=listItem.findViewById(R.id.cholesterol);
            TextView fat=listItem.findViewById(R.id.fat);
            TextView protein=listItem.findViewById(R.id.protein);
            TextView sodium=listItem.findViewById(R.id.sodium);

            String calStr = String.format("Total Calorie: %.2f", currMeal.getCalorie());
            String choStr = String.format("Total Cholesterol: %.2f mg ", currMeal.getCholesterol());
            String fatStr = String.format("Total Fat: %.2f g", currMeal.getFat());
            String proStr = String.format("Total Protein: %.2f g", currMeal.getProtein() );
            String sodStr = String.format("Total Sodium: %.2f mg", currMeal.getSodium());



            calorie.setText(calStr);
            calorie.setTextColor(Color.BLUE);


            cholesterol.setText(choStr);
            cholesterol.setTextColor(Color.BLUE);

            fat.setText(fatStr);
            fat.setTextColor(Color.BLUE);

            protein.setText(proStr);
            protein.setTextColor(Color.BLUE);

            sodium.setText(sodStr);
            sodium.setTextColor(Color.BLUE);



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
