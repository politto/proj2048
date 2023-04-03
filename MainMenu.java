import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {
    
    private static String fontName = "freesiaUPC";
    private JPanel buttonPanel = new JPanel();
    private JLabel header = new JLabel("The Easy 2048 Game", SwingConstants.CENTER);

    MainMenu(){
        setTitle("The Easy 2048 Game");
        setFrame();
        setLabel();
        setButton();
        setVisible(true);

        SoundPlayer sp = new SoundPlayer();
        sp.RandomPlayBGmusic(50);
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

        MaButton start = new MaButton("Play");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new CoreSystem();

            }
        });

        MaButton mdBT = new MaButton("Modes");
        mdBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        MaButton ldbBT = new MaButton("Leaderboard");
        ldbBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to show options menu goes here
            }
        });

        MaButton stBT = new MaButton("Setting");
        stBT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        MaButton hpBT = new MaButton("Help");
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

class MaButton extends JButton {

    private int roundTR = 25;
    private int roundTL = 25;
    private int roundBR = 25;
    private int roundBL = 25;

    MaButton(String text){
        super(text);
        this.setPreferredSize(new Dimension());
        this.setPreferredSize(new Dimension(100, 100));
        this.setFont(new Font(mainApperence.getFontName(), Font.PLAIN, 40));
        
        this.setOpaque(false);
    }

    public int getRoundTR() {
        return roundTR;
    }

    public void setRoundTR(int roundTR) {
        this.roundTR = roundTR;
        repaint();
    }

    public int getRoundTL() {
        return roundTL;
    }

    public void setRoundTL(int roundTL) {
        this.roundTL = roundTL;
        repaint();
    }

    public int getRoundBR() {
        return roundBR;
    }

    public void setRoundBR(int roundBR) {
        this.roundBR = roundBR;
        repaint();
    }

    public int getRoundBL() {
        return roundBL;
    }

    public void setRoundBL(int roundBL) {
        this.roundBL = roundBL;
        repaint();
    }


    private Shape createRoundTopLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTL);
        int roundY = Math.min(height, roundTL);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area; 
    }

    private Shape createRoundTopRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundTR);
        int roundY = Math.min(height, roundTR);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area; 
    }

    private Shape createRoundBottomLeft() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBL);
        int roundY = Math.min(height, roundBL);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area; 
    }

    
    private Shape createRoundBottomRight() {
        int width = getWidth();
        int height = getHeight();
        int roundX = Math.min(width, roundBR);
        int roundY = Math.min(height, roundBR);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area; 
    }

    @Override
    public void updateUI() {
        super.updateUI();
        setBorder(new EmptyBorder(1, 3, 1, 3));
        
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Area area = new Area(createRoundTopLeft());
        if (roundTR > 0) {
            area.intersect(new Area(createRoundTopRight()));
        }
        if (roundTL > 0) {
            area.intersect(new Area(createRoundTopLeft()));
        }
        if (roundBR > 0) {
            area.intersect(new Area(createRoundBottomRight()));
        }
        if (roundBL > 0) {
            area.intersect(new Area(createRoundBottomLeft()));
        }
        g2.fill(area);
        g2.dispose();
        super.paintComponent(g);
    }


}
   
