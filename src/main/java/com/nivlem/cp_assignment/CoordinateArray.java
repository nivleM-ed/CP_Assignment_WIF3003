/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

import java.awt.Color;
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

    //declare lock
    private ReentrantLock lock = new ReentrantLock();

    //declare executor
    private ExecutorService executorService;
    
    private LinesComponent lines;

    //constructor to input number of coordinates to create list of coordinates
    public CoordinateArray(ExecutorService exectorService, int n, LinesComponent lines) {
        this.coordinates = createRandomCoordinates(n);
        System.out.println("COORDINATES: " + coordinates);
        this.executorService = exectorService;
        this.lines = lines;
    }

    //main method of add edge
    public int addEdge(Color c) throws InterruptedException {
        lock.lock(); //Thread to acquire lock first
        int token = 0; // 0 = No more coordinate to use, 1 = edge added, 2 = fail to add edge
     
        if (edges.size() < coordinates.size() / 2) { //check if all coordinates have been used

            if (coordinates.size() > 1) { //check if pairs can be made
                Coordinate first = coordinates.get(new Random().nextInt(coordinates.size()));
                Coordinate second = coordinates.get(new Random().nextInt(coordinates.size()));

                Edge tmpE = new Edge(first, second);

                if (!tmpE.isExist(edges)) { //check if the edge exist
                    //if edge doesn't exist, push edge into edge list and draw line
                    System.out.println("Edge Created By " + Thread.currentThread().getName() + " : " + tmpE.toString());
                    edges.add(tmpE);
                    lines.addLine(tmpE.getFirst().getX(), tmpE.getFirst().getY(), tmpE.getSecond().getX(), tmpE.getSecond().getY(), c);
                    token = 1; // edge is added
                } else {
                    token = 2; // edge is not added
                }
            }
        }
        lines.repaint(); // make sure line is drawn on the user interface 
        lock.unlock(); // unlock the lock
        return token; // return 0,1,2
    }

    //method: create list of coordinates
    private ArrayList<Coordinate> createRandomCoordinates(int n) {
        ArrayList<Coordinate> temp = new ArrayList<Coordinate>();
        while (temp.size() != n) { // loop until the size reaches n
            Random rand = new Random(); 
            double x = Math.round((rand.nextFloat() * (MAX_REGION - MIN_REGION) + MIN_REGION) * 100.0) / 100.0;
            double y = Math.round((rand.nextFloat() * (MAX_REGION - MIN_REGION) + MIN_REGION) * 100.0) / 100.0;
            Coordinate tempC = new Coordinate(x, y); // create Coordinate with randomly created x and y
            if (!tempC.isExist(temp)) { // check if Coodinate exist in the list
                temp.add(tempC);
            }
        }
        return temp;
    }

    //getter method
    public synchronized ArrayList<Edge> getEdge() {
        return edges;
    }
}
