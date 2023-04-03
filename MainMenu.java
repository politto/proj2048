import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame {
    private static String fontName = "freesiaUPC";
    JPanel buttonPanel = new JPanel();
    JLabel header = new JLabel("The Easy 2048 Game", SwingConstants.CENTER);
    CoreSystem gm;
    MainMenu(){
    //this.gm = gm;
    setTitle("The Easy 2048 Game");
    setFrame();
    setLabel();
    setButton();
    setVisible(true);

    SoundPlayer sp = new SoundPlayer();
    sp.RandomPlayBGmusic();
}

void setFrame(){
    setBackground(new Color(84,25,48));
    setSize(500, 750);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationByPlatform(true);
    }
void setLabel(){
    header.setPreferredSize(new Dimension(450,50));
    header.setFont(new Font(fontName, Font.PLAIN, 45));
    header.setForeground(new Color(0, 0, 0));
    this.add(header);
    
}
void setButton(){
        JButton start = new JButton("Play");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new CoreSystem();

            }
        });
        JButton mdBT = new JButton("Modes");
        mdBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton ldbBT = new JButton("Leaderboard");
        ldbBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to show options menu goes here
            }
        });

        JButton stBT = new JButton("Setting");
        stBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton hpBT = new JButton("Help");
        hpBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        buttonPanel.setLayout(new GridLayout(6, 0));
        buttonPanel.add(header);
        buttonPanel.add(start);
        buttonPanel.add(mdBT);
        buttonPanel.add(ldbBT);
        buttonPanel.add(stBT);
        buttonPanel.add(hpBT);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setSize(100,50);
    }
   
}
   
