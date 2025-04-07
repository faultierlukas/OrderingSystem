package bestellsystem.Homepage;

import Profil.Profile;
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

public class Head_Homepage extends JPanel {

    public Head_Homepage(int logoWidth, int logoHeight, int padding, int userID) {
        /*
        features
        Profil -> Anmelden, wenn angemeldet Einstellungen (user: zum Profil, admin: Werte ändern, Produkte bearbeiten)
        Bestellungen ansehen
        beenden
        
         */
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel IconLabel = new JLabel();
        IconLabel.setIcon(new ImageIcon("src//bestellsystem//img//logo_" + logoWidth + ".png"));
        IconLabel.setBounds(0, 0, logoWidth, logoHeight);
        add(IconLabel);

        JPanel Border = new JPanel();
        Border.setBackground(Color.BLACK);
        Border.setBounds((logoWidth + padding), (int) (logoHeight / 2), (bestellsystem.GUI.SCREENWIDTH - logoWidth - (2 * padding)), 1);
        add(Border);

        int iconSize = (logoHeight - (4 * padding)) / 2;
        JTextField input = new JTextField();
        String value = (double) iconSize * 0.6 + "f";
        input.setFont(input.getFont().deriveFont(Float.parseFloat(value)));
        input.setBounds((logoWidth + padding), ((logoHeight / 2) + padding), (bestellsystem.GUI.SCREENWIDTH - logoWidth - (3 * padding) - iconSize), iconSize);
        add(input);

        JLabel Search = new JLabel(new ImageIcon(new ImageIcon("src//bestellsystem//img//suche.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        Search.setBounds((bestellsystem.GUI.SCREENWIDTH - padding - iconSize), (int) ((logoHeight / 2) + padding), iconSize, iconSize);
        Search.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                OrderingSystem.CL.SearchResult = new SearchResult();
                OrderingSystem.CL.GUI.setBackground(OrderingSystem.CL.SearchResult);
            }
        });
        add(Search);

        JLabel Profile = new JLabel(new ImageIcon(new ImageIcon("src//bestellsystem//img//profil.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        Profile.setBounds((bestellsystem.GUI.SCREENWIDTH - (2 * iconSize) - (3 * padding)), padding, iconSize, iconSize);
        Profile.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                OrderingSystem.CL.ProfilePanel = new Profile(userID);
                OrderingSystem.CL.GUI.setBackground(OrderingSystem.CL.ProfilePanel);
            }
        });
        add(Profile);

        JLabel Aus = new JLabel(new ImageIcon(new ImageIcon("src//bestellsystem//img//aus.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        Aus.setBounds((bestellsystem.GUI.SCREENWIDTH - iconSize - padding), padding, iconSize, iconSize);
        Aus.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (OrderingSystem.CL.Database.closeConnection()) {
                    System.exit(0);
                }
            }
        });
        add(Aus);

        JLabel LoremIpsum = new JLabel("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy");
        LoremIpsum.setFont(LoremIpsum.getFont().deriveFont(Float.parseFloat(value)));
        LoremIpsum.setBounds((logoWidth + padding), padding, (bestellsystem.GUI.SCREENWIDTH - logoWidth - (5 * padding) - (2*iconSize)), iconSize);
        add(LoremIpsum);

    }
}