package com.xldeng;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 邓鑫林
 * @since 2020/8/5 23:46
 */
public class TankFrame extends Frame {
    /** 游戏界面 **/
    public static final Integer GAME_WIDTH = 800, GAME_HEIGHT = 600;
    /** 我方坦克 **/
    Tank myTank = new Tank(200, 200, Dir.DOWN,Group.GOOD, this);
    /** 敌方坦克 **/
    List<Tank> tanks = new ArrayList<>();
    /** 炮弹 **/
    List<Bullet> bullets = new ArrayList<>();


    Image offScreenImage = null;

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("敌方坦克数量："+ tanks.size(),10,60);
        g.drawString("子弹数量："+ bullets.size(),10,80);


        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
//        for (Bullet bullet : bullets) {
//            bullet.paint(g);
//        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
//                bullets.get(i).collideWith(myTank);
            }
        }
    }

    //解决闪烁问题
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * @Description: 处理键盘事件监听
     * @Author: xldeng
     * @Date: 2020/8/6 10:11
     **/
    class MyKeyListener extends KeyAdapter {
        private boolean left = false;
        private boolean right = false;
        private boolean up = false;
        private boolean down = false;


        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    left = true;
                    break;
                case KeyEvent.VK_UP:
                    up = true;
                    break;
                case KeyEvent.VK_DOWN:
                    down = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;

                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    left = false;
                    break;
                case KeyEvent.VK_UP:
                    up = false;
                    break;
                case KeyEvent.VK_DOWN:
                    down = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    right = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!left && !right && !up && !down) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (left) {
                    myTank.setDir(Dir.LEFT);
                }
                if (right) {
                    myTank.setDir(Dir.RIGHT);
                }
                if (down) {
                    myTank.setDir(Dir.DOWN);
                }
                if (up) {
                    myTank.setDir(Dir.UP);
                }
            }
        }
    }
}