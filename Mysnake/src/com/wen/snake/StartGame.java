package com.wen.snake;

import javax.swing.*;

public class StartGame {
    public static void main(String[] args) {
        //1.����һ����̬����  JFrame
        JFrame frame = new JFrame("̰����");
        //���ý���Ĵ�С
        frame.setBounds(10,10,900,720);
        //���ڴ�С�򲻿��Ըı�
        frame.setResizable(false);
        //�ô��ڿ��Թر�
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //2.��� JPanel
        frame.add(new GamePanel());

        frame.setVisible(true);
    }
}
