package bestellsystem;

public class OrderingSystem {

    public static ClassList CL;

    public static void main(String[] args) {
        CL = new ClassList();
        if (CL.Database.createConnection()) {
            //CL.Database.createContent();
            CL.Homepage.loadComponents(0,-1);
            CL.GUI.setBackground(CL.Homepage);
        }
        CL.GUI.setVisible(true);
    }

}
