package com.xldeng;

import java.awt.*;

/**
 * @author 邓鑫林
 * @since 2020/8/6 21:14
 */
public class Explode {

    /** 爆炸大小 **/
    public static final Integer WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final Integer HEIGHT =ResourceMgr.explodes[0].getHeight();
    /** 爆炸位置 **/
    private final Integer x;
    private final Integer y;

    private Integer step = 0;


    private final TankFrame tankFrame;

    public Explode(Integer x, Integer y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step >= ResourceMgr.explodes.length){
            tankFrame.explodes.remove(this);
        }
    }
}