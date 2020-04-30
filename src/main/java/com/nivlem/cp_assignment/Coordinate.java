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
    private float x;
    private float y;
    
    public Coordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
    
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
    
    public String toString() {
        return "("+x+","+y+")";
    }
}
