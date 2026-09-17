// Schritt 12: Die Klasse Raumstation kann Raumschiffe betanken.

public class Raumstation {

    private String name;
    private double x;
    private double y;
    private double reichweite;
    private double treibstoffVorrat;

    public Raumstation(String name, double x, double y,
                       double reichweite, double treibstoffVorrat) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.reichweite = reichweite;
        this.treibstoffVorrat = treibstoffVorrat;
    }

    // ist das Raumschiff nah genug an der Station?
    public boolean istInReichweite(Raumschiff s) {
        double entfernung = s.berechneEntfernung(x, y);
        return entfernung < reichweite;
    }

    // tankt das Raumschiff auf, wenn es in Reichweite ist
    public void betanke(Raumschiff s) {
        if (!istInReichweite(s)) {
            return;
        }
        double menge = 100 - s.getTreibstoff(); // so viel passt noch rein
        if (menge > treibstoffVorrat) {
            menge = treibstoffVorrat; // mehr als vorhanden geht nicht
        }
        if (menge > 0) {
            s.tanke(menge);
            treibstoffVorrat = treibstoffVorrat - menge;
        }
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

    public double getReichweite() {
        return reichweite;
    }

    public double getTreibstoffVorrat() {
        return treibstoffVorrat;
    }
}
