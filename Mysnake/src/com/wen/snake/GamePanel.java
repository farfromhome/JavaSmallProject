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

    int length;//�ߵĳ���
    int[] snakex = new int[7];//�ߵ�����x  ��������ִ�������ĳ���
    int[] snakey = new int[7];//�ߵ�����y
    String fx ;

    boolean isStart = false;//��Ϸ�Ƿ�ʼ
    Timer timer = new Timer(100,this);

    //����ʳ��
    int foodx;
    int foody;
    Random random = new Random();

    //�ж��Ƿ�ʧ��
    boolean isFale = false;

    //����ϵͳ
    int score;

    //������
    public GamePanel(){
        init();
        //��ȡ���������¼�
        this.setFocusable(true);//��ȡ����
        this.addKeyListener(this);//��Ӽ���
        timer.start();//��ʱ�䶯����
    }
    //��ʼ��
    public void init(){
        length = 3;
        snakex[0]=100;snakey[0]=100;//ͷ������
        snakex[1]=75;snakey[1]=100;//��һ����������
        snakex[2]=50;snakey[2]=100;//�ڶ�����������
        fx= "R";// ��ʼ����λ�ұ�

        foodx = 25 +25*random.nextInt(33);
        foody = 75 + 25*random.nextInt(25);

        score = 0;
    }

    //���壺 �����棬����
    @Override //ctrl + o    alt + insert
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//����
        this.setBackground(Color.BLACK);

        //����ͷ���Ĺ����
        Data.header.paintIcon(this,g,25,11);
        //������Ϸ����
        g.fillRect(25,75,850,700);

        //��һ����ֹ��С��
        if(fx.equals("R")){
            //���õ������֪ʶ
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
            //�ߵĳ��ȸ�length��ֵ
            Data.body.paintIcon(this,g,snakex[i],snakey[i]);
        }

        //������
        g.setColor(Color.white);
        g.setFont(new Font("΢���ź�",BOLD,20));
        g.drawString("���ȣ�"+length,750,35);
        g.drawString("����:"+score,750,55);
        //��ʳ��
        Data.food.paintIcon(this,g,foodx,foody);

        //��Ϸ��ʾ�Ƿ�ʼ
        if(isStart==false){
            //��һ������
            g.setColor(Color.white);
            g.setFont(new Font("΢���ź�", BOLD,40));
            g.drawString("���¿ո�ʼ��Ϸ",300,300);
        }

        //ʧ������
        if(isFale){
            //��һ�����֣�����ʧ��
            g.setColor(Color.white);
            g.setFont(new Font("΢���ź�",BOLD,40));
            g.drawString("��Ϸʧ�ܣ����¿ո����¿�ʼ",200,200);
        }
    }

       //���ռ��̵����룺����
    @Override
    public void keyReleased(KeyEvent e) {
        //��ȡ���¼������ĸ���   ���£��ͷ�
        int keyCode = e.getKeyCode();
        //������µ��ǿո��ǾͿ�ʼȡ��
        if (keyCode==KeyEvent.VK_SPACE)
        {
            if(isFale){//ʧ�ܣ���Ϸ����һ��
                isFale = false;
                init();
            }else{//��ͣ��Ϸ
                isStart = !isStart;
            }

            repaint();//ˢ�½���
        }
        //���̿�������
        if(keyCode==KeyEvent.VK_RIGHT)
        {fx ="R";}
        else if(keyCode==KeyEvent.VK_LEFT)
        {fx ="L";}
        else if(keyCode==KeyEvent.VK_UP)
        {fx ="U";}
        else  if(keyCode==KeyEvent.VK_DOWN)
        {fx ="D";}
    }

    //��ʱ��,����ʱ��,ִ�ж�ʱ����
     @Override
    public void actionPerformed(ActionEvent e) {
        //�����Ϸ���ڿ�ʼ״̬��������Ϸû��ʧ��
         if(isStart && isFale==false){
             //�ұ��ƶ�
             for (int i = length-1; i >0 ; i--) {
                 //�����Դ������嶼��ǰ�ƶ�,��һ������ڵ��ܵ�ǰһ����λ��
                 snakex[i]= snakex[i-1];
                 snakey[i]= snakey[i-1];
             }
//ͨ�����Ʒ�����ͷ���ƶ�
             if(fx.equals("R")){
                 snakex[0]= snakex[0]+25;//ͷ���ƶ�
                 if(snakex[0]>850)   snakex[0]=25; //�߽��ж�

             }else if (fx.equals("L")){
                 snakex[0]= snakex[0]-25;//ͷ���ƶ�
                 if(snakex[0]<25) snakex[0]=850;  //�߽��ж�
             }
             else if (fx.equals("U")){
                 snakey[0]= snakey[0]-25;//ͷ���ƶ�
                 if(snakey[0]<75) snakey[0]=650;  //�߽��ж�
             }
             else if (fx.equals("D")){
                 snakey[0]= snakey[0]+25;//ͷ���ƶ�
                 if(snakey[0]>650) snakey[0]=75;  //�߽��ж�
             }
             //����ߵ�ͷ��ʳ��������غ��ˣ��ʹ���Ե�ʳ����
             if(snakex[0]==foodx && snakey[0]==foody){
                 length++;//���ȼ�һ
                 score = score +10;

                 //��������ʳ��
                 foodx = 25 +25*random.nextInt(33);
                 foody = 75 + 25*random.nextInt(25);
             }

             //�����ж�
             for (int i = 1; i <length ; i++) {
                if(snakex[0]==snakex[i]&& snakey[0]==snakey[i]){
                    isFale = true;
                }
             }
             //ˢ�½���
                repaint();
         }
         timer.start();//������ʱ��
    }


    @Override
    public void keyTyped(KeyEvent e) {
        //���µ���
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //���̰���
    }
}
