package simulator.view;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {

    private List<Body> bodies;
    private final String[] columnNames = { "Id", "Mass", "Position", "Velocity", "Acceleration" };

    BodiesTableModel(Controller ctrl){
        bodies = new ArrayList<>();
        ctrl.addObserver(this);
    }

    @Override
    public int getRowCount() {
        return bodies.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int index) {
        return columnNames[index];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Body b  = bodies.get(rowIndex);
        String ret = null;

        switch (columnIndex){
            case 0:
                ret = b.getId();
                break;
            case 1:
                ret = Double.toString(b.getMass());
                break;
            case 2:
                ret = b.getPosition().toString();
                break;
            case 3:
                ret = b.getVelocity().toString();
                break;
            case 4:
                ret = b.getAcceleration().toString();
                break;
        }

        return ret;
    }

    @Override
    public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
        this.bodies = bodies;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                fireTableDataChanged();
            }
        });
    }

    @Override
    public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
        this.bodies = bodies;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                fireTableDataChanged();
            }
        });
    }

    @Override
    public void onBodyAdded(List<Body> bodies, Body b) {
        this.bodies = bodies;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                fireTableDataChanged();
            }
        });
    }

    @Override
    public void onAdvance(List<Body> bodies, double time) {
        this.bodies = bodies;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                fireTableDataChanged();
            }
        });
    }

    @Override
    public void onDeltaTimeChanged(double dt) {}

    @Override
    public void onGravityLawChanged(String gLawsDesc) {}
}
