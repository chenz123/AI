package agents;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public interface Agent<G extends Graph<V, E>, V extends Vertex<E,V>, E extends Edge<V,E>> {

	public int drive(E e);
	
	public int noOp();
	
	public V getLocation();
	
	public G getGraph();
}
