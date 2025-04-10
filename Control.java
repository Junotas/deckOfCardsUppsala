import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Control extends JPanel implements ActionListener {
    private JButton blipButton  = new JButton("blip");
    private JButton blupButton  = new JButton("blup");
    private JLabel  label      = new JLabel("????");

    public Control () { 

        setBackground(Color.GREEN);
        
        add(blipButton);
        add(blupButton);
        add(label);
	
        blipButton.addActionListener(this); 
        blupButton.addActionListener(this); 
    }

    public void actionPerformed(ActionEvent event) {
	if (event.getSource() == blipButton) {
            label.setText("blip");
        }
        else if (event.getSource() == blupButton) {
            label.setText("blup");
        }
    }
}
