package com.example.gads2020practice.SkillIQ;

import com.google.gson.annotations.SerializedName;

public class SkillIQ {
    @SerializedName("name")
    private String name;
    private int score;
    private String country;

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }
}
