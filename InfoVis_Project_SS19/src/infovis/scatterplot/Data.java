package infovis.scatterplot;

import java.awt.*;

public class Data{
	private double [] values;
	private Color color = Color.BLACK;
	private String label = "";
	
	public Data(double[] values, String label) {
		super();
		this.values = values;
		this.label = label;
	}

	public Data(double[] values, Color color, String label) {
		super();
		this.values = values;
		this.color = color;
		this.label = label;
	}

	public Data (double[] values) {
		this.values = values;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double[] getValues() {
		return values;
	}

	public void setValues(double[] values) {
		this.values = values;
	}
	public int getDimension(){
		 return values.length;
	}
	public double getValue(int index){
		return values[index];
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public String toString(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(label);
		stringBuffer.append('[');
		for (double value : values) {
			stringBuffer.append(value);
			stringBuffer.append(',');
		}
		
		stringBuffer.append(']');
	return stringBuffer.toString();
	}

}
