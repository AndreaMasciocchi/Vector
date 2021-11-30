import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 *
 * @author Andrea Masciocchi
 * @version 23.09.21
 */
public class Body {
    private double x;
    private double y;
    ArrayList<Vector> vectors = new ArrayList<>();
    
    public Body(double canvasLengthX, double canvasLengthY){
        this.x = canvasLengthX/2;
        this.y = canvasLengthY/2;
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        Graphics2D g2d = (Graphics2D)g;
        g2d.fill(new Ellipse2D.Double(x-5, y-5, 10, 10));
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public void setX(double x) {
        this.x = x;
    }
}
