import javax.swing.JLabel;
public class numBox extends JLabel{

    private int value;

    numBox(String txt, int property){
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

    boolean isEquals(numBox other){
        if (this.getValue() == other.getValue()){
            return true;
        }
        return false;
    }
}
