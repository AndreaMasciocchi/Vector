
import java.awt.Color;
import java.util.Objects;

/**
 *
 * @author Andrea Masciocchi
 * @version 23.09.21
 */
public class Vector {
    private double x;
    private double y;
    private int varX;
    private int varY;
    private double newton;
    private double angle;
    private String name;
    private Color color;

    public Vector(double first, double second, String name, Color color, int varX, int varY) {
        this.name = name;
        this.color = color;
        this.varX = varX;
        this.varY = varY;
        int n = 1;
        this.x = first;
        this.y = second;
       
        if(x<0){n = -1;}
        this.newton = n * Math.sqrt(Math.pow(first, 2) + Math.pow(second, 2));
        this.angle = Math.atan(y/x);
//        this.newton = first;
//        this.angle = second;
//        this.x = newton * Math.cos(angle);
//        this.y = newton * Math.sin(angle);
    }
    
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getNewton(){
        return this.newton;
    }
    public double getAngle(){
        return this.angle;
    }
    public String getName(){
        return this.name;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getVarX() {
        return varX;
    }

    public int getVarY() {
        return varY;
    }
    
    
    
    
    @Override
    public String toString(){
        String txt = "";
        txt += "Name: " + this.getName() + "\n";
        txt += "x: " + this.getX() + "\n";
        txt += "y: " + this.getY() + "\n";
        txt += "Angle: " + this.getAngle() + "\n";
        txt += "Newton: " + this.getNewton() + "\n";
        return txt;
    }
}