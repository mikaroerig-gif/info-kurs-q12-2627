// Schritt 7 bis 13: MissionSpace verbindet alle Teile zu einem Spiel.

import java.util.ArrayList;

public class MissionSpace {

    // Schritt 7
    private Raumschiff schiff;
    private SpaceWindow window;

    // Schritt 9: vier Asteroiden in einem Array
    private Asteroid[] asteroiden = new Asteroid[4];

    // Schritt 11: Planeten in einer ArrayList
    private ArrayList<Planet> planeten = new ArrayList<Planet>();

    // Schritt 12: eine Raumstation
    private Raumstation station;

    // Schritt 13: Punktestand
    private int punkte = 0;

    // Groesse des Fensters
    private int breite = 800;
    private int hoehe = 600;

    public MissionSpace() {
        // Schritt 7: Fenster und Raumschiff mit new erzeugen
        window = new SpaceWindow(breite, hoehe);
        schiff = new Raumschiff("Orion", 400, 300, 100);

        // Schritt 10: Pilot erzeugen und mit dem Raumschiff verbinden
        Pilot pilot = new Pilot("Kim Berger", "OR-1");
        schiff.setPilot(pilot);

        // Schritt 9: vier Asteroiden mit zufaelligen Positionen
        for (int i = 0; i < asteroiden.length; i++) {
            double ax = Math.random() * breite;
            double ay = Math.random() * hoehe;
            double gx = Math.random() * 4 - 2; // -2 bis 2
            double gy = Math.random() * 4 - 2;
            asteroiden[i] = new Asteroid("A" + (i + 1), ax, ay, 15, gx, gy);
        }

        // Schritt 11: mindestens 3 Planeten hinzufuegen
        planeten.add(new Planet("Terra", 120, 120, 30));
        planeten.add(new Planet("Mars", 650, 150, 25));
        planeten.add(new Planet("Kepler", 600, 480, 35));
        planeten.add(new Planet("Luna", 200, 450, 20));

        // Schritt 12: Raumstation "Helios"
        station = new Raumstation("Helios", 400, 80, 120, 300);
    }

    // Schritt 8: die Spielschleife
    public void starte() {
        while (window.istOffen()) {
            steuereRaumschiff();
            bewegeAsteroiden();
            pruefeKollisionen();
            pruefeMission();
            zeichneSzene();
            window.warteMs(30);
        }
    }

    // Schritt 8: Steuerung mit den Pfeiltasten, R zum Tanken
    private void steuereRaumschiff() {
        double schritt = 5;
        if (window.istTasteGedrueckt("LEFT")) {
            schiff.fliege(-schritt, 0);
        }
        if (window.istTasteGedrueckt("RIGHT")) {
            schiff.fliege(schritt, 0);
        }
        if (window.istTasteGedrueckt("UP")) {
            schiff.fliege(0, -schritt);
        }
        if (window.istTasteGedrueckt("DOWN")) {
            schiff.fliege(0, schritt);
        }
        // Schritt 12: R-Taste betankt an der Station
        if (window.istTasteGedrueckt("R")) {
            station.betanke(schiff);
        }
    }

    // Schritt 9: alle Asteroiden bewegen
    private void bewegeAsteroiden() {
        for (Asteroid a : asteroiden) {
            if (a != null) {
                a.bewege();
            }
        }
    }

    // Schritt 13: Kollisionen mit Asteroiden pruefen
    private void pruefeKollisionen() {
        for (Asteroid a : asteroiden) {
            if (a == null) {
                continue;
            }
            double entfernung = schiff.berechneEntfernung(a.getX(), a.getY());
            if (entfernung < a.getRadius() + 10) {
                // bei einer Kollision kostet es einen Punkt (nicht unter 0)
                if (punkte > 0) {
                    punkte = punkte - 1;
                }
            }
        }
    }

    // Schritt 13: Mission pruefen - besuchte Planeten geben Punkte
    private void pruefeMission() {
        for (int i = 0; i < planeten.size(); i++) {
            Planet p = planeten.get(i);
            double entfernung = schiff.berechneEntfernung(p.getX(), p.getY());
            if (entfernung < p.getRadius() + 15) {
                punkte = punkte + 10;
                planeten.remove(i);
                i--; // Index anpassen, weil ein Element entfernt wurde
            }
        }
    }

    // Schritt 11: den naechsten Planeten zum Raumschiff finden
    public Planet findeNaechstenPlaneten() {
        if (planeten.isEmpty()) {
            return null;
        }
        Planet naechster = planeten.get(0);
        double kleinsteEntfernung = schiff.berechneEntfernung(naechster.getX(), naechster.getY());
        for (Planet p : planeten) {
            double entfernung = schiff.berechneEntfernung(p.getX(), p.getY());
            if (entfernung < kleinsteEntfernung) {
                kleinsteEntfernung = entfernung;
                naechster = p;
            }
        }
        return naechster;
    }

    // Schritt 7 + 13: die ganze Szene zeichnen
    public void zeichneSzene() {
        // Planeten
        for (Planet p : planeten) {
            window.zeichneKreis(p.getX(), p.getY(), p.getRadius(), "blau");
            window.zeichneText(p.getX() - 20, p.getY() - p.getRadius() - 5, p.getName());
        }

        // Asteroiden
        for (Asteroid a : asteroiden) {
            if (a != null) {
                window.zeichneKreis(a.getX(), a.getY(), a.getRadius(), "grau");
            }
        }

        // Raumstation
        window.zeichneKreis(station.getX(), station.getY(), 18, "gelb");
        window.zeichneText(station.getX() - 20, station.getY() - 25, station.getName());

        // Raumschiff
        window.zeichneKreis(schiff.getX(), schiff.getY(), 12, "rot");
        window.zeichneText(schiff.getX() - 20, schiff.getY() - 20, schiff.getName());

        zeichneHUD();

        window.aktualisiere();
    }

    // Schritt 13: die Anzeige oben im Fenster
    private void zeichneHUD() {
        window.zeichneText(10, 20, "Treibstoff: " + (int) schiff.getTreibstoff());
        window.zeichneText(10, 40, "Punkte: " + punkte);

        if (schiff.getPilot() != null) {
            window.zeichneText(10, 60, "Pilot: " + schiff.getPilot().getRufzeichen());
        }

        if (planeten.isEmpty()) {
            window.zeichneText(breite / 2 - 60, hoehe / 2, "MISSION GESCHAFFT!");
        }
    }

    // Schritt 8: Startpunkt des Spiels
    public static void main(String[] args) {
        MissionSpace mission = new MissionSpace();
        mission.starte();
    }
}
