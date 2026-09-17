// Main.java - testet die Klassen aus den Schritten 2 bis 7 auf der Konsole.

public class Main {

    public static void main(String[] args) {

        // Schritt 2 + 3: zwei Raumschiffe mit dem Konstruktor erzeugen
        Raumschiff orion = new Raumschiff("Orion", 200, 300, 100);
        Raumschiff nova = new Raumschiff("Nova", 50, 80, 60);

        System.out.println("--- Schritt 2: Werte ausgeben ---");
        zeigeSchiff(orion);
        zeigeSchiff(nova);

        // Schritt 2: nur den Treibstoff von orion aendern (ueber tanke)
        orion.tanke(-30); // negativ -> wird ignoriert
        System.out.println("orion nach tanke(-30): " + orion.getTreibstoff());
        System.out.println("nova ist unveraendert: " + nova.getTreibstoff());

        // Schritt 4 + 5: Methoden tanke und fliege testen
        System.out.println("\n--- Schritt 4/5: tanke und fliege ---");
        orion.tanke(50); // wird auf 100 begrenzt (Schritt 6)
        System.out.println("orion Treibstoff (max 100): " + orion.getTreibstoff());

        boolean geflogen1 = orion.fliege(10, 5);
        boolean geflogen2 = orion.fliege(-3, 0);
        System.out.println("fliege 1: " + geflogen1 + "  fliege 2: " + geflogen2);
        zeigeSchiff(orion);

        // Schritt 5: Rueckgabewerte testen
        System.out.println("\n--- Schritt 5: Rueckgabewerte ---");
        System.out.println("hatTreibstoff: " + orion.hatTreibstoff());
        double dist = orion.berechneEntfernung(0, 0);
        System.out.println("Entfernung zu (0,0): " + dist);

        // leer fliegen, um den false-Fall zu sehen
        Raumschiff leer = new Raumschiff("Leer", 0, 0, 0);
        System.out.println("leer.fliege: " + leer.fliege(1, 1));
        System.out.println("leer.hatTreibstoff: " + leer.hatTreibstoff());

        // Schritt 1: Planet
        Planet erde = new Planet("Erde", 100, 100, 30);
        System.out.println("\nPlanet: " + erde.getName() + " r=" + erde.getRadius());

        // Schritt 10: Pilot mit Raumschiff verbinden
        Pilot pilot = new Pilot("Alex Stein", "OR-7");
        orion.setPilot(pilot);
        System.out.println("Pilot von orion: " + orion.getPilot().getRufzeichen());

        // Schritt 12: Raumstation testen
        Raumstation helios = new Raumstation("Helios", 210, 305, 100, 200);
        System.out.println("in Reichweite: " + helios.istInReichweite(orion));
        orion.fliege(1, 0); // Treibstoff etwas senken
        helios.betanke(orion);
        System.out.println("orion nach betanke: " + orion.getTreibstoff());

        // Schritt 7: MissionSpace erzeugen und Szene zeichnen
        // (oeffnet ein Fenster - fuer den reinen Klassentest auskommentiert lassen,
        //  zum Spielen bitte MissionSpace.main starten)
        // MissionSpace mission = new MissionSpace();
        // mission.zeichneSzene();

        System.out.println("\nAlle Konsolentests durchgelaufen.");
    }

    // kleine Hilfsmethode zum Ausgeben eines Raumschiffs
    private static void zeigeSchiff(Raumschiff s) {
        System.out.println(s.getName() + ": x=" + s.getX() + " y=" + s.getY()
                + " treibstoff=" + s.getTreibstoff());
    }
}
