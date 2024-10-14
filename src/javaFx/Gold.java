/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaFx;

/**
 *
 * @author Balraj Sadhra
 */
public class Gold extends Status {

    @Override
    public void turnGold(Customer c) {

    }

    @Override
    public void turnSilver(Customer c) {
        c.setStatus(new Silver());
    }
}
