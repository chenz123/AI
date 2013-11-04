package graph;
import java.util.AbstractCollection;


public class Graph<T extends Vertex<?>, U extends Edge<?>>{

	private AbstractCollection<T> vertices;
	private AbstractCollection<U> edges;
	
	public Graph(AbstractCollection<T> vertices, AbstractCollection<U> edges){
		this.edges = edges;
		this.vertices = vertices;
	}
	
	public AbstractCollection<T> getVertices(){
		return this.vertices;
	}
	
	public AbstractCollection<U> getEdges(){
		return this.edges;
	}
	
	public void setEdges(AbstractCollection<U> edges){
		this.edges = edges;
	}
	
	public void setVertices(AbstractCollection<T> vertices){
		this.vertices = vertices;
	}
	
}
