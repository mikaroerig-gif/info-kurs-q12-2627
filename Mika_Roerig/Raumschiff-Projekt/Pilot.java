// Schritt 10: Die Klasse Pilot beschreibt den Piloten eines Raumschiffs.

public class Pilot {

    private String name;
    private String rufzeichen;

    public Pilot(String name, String rufzeichen) {
        this.name = name;
        this.rufzeichen = rufzeichen;
    }

    public String getName() {
        return name;
    }

    public String getRufzeichen() {
        return rufzeichen;
    }
}
