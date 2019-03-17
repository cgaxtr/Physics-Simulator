package simulator.view;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatusBar extends JPanel implements SimulatorObserver {

    private static final String TEXT_TIME = "Time:";
    private static final String TEXT_LAW = "Laws:";
    private static final String TEXT_BODIES = "Bodies:";

    private Controller ctrl;

    private JLabel currTime;
    private JLabel currLaws;
    private JLabel numOfBodies;


    StatusBar(Controller ctrl){
        initGUI();
        this.ctrl = ctrl;
        this.ctrl.addObserver(this);
    }

    private void initGUI(){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder( BorderFactory.createBevelBorder( 1 ));

        currTime = new JLabel(TEXT_TIME);
        currLaws = new JLabel(TEXT_LAW);
        numOfBodies = new JLabel(TEXT_BODIES);

        add(currTime);
        add(currLaws);
        add(numOfBodies);

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
}
