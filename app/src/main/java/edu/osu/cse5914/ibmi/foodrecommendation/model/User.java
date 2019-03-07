package edu.osu.cse5914.ibmi.foodrecommendation.model;

import com.google.firebase.firestore.Exclude;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseModel {
    private String username;
    private String password;
    private int pref;
    private List<String> allergies;
    private boolean needinit;
    private int gender;
    private int healthoption;
    private int dietoption;

    public User() {
        gender = -1;
        pref = -1;
        dietoption = -1;
        healthoption = -1;
        allergies = new ArrayList<>();
        needinit = true;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        pref = -1;
        gender = -1;
        dietoption = -1;
        healthoption = -1;
        allergies = new ArrayList<>();
        needinit = true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPref(int pref) {
        this.pref = pref;
    }

    public void setAllergies(List<String> allergies) {
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

    public int getPref() {
        return pref;
    }

    public List<String> getAllergies() {
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
