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
    private final int MAX_FAILURE = 2000;
    private double m;
    private double diff;
    private String threadName;
    private int failure;
    private int success;
    private OutputPanel output;

    public LogicWorker(CoordinateArray coArr, double m, OutputPanel output) {
        this.coArr = coArr;
        this.m = m;
        this.failure = 0;
        this.success = 0;
        this.output = output;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        threadName = Thread.currentThread().getName();
        
        String getThreadNumber[] = threadName.split("-");
        //to be changed    
        Color[] colors = new Color[10];
        colors[0] = Color.red;
        colors[1] = Color.green;
        colors[2] = Color.blue;
        colors[3] = Color.yellow;
        colors[4] = Color.cyan;
        colors[5] = Color.magenta;
        colors[6] = Color.black;
        colors[7] = Color.gray;
        colors[8] = Color.orange;
        colors[9] = Color.pink;
        
        try {
            while (failure < MAX_FAILURE && !endToken && !checkTime(start)) { //condition for thread to stop
                int result = coArr.addEdge(colors[Integer.parseInt(getThreadNumber[3])-1], output);

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
