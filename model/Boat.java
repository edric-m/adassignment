package model;

import java.util.*;

public class Boat extends Viewable
{   private int id;
    private int stops;
    private LinkedList<Person> passengers = new LinkedList<Person>();
    private double rate = 10.00;
    
    public Boat(int id, int stops)
    {   this.id = id;
        this.stops = stops;   }
        
    public boolean has(int id)
    {   return this.id == id;  }
    
    public void book(String name, int start, int end, Clients clients)
    {   Person client = clients.person(name);
        if(client == null){
            clients.add(name); 
            client = clients.person(name);  }
        client.book(start,end);
        passengers.add(client);  
        update();   }
             
    public String on(int i)
    {   String s = "";
        for (Person person: passengers)
            if (person.getsOn(i))
                s += person.name() + " ";
        return s;  }
        
    public String off(int i)
    {   String s = "";
        for (Person person: passengers)
            if (person.getsOff(i))
                s += person.name() + " ";
        return s;   }
                
    public int id()
    {   return id;  }
    
    public int stops()
    {   return stops;  }
    
    public void go()
    {   for (int stop = 0; stop < stops; stop++)
        {   charge(stop);
            if (stopAt(stop))
            {   pay(stop); }}
        passengers.clear();   
        update();    }
    
    private void charge(int stop)
    {   int people = used(stop);
        if (people == 0)
            return;
        double charge = rate / people;
        for (Person person: passengers)
            if (person.isOn(stop))
                person.add(charge);   }
                
    private int used(int stop)
    {   int used = 0;
        for (Person person: passengers)
            if (person.isOn(stop))
                used++;
        return used;    }
        
    private boolean stopAt(int i)
    {   for (Person person: passengers)
            if (person.uses(i))
                return true;
        return false;   }
        
    private void pay(int i)
    {   for (Person person: passengers)
            if (person.getsOff(i))
                person.pay();   }
}
