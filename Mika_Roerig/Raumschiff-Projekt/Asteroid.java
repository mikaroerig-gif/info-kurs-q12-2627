// Schritt 9: Die Klasse Asteroid beschreibt einen fliegenden Asteroiden.

public class Asteroid {

    private String name;
    private double x;
    private double y;
    private double radius;
    private double geschwindigkeitX;
    private double geschwindigkeitY;

    public Asteroid(String name, double x, double y, double radius,
                    double geschwindigkeitX, double geschwindigkeitY) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.geschwindigkeitX = geschwindigkeitX;
        this.geschwindigkeitY = geschwindigkeitY;
    }

    // bewegt den Asteroiden um seine Geschwindigkeit weiter
    public void bewege() {
        x = x + geschwindigkeitX;
        y = y + geschwindigkeitY;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public double getGeschwindigkeitX() {
        return geschwindigkeitX;
    }

    public double getGeschwindigkeitY() {
        return geschwindigkeitY;
    }
}
