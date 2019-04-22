package simulator.view;

import simulator.control.Controller;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Viewer extends JComponent implements SimulatorObserver {

    private static final int _WIDTH = 1000;
    private static final int _HEIGHT = 1000;

    private static final Color BLUE = Color.BLUE;
    private static final Color RED = Color.RED;
    private static final Color BLACK = Color.black;

    private static final int CROSS_SIZE = 4;
    private static final int BODY_SIZE = 6;

    private int _centerX;
    private int _centerY;
    private double _scale;
    private List<Body> _bodies;
    private boolean _showHelp;

    Viewer(Controller ctrl){
        initGUI();
        ctrl.addObserver(this);
        setPreferredSize(new Dimension(_WIDTH, _HEIGHT));
    }

    private void initGUI() {
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),
                "Viewer", TitledBorder.LEFT, TitledBorder.TOP));
        _bodies = new ArrayList<>();
        _scale = 1.0;
        _showHelp = true;

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()){
                    case '-': _scale *= 1.1;
                        break;
                    case '+': _scale = Math.max(1000.0, _scale/1.1);
                        break;
                    case '=': autoScale();
                        break;
                    case 'h': _showHelp = !_showHelp;
                        break;
                    default:
                }
                repaint();
            }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) { }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                requestFocus();
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g;
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        _centerX = getWidth() / 2;
        _centerY = getHeight() / 2;

        //draw cross in the middle
        gr.setColor(RED);
        gr.drawLine(_centerX - CROSS_SIZE, _centerY, _centerX + CROSS_SIZE, _centerY);
        gr.drawLine(_centerX, _centerY - CROSS_SIZE, _centerX, _centerY + CROSS_SIZE);

        //draw bodies and tag
        for(Body b : _bodies){

            gr.setColor(BLUE);
            int x = _centerX + (int) (b.getPosition().coordinate(0)/_scale);
            int y = _centerY + (int) (b.getPosition().coordinate(1)/_scale);
            gr.fillOval(x , y, BODY_SIZE, BODY_SIZE);

            gr.setColor(BLACK);
            gr.drawString(b.getId(), x - (BODY_SIZE/2), y - BODY_SIZE);

        }

        //draw help
        if (_showHelp){
            gr.setColor(RED);
            gr.drawString("h: toggle help, +: zoom-in, -: zoom-out, =: fit", 10, 25);
            gr.drawString("Scaling ration: " + _scale, 10, 25 + gr.getFontMetrics().getHeight());
        }

    }


    private void autoScale(){
        double max = 1.0;
        for(Body b : _bodies){
            Vector p = b.getPosition();
            for (int i = 0; i < p.dim(); i++)
                max = Math.max(max, Math.abs(b.getPosition().coordinate(i)));
        }
        double size = Math.max(1.0, Math.min((double) getWidth(), (double) getHeight()));
        _scale = max > size ? 4.0 * max / size : 1.0;
    }


    @Override
    public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
        this._bodies = bodies;
        autoScale();
        repaint();
    }

    @Override
    public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
        this._bodies = bodies;
        autoScale();
        repaint();
    }

    @Override
    public void onBodyAdded(List<Body> bodies, Body b) {
        this._bodies = bodies;
        autoScale();
        repaint();
    }

    @Override
    public void onAdvance(List<Body> bodies, double time) {
        repaint();
    }

    @Override
    public void onDeltaTimeChanged(double dt) { }

    @Override
    public void onGravityLawChanged(String gLawsDesc) { }
}
