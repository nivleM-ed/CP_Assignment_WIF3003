/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author melvi
 */
public class LogicMain {
    private int t;
    private int m;
    private int n;
    private ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    
    public LogicMain(int t, int m,int n, ArrayList<Coordinate> coordinates) {
        this.t = t;
        this.m = m;
        this.n = n;
        this.coordinates = coordinates;
    }
    
    public void runLogic() {
        Thread[] thread = new Thread[t];
        LogicWorker[] lW = new LogicWorker[t];
        ArrayList<Coordinate> temp = setArray();
        
        for(int i=0; i<t; i++) {
            lW[i] = new LogicWorker(m,temp);
            thread[i] = new Thread(lW[i]);
            thread[i].start();
        }
        
        try {
            for(int i=0; i<t; i++) {
            thread[i].join();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for(int i=0; i<t; i++) {
            edges.addAll(lW[i].getEdges());
        }
    }
    
    private ArrayList<Coordinate> setArray() {
        ArrayList<Coordinate> temp = new ArrayList<Coordinate>();
        while(temp.size() != n/t) {
            Random rand = new Random();
            temp.add(coordinates.remove(rand.nextInt(coordinates.size())));
        }
        return temp;
    }
    
    public ArrayList<Edge> getEdges() {
        return edges;
    }
    
    public int getEdgesLength() {
        return edges.size();
    }
}
