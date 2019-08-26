package infovis.diagram.elements;

import java.awt.Color;
import java.awt.Graphics2D;

public interface Element {
 public void paint (Graphics2D g2D);
 public boolean contains (double x,double y);
 public Color getColor ();
 public void setColor(Color color);
 public int getID();
   /*
    * using a class "moveable" instead that implements the Elements interface? 
    */ 
 public double getX();
 public double getY();
 public void setX (double x);
 public void setY (double y);
 public void updatePosition(double x,double y);
}
