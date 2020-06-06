package com.nivlem.cp_assignment;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andre
 */
public class Result extends JFrame{
    
    JPanel main;
    JLabel announce;
    
    public Result (String winner, int edges)
    {
        setTitle("And the winner is...");
        setSize(500, 250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        main = new JPanel();
        main.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        String announcement;
        announcement = "THE WINNER IS " + winner + " WITH " + edges + " EDGES!";
        Font font = new Font("Jokerman", Font.PLAIN, 20);
        announce = new JLabel(announcement, SwingConstants.CENTER);
        announce.setFont(font);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.ipady = 100;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        main.add(announce, c);
        
        add(main);
    }
    
}
