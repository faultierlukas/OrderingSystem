package Profil;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Profile extends JPanel {

    public Profile(int userID) {
        setLayout(null);
        if (userID == -1) {
            Head_Profile Head = new Head_Profile(400, 100, 10, userID, "Anmeldung");
            Head.setBounds(0, 0, bestellsystem.GUI.SCREENWIDTH, 100);
            add(Head);
        } else {

            add(new JLabel("Profil!"));
        }

    }
}
