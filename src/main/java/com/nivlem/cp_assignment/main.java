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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
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
        mainFrame.getContentPane().setBackground(Color.BLACK);

        //declare LinesComponent
        final LinesComponent lines = new LinesComponent();
        lines.setPreferredSize(new Dimension(1000, 1000));
        mainFrame.getContentPane().add(lines, BorderLayout.CENTER);

        //declare and format JPanel for input
        JPanel inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(250, 50));
        inputPanel.setBackground(Color.WHITE);
//    inputPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        //declare and format JPanel for output
        JPanel outputPanel = new JPanel();
        outputPanel.setPreferredSize(new Dimension(250, 1000));
        outputPanel.setBackground(Color.WHITE);
//    outputPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        //declare textfield/labels/buttons for inputPanel
        JLabel label_coordinates = new JLabel("Coordinates");
        JLabel label_thread = new JLabel("Threads/Player");
        JLabel label_time = new JLabel("Time Limit");
        JTextField coordinates = new JTextField("", 1);
        JTextField thread = new JTextField("", 2);
        JTextField time = new JTextField("", 3);
        JButton start = new JButton("Start");
        JButton clear = new JButton("Restart");

        coordinates.setColumns(20);
        thread.setColumns(20);
        time.setColumns(20);

        //declare textfield/labels/buttons for outputPanel
        JLabel label_output = new JLabel("Output");
        JTextArea output = new JTextArea();
//    output.setPreferredSize(new Dimension(240, 800));
        JScrollPane scroll = new JScrollPane(output);
        scroll.setPreferredSize(new Dimension(230, 750));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

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
        inputPanel.add(clear);
        inputPanel.add(outputPanel);

        //add panels into mainFrame
        mainFrame.getContentPane().add(inputPanel, BorderLayout.EAST);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n, t;
                double m;
                
                if (coordinates.getText().isEmpty() || thread.getText().isEmpty() || time.getText().isEmpty()){
                    CheckInput valid = new CheckInput("kosong");
                }else if(!isInteger(coordinates.getText()) || !isInteger(thread.getText()) || !isDouble(time.getText())){
                    CheckInput valid = new CheckInput("num");
                }
                
                n = Integer.parseInt(coordinates.getText());
                t = Integer.parseInt(thread.getText());
                m = Double.parseDouble(time.getText());

                System.out.println("Number of points = " + n);
                System.out.println("Threads = " + t);
                System.out.println("Time termination = " + m);

                //add validation
                runLogic(n, t, m, lines, output);
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lines.clearLines();
                coordinates.setText("");
                thread.setText("");
                time.setText("");
            }
        });

        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    public static void runLogic(int n, int t, double m, LinesComponent lines, JTextArea output) {
        int i = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(t);
        CoordinateArray coArr = new CoordinateArray(executorService, n, lines);

        //execute threads to add edges
        LogicWorker lw[] = new LogicWorker[t];
        while (i < t) {
            lw[i] = new LogicWorker(coArr, m);
            executorService.submit(lw[i]);
            i++;
        }

        executorService.shutdownNow();
        while (!executorService.isTerminated()) {
        }

        int winner = 0;
        for (int j = 0; j < t; j++) {
            String txt = lw[j].getName() + ": \nSuccess: " + lw[j].getSuccess() + " \nFailure: " + lw[j].getFailure() + " \nFinished in " + lw[j].getRuntime() + "\n";
            System.out.println(txt);
            output.setText(output.getText() + "\n" + txt);

            if (lw[j].getSuccess() > lw[winner].getSuccess()) {
                winner = j;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("The winner is " + lw[winner].getName() + " with " + lw[winner].getSuccess() + " edges");
        System.out.println("Total Edges Created: " + coArr.getEdge().size());
        output.setText(output.getText() + "\nThe winner is " + lw[winner].getName() + " \nwith " + lw[winner].getSuccess() + " edges");

    }
    
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            // s is a valid integer
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            // s is a valid integer
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
