package com.mycompany.myprojectgame;

import javax.swing.*;
import java.awt.*;

public class Status extends JPanel {
    private int health = 100;  // เปลี่ยนจาก heartCount เป็น health เพื่อให้เลือดเริ่มต้นที่ 100
    private int health2 = 100;
    private Image[] heartImages = new Image[5];  // ถ้าต้องการภาพหัวใจยังคงใช้ 5 ภาพ
    private Image[] heartImages2 = new Image[5];
    private Image foodImage; // ภาพอาหาร
    private Mygame game;  
    private Event event;  

    public Status(Mygame game, Event event) {
        this.game = game;
        this.event = event;
        loadHeartImages(); 
        loadFoodImage(); 
        setBounds(10, 10, 550, 150); // ขนาดของ Status
        setOpaque(false); // ทำให้โปร่งใส
    }

    private void loadHeartImages() {
        for (int i = 0; i < 5; i++) {
            heartImages[i] = new ImageIcon("C:/Users/asus/OneDrive/Documents/NetBeansProjects/Myprojectgame/src/main/java/com/mycompany/myprojectgame/hearth.png").getImage();  // เปลี่ยน path ให้ตรงกับที่เก็บภาพหัวใจ
            heartImages2[i] = new ImageIcon("C:/Users/asus/OneDrive/Documents/NetBeansProjects/Myprojectgame/src/main/java/com/mycompany/myprojectgame/hearth.png").getImage();  // เปลี่ยน path ให้ตรงกับที่เก็บภาพหัวใจ
        
        }
    }

    private void loadFoodImage() {
        foodImage = new ImageIcon("C:/Users/asus/OneDrive/Documents/NetBeansProjects/Myprojectgame/src/main/java/com/mycompany/myprojectgame/food.png").getImage(); // เปลี่ยน path ของภาพอาหาร
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // monk
        for (int i = 0; i < 5; i++) {
            g.drawImage(heartImages[i], i * 30, 0, 100, 100, this);  // วาดหัวใจ
        }
        // bandit
        for (int i = 0; i < 5; i++) {
            g.drawImage(heartImages[i], 730+(1+i) * 30, 0, 100, 100, this); 
        }

        
        g.setColor(Color.BLACK); 
        g.setFont(new Font("Arial", Font.BOLD, 20)); 
        g.drawString("Health: " + health, 50, 80);  
        
        g.drawString("Health2: " + health2, 800, 80); 

       
        if (foodImage != null) {
            g.drawImage(foodImage, 10, 50, 150, 150, this); 
        }
    }

    // เมธอดลดเลือด
    public void decreaseHealth(int amount) {
        if (health2 > 0) {
            health2 -= amount; // ลดเลือดตามที่กำหนด
            if (health2 < 0) {
                health = 0;
                game.nextLevel3(); // ให้เลือดไม่ต่ำกว่า 0
            }
            repaint();  // รีเฟรชการแสดงผล
        }
    }

    
}

