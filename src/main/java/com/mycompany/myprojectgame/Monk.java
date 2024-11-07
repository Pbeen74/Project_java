package com.mycompany.myprojectgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Monk extends JPanel implements ActionListener   {
    private int x;
    private int y;
    private boolean isJumping = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private Timer timer;
    private Mygame game;
    private Status status;

    private static Image[] leftImages;  // ภาพอนิเมชันสำหรับเดินซ้าย
    private static Image[] rightImages; // ภาพอนิเมชันสำหรับเดินขวา
    private static Image standingLeftImage, standingRightImage;
    private Image currentImage;

    private boolean lastDirectionLeft = false;
    private int currentFrame = 0;      // เฟรมปัจจุบันสำหรับอนิเมชัน
    private int frameDelay = 5;        // หน่วงการเปลี่ยนเฟรม (ยิ่งสูงยิ่งช้าลง)
    private int frameCounter = 0;      // ตัวนับเพื่อสลับเฟรม

    private int gravity = 2;
    private int jumpSpeed = 70;

    // Left-Right 4
    private static Image[] page4LeftImages;
    private static Image[] page4RightImages;
    
    //gun
    private boolean isShooting = false;  // เช็คสถานะการยิง
    private int health2 = 100;  // เลือดของเป้าหมาย
    private static Image bulletImage;  // ตัวแปรสำหรับเก็บรูปภาพของกระสุน
    private int bulletX = -1;  // ตัวแปรตำแหน่งของกระสุน
    private int bulletY = -1;  // ตัวแปรตำแหน่ง Y ของกระสุน
    private boolean isBulletFired = false;  

    public Monk(Mygame game, int startX, int startY) {
        this.game = game;
        this.x = startX;
        this.y = startY;
        this.status = status;
        loadImages();
        currentImage = standingRightImage;

        timer = new Timer(20, this);
        timer.start();
        
        
    }
    


    private static void loadImages() {
        if (leftImages == null) {
            // โหลดภาพเดินซ้าย 4 ภาพ
            leftImages = new Image[]{
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KruBarstandingLeft.png").getImage(),
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KruBarstandingLeft1png").getImage(),
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KruBarstandingLeft2.png").getImage(),
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KruBarstandingLeft3.png").getImage()
            };

            // โหลดภาพเดินขวา 4 ภาพ
            rightImages = new Image[]{
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KruBarstandingRight.png").getImage(),
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KruBarstandingRight1.png").getImage(),
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KruBarstandingRight2.png").getImage(),
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KruBarstandingRight3.png").getImage()
            };

            // โหลดภาพหยุดนิ่ง
            standingLeftImage = new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KruBarstandingLeft.png").getImage();
            standingRightImage = new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KruBarstandingRight.png").getImage();

            // โหลดภาพเดินสำหรับหน้า 4
            page4LeftImages = new Image[]{
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KrugunLeft1.png").getImage(),
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KrugunLeft2.png").getImage(),
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KrugunLeft3.png").getImage()
                
            };

            page4RightImages = new Image[]{
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KrugunRight1.png").getImage(),
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KrugunRight2.png").getImage(),
                new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KrugunRight3.png").getImage(),
            };
        }
        
        if (bulletImage == null) {
            bulletImage = new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\childgumRoght.png").getImage(); // ใส่เส้นทางของรูปภาพกระสุน
        }
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

    public void stopMoving() {
    moveLeft = false;
    moveRight = false;
    currentFrame = 0;  // รีเซ็ตเฟรมเมื่อหยุดเคลื่อนที่
    
    if (game.currentPage == 4) {  // ถ้าอยู่ในหน้า page4
        if (lastDirectionLeft) {
            currentImage = new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KrugunLeft1.png").getImage(); // รูปภาพถือปืนซ้าย
        } else {
            currentImage = new ImageIcon("C:\\Users\\asus\\OneDrive\\Documents\\NetBeansProjects\\Myprojectgame\\src\\main\\java\\com\\mycompany\\myprojectgame\\KrugunRight1.png").getImage(); // รูปภาพถือปืนขวา
        }
    } else {  // ถ้าไม่ใช่หน้า page4
        if (lastDirectionLeft) {
            currentImage = standingLeftImage; // ภาพยืนตรงหันซ้าย
        } else {
            currentImage = standingRightImage; // ภาพยืนตรงหันขวา
        }
    }
}


    public void jump() {
        if (!isJumping) {
            isJumping = true;
            y -= jumpSpeed;
        }
    }
    
        // 
    public void childGunRight() {
        if (game.currentPage == 4 && !isShooting) {
            isShooting = true;
            System.out.println("Firing the gun!");
            // 
            bulletX = x + 100; 
            bulletY = y + 50;  

            isBulletFired = true;
        }
    }
    
    
// ฟังก์ชันเคลื่อนที่กระสุน
    public void moveBullet() {
        if (isBulletFired) {
            bulletX += 10; // เพิ่มค่า x เพื่อให้กระสุนเคลื่อนที่ไปทางขวา

            // ตรวจสอบการชนกับเป้าหมาย (กำหนดขอบเขตที่กระสุนสามารถชนได้)
            if (bulletX >= 800 && bulletX <= 1050 && bulletY >= 520 && bulletY <= 770) {
                status.decreaseHealth(10); // ลดเลือดของเป้าหมาย
                isBulletFired = false; // หยุดการยิงกระสุน
            }

            // หากกระสุนเคลื่อนที่ออกจากหน้าจอ
            if (bulletX > getWidth()) {
                isBulletFired = false;  // หยุดการยิงกระสุนเมื่อมันออกจากหน้าจอ
            }
        }
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (moveLeft || moveRight) {
            frameCounter++;
            if (frameCounter >= frameDelay) {
                frameCounter = 0;
                if (moveLeft) {
                    if (game.currentPage == 4) {
                        currentFrame = (currentFrame + 1) % page4LeftImages.length;
                        currentImage = page4LeftImages[currentFrame];
                    } else {
                        currentFrame = (currentFrame + 1) % leftImages.length;
                        currentImage = leftImages[currentFrame];
                    }
                } else if (moveRight) {
                    if (game.currentPage == 4) {
                        currentFrame = (currentFrame + 1) % page4RightImages.length;
                        currentImage = page4RightImages[currentFrame];
                    } else {
                        currentFrame = (currentFrame + 1) % rightImages.length;
                        currentImage = rightImages[currentFrame];
                    }
                }
            }
        }

        // เคลื่อนที่
        if (moveLeft) {
            x -= 5;
            if (x <= 0) x = 4;
        }
        if (moveRight) {
            x += 5;
            if (x >= 900 && game.currentPage == 2) {
                game.nextLevel(); // เปลี่ยนเป็น page3
            } else if (x >= 900 && game.currentPage == 3) {
                game.nextLevel2(); // เปลี่ยนเป็น page4
            }
        }

        if (isJumping) {
            y += gravity;
            if (y >= 500) {
                y = 500;
                isJumping = false;
            }
        }
        
        game.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(currentImage, x, y, 300, 300, this); // วาดภาพขนาด 200x200
        
        if(isBulletFired)
        {
            g.drawImage(bulletImage,bulletX,bulletY,50,20,this);
        }
    }

    // set monk
    public void setPosition(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }
    
    
     public void paint(Graphics g) {
        super.paint(g);

        // ถ้ามีการยิงกระสุน
        if (isBulletFired) {
            g.setColor(Color.RED); // กำหนดสีของกระสุน
            g.fillRect(bulletX, bulletY, 10, 5); // วาดกระสุนเป็นสี่เหลี่ยม
        }
    }
}
 