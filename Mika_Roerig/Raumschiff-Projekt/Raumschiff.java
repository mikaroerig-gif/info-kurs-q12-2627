// Raumschiff-Projekt - Schritt 1 bis 10
// Die Klasse Raumschiff beschreibt ein einzelnes Raumschiff im Spiel.

public class Raumschiff {

    // Schritt 6: alle Attribute sind private (Kapselung)
    private String name;
    private double x;
    private double y;
    private double treibstoff;

    // Schritt 10: ein Raumschiff kann einen Piloten haben (Assoziation)
    private Pilot pilot;

    // Schritt 3: parametrisierter Konstruktor mit vier Parametern
    public Raumschiff(String name, double x, double y, double treibstoff) {
        this.name = name;
        this.x = x;
        this.y = y;

        // Schritt 6: Starttreibstoff wird auf 0 bis 100 begrenzt
        if (treibstoff < 0) {
            treibstoff = 0;
        }
        if (treibstoff > 100) {
            treibstoff = 100;
        }
        this.treibstoff = treibstoff;
    }

    // Schritt 4: tanken - nur positive Mengen erhoehen den Treibstoff
    // Schritt 6: maximal 100 Treibstoff
    public void tanke(double menge) {
        if (menge <= 0) {
            return; // negative oder null Menge wird ignoriert
        }
        treibstoff = treibstoff + menge;
        if (treibstoff > 100) {
            treibstoff = 100;
        }
    }

    // Schritt 4 + Schritt 5: fliegen gibt jetzt boolean zurueck
    // true  = das Schiff ist geflogen
    // false = kein Treibstoff, nichts passiert
    public boolean fliege(double dx, double dy) {
        if (treibstoff >= 1) {
            x = x + dx;
            y = y + dy;
            treibstoff = treibstoff - 1;
            return true;
        }
        return false;
    }

    // Schritt 5: hat das Schiff noch Treibstoff?
    public boolean hatTreibstoff() {
        return treibstoff >= 1;
    }

    // Schritt 5: Luftlinie zu einem Zielpunkt (Satz des Pythagoras)
    public double berechneEntfernung(double zielX, double zielY) {
        double dx = zielX - x;
        double dy = zielY - y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Schritt 6: Getter fuer alle vier Attribute
    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTreibstoff() {
        return treibstoff;
    }

    // Schritt 10: Pilot setzen und auslesen
    public void setPilot(Pilot p) {
        this.pilot = p;
    }

    public Pilot getPilot() {
        return pilot;
    }
}
