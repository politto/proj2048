import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

class ButtonClickListener implements ActionListener{
    
    public void actionPerformed(ActionEvent ev){

        JButton source = (JButton)ev.getSource();
        //System.out.println(source);
        CoreSystem.clearAllValue();
        CoreSystem.clearIntScore();
        if(source == mainApperence.getCloseGameButton() || source.getText().equals("Quit")) System.exit(1);
        else {
            mainApperence.getPopup().setVisible(false);
            mainApperence.getWindow().setVisible(false);
            new CoreSystem();
        }
    }

}
