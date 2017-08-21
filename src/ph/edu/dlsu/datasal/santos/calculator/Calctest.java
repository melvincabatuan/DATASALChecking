/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.santos.calculator;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

public class Calctest extends JFrame {

    public Calctest(){

        initComponents();

    }

    private void initComponents(){

        JPanel panelScreen = new JPanel(new GridLayout(0,1));

        JTextArea screen = new JTextArea();
        panelScreen.add(screen);

        JFrame frame = new JFrame("CALCULATOR");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelButtons = new JPanel(new GridLayout(3,3));

        JButton oneButton = new JButton("1");
        panelButtons.add(oneButton);

        JButton twoButton = new JButton("2");
        panelButtons.add(twoButton);

        JButton threeButton = new JButton("3");
        panelButtons.add(threeButton);

        JButton fourButton = new JButton("4");
        panelButtons.add(fourButton);

        JButton fiveButton = new JButton("5");
        panelButtons.add(fiveButton);

        JButton sixButton = new JButton("6");
        panelButtons.add(sixButton);

        JButton sevenButton = new JButton("7");
        panelButtons.add(sevenButton);

        JButton eightButton = new JButton("8");
        panelButtons.add(eightButton);

        JButton nineButton = new JButton("9");
        panelButtons.add(nineButton);

        frame.getContentPane().add(panelScreen, BorderLayout.NORTH);
        //frame.getContentPane().add(new JSeparator(), BorderLayout.CENTER);
        frame.getContentPane().add(panelButtons, BorderLayout.SOUTH);
        frame.setBounds(50, 50, 500, 500);
        frame.setResizable(false);
        //frame.pack();
        frame.setVisible(true);


    }


    public static void main(String[] args) {
        new Calctest();
    }
}