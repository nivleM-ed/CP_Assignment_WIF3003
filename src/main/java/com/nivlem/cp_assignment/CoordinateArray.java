/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author melvi
 */
public class CoordinateArray {
    //to implement coordinates in a 1000x1000 region
    private final int MAX_REGION = 1000;
    private final int MIN_REGION = 0;
    
    //declare ArrayList/Stack
    private ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    
    //declare failure
    private int failure = 0;
    
    //declare lock
    private ReentrantLock lock = new ReentrantLock();
    
    //declare executor
    private ExecutorService executorService;
    
    //constructor: input number of coordinates to create list of coordinates
    public CoordinateArray(ExecutorService exectorService, int n) {
        this.coordinates = createRandomCoordinates(n);
        System.out.println("COORDINATES: " + coordinates);
        this.executorService = exectorService;
    }
    
    //method: add one edge
    public void addEdge() throws InterruptedException {
            lock.lock(); //Thread to acquire lock first
            
            if(failure > 6) shutdownThreads();
            
            if(coordinates.size() > 1) {
                Coordinate first = coordinates.get(new Random().nextInt(coordinates.size()));
                Coordinate second = coordinates.get(new Random().nextInt(coordinates.size()));
                
                Edge tmpE = new Edge(first, second);
                System.out.println("Edge Created By " + Thread.currentThread().getName() + " : " + tmpE.toString());
                
                if(!tmpE.isExist(edges)) edges.add(tmpE);
                else {
                    System.out.println("Failure: " + failure);
                    failure++;
                }
            }
            lock.unlock(); 
    }
    
    //method: create list of coordinates
    private ArrayList<Coordinate> createRandomCoordinates(int n) {
        ArrayList<Coordinate> temp = new ArrayList<Coordinate>();
        while(temp.size() != n) {
            Random rand = new Random();
            double x = Math.round((rand.nextFloat() * (MAX_REGION - MIN_REGION) + MIN_REGION) * 100.0) / 100.0;
            double y = Math.round((rand.nextFloat() * (MAX_REGION - MIN_REGION) + MIN_REGION) * 100.0) / 100.0;
            Coordinate tempC = new Coordinate(x,y);
            if(!tempC.isExist(temp)) temp.add(tempC);
        }
        return temp;
    }
    
    private void shutdownThreads() {
        try {
            executorService.shutdownNow();
            System.out.println("Shutting down threads: 20 failures");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized ArrayList<Edge> getEdge() {
        return edges;
    }
}
