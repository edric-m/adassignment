package model;

import java.util.*;

public class Clients extends Viewable
{   private LinkedList<Person> clients = new LinkedList<Person>();
    
    public Clients()
    {   clients.add(new Person("Homer"));
        clients.add(new Person("Marge")); 
        clients.add(new Person("Bart")); 
        clients.add(new Person("Lisa"));   }
        
    public void add(String name)
    {   clients.add(new Person(name));
        update();    }
    
    public void remove(String name)
    {   Person person = person(name);
        if (person == null)
            System.out.println("  No such client");
        else
            clients.remove(person);   }
        
    public Person person(String handle)
    {   for (Person client: clients)
            if (client.matches(handle))
                return client;
        return null;    }
        
    public int size()
    {   return clients.size();  }
    
    public LinkedList<Person> clients()
    {   return clients; }
}