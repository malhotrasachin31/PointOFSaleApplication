/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pointofsale;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author dell
 */
public class about extends JFrame implements ActionListener
{
    about(){
        
        ImageIcon img=new ImageIcon(ClassLoader.getSystemResource("images/aboutwindow.png"));
        Image img2=img.getImage().getScaledInstance(400, 140, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(img2);
        JLabel l1=new JLabel(i3);
        l1.setBounds(150,40,400,140);
        add(l1);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("images/pos.jpg"));
        Image i2=i1.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon img3=new ImageIcon(i2);
        JLabel l2=new JLabel(img3);
        l2.setBounds(50,180,70,70);
        add(l2);
        
        JLabel l3=new JLabel("<html>malhotra-engineers<br>Version 1.0.0<br>All Rights Reserved<br>The applications for POI are extensive so too the applications for POI are also expanding. Geocaching applications are built around POI collections. In vehicle tracking systems, POIs are used to mark destination points and/or offices to that users of GPS tracking software would easily monitor position of vehicles according to POIs.</html>");
        l3.setBounds(150,96,500,300);
        l3.setFont(new Font("san-serif",Font.BOLD,18));
        add(l3);
        
        JButton b1=new JButton("ok");
        b1.setBounds(580,500,90,20);
        b1.setFocusable(false);
        b1.addActionListener(this);
        add(b1);
        

        
       
        setBounds(600,200,700,600);
        setLayout(null);
        getContentPane().setBackground(Color.white);
    }
    
        
    public static void main(String args[])
    {
        new about().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }
}
