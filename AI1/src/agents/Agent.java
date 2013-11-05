package agents;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public interface Agent<G extends Graph<V, E>, V extends Vertex<E,V>, E extends Edge<V,E>> {

//	public void drive(E e) throws VertexNotPartOfEdgeException, NoMoreMovesException;
	
	public E getNextMove(G graph, V location);
	
	public void noOp();
	
	public String getName();
	
	public void setName(String name);
	
	public long getScore();
	
//	public boolean carryChemicals(V location);
//	
//	public boolean leaveChemicals(V location);
//	
//	public boolean requestEscort(V location);
//	
//	public boolean leaveEscort(V location);
	
//	public V getLocation();
//	
//	public G getGraph();
//	
//	public E chooseMove();
}
