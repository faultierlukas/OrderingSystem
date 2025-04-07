package Search;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchResult extends JPanel {

    public SearchResult(){
        setLayout(new GridBagLayout());
        add(new JLabel("Suchergebnisse!"));
    }
}
