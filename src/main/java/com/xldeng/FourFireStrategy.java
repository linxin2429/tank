package com.xldeng;

/**
 * @author 邓鑫林
 * @since 2020/8/7 10:16
 */
public class FourFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        Integer bulletx = tank.getX() + (Tank.WIDTH >> 1) - (Bullet.WIDTH >> 1);
        Integer bullety = tank.getY() + (Tank.HEIGHT >> 1) - (Bullet.HEIGHT >> 1);
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            new Bullet(bulletx, bullety, dir, tank.getGroup(), tank.getTankFrame());
        }
    }
}