package model;

import java.text.*;

public class Person
{   private String name;
    private int start = 0;
    private int end = 0;
    private double cash = 100.00;
    private double charge = 0;
    
    public Person(String name)
    {   this.name = name;   }
        
    public boolean getsOn(int stop)
    {   return start == stop;  }
        
    public boolean getsOff(int stop)
    {   return end == stop;  }
    
    public boolean isOn(int stop)
    {   return (start < stop) && (end >= stop); }
        
    public String name()
    {   return name;    }
    
    public String cash()
    {   return "$" + formatted(cash);  }
    
    private String formatted(double amount)
    {   DecimalFormat form = new DecimalFormat("###,##0.00");
        return form.format(amount);  }
    
    public void book(int start, int end)
    {   this.start = start;
        this.end = end; }
    
    public void add(double amount)
    {   charge += amount;   }
    
    public boolean uses(int stop)
    {   return getsOn(stop) || getsOff(stop); }
    
    public void pay()
    {   cash -= charge;
        charge = 0;
        start = 0;
        end = 0; }
        
    public boolean matches(String handle)
    {   return handle.equals(handle()); }
    
    public String handle()
    {   return name;    }
}
