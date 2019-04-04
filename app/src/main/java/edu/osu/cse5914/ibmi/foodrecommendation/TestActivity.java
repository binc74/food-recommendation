package edu.osu.cse5914.ibmi.foodrecommendation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import edu.osu.cse5914.ibmi.foodrecommendation.db.MealService;
import edu.osu.cse5914.ibmi.foodrecommendation.db.UserService;
import edu.osu.cse5914.ibmi.foodrecommendation.model.Meal;
import edu.osu.cse5914.ibmi.foodrecommendation.tasks.NutrionixTask;
import edu.osu.cse5914.ibmi.foodrecommendation.tasks.VisualRecTask;
import edu.osu.cse5914.ibmi.foodrecommendation.util.EditTextUtil;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    private static String TAG = "TestActivity";
    private ImageView mImage;
    private Button mReport;
    //private Button mDiscoveryButton;
    private Button mAddFoodButton;
    private EditText mTextView;
    private TextView mDiscoveryView;
    private TextView mPrevFoodView;
    // private VisualRecTask visualRecTask;
    private String filePath;
    private String uid;

    private ArrayList<String> prevFood;

    //private VisualRecognition VisualRecor;
    //private VisualRecTask visualRecTaskTask;

    private MealService mealService;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Bundle extra = getIntent().getExtras();
        filePath = extra.getString("imagePath");
        uid = extra.getString("uid");
        prevFood = extra.getStringArrayList("prevFood");

        mealService = new MealService();
        userService = new UserService();
        userService.getDocumentReference(uid);

        Log.d(TAG, "get path1: " + filePath);

        mPrevFoodView = findViewById(R.id.prev_food);
        mAddFoodButton = findViewById(R.id.add_food);
        mAddFoodButton.setOnClickListener(this);
        mDiscoveryView = findViewById(R.id.textView8);
        mDiscoveryView.setVisibility(View.GONE);
        mImage = findViewById(R.id.image);
        mTextView = findViewById( R.id.tv_visual_rec );
        //mDiscoveryButton = findViewById(R.id.DiscoveryButton);
        //mDiscoveryButton.setOnClickListener(this);
        //mDiscoveryButton.setEnabled(false);
        mReport = findViewById(R.id.report);
        mReport.setOnClickListener(this);
        Bitmap image = BitmapFactory.decodeFile(filePath);
        Log.d(TAG, "file size : " + image.getByteCount());
        mImage.setImageBitmap(image);

        mAddFoodButton.setEnabled(false);
        mReport.setEnabled(false);

        // Compress bitmap
        //Bitmap compressedImage = Bitmap.createScaledBitmap(image, 300, 300,true);
        //Log.d(TAG, "" + image.getByteCount() + " : " + compressedImage.getByteCount());
        updatePrevFoodView();

        new VisualRecTask(mAddFoodButton, mReport, mTextView, mDiscoveryView).execute(filePath);
    }

    private void updatePrevFoodView() {
        String str = "Previous added food:\n";
        if (prevFood.size() != 0) {
            str += prevFood.get(0);
            for (int i = 1; i < prevFood.size(); ++i) {
                str += ", " + prevFood.get(i);
            }
        }
        else {
            str += "None";
        }

        mPrevFoodView.setText(str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.report:
                String fc = EditTextUtil.getString(mTextView);

                String cal="0",minCalAllowed,maxCalAllowed;

                float flt_cal=Float.parseFloat(cal);
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
                ArrayList<String> min_max_cal=new ArrayList<String>();
                min_max_cal.add(minCalAllowed);
                min_max_cal.add(maxCalAllowed);

                Meal m = new Meal();
                for (String str: prevFood) {
                    m.getFood().add(str);
                }
                m.getFood().add(fc);
                mealService.addNewMeal(m, task -> {
                    String mid = task.getId();

                    mealService.getDocumentReference(mid);
                    mealService.setDocumentId(mid);
                    m.setDocumentId(mid);
                    userService.updateHistory(mid);

                    new NutrionixTask(uid, m,this).execute();
                });


//                pref_intent.putExtra("food_category", fc);
//                pref_intent.putStringArrayListExtra("min_max", min_max_cal);

                break;

            case R.id.add_food:
                prevFood.add(EditTextUtil.getString(mTextView));

                Intent intent = new Intent();
                intent.putExtra("prevFood", prevFood);
                setResult(RESULT_OK, intent);
                finish();
                break;
/*
            case R.id.DiscoveryButton:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mDiscoveryView.getText().toString()));
                startActivity(i);
                break;*/
        }

    }
}
