package edu.osu.cse5914.ibmi.foodrecommendation.model;

import com.google.firebase.firestore.ServerTimestamp;
import com.google.type.Date;

import java.util.ArrayList;

public class Meal extends BaseModel {
    private ArrayList<String> food;
    private int calorie;

    @ServerTimestamp
    Date time;

    public ArrayList<String> getFood() {
        return food;
    }

    public void setFood(ArrayList<String> food) {
        this.food = food;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
}
