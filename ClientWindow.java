import model.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class ClientWindow extends JFrame
{   public ClientWindow(Clients clients, int x, int y)
    {   setup(x, y );
        build(clients);
        pack();
        setVisible(true);   }
        
    private void setup(int x, int y)
    {   setLocation(x, y ); 
        setLayout(new FlowLayout());    }
        
    private void build(Clients clients)
    {   setTitle("Clients");
        add(new ClientPanel(clients));  }

    private class ClientPanel extends JPanel implements View
    {   private Clients clients;
        private ClientTable model;
        private JTable table;
        
        public ClientPanel(Clients clients)
        {   this.clients = clients;
            clients.attach(this);
            setup();
            build();    }
    
        private void setup()
        {   setBorder(BorderFactory.createLineBorder(Color.red));
            model = new ClientTable();
            table = new JTable(model);
            table.setBorder(BorderFactory.createLineBorder(Color.red)); }
        
        private void build()
        {   Box box = Box.createVerticalBox();
            table.getColumnModel().getColumn(0).setPreferredWidth(100);
            table.getColumnModel().getColumn(1).setPreferredWidth(70);
            box.add(table.getTableHeader());
            box.add(table);
            add(box);   }
          
        public void update()
        {   model.fireTableDataChanged();   }
    
        private class ClientTable extends AbstractTableModel
        {   private final int cols = 2;
            private final String[] columnNames = {"Name", "Cash"};
            
            public String getColumnName(int col)
            {   return columnNames[col];    }
            
            public int getRowCount()
            {   return clients.size(); }
            
            public int getColumnCount()
            {   return cols;    }
            
            public Object getValueAt(int row, int col)
            {   LinkedList<Person> list = new LinkedList<Person>();
                list = clients.clients();
                switch(col)
                {   case 0: return "   " + list.get(row).handle();
                    case 1: return "  " + list.get(row).cash();
                    default: return ""; }    }
        }
    }
}