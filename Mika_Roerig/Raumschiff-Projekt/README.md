# Raumschiff-Projekt

Ein kleines Weltraum-Spiel in reinem Java (mit Java Swing für das Fenster).
Man steuert ein Raumschiff, fliegt Planeten an, weicht Asteroiden aus und tankt an einer
Raumstation.

## Was macht das Projekt?

- Ein Raumschiff bewegt sich mit den Pfeiltasten durch ein Fenster.
- Jeder Flug kostet Treibstoff.
- Fliegt man einen Planeten an, gibt es Punkte und der Planet verschwindet.
- Berührt man einen Asteroiden, verliert man einen Punkt.
- An der Raumstation "Helios" kann man mit `R` auftanken.
- Sind alle Planeten besucht, erscheint "MISSION GESCHAFFT!".

## Klassen

| Klasse          | Aufgabe |
|-----------------|---------|
| `Raumschiff`    | Name, Position, Treibstoff. Methoden `tanke`, `fliege`, `berechneEntfernung`. Kann einen `Pilot` haben. |
| `Planet`        | Ein Planet mit Name, Position und Radius. |
| `Asteroid`      | Ein Asteroid mit Position, Radius und Geschwindigkeit. `bewege()` fliegt weiter. |
| `Pilot`         | Name und Rufzeichen des Piloten. |
| `Raumstation`   | Kann prüfen, ob ein Schiff in Reichweite ist (`istInReichweite`), und es betanken (`betanke`). |
| `SpaceWindow`   | Das Fenster (Swing). Zeichnet Kreise und Text, fragt Tasten ab, macht Pausen. |
| `MissionSpace`  | Baut das Spiel zusammen: Schiff, Fenster, Asteroiden, Planeten, Station. Enthält die Spielschleife. |
| `Main`          | Konsolentests für die Klassen (ohne Fenster). |

## Wie startet man das Spiel?

Im Projektordner:

```
javac *.java
java MissionSpace
```

Nur die Klassentests auf der Konsole (ohne Fenster):

```
java Main
```

## Steuerung

| Taste          | Wirkung |
|----------------|---------|
| Pfeil links    | nach links fliegen |
| Pfeil rechts   | nach rechts fliegen |
| Pfeil hoch     | nach oben fliegen |
| Pfeil runter   | nach unten fliegen |
| `R`            | an der Raumstation tanken (nur in Reichweite) |

Fenster schließen beendet das Spiel.

## Weitere Dokumentation

- `DOKU.md` – Begründungen zu jedem Entwicklungsschritt.
- `UML.md` – Klassendiagramm mit Attributen, Methoden und Beziehungen.
