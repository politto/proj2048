import java.util.ArrayList;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import java.lang.IndexOutOfBoundsException;

public class CoreSystem implements App2048interface{

    private static ArrayList<ArrayList<NumBox>> numMap;
    
    private static int intScore;
    private static boolean isMoved;

    public CoreSystem(){
        
        numMap = new ArrayList<ArrayList<NumBox>>(4); 
        Apperence.uiBuildUp();
        startgame();
        
    }

    //startGame method
    void startgame(){
        

        randomNumSpawn(false);
        randomNumSpawn(false);
        
        Apperence.painter();

        Apperence.getWindow().addKeyListener(new KeyAdapter() {
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

                Apperence.painter();
                cligame();

            }
        });

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
        int alreadySumValue = 0;
        boolean mayOver = false;
        
        for (int i = beginx; i < termimatex; i++){
            alreadySumValue = 0;
            for (int j = beginy; j < termimatey; j++){

                NumBox NumBox = numMap.get(i).get(j);
                NumBox nextBox = numMap.get(0).get(0);
                
                if(NumBox.getValue() == 64) Apperence.gameOver("win");

                try{
                    switch (dir) {
                        case "north" : nextBox = numMap.get(i - 1).get(j); break;
                        case "east" : nextBox = numMap.get(i).get(j + 1); break;
                        case "sounth" : nextBox = numMap.get(i + 1).get(j); break;
                        case "west" : nextBox = numMap.get(i).get(j - 1); break;
                        default : System.out.println("Direction input error!(2)"); System.exit(1);
                    }
                }
                catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }
                catch (IndexOutOfBoundsException e){
                    continue;
                }

                if(NumBox.isEquals(nextBox) && NumBox.getValue() != 0 && nextBox.getValue() != 0 && NumBox.getValue() != alreadySumValue) {
                        
                    alreadySumValue = NumBox.getValue();
                    nextBox.increment();
                    NumBox.clearValue();
                    intScore++;
                    Apperence.setScore(intScore);
                    isMoved = true;
                    alreadySumValue = 0;
                }
                else {
                    mayOver = true;
                }

            }
        }

        if (mayOver) isGameOver();
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

    static void clearAllValue(){
        for(int i =0; i< 4; i++){
            for(int j = 0; j< 4; j++){
                numMap.get(i).get(j).clearValue();
            }
        }
    }

    //check if game is over.
    private void isGameOver(){
        boolean over = true;

        for(int i =0; i< 4; i++){
            for(int j = 0; j< 4; j++){
                try{
                    int thisVal = numMap.get(i).get(j).getValue();
                    if (thisVal == 0 || thisVal == numMap.get(i).get(j + 1).getValue() || thisVal == numMap.get(i).get(j - 1).getValue() || thisVal == numMap.get(i + 1).get(j).getValue() || thisVal == numMap.get(i - 1).get(j).getValue()){
                        over = false;
                        break;
                    }
                }
                    
                catch(IndexOutOfBoundsException e){
                    continue;
                }

            }
        }

        if(over && intScore > 2) {
            System.out.println(intScore);
            Apperence.gameOver("lose");
        }

    }

    //for debugging.
    private void cligame(){
        for(int i =0; i< 4; i++){
            for(int j = 0; j< 4; j++){
                System.out.printf("%4d", numMap.get(i).get(j).getValue());
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    static void clearIntScore(){
        intScore = 0;
        System.out.println(intScore);
    }

    public static int getScore(){
        return intScore;
    }

    static ArrayList<ArrayList<NumBox>> getNumMap(){
        // System.out.println(numMap);
        return numMap;
    }
    

}
