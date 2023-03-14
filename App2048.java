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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class App2048 implements App2048interface{

    private JFrame window;
    private JFrame popup;

    private JLabel header;
    private ArrayList<ArrayList<NumBox>> numMap;
    private JLabel desc;
    private JLabel lbScore;

    private JLabel popupdesc1;
    private JLabel popupdesc2;
    
    private int intScore;
    private boolean isMoved;
    private String fontName = "freesiaUPC";
    public App2048(){

        window = new JFrame("Easy 2048");
        window.setSize(500, 700);
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
                        moveLeft();
                        numChangeOnPressed("west");
                        moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRight();
                        numChangeOnPressed("east");
                        moveRight();
                        break;
                    case KeyEvent.VK_UP:
                        moveUp();
                        numChangeOnPressed("north");
                        moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        moveDown();
                        numChangeOnPressed("sounth");
                        moveDown();
                        break;
                }

                if (isMoved) {
                    randomNumSpawn(true);
                    isMoved = !isMoved;
                }

                painter();
                cligame();

            }
        });

    }

    private void buildUpComponents(){

        header = new JLabel("The Easy 2048 Game", SwingConstants.CENTER);
        header.setPreferredSize(new Dimension(450,50));
        header.setFont(new Font("freesiaUPC", Font.PLAIN, 45));
        numMap = new ArrayList<ArrayList<NumBox>>(4); 
        lbScore = new JLabel("Score : ",SwingConstants.CENTER);

        for (int i = 0; i < 4; i++){
            numMap.add(new ArrayList<NumBox>());
            for (int j = 0; j < 4; j++){
                numMap.get(i).add(new NumBox(" ", SwingConstants.CENTER));
                NumBox NumBox = numMap.get(i).get(j);
                NumBox.setPreferredSize(new Dimension(100, 100));
                NumBox.setFont(new Font(fontName, Font.PLAIN, 40));
                NumBox.setOpaque(true);
            }
        }

        desc = new JLabel("Use your Keyboard arrow keys to play this game[^v<>]", SwingConstants.CENTER);
        desc.setPreferredSize(new Dimension(450, 100));
        desc.setFont(new Font(fontName, Font.PLAIN, 30));
        lbScore.setPreferredSize(new Dimension(450, 100));
        lbScore.setFont(new Font(fontName, Font.PLAIN, 30));
        window.setLayout(new FlowLayout());
        
        window.add(header);
        window.add(lbScore);
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                window.add(numMap.get(i).get(j));
            }
        }
        window.add(desc);
        

    }


    private void moveLeft() {
        for (int i = 0; i < 4; i++) {
            int k = 0;
            for (int j = 0; j < 4; j++) {
                NumBox box = numMap.get(i).get(j);
                if (box.getValue() != 0) {
                    if (k != j) {
                        numMap.get(i).get(k).setValue(box.getValue());
                        box.clearValue();
                        isMoved = true;
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
                NumBox box = numMap.get(i).get(j);
                if (box.getValue() != 0) {
                    if (k != j) {
                        numMap.get(i).get(k).setValue(box.getValue());
                        box.clearValue();
                        isMoved = true;
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
                NumBox box = numMap.get(i).get(j);
                if (box.getValue() != 0) {
                    if (k != i) {
                        numMap.get(k).get(j).setValue(box.getValue());
                        box.clearValue();
                        isMoved = true;
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
                NumBox box = numMap.get(i).get(j);
                if (box.getValue() != 0) {
                    if (k != i) {
                        numMap.get(k).get(j).setValue(box.getValue());
                        box.clearValue();
                        isMoved = true;
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

        for (int i = beginx; i < termimatex; i++){
            for (int j = beginy; j < termimatey; j++){

                NumBox NumBox = numMap.get(i).get(j);
                NumBox nextBox = numMap.get(0).get(0);
                if(NumBox.getValue() == 1024){
                    String show = "VICTORY\nScore :" + intScore;
                    JOptionPane.showMessageDialog(null, show );
                    clearAllValue();
                    randomNumSpawn(false);
                    randomNumSpawn(false);

                }

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
                        intScore++;
                        lbScore.setText("Score : " + intScore);
                    }
                    else isGameOver();
                }
                catch (ArrayIndexOutOfBoundsException e){
                    continue;
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
                NumBox nb = numMap.get(i).get(j);
                nb.setBackground(bgColorsSelection(nb.getValue()));
                if(nb.getValue() > 64){
                    nb.setForeground(Color.WHITE);
                }
                else{
                    nb.setForeground(Color.BLACK);
                }
                    
            }
        }
    }
        
    

    private Color bgColorsSelection(int num) {

        Color ret = new Color(126,171,139);
        switch(num){
            case(2): {
                ret = new Color(193,183,132);
                break;
            }
            case(4): {
                ret = new Color(190,175,95);
                break;
            }
            case(8): {
                ret = new Color(150,140,49);
                break;
            }
            case(16): {
                ret = new Color(110,100,55);
                break;
            }
            case(32): {
                ret = new Color(190,190,190);
                break;
            }
            case(64): {
                ret = new Color(115,115,90);
                break;
            }
            case(128): {
                ret = new Color(81, 81, 52);
                break;
            }
            case(256): {
                ret = new Color(45, 45, 30);
                break;
            }
            case(512): {
                ret = new Color(75,40,30);
                break;
            }
            case(1024): {
                ret = new Color(38,6,6);
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

        NumBox startBox = numMap.get(randomx).get(randomy);

        if (midgame) startBox.setValue(Math.random() > 0.5 ? 2 : 4);
        else startBox.setValue(2);

    }

    private void clearAllValue(){
        for(int i =0; i< 4; i++){
            for(int j = 0; j< 4; j++){
                numMap.get(i).get(j).clearValue();
            }
        }
    }

    private void isGameOver(){
        boolean over = true;

        for(int i =0; i< 4; i++){
            for(int j = 0; j< 4; j++){
                if (numMap.get(i).get(j).getValue() == 0) over = false;
            }
        }

        if(over) {
            popup = new JFrame("Game over");

            popupdesc1 = new JLabel("GAME OVER");
            popupdesc1.setFont(new Font(fontName, Font.PLAIN, 30));
            popupdesc2 = new JLabel("score : " + intScore);
            //String show = "Game over\nScore :" + intScore;
            clearAllValue();
        }

    }

    private void cligame(){
        for(int i =0; i< 4; i++){
            for(int j = 0; j< 4; j++){
                System.out.printf("%4d", numMap.get(i).get(j).getValue());
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

}
