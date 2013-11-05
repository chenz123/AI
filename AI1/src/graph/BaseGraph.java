package graph;
import java.util.AbstractCollection;
import java.util.ArrayList;


public class BaseGraph<V extends Vertex<E,V>, E extends Edge<V,E>> implements Graph<V, E>{

	private AbstractCollection<V> vertices;
	private AbstractCollection<E> edges;
	
	public BaseGraph(){
		this.vertices = new ArrayList<V>();
		this.edges = new ArrayList<E>();
	}
	public BaseGraph(AbstractCollection<V> vertices, AbstractCollection<E> edges){
		this.edges = edges;
		this.vertices = vertices;
	}
	
	public AbstractCollection<V> getVertices(){
		return this.vertices;
	}
	
	public AbstractCollection<E> getEdges(){
		return this.edges;
	}
	
	public void setEdges(AbstractCollection<E> edges){
		this.edges = edges;
	}
	
	public void setVertices(AbstractCollection<V> vertices){
		this.vertices = vertices;
	}
	
}
