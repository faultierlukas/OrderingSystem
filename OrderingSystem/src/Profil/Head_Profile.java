package Profil;

import bestellsystem.Homepage.*;
import Search.SearchResult;
import bestellsystem.OrderingSystem;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Head_Profile extends JPanel {

    public Head_Profile(int logoWidth, int logoHeight, int padding, int userID, String Title) {
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel IconLabel = new JLabel();
        IconLabel.setIcon(new ImageIcon("src//bestellsystem//img//logo_" + logoWidth + ".png"));
        IconLabel.setBounds(0, 0, logoWidth, logoHeight);
        IconLabel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                OrderingSystem.CL.Homepage = new Homepage();
                OrderingSystem.CL.Homepage.loadComponents(0, userID);
                OrderingSystem.CL.GUI.setBackground(OrderingSystem.CL.Homepage);
            }
        });
        add(IconLabel);

        String value = (double) logoHeight * 0.4 + "f";
        JLabel TitleLabel = new JLabel(Title);
        TitleLabel.setFont(TitleLabel.getFont().deriveFont(Float.parseFloat(value)));
        TitleLabel.setBounds((logoWidth + padding), padding, (bestellsystem.GUI.SCREENWIDTH - logoWidth - (2 * padding)), (logoHeight - 2 * padding));
        add(TitleLabel);

    }
}
