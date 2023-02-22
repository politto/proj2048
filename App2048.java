import java.util.ArrayList;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        
        buildUpComponents();
        startgame();
        
    }

    private void startgame(){

        randomNumSpawn(false);
        randomNumSpawn(false);
        
        painter();

        window.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        numChangeOnPressed("west");
                        moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        numChangeOnPressed("east");
                        moveRight();
                        break;
                    case KeyEvent.VK_UP:
                        moveUp();
                        numChangeOnPressed("north");
                        break;
                    case KeyEvent.VK_DOWN:
                        moveDown();
                        numChangeOnPressed("sounth");
                        break;
                }

                randomNumSpawn(true);
                painter();

            }
        });

    }

    private void buildUpComponents(){

        header = new JLabel("The easy 2048 game", SwingConstants.CENTER);
        header.setPreferredSize(new Dimension(450,50));
        header.setFont(new Font("th sarabunPSK", Font.PLAIN, 36));
        numMap = new ArrayList<ArrayList<numBox>>(4);
        
        for (int i = 0; i < 4; i++){
            numMap.add(new ArrayList<numBox>());
            for (int j = 0; j < 4; j++){
                numMap.get(i).add(new numBox(" ", SwingConstants.CENTER));

                numBox NumBox = numMap.get(i).get(j);
                NumBox.setPreferredSize(new Dimension(100, 100));
                NumBox.setFont(new Font("th sarabunPSK", Font.PLAIN, 40));
                NumBox.setOpaque(true);
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


    private void moveLeft() {
        for (int i = 0; i < 4; i++) {
            int k = 0;
            for (int j = 0; j < 4; j++) {
                numBox box = numMap.get(i).get(j);
                if (box.getValue() != 0) {
                    if (k != j) {
                        numMap.get(i).get(k).setValue(box.getValue());
                        box.clearValue();
                    }
                    k++;
                }
            }
        }
    }

    private void moveRight() {
        for (int i = 0; i < 4; i++) {
            int k = 3;
            for (int j = 3; j >= 0; j--) {
                numBox box = numMap.get(i).get(j);
                if (box.getValue() != 0) {
                    if (k != j) {
                        numMap.get(i).get(k).setValue(box.getValue());
                        box.clearValue();
                    }
                    k--;
                }
            }
        }
    }
    
    private void moveDown() {
        for (int j = 0; j < 4; j++) {
            int k = 3;
            for (int i = 3; i >= 0; i--) {
                numBox box = numMap.get(i).get(j);
                if (box.getValue() != 0) {
                    if (k != i) {
                        numMap.get(k).get(j).setValue(box.getValue());
                        box.clearValue();
                    }
                    k--;
                }
            }
        }
    }
    
    private void moveUp() {
        for (int j = 0; j < 4; j++) {
            int k = 0;
            for (int i = 0; i < 4; i++) {
                numBox box = numMap.get(i).get(j);
                if (box.getValue() != 0) {
                    if (k != i) {
                        numMap.get(k).get(j).setValue(box.getValue());
                        box.clearValue();
                    }
                    k++;
                }
            }
        }
    }
    

    private void numChangeOnPressed(String dir){

        int beginx = 0;
        int beginy = 0;
        int termimatex = 4;
        int termimatey = 4;

        switch (dir) {
            case "north" : beginy = 1; break;
            case "east" : termimatex = 3; break;
            case "sounth" : termimatey = 3; break;
            case "west" : beginx = 1; break;
            default : System.out.println("Direction input error!(1)"); System.exit(1);
        }
        for (int i = beginx; i < termimatex; i++){
            for (int j = beginy; j < termimatey; j++){

                numBox NumBox = numMap.get(i).get(j);
                numBox nextBox = numMap.get(0).get(0);

                try{
                    switch (dir) {
                        case "north" : nextBox = numMap.get(i - 1).get(j); break;
                        case "east" : nextBox = numMap.get(i).get(j + 1); break;
                        case "sounth" : nextBox = numMap.get(i + 1).get(j); break;
                        case "west" : nextBox = numMap.get(i).get(j - 1); break;
                        default : System.out.println("Direction input error!(2)"); System.exit(1);
                    }

                    if(NumBox.isEquals(nextBox) && NumBox.getValue() != 0 && nextBox.getValue() != 0) {
                        nextBox.increment();
                        NumBox.clearValue();
                        i = beginx;
                        j = beginy;
                    }
                }
                catch (IndexOutOfBoundsException e){
                    continue;
                }

            }
        }
    }

    private void painter(){

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                    numBox nb = numMap.get(i).get(j);
                    nb.setBackground(bgColorsSelection(nb.getValue()));
                }
            }
        }
        
    

    private Color bgColorsSelection(int num) {

        Color ret = Color.WHITE;
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

    private void randomNumSpawn(boolean midgame){

        int randomx = (int)(Math.random() * 4);
        int randomy = (int)(Math.random() * 4);

        while( numMap.get(randomx).get(randomy).getValue() != 0){
            randomx = (int)(Math.random() * 4);
            randomy = (int)(Math.random() * 4);
        }

        numBox startBox = numMap.get(randomx).get(randomy);

        if (midgame) startBox.setValue(Math.random() > 0.5 ? 2 : 4);
        else startBox.setValue(2);

    }

}
