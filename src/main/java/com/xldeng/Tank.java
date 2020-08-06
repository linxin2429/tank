package com.xldeng;

import java.awt.*;

/**
 * @author 邓鑫林
 * @since 2020/8/6 14:10
 */
public class Tank {
    /** 坦克速度 **/
    private static final Integer SPEED = 10;
    /** 坦克位置 **/
    private Integer x, y;
    /** 坦克方向 **/
    private Dir dir = Dir.DOWN;
    /** 是否移动 **/
    private boolean moving = false;

    public Tank(Integer x, Integer y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);
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
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}