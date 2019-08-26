package infovis.diagram.elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class GroupingRectangle implements Element {
	Color color = Color.RED;
	Rectangle2D rect2D = null; 

	public GroupingRectangle(double x,double y){
		rect2D = new Rectangle2D.Double(x,y,x,y);
	}
	public boolean contains(double x, double y) {
		return rect2D.contains(x, y);
	}
	public boolean contains(Rectangle2D r) {
		return rect2D.contains(r);
	}
	
	public Color getColor() {
		return color;
	}
	public int getID() {
		return 0;
	}
	public double getX() {
		return rect2D.getX();
	}
	public double getY() {
		return rect2D.getY();
	}

	public void paint(Graphics2D g2D) {
		g2D.setColor(color);
		g2D.draw(rect2D);
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setX(double x) {
		rect2D.setFrame(x,rect2D.getY(),rect2D.getWidth(),rect2D.getHeight());	
	}
	public void setY(double y) {
		rect2D.setFrame(rect2D.getX(),y,rect2D.getWidth(),rect2D.getHeight());
	}

	public void updatePosition(double x, double y) {
		rect2D.setFrame(x,y,rect2D.getWidth(),rect2D.getHeight());
	}
	public void updateSecondPositions(double x2, double y2){
		rect2D.setFrame(rect2D.getX(),rect2D.getY(),x2-rect2D.getX(),y2-rect2D.getY());
	}
	public double getCenterX(){
		return rect2D.getCenterX();
	}
	public double getCenterY(){
		return rect2D.getCenterY();
	}

}
