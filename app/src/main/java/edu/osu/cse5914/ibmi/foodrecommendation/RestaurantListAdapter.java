package edu.osu.cse5914.ibmi.foodrecommendation;
//referenced: https://github.com/mitchtabian/ListViews
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;


import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.yelp.fusion.client.models.Coordinates;

import edu.osu.cse5914.ibmi.foodrecommendation.data.Recepie;
import edu.osu.cse5914.ibmi.foodrecommendation.data.Restaurant;


public class RestaurantListAdapter extends ArrayAdapter<Restaurant> {

    private static final String TAG = "RestaurantListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    private static class ViewHolder {
        ImageView mImageView;
        TextView name;
        TextView rating;

//                    holder. mImageView = (ImageView) convertView.findViewById(R.id.imageView0);
//            holder.name = (TextView) convertView.findViewById(R.id.textView2);
//            holder.prepTime = (TextView) convertView.findViewById(R.id.textView3);

    }


    public RestaurantListAdapter(Context context, int resource, ArrayList<Restaurant> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getName();
        String rating = getItem(position).getRating();
        String displayPhone = getItem(position).getDisplayPhone();
        String imgUrl = getItem(position).getImgUrl();
        Coordinates cd= getItem(position).getCoord();

        Restaurant restaurant = new Restaurant(name,rating,displayPhone,imgUrl,cd);

        final View result;

        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder. mImageView = (ImageView) convertView.findViewById(R.id.imageView0);
            holder.name = (TextView) convertView.findViewById(R.id.textView2);
            holder.rating = (TextView) convertView.findViewById(R.id.textView3);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }



        lastPosition = position;
        holder.name.setText(restaurant.getName());
        holder.rating.setText("Rating: "+restaurant.getRating());
/*
         BitmapFactory.decodeStream(url.openConnection().getInputStream());
*/
//        Picasso.with(mContext).load(imgUrl).into(holder.mImageView);
//        ImageView image=BitmapFactory.decodeStream(imgUrl.openConnection().getInputStream()
//        holder.mImageView.setImageBitmap());


        Glide.with(mContext).load(imgUrl).into(holder.mImageView);
        return convertView;
    }
}





