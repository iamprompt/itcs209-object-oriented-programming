import javax.swing.*;
import java.awt.*;

public class JFrameGraphics extends JPanel {

    public void paint(Graphics g) {
        // Home -> Roof
        Polygon roof = new Polygon(new int[]{30, 80, 130}, new int[]{100, 20, 100}, 3);
        g.setColor(Color.BLUE);
        g.drawPolygon(roof);
        g.setColor(Color.BLUE);
        g.fillPolygon(roof);

        // Home -> Body
        Polygon home = new Polygon(new int[]{40, 120, 120, 40}, new int[]{100, 100, 200, 200}, 4);
        g.setColor(Color.GRAY);
        g.drawPolygon(home);
        g.setColor(Color.GRAY);
        g.fillPolygon(home);

        // Grass
        Polygon grass = new Polygon(new int[]{0, 300, 300, 0}, new int[]{200, 200, 300, 300}, 4);
        g.setColor(Color.GREEN);
        g.drawPolygon(grass);
        g.setColor(Color.GREEN);
        g.fillPolygon(grass);

        // Flag -> Base
        Polygon base = new Polygon(new int[]{160, 160, 170, 170, 240, 240, 250, 250}, new int[]{200, 190, 190, 170, 170, 190, 190, 200}, 8);
        g.setColor(Color.BLACK);
        g.drawPolygon(base);
        g.fillPolygon(base);

        // Flag -> Pole
        Polygon pole = new Polygon(new int[]{200, 200, 210, 210}, new int[]{170, 30, 30, 170}, 4);
        g.setColor(Color.BLACK);
        g.drawPolygon(pole);
        g.fillPolygon(pole);

        int ySTART = 35;
        int unitFlag = 10;

        int x = ySTART + unitFlag;
        int w = ySTART;

        // Flag -> RED1
        Polygon red1 = new Polygon(new int[]{210, 280, 280, 210}, new int[]{w, w, x, x}, 4);
        g.setColor(Color.RED);
        g.drawPolygon(red1);
        g.fillPolygon(red1);

        w = x;
        x += unitFlag;

        // Flag -> WHITE1
        Polygon white1 = new Polygon(new int[]{210, 280, 280, 210}, new int[]{w, w, x, x}, 4);
        g.setColor(Color.WHITE);
        g.drawPolygon(white1);
        g.fillPolygon(white1);

        w = x;
        x += (2 * unitFlag);

        // Flag -> BLUE
        Polygon blue = new Polygon(new int[]{210, 280, 280, 210}, new int[]{w, w, x, x}, 4);
        g.setColor(Color.BLUE);
        g.drawPolygon(blue);
        g.fillPolygon(blue);

        w = x;
        x += unitFlag;

        // Flag -> WHITE2
        Polygon white2 = new Polygon(new int[]{210, 280, 280, 210}, new int[]{w, w, x, x}, 4);
        g.setColor(Color.WHITE);
        g.drawPolygon(white2);
        g.fillPolygon(white2);

        w = x;
        x += unitFlag;

        // Flag -> RED2
        Polygon red2 = new Polygon(new int[]{210, 280, 280, 210}, new int[]{w, w, x, x}, 4);
        g.setColor(Color.RED);
        g.drawPolygon(red2);
        g.fillPolygon(red2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("6288087");
        frame.getContentPane().add(new JFrameGraphics());
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}
