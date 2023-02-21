import java.util.ArrayList;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import java.lang.IndexOutOfBoundsException;

import javax.swing.JFrame;
import javax.swing.JLayer;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class App2048 implements App2048interface{
    private JFrame window;

    private JLabel header;
    private ArrayList<ArrayList<numBox>> numMap;

    public App2048(){
        window = new JFrame("Easy 2048");
        window.setSize(500, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        startgame();
        
    }

    private void startgame(){

        int random1 = (int)(Math.random() * 4);
        int random2 = (int)(Math.random() * 4);
        int random3 = (int)(Math.random() * 4);
        int random4 = (int)(Math.random() * 4);

        while(random3 == random1) random3 = (int)(Math.random() * 4);
        while(random4 == random2) random4 = (int)(Math.random() * 4);
        
        buildUpComponents();

        numBox startBox1 = numMap.get(random1).get(random2);
        numBox startBox2 = numMap.get(random3).get(random4);
        
        startBox1.setValue(2);
        startBox2.setValue(2);
        
        startBox1.setBackground(bgColors(startBox1.getValue()));
        startBox2.setBackground(bgColors(startBox2.getValue()));

    }

    private void buildUpComponents(){
        int value;

        header = new JLabel("The easy 2048 game", SwingConstants.CENTER);
        header.setPreferredSize(new Dimension(450,50));
        header.setFont(new Font("th sarabunPSK", Font.PLAIN, 36));
        numMap = new ArrayList<ArrayList<numBox>>(4);
        
        for (int i = 0; i < 4; i++){
            numMap.add(new ArrayList<numBox>());
            for (int j = 0; j < 4; j++){
                numMap.get(i).add(new numBox(" ", SwingConstants.CENTER));

                numBox numBox = numMap.get(i).get(j);
                numBox.setPreferredSize(new Dimension(100, 100));
                numBox.setFont(new Font("th sarabunPSK", Font.PLAIN, 40));
                numBox.setBackground(bgColors(numBox.getValue()));
                numBox.setOpaque(true);
            }
        }

        window.setLayout(new FlowLayout());

        window.add(header);
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                window.add(numMap.get(i).get(j));
            }
        }

    }

    private void swap(){
        var l= KeyEvent.VK_LEFT;
        var r=KeyEvent.VK_RIGHT;
        var u = KeyEvent.VK_UP;
        var d = KeyEvent.VK_DOWN;

        window.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
              int keyCode = e.getKeyCode();
              if (keyCode == KeyEvent.VK_UP) {
                 //up
            for(int i = 0;i<4;i++){
                for (int j = 0;j<4;j++){
                    numBox next = numMap.get(j).get(i);
                    numBox idx = numMap.get(0).get(0);
                    if(next.getValue()!=0){
                        idx=next;
                }
                continue;
            }
        }
              }
              else if (keyCode == KeyEvent.VK_DOWN) {
                //down
            for(int i = 0;i<4;i++){
                for (int j = 3;j==0;j--){
                    numBox next = numMap.get(j).get(i);
                    numBox idx = numMap.get(3).get(0);
                    if(next.getValue()!=0){
                        idx=next;
                }
                continue;
            }
        }
              }
              else if (keyCode == KeyEvent.VK_LEFT) {
                 //left
                for(int i = 0;i<4;i++){
            for (int j = 0;j<4;j++){
                numBox next = numMap.get(i).get(j);
                numBox idx = numMap.get(0).get(0);
                if(next.getValue()!=0){
                    idx=next;
                }
                continue;
            }
        }   
              }
              else if (keyCode == KeyEvent.VK_RIGHT) {
              //right
        for(int i = 0;i<4;i++){
            for (int j = 3;j==0;j--){
                numBox next = numMap.get(i).get(j);
                numBox idx = numMap.get(0).get(3);
                if(next.getValue()!=0){
                    idx=next;
                }
                continue;
                }
            }
              }
            }
          });
        
        
           
       
        


    }


    private void numChangeOnPressed(String dir){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                numBox numBox = numMap.get(i).get(j);
                numBox nextBox = numMap.get(0).get(0);

                try{
                    switch (dir) {
                        case "north" : nextBox = numMap.get(i - 1).get(j); break;
                        case "east" : nextBox = numMap.get(i).get(j + 1); break;
                        case "sounth" : nextBox = numMap.get(i + 1).get(j); break;
                        case "west" : nextBox = numMap.get(i).get(j - 1); break;
                        default : System.out.println("Direction input error!"); System.exit(1);
                    }
                    
                    if(numBox.equals(nextBox)) {
                        nextBox.increment();
                        numBox.clearValue();
                    }
                }
                catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }
                
                
            }
        }
    }

    private Color bgColors(int num){
        
        Color ret = Color.WHITE;
        System.out.println(num);
        
        switch(num){
            case(2): {
                ret = Color.LIGHT_GRAY;
                break;
            }
            case(4): {
                ret = Color.DARK_GRAY;
                break;
            }
            case(8): {
                ret = Color.MAGENTA;
                break;
            }
            case(16): {
                ret = Color.PINK;
                break;
            }
            case(32): {
                ret = Color.YELLOW;
                break;
            }
            case(64): {
                ret = new Color(105, 16, 255);
                break;
            }
            case(128): {
                ret = new Color(121, 99, 160);
                break;
            }
            case(256): {
                ret = new Color(224, 49, 255);
                break;
            }
            case(512): {
                ret = new Color(255, 49, 149);
                break;
            }
            case(1024): {
                ret = new Color(255, 49, 52, 1);
                break;
            }
            case(2048): {
                ret = new Color(219, 234, 79);
                break;
            }
        }
        return ret;
    }

}
