/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

import javafx.scene.control.CheckBox;

public class Book {

    private String bookName;
    private double bookPrice;
//        private double bookPrice;
    private CheckBox select;

    Book(String bName, double bPrice) {
        this.bookName = bName;
        this.bookPrice = bPrice;
        this.select = new CheckBox();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bName) {
        bookName = bName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bPrice) {
        bookPrice = bPrice;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox s) {
        this.select = s;
    }
}
