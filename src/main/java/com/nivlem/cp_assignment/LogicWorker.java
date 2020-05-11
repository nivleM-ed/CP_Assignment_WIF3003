/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

import java.util.ArrayList;

/**
 *
 * @author melvi
 */
public class LogicWorker implements Runnable{
    
    private CoordinateArray coArr;
    
    public LogicWorker(CoordinateArray coArr) {
        this.coArr = coArr;
    }

    @Override
    public void run() {
        //Thread sleep to test time termination
//        try {
//                Thread.sleep(10);
//        } catch (Exception e) {
//        }
        
        try {
            coArr.addEdge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}