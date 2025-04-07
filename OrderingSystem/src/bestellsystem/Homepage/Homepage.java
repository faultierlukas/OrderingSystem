package bestellsystem.Homepage;

import bestellsystem.GUI;
import bestellsystem.OrderingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;

public class Homepage extends JPanel {

    public void loadComponents(int categoryID, int userID) {
        removeAll();
        setLayout(null);

        ResultSet products = OrderingSystem.CL.Database.processRequest("SELECT * FROM `product` WHERE " + (categoryID == 0 ? ("1") : "`categoryID`='" + categoryID + "'"));
        ResultSet categorys = OrderingSystem.CL.Database.processRequest("SELECT * FROM `category`");

        int headX = 0;
        int headY = 0;
        int logoWidth = 400;
        int logoHeight = 100;
        int headPadding = 10;
        JPanel Head = new Head_Homepage(logoWidth, logoHeight, headPadding, userID);
        Head.setBounds(headX, headY, GUI.SCREENWIDTH, logoHeight);
        add(Head);

        int sideX = 0;
        int sideY = logoHeight;
        int sideWidth = 250;
        int sideHeight = (GUI.SCREENHEIGHT - logoHeight);
        int sideCategoryHeight = 50;
        int sideHeadingHeight = 37;
        Color sideSelectedColor = Color.GREEN;
        Side_Homepage Side = new Side_Homepage(categoryID, categorys, sideCategoryHeight, sideHeadingHeight, sideWidth, sideHeight, sideSelectedColor, userID);
        if (Side.scroll != null) {
            Side.scroll.setBounds(sideX, sideY, sideWidth, sideHeight);
            add(Side.scroll);
        } else {
            Side.setBounds(sideX, sideY, sideWidth, sideHeight);
            add(Side);
        }

        int mainX = sideWidth;
        int mainY = logoHeight;
        int mainWidth = (GUI.SCREENWIDTH - sideWidth);
        int mainHeight = (GUI.SCREENHEIGHT - logoHeight);
        int ItemsPerColumn = 4;
        int mainPadding = 25;
        int InfoHeight = 50;
        Main_Homepage Main = new Main_Homepage(ItemsPerColumn, mainPadding, InfoHeight, mainWidth, mainHeight, products);
        if (Main.scroll != null) {
            Main.scroll.setBounds(mainX, mainY, mainWidth, mainHeight);
            add(Main.scroll);
        } else {
            Main.setBounds(mainX, mainY, mainWidth, mainHeight);
            add(Main);
        }
    }

    public static ImageIcon getImageFromBlob(Blob blob, int width) {
        if (blob == null) {
            return new ImageIcon(); // Falls kein Bild vorhanden ist
        }
        try {
            byte[] imageBytes = blob.getBytes(1, (int) blob.length());
            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(bais);

            if (bufferedImage != null) {
                return new ImageIcon(bufferedImage.getScaledInstance(width, width, Image.SCALE_SMOOTH));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return new ImageIcon(); // Leeres Bild als Fallback
    }
}
