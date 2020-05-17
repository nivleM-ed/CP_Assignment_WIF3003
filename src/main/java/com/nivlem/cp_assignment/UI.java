///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.nivlem.cp_assignment;
//
///**
// *
// * @author user
// */
//
//import java.awt.Graphics;
//import java.util.ArrayList;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//public class UI extends JFrame implements Runnable {
//    private ArrayList<Edge> edges = new ArrayList<Edge>();
//    private int worker;
//    
//    public UI(ArrayList edge, int worker){
//        setTitle("Test Lines");
//        setSize(960, 960);
//        setVisible(true);
//        this.edges = edge;
//        this.worker = worker;
//    }
//    public void paint(Graphics g){
//        String third = new String();
//        String forh = new String();
//        g.drawLine(0, 480, 100, 500);
//        for(int i = 0; i<edges.size(); i++){
//            Edge tempt = edges.get(i);
//            String splt1[] = tempt.toString().split("@");
//            String firstEdge[] = splt1[0].split(",");
//            String secondEdge[] = splt1[1].split(",");
//            System.out.println(StringtoInt(firstEdge[0]));
//            g.drawLine(StringtoInt(firstEdge[0]), StringtoInt(secondEdge[0]), StringtoInt(firstEdge[1]), StringtoInt(secondEdge[1])); 
//            
//        }
//    } 
//    @Override
//    public void run() {
////        System.out.println("Worker "+ worker + ":" + coordinates.size());
//        try {
//            JPanel panel = new JPanel();
//            Graphics g = panel.getGraphics();
////            Edge tempt = edges.get(worker);
////            String splt1[] = tempt.toString().split("@");
////            String firstEdge[] = splt1[0].split(",");
////            String secondEdge[] = splt1[1].split(",");
////            System.out.println(StringtoInt(firstEdge[0]));
////            g.drawLine(StringtoInt(firstEdge[0]), StringtoInt(secondEdge[0]), StringtoInt(firstEdge[1]), StringtoInt(secondEdge[1])); 
//            paint( g);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public int StringtoInt(String x){
//        int cvt = (int) Math.round(Double.parseDouble(x));
//        return cvt;
//    }
//}


//=======================================================================================================================================
//=======================================================================================================================================

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

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class UI extends JFrame {
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    
    public UI(ArrayList edge){
        setTitle("Test Lines");
        setSize(1050, 960);
        setVisible(true);
        this.edges = edge;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void paint(Graphics g){
        int[] x1 = new int[edges.size()];
        int[] x2 = new int[edges.size()];
        int[] y1 = new int[edges.size()];
        int[] y2 = new int[edges.size()];
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
        
//        Graphics2D g2d = (Graphics2D) g;
// 
//        g2d.drawLine(120, 50, 360, 50);
// 
//        g2d.draw(new Line2D.Double(59.2d, 99.8d, 419.1d, 99.8d));
// 
//        g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f, 132.50f));
        
//        for(int i = 0; i<edges.size(); i++){
//            Edge tempt = edges.get(i);
//            String splt1[] = tempt.toString().split("@");
//            String firstEdge[] = splt1[0].split(",");
//            String secondEdge[] = splt1[1].split(",");
//            System.out.println(StringtoInt(firstEdge[0]));
//            x1[i] = StringtoInt(firstEdge[0]);
//            y1[i] = StringtoInt(secondEdge[0]);
//            x2[i] = StringtoInt(firstEdge[1]);
//            y2[i] = StringtoInt(secondEdge[1]);
//            g.setColor(colors[i%9]);
//            g.drawLine(StringtoInt(firstEdge[0]), StringtoInt(secondEdge[0]), StringtoInt(firstEdge[1]), StringtoInt(secondEdge[1])); 
//            
//        }
        
        for(int i = 0; i<edges.size(); i++){
            Edge tempt = edges.get(i);
            String splt1[] = tempt.toString().split("@");
            String firstEdge[] = splt1[0].split(",");
            String secondEdge[] = splt1[1].split(",");
            System.out.println(StringtoInt(firstEdge[0]));
            g.setColor(colors[i%9]);
            g.drawLine(StringtoInt(firstEdge[0]), StringtoInt(secondEdge[0]), StringtoInt(firstEdge[1]), StringtoInt(secondEdge[1])); 
//               g.drawLine(x1[i],y1[i],x2[i],y2[i]);
            
        }
    } 
    public int StringtoInt(String x){
        int cvt = (int) Math.round(Double.parseDouble(x));
        return cvt;
    }
}
