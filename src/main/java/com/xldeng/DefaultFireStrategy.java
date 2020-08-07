package com.xldeng;

/**
 * @author 邓鑫林
 * @since 2020/8/7 10:03
 */
public class DefaultFireStrategy implements FireStrategy{

    @Override
    public void fire(Tank tank) {
        Integer bulletx = tank.getX() + (Tank.WIDTH >> 1) - (Bullet.WIDTH >> 1);
        Integer bullety = tank.getY() + (Tank.HEIGHT >> 1) - (Bullet.HEIGHT >> 1);
        new Bullet(bulletx, bullety, tank.getDir(), tank.getGroup(), tank.getTankFrame());
    }
}