///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.nivlem.cp_assignment;
//
//import java.util.ArrayList;
//
///**
// *
// * @author user
// */
//public class UILogic {
//    private ArrayList<Edge> edges = new ArrayList<Edge>();
//    private int t;
//    
//    public UILogic(ArrayList edge, int t){
//        this.edges = edge;
//        this.t = t;
//    }
//    
//    public void runUI(){
//        Thread[] thread = new Thread[t];
//        LogicWorker[] lW = new LogicWorker[t];
//        UI[] ui = new UI[t];
//        
//        for(int i=0; i<edges.size(); i++) {
//            System.out.println("Generating lines");
//            ui[i] = new UI(edges,i);
//            thread[i] = new Thread(ui[i]);
//            thread[i].start();
//        }
//        
//    }
//    
//}
