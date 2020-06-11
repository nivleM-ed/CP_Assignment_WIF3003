/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Melvin
 */
public class CheckInput extends JFrame {

    JPanel cont;
    JLabel msg;
    JButton ok;

    String text;

    public CheckInput(String type) {
        setTitle("WARNING");
        setSize(300, 200);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if(type == "checkNum"){
            text = "<html>" + 
                "<p>INVALID INPUT</p>"+
                "<p>Coordinates: Must be an Integer.</p>"+
                "<p>Threads/Players: Must be an Integer.</p>" + 
                "<p>Time: Must be a Double.</p>" +
                "</html>";
        } else {
            text = "<html>" + 
                "<p>All fields are required.</p>" + 
                "</html>";
        }


        msg = new JLabel(text);
        c.fill = GridBagConstraints.CENTER;
        c.ipady = 20;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        add(msg, c);

        ok = new JButton("Ok");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               dispose();
            }
        });
        c.fill = GridBagConstraints.CENTER;
        c.ipady = 10;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        add(ok, c);
    }
}