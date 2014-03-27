/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
public class DITester {

    public static void main(String args[]) {
        //Function function = new SquaredFunction();
        DIView gui = new DIView();
        gui.launchFrame();
        for (double i = 0; i < 1e5; i += 5) {
            gui.graph(new Function() {
                double j;

                public double getOutput(double input) {
                    return ((input * input * input + 250 * input * input) / 10000) + 100 - 200 + j;
                }

                private Function init(double arg) {
                    j = arg;
                    return this;
                }
            }.init(i), -DIView.FRAME_WIDTH / 2, DIView.FRAME_WIDTH / 2, 100);
            try {
                Thread.sleep(150);
            } catch (Exception e) {
            }
        }
    }
}
