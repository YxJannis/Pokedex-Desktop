package infovis.diagram.layout;

import infovis.diagram.Model;
import infovis.diagram.View;
import infovis.diagram.elements.Edge;
import infovis.diagram.elements.Vertex;

public interface Layout {

	public void setMouseCoords(int x, int y, View view);
	public Model transform(Model model, View view);
	
}
