package com.xldeng;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author 邓鑫林
 * @since 2020/8/6 14:40
 */
public class Bullet {
    /** 子弹速度 **/
    private static final Integer SPEED = 10;
    /** 子弹大小 **/
    public static final Integer WIDTH = ResourceMgr.bulletUp.getWidth();
    public static final Integer HEIGHT =ResourceMgr.bulletUp.getHeight();
    /** 子弹位置 **/
    private Integer x, y;
    /** 子弹方向 **/
    private Dir dir;
    /** 子弹存活 **/
    private boolean live = true;

    private TankFrame tankFrame;

    public Bullet(Integer x, Integer y, Dir dir,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }
    /**
     * @Description: 绘制子弹
     * @Author: xldeng
     * @Date: 2020/8/6 18:34
     * @param g:
     * @return: void
     **/
    public void paint(Graphics g) {
        if (!live){
            tankFrame.bullets.remove(this);
        }
        BufferedImage bulletImage = null;
        switch (dir){
            case RIGHT:
                bulletImage = ResourceMgr.bulletRight;
                break;
            case LEFT:
                bulletImage = ResourceMgr.bulletLeft;
                break;
            case UP:
                bulletImage = ResourceMgr.bulletUp;
                break;
            case DOWN:
                bulletImage = ResourceMgr.bulletDown;
                break;
            default:
                break;
        }
        g.drawImage(bulletImage,x,y,null);
        move();
    }
    /**
     * @Description: 子弹移动
     * @Author: xldeng
     * @Date: 2020/8/6 18:35

     * @return: void
     **/
    private void move() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }

        if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT){
            live = false;
        }
    }
    /**
     * @Description: 子弹和坦克碰撞检测
     * @Author: xldeng
     * @Date: 2020/8/6 18:35
     * @param tank:
     * @return: boolean
     **/
    public boolean collideWith(Tank tank) {
        Rectangle bulletRect = new Rectangle(x,y,WIDTH,HEIGHT);
        Rectangle tankRect = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if (bulletRect.intersects(tankRect)){
            tank.die();
            this.die();
        }
        return false;
    }

    private void die() {
        live = false;
    }
}