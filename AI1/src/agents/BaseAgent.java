package agents;

import exceptions.NoMoreMovesException;
import exceptions.VertexNotPartOfEdgeException;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

public class BaseAgent<G extends Graph<V, E>, V extends Vertex<E, V>, E extends Edge<V, E>> implements Agent<G, V, E>{

	private V location;
	private G graph;
	private long score;
	
	public BaseAgent(G graph, V start){
		this.graph = graph;
		this.location = start;
	}

	@Override
	public long drive(E e) throws VertexNotPartOfEdgeException, NoMoreMovesException {
		// make sure this is a valid move
		if (e.hasVertex(this.location)){
			// move agent
			this.location = e.otherVertex(this.location);
			// update score
			this.score += e.getWeight();
			return e.getWeight();
		}
		
		return this.score;
	}

	@Override
	public int noOp() {
		return 0;
	}

	@Override
	public V getLocation() {
		return this.location;
	}

	@Override
	public G getGraph() {
		return this.graph;
	}

}
