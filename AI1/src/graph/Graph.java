package graph;

import java.io.IOException;
import java.util.AbstractCollection;

import syriangraph.EdgeAlreadyExistsException;

import exceptions.VertexAlreadyExistsException;


public interface Graph<V, E> {
	public AbstractCollection<V> getVertices();
	
	
	public AbstractCollection<E> getEdges();
	
	public void setEdges(AbstractCollection<E> edges);
	
	public void setVertices(AbstractCollection<V> vertices);
	
	public V getVertexByNumber(int num);
	
	public V getVertexByNumber(int num, boolean b) throws VertexAlreadyExistsException;
	
	public V addVertex() throws VertexAlreadyExistsException;
	
	public V addVertex(V v) throws VertexAlreadyExistsException;
	
	public E addEdge(V v1, V v2) throws EdgeAlreadyExistsException;
	
	public E getEdgeByVertices(V v1, V v2);
	
	public void exportToDotFile(String filename) throws IOException;
}
