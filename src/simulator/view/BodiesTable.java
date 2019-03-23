package simulator.view;

import simulator.control.Controller;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BodiesTable extends JPanel{

    BodiesTable(Controller ctrl) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),
                "Bodies", TitledBorder.LEFT, TitledBorder.TOP));

        BodiesTableModel model = new BodiesTableModel(ctrl);
        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        this.add(scroll);
    }
}
