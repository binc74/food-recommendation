package edu.osu.cse5914.ibmi.foodrecommendation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;

import com.yelp.fusion.client.connection.YelpFusionApi;
import com.yelp.fusion.client.connection.YelpFusionApiFactory;
import com.yelp.fusion.client.models.Business;
import com.yelp.fusion.client.models.SearchResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.osu.cse5914.ibmi.foodrecommendation.R;
import edu.osu.cse5914.ibmi.foodrecommendation.RecepieListAdapter;
import edu.osu.cse5914.ibmi.foodrecommendation.RestaurantListAdapter;
import edu.osu.cse5914.ibmi.foodrecommendation.data.ProjectApi;
import edu.osu.cse5914.ibmi.foodrecommendation.data.Recepie;
import edu.osu.cse5914.ibmi.foodrecommendation.data.Restaurant;
import retrofit2.Call;
import retrofit2.Response;

public class SuggestRestaurantTask extends AsyncTask<ArrayList, Void, ArrayList> {
    protected TextView mText;
    protected ListView mList;
    private final Context mContext;
    private String foodType;


    public SuggestRestaurantTask(ListView lv,Context ct,String foodType ){
        mList = lv;
        mContext = ct;
        foodType=foodType;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    protected ArrayList<Restaurant> doInBackground(ArrayList... objects) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String recipieJsonStr = null;
        String single ="";
        String rcpParsed = "";
        ArrayList<Restaurant> restaurantList = new ArrayList<>();

        try {
            YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
            YelpFusionApi yelpFusionApi = apiFactory.createAPI(ProjectApi.YELP_API);

            Map<String, String> params = new HashMap<>();

// general params
            params.put("term", "indian food");
            params.put("latitude", "40.581140");
            params.put("longitude", "-111.914184");

            Call<SearchResponse> call = yelpFusionApi.getBusinessSearch(params);
            Response<SearchResponse> response = call.execute();
            ArrayList<Business> business=response.body().getBusinesses();
            String a="s";
            for (int x=0; x<business.size(); x++){
                Business b=business.get(x);
                String name=b.getName();
                String rating=Double.toString(b.getRating());
                String displayedPhone=b.getDisplayPhone();
                String imgUrl=b.getImageUrl();
                restaurantList.add(new Restaurant(name,rating, imgUrl,displayedPhone));//use rating=1 and prepTime=2 for now and change later

            }



        }
        catch (IOException e){
            e.printStackTrace();

        }


        return restaurantList;

    }

    @Override
    protected void onPostExecute(ArrayList o) {
        super.onPostExecute(o);

        RestaurantListAdapter adapter = new RestaurantListAdapter(mContext, R.layout.adapter_view_layout, o);
        mList.setAdapter(adapter);


    }



}
