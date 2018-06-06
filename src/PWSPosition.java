/**
 * Class containing information about the position attributes described in xml
 *
 * @author Samuel Broughton
 * @version 2.2
 */
public class PWSPosition {
//    x/y top-left coordinate
    private double x;
    private double y;
//    x2/y2 bottom-right coordinate
    private double x2;
    private double y2;

    /**
     * Construction for PWS position
     * @param x left edge
     * @param y top edge
     * @param x2 right edge
     * @param y2 bottom edge
     */
    public PWSPosition(double x, double y, double x2, double y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Getter for private x
     * @return left edge of object
     */
    public double getX() { return this.x; }

    /**
     * Getter for private y
     * @return top edge of object
     */
    public double getY() { return this.y; }

    /**
     * Getter for private x2
     * @return right edge of object
     */
    public double getX2() { return this.x2; }

    /**
     * Getter for private y2
     * @return bottom edge of object
     */
    public double getY2() { return this.y2; }

    /**
     * Getter for object width, saves calculating elsewhere
     * @return width of object (x2 - x)
     */
    public double getWidth() { return this.x2 - this.x; }

    /**
     * Getter for object height, ssaves calculating elsewhere
     * @return height of object (y2 - y)
     */
    public double getHeight() { return this.y2 - this.y; }

    /**
     * Getter for center x coordinate
     * @return center x coordinate of object
     */
    public double getCenterX() { return (this.x + this.x2) / 2; }

    /**
     * Getter for center y coordinate
     * @return center y coordinate of object
     */
    public double getCenterY() { return (this.y + this.y2) / 2; }

    /**
     * Override of toString method
     * @return String listing position information of the object
     */
    @Override
    public String toString() {
        return "PWSPosition: x = " + this.x + ", y = " + this.y + ", x2 = " + this.x2 + ", y2 = " + this.y2;
    }
}