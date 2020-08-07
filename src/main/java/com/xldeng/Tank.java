package com.xldeng;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author 邓鑫林
 * @since 2020/8/6 14:10
 */
public class Tank {
    /** 坦克宽度和高度 **/
    public static final Integer WIDTH = ResourceMgr.goodTankUp.getWidth();
    public static final Integer HEIGHT = ResourceMgr.goodTankUp.getHeight();
    /** 坦克速度 **/
    private static final Integer SPEED = PropertyMgr.getInt("tankSpeed");
    /** 坦克位置 **/
    private Integer x, y;
    /** 坦克方向 **/
    private Dir dir = Dir.DOWN;
    /** 是否移动 **/
    private boolean moving = true;
    /** 坦克存活 **/
    private boolean live = true;
    /** 碰撞检测矩形 **/
    private Rectangle tankRect;

    private Random random = new Random();

    private Group group = Group.BAD;
    private TankFrame tankFrame;

    private FireStrategy fireStrategy;

    public Tank(Integer x, Integer y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
        tankRect = new Rectangle(x, y, WIDTH, HEIGHT);


        if (this.group == Group.GOOD) {
            fireStrategy = PropertyMgr.getInstance("goodTankFireStrategy", FireStrategy.class);
        } else {
            fireStrategy = PropertyMgr.getInstance("badTankFireStrategy", FireStrategy.class);
        }

    }

    public void paint(Graphics g) {
        if (!live) {
            tankFrame.tanks.remove(this);
        }
        BufferedImage tankImage = null;
        switch (dir) {
            case RIGHT:
                tankImage = this.group == Group.GOOD ? ResourceMgr.goodTankRight : ResourceMgr.badTankRight;
                break;
            case LEFT:
                tankImage = this.group == Group.GOOD ? ResourceMgr.goodTankLeft : ResourceMgr.badTankLeft;
                break;
            case UP:
                tankImage = this.group == Group.GOOD ? ResourceMgr.goodTankUp : ResourceMgr.badTankUp;
                break;
            case DOWN:
                tankImage = this.group == Group.GOOD ? ResourceMgr.goodTankDown : ResourceMgr.badTankDown;
                break;
            default:
                break;
        }
        g.drawImage(tankImage, x, y, null);
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
        if (group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }

        if (group == Group.BAD && random.nextInt(100) > 95) {
            this.randomDir();
        }

        boundCheck();
        tankRect.x = x;
        tankRect.y = y;
    }

    private void boundCheck() {
        if (this.x < 0) {
            this.x = 0;
        } else if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) {
            this.x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        } else if (this.y < Tank.HEIGHT) {
            this.y = Tank.HEIGHT;
        } else if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) {
            this.y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
        }

    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Rectangle getTankRect() {
        return tankRect;
    }

    public void setTankRect(Rectangle tankRect) {
        this.tankRect = tankRect;
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

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    /**
     * @Description: 坦克开火
     * @Author: xldeng
     * @Date: 2020/8/6 18:41
     * @return: void
     **/
    public void fire() {
        fireStrategy.fire(this);
    }

    public void die() {
        live = false;
    }

    private void randomDir() {
        dir = Dir.values()[random.nextInt(4)];
    }
}