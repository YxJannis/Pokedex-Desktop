package infovis.diagram.elements;

import java.awt.Color;
import java.awt.Graphics2D;

public class DrawingEdge implements Element {
	Vertex from = null;
	double mouseX = 0;
	double mouseY = 0;
	Color color = Color.RED;
	
	public DrawingEdge(Vertex from) {
		super();
		this.from = from;
		mouseX = from.getCenterX();
		mouseY = from.getCenterY();
	}

	public boolean contains(double x, double y) {
		return false;
	}
	
	public Color getColor() {
		return color;
	}

	public double getX() {
		return 0;
	}

	public double getY() {
		return 0;
	}

	public void paint(Graphics2D g2D) {
			g2D.setColor(color);
			g2D.drawLine((int) from.getCenterX() , (int)from.getCenterY(),(int)mouseX, (int) mouseY);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setX(double x) {
		mouseX = x;
	}

	public void setY(double y) {
		mouseY = y;
	}

	public void updatePosition(double x, double y) {
		mouseX = x;
		mouseY = y;
	}
	public Vertex getFrom() {
		return from;
	}

	public int getID() {
		return 0;
	}

}
