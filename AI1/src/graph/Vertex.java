package graph;
import java.util.AbstractCollection;

public interface Vertex<T extends Edge<?>>{
	
	public void setNumber(int number);
	
	public void setName(String name);
	
	public void setEdges(AbstractCollection<T> edges);
	
	public int getNumber();
	
	public String name();
	
	public AbstractCollection<T> getEdges();
	
	/**
	 * Add edge to vertex
	 * @param e
	 * 				edge to add to the vertex
	 * @return
	 * 				true - if successful
	 * 				false - otherwise
	 */
	public boolean addEdge(T e);
	
	/**
	 * Remove edge from vertex
	 * @param e
	 * 				edge to remove from vertex
	 * @return
	 * 				true - if successful
	 * 				false-  otherwise
	 */
	public boolean removeEdge(T e);

	
}
