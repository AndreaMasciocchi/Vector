import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;



/**
 *
 * @author Andrea Masciocchi
 * @version 23.09.21
 */
public class Body {
    private int x;
    private int y;
    ArrayList<Vector> vectors = new ArrayList<>();
    
    public Body(int canvasLengthX, int canvasLengthY){
        this.x = canvasLengthX/2;
        this.y = canvasLengthY/2;
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x-5, y-5, 10, 10);
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    
    
}
