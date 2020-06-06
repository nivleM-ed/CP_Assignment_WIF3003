/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

/**
 *
 * @author melvi
 */
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class LinesComponent extends JComponent{

private static class Line{
    final double x1; 
    final double y1;
    final double x2;
    final double y2;   
    final Color color;

    public Line(double x1, double y1, double x2, double y2, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }               
}

private volatile LinkedList<Line> lines = new LinkedList<Line>();

public void addLine(double x1, double x2, double x3, double x4) {
    addLine(x1, x2, x3, x4, Color.black);
}

public boolean addLine(double x1, double x2, double x3, double x4, Color color) {
    lines.add(new Line(x1,x2,x3,x4, color));
    drawLines(getGraphics(),x1,x2,x3,x4,color);
//    repaint();
    return true;
}

public void clearLines() {
    lines.clear();
//    repaint();
}
public void drawLines(Graphics g,double x1, double x2, double x3, double x4, Color color){
    Graphics2D g2g = (Graphics2D) g;
    g2g.setColor(color);
    g2g.setStroke(new BasicStroke(3)); //set thickness of lines
    g2g.draw(new Line2D.Float((float)x1, (float)x2, (float)x3, (float)x4));
}

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2g = (Graphics2D) g;
    for (Line line : lines) {
        g2g.setColor(line.color);
        g2g.setStroke(new BasicStroke(3)); //set thickness of lines
        g2g.draw(new Line2D.Float((float)line.x1, (float)line.y1, (float)line.x2, (float)line.y2));
    }
}

//public static void main(String[] args) {
//    //declare and format JFrame
//    JFrame mainFrame = new JFrame();
//    mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    mainFrame.getContentPane().setBackground( Color.BLACK );
//    
//    //declare LinesComponent
//    final LinesComponent lines = new LinesComponent();
//    lines.setPreferredSize(new Dimension(1000, 1000));
//    mainFrame.getContentPane().add(lines, BorderLayout.CENTER);
//
//    //declare and format JPanel for input
//    JPanel inputPanel = new JPanel();
//    inputPanel.setPreferredSize(new Dimension(250,50));
//    inputPanel.setBackground(Color.WHITE);
////    inputPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
//    
//    //declare and format JPanel for output
//    JPanel outputPanel = new JPanel();
//    outputPanel.setPreferredSize(new Dimension(300,1000));
//    outputPanel.setBackground(Color.WHITE);
////    outputPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
//    
//    //declare textfield/labels/buttons for inputPanel
//    JLabel label_coordinates = new JLabel("Coordinates");
//    JLabel label_thread = new JLabel("Threads");
//    JLabel label_time = new JLabel("Time");
//    JTextField coordinates = new JTextField("",1);
//    JTextField thread = new JTextField("",2);
//    JTextField time = new JTextField("",3);
//    JButton start = new JButton("Start");
//    
//    coordinates.setColumns(20);
//    thread.setColumns(20);
//    time.setColumns(20);
//    
//    //declare textfield/labels/buttons for outputPanel
//    JLabel label_output = new JLabel("Output");
//    JTextArea output = new JTextArea();
//    output.setPreferredSize(new Dimension(240, 600));
//    JScrollPane scroll = new JScrollPane(output);
//    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//    
//    //add components into outputPanel
//    outputPanel.add(label_output);
//    outputPanel.add(scroll);
//    
//    //add components into inputPanel
//    inputPanel.add(label_coordinates);
//    inputPanel.add(coordinates);
//    inputPanel.add(label_thread);
//    inputPanel.add(thread);
//    inputPanel.add(label_time);
//    inputPanel.add(time);
//    inputPanel.add(start);
//    inputPanel.add(outputPanel);
//    
//    //add panels into mainFrame
//    mainFrame.getContentPane().add(inputPanel, BorderLayout.EAST);
//    
//    start.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            int n,t;
//            double m;
//            n = Integer.parseInt(coordinates.getText());
//            t = Integer.parseInt(thread.getText());
//            m = Double.parseDouble(time.getText());
//
//            System.out.println("Number of points = " + n);
//            System.out.println("Threads = " + t);
//            System.out.println("Time termination = " + m);
//
//            int i = 0;
//            ExecutorService executorService = Executors.newFixedThreadPool(t);
//            CoordinateArray coArr = new CoordinateArray(executorService, n, lines);
//            OutputPanel outPanel = new OutputPanel(output);
//
//            //execute threads to add edges
//            LogicWorker lw[] = new LogicWorker[t];
//            while(i < t) {
//                lw[i] = new LogicWorker(coArr, m, outPanel);
//                executorService.submit(lw[i]);
//                i++;
//            }
//
//            executorService.shutdownNow();
//            while(!executorService.isTerminated()) {}
//
//            int winner = 0;
//            for (int j = 0; j < t; j++) {
//                System.out.println(lw[j].getName() + ": Success: " + lw[j].getSuccess() + " Failure: " + lw[j].getFailure() + " Finished in " + lw[j].getRuntime());
//                if(lw[j].getSuccess() > lw[winner].getSuccess()) winner = j;
//            }
//            System.out.println("The winner is " + lw[winner].getName() + " with " + lw[winner].getSuccess() + " edges");
//            System.out.println("Total Edges Created: " + coArr.getEdge().size());
//            }
//    });
//    
//    mainFrame.pack();
//    mainFrame.setVisible(true);
//}

}