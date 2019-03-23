package simulator.view;

import simulator.control.Controller;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.*;

public class MainWindow extends JFrame {

    Controller ctrl;
    private ControlPanel controlPanel;
    private StatusBar statusBar;
    private BodiesTable bodiesTable;
    private Viewer viewer;

    public MainWindow(Controller ctrl){
        super("Physics Simulator");
        this.ctrl = ctrl;
        initGUI();
    }

    private void initGUI(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
        setIconImage(new ImageIcon("src/resources/icons/physics.png").getImage());

        controlPanel = new ControlPanel(ctrl);
        statusBar = new StatusBar(ctrl);
        bodiesTable = new BodiesTable(ctrl);
        viewer = new Viewer(ctrl);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        centerPanel.add(bodiesTable);
        centerPanel.add(viewer);


        add(controlPanel, PAGE_START);
        add(centerPanel, CENTER);
        add(statusBar, PAGE_END);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
