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
    public static ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    public static int n = 10000;
    public static int t = 5;
    public static int m = 10;
            
    public static void main(String[] args) {
        coordinates = createRandomCoordinates(n);
        System.out.println(coordinates);
        LogicMain lM = new LogicMain(t, m, n, coordinates);
        System.out.println(lM.runLogic());
//        System.out.println(lM.getEdges());
        System.out.println("Total Length:"+lM.getEdgesLength());
    }
    
    public static ArrayList<Coordinate> createRandomCoordinates(int n) {
        ArrayList<Coordinate> temp = new ArrayList<Coordinate>();
        while(temp.size() != n) {
            Random rand = new Random();
            float x = rand.nextFloat()*1000;
            float y = rand.nextFloat()*1000;
            Coordinate tempC = new Coordinate(x,y);
            if(!tempC.isExist(temp)) temp.add(tempC);
        }
        return temp;
    }
}
