package com.mycompany.myprojectgame;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Event {
    private int x, y; // ตำแหน่งกับดักอันแรก
    private int x2, y2; // ตำแหน่งกับดักอันที่สอง
    private Image trapImage; // ภาพกับดักอันแรก
    private Image trapImage2; // ภาพกับดักอันที่สอง
    private Rectangle blackBox1; // กล่องสีดำอันแรก
    private Rectangle blackBox2; // กล่องสีดำอันที่สอง

    public Event(int x, int y, int x2, int y2, String imagePath1, String imagePath2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        
        blackBox1 = new Rectangle(310, 625, 320, 100); // กล่องสีดำอันแรก
        blackBox2 = new Rectangle(910, 660, 50, 50);
        loadImages(imagePath1, imagePath2); 
    }

    private void loadImages(String path1, String path2) {
        try {
            trapImage = ImageIO.read(new File(path1));
            trapImage2 = ImageIO.read(new File(path2)); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        if (trapImage != null) {
            g.drawImage(trapImage, x, y, 200, 200, null); 
        }
        if (trapImage2 != null) {
            g.drawImage(trapImage2, x2, y2, 400, 400, null); 
        }
        g.setColor(Color.BLACK);
            g.fillRect(blackBox1.x, blackBox1.y, blackBox1.width, blackBox1.height);
            g.fillRect(blackBox2.x, blackBox2.y, blackBox2.width, blackBox2.height);
    }
}

