/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author melvi
 */
public class CoordinateArray {
    private ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private ReentrantLock lock = new ReentrantLock();
    
    public CoordinateArray(int n) {
        this.coordinates = createRandomCoordinates(n);
        System.out.println(coordinates);
    }
    
    public synchronized void addEdge(int num) throws InterruptedException {
            lock.lock();
            if(coordinates.size() > 1) {
                Coordinate first = coordinates.remove(0);
                Coordinate second = coordinates.remove(0);
                Edge tmpE = new Edge(first, second);
                edges.add(tmpE);
            }
            lock.unlock(); 
    }
    
    private ArrayList<Coordinate> createRandomCoordinates(int n) {
        ArrayList<Coordinate> temp = new ArrayList<Coordinate>();
        while(temp.size() != n) {
            Random rand = new Random();
            float x = rand.nextFloat();
            float y = rand.nextFloat();
            Coordinate tempC = new Coordinate(x,y);
            if(!tempC.isExist(temp)) temp.add(tempC);
        }
        return temp;
    }

    public synchronized ArrayList<Edge> getEdge() {
        return edges;
    }
}
