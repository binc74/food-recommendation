package edu.osu.cse5914.ibmi.foodrecommendation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

import edu.osu.cse5914.ibmi.foodrecommendation.adapters.HistoryListAdapter;
import edu.osu.cse5914.ibmi.foodrecommendation.db.MealService;
import edu.osu.cse5914.ibmi.foodrecommendation.db.UserService;
import edu.osu.cse5914.ibmi.foodrecommendation.model.Meal;
import edu.osu.cse5914.ibmi.foodrecommendation.model.User;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "HistoryActivity";

    private String uid;
    private Button mBack;
    private ListView mHistList;

    private UserService userService;
    private MealService mealService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Bundle extra = getIntent().getExtras();
        uid = extra.getString("uid");
        mBack = findViewById(R.id.back);
        mBack.setOnClickListener(this);

        mHistList = findViewById(R.id.histList);
        userService = new UserService();
        mealService = new MealService();

        ArrayList<Meal> hist = new ArrayList<>();
        HistoryListAdapter histAdapter = new HistoryListAdapter(this, hist);
        mHistList.setAdapter(histAdapter);

        userService.processUserById(uid, task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot ds = task.getResult();
                User user = UserService.getUserFromDocument(ds);

                ArrayList<String> histStrArr = user.getHistory();

                for (String str : histStrArr) {
                    mealService.processMealById(str, mealTask -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot ds2 = mealTask.getResult();
                            Meal meal = MealService.getMealFromDocument(ds2);
                                Log.d(TAG, meal.getTime().toString());
                            histAdapter.add(meal);
                            histAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
