package edu.osu.cse5914.ibmi.foodrecommendation.adapters;
//referenced: https://github.com/mitchtabian/ListViews
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;


import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.yelp.fusion.client.models.Coordinates;

import edu.osu.cse5914.ibmi.foodrecommendation.R;
import edu.osu.cse5914.ibmi.foodrecommendation.data.Recepie;
import edu.osu.cse5914.ibmi.foodrecommendation.data.Restaurant;


public class RestaurantListAdapter extends ArrayAdapter<Restaurant> {

    private static final String TAG = "RestaurantListAdapter";

    private Context mContext;
    private int mResource;
    private ArrayList<Restaurant> restaurants;

    public RestaurantListAdapter(Context context, ArrayList<Restaurant> objects) {
        super(context, 0, objects);
        mContext = context;
        restaurants=objects;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.adapter_view_layout ,parent, false);


        if (position < restaurants.size()) {
            Restaurant currRestaurant=restaurants.get(position);
            TextView name=listItem.findViewById(R.id.textView2);
            TextView rating=listItem.findViewById(R.id.textView3);

            name.setText(currRestaurant.getName());
            rating.setText(currRestaurant.getRating());


            String imgUrl=currRestaurant.getImgUrl();
            ImageView image=listItem.findViewById(R.id.imageView0);
            Glide.with(mContext).load(imgUrl).into(image);

        }

        return listItem;

    }

    @Override
    public int getCount() {
        return restaurants.size();
    }

}





