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

import edu.osu.cse5914.ibmi.foodrecommendation.data.Recepie;


public class RecepieListAdapter extends ArrayAdapter<Recepie> {

    private static final String TAG = "RecepieListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    private static class ViewHolder {
        ImageView mImageView;
        TextView name;
        TextView prepTime;

//                    holder. mImageView = (ImageView) convertView.findViewById(R.id.imageView0);
//            holder.name = (TextView) convertView.findViewById(R.id.textView2);
//            holder.prepTime = (TextView) convertView.findViewById(R.id.textView3);

    }


    public RecepieListAdapter(Context context, int resource, ArrayList<Recepie> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getName();
        String rating = getItem(position).getRating();
        String prepTime = getItem(position).getPrepTime();
        String imgUrl = getItem(position).getImgUrl();


        Recepie recepie = new Recepie(name,rating,prepTime,imgUrl);

        final View result;

        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder. mImageView = (ImageView) convertView.findViewById(R.id.imageView0);
            holder.name = (TextView) convertView.findViewById(R.id.textView2);
            holder.prepTime = (TextView) convertView.findViewById(R.id.textView3);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }



        lastPosition = position;
        holder.name.setText(recepie.getName());
        holder.prepTime.setText("Preparation time: "+recepie.getPrepTime()+" minutes");
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





