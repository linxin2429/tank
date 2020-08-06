package com.xldeng;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author 邓鑫林
 * @since 2020/8/6 14:10
 */
public class Tank {
    /** 坦克速度 **/
    private static final Integer SPEED = 5;
    /** 坦克位置 **/
    private Integer x, y;
    /** 坦克宽度和高度 **/
    public static final Integer WIDTH = ResourceMgr.tankUp.getWidth();
    public static final Integer HEIGHT =ResourceMgr.tankUp.getHeight();
    /** 坦克方向 **/
    private Dir dir = Dir.DOWN;
    /** 是否移动 **/
    private boolean moving = true;
    /** 坦克存活 **/
    private boolean live = true;

    private Random random = new Random();

    private Group group = Group.BAD;
    private TankFrame tankFrame;

    public Tank(Integer x, Integer y, Dir dir,Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        if (!live){
            tankFrame.tanks.remove(this);
        }
        BufferedImage tankImage = null;
        switch (dir){
            case RIGHT:
                tankImage = ResourceMgr.tankRight;
                break;
            case LEFT:
                tankImage = ResourceMgr.tankLeft;
                break;
            case UP:
                tankImage = ResourceMgr.tankUp;
                break;
            case DOWN:
                tankImage = ResourceMgr.tankDown;
                break;
            default:
                break;
        }
        g.drawImage(tankImage,x,y,null);
        move();
    }

    private void move() {
        if (!moving) {
            return;
        }
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
        if (group == Group.BAD && random.nextInt(10) > 8) {
            this.fire();
        }
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }


    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    /**
     * @Description: 坦克开火
     * @Author: xldeng
     * @Date: 2020/8/6 18:41

     * @return: void
     **/
    public void fire() {
        Integer bulletx =x + (WIDTH >> 1) -(Bullet.WIDTH >> 1);
        Integer bullety =y + (HEIGHT >> 1) -(Bullet.HEIGHT >> 1);
        tankFrame.bullets.add(new Bullet(bulletx,bullety,dir,this.group, this.tankFrame));
    }

    public void die() {
        live = false;
    }
}