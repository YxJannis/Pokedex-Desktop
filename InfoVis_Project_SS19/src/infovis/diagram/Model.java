package infovis.diagram;

import infovis.diagram.elements.Edge;
import infovis.diagram.elements.Element;
import infovis.diagram.elements.Label;
import infovis.diagram.elements.Vertex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model {
	private static int idCounter = 0;
	private List<Element> elements = new ArrayList<Element>();
	private List<Vertex>  vertices  = new ArrayList<Vertex>();
	private List<Edge>    edges = new ArrayList<Edge>();
    
   
	public void addVertex(Vertex v){
		vertices.add(v);
		elements.add(v);
	}
	public void addVertices(List<Vertex> list){
		vertices.addAll(list);
		elements.addAll(list);
	}

	public void addEdge(Edge edge){
		edges.add(edge);
		elements.add(edge);
	}
	public void addEdges(List<Edge> list){
		edges.addAll(list);
		elements.addAll(list);
	}
	public Iterator iterator(){
		return elements.iterator();
	}
	public Iterator iteratorVertices(){
		return vertices.iterator();
	}
	public Iterator iteratorEdges(){
		return edges.iterator();
	}

	public void addLabel(Label label){
		//Debug.print("DiagramModel.addLabel invoked");
		elements.add(label);
	}
	public void addElement(Element element) {
		elements.add(element);	
	}
	public void removeElement(Element element){
		elements.remove(element);
		vertices.remove(element);
		edges.remove(element);
	}
	public void removeVertex(Vertex vertex){
		vertices.remove(vertex);
		elements.remove(vertex);
	}
	public void removeVertices(List<Vertex> list){
		vertices.removeAll(list);
		elements.removeAll(list);
	}
	public void removeEdge(Edge edge){
		edges.remove(edge);
		elements.remove(edge);
	}
	public void removeEdges(List<Edge> list){
		edges.removeAll(list);
		elements.removeAll(list);
	}
	
	public static int generateNewID(){
		idCounter++;
		return idCounter;
	}
	public void generateTestValues(){
		addVertex(new Vertex(70,90));
    	addVertex(new Vertex(270,290));
    	addVertex(new Vertex(270,230));
    	addVertex(new Vertex(210,290));
    	addVertex(new Vertex(400,90));
    	addVertex(new Vertex(400,690));
    	addVertex(new Vertex(700,90));
    	addVertex(new Vertex(700,490));
	}
	public boolean isEmpty(){
		return elements.isEmpty();
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public List<Vertex> getVertices() {
		return vertices;
	}
	
	public List<Element> getElements() {
		return elements;
	}
	
}