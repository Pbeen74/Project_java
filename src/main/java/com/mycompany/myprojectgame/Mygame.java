package com.mycompany.myprojectgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mygame extends JFrame {
    private Monk monk; // ตัวละคร Monk
    private Mudeng mudeng; // ตัวละคร Mudeng
    private BufferedImage backgroundImage; // ภาพพื้นหลัง
    public int currentPage; // ตัวแปรเก็บเลขหน้าปัจจุบัน
    private BufferedImage foodImage;
    private BufferedImage heartImage;
    private BufferedImage banditImage;

    private Status status;  // ฟิลด์ status
    private Event event;    // ฟิลด์ event
    
    
    
    

    public Mygame() {
        setTitle("Protect Mudeng");
        setSize(1039, 813);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        currentPage = 2; // ตั้งค่าเริ่มต้นให้เป็น page1
        
        //Event trap
        event = new Event(850, 600, 300, 500, "C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\trab1.png", "C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\trab2.png");
        status = new Status(this, event);  // สร้างอ็อบเจ็กต์ของ Status
        
        try {
            foodImage = ImageIO.read(new File("C:/Users/asus/OneDrive/Documents/NetBeansProjects/Myprojectgame/src/main/java/com/mycompany/myprojectgame/food.png"));
            heartImage = ImageIO.read(new File("C:/Users/asus/OneDrive/Documents/NetBeansProjects/Myprojectgame/src/main/java/com/mycompany/myprojectgame/hearth.png"));
            banditImage = ImageIO.read(new File("C:/Users/asus/OneDrive/Documents/NetBeansProjects/Myprojectgame/src/main/java/com/mycompany/myprojectgame/bad1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // สร้าง JPanel สำหรับแสดงภาพพื้นหลังและตัวละคร
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
                monk.paintComponent(g); // วาดตัวละคร Monk บน panel
                if (currentPage != 3) { // ตรวจสอบว่าไม่ใช่ page3
                    mudeng.paintComponent(g); // วาดตัวละคร Mudeng บน panel ถ้าไม่ใช่ page3
                }
                if (currentPage == 3 ) {
                    event.paintComponent(g); // วาดกับดักอันแรก
                    status.paintComponent(g);
                }
                 if (currentPage == 4){
                    status.paintComponent(g); //heart
                    g.drawImage(banditImage,800,520,250,250,null); //bandit
                }
            } 
            
        };

        panel.setOpaque(false); // ตั้งค่าให้ panel โปร่งใส
        panel.setLayout(null);

        int startX = 2;
        int startY = 500;
        

        monk = new Monk(this, startX, startY);
        mudeng = new Mudeng(this, startX-1, startY+150);  // สร้าง Mudeng ที่อยู่ข้างหลัง Monk

        monk.setBounds(startX, startY, 0, 0);
        mudeng.setBounds(startX - 2, startY+150, 0, 0);
        panel.add(monk);
        panel.add(mudeng);

        setContentPane(panel);

        // โหลดภาพพื้นหลังแรกที่ใช้ใน Mygame
        loadBackgroundImage("C:/Users/asus/OneDrive/Documents/NetBeansProjects/Myprojectgame/src/main/java/com/mycompany/myprojectgame/page2.png");

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_A -> {
                        monk.moveLeft();
                        mudeng.moveLeft();
                    }
                    case KeyEvent.VK_D -> {
                        monk.moveRight();
                        mudeng.moveRight();
                    }
                    case KeyEvent.VK_SPACE -> {
                        monk.jump();
                        mudeng.jump(); // เพิ่มให้ Mudeng กระโดดด้วย
                    }
                    case KeyEvent.VK_ENTER->{
                        monk.childGunRight();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
                    monk.stopMoving();
                    mudeng.stopMoving(); // หยุด Mudeng เมื่อปล่อยปุ่ม
                }
            }
            
        });
        this.setFocusable(true);
    }

    // เมธอดสำหรับโหลดภาพพื้นหลัง
    private void loadBackgroundImage(String path) {
        try {
            backgroundImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // เมธอดสำหรับเปลี่ยนไปยังด่านถัดไป
    public void nextLevel() {
        currentPage = 3; // ตั้งค่าหน้าเป็น page3
        loadBackgroundImage("C:/Users/asus/OneDrive/Documents/NetBeansProjects/Myprojectgame/src/main/java/com/mycompany/myprojectgame/page2.png");
        monk.setPosition(1, 500); // รีเซ็ตตำแหน่ง monk
        repaint(); // วาดใหม่เพื่อแสดงภาพพื้นหลังใหม่
    }
    
    public void nextLevel2() {
        currentPage = 4; // ตั้งค่าหน้าเป็น page3
        loadBackgroundImage("C:/Users/asus/OneDrive/Documents/NetBeansProjects/Myprojectgame/src/main/java/com/mycompany/myprojectgame/page2.png");
        monk.setPosition(1, 500); // รีเซ็ตตำแหน่ง monk
        repaint(); // วาดใหม่เพื่อแสดงภาพพื้นหลังใหม่
    }
    
    public void nextLevel3()
    {
        loadBackgroundImage("C:/Users/asus/OneDrive/Documents/NetBeansProjects/Myprojectgame/src/main/java/com/mycompany/myprojectgame/End.png");
        repaint();
    }

    // เพิ่มเมธอด getStatus()
    public Status getStatus() {
        return this.status;
    }

    // เพิ่มเมธอด getEvent()
    public Event getEvent() {
        return this.event;
    }

    public static void main(String[] args) {
        menu frame = new menu();
        frame.setVisible(true);
    }
}
