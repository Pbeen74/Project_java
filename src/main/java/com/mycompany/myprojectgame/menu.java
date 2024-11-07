package com.mycompany.myprojectgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class menu extends JFrame {
    public menu() {
        setTitle("Protect Mudeng");
        setSize(1039, 813); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);  

        try {
            BufferedImage image = ImageIO.read(new File("C:/Users/asus/OneDrive/Documents/NetBeansProjects/Myprojectgame/src/main/java/com/mycompany/myprojectgame/menu.jpg"));
            ImageIcon icon = new ImageIcon(image);
            JLabel label = new JLabel(icon);
            label.setBounds(0, 0, 1025, 785);  
            panel.add(label);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton startButton = new JButton();
        startButton.setBounds(410, 548, 290, 126);  
        startButton.setBackground(Color.RED); 
        startButton.setOpaque(true);  
        startButton.setContentAreaFilled(false); 
        startButton.setBorderPainted(false); 

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  
                
                Mygame gameWindow = new Mygame();
                gameWindow.setVisible(true);
            }
        });

        panel.add(startButton);
        setContentPane(panel);
    }
} 

