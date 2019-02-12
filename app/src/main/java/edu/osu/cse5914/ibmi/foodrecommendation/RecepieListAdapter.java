package edu.osu.cse5914.ibmi.foodrecommendation;
//referenced: https://github.com/mitchtabian/ListViews
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.osu.cse5914.ibmi.foodrecommendation.data.Recepie;


public class RecepieListAdapter extends ArrayAdapter<Recepie> {

    private static final String TAG = "RecepieListAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    private static class ViewHolder {
        TextView name;
        TextView rating;
        TextView prepTime;
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

        Recepie recepie = new Recepie(name,rating,prepTime);

        final View result;

        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView1);
            holder.rating = (TextView) convertView.findViewById(R.id.textView2);
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
        holder.rating.setText(recepie.getRating());
        holder.prepTime.setText(recepie.getPrepTime());


        return convertView;
    }
}





