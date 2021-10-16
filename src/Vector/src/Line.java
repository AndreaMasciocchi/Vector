/**
 *
 * @author Andrea Masciocchi
 * @version 16.10.2021
 */
public class Line {
    private int startX;
    private int startY;
    private int destX;
    private int destY;

    public Line(int startX, int startY, int destX, int destY) {
        this.startX = startX;
        this.startY = startY;
        this.destX = destX;
        this.destY = destY;
    }

    public int getDestX() {
        return destX;
    }

    public int getDestY() {
        return destY;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
}
