import model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainPanel extends JPanel
{   private JTextField name = new JTextField(9);
    private JTextField start = new JTextField(9);
    private JTextField end = new JTextField(9);
    private JList list;
    private Boats boats;
    private Clients clients;

    public MainPanel(Boats boats, Clients clients)
    {   this.boats = boats;
        this.clients = clients;
        setup();
        build(boats);   }
        
    private void setup()
    {   setBorder(BorderFactory.createLineBorder(Color.blue));
        setLayout(new FlowLayout());  }
        
    private void setSize(JComponent c, int x)
    {   c.setPreferredSize(new Dimension(x, 20));
        c.setMinimumSize(new Dimension(x, 20));
        c.setMaximumSize(new Dimension(x, 20)); }
    
    private void build(Boats boats)
    {   Box box = Box.createHorizontalBox();
        box.add(inputBox());
        box.add(listBox());
        add(box);   }
        
    private Box inputBox()
    {   Box box = Box.createVerticalBox();
        box.add(pair("Name ", name, 100 ));
        box.add(pair("  Start ", start, 50 ));
        box.add(pair("    End ", end, 50 ));
        return box; }
        
    private Box pair(String label, JTextField field, int size)
    {   Box box = Box.createHorizontalBox();
        setSize(box, 150 );
        box.add(new JLabel(label));
        box.add(Box.createHorizontalStrut(5));
        setSize(field,size);
        box.add(field);
        return box; }
    
    private Box listBox()
    {   Box box = Box.createVerticalBox();
        box.add(Box.createHorizontalStrut(100));
        box.add(theList());
        return box; }
    
    private JList theList()
    {   list = new JList(values());   
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new Listener());
        return list;    }
        
    private String[] values()
    {   String[] values = new String[boats.size()];
        int i = 0;
        for (Boat boat: boats.boats())
            values[i++] = "Boat " + boat.id();
        return values;  }

    private void clear()
    {   name.setText("");
        start.setText("");
        end.setText("");
        list.clearSelection();  }
    
    private class Listener implements ListSelectionListener
    {   public void valueChanged(ListSelectionEvent e)
        {   if (e.getValueIsAdjusting())
                return;
            if (list.getSelectedIndex() == -1)
                return; 
            int selected = list.getSelectedIndex();
            Boat boat = boats.boat(selected);
            boat.book(name.getText(), 
                        Integer.parseInt(start.getText()), 
                        Integer.parseInt(end.getText()),
                        clients);
            clear();  }
    }
}
