/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 14.08.2026
 * @author 
 */

public class Raumschiff {
  
  // start attributes
  private boolean x;
  private String name;
  private int Geschwindigkeit;
  private double Treibstoff;
  // end attributes
  
  public Raumschiff() {
    this.x = false;
    this.name = "";
    this.Geschwindigkeit = 0;
    this.Treibstoff = 0;
  }

  // start methods
  public boolean getX() {
    return x;
  }

  public String getName() {
    return name;
  }

  public int getGeschwindigkeit() {
    return Geschwindigkeit;
  }

  public double getTreibstoff() {
    return Treibstoff;
  }

  public void fliege() {
    // TODO add your code here
    
  }

  public void tanke() {
    // TODO add your code here
    
  }

  // end methods
} // end of Raumschiff
