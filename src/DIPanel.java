
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alex
 */
public class DIPanel extends JPanel {

    private final int ORIGIN_X = DIView.FRAME_WIDTH / 2;  //pixels along x-axis to origin
    private final int ORIGIN_Y = DIView.FRAME_HEIGHT / 2; //pixels along y-axis to origin
    private Function function;      //function to graph
    private Rectangle rectangles[];     //array of rectangles to graph
    private int a, b;   //left and right bounds
    private final double x_interval = 1;    //pixels along x-axis between each point graphed (smaller means more precise graph)

    public void paintComponent(Graphics g) {
        cls(g);
        paintAxes(g);
        paintRectangles(g);
        paintFunction(g);
        estimateArea();
    }

    public void graph(Function func, int a, int b, int rects) {
        this.function = func;
        this.a = a;
        this.b = b;
        initRectangles(rects);
        repaint();
    }

    private void initRectangles(int rects) {
        final double c = (double) (b - a) / (double) rects; //width of each rectangle
        double j = a;       //x position of each rectangle, incremented by c

        rectangles = new Rectangle[rects];
        for (int i = 0; i < rectangles.length; i++) {

            if ((function.getOutput(j) >= 0 && j >= 0)
                    || (function.getOutput(j) < 0 && j < 0)) {   //right of y-axis && +height || -height && left of y-axis
                double f_of_x = function.getOutput(j + c);
                rectangles[i] = new Rectangle((int) j, 0, (int) (c), (int) (f_of_x));
                j += c;
            } else if ((function.getOutput(j) >= 0 && j < 0)
                    || (function.getOutput(j) < 0 && j >= 0)) {        //left of y-axis && +height || -height && right of y-axis
                double f_of_x = function.getOutput(j);
                rectangles[i] = new Rectangle((int) j, 0, (int) (c), (int) (f_of_x));
                j += c;
            }
        }
    }

    private void cls(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, DIView.FRAME_WIDTH, DIView.FRAME_HEIGHT);
    }

    private void paintAxes(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(0, ORIGIN_Y, DIView.FRAME_WIDTH, ORIGIN_Y);    //x-axis
        g.drawLine(ORIGIN_X, 0, ORIGIN_X, DIView.FRAME_HEIGHT);     //y-axis
        //g.drawString("(0, 0)", ORIGIN_X-35, ORIGIN_Y+15);         //print origin
    }

    private void paintFunction(Graphics g) {
        //function paints many small lines to approximate the function given by 'function'
        g.setColor(Color.BLACK);
        for (int i = (ORIGIN_X + a); i < (ORIGIN_X + b); i += x_interval) //i represents starting x position of line
        {
            double f_of_xo = function.getOutput(i - ORIGIN_X);     //get y value at starting x
            double f_of_xn = function.getOutput(i + x_interval - ORIGIN_X);    //get y value at final x

            //System.out.println("f(" + (i - ORIGIN_X) + ") = " + f_of_xo);

            g.drawLine((int) i, (int) (ORIGIN_Y - f_of_xo), (int) (i + x_interval), (int) (ORIGIN_Y - f_of_xn));
        }
    }

    private void paintRectangles(Graphics g) {
        //functions paints
        for (int i = 0; i < rectangles.length; i++) {
            paintSingleRect(g, rectangles[i]);
        }
    }

    private void paintSingleRect(Graphics g, Rectangle rect) {
        /*
         System.out.println("(" + (rect.getX()) + ", " + (rect.getY()) + "): "
         + "w=" + rect.getWidth() + " h=" + rect.getHeight());
         */
        int y;
        int height;
        if (rect.getHeight() > 0) {
            g.setColor(Color.GREEN);
            y = ORIGIN_Y - rect.getHeight();
            height = rect.getHeight();
        } else {
            g.setColor(Color.RED);
            y = ORIGIN_Y;
            height = -rect.getHeight();
        }
        g.fillRect(rect.getX() + ORIGIN_X, y, rect.getWidth(), height);
        g.setColor(Color.BLACK);
        g.drawRect(rect.getX() + ORIGIN_X, y, rect.getWidth(), height);
    }

    private void estimateArea() {
        double area = 0;
        for (int i = 0; i < rectangles.length; i++) {
            area += rectangles[i].getWidth() * rectangles[i].getHeight();
        }
        System.out.printf("Approximate area of region bounded by x=%d, y=0, x=%d, and f(x) is %f\n", a, b, area);
    }
}
