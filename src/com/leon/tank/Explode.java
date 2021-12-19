package com.leon.tank;

import java.awt.*;

public class Explode {
    private int x;
    private int y;
    private TankFrame tankFrame;
    final static int WIDTH=ResouceMgr.explodes[0].getWidth();
    final static int HEIGHT=ResouceMgr.explodes[0].getHeight();
    private int step =0;
    public Explode(int x, int y,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame=tankFrame;
    }

    public void paint(Graphics g){
        g.drawImage(ResouceMgr.explodes[step++],x,y,null);
        if(step>=ResouceMgr.explodes.length) tankFrame.explodes.remove(this);
    }

}
