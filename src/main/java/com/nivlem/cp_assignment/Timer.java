/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

/**
 *
 * @author user
 */
public class Timer {
    private long start;
    private long finish;

    public void start(){
        start = System.currentTimeMillis();
    }
    public void finish(){
        finish = System.currentTimeMillis();
    }

    public long timeTaken(){
        return finish - start;
    }
}
