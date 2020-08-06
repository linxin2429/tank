package com.xldeng.test;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author 邓鑫林
 * @since 2020/8/6 17:18
 */
public class TestImage {
    @Test
    public void test1() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(TestImage.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
        Assert.assertNotNull(bufferedImage);
    }
}