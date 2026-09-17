// Schritt 7: Ein einfaches Fenster mit Java Swing.
// Das Fenster kann Kreise und Text zeichnen und Tastendruecke abfragen.

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SpaceWindow {

    private JFrame frame;
    private ZeichenPanel panel;
    private boolean offen = true;

    // Liste der Zeichenbefehle fuer das aktuelle Bild
    private ArrayList<String[]> befehle = new ArrayList<String[]>();

    // welche Tasten sind gerade gedrueckt (nach KeyCode)
    private boolean[] tasten = new boolean[256];

    public SpaceWindow(int breite, int hoehe) {
        frame = new JFrame("Weltraum-Mission");
        frame.setSize(breite, hoehe);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        panel = new ZeichenPanel();
        panel.setBackground(Color.BLACK);
        frame.add(panel);

        // Tastatur abfragen
        frame.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code >= 0 && code < tasten.length) {
                    tasten[code] = true;
                }
            }

            public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();
                if (code >= 0 && code < tasten.length) {
                    tasten[code] = false;
                }
            }

            public void keyTyped(KeyEvent e) {
            }
        });

        frame.setVisible(true);
        frame.requestFocus();
    }

    // einen Kreis vormerken (wird beim naechsten aktualisiere() gezeichnet)
    public void zeichneKreis(double x, double y, double radius, String farbe) {
        befehle.add(new String[]{"kreis", "" + x, "" + y, "" + radius, farbe});
    }

    // einen Text vormerken
    public void zeichneText(double x, double y, String text) {
        befehle.add(new String[]{"text", "" + x, "" + y, text});
    }

    // das Bild neu zeichnen
    public void aktualisiere() {
        panel.setzeBefehle(new ArrayList<String[]>(befehle));
        befehle.clear();
        panel.repaint();
    }

    // kurz warten (fuer die Spielgeschwindigkeit)
    public void warteMs(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // nichts tun
        }
    }

    // ist eine bestimmte Taste gerade gedrueckt?
    public boolean istTasteGedrueckt(String taste) {
        int code = codeFuer(taste);
        if (code < 0 || code >= tasten.length) {
            return false;
        }
        return tasten[code];
    }

    // ist das Fenster noch offen?
    public boolean istOffen() {
        return offen && frame.isDisplayable();
    }

    // wandelt einen Tastennamen in einen KeyCode um
    private int codeFuer(String taste) {
        String t = taste.toUpperCase();
        if (t.equals("LEFT") || t.equals("LINKS")) {
            return KeyEvent.VK_LEFT;
        }
        if (t.equals("RIGHT") || t.equals("RECHTS")) {
            return KeyEvent.VK_RIGHT;
        }
        if (t.equals("UP") || t.equals("OBEN")) {
            return KeyEvent.VK_UP;
        }
        if (t.equals("DOWN") || t.equals("UNTEN")) {
            return KeyEvent.VK_DOWN;
        }
        if (t.equals("R")) {
            return KeyEvent.VK_R;
        }
        if (t.equals("SHIFT")) {
            return KeyEvent.VK_SHIFT;
        }
        if (t.equals("SPACE")) {
            return KeyEvent.VK_SPACE;
        }
        return -1;
    }

    // wandelt einen Farbnamen in eine echte Farbe um
    private static Color farbeFuer(String farbe) {
        String f = farbe.toLowerCase();
        if (f.equals("rot") || f.equals("red")) {
            return Color.RED;
        }
        if (f.equals("blau") || f.equals("blue")) {
            return Color.BLUE;
        }
        if (f.equals("gruen") || f.equals("grün") || f.equals("green")) {
            return Color.GREEN;
        }
        if (f.equals("gelb") || f.equals("yellow")) {
            return Color.YELLOW;
        }
        if (f.equals("weiss") || f.equals("weiß") || f.equals("white")) {
            return Color.WHITE;
        }
        if (f.equals("orange")) {
            return Color.ORANGE;
        }
        if (f.equals("grau") || f.equals("gray")) {
            return Color.GRAY;
        }
        return Color.LIGHT_GRAY;
    }

    // die innere Panel-Klasse zeichnet die vorgemerkten Befehle
    private static class ZeichenPanel extends JPanel {

        private ArrayList<String[]> aktuelleBefehle = new ArrayList<String[]>();

        public void setzeBefehle(ArrayList<String[]> b) {
            this.aktuelleBefehle = b;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (String[] befehl : aktuelleBefehle) {
                if (befehl[0].equals("kreis")) {
                    double x = Double.parseDouble(befehl[1]);
                    double y = Double.parseDouble(befehl[2]);
                    double radius = Double.parseDouble(befehl[3]);
                    g.setColor(farbeFuer(befehl[4]));
                    int d = (int) (radius * 2);
                    g.fillOval((int) (x - radius), (int) (y - radius), d, d);
                } else if (befehl[0].equals("text")) {
                    double x = Double.parseDouble(befehl[1]);
                    double y = Double.parseDouble(befehl[2]);
                    g.setColor(Color.WHITE);
                    g.drawString(befehl[3], (int) x, (int) y);
                }
            }
        }
    }
}
