// Schritt 1: Die Klasse Planet beschreibt einen Planeten im Weltraum.
// Schritt 11: Planeten werden in einer ArrayList verwaltet.

public class Planet {

    private String name;
    private double x;
    private double y;
    private double radius;

    public Planet(String name, double x, double y, double radius) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.radius = radius;
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
}
