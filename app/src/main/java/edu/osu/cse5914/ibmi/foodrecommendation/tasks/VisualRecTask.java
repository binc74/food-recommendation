package edu.osu.cse5914.ibmi.foodrecommendation.tasks;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import edu.osu.cse5914.ibmi.foodrecommendation.data.ProjectApi;

public class VisualRecTask extends AsyncTask<String, Void, String> {
    private static final String TAG = "VisualRecTask";

    public TextView mText;
    public Button madd;
    public Button msuggest;
    protected VisualRecognition mVisualRecor;
    protected TextView mDiscoveryView;

    public VisualRecTask(Button madd,Button msuggest, TextView tv,TextView mDiscoveryView) {
        mText = tv;
        this.madd = madd;
        this.msuggest = msuggest;
        this.mDiscoveryView = mDiscoveryView;
        IamOptions options = new IamOptions.Builder()
                .apiKey(ProjectApi.VISUALREC_API)
                .build();

        mVisualRecor = new VisualRecognition("2018-03-19", options);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected String doInBackground(String... objects) {
        mText.setText("Recognizing...");
        mText.setEnabled(false);

        Log.d("VisualRecTask", "File Place: " + objects[0]);
        //final File photoFile = new File( (Uri.parse(objects[0])).getPath());
        final File photoFile = new File(objects[0]);
        //final File photoFile = new File("C:\\Users\\Ziming Ma\\Desktop\\Steak\\5.jpg");
        Log.d("VisualRecTask", "Current File: " + photoFile.length());

        InputStream imagesStream = null;
        try {
            imagesStream = new FileInputStream(photoFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Log.d("VisualRecTask", "Current File: " + imagesStream);
        ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                .imagesFile(imagesStream)
                .imagesFilename(photoFile.getName())
                //.url("http://e.hiphotos.baidu.com/nuomi/pic/item/b64543a98226cffc07155532b1014a90f603ea77.jpg")
                .classifierIds(Arrays.asList("DefaultCustomModel_220455181"))
                .threshold((float) 0.0)
                .build();

        ClassifiedImages result = mVisualRecor.classify(classifyOptions).execute();

        // Close image stream
        try {
            imagesStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove image from the phone
        if (photoFile.exists()) {
            if (photoFile.delete()) {
                Log.d(TAG, "file Deleted :" + photoFile);
            } else {
                Log.d(TAG, "file not Deleted :" + photoFile);
            }
        }

        String className = "";
        float maxScore = 0;

        for(int i = 0; i < result.getCustomClasses(); i++){
            if(maxScore < result.getImages().get(0).getClassifiers().get(0).getClasses().get(i).getScore()){
                maxScore = result.getImages().get(0).getClassifiers().get(0).getClasses().get(i).getScore();
                className = result.getImages().get(0).getClassifiers().get(0).getClasses().get(i).getClassName();
            }
        }

        Log.d("VisualRecTask", "Current File: " + result.toString());
        return className;
        //return "Apple";
    }

    @Override
    protected void onProgressUpdate(Void[] values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(String o) {
        super.onPostExecute(o);
        //new DiscoveryTask(o,mdiscovery,mDiscoveryView).execute();
        mText.setText(o);
        mText.setEnabled(true);
        madd.setEnabled(true);
        msuggest.setEnabled(true);
    }
}

