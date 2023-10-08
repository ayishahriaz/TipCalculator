package com.example.assignment01;

public class Profile {
    String tip;
    double bill;

    public Profile() {}

    public Profile(String tip, double bill) {
        this.tip = tip;
        this.bill = bill;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }
}