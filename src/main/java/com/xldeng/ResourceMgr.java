package com.xldeng;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author 邓鑫林
 * @since 2020/8/6 17:46
 */
public class ResourceMgr {
    public static BufferedImage goodTankUp, goodTankDown, goodTankLeft, goodTankRight;
    public static BufferedImage badTankUp, badTankDown, badTankLeft, badTankRight;
    public static BufferedImage bulletUp, bulletDown, bulletLeft, bulletRight;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            goodTankUp = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream(
                    "images/GoodTank1.png")));
            goodTankDown = ImageUtils.rotateImage(goodTankUp, 180);
            goodTankLeft = ImageUtils.rotateImage(goodTankUp, -90);
            goodTankRight = ImageUtils.rotateImage(goodTankUp, 90);

            badTankUp = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream(
                    "images/BadTank1.png")));
            badTankDown = ImageUtils.rotateImage(badTankUp, 180);
            badTankLeft = ImageUtils.rotateImage(badTankUp, -90);
            badTankRight = ImageUtils.rotateImage(badTankUp, 90);

            bulletUp = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream(
                    "images/bulletU.png")));
            bulletDown = ImageUtils.rotateImage(bulletUp,180);
            bulletLeft = ImageUtils.rotateImage(bulletUp,-90);
            bulletRight = ImageUtils.rotateImage(bulletUp,90);

            for (int i = 0; i < 16; i++) {
                explodes[i] =
                        ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream(
                                "images/e" + (i + 1) + ".gif")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}