package com.mycompany.myprojectgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mudeng extends JPanel implements ActionListener {
    private int x;
    private int y;
    private boolean isJumping = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private Timer timer;
    private Mygame game;

    private Image[] leftImages;
    private Image[] rightImages;
    private Image currentImage;
    private boolean lastDirectionLeft = false;
    private int currentFrame = 0;
    private int frameDelay = 5;
    private int frameCounter = 0;
    private int gravity = 2;
    private int jumpSpeed = 50;

    public Mudeng(Mygame game, int startX, int startY) {
        this.game = game;
        this.x = startX;
        this.y = startY;
        loadImages();
        currentImage = rightImages[0];

        timer = new Timer(20, this);
        timer.start();
    }

    private void loadImages() {
        leftImages = new Image[] {
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\StandingLeft.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengLeft2.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengLeft4.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengLeft5.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengLeft6.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengLeft7.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengLeft8.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengLeft9.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengLeft10.png").getImage()
        };

        rightImages = new Image[] {
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengRight1.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengRight2.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengRight3.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengRight4.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengRight5.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengRight6.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengRight7.png").getImage(),
            new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\MudengRight8.png").getImage()
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            currentFrame = (currentFrame + 1) % (moveLeft ? leftImages.length : rightImages.length);
            currentImage = moveLeft ? leftImages[currentFrame] : rightImages[currentFrame];
        }

        // เคลื่อนไหว
        if (moveLeft) {
            x -= 5;
            if(x<0){
                x=1;
            }
        }
        if (moveRight) {
            x += 10;
        }

        // การกระโดด
        if (isJumping) {
            y += gravity;
            if (y >= 650) { // ปรับตำแหน่ง y เมื่อถึงพื้น
                y = 650;
                isJumping = false;
            }
        }

        // หันหน้าไปทางทิศทางล่าสุด
        if (!moveLeft && !moveRight) {
            currentImage = lastDirectionLeft ? leftImages[0] : rightImages[0];
        }

        game.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(currentImage, x, y, 100, 100, this); // กำหนดขนาด Mudeng เป็น 100x100
    }

    public void stopMoving() {
        moveLeft = false;
        moveRight = false;
        currentFrame = 0; // รีเซ็ตเฟรมเมื่อหยุดเคลื่อนไหว
        currentImage = lastDirectionLeft ? leftImages[0] : rightImages[0];
    }

    public void moveLeft() {
        moveLeft = true;
        moveRight = false;
        lastDirectionLeft = true;
    }

    public void moveRight() {
        moveRight = true;
        moveLeft = false;
        lastDirectionLeft = false;
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            y -= jumpSpeed;
        }
    }
}

