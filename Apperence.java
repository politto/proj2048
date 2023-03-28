import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class Apperence {

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

    static void uiBuildUp(){

        header = new JLabel("The Easy 2048 Game", SwingConstants.CENTER);
        header.setPreferredSize(new Dimension(450,50));
        header.setFont(new Font("freesiaUPC", Font.PLAIN, 45));
        lbScore = new JLabel("Score : ",SwingConstants.CENTER);

        for (int i = 0; i < 4; i++){
            CoreSystem.getNumMap().add(new ArrayList<NumBox>());
            for (int j = 0; j < 4; j++){
                numMap.get(i).add(new NumBox(" ", SwingConstants.CENTER));
                NumBox NumBox = numMap.get(i).get(j);
                NumBox.setPreferredSize(new Dimension(100, 100));
                NumBox.setFont(new Font(fontName, Font.PLAIN, 40));
                NumBox.setOpaque(true);
            }
        }

        desc = new JLabel("Use your Keyboard arrow keys to ", SwingConstants.CENTER);
        desc.setPreferredSize(new Dimension(450, 50));
        desc.setFont(new Font(fontName, Font.PLAIN, 30));
        desc2 = new JLabel("play this game[^v<>]", SwingConstants.CENTER);
        desc2.setPreferredSize(new Dimension(450, 50));
        desc2.setFont(new Font(fontName, Font.PLAIN, 30));
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
        window.add(desc2);
        

    }

    static void painter(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                NumBox nb = numMap.get(i).get(j);
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

    static void setScore(int sc){
        lbScore.setText("Score : " + sc);
    }

    //If game over, do this method.
    public static void gameOver(String winOrLose){

        if (winOrLose.equals("win")) popup = new JFrame("You win");
        else popup = new JFrame("Game over");
        
        popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        popup.setSize(new Dimension(400,300));
        
        if (winOrLose.equals("win")) popupdesc1 = new JLabel("You win!", SwingConstants.CENTER);
        else popupdesc1 = new JLabel("GAME OVER", SwingConstants.CENTER);
        popupdesc2 = new JLabel("score : " + intScore, SwingConstants.CENTER);
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

    class ButtonClickListener implements ActionListener{
    
        public void actionPerformed(ActionEvent ev){
    
            JButton source = (JButton)ev.getSource();
            System.out.println(source);
            clearAllValue();
            clearIntScore();
            if(source == closeGame || source.getText().equals("Quit")) System.exit(1);
            else {
                popup.setVisible(false);
                window.setVisible(false);
                new CoreSystem();
            }
        }
    
    }
}