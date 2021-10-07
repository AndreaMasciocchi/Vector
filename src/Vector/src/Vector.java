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
            int n = 1;
            this.x = first;
            this.y = second;
            if(x<0){n = -1;}
            this.newton = n * Math.sqrt(Math.pow(first, 2) + Math.pow(second, 2));
            this.angle = Math.atan(y/x);
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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    
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