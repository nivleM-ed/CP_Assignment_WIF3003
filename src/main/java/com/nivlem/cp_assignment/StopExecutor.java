/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

import java.util.concurrent.ExecutorService;

/**
 *
 * @author melvi
 */
public class StopExecutor implements Runnable{
    private ExecutorService executorService;
    private int m;
    
    public StopExecutor(ExecutorService executorService, int m) {
        this.executorService = executorService; 
        this.m = m * 1000;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        try {
            Thread.sleep(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            long end = System.nanoTime();
            executorService.shutdownNow();
            double diff = (double)(end - start) / 1_000_000_000.0;
            System.out.println("Shutting down threads: " + diff + " seconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
