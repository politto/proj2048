import java.util.ArrayList;
import java.util.Timer;
import java.util.Scanner;
import java.util.InputMismatchException;

import java.io.IOException;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.lang.IndexOutOfBoundsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.NoSuchFileException;

public class CoreSystem implements App2048interface{

    private static ArrayList<ArrayList<NumBox>> numMap;
    
    private static int intScore;
    private static int highScore;
    private static boolean isMoved;
    private static boolean isSum;

    public CoreSystem(){
        
        numMap = new ArrayList<ArrayList<NumBox>>(4); 
        mainApperence.uiBuildUp();
        startgame();

        
    }

    //startGame method
    void startgame(){
        
        loadHighScore();
        randomNumSpawn(false);
        randomNumSpawn(false);
        
        mainApperence.painter();
        mainApperence.setScore(getScore(), getHighScore());

        mainApperence.getWindow().addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        numChangeLeft();
                        moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        numChangeRight();
                        moveRight();
                        break;
                    case KeyEvent.VK_UP:
                        numChangeUp();
                        moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        numChangeDown();
                        moveDown();
                        break;
                }

                if (isMoved||isSum) {
                    randomNumSpawn(true);
                    isMoved = false;
                    isSum=false;
                }
                isGameOver();
                mainApperence.painter();
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
    

    private void numChangeLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                NumBox box = numMap.get(i).get(j);
                if(box.getValue()!=0){
                    for(int k=j+1;k<4;k++){
                        NumBox index= numMap.get(i).get(k);
                        if(index.getValue()!=0){
                            if(index.getValue()==box.getValue()){
                                box.increment();
                                index.clearValue();
                                isSum=true;
                                setScore(getScore() + 1);
                                
                                break;
                            }else break ;
                        }
                    }
                } 
            }
        }
    }

    private void numChangeRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j > 0; j--) {
                NumBox box = numMap.get(i).get(j);
                if(box.getValue()!=0){
                    for(int k = j-1;k>=0;k--){
                        NumBox index= numMap.get(i).get(k);
                        if(index.getValue()!=0){
                            if(index.getValue()==box.getValue()){
                                box.increment();
                                index.clearValue();
                                isSum=true;
                                setScore(getScore() + 1);
                                
                                break;
                            }else break ;
                        }
                    }
                } 
            }
        }
        
    }


    private void numChangeUp() {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 3; i++) {
                NumBox box = numMap.get(i).get(j);
                if(box.getValue()!=0){
                    for(int k=i+1;k<4;k++){
                        NumBox index= numMap.get(k).get(j);
                        if(index.getValue()!=0){
                            if(index.getValue()==box.getValue()){
                                box.increment();
                                index.clearValue();
                                isSum=true;
                                setScore(getScore() + 1);
                                
                                break;
                            }else break ;
                        }
                    }
                } 
            }
        }
    }
    
    private void numChangeDown(){
    for (int j = 0; j < 4; j++) {
        for (int i = 3; i >0; i--) {
            NumBox box = numMap.get(i).get(j);
            if(box.getValue()!=0){
                for(int k=i-1;k>=0;k--){
                    NumBox index= numMap.get(k).get(j);
                    if(index.getValue()!=0){
                        if(index.getValue()==box.getValue()){
                            box.increment();
                            index.clearValue();
                            isSum=true;
                            setScore(getScore() + 1);
                            
                            break;
                        }else break ;
                    }
                }
            } 
        }
    }
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
            mainApperence.gameOver("lose");
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
        System.out.println(intScore);
        return intScore;
    }

    public static void setScore(int num){
        intScore = num;
    }

    public static int getHighScore(){
        return highScore;
    }

    public static void setHighScore(int num){
        highScore = num;
    }

    static ArrayList<ArrayList<NumBox>> getNumMap(){
        // System.out.println(numMap);
        return numMap;
    }
    

    static void loadHighScore(){
        int highSc = 0;
        try (Scanner input = new Scanner(Paths.get("highscore.txt"))){
            highSc = input.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("doesn't match input.");
        }
        catch (NoSuchFileException e){
            System.out.println("File not found.");
        }
        catch (IOException e){
            System.out.println("Error ai rai wa");
        }
        setHighScore(highSc);

    }

}
