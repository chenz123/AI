package agents;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class BaseAgent<G extends Graph<V, E>, V extends Vertex<E, V>, E extends Edge<V, E>> implements Agent<G, V, E>{

	private V location;
	private G graph;
	
	public BaseAgent(G graph){
		
	}

	@Override
	public int drive(E e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int noOp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public V getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public G getGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
