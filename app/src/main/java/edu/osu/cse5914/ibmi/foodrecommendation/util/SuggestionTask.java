package edu.osu.cse5914.ibmi.foodrecommendation.util;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//referenced website: https://www.codexpedia.com/android/asynctask-and-httpurlconnection-sample-in-android/
public class SuggestionTask extends AsyncTask<String, Void, String> {
    protected TextView mText;

    public SuggestionTask(TextView tv){
        mText = tv;


    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... objects) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String recipieJsonStr = null;
        Log.d("10", "This is my message");
       try {
           URL url = new URL("http://api.yummly.com/v1/api/recipe/Avocado-cream-pasta-sauce-recipe-306039?_app_id=1818c65c&_app_key=a20fe4e4dbb576f4e146ad953f72bacc");
           urlConnection = (HttpURLConnection) url.openConnection();
           urlConnection.setRequestMethod("GET");
           urlConnection.connect();

           InputStream inputStream = urlConnection.getInputStream();
           StringBuffer buffer = new StringBuffer();
           if (inputStream == null) {
               return null;
           }
           reader = new BufferedReader(new InputStreamReader(inputStream));

           String line;
           while ((line = reader.readLine()) != null) {
               buffer.append(line + "\n");
           }

           if (buffer.length() == 0) {
               return null;
           }

           recipieJsonStr = buffer.toString();
           return recipieJsonStr;
       }
       catch (IOException e){

           Log.e("PlaceholderFragment", "Error ", e);
           return null;

       }

       finally {
           if (urlConnection != null) {
               urlConnection.disconnect();
           }
           if (reader != null) {
               try {
                   reader.close();
               } catch (final IOException e) {
                   Log.e("PlaceholderFragment", "Error closing stream", e);
               }
           }
       }

    }

    @Override
    protected void onProgressUpdate(Void[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String o) {
        super.onPostExecute(o);
        mText.setText(o);
        Log.i("json", o);
    }




}
