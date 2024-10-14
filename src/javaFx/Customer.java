/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

/**
 *
 * @author
 */
public class Customer {

    private Status myStatus = new Silver();
    public String name;
    public String password;
    public int points;
    //Status status;

    public Customer(String name, String password, int points) {
        this.name = new String(name);
        this.password = new String(password);
        this.points = points;
    }

    public void accPoints(int price) { // Might change this to void later
        int test = 10 * price;
        this.points += test;

    }

    public double redeemPoints(double price) { // Might change this to void later
        int testPoints = points;
        double testPrice = price;

        while (price >= 0) {
            if (points <= 0) {
                break;
            }

            if (points < 0) {
                points = testPoints;
            }

            if (price - 1 < 0) {
                price = testPrice;
                break;
            }

            price = price - 1;
            points = points - 100;
        }

        return price;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getPoints() {
        return this.points;
    }

    public void setName(String ownerName) {
        this.name = ownerName;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void turnGold() {
        myStatus.turnGold(this);
    }

    public void turnSilver() {
        myStatus.turnSilver(this);
    }

    public void setStatus(Status s) {
        myStatus = s;
    }

    public Status getStatus() {
        return myStatus;
    }

}
