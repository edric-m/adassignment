//Edric Mendoza 11731039

import model.*;

import java.awt.*;
import javax.swing.*;

public class Root extends JFrame
{   public static void main(String[] args)
    {   new Root(new Boats(),new Clients());    }
    
    private int x = 500;    private int y = 500;
    
    public Root(Boats boats, Clients clients)
    {   setup();
        build(boats,clients);
        pack();
        setVisible(true);   }
        
    private void setup()
    {  setLocation(x, y);
       setDefaultCloseOperation(EXIT_ON_CLOSE); }
        
    private void build(Boats boats, Clients clients)
    {   add(new MainPanel( boats, clients ));
        new BoatWindow(boats.boat(0), clients, x+300, y );
        new BoatWindow(boats.boat(1), clients, x+300, y+150 );
        new BoatWindow(boats.boat(2), clients, x+300, y+300 );
        new ClientWindow(clients, x, y+120 );  }
}
