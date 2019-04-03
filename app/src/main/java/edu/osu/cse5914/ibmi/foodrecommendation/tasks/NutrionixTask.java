package edu.osu.cse5914.ibmi.foodrecommendation.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

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

import edu.osu.cse5914.ibmi.foodrecommendation.AsyncResponse;
import edu.osu.cse5914.ibmi.foodrecommendation.R;
import edu.osu.cse5914.ibmi.foodrecommendation.RestaurantListAdapter;
import edu.osu.cse5914.ibmi.foodrecommendation.data.ProjectApi;
import edu.osu.cse5914.ibmi.foodrecommendation.data.Recepie;
import edu.osu.cse5914.ibmi.foodrecommendation.data.Restaurant;
import retrofit2.Call;
import retrofit2.Response;

public class NutrionixTask  extends AsyncTask<String, Void,String> {

    private String foodType;
    protected ListView mList;
    private final Context mContext;
    public AsyncResponse delegate = null;

    public NutrionixTask(String foodType,Context ct,ListView lv){
        this.foodType=foodType;
        mContext = ct;
        mList = lv;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    protected String doInBackground(String...objects) {


        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String recipieJsonStr = null;
        String single ="";
        String rcpParsed = "";
        String cal="0";

        try {

            //remember to only search those with images
//            URL url = new URL("http://api.myjson.com/bins/xu8g0");
            URL url = new URL("http://api.nutritionix.com/v1_1/search/"+foodType+"?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat&appId=4a38b710&appKey=ff6b72c536d2ed14864e1688a34d9e45");
//            URL url = new URL("http://api.nutritionix.com/v1_1/search/steak?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat&appId=4a38b710&appKey=ff6b72c536d2ed14864e1688a34d9e45");

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            recipieJsonStr = buffer.toString();
            JSONObject jsreader = new JSONObject(recipieJsonStr);
            JSONArray rcpArr = jsreader.getJSONArray("hits");


            JSONObject JO = (JSONObject) rcpArr.get(0);
            JSONObject JO1=JO.getJSONObject("fields");
            cal=JO1.get("nf_calories").toString();
            String a="1";



        }
        catch (IOException e){
            e.printStackTrace();

        }

        catch (JSONException e) {
            e.printStackTrace();
        }
        return cal;







    }
    @Override
    protected void onPostExecute(String o) {
//        delegate.processFinish(o);
        String minCalAllowed;
        String maxCalAllowed;
        float flt_cal=Float.parseFloat(o);
        int a=2;
        if (flt_cal>800) {
            minCalAllowed = "0.5";
            maxCalAllowed = "1.0";
        }
        else if(200<flt_cal&&flt_cal<800){
            minCalAllowed = "1.0";
            maxCalAllowed = "4.0";
        }
        else{
            minCalAllowed="4";
            maxCalAllowed = "8";}
        new SuggestionTask(mList, mContext,maxCalAllowed,minCalAllowed).execute();
    }

}
