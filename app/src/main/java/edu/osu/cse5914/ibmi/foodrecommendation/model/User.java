package edu.osu.cse5914.ibmi.foodrecommendation.model;

import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseModel {
    private String username;
    private String password;
    private ArrayList<String> allergies;
    private boolean needinit;
    private int gender;
    private int healthoption;
    private int dietoption;
    private float weight;
    private String birthday;

    public User() {
        birthday = "";
        gender = -1;
        dietoption = -1;
        healthoption = -1;
        weight = -1;
        allergies = new ArrayList<>();
        needinit = true;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;

       birthday = "";
        gender = 0;
        dietoption = 0;
        healthoption = 0;
        weight = -1;
        allergies = new ArrayList<>();
        needinit = true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }

    public void setNeedinit(boolean needinit) {
        this.needinit = needinit;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setDietoption(int dietoption) {
        this.dietoption = dietoption;
    }

    public void setHealthoption(int healthoption) {
        this.healthoption = healthoption;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean getNeedinit() { return needinit; }

    public int getGender() { return gender; }

    public int getDietoption() {
        return dietoption;
    }

    public int getHealthoption() {
        return healthoption;
    }

    public float getWeight() {
        return weight;
    }

    public String getBirthday() {
        return birthday;
    }

    @Override
    @Exclude
    public String getDocumentId() {
        return getUsername();
    }

    @Override
    public void setDocumentId(String documentId) {
        setUsername(documentId);
    }
}
