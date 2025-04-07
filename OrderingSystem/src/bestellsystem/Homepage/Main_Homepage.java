package bestellsystem.Homepage;

import static bestellsystem.Homepage.Homepage.getImageFromBlob;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Main_Homepage extends JPanel {
    
    JScrollPane scroll;
    
    public Main_Homepage(int ItemsPerColumn, int padding, int descHeight, int sumWidth, int sumHeight, ResultSet products) {
        setLayout(null);
        
        int width = ((-padding * ItemsPerColumn + (sumWidth - padding)) / ItemsPerColumn);

        ArrayList<JPanel> productPanels = new ArrayList<>();
        try {
            while (products.next()) {
                ImageIcon image = getImageFromBlob(products.getBlob("image"), width); // Bild als Blob aus DB
                String name = products.getString("name"); // Produktname
                int amount = products.getInt("amount"); // Anzahl
                String description = products.getString("description"); // Beschreibung
                productPanels.add(new ProductPanel(width, descHeight, image, name, amount, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int productCount = productPanels.size();

        for (int i = 0; i < productCount; i++) {
            int x = (i % ItemsPerColumn) * (width + padding) + padding;
            int y = (i / ItemsPerColumn) * (width + padding + descHeight) + padding;
            productPanels.get(i).setBounds(x, y, width, (width + descHeight));
            add(productPanels.get(i));
            JPanel Border = new JPanel();
            Border.setBackground(Color.BLACK);
            Border.setBounds(x - 1, y - 1, width + 2, ((width + descHeight) + 2));
            add(Border);
        }

        //Höhe der Panels (
        int rows = (int) Math.ceil(productCount / (ItemsPerColumn * 1.0));
        int height = rows * (width + descHeight) + (rows + 1) * padding;

        setPreferredSize(new Dimension(sumWidth, height));
        JPanel Border = new JPanel();
        Border.setBounds(0, 0, 1, height > sumHeight ? height : sumHeight);
        Border.setBackground(Color.BLACK);
        add(Border);

        //setBackground(new Color(222, 214, 213));
        if (height >= (sumHeight - 2 * padding)) {
            scroll = new JScrollPane(this);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scroll.setBorder(null);
        }
    }
}

class ProductPanel extends JPanel {

    public ProductPanel(int width, int descHeight, ImageIcon image, String name, int amount, String description){
        setLayout(null);
        setPreferredSize(new Dimension(width, (width+descHeight)));

        // Produktbild
        JLabel imageLabel = new JLabel(image);
        imageLabel.setBounds(0, 0, width,width);
        add(imageLabel);

        JPanel Info = new JPanel(null);
        
        JPanel Name = new JPanel(new GridBagLayout());
        JLabel NameLabel = new JLabel(name + " ("+amount+")");
        NameLabel.setFont(NameLabel.getFont().deriveFont(Float.parseFloat((descHeight*0.5)+"f")));
        Name.add(NameLabel);
        Name.setBackground(new Color(230,230,230));
        Name.setBounds(0,0,width, (int) (descHeight*0.6));
        Info.add(Name);
       
        
        JPanel Desc = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel DescLabel = new JLabel(description);
        DescLabel.setFont(DescLabel.getFont().deriveFont(Float.parseFloat((descHeight*0.2)+"f")));
        Desc.add(DescLabel);
        Desc.setBounds(0, (int) (descHeight*0.6),width, (int) (descHeight*0.4));
        Desc.setBackground(new Color(232,232,232));
        Info.add(Desc);
       
        Info.setBounds(0,width,width,descHeight);
        add(Info);
    }
    
    
}
