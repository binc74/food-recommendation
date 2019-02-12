package edu.osu.cse5914.ibmi.foodrecommendation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class TestActivity extends AppCompatActivity {

    private ImageView mImage;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        mImage = findViewById(R.id.image);

        mImage.setImageBitmap(bitmap);
    }
}
