/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nivlem.cp_assignment;

import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JTextArea;

/**
 *
 * @author melvi
 */
public class OutputPanel {
    private JTextArea output;
    private volatile String text = ""; 
    
    public OutputPanel(JTextArea output) {
        this.output = output;
    }
    
    public void appendText(String text) {
        System.out.println(text);
        this.text = this.text + "\n" + text;
        output.setText(this.text);
    }
}
