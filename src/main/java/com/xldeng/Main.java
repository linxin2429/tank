package com.xldeng;

/**
 * @author 邓鑫林
 * @since 2020/8/5 21:36
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        //敌方坦克初始化
        for (int i = 0; i < 5; i++) {
            tankFrame.tanks.add(new Tank(i * 200,50 + 50 * i,Dir.DOWN,Group.BAD, tankFrame));
        }

        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}