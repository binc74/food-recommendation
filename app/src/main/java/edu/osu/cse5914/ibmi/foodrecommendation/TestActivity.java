package edu.osu.cse5914.ibmi.foodrecommendation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

public class TestActivity extends AppCompatActivity {
    private static String TAG = "TestActivity";

    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String filePath = getIntent().getStringExtra("imagePath");
        Log.d(TAG, "get path: " + filePath);


        mImage = findViewById(R.id.image);

        Bitmap image = BitmapFactory.decodeFile(filePath);

        new File(filePath).delete();

        Log.d(TAG, "file size : " + image.getByteCount());
        mImage.setImageBitmap(image);
    }
}
