/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author melvi
 */
public class LogicWorker implements Runnable {

    private CoordinateArray coArr;
    private boolean endToken = false;
    private final int MAX_FAILURE = 20;
    private double m;
    private double diff;
    private String threadName;
    private int failure;
    private int success;
    private final Color[] colors = {Color.red,Color.green,Color.blue,Color.yellow,Color.cyan,Color.magenta,Color.white,Color.gray,Color.orange,Color.pink};

    public LogicWorker(CoordinateArray coArr, double m) {
        this.coArr = coArr;
        this.m = m;
        this.failure = 0;
        this.success = 0;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        String threadNameTmp = Thread.currentThread().getName();
        String getThreadNumber[] = threadNameTmp.split("-");
        threadName = "Player " + getThreadNumber[3];
        try {
            while (failure < MAX_FAILURE && !endToken && !checkTime(start)) { //condition for thread to stop
                int result = coArr.addEdge(colors[Integer.parseInt(getThreadNumber[3])-1]);

                switch (result) {
                    case 0: //All coordinates have been used
                        endToken = true; 
                        break;
                    case 1: //successfully create an edge
                        success++;
                        break;
                    case 2: //fail to create an edge
                        failure++;
                        break;
                    default: //do nothing
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean checkTime(long start) {
        long now = System.nanoTime();
        diff = (double)(now - start) / 1_000_000_000.0;
        
        if(diff >= m) return true;
        return false;
    }
    
    public int getFailure() {
        return failure;
    }
    
    public int getSuccess() {
        return success;
    }
    
    public String getName() {
        return threadName;
    }
    
    public double getRuntime() {
        return diff;
    }
}
