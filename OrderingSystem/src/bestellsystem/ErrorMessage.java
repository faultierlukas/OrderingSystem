package bestellsystem;

import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ErrorMessage extends JPanel {

    public ErrorMessage(String Class, String Message){
        setLayout(null);
        
        JPanel Head = new JPanel(new GridBagLayout());
        JLabel HeadLabel =  new JLabel("Error in Class: " + Class);
        HeadLabel.setFont(HeadLabel.getFont().deriveFont(50f));
        Head.add(HeadLabel);
        Head.setBounds(0,0,GUI.SCREENWIDTH, 250);
        add(Head);
        
        JPanel Body = new JPanel(new GridBagLayout());
        JLabel BodyLabel = new JLabel(Message);
        BodyLabel.setFont(BodyLabel.getFont().deriveFont(30f));
        Body.add(BodyLabel);
        Body.setBounds(0,250,GUI.SCREENWIDTH, 400);
        add(Body);
        
        JPanel Foot = new JPanel(new GridBagLayout());
        JButton FootButton = new JButton("OK, exit program.");
        FootButton.setFont(FootButton.getFont().deriveFont(30f));
        FootButton.setFocusable(false);
        FootButton.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                OrderingSystem.CL.Database.closeConnection();
                System.exit(0);
            }
        });
        Foot.add(FootButton);
        Foot.setBounds(0,650,GUI.SCREENWIDTH, 100);
        add(Foot);
    }
}
