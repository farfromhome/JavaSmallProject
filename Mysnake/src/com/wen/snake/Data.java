package com.wen.snake;

import javax.swing.*;
import java.net.URL;

//存放外部数据
public class Data {
    //头部的图片  URL
    public  static URL headurl = Data.class.getResource("/static/header.png");
    public  static ImageIcon header = new ImageIcon(headurl);

    //头
    public  static URL upUrl = Data.class.getResource("/static/up.png");
    public  static ImageIcon up = new ImageIcon(upUrl);

    public  static URL downUrl = Data.class.getResource("/static/down.png");
    public  static ImageIcon down = new ImageIcon(downUrl);

    public  static URL leftUrl = Data.class.getResource("/static/left.png");
    public  static ImageIcon left = new ImageIcon(leftUrl);

    public  static URL rigthUrl = Data.class.getResource("/static/right.png");
    public  static ImageIcon right = new ImageIcon(rigthUrl);

    //身体
    public  static URL bodyUrl = Data.class.getResource("/static/body.png");
    public  static ImageIcon body = new ImageIcon(bodyUrl);

    //食物
    public  static URL foodUrl = Data.class.getResource("/static/food.png");
    public  static ImageIcon food = new ImageIcon(foodUrl);
}
