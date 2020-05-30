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
public class LogicWorker implements Runnable{
    private int m;
    private int worker; 
    private ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    
    public LogicWorker(int m, ArrayList<Coordinate> coordinates, int worker) {
        this.m = m;
        this.coordinates = coordinates;
        this.worker = worker;
    }

    @Override
    public void run() {
        System.out.println("Worker "+ worker + ":" + coordinates.size());
        try {
            while(coordinates.size() > 1) {
                Coordinate first = coordinates.remove(0);
                Coordinate second = coordinates.remove(0);
                System.out.println("Worker "+ worker + ":" + coordinates.size());
                Edge tmpE = new Edge(first, second);
                edges.add(tmpE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Edge> getEdges() {
        return edges;
    }
}
