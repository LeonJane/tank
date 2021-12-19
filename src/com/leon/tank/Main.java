package com.leon.tank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame=new TankFrame();
        tankFrame.setVisible(true);

        int tankInitAmount=Integer.parseInt((String)PropertyMgr.get("initTankAmount"));
        for(int i=0;i<tankInitAmount;i++){
            tankFrame.enemies.add(new Tank(50+i*80,200,Dir.DOWN,tankFrame,Group.BAD));
        }
        //music
//        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while(true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
