package com.trendz.mvp.model;

public class Trend {
    private String name;
    private double score;

    public Trend() {
    }

    public Trend(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}