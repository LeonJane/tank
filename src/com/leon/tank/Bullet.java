package com.leon.tank;

import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    private Dir dir;
    Rectangle bulletRc=new Rectangle();
    private Group group=Group.BAD;
    private final static int SPEED=Integer.parseInt((String)PropertyMgr.get("bulletSpeed"));
    final static int WIDTH=ResouceMgr.bulletBl.getWidth();
    final static int HEIGHT=ResouceMgr.bulletBl.getHeight();
    boolean isAlive=true;


    public int getX() {
        return x;
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    private TankFrame tankFrame;


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir,TankFrame tankFrame,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame=tankFrame;
        this.group=group;

        bulletRc.x=x;
        bulletRc.y=y;
        bulletRc.width=WIDTH;
        bulletRc.height=HEIGHT;
    }

    public void paint(Graphics g){
        if(!isAlive){
           tankFrame.bullets.remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(ResouceMgr.bulletBl,x,y,null);
                break;
            case UP:
                g.drawImage(ResouceMgr.bulletBu,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResouceMgr.bulletBr,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResouceMgr.bulletBd,x,y,null);
                break;
            default:break;
        }
        move();
    }

    private void move() {
        switch (dir){
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

        if(x<0||y<0||x>TankFrame.GAME_WIDTH||y>TankFrame.GAME_HEIGHT) isAlive=false;

        bulletRc.x=this.x;
        bulletRc.y=this.y;
    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) return;
//        Rectangle bulletRc=new Rectangle(this.x,this.y,WIDTH,HEIGHT);
//        Rectangle tankRc=new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if(bulletRc.intersects(tank.tankRc)){
            tank.die();
            this.die();
            int eX=this.getX()+Tank.WIDTH/2-Explode.WIDTH/2;
            int eY=this.getY()+Tank.HEIGHT/2-Explode.HEIGHT/2;
            tankFrame.explodes.add(new Explode(eX,eY,this.tankFrame));
        }
    }

    private void die() {
        isAlive=false;
    }
}
