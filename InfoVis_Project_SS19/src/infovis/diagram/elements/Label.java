package infovis.diagram.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Label implements Element {
	private String text = "Neues Label";
	private Color color = Color.BLACK;
	private Color background = Color.WHITE;
	private Font font = new Font("sansserif", Font.BOLD, 12);
	private double x;
	private double y;



	public void paint(Graphics2D g2D) {
		/*
		 * http://www.apl.jhu.edu/~hall/java/Java2D-Tutorial.html
		 */
		//Debug.print("Paint label");
	    g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);
	    Font font = new Font("Serif", Font.PLAIN, 12);
	    g2D.setColor(color);
	    g2D.setFont(font);
	    g2D.drawString(text, 40, 120); 
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
		
	}

	public void setY(double y) {
		this.y = y;
		
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean contains(double x, double y) {
		return false;
	}

	public void updatePosition(double x, double y) {
		setX(x);
		setY(y);
		
	}

	public int getID() {
		return 0;
	}

}
