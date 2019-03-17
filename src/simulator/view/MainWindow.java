package simulator.view;

import simulator.control.Controller;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.PAGE_END;
import static java.awt.BorderLayout.PAGE_START;

public class MainWindow extends JFrame {

    Controller ctrl;
    private ControlPanel controlPanel;
    private StatusBar statusBar;

    public MainWindow(Controller ctrl){
        super("Physics Simulator");
        this.ctrl = ctrl;
        controlPanel = new ControlPanel(ctrl);
        statusBar = new StatusBar(ctrl);
        initGUI();
    }

    private void initGUI(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        setIconImage(new ImageIcon("src/resources/icons/physics.png").getImage());
        add(controlPanel, PAGE_START);
        add(statusBar, PAGE_END);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);


        //PAGE_START control panel
        //PAGE_END barra estado
        //boxLayout center

    }
}
