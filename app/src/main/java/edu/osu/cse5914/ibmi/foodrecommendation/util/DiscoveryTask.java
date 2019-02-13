package edu.osu.cse5914.ibmi.foodrecommendation.util;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.discovery.v1.model.*;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

public class DiscoveryTask extends AsyncTask<String, Void, String> {
    protected TextView mText;
    protected Discovery mDiscovery;
    protected String environmentId;
    protected String collectionId;

    public DiscoveryTask(TextView tv) {
        mText = tv;
        Log.d("DiscoveryTask", "Current File: " + mText.getText().toString());
        IamOptions options = new IamOptions.Builder()
                .apiKey("1YJgxYDcHqAH75k-Q3Z1_LKMegZa30d4gKAGEr_hiKKA")
                .build();
        mDiscovery = new Discovery("2018-12-03",options);
        mDiscovery.setEndPoint("https://gateway.watsonplatform.net/discovery/api");


        mDiscovery.setIamCredentials(options);
        environmentId = "system";
        collectionId = "news-en";

        ListCollectionsOptions listOptions = new ListCollectionsOptions.Builder(environmentId).build();
        ListCollectionsResponse listResponse = mDiscovery.listCollections(listOptions).execute();
        Log.d("DiscoveryTask", "Current File: " + mText.getText().toString());

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected String doInBackground(String... objects) {
        Log.d("DiscoveryTask", "1" + objects[0]);
        return null;
    }
    @Override
    protected void onProgressUpdate(Void[] values) {

        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String o) {
        super.onPostExecute(o);
        mText.setText(o);
    }

}
