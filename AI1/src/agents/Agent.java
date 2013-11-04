package agents;

import exceptions.NoMoreMovesException;
import exceptions.VertexNotPartOfEdgeException;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

public interface Agent<G extends Graph<V, E>, V extends Vertex<E,V>, E extends Edge<V,E>> {

	public long drive(E e) throws VertexNotPartOfEdgeException, NoMoreMovesException;
	
	public int noOp();
	
	public V getLocation();
	
	public G getGraph();
}
