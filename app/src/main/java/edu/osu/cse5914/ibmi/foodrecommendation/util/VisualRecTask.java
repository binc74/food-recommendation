package edu.osu.cse5914.ibmi.foodrecommendation.util;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import edu.osu.cse5914.ibmi.foodrecommendation.CameraActivity;

public class VisualRecTask extends AsyncTask<String, Void, String> {
    protected TextView mText;
    protected VisualRecognition mVisualRecor;

    public VisualRecTask(TextView tv) {
        mText = tv;

        IamOptions options = new IamOptions.Builder()
                .apiKey("gqPKKIDPqLEkA0qprEYRT8-spN3QdFTH5tE8SFtvQz_V")
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

        Log.d("VisualRecTask", "File Place: " + objects[0]);
        //final File photoFile = new File( (Uri.parse(objects[0])).getPath());
        final File photoFile = new File(objects[0]);
        //final File photoFile = new File("C:\\Users\\Ziming Ma\\Desktop\\Steak\\5.jpg");
        Log.d("VisualRecTask", "Current File: " + photoFile);

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
                .classifierIds( Collections.singletonList("DefaultCustomModel_1496929061"))
                .threshold((float) 0.6)
                .owners( Arrays.asList("me"))
                .build();
        ClassifiedImages result = mVisualRecor.classify(classifyOptions).execute();


        Log.d("VisualRecTask", "Current File: " + result.toString());
        return result.getImages().get(0).getClassifiers().get(0).getClasses().get(0).getClassName();
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

