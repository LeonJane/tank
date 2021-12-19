package com.leon.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    final static int GAME_WIDTH=1080,GAME_HEIGHT=960;
    Tank myTank=new Tank(200,400,Dir.UP,this,Group.GOOD);
    List<Bullet> bullets=new ArrayList<>();
    List<Tank> enemies=new ArrayList<>();
    List<Explode> explodes=new ArrayList<>();
    public TankFrame(){
        setVisible(true);
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage=null;
    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        Color c=g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量:"+bullets.size(),10,60);
        g.drawString("敌人的数量:"+enemies.size(),10,80);
        g.drawString("爆炸的数量:"+explodes.size(),10,100);
        g.setColor(c);
        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        //上面的for循环遍历如果采用for each方式，那么在删除子弹的时候需要采用下面的迭代器删除方式，不然会抛出ConcurrentModificationException异常
//        for (Iterator<Bullet> it=bullets.iterator();it.hasNext();){
//            Bullet b=it.next();
//            if(!b.isAlive) it.remove();
//        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).paint(g);
        }
        for (Bullet bullet:bullets){
            for(Tank tank:enemies){
                bullet.collideWith(tank);
            }
        }
        for (int i = 0; i <explodes.size() ; i++) {
            explodes.get(i).paint(g);
        }

    }

    class MyKeyListener extends KeyAdapter {
        boolean bl=false;
        boolean bu=false;
        boolean br=false;
        boolean bd=false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key=e.getKeyCode();
            switch(key){
                case KeyEvent.VK_LEFT:
                    bl=true;
                    break;
                case KeyEvent.VK_UP:
                    bu=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br=true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd=true;
                    break;
                default:break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!bl&&!bu&&!br&&!bd) {
                myTank.setMoving(false);
                return;
            }
            myTank.setMoving(true);
            if(bl) myTank.setDir(Dir.LEFT);
            if(bu) myTank.setDir(Dir.UP);
            if(br) myTank.setDir(Dir.RIGHT);
            if(bd) myTank.setDir(Dir.DOWN);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key=e.getKeyCode();
            switch(key){
                case KeyEvent.VK_LEFT:
                    bl=false;
                    break;
                case KeyEvent.VK_UP:
                    bu=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br=false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd=false;
                    break;
                case KeyEvent.VK_SPACE:
                    myTank.fire();
                    break;
                default:break;
            }
            setMainTankDir();
        }

    }
}
