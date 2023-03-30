import javax.swing.JLabel;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.*;


public class NumBox extends JLabel{

    private int value;
    private int roundTR = 25;
    private int roundTL = 25;
    private int roundBR = 25;
    private int roundBL = 25;

    NumBox(String txt, int property){
        super(txt,property);
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

    void genNum() {
        this.setValue((Math.random() / 2 > 0.5)? 2 : 4);
    }

    void clearValue() {
        this.setText(" ");
        value = 0;
    }

    void increment() {
        this.setValue(this.value*=2);
    }

    int getValue() {
        return this.value;
    }

    void setValue(int v) {
        this.setText(Integer.toString(v));
        this.value = v;
        if (this.value == 0) this.clearValue();
    }

    boolean isEquals(NumBox other){
        if (this.getValue() == other.getValue()){
            return true;
        }
        return false;
    }

    public Color bgColorsSelection(int num) {

        Color ret = new Color(126,171,139, 200);
        switch(num){
            case(0): {
                ret = new Color(126,171,139, 200);
                break;
            }
            case(2): {
                ret = new Color(193,183,132, 200);
                break;
            }
            case(4): {
                ret = new Color(190,175,95, 200);
                break;
            }
            case(8): {
                ret = new Color(150,140,49, 200);
                break;
            }
            case(16): {
                ret = new Color(110,100,55, 200);
                break;
            }
            case(32): {
                ret = new Color(190,190,190, 200);
                break;
            }
            case(64): {
                ret = new Color(115,115,90, 200);
                break;
            }
            case(128): {
                ret = new Color(81, 81, 52, 200);
                break;
            }
            case(256): {
                ret = new Color(45, 45, 30, 200);
                break;
            }
            case(512): {
                ret = new Color(75,40,30, 200);
                break;
            }
            case(1024): {
                ret = new Color(38,6,6, 200);
                break;
            }
            case(2048): {
                ret = new Color(219, 234, 79, 200);
                break;
            }
        }
        return ret;
    }
}