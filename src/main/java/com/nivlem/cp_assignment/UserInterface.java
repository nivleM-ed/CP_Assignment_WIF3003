<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
=======
>>>>>>> fd624187fde7b319719c84213fae30ca8960b39c
package com.nivlem.cp_assignment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andre
 */
public class UserInterface extends JFrame implements ActionListener {
    
    boolean submit = false;

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        status.setText("Game is starting...");
        submit = true;
    }
    
    private final JPanel container, points_panel, time_panel, threads_panel, button_panel;
    private final JLabel points, time, threads, status;
    private final JButton goButton;
    private final JTextField points_input, time_input, threads_input;
    
    public UserInterface ()
    {
        //Main panel = container, Sub panels = points_panel, time_panel, threads_panel, button_panel
        container = new JPanel();
        points_panel = new JPanel();
        time_panel = new JPanel();
        threads_panel = new JPanel();
        button_panel = new JPanel();
        //Set main panel and add sub panels to it
        //container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(points_panel);
        container.add(time_panel);
        container.add(threads_panel);
        container.add(button_panel);
        //Button(s)
        goButton = new JButton("GO");
        goButton.addActionListener(this);
        //Label(s)
        status = new JLabel("Click GO to Start");
        points = new JLabel("Points");
        time = new JLabel("Time");
        threads = new JLabel("Threads");
        //Add button and label to button_panel
        button_panel.add(goButton);
        button_panel.add(status);
        //Text fields
        points_input = new JTextField();
        points_input.setColumns(15);
        time_input = new JTextField();
        time_input.setColumns(15);
        threads_input = new JTextField();
        threads_input.setColumns(15);
        //Add labels and text fields to points_panel, time_panel and threads_panel
        points_panel.add(points);
        points_panel.add(points_input);
        time_panel.add(time);
        time_panel.add(time_input);
        threads_panel.add(threads);
        threads_panel.add(threads_input);
        
        add(container, BorderLayout.WEST);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Concurrent Programmed Game");
        pack();
        setVisible(true);
    }
    
    public int getN()
    {
        return parseInt(points_input.getText());
    }
    
    public int getT()
    {
        return parseInt(threads_input.getText());
    }
    
    public int getM()
    {
        return parseInt(time_input.getText());
    }
    
    public boolean getSubmit()
    {
        return submit;
    }

}
