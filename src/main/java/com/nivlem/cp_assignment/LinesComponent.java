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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import javax.swing.JComponent;

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

//public void addLine(double x1, double x2, double x3, double x4) {
//    addLine(x1, x2, x3, x4, Color.black);
//}

// method to add line
public void addLine(double x1, double x2, double x3, double x4, Color color) {
    lines.add(new Line(x1,x2,x3,x4, color)); 
    drawLine(getGraphics(), x1, x2, x3, x4, color);
    repaint();
}

// draw the line with specific color
public void drawLine(Graphics g, double x1, double x2, double x3, double x4, Color color) {
    Graphics2D g2g = (Graphics2D) g;
    for (Line line : lines) {
        g2g.setColor(line.color);
        g2g.setStroke(new BasicStroke(3)); //set thickness of lines
        g2g.draw(new Line2D.Float((float)line.x1, (float)line.y1, (float)line.x2, (float)line.y2));
    }
}

// remove all line during rest
public void clearLines() {
    lines.clear();
    repaint();
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
}