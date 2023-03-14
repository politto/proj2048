import javax.swing.JLabel;
import java.awt.Color;
public class NumBox extends JLabel{

    private int value;

    NumBox(String txt, int property){
        super(txt,property);
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
}
