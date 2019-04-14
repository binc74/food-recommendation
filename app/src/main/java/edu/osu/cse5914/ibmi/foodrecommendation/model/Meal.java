package edu.osu.cse5914.ibmi.foodrecommendation.model;

import com.google.firebase.firestore.ServerTimestamp;
import java.util.Date;

import java.util.ArrayList;

public class Meal extends BaseModel {
    private ArrayList<String> food;
    private float calorie;
    private float protein;

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    private float fat;

    public float getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(float cholesterol) {
        this.cholesterol = cholesterol;
    }

    private float cholesterol;

    public float getSodium() {
        return sodium;
    }

    public void setSodium(float sodium) {
        this.sodium = sodium;
    }

    private float sodium;
    @ServerTimestamp
    private Date time;

    public Meal() {
        food = new ArrayList<>();
    }

    public Meal(String f, float calorie,float fat, float cholesterol,float sodium, float protein) {
        food = new ArrayList<>();
        food.add(f);
        this.calorie = calorie;
        this.fat=fat;
        this.cholesterol=cholesterol;
        this.sodium=sodium;
        this.protein=protein;
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
