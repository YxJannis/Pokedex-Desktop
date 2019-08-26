package infovis.diagram.elements;

import java.awt.Color;
import java.awt.Graphics2D;

public class Edge implements Element {
	Vertex from = null;
	Vertex to = null;
	

	public Edge(Vertex from, Vertex to) {
		super();
		this.from = from;
		this.to = to;
	}	
	public boolean contains(double x, double y) {
		return false;
	}

	
	public Color getColor() {
		return null;
	}

	
	public double getX() {
		return 0;
	}

	
	public double getY() {
		return 0;
	}

	public void paint(Graphics2D g2D) {
		g2D.setColor(Color.BLACK);
		g2D.drawLine((int) from.getCenterX() , (int)from.getCenterY(), (int) to.getCenterX(), (int) to.getCenterY());
	}

	
	public void setColor(Color color) {
	}	
	public void setX(double x) {	
	}

	
	public void setY(double y) {
	}


	public void updatePosition(double x, double y) {
	}
	public int getID() {
		return 0;
	}
	public Vertex getSource (){
		return from;
	}
	public Vertex getTarget (){
		return to;
	}
	

}
