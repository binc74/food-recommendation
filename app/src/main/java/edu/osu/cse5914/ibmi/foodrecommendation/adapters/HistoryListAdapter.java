package edu.osu.cse5914.ibmi.foodrecommendation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.osu.cse5914.ibmi.foodrecommendation.R;
import edu.osu.cse5914.ibmi.foodrecommendation.model.Meal;

public class HistoryListAdapter extends ArrayAdapter<Meal> {
    private static final String TAG = "HistoryListAdapter";

    private Context mContext;
    private List<Meal> mealList = new ArrayList<>();

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
            date.setText(currMeal.getTime().toString());

            TextView calorie = listItem.findViewById(R.id.calorie);
            calorie.setText(Float.toString(currMeal.getCalorie()));
            Log.d(TAG, "Here");
        }

        return listItem;
    }

    @Override
    public int getCount() {
        return mealList.size();
    }
}
