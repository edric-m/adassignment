package model;

import java.util.*;

public class Boats
{   private LinkedList<Boat> boats = new LinkedList<Boat>();
    
    public Boats()
    {   boats.add(new Boat(1, 3));
        boats.add(new Boat(2, 4));
        boats.add(new Boat(3, 5));   }
        
    public Boat boat(int id)
    {   return boats.get(id);    }
        
    public LinkedList<Boat> boats()
    {   return boats;   }
    
    public int size()
    {   return boats.size();    }
}