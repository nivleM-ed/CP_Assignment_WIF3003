/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author melvi
 */
public class main {
//    public static ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    public static int n = 10000;    //number of coodinates
    public static int t = 5;        //number of threads
    public static int m = 10;        //time to terminate
            
    public static void main(String[] args) {
        int i = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(t);
        CoordinateArray coArr = new CoordinateArray(executorService, n);
        
        //run thread to terminate thread after m seconds
        Thread thread = new Thread(new StopExecutor(executorService, m));
        thread.start();
        
        //execute threads to add edges
        while(i < n/2) {
            LogicWorker lw = new LogicWorker(coArr);
            executorService.submit(lw);
            i++;
        }
        
        while(!executorService.isTerminated()) {}
        
        System.out.println(coArr.getEdge());
        System.out.println("Total Edges: " + coArr.getEdge().size());
    }
}
