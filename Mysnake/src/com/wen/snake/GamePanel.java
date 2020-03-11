package com.wen.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static java.awt.Font.BOLD;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int length;//蛇的长度
    int[] snakex = new int[7];//蛇的坐标x  里面的数字代表蛇身的长度
    int[] snakey = new int[7];//蛇的坐标y
    String fx ;

    boolean isStart = false;//游戏是否开始
    Timer timer = new Timer(100,this);

    //定义食物
    int foodx;
    int foody;
    Random random = new Random();

    //判断是否失败
    boolean isFale = false;

    //积分系统
    int score;

    //构造器
    public GamePanel(){
        init();
        //获取监听键盘事件
        this.setFocusable(true);//获取焦点
        this.addKeyListener(this);//添加监听
        timer.start();//让时间动起来
    }
    //初始化
    public void init(){
        length = 3;
        snakex[0]=100;snakey[0]=100;//头部坐标
        snakex[1]=75;snakey[1]=100;//第一个身体坐标
        snakex[2]=50;snakey[2]=100;//第二个身体坐标
        fx= "R";// 初始方向定位右边

        foodx = 25 +25*random.nextInt(33);
        foody = 75 + 25*random.nextInt(25);

        score = 0;
    }

    //画板： 画界面，画蛇
    @Override //ctrl + o    alt + insert
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        this.setBackground(Color.BLACK);

        //绘制头部的广告栏
        Data.header.paintIcon(this,g,25,11);
        //绘制游戏区域
        g.fillRect(25,75,850,700);

        //画一条静止的小蛇
        if(fx.equals("R")){
            //运用到反射的知识
            Data.right.paintIcon(this,g,snakex[0],snakey[0]);
        }else if (fx.equals("L"))
        {
            Data.left.paintIcon(this,g,snakex[0],snakey[0]);
        }else if (fx.equals("U"))
        {
            Data.up.paintIcon(this,g,snakex[0],snakey[0]);
        }else if (fx.equals("D"))
        {
            Data.down.paintIcon(this,g,snakex[0],snakey[0]);
        }
        for (int i = 1; i <length ; i++) {
            //蛇的长度给length赋值
            Data.body.paintIcon(this,g,snakex[i],snakey[i]);
        }

        //画积分
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",BOLD,20));
        g.drawString("长度："+length,750,35);
        g.drawString("分数:"+score,750,55);
        //画食物
        Data.food.paintIcon(this,g,foodx,foody);

        //游戏提示是否开始
        if(isStart==false){
            //画一个文字
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", BOLD,40));
            g.drawString("按下空格开始游戏",300,300);
        }

        //失败提醒
        if(isFale){
            //画一个文字，提醒失败
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",BOLD,40));
            g.drawString("游戏失败，按下空格重新开始",200,200);
        }
    }

       //接收键盘的输入：监听
    @Override
    public void keyReleased(KeyEvent e) {
        //获取按下键盘是哪个键   按下，释放
        int keyCode = e.getKeyCode();
        //如果按下的是空格，那就开始取反
        if (keyCode==KeyEvent.VK_SPACE)
        {
            if(isFale){//失败，游戏再来一遍
                isFale = false;
                init();
            }else{//暂停游戏
                isStart = !isStart;
            }

            repaint();//刷新界面
        }
        //键盘控制走向
        if(keyCode==KeyEvent.VK_RIGHT)
        {fx ="R";}
        else if(keyCode==KeyEvent.VK_LEFT)
        {fx ="L";}
        else if(keyCode==KeyEvent.VK_UP)
        {fx ="U";}
        else  if(keyCode==KeyEvent.VK_DOWN)
        {fx ="D";}
    }

    //定时器,监听时间,执行定时操作
     @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态，并且游戏没有失败
         if(isStart && isFale==false){
             //右边移动
             for (int i = length-1; i >0 ; i--) {
                 //除了脑袋，身体都向前移动,后一个身体节点跑到前一个的位置
                 snakex[i]= snakex[i-1];
                 snakey[i]= snakey[i-1];
             }
//通过控制方向，让头部移动
             if(fx.equals("R")){
                 snakex[0]= snakex[0]+25;//头部移动
                 if(snakex[0]>850)   snakex[0]=25; //边界判断

             }else if (fx.equals("L")){
                 snakex[0]= snakex[0]-25;//头部移动
                 if(snakex[0]<25) snakex[0]=850;  //边界判断
             }
             else if (fx.equals("U")){
                 snakey[0]= snakey[0]-25;//头部移动
                 if(snakey[0]<75) snakey[0]=650;  //边界判断
             }
             else if (fx.equals("D")){
                 snakey[0]= snakey[0]+25;//头部移动
                 if(snakey[0]>650) snakey[0]=75;  //边界判断
             }
             //如果蛇的头和食物的坐标重合了，就代表吃到食物了
             if(snakex[0]==foodx && snakey[0]==foody){
                 length++;//长度加一
                 score = score +10;

                 //重新生成食物
                 foodx = 25 +25*random.nextInt(33);
                 foody = 75 + 25*random.nextInt(25);
             }

             //结束判断
             for (int i = 1; i <length ; i++) {
                if(snakex[0]==snakex[i]&& snakey[0]==snakey[i]){
                    isFale = true;
                }
             }
             //刷新界面
                repaint();
         }
         timer.start();//启动定时器
    }


    @Override
    public void keyTyped(KeyEvent e) {
        //按下弹起
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //键盘按下
    }
}
