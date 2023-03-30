import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JLabel;


import java.util.ArrayList;

public class mainApperence {

    private static JFrame window;
    private static JFrame popup;
    private static JLabel header;
    private static JLabel desc;
    private static JLabel desc2;
    private static JLabel lbScore;
    

    private static JLabel popupdesc1;
    private static JLabel popupdesc2;
    private static JButton tryAgain;
    private static JButton closeGame;

    private static String fontName = "freesiaUPC";

    static void uiBuildUp(){

        window = new JFrame("Easy 2048");
        window.setBackground(new Color(84,25,48));
        window.setSize(500, 750);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationByPlatform(true);
        window.setVisible(true);

        header = new JLabel("The Easy 2048 Game", SwingConstants.CENTER);
        header.setPreferredSize(new Dimension(450,50));
        header.setFont(new Font(fontName, Font.PLAIN, 45));
        header.setForeground(new Color(255, 255, 255));
        
        lbScore = new JLabel("Score : 0, HighScore : 0",SwingConstants.CENTER);
        // lbScore.setForeground(new Color(255, 255, 255));

        for (int i = 0; i < 4; i++){
            CoreSystem.getNumMap().add(new ArrayList<NumBox>());
            for (int j = 0; j < 4; j++){
                CoreSystem.getNumMap().get(i).add(new NumBox(" ", SwingConstants.CENTER));
                NumBox NumBox = CoreSystem.getNumMap().get(i).get(j);
                

            }
        }

        desc = new JLabel("Use your Keyboard arrow keys to ", SwingConstants.CENTER);
        desc.setPreferredSize(new Dimension(450, 50));
        desc.setFont(new Font(fontName, Font.PLAIN, 30));
        // desc.setForeground(new Color(255, 255, 255));
        
        desc2 = new JLabel("play this game[^v<>]", SwingConstants.CENTER);
        desc2.setPreferredSize(new Dimension(450, 50));
        desc2.setFont(new Font(fontName, Font.PLAIN, 30));
        // desc2.setForeground(new Color(255, 255, 255));
        lbScore.setPreferredSize(new Dimension(450, 100));
        lbScore.setFont(new Font(fontName, Font.PLAIN, 30));
        window.setLayout(new FlowLayout());
        
        window.add(header);
        window.add(lbScore);
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                window.add(CoreSystem.getNumMap().get(i).get(j));
            }
        }
        window.add(desc);
        window.add(desc2);
        

    }

    static void painter(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                NumBox nb = CoreSystem.getNumMap().get(i).get(j);
                nb.setBackground(nb.bgColorsSelection(nb.getValue()));
                if(nb.getValue() > 64){
                    nb.setForeground(Color.WHITE);
                }
                else{
                    nb.setForeground(Color.BLACK);
                }
                    
            }
        }
    }


    static JFrame getWindow(){
        return window;
    }

    static void setScore(int sc, int highsc){
        lbScore.setText("Score : " + sc + " Highscore :" + highsc);
    }

    //If game over, do this method.
    public static void gameOver(String winOrLose){

        if (winOrLose.equals("win")) popup = new JFrame("You win");
        else popup = new JFrame("Game over");
        
        popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        popup.setSize(new Dimension(400,300));
        
        if (winOrLose.equals("win")) popupdesc1 = new JLabel("You win!", SwingConstants.CENTER);
        else popupdesc1 = new JLabel("GAME OVER", SwingConstants.CENTER);
        popupdesc2 = new JLabel("score : " + CoreSystem.getScore(), SwingConstants.CENTER);
        tryAgain = new JButton("Try again");
        closeGame = new JButton("Quit");

        popupdesc1.setPreferredSize(new Dimension(400,50));
        popupdesc1.setFont(new Font("th sarabunPSK bold", Font.PLAIN, 40));

        popupdesc2.setPreferredSize(new Dimension(400,50));
        popupdesc1.setFont(new Font("th sarabunPSK bold", Font.PLAIN, 20));

        tryAgain.setPreferredSize(new Dimension(200,50));        
        closeGame.setPreferredSize(new Dimension(200,50));        
        
        popup.setLayout(new FlowLayout());
        popup.add(popupdesc1);
        popup.add(popupdesc2);
        popup.add(tryAgain);
        popup.add(closeGame);
        popup.setVisible(true);
        
        gameOverCont();
    }

    public static void gameOverCont(){
        
        ButtonClickListener bcl = new ButtonClickListener();
        tryAgain.addActionListener(bcl);
        closeGame.addActionListener(bcl);

    }

    static JFrame getPopup(){
        return popup;
    }

    static JButton getTryagainButton(){
        return tryAgain;
    }

    static JButton getCloseGameButton(){
        return closeGame;
    }

    static String getFontName(){
        return fontName;
    }


}