/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

import java.awt.Color;

/**
 *
 * @author melvi
 */
public class LogicWorker implements Runnable {

    private CoordinateArray coArr;
    private boolean endToken = false;
    private final int MAX_FAILURE = 20;
    private double m;
    private double diff = 0;

    private int failure;
    private int success;

    private final Color[] colors = {Color.red, Color.green, Color.blue, Color.yellow, Color.cyan, Color.magenta, Color.white, Color.gray, Color.orange, Color.pink};
    private final String[] color = {"Red", "Green", "Blue", "Yellow", "Cyan", "Magenta", "White", "Gray", "Orange", "Pink"};

    private String threadName;
//    private Color threadColor;
    private int threadNum;

    //constructor class 
    public LogicWorker(CoordinateArray coArr, double m) {
        this.coArr = coArr;
        this.m = m;
        this.failure = 0;
        this.success = 0;
    }

    @Override
    public void run() {
        long start = System.nanoTime(); //get the starting time
        String threadNameTmp = Thread.currentThread().getName(); //get name of current thread
        String getThreadNumber[] = threadNameTmp.split("-"); //get thread number
        threadNum = Integer.parseInt(getThreadNumber[3]) - 1;
        threadName = "Player " + threadNum; //set name of thread
        Color threadColor = threadNum > 9 ? Color.white : colors[threadNum]; //if  there are more than 10 players, set color to white
        try {
            //condition for thread to stop
            while (failure < MAX_FAILURE && !endToken && !checkTime(start)) { //proceed if failure is not more than 20, there are still Coordinates left in the list, executed time is not more than the time set by the user
                int result = coArr.addEdge(threadColor);

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

                try { 
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //check time - return true if exceed time/ return false if not
    private boolean checkTime(long start) {
        long now = System.nanoTime();
        double tmpDiff = (double) (now - start) / 1_000_000_000.0;

        if (tmpDiff >= m) {
            return true;
        }

        diff = tmpDiff;
        return false;
    }

    //getter methods
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

    public String getColor() {
        return threadNum < 10 ? color[threadNum] : "White";
    }
}
