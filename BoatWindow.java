import model.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

public class BoatWindow extends JFrame
{   public BoatWindow(Boat boat, Clients clients, int x, int y)
    {   setup(x, y );
        build(boat, clients );
        pack();
        setVisible(true);   }
        
    private void setup(int x, int y)
    {   setLocation(x, y );  }
        
    private void build(Boat boat, Clients clients)
    {   setTitle("Boat " + boat.id());
        add(new BoatPanel(boat, clients ));   }

    private class BoatPanel extends JPanel implements View
    {   private Boat boat;
        private Clients clients;
        private BoatTable model;
        private JTable table;
        
        public BoatPanel(Boat boat, Clients clients)
        {   this.boat = boat;
            this.clients = clients;
            boat.attach(this);
            clients.attach(this);
            setup();
            build();    }
    
        private void setup()
        {   setBorder(BorderFactory.createLineBorder(Color.green));
            model = new BoatTable();
            table = new JTable(model);
            table.setBorder(BorderFactory.createLineBorder(Color.green));    }
        
        private void build()
        {   Box box = Box.createHorizontalBox();
            box.add(table());
            box.add(Box.createHorizontalStrut(10));
            JButton button = new JButton("Go");
            button.addActionListener(new Listener());
            box.add(button);
            add(box);  }
          
        private Box table()
        {   table.getColumnModel().getColumn(0).setPreferredWidth(30);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            Box box = Box.createVerticalBox();
            box.add(table.getTableHeader());
            box.add(table);
            return box; }
          
        public void update()
        {   model.fireTableDataChanged();   }
    
        private class BoatTable extends AbstractTableModel
        {   private final int cols = 3;
            private final String[] columnNames = {"Stop", "On", "Off"};
            
            public String getColumnName(int col)
            {   return columnNames[col];    }
            
            public int getRowCount()
            {   return boat.stops(); }
            
            public int getColumnCount()
            {   return cols;    }
            
            public Object getValueAt(int row, int col)
            {   switch(col)
                {
                    case 0: return "  " + row;
                    case 1: return boat.on(row);
                    case 2: return boat.off(row);
                    default: return ""; }   }
        }
    
        private class Listener implements ActionListener
        {   public void actionPerformed(ActionEvent e)
            {   boat.go(); 
                clients.update();   }
        }
    }
}