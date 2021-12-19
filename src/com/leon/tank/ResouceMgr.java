package com.leon.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResouceMgr {
    static BufferedImage goodTankBl,goodTankBu,goodTankBr,goodTankBd;
    static BufferedImage badTankBl,badTankBu,badTankBr,badTankBd;
    static BufferedImage bulletBl,bulletBu,bulletBr,bulletBd;
    static BufferedImage[] explodes=new BufferedImage[16];

    static {
        try {
            goodTankBu= ImageIO.read(ResouceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankBl= ImageUtil.rotateImage(goodTankBu,-90);
            goodTankBr= ImageUtil.rotateImage(goodTankBu,90);
            goodTankBd= ImageUtil.rotateImage(goodTankBu,180);

            badTankBu= ImageIO.read(ResouceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankBl= ImageUtil.rotateImage(badTankBu,-90);
            badTankBr= ImageUtil.rotateImage(badTankBu,90);
            badTankBd= ImageUtil.rotateImage(badTankBu,180);

            bulletBu=ImageIO.read(ResouceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletBl=ImageIO.read(ResouceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletBr=ImageIO.read(ResouceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletBd=ImageIO.read(ResouceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

            for (int i = 0; i < explodes.length ; i++) {
                explodes[i]=ImageIO.read(ResouceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
