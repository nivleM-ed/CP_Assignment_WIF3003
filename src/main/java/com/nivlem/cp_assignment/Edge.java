/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;
import java.util.HashMap;
/**
 *
 * @author melvi
 */
public class Edge {
    Coordinate first;
    Coordinate second;
//    HashMap<Coordinate , Coordinate > EdgeMap = new HashMap<Coordinate , Coordinate >();
    public Edge(Coordinate first, Coordinate second) {
        this.first = first;
        this.second = second;
    }
    
    public String toString() {
        return "(" + first.toString() + ","+ second.toString() + ")";
    }
}
