package edu.osu.cse5914.ibmi.foodrecommendation.adapters;
//referenced: https://github.com/mitchtabian/ListViews
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.Rating;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ImageView;


import java.util.ArrayList;

import com.bumptech.glide.Glide;

import edu.osu.cse5914.ibmi.foodrecommendation.R;
import edu.osu.cse5914.ibmi.foodrecommendation.data.Recepie;
import java.util.List;


public class RecepieListAdapter extends ArrayAdapter<Recepie> {

    private static final String TAG = "RecepieListAdapter";

    private Context mContext;
    private ArrayList<Recepie> local_recepies;


    public RecepieListAdapter(Context context, ArrayList<Recepie> objects) {
        super(context, 0, objects);
        mContext = context;
        local_recepies=objects;
    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.adapter_view_layout ,parent, false);

        if (local_recepies == null)
            return listItem;

        if (position < local_recepies.size()) {
            Recepie currRecepie=local_recepies.get(position);
            TextView name=listItem.findViewById(R.id.textView2);
           // TextView rating=listItem.findViewById(R.id.textView3);
            RatingBar rating=listItem.findViewById( R.id.ratingbar );

            name.setText(currRecepie.getName());
            //rating.setText(currRecepie.getRating());
            float rate = Float.parseFloat(currRecepie.getRating());
            rating.setRating( rate );



            String imgUrl=currRecepie.getImgUrl();
            ImageView image=listItem.findViewById(R.id.imageView0);
            Glide.with(mContext).load(imgUrl).into(image);

        }

        return listItem;


        }




    @Override
    public int getCount() {
        if (local_recepies == null)
            return 0;

        return local_recepies.size();
    }
}





