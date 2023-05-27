package com.example.debug;

public class toolModel {

    private int id;
    private int rate;
    private int rateNum;
    private String name;
    private String model;
    private String overview;
    private int cost;
    private String prodYear;

    private String userName;

    public toolModel(int id, int rate, String name, String model, String overview, int cost, String prodYear,int rateNum, String userName) {
        this.id = id;
        this.rate = rate;
        this.name = name;
        this.model = model;
        this.overview = overview;
        this.cost = cost;
        this.prodYear = prodYear;
        this.rateNum = rateNum;
        this.userName=userName;
    }


    public int getId(){return id;}
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate/rateNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }


    public String getOverview() {
        return overview;
    }



    public int getCost() {
        return cost;
    }


    public String getProdYear() {
        return prodYear;
    }


    public int getRateNum() {
        return rateNum;
    }


    @Override
    public String toString() {
        return  "id:    " + id +
                "\nrate:    " + rate +"("+""+")"+
                "\nname:    " + name +
                "\nmodel:   " + model +
                "\n \noverview:    " + overview +
                "\ncost:   " + cost +
                "\nprodYear:    " + prodYear;
    }
    public String abstracttoString() {
        return  "\nrate:    " + rate +
                "\nname:    " + name +
                "\nmodel:   " + model;
    }
}

