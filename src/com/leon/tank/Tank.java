package com.leon.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x;
    private int y;

    private TankFrame tankFrame;
    private final static int SPEED=Integer.parseInt((String)PropertyMgr.get("tankSpeed"));
    private boolean moving=true;
    private boolean isAlive=true;
    private Group group=Group.BAD;
    Rectangle tankRc=new Rectangle();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    private Random random=new Random();
    final static int WIDTH=ResouceMgr.goodTankBu.getWidth();
    final static int HEIGHT=ResouceMgr.goodTankBu.getHeight();
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir,TankFrame tankFrame,Group group) {
        this.x = x;
        this.y = y;
        this.dir=dir;
        this.tankFrame=tankFrame;
        this.group=group;
        tankRc.x=this.x;
        tankRc.y=this.y;
        tankRc.width=WIDTH;
        tankRc.height=HEIGHT;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private Dir dir;

    public int getX() {
        return x;
    }

    public void paint(Graphics g) {
        if(!isAlive) tankFrame.enemies.remove(this);
        switch (dir){
            case LEFT:
                g.drawImage((this.getGroup()==Group.GOOD?ResouceMgr.goodTankBl:ResouceMgr.badTankBl),x,y,null);
                break;
            case UP:
                g.drawImage((this.getGroup()==Group.GOOD?ResouceMgr.goodTankBu:ResouceMgr.badTankBu),x,y,null);
                break;
            case RIGHT:
                g.drawImage((this.getGroup()==Group.GOOD?ResouceMgr.goodTankBr:ResouceMgr.badTankBr),x,y,null);
                break;
            case DOWN:
                g.drawImage((this.getGroup()==Group.GOOD?ResouceMgr.goodTankBd:ResouceMgr.badTankBd),x,y,null);
                break;
            default:break;
        }
        move();
    }

    private void move() {
        if(!moving) return;

        switch(dir){
            case LEFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
            default:
                break;
        }
        if(this.group == Group.BAD && random.nextInt(10)>8)
            this.fire();

        if(this.group == Group.BAD && random.nextInt(100)>95)
            randomDir();

        boundaryCheck();

        tankRc.x=this.x;
        tankRc.y=this.y;
    }

    private void boundaryCheck() {
        if(this.x<2) x=2;
        if(this.y<28) y=28;
        if(this.x> TankFrame.GAME_WIDTH-Tank.WIDTH-2) x=TankFrame.GAME_WIDTH-Tank.WIDTH-2;
        if(this.y> TankFrame.GAME_HEIGHT-Tank.HEIGHT-2) y=TankFrame.GAME_HEIGHT-Tank.HEIGHT-2;
    }

    private void randomDir() {
        dir=Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bulletX=this.x+Tank.WIDTH/2-Bullet.WIDTH/2;
        int bulletY=this.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;
        tankFrame.bullets.add(new Bullet(bulletX,bulletY,this.dir,this.tankFrame,this.group));
    }

    public void die() {
        isAlive=false;
    }
}
