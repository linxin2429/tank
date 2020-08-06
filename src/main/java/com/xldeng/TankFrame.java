package com.xldeng;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author 邓鑫林
 * @since 2020/8/5 23:46
 */
public class TankFrame extends Frame {
    Tank myTank = new Tank(200, 200, Dir.DOWN);

    public TankFrame() {
        setSize(800, 600);
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
        myTank.paint(g);

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