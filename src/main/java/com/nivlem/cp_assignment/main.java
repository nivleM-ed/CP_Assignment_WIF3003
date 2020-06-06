/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author melvi
 */
public class main {
    public static int n = 100000;    //number of coodinates
    public static int t = 5;        //number of threads
    public static double m = 0.5;        //time to terminate
    
            
    public static void main(String[] args) {
    //declare and format JFrame
    JFrame mainFrame = new JFrame();
    mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    mainFrame.getContentPane().setBackground( Color.BLACK );
    
    //declare LinesComponent
    final LinesComponent lines = new LinesComponent();
    lines.setPreferredSize(new Dimension(1000, 1000));
    mainFrame.getContentPane().add(lines, BorderLayout.CENTER);

    //declare and format JPanel for input
    JPanel inputPanel = new JPanel();
    inputPanel.setPreferredSize(new Dimension(500,50));
    inputPanel.setBackground(Color.WHITE);
//    inputPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
    
    //declare and format JPanel for output
    JPanel outputPanel = new JPanel();
    outputPanel.setPreferredSize(new Dimension(500,1000));
    outputPanel.setBackground(Color.WHITE);
//    outputPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
    
    //declare textfield/labels/buttons for inputPanel
    JLabel label_coordinates = new JLabel("Coordinates");
    JLabel label_thread = new JLabel("Threads");
    JLabel label_time = new JLabel("Time");
    JTextField coordinates = new JTextField("",1);
    JTextField thread = new JTextField("",2);
    JTextField time = new JTextField("",3);
    JButton start = new JButton("Start");
    
    coordinates.setColumns(20);
    thread.setColumns(20);
    time.setColumns(20);
    
    //declare textfield/labels/buttons for outputPanel
    JLabel label_output = new JLabel("Output");
    JTextArea output = new JTextArea();
    output.setPreferredSize(new Dimension(440, 600));
    JScrollPane scroll = new JScrollPane(output);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//    mainFrame.getContentPane().add(scroll);
    
    //add components into outputPanel
    outputPanel.add(label_output);
    outputPanel.add(scroll);
    
    //add components into inputPanel
    inputPanel.add(label_coordinates);
    inputPanel.add(coordinates);
    inputPanel.add(label_thread);
    inputPanel.add(thread);
    inputPanel.add(label_time);
    inputPanel.add(time);
    inputPanel.add(start);
    inputPanel.add(outputPanel);
    
    //add panels into mainFrame
    mainFrame.getContentPane().add(inputPanel, BorderLayout.EAST);
    
    start.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int n,t;
            double m;
            n = Integer.parseInt(coordinates.getText());
            t = Integer.parseInt(thread.getText());
            m = Double.parseDouble(time.getText());

            System.out.println("Number of points = " + n);
            System.out.println("Threads = " + t);
            System.out.println("Time termination = " + m);
            
            runLogic(n ,t, m, lines, output);
            }
    });
    
    mainFrame.pack();
    mainFrame.setVisible(true);
}
    public static void runLogic(int n, int t, double m, LinesComponent lines, JTextArea output) {
        int i = 0;
            ExecutorService executorService = Executors.newFixedThreadPool(t);
            CoordinateArray coArr = new CoordinateArray(executorService, n, lines);
            OutputPanel outPanel = new OutputPanel(output);

            //execute threads to add edges
            LogicWorker lw[] = new LogicWorker[t];
            while(i < t) {
                lw[i] = new LogicWorker(coArr, m, outPanel);
                executorService.submit(lw[i]);
                i++;
            }

            executorService.shutdownNow();
            while(!executorService.isTerminated()) {}

            int winner = 0;
            for (int j = 0; j < t; j++) {
                System.out.println(lw[j].getName() + ": Success: " + lw[j].getSuccess() + " Failure: " + lw[j].getFailure() + " Finished in " + lw[j].getRuntime());
                if(lw[j].getSuccess() > lw[winner].getSuccess()) winner = j;
            }
            System.out.println("The winner is " + lw[winner].getName() + " with " + lw[winner].getSuccess() + " edges");
            System.out.println("Total Edges Created: " + coArr.getEdge().size());
            
            outPanel.appendText("\nThe winner is " + lw[winner].getName() + " with " + lw[winner].getSuccess() + " edges");
            outPanel.appendText("Total Edges Created: " + coArr.getEdge().size());
    }
}
