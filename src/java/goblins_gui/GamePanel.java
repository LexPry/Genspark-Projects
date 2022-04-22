package goblins_gui;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
    {
    // screen settings
    final int originalTileSize = 16; // 16 x 16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // FPS
    int fps = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    // Set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel()
        {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        }

    public void startGameThread()
        {
        gameThread = new Thread(this);
        gameThread.start();
        }


    /**
     * Sleep method for updating game
     * uses System.nanoTime() Nanoseconds to track time.
     */
//    @Override
//    public void run()
//        {
//        double drawInterval = 1_000_000_000 / fps; // 0.016666 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while (gameThread != null)
//            {
//            long currentTime = System.nanoTime();
//
//
//            // TODO update information
//            update();
//            // TODO draw the screen with updated information
//            repaint();
//
//
//            try
//                {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1_000_000;
//
//                if (remainingTime < 0)
//                    {
//                    remainingTime = 0;
//                    }
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//
//                } catch (InterruptedException e)
//                {
//                e.printStackTrace();
//                }
//            }
//        }

    public void run()
        {
        double drawInterval = 1_000_000_000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null)
            {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1)
                {
                update();
                repaint();
                delta--;
                drawCount++;
                }

            if (timer >= 1000000000)
                {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
                }
            }
        }

    public void update()
        {
        if (keyHandler.upPressed)
            {
            playerY -= playerSpeed;
            } else if (keyHandler.downPressed)
            {
            playerY += playerSpeed;
            } else if (keyHandler.leftPressed)
            {
            playerX -= playerSpeed;
            } else if (keyHandler.rightPressed)
            {
            playerX += playerSpeed;
            }
        }

    @Override
    public void paintComponent(Graphics g)
        {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.CYAN);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
        }
    }
