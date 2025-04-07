package bestellsystem;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {

    public static final int SCREENWIDTH = 1250;
    public static final int SCREENHEIGHT = 750;
    public static final boolean isResizable = false;
    public static final int DefaultCloseOperation = JFrame.EXIT_ON_CLOSE;
    public static final boolean isUndecorated = false;
    public static final boolean isCentered = true;
    
    public GUI(){
        getContentPane().setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        setResizable(isResizable);
        setDefaultCloseOperation(DefaultCloseOperation);
        setUndecorated(isUndecorated);
        pack();
        if(isCentered) setLocationRelativeTo(null);
    }
    
    public void setBackground(JPanel newBackground) {
        getContentPane().removeAll();
        getContentPane().add(newBackground);
        pack();
    }

}
