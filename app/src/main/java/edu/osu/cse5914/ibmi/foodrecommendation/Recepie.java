package edu.osu.cse5914.ibmi.foodrecommendation;

public class Recepie {

    private String name;
    private String  rating;
    private String prepTime;




    public Recepie(String name, String rating, String prepTime) {
        this.rating = rating;
        this.name = name;
        this.prepTime = prepTime;
    }



    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getPrepTime() {

        return prepTime;
    }


    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
