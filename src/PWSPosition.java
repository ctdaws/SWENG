public class PWSPosition {
    private double x;
    private double y;
    private double x2;
    private double y2;

    public PWSPosition(double x, double y, double x2, double y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double getX() { return this.x; }

    public double getY() { return this.y; }

    public double getX2() { return this.x2; }

    public double getY2() { return this.y2; }

    public double getWidth() { return this.x2 - this.x; }

    public double getHeight() { return this.y2 - this.y; }

    public double getCenterX() { return (this.x + this.x2) / 2; }

    public double getCenterY() { return (this.y + this.y2) / 2; }

    @Override
    public String toString() {
        return "PWSPosition: x = " + this.x + ", y = " + this.y + ", x2 = " + this.x2 + ", y2 = " + this.y2;
    }
}