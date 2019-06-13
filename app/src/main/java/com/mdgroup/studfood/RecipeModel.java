package com.mdgroup.studfood;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pictureID")
    @Expose
    private int pictureID;
    @SerializedName("ingredients")
    @Expose
    private String ingredients;
    @SerializedName("technology")
    @Expose
    private String technology;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPictureID() {
        return pictureID;
    }

    public void setPictureID(int pictureID) {
        this.pictureID = pictureID;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }
}
