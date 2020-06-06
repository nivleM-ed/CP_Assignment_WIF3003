/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author melvi
 */
public class CoordinateArray extends JFrame {
    
    JTextArea text;
    
    //to implement coordinates in a 1000x1000 region
    private final int MAX_REGION = 1;
    private final int MIN_REGION = 0;

    //declare ArrayList/Stack
    private ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();

    //declare lock
    private ReentrantLock lock = new ReentrantLock();

    //declare executor
    private ExecutorService executorService;

    //constructor: input number of coordinates to create list of coordinates
    public CoordinateArray(ExecutorService exectorService, int n) {
        this.coordinates = createRandomCoordinates(n);
        System.out.println("COORDINATES: " + coordinates);
        this.executorService = exectorService;
        
        setTitle("Drawing Lines");
        setSize(1600, 1100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel draw = new JPanel();
        draw.setPreferredSize(new Dimension(1000,1000));
        draw.setBackground(Color.white);
        add(draw);
        
        JPanel score = new JPanel();
        score.setPreferredSize(new Dimension(350,1000));
        score.setBackground(Color.green);
        score.setBorder(new EmptyBorder(5,5,5,5));
        score.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        JLabel test1 = new JLabel("SCOREBOARD", SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.ipady = 100;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        score.add(test1, c);
        
        text = new JTextArea();
        JScrollPane scroll = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 800;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        score.add(scroll, c);
        
        add(score, BorderLayout.EAST);
        
    }

    //method: add one edge
    public int addEdge(Color c) throws InterruptedException {
        lock.lock(); //Thread to acquire lock first
        int token = 0; // 0 = No more coordinate to use 1 = edge added, 2 = fail to add edge
        if (edges.size() < coordinates.size() / 2) { //check if all coordinates have been used

            if (coordinates.size() > 1) {
                Coordinate first = coordinates.get(new Random().nextInt(coordinates.size()));
                Coordinate second = coordinates.get(new Random().nextInt(coordinates.size()));
               
                
                
    
                Edge tmpE = new Edge(first, second);
                

//                System.out.println(tmpE);
                if (!tmpE.isExist(edges)) {
                    String threadName = Thread.currentThread().getName();
                    String event = "Player" + threadName.substring(threadName.lastIndexOf("-")) 
                            + " drew a line from " + tmpE.toString();
                    System.out.println(event);
//                    text.append(event + "\n");
                    edges.add(tmpE);
                    doDrawing(getGraphics(),tmpE, c);
                    token = 1;
                } else {
                    token = 2;
                }
            }
        }
        lock.unlock();
        return token;
    }
    

    //method: create list of coordinates
    private ArrayList<Coordinate> createRandomCoordinates(int n) {
        ArrayList<Coordinate> temp = new ArrayList<Coordinate>();
        while (temp.size() != n) {
            Random rand = new Random();
//            double x = Math.round((rand.nextFloat() * (MAX_REGION - MIN_REGION) + MIN_REGION) * 100.0) / 100.0;
//            double y = Math.round((rand.nextFloat() * (MAX_REGION - MIN_REGION) + MIN_REGION) * 100.0) / 100.0;
            double x = Math.round((rand.nextFloat() * (MAX_REGION - MIN_REGION) + MIN_REGION) * 100.0);
            double y = Math.round((rand.nextFloat() * (MAX_REGION - MIN_REGION) + MIN_REGION) * 100.0);
            Coordinate tempC = new Coordinate(x, y);
            if (!tempC.isExist(temp)) {
                temp.add(tempC);
            }
        }
        return temp;
    }

    public synchronized ArrayList<Edge> getEdge() {
        return edges;
    }
    private void doDrawing(Graphics g, Edge edge, Color c){
        String splt1[] = edge.toString().split(" to ");
        String firstEdge[] = splt1[0].split(",");
        String secondEdge[] = splt1[1].split(",");
//        System.out.println(firstEdge[0].replaceAll("\\p{P}",""));

        //log("ThreadSwing: " + Thread.currentThread());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(c);
        g2d.draw(new Line2D.Float(
                StringtoDouble(firstEdge[0].replaceAll("\\p{P}","")), 
                StringtoDouble(secondEdge[0].replaceAll("\\p{P}","")), 
                StringtoDouble(firstEdge[1].replaceAll("\\p{P}","")), 
                StringtoDouble(secondEdge[1].replaceAll("\\p{P}",""))
        ));


            //Render a straight line
//            g2d.drawLine(0, HEIGHT/2, WIDTH, HEIGHT/2);
//            g2d.drawLine(120, 50, 360, 50);
//            g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f, 132.50f));
            //g2d.drawLine(0, 0, WIDTH, HEIGHT);
//            return;
    }
    public Float StringtoDouble(String x){
        Float cvt = Float.parseFloat(x);
        return cvt;
    }
    
}
