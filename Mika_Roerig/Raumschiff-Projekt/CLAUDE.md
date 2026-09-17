# Raumschiff-Projekt – Java

Erstelle ein Java-Projekt mit einem Weltraum-Spiel. Alles in reinem Java, kein Editor-GUI — nur Code.
Jeder Schritt baut auf dem vorherigen auf. Nach jedem Schritt: kompilieren, testen, Fehler beheben.
Schreibe den Code so, wie ein Schüler ihn schreiben würde — einfach, klar, keine fancy Patterns.
Dokumentiere jeden Schritt mit kurzen Kommentaren im Code.

---

## Schritt 1 – Klasse Raumschiff anlegen

Erstelle `Raumschiff.java` mit vier Attributen:
- `name` (String)
- `x` (double)
- `y` (double)
- `treibstoff` (double)

Erstelle zusätzlich `Planet.java` mit sinnvollen Attributen (name, x, y, radius).

Schreibe in eine separate Datei `DOKU.md`:
- Welche Infos sind Attribute, welche eher Methoden?
- Begründung kurz und knapp.

---

## Schritt 2 – Objekte erzeugen und testen

Erstelle eine `Main.java` mit main-Methode.
Erzeuge zwei Raumschiff-Objekte `orion` und `nova` mit unterschiedlichen Werten.
Gib alle Werte aus. Ändere nur den Treibstoff von `orion` und prüfe, dass `nova` unverändert bleibt.

Ergänze `DOKU.md`:
- Warum findet man `orion` und `nova` nicht in `Raumschiff.java`?
- Skizziere ein Objektdiagramm für beide Objekte (als Text/ASCII).

---

## Schritt 3 – Konstruktor

Ergänze in `Raumschiff` einen parametrisierten Konstruktor mit vier Parametern (name, x, y, treibstoff).
Passe `Main.java` an: Erzeuge `orion` mit ("Orion", 200, 300, 100) und `nova` mit anderen Werten.

Ergänze `DOKU.md`:
- Welche Startwerte wären fachlich unsinnig? Formuliere Regeln für Treibstoff.
- Warum ist ein Konstruktor besser als jedes Attribut einzeln zu setzen?

---

## Schritt 4 – Methoden tanke und fliege

Erstelle in `Raumschiff`:
- `tanke(double menge)` — nur positive Mengen erhöhen den Treibstoff
- `fliege(double dx, double dy)` — nur wenn mindestens 1 Treibstoff vorhanden, ändern sich x/y und Treibstoff sinkt um 1

Teste beide Methoden in `Main.java` mehrfach hintereinander.

Ergänze `DOKU.md`:
- Warum sind `dx` und `dy` Parameter und nicht Attribute?

---

## Schritt 5 – Rückgabewerte

Ändere `fliege` so, dass sie `boolean` zurückgibt (true wenn geflogen, false wenn kein Treibstoff).
Erstelle:
- `hatTreibstoff()` → boolean
- `berechneEntfernung(double zielX, double zielY)` → double (Luftlinie mit Math.sqrt)

Teste alle Rückgabewerte in Main.

Ergänze `DOKU.md`:
- Warum ist ein Rückgabewert oft besser als System.out.println?

---

## Schritt 6 – Sichtbarkeit und Kapselung

Stelle alle vier Attribute auf `private`.
Erstelle Getter für alle vier Attribute.
Verzichte auf öffentliche Setter für x, y und Treibstoff.
Begrenze `tanke` auf maximal 100 Treibstoff.
Begrenze im Konstruktor den Starttreibstoff auf 0 bis 100.

Passe Main.java an (nur noch Getter verwenden).

Ergänze `DOKU.md`:
- Warum keine Setter für x/y/Treibstoff?
- Drei Invarianten die für ein gültiges Raumschiff immer gelten sollen.
- Für jedes Attribut: wäre ein Setter sinnvoll? Begründung.

---

## Schritt 7 – SpaceWindow und MissionSpace

Erstelle `SpaceWindow.java` — eine einfache Fensterklasse mit Java Swing:
- Konstruktor nimmt Breite und Höhe
- Methode `zeichneKreis(double x, double y, double radius, String farbe)`
- Methode `zeichneText(double x, double y, String text)`
- Methode `aktualisiere()` — repaint
- Methode `warteMs(int ms)` — Thread.sleep
- Methode `istTasteGedrueckt(String taste)` → boolean (Pfeiltasten, R, Shift etc.)

Erstelle `MissionSpace.java` mit:
- Attributen `schiff` (Raumschiff) und `window` (SpaceWindow)
- Konstruktor erzeugt beides mit `new`
- `zeichneSzene()` zeichnet das Raumschiff als Kreis mit Name

