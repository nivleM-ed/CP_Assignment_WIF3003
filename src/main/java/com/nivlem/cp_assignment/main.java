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
    public static int n = 100;
    public static int t = 5;
    public static int m = 10;
            
    public static void main(String[] args) {
//        coordinates = createRandomCoordinates(n);
//        System.out.println(coordinates);
//        LogicMain lM = new LogicMain(t, m, n, coordinates);
//        System.out.println(lM.runLogic());
//        System.out.println(lM.getEdges());
//        System.out.println("Total Length:"+lM.getEdgesLength());

        int i = 0;
        CoordinateArray coArr = new CoordinateArray(n);
        ExecutorService executorService = Executors.newFixedThreadPool(t);
        
        while(i < n/2) {
            LogicWorker lw = new LogicWorker(coArr, i);
            executorService.submit(lw);
            i++;
        }
        
        executorService.shutdown();
        while(!executorService.isTerminated()) {}
        
        System.out.println(coArr.getEdge());
    }
    
//    public static ArrayList<Coordinate> createRandomCoordinates(int n) {
//        ArrayList<Coordinate> temp = new ArrayList<Coordinate>();
//        while(temp.size() != n) {
//            Random rand = new Random();
//            float x = rand.nextFloat();
//            float y = rand.nextFloat();
//            Coordinate tempC = new Coordinate(x,y);
//            if(!tempC.isExist(temp)) temp.add(tempC);
//        }
//        return temp;
//    }
}
