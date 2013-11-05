package simulations;

import java.util.AbstractList;
import java.util.ArrayList;

import syriangraph.Habitable;
import exceptions.NoMoreMovesException;
import exceptions.VertexNotPartOfEdgeException;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import agents.Agent;

public class BaseSimulation<G extends Graph<V, E>, A extends Agent<G, V, E>, V extends Vertex<E, V> & Habitable<A>, E extends Edge<V, E>>
		implements Simulation<G, A, V, E> {

	private G graph;
	private AbstractList<A> agents;

	@Override
	public void setGraph(G graph) {
		this.graph = graph;
	}

	@Override
	public G getGraph() {
		return this.graph;
	}

//	@Override
//	public void moveAgent(A agent) throws VertexNotPartOfEdgeException, NoMoreMovesException, AgentLocationNotFoundException {
//		agent.drive(agent.getNextMove(this.graph, this.getAgentLocation(agent)));
//	}

	@Override
	public boolean addAgent(A agent, V start) throws AgentAlreadyInSimulationException {
		
		if (null == this.agents){
			this.agents = new ArrayList<A>();
		}
		
		if (this.agents.contains(agent)){
			throw new AgentAlreadyInSimulationException("Agent " + agent.getName() + " already in simulation.");
			// return
		}
		
		return this.agents.add(agent);
	}

	@Override
	public V getAgentLocation(A agent) throws AgentLocationNotFoundException {
		for (V v : this.graph.getVertices()){
			if (v.getAgents().contains(agent)){
				return v;
			}
		}
		throw new AgentLocationNotFoundException("Agent "+agent.getName()+" was not found on graph!");
	}

	@Override
	public void moveAgents() throws AgentLocationNotFoundException {
		for (A a : this.agents){
			a.traverse(a.getNextMove(this.graph, this.getAgentLocation(a)));
		}
	}

}
