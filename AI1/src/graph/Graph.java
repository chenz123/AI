package graph;

import java.util.AbstractCollection;

import exceptions.VertexAlreadyExistsException;


public interface Graph<V, E> {
	public AbstractCollection<V> getVertices();
	
	
	public AbstractCollection<E> getEdges();
	
	public void setEdges(AbstractCollection<E> edges);
	
	public void setVertices(AbstractCollection<V> vertices);
	
	public V getVertexByNumber(int num);
	
	public V getVertexByNumber(int num, boolean b);
	
	public V addVertex(int num);
	public V addVertex(V v) throws VertexAlreadyExistsException;
}
