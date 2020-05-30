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
            
    public static void main(String[] args) {
        UserInterface gui = new UserInterface();
        do {
            System.out.println("Pending user input...");
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!gui.getSubmit());
        int n = gui.getN();     //number of coodinates
        int t = gui.getT();     //number of threads
        double m = gui.getM();  //time to terminate
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
        }
        System.out.println("The winner is " + lw[winner].getName() + " with " + lw[winner].getSuccess() + " edges");
        System.out.println("Total Edges Created: " + coArr.getEdge().size());
    }
}
