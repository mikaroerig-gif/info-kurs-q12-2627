# UML.md – Klassendiagramm (Text)

## Klassen

```
+---------------------------------------------------+
|                   Raumschiff                      |
+---------------------------------------------------+
| - name : String                                   |
| - x : double                                      |
| - y : double                                      |
| - treibstoff : double                             |
| - pilot : Pilot                                   |
+---------------------------------------------------+
| + Raumschiff(name, x, y, treibstoff)              |
| + tanke(menge : double) : void                    |
| + fliege(dx : double, dy : double) : boolean      |
| + hatTreibstoff() : boolean                       |
| + berechneEntfernung(zielX, zielY) : double       |
| + getName() : String                              |
| + getX() : double                                 |
| + getY() : double                                 |
| + getTreibstoff() : double                        |
| + setPilot(p : Pilot) : void                      |
| + getPilot() : Pilot                              |
+---------------------------------------------------+

+-----------------------------+     +-----------------------------+
|            Pilot            |     |           Planet            |
+-----------------------------+     +-----------------------------+
| - name : String             |     | - name : String             |
| - rufzeichen : String       |     | - x : double                |
+-----------------------------+     | - y : double                |
| + Pilot(name, rufzeichen)   |     | - radius : double           |
| + getName() : String        |     +-----------------------------+
| + getRufzeichen() : String  |     | + Planet(name, x, y, radius)|
+-----------------------------+     | + getName() : String        |
                                    | + getX() : double           |
                                    | + getY() : double           |
                                    | + getRadius() : double      |
                                    +-----------------------------+

+-------------------------------------+   +-----------------------------------+
|              Asteroid               |   |            Raumstation            |
+-------------------------------------+   +-----------------------------------+
| - name : String                     |   | - name : String                   |
| - x : double                        |   | - x : double                      |
| - y : double                        |   | - y : double                      |
| - radius : double                   |   | - reichweite : double             |
| - geschwindigkeitX : double         |   | - treibstoffVorrat : double       |
| - geschwindigkeitY : double         |   +-----------------------------------+
+-------------------------------------+   | + Raumstation(name,x,y,reich,vor) |
| + Asteroid(name,x,y,r,gx,gy)        |   | + istInReichweite(s:Raumschiff):bool|
| + bewege() : void                   |   | + betanke(s : Raumschiff) : void   |
| + getName() : String                |   | + getName() : String              |
| + getX() : double                   |   | + getX() : double                 |
| + getY() : double                   |   | + getY() : double                 |
| + getRadius() : double              |   | + getReichweite() : double        |
| + getGeschwindigkeitX() : double    |   | + getTreibstoffVorrat() : double  |
| + getGeschwindigkeitY() : double    |   +-----------------------------------+
+-------------------------------------+

+---------------------------------------------------+
|                  SpaceWindow                      |
+---------------------------------------------------+
| - frame : JFrame                                  |
| - panel : ZeichenPanel                            |
| - befehle : ArrayList<String[]>                   |
| - tasten : boolean[]                              |
+---------------------------------------------------+
| + SpaceWindow(breite : int, hoehe : int)          |
| + zeichneKreis(x, y, radius, farbe : String)      |
| + zeichneText(x, y, text : String)                |
| + aktualisiere() : void                           |
| + warteMs(ms : int) : void                        |
| + istTasteGedrueckt(taste : String) : boolean     |
| + istOffen() : boolean                            |
+---------------------------------------------------+

+---------------------------------------------------+
|                  MissionSpace                     |
+---------------------------------------------------+
| - schiff : Raumschiff                             |
| - window : SpaceWindow                            |
| - asteroiden : Asteroid[4]                        |
| - planeten : ArrayList<Planet>                    |
| - station : Raumstation                           |
| - punkte : int                                    |
+---------------------------------------------------+
| + MissionSpace()                                  |
| + starte() : void                                 |
| + zeichneSzene() : void                           |
| + findeNaechstenPlaneten() : Planet               |
| - steuereRaumschiff() : void                      |
| - bewegeAsteroiden() : void                       |
| - pruefeKollisionen() : void                      |
| - pruefeMission() : void                          |
| - zeichneHUD() : void                             |
| + main(args : String[]) : void                    |
+---------------------------------------------------+
```

## Beziehungen mit Multiplizitäten

```
MissionSpace  1 ------------- 1   Raumschiff        (Komposition: erzeugt mit new)
MissionSpace  1 ------------- 1   SpaceWindow       (Komposition: erzeugt mit new)
MissionSpace  1 ------------- 4   Asteroid          (Array mit 4 Plätzen)
MissionSpace  1 ------------- 0..* Planet           (ArrayList, wird kleiner)
MissionSpace  1 ------------- 1   Raumstation       (Komposition: erzeugt mit new)

Raumschiff    1 ------------- 0..1 Pilot            (Assoziation: setPilot / getPilot)

Raumstation   ..... nutzt .....>  Raumschiff        (Parameter in istInReichweite/betanke)
```

Legende:
- `1 --- 1` genau eins
- `1 --- 4` genau vier
- `0..1`   keins oder eins
- `0..*`   beliebig viele (auch null)
