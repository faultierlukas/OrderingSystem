package bestellsystem.Homepage;

import bestellsystem.OrderingSystem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Side_Homepage extends JPanel {

    public JScrollPane scroll;

    public Side_Homepage(int currentCategory, ResultSet categorys, int categoryHeight, int HeadingHeight, int sideWidth, int sideHeight, Color SelectedColor, int userID) {
        setLayout(null);

        JPanel Heading = new JPanel(new GridBagLayout());
        JLabel HeadingLabel = new JLabel("Categorys");
        String value = "" + ((double) HeadingHeight * 0.72) + "f";
        HeadingLabel.setFont(HeadingLabel.getFont().deriveFont(Float.parseFloat(value)));
        Heading.add(HeadingLabel);
        Heading.setBounds(0, 0, sideWidth, HeadingHeight);
        add(Heading);

        ArrayList<CategoryPanel> categoryPanels = new ArrayList<>();
        categoryPanels.add(null);
        int count = 0;
        try {
            while (categorys.next()) {
                count += categorys.getInt("count");
                categoryPanels.add(new CategoryPanel(categoryHeight, sideWidth, categorys.getString("name"), categorys.getInt("categoryID"), categorys.getInt("count"), Homepage.getImageFromBlob(categorys.getBlob("image"), (int) (categoryHeight * 0.8))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        categoryPanels.set(0, new CategoryPanel(categoryHeight, sideWidth, "all", 0, count, new ImageIcon(new ImageIcon("src//bestellsystem//img//global.png").getImage().getScaledInstance((int) (categoryHeight * 0.8), (int) (categoryHeight * 0.8), Image.SCALE_SMOOTH))));

        int height = (categoryHeight) * categoryPanels.size() + HeadingHeight;

        setPreferredSize(new Dimension(sideWidth, height));
        for (int i = 0; i < categoryPanels.size(); i++) {
            categoryPanels.get(i).setBounds(0, (i * categoryHeight + HeadingHeight), sideWidth, categoryHeight);
            if (currentCategory == (categoryPanels.get(i).categoryID)) {
                categoryPanels.get(i).setBackground(SelectedColor);
                for (Component C : categoryPanels.get(i).getComponents()) {
                    C.setBackground(SelectedColor);
                }
            }
            final int finalI = i;
            categoryPanels.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (currentCategory == categoryPanels.get(finalI).categoryID) {
                        return;
                    }
                    OrderingSystem.CL.Homepage = new Homepage();
                    OrderingSystem.CL.Homepage.loadComponents(categoryPanels.get(finalI).categoryID, userID);
                    OrderingSystem.CL.GUI.setBackground(OrderingSystem.CL.Homepage);
                }
            });
            add(categoryPanels.get(i));
        }

        if (height >= (sideHeight)) {
            scroll = new JScrollPane(this);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scroll.setBorder(null);
        }
    }
}

class CategoryPanel extends JPanel {

    public String categoryName;
    public int categoryID;

    public CategoryPanel(int panelHeight, int panelWidth, String category, int categoryID, int count, ImageIcon icon) {
        this.categoryName = category;
        this.categoryID = categoryID;
        setLayout(null);
        JLabel Icon = new JLabel(icon);
        int padding = (int) (panelHeight * 0.1);
        int size = (int) (panelHeight * 0.8);
        Icon.setBounds(padding, padding, size, size);
        add(Icon);

        JPanel Name = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String Text = " ".repeat(3) + category + " (" + count + ")";
        JLabel NameLabel = new JLabel(Text);
        String value = "" + ((double) panelHeight * 0.4) + "f";
        NameLabel.setFont(NameLabel.getFont().deriveFont(Float.parseFloat(value)));
        Name.setBounds((2 * padding + size), padding, (panelWidth - (3 * size - panelHeight)), (panelHeight - 2 * padding));
        Name.add(NameLabel);
        add(Name);

    }
}
