package edu.osu.cse5914.ibmi.foodrecommendation.tasks;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import edu.osu.cse5914.ibmi.foodrecommendation.data.ProjectApi;


public class RetrievingCalTask extends AsyncTask<String, Void, String> {
    protected TextView mText;
    protected Discovery mDiscovery;
    protected String environmentId;
    protected String collectionId;
    protected String documentJson;

    public RetrievingCalTask(TextView tv) {
        mText = tv;

        mDiscovery = new Discovery("2018-12-03");
        mDiscovery.setEndPoint("https://gateway.watsonplatform.net/discovery/api");

        IamOptions options = new IamOptions.Builder()
                .apiKey(ProjectApi.DISCOVERY_API2)
                .build();
        mDiscovery.setIamCredentials(options);
        environmentId = "d73e9ac3-e284-4c40-b672-bb0a228a3e81";
        collectionId = "27e5c66c-09cd-4cd8-b221-cabbb18e5bf7";
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected String doInBackground(String... objects) {
        InputStream documentStream = new ByteArrayInputStream(documentJson.getBytes());

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
