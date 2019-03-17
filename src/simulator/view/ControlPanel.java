package simulator.view;

import org.json.JSONObject;
import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ControlPanel extends JPanel implements SimulatorObserver {

    private Controller ctrl;
    private boolean stopped;

    private JToolBar toolBar;
    private JFileChooser fileChooser;
    private JButton file, law, start, stop, close;
    private JSpinner numSteeps;
    private JTextField deltaTime;
    private JLabel numSteepsLabel, deltaTimeLabel;

    public ControlPanel(Controller ctrl){
        super();
        this.ctrl = ctrl;
        stopped = true;
        initGUI();
        this.ctrl.addObserver(this);
    }

    private void initGUI(){
        toolBar = new JToolBar();
        fileChooser = new JFileChooser(new File(System.getProperty("user.dir")+ "/src/resources"));
        numSteepsLabel = new JLabel("Steeps:");
        deltaTimeLabel = new JLabel("Delta-Time:");
        file = new JButton(new ImageIcon("src/resources/icons/open.png"));
        file.setToolTipText("Load a file to simulate");
        law = new JButton(new ImageIcon("src/resources/icons/physics.png"));
        law.setToolTipText("Select a law to simulate");
        start = new JButton(new ImageIcon("src/resources/icons/run.png"));
        start.setToolTipText("Run simulation");
        stop = new JButton(new ImageIcon("src/resources/icons/stop.png"));
        stop.setToolTipText("Stop simulation");
        close = new JButton(new ImageIcon("src/resources/icons/exit.png"));
        close.setToolTipText("Close physics simulator");
        numSteeps = new JSpinner();
        deltaTime = new JTextField(7);

        file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION){
                    System.out.println(fileChooser.getSelectedFile().getName());
                    //InputStream in = new FileInputStream(fileChooser.getSelectedFile().getName());
                    //ctrl.loadBodies(in);
                }
            }
        });

        law.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> availableLaws = new ArrayList<>();

                for(JSONObject o : ctrl.getGravityLawsFactory().getInfo()){
                    availableLaws.add(o.getString("desc") + " (" + o.get("type") + ")");
                }
                //todo check
                String law = (String) JOptionPane.showInputDialog(getParent(),
                        "Select gravity laws to be used",
                        "Gravity Laws selector",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        availableLaws.toArray(),
                        ctrl.getGravityLawsFactory().getInfo().get(0));

                System.out.println(law);

            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopped = true;
                enabledButtons(false);
                //stopped = false;
                //ctrl.setDeltaTime(Double.parseDouble(deltaTime.getText()));
                //run_sim(Integer.parseInt(numSteeps.getValue().toString()));
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enabledButtons(true);
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to quit?", "Quit",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        toolBar.add(file);
        //toolBar.add(new JSeparator(SwingConstants.VERTICAL));
        toolBar.addSeparator();
        toolBar.add(law);
        toolBar.addSeparator();
        toolBar.add(start);
        toolBar.add(stop);
        toolBar.add(numSteepsLabel);
        toolBar.add(numSteeps);
        toolBar.add(deltaTimeLabel);
        toolBar.add(deltaTime);
        toolBar.addSeparator(new Dimension(800,0));
        toolBar.add(close);
        toolBar.setFloatable(false);

        add(toolBar);

        /*
        add(file);
        add(law);
        add(start);
        add(stop);
        add(numSteepsLabel);
        add(numSteeps);
        add(deltaTimeLabel);
        add(deltaTime);
        add(close);
        */
    }


    private void run_sim(int n){
        if(n>0 && !stopped){
            try {
                ctrl.run(1);
            }catch (Exception e){
                //TODO show the error in a dialog box
                //TODO enable all buttons
                stopped = true;
                return;
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    run_sim(n-1);
                }
            });
        }else{
            stopped = true;
            //TODO enable all buttons
        }
    }

    @Override
    public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {

    }

    @Override
    public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {

    }

    @Override
    public void onBodyAdded(List<Body> bodies, Body b) {

    }

    @Override
    public void onAdvance(List<Body> bodies, double time) {

    }

    @Override
    public void onDeltaTimeChanged(double dt) {

    }

    @Override
    public void onGravityLawChanged(String gLawsDesc) {

    }

    private void enabledButtons(boolean enable){
        file.setEnabled(enable);
        law.setEnabled(enable);
        start.setEnabled(enable);
        close.setEnabled(enable);
    }
}
