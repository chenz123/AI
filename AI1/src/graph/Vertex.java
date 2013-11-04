package graph;
import java.util.AbstractCollection;

import exceptions.VertexNotPartOfEdgeException;

public interface Vertex<E extends Edge<V, E>, V extends Vertex<E,V>>{
	
	public void setNumber(int number);
	
	public void setName(String name);
	
	public void setEdges(AbstractCollection<E> edges);
	
	public int getNumber();
	
	public String name();
	
	public AbstractCollection<E> getEdges();
	
	/**
	 * Add edge to vertex
	 * @param e
	 * 				edge to add to the vertex
	 * @return
	 * 				true - if successful
	 * 				false - otherwise
	 */
	public boolean addEdge(E e);
	
	/**
	 * Remove edge from vertex
	 * @param e
	 * 				edge to remove from vertex
	 * @return
	 * 				true - if successful
	 * 				false-  otherwise
	 */
	public boolean removeEdge(E e);
	
	/**
	 * Get all vertices that have an edge connecting
	 * them to this vertex
	 * @return
	 * 				a collection of all neighboring vertices
	 * @throws VertexNotPartOfEdgeException if vertex not part of edge 
	 */
	public AbstractCollection<V> getNeighbours() throws VertexNotPartOfEdgeException;

	/**
	 * Check if vertex has an edge connecting it with vertex v 
	 * @param v
	 * 			destination vertex
	 * @return
	 * 			the edge connecting this vertex to v
	 * 			null otherwise
	 */
	public E hasEdgeTo(V v);
}
