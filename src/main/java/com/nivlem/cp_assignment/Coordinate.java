/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

import java.util.ArrayList;

/**
 *
 * @author melvi
 */
public class Coordinate {
    private double x;
    private double y;
    
    // constructor to save x and y axis
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    // check if coordinate exist in list
    public boolean isExist(ArrayList<Coordinate> array) {
        try {
           for(int i=0; i < array.size(); i++) {
            if(array.get(i).getX() == x && array.get(i).getY() == y) return true;
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //getter method
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public String toString() {
        return "("+x+","+y+")";
    }
}
