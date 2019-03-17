package simulator.view;

import simulator.model.Body;
import simulator.model.SimulatorObserver;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {
    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
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
