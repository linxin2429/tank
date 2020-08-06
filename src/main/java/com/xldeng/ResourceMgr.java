package com.xldeng;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author 邓鑫林
 * @since 2020/8/6 17:46
 */
public class ResourceMgr {
    public static BufferedImage tankUp,tankDown,tankLeft,tankRight;
    public static BufferedImage bulletUp,bulletDown,bulletLeft,bulletRight;
    static {
        try {
            tankUp = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream(
                    "images/tankU.gif")));
            tankDown = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream(
                    "images/tankD.gif")));
            tankLeft = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream(
                    "images/tankL.gif")));
            tankRight = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream(
                    "images/tankR.gif")));
            bulletUp = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream(
                    "images/bulletU.gif")));
            bulletDown = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream(
                    "images/bulletD.gif")));
            bulletLeft = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream(
                    "images/bulletL.gif")));
            bulletRight = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream(
                    "images/bulletR.gif")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}