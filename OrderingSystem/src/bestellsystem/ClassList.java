package bestellsystem;

import Profil.Profile;
import Search.SearchResult;
import bestellsystem.Homepage.Homepage;

public class ClassList {

    public Database Database;
    public GUI GUI;

    public Homepage Homepage;
    public SearchResult SearchResult;
    public Profile ProfilePanel;
    
    public ClassList(){
        Database = new Database();
        GUI = new GUI();
        
        Homepage = new Homepage();
    }
}
