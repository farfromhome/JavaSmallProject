package com.wen.snake;

import javax.swing.*;

public class StartGame {
    public static void main(String[] args) {
        //1.绘制一个静态窗口  JFrame
        JFrame frame = new JFrame("贪吃蛇");
        //设置界面的大小
        frame.setBounds(10,10,900,720);
        //窗口大小则不可以改变
        frame.setResizable(false);
        //让窗口可以关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //2.面板 JPanel
        frame.add(new GamePanel());

        frame.setVisible(true);
    }
}
