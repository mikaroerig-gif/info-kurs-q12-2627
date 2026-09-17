# DOKU.md – Raumschiff-Projekt

Kurze Begründungen zu jedem Schritt.

---

## Schritt 1 – Attribute oder Methoden?

**Attribute** speichern einen Zustand, der sich merken lässt:
- `name`, `x`, `y`, `treibstoff` beim Raumschiff
- `name`, `x`, `y`, `radius` beim Planet

**Methoden** beschreiben ein Verhalten oder eine Berechnung, die man bei Bedarf ausführt:
- "tanken", "fliegen", "Entfernung zu einem Ziel berechnen"

Begründung: Eine Entfernung ändert sich ständig, je nachdem wohin man rechnet. So etwas
speichert man nicht als Attribut, sondern berechnet es in einer Methode. Der Treibstoff
dagegen ist ein fester Wert, den das Objekt dauerhaft "bei sich trägt".

---

## Schritt 2 – Objekte

**Warum stehen `orion` und `nova` nicht in `Raumschiff.java`?**
`Raumschiff.java` ist der Bauplan (die Klasse). `orion` und `nova` sind konkrete Objekte,
die zur Laufzeit mit `new` entstehen. Der Bauplan weiß nicht, wie viele Schiffe es später
gibt oder wie sie heißen. Die Objekte "leben" dort, wo sie erzeugt werden (in `Main`).

**Objektdiagramm:**

```
        orion : Raumschiff              nova : Raumschiff
   +-----------------------+       +-----------------------+
   | name       = "Orion"  |       | name       = "Nova"   |
   | x          = 200.0    |       | x          = 50.0     |
   | y          = 300.0    |       | y          = 80.0     |
   | treibstoff = 100.0    |       | treibstoff = 60.0     |
   +-----------------------+       +-----------------------+
```

Ändert man `orion.treibstoff`, bleibt `nova` unberührt – zwei getrennte Objekte mit
eigenem Speicher.

---

## Schritt 3 – Konstruktor

**Fachlich unsinnige Startwerte:**
- negativer Treibstoff (`-10`) – gibt es nicht
- Treibstoff über der Tankgröße (`5000`) – Tank ist begrenzt
- kein Name (`null` oder `""`) – Schiff wäre nicht identifizierbar

**Regeln für Treibstoff:**
1. Treibstoff ist immer `>= 0`.
2. Treibstoff ist immer `<= 100` (Tankgröße).
3. Beim Start wird ein zu großer oder zu kleiner Wert auf diesen Bereich zurechtgestutzt.

**Warum Konstruktor statt jedes Attribut einzeln setzen?**
- Das Objekt ist sofort vollständig und gültig – kein "halb fertiger" Zustand.
- Man kann das Setzen nicht vergessen.
- Die Prüfregeln stehen an einer Stelle und gelten für jedes neue Objekt.

---

## Schritt 4 – Methoden `tanke` und `fliege`

**Warum sind `dx` und `dy` Parameter und keine Attribute?**
`dx`/`dy` sind nur die Bewegung *für diesen einen Flug*. Danach werden sie nicht mehr
gebraucht. Ein Attribut würde einen Wert dauerhaft speichern, obwohl er sich bei jedem
Aufruf ändert. Parameter sind genau dafür da: Werte, die man einer Methode kurz mitgibt.

---

## Schritt 5 – Rückgabewerte

**Warum ist ein Rückgabewert oft besser als `System.out.println`?**
- `println` schreibt nur Text auf die Konsole; das Programm kann damit nicht weiterarbeiten.
- Ein Rückgabewert (`boolean`, `double`) kann in einer `if`-Abfrage oder Rechnung
  weiterverwendet werden.
- Die Methode bleibt wiederverwendbar – auch im Fenster-Spiel, wo es keine Konsole gibt.
- Testen wird einfacher: man vergleicht den Rückgabewert mit dem Erwarteten.

---

## Schritt 6 – Sichtbarkeit und Kapselung

**Warum keine Setter für x, y und Treibstoff?**
Diese Werte dürfen sich nur über die Spielregeln ändern: `x`/`y` nur durch `fliege`,
`treibstoff` nur durch `tanke` und `fliege`. Ein öffentlicher Setter würde diese Regeln
umgehen (z. B. `setTreibstoff(99999)`).

**Drei Invarianten für ein gültiges Raumschiff:**
1. `treibstoff >= 0`
2. `treibstoff <= 100`
3. `name != null` (das Schiff hat immer einen Namen)

