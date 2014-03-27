
import java.awt.Dimension;
import javax.swing.JFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alex
 */
public class DIView {

    public static final int FRAME_WIDTH = 80 * 16;
    public static final int FRAME_HEIGHT = 80 * 9;
    private JFrame frame;
    private DIPanel panel;

    public static int getScreenWorkingWidth() {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }

    public static int getScreenWorkingHeight() {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }

    public DIView() {
        frame = new JFrame("Definite Integral");
        panel = new DIPanel();
    }

    public void launchFrame() {
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setLocation((getScreenWorkingWidth()-FRAME_WIDTH)/2,
                (getScreenWorkingHeight()-FRAME_HEIGHT)/2);
        frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);
    }

    public void graph(Function func, int a, int b, int rects) {
        panel.graph(func, a, b, rects);
    }
}