Teste in Main: Erzeuge MissionSpace und rufe zeichneSzene auf.

Ergänze `DOKU.md`:
- Warum ist die Objekterzeugung in MissionSpace sinnvoller als in Raumschiff?

---

## Schritt 8 – Spielschleife und Steuerung

Erstelle in MissionSpace:
- `starte()` — while-Schleife die läuft solange Fenster offen. Pro Durchlauf: steuereRaumschiff, zeichneSzene, warteMs(30)
- `steuereRaumschiff()` — vier Pfeiltasten bewegen das Raumschiff (rufe fliege auf)
- `main`-Methode in MissionSpace die alles startet

Ergänze `DOKU.md`:
- Warum wäre eine Endlosschleife ohne Pause problematisch?

---

## Schritt 9 – Asteroiden

Erstelle `Asteroid.java` mit privaten Attributen (name, x, y, radius, geschwindigkeitX, geschwindigkeitY), Konstruktor und Gettern.
Methode `bewege()` ändert x/y um die Geschwindigkeit.

In MissionSpace:
- Attribut `asteroiden` als Asteroid-Array mit 4 Plätzen
- Im Konstruktor 4 Asteroiden mit zufälligen Positionen erzeugen
- In zeichneSzene alle Asteroiden mit for-each zeichnen
- In der Spielschleife alle Asteroiden bewegen

Ergänze `DOKU.md`:
- Unterschied zwischen `new Asteroid[4]` und `new Asteroid(...)`
- Was passiert wenn ein Arrayplatz null bleibt?

---

## Schritt 10 – Pilot

Erstelle `Pilot.java` mit `name` und `rufzeichen`, Konstruktor und Gettern.

In Raumschiff:
- Privates Attribut `pilot` (Pilot)
- `setPilot(Pilot p)` und `getPilot()`

In MissionSpace: Pilot erzeugen, mit Raumschiff verbinden, Rufzeichen im Fenster anzeigen.

Erstelle ein UML-Diagramm als `UML.md`:
- Alle Klassen mit Attributen und Methoden
- Beziehungen zwischen den Klassen mit Multiplizitäten
- Als ASCII/Text-Diagramm

Ergänze `DOKU.md`:
- Beziehung Raumschiff-Pilot: Assoziation oder Aggregation?

---

## Schritt 11 – ArrayList mit Planeten

In MissionSpace:
- Attribut `planeten` als `ArrayList<Planet>` (mit Import)
- Im Konstruktor mindestens 3 Planeten hinzufügen
- In zeichneSzene alle Planeten mit for-each zeichnen
- `findeNaechstenPlaneten()` gibt den nächsten Planet zum Raumschiff zurück

Ergänze `DOKU.md`:
- Vergleich Array vs ArrayList: Laufzeit und Lesbarkeit

---

## Schritt 12 – Raumstation

Erstelle `Raumstation.java`:
- Attribute: name, x, y, reichweite, treibstoffVorrat
- Konstruktor und Getter
- `istInReichweite(Raumschiff s)` → boolean (Entfernung < Reichweite)
- `betanke(Raumschiff s)` — tankt das Raumschiff auf wenn in Reichweite

In MissionSpace:
- Attribut `station` (Raumstation), erzeugen als "Helios"
- Zeichnen in zeichneSzene
- R-Taste ruft betanke auf

Ergänze `DOKU.md`:
- Drei Testfälle für istInReichweite (Normal, Grenz, Fehler)

---

## Schritt 13 – Missionsstatus und Refactoring

Ergänze in MissionSpace:
- `punkte` (int) — steigt wenn Raumschiff einen Planeten besucht (nah genug)
- Besuchter Planet wird aus der Liste entfernt
- Punkte im Fenster anzeigen
- Wenn alle Planeten besucht: Gewinn-Nachricht

Refactoring:
- Zerlege lange Methoden in kleinere private Methoden
- z.B. `zeichneHUD()`, `pruefeKollisionen()`, `pruefeMission()`

Ergänze `DOKU.md`:
- Begründung der neuen Methodenaufteilung

---

## Schritt 14 – Dokumentation

Schreibe eine `README.md` im Projektordner:
- Was macht das Projekt
- Welche Klassen gibt es und was machen sie
- Wie startet man das Spiel
- Steuerung (Pfeiltasten, R zum Tanken)
- Kurz und sachlich geschrieben, für eine andere Lerngruppe verständlich

Prüfe dass `DOKU.md` vollständig ist mit allen Begründungen aus allen Schritten.
Prüfe dass `UML.md` alle finalen Klassen und Beziehungen zeigt.

Kompiliere alles und teste das komplette Spiel einmal durch.
