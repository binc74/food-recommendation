package edu.osu.cse5914.ibmi.foodrecommendation.model;

import com.google.firebase.firestore.ServerTimestamp;
import java.util.Date;

import java.util.ArrayList;

public class Meal extends BaseModel {
    private ArrayList<String> food;
    private float calorie;
    private float protein;

    @ServerTimestamp
    private Date time;

    public Meal() {
        food = new ArrayList<>();
    }

    public Meal(String f, float calorie) {
        food = new ArrayList<>();
        food.add(f);
        this.calorie = calorie;
    }

    public Meal(String f) {
        food = new ArrayList<>();
        food.add(f);
    }

    public ArrayList<String> getFood() {
        return food;
    }

    public void setFood(ArrayList<String> food) {
        this.food = food;
    }

    public float getCalorie() {
        return calorie;
    }

    public void setCalorie(float calorie) {
        this.calorie = calorie;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
