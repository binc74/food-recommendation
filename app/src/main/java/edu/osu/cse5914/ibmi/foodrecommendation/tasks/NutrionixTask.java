package edu.osu.cse5914.ibmi.foodrecommendation.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;


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
import edu.osu.cse5914.ibmi.foodrecommendation.MainActivity;
import edu.osu.cse5914.ibmi.foodrecommendation.R;
import edu.osu.cse5914.ibmi.foodrecommendation.adapters.RestaurantListAdapter;
import edu.osu.cse5914.ibmi.foodrecommendation.TestActivity;
import edu.osu.cse5914.ibmi.foodrecommendation.data.ProjectApi;
import edu.osu.cse5914.ibmi.foodrecommendation.data.Recepie;
import edu.osu.cse5914.ibmi.foodrecommendation.data.Restaurant;

import edu.osu.cse5914.ibmi.foodrecommendation.db.MealService;
import edu.osu.cse5914.ibmi.foodrecommendation.model.Meal;
import retrofit2.Call;
import retrofit2.Response;


public class NutrionixTask  extends AsyncTask<String, Void,ArrayList> {
    private static final String TAG = "NutrionixTask";

    private Meal meal;
    private String uid;


    private Context ct;

    public NutrionixTask(String uid, Meal m, Context ctxt){
        ct=ctxt;
        this.uid = uid;
        meal=m;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    protected ArrayList<String> doInBackground(String...objects) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String recipieJsonStr = null;
        String single ="";
        String rcpParsed = "";

        String cal="0";
        String fat="0";
        String cholesterol="0";
        String sodium="0";
        String protein="0";

        int nMeals= meal.getFood().size();
        float all_cal=0;
        float all_fat=0;
        float all_cholesterol=0;
        float all_sodium=0;
        float all_protein=0;

        for (int i=0;i<nMeals;i++) {
            try {


                //remember to only search those with images
//            URL url = new URL("http://api.myjson.com/bins/xu8g0");
//                URL url = new URL("http://api.nutritionix.com/v1_1/search/" + meal.getFood().get(i) + "?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat&appId=4a38b710&appKey=ff6b72c536d2ed14864e1688a34d9e45");
                URL url = new URL("http://api.nutritionix.com/v1_1/search/" + meal.getFood().get(i) + "?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_vitaminc%2Cnf_total_carbohydrates%2Cnf_protein%2Cnf_total_fat%2Cnf_cholesterol%2Cnf_sodium&appId=4a38b710&appKey=ff6b72c536d2ed14864e1688a34d9e45");
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
                JSONObject JO1 = JO.getJSONObject("fields");
                cal = JO1.get("nf_calories").toString();
                fat = JO1.get("nf_total_fat").toString();
                cholesterol=JO1.get("nf_cholesterol").toString();
                sodium=JO1.get("nf_sodium").toString();
                protein=JO1.get("nf_protein").toString();
                String a = "1";


            } catch (IOException e) {
                e.printStackTrace();

            } catch (JSONException e) {
                e.printStackTrace();
            }
            float clf=Float.parseFloat(cal);
            float clf_fat=Float.parseFloat(fat);
            float clf_cholesterol=Float.parseFloat(cholesterol);
            float clf_sodium=Float.parseFloat(sodium);
            float clf_protein=Float.parseFloat(protein);


            all_cal+=clf;
            all_fat+=clf_fat;
            all_cholesterol+=clf_cholesterol;
            all_sodium+=clf_sodium;
            all_protein+=clf_protein;
        }

        String strval=String.valueOf(all_cal);
        String str_fat=String.valueOf(all_fat);
        String str_cholesterol=String.valueOf(all_cholesterol);
        String str_sodium=String.valueOf(all_sodium);
        String str_protein=String.valueOf(all_protein);

        ArrayList<String> all_nutri=new ArrayList<>();
        all_nutri.add(strval);
        all_nutri.add(str_fat);
        all_nutri.add(str_cholesterol);
        all_nutri.add(str_sodium);
        all_nutri.add(str_protein);
        return all_nutri;

    }

    protected void onPostExecute(ArrayList<String> o) {
        super.onPostExecute(o);

        ArrayList<String> min_max_cal=new ArrayList<String>();
        float cal=Float.parseFloat(o.get(0));
        float fat=Float.parseFloat(o.get(1));
        float cholesterol=Float.parseFloat(o.get(2));
        float sodium=Float.parseFloat(o.get(3));
        float protein=Float.parseFloat(o.get(4));



        String minCalAllowed,maxCalAllowed;
        meal.setCalorie(cal);
        meal.setFat(fat);
        meal.setCholesterol(cholesterol);
        meal.setSodium(sodium);
        meal.setProtein(protein);
        MealService mealService = new MealService();
        Log.d(TAG, "ID: " + meal.getDocumentId());
        mealService.getDocumentReference(meal.getDocumentId());
        mealService.updateCalorie(cal);
        mealService.updateFat(fat);
        mealService.updateCholesterol(cholesterol);
        mealService.updateSodium(sodium);
        mealService.updateProtein(protein);

        if (cal>800) {
            minCalAllowed = "0.5";
            maxCalAllowed = "1.0";
        }
        else if(200<cal&&cal<800){
            minCalAllowed = "1.0";
            maxCalAllowed = "4.0";
        }
        else{
            minCalAllowed="4";
            maxCalAllowed = "8";}
        min_max_cal.add(minCalAllowed);
        min_max_cal.add(maxCalAllowed);

        Intent pref_intent = new Intent(ct, MainActivity.class); //link to preference view
        pref_intent.putStringArrayListExtra("min_max",min_max_cal);
        pref_intent.putExtra("uid", uid);
        ct.startActivity(pref_intent);
    }

}