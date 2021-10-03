import java.math.*;

/**
 *
 * @author Andrea Masciocchi
 * @version 23.09.21
 */
public class Vector {
    private double x;
    private double y;
    private double newton;
    private double angle;
    private String name;

    public Vector(double first, double second, String name, boolean isPolar) {
        this.name = name;
        if(isPolar){
            this.newton = first;
            this.angle = second;
            this.x = newton * Math.cos(angle);
            this.y = newton * Math.sin(angle);
        }else{
            this.x = first;
            this.y = second;
            this.newton = Math.sqrt(Math.pow(first, 2) + Math.pow(second, 2));
            this.angle = Math.tanh(y/x);
        }
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
    
    public String toString(){
        String txt = "";
        txt += this.getName() + "\n";
        txt += this.getX() + "\n";
        txt += this.getY() + "\n";
        txt += this.getAngle() + "\n";
        txt += this.getNewton() + "\n";
        return txt;
    }
}