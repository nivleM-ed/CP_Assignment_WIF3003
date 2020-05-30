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
public class main {
<<<<<<< Updated upstream
    public static ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
//    public static int n = 20;
//    public static int t = 5;
//    public static int m = 10;
=======
//    public static int n = 100;    //number of coodinates
//    public static int t = 10;        //number of threads
//    public static double m = 2000.0;        //time to terminate
>>>>>>> Stashed changes
    
    public static void main(String[] args) {
<<<<<<< Updated upstream
//        UI ui = new UI();
//        ui.paint();
        GUI input = new GUI();
        do {
            System.out.println("Pending...");
        } while (!input.getSubmit());
        int n = input.getN();
        int t = input.getT();
        int m = input.getM();
        Timer tm = new Timer();
        tm.start();
        System.out.println("Generating "+n+" coordinates..");
        coordinates = createRandomCoordinates(n);
        System.out.println(coordinates.size()+" coordinates generated.."+coordinates);
        System.out.println("Number of threads.."+t);
        LogicMain lM = new LogicMain(t, m, n, coordinates);
        ArrayList edge = lM.runLogic();
        System.out.println(edge);
//        UILogic ul = new UILogic(edge,t);
        UI ui = new UI(edge);
        tm.finish();
//        System.out.println(lM.getEdges());
        System.out.println("Total Length:"+lM.getEdgesLength());
        System.out.println("Time Taken: "+(float)tm.timeTaken()/1000+ " seconds");
    }
    
    public static ArrayList<Coordinate> createRandomCoordinates(int n) {
        ArrayList<Coordinate> temp = new ArrayList<Coordinate>();
        while(temp.size() != n) {
            Random rand = new Random();
            float x = rand.nextInt(1000);
            float y = rand.nextInt(1000);
            Coordinate tempC = new Coordinate(x,y);
            if(!tempC.isExist(temp)) temp.add(tempC);
=======
        
        UserInterface gui = new UserInterface();
        do {
            System.out.println("Pending user input...");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Error");
            }
        } while (!gui.getSubmit());
        
        int n = gui.getN();
        int t = gui.getT();
        int m = gui.getM();
        
        int i = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(t);
        CoordinateArray coArr = new CoordinateArray(executorService, n);
        
        //run thread to terminate thread after m seconds
//        Thread thread = new Thread(new StopExecutor(executorService, m));
//        thread.start();
        
        //execute threads to add edges
        LogicWorker lw[] = new LogicWorker[t];
        while(i < t) {
            lw[i] = new LogicWorker(coArr, m);
            executorService.submit(lw[i]);
            i++;
        }
        
        executorService.shutdownNow();
        while(!executorService.isTerminated()) {}
        
        int winner = 0;
        for (int j = 0; j < t; j++) {
            System.out.println(lw[j].getName() + ": Success: " + lw[j].getSuccess() + " Failure: " + lw[j].getFailure() + " Finished in " + lw[j].getRuntime());
            if(lw[j].getSuccess() > lw[winner].getSuccess()) winner = j;
>>>>>>> Stashed changes
        }
        return temp;
    }
}