**Setter pro Attribut sinnvoll?**
- `name`: eher nein – ein Schiff wird normalerweise nicht umbenannt.
- `x`: nein – Position ändert sich nur durch `fliege`.
- `y`: nein – gleiche Begründung wie `x`.
- `treibstoff`: nein – nur über `tanke`/`fliege`, sonst sind die Invarianten nicht sicher.

---

## Schritt 7 – SpaceWindow und MissionSpace

**Warum ist die Objekterzeugung in `MissionSpace` sinnvoller als in `Raumschiff`?**
`Raumschiff` soll nur ein Raumschiff sein. Es hat nichts mit Fenstern, Grafik oder dem
Spielaufbau zu tun. `MissionSpace` ist die Klasse, die das Spiel zusammenbaut – dort ist
der richtige Ort, um `Raumschiff` und `SpaceWindow` mit `new` zu erzeugen und zu verbinden.
So bleibt `Raumschiff` einfach und unabhängig von Swing.

---

## Schritt 8 – Spielschleife

**Warum wäre eine Endlosschleife ohne Pause problematisch?**
Ohne `warteMs` läuft die Schleife tausende Male pro Sekunde. Das lastet einen
Prozessorkern voll aus, das Spiel wird viel zu schnell und das Fenster reagiert kaum noch
auf Eingaben. Die kurze Pause (ca. 30 ms) gibt eine gleichmäßige Bildrate (~33 Bilder/s)
und lässt dem Rechner Zeit für andere Aufgaben.

---

## Schritt 9 – Asteroiden

**Unterschied `new Asteroid[4]` und `new Asteroid(...)`:**
- `new Asteroid[4]` erzeugt nur das **Array** – einen Behälter mit 4 Plätzen. Alle Plätze
  sind zunächst `null`.
- `new Asteroid(...)` erzeugt ein einzelnes **Asteroid-Objekt**, das dann in einen Platz
  gelegt wird.

**Was passiert, wenn ein Arrayplatz `null` bleibt?**
Beim Zugriff (`asteroiden[i].getX()`) gibt es eine `NullPointerException`. Deshalb wird im
Code mit `if (a != null)` geprüft, bevor ein Asteroid benutzt wird.

---

## Schritt 10 – Pilot

**Beziehung Raumschiff–Pilot: Assoziation oder Aggregation?**
Es ist eine **Assoziation** (lockere Verbindung). Der Pilot wird außerhalb erzeugt und dem
Schiff nur zugewiesen (`setPilot`). Der Pilot kann auch ohne Schiff existieren und das
Schiff ohne Pilot. Es ist keine feste "besteht-aus"-Beziehung (Aggregation/Komposition),
weil der Pilot nicht Teil des Schiffs ist.

---

## Schritt 11 – ArrayList mit Planeten

**Array vs. ArrayList – Laufzeit und Lesbarkeit:**
- **Array:** feste Größe. Elemente entfernen/hinzufügen ist umständlich (manuell umkopieren).
  Zugriff über Index ist sehr schnell.
- **ArrayList:** wächst und schrumpft automatisch. `add`, `remove`, `size`, `for-each` sind
  gut lesbar. `remove` in der Mitte muss intern alles nachrücken (O(n)), fällt bei wenigen
  Planeten aber nicht auf.
- Für die Planeten passt die ArrayList besser, weil besuchte Planeten entfernt werden.
  Für die immer gleich vielen Asteroiden reicht ein Array.

---

## Schritt 12 – Raumstation

**Drei Testfälle für `istInReichweite`:**
1. **Normalfall:** Schiff bei (400,300), Station bei (400,300), Reichweite 120
   → Entfernung 0 < 120 → `true`.
2. **Grenzfall:** Entfernung genau = Reichweite (z. B. 120 und 120)
   → `120 < 120` ist `false`. Der Rand zählt also nicht als "in Reichweite".
3. **Fehlerfall / weit weg:** Schiff bei (0,0), Station bei (400,300), Reichweite 120
   → Entfernung 500 > 120 → `false`.

---

## Schritt 13 – Missionsstatus und Refactoring

**Begründung der Methodenaufteilung:**
`starte()` und `zeichneSzene()` wurden lang und machten mehrere Dinge gleichzeitig.
Aufgeteilt in kleine private Methoden mit einem klaren Zweck:
- `steuereRaumschiff()` – nur Tasteneingabe
- `bewegeAsteroiden()` – nur Asteroiden bewegen
- `pruefeKollisionen()` – nur Zusammenstöße mit Asteroiden
- `pruefeMission()` – nur Planeten-Besuche und Punkte
- `zeichneHUD()` – nur die Textanzeige

Vorteile: Jede Methode ist kurz und über ihren Namen sofort verständlich. Fehler lassen
sich leichter eingrenzen. `starte()` liest sich wie eine Zusammenfassung des Spielablaufs.
