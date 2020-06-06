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
public class Edge {
    Coordinate first;
    Coordinate second;
    
    public Edge(Coordinate first, Coordinate second) {
        this.first = first;
        this.second = second;
    }
    
    public boolean isExist(ArrayList<Edge> array) {
        try {
            for(int i=0; i < array.size(); i++) {
                if(array.get(i).getFirst() == first || array.get(i).getSecond() == second) return true;
                else if(array.get(i).getFirst() == second || array.get(i).getSecond() == first) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Coordinate getFirst() {
        return this.first;
    }
    
    public Coordinate getSecond() {
        return this.second;
    }
    
    public String toString() {
        return first.toString() + "@"+ second.toString();
    }
}
