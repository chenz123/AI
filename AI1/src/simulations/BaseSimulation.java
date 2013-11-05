package simulations;

import exceptions.NoMoreMovesException;
import exceptions.VertexNotPartOfEdgeException;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import agents.Agent;

public class BaseSimulation<G extends Graph<V, E>, A extends Agent<G, V, E>, V extends Vertex<E, V>, E extends Edge<V, E>>
		implements Simulation<G, A, V, E> {

	private G graph;

	@Override
	public void setGraph(G graph) {
		this.graph = graph;
	}

	@Override
	public G getGraph() {
		return this.graph;
	}

	@Override
	public void moveAgent(A agent) throws VertexNotPartOfEdgeException, NoMoreMovesException {
		agent.drive(agent.chooseMove());
	}

	@Override
	public void addAgent(A agent, V start) {
		// TODO Auto-generated method stub
		
	}

}
