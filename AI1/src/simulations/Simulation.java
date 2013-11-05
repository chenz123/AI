package simulations;

import exceptions.NoMoreMovesException;
import exceptions.VertexNotPartOfEdgeException;
import agents.Agent;
import graph.Edge;
import graph.Graph;
import graph.Vertex;

public interface Simulation<G extends Graph<V, E>, A extends Agent<G, V, E>, V extends Vertex<E, V>, E extends Edge<V, E>> {

	public void setGraph(G graph);

	public G getGraph();

//	public void moveAgent(A agent) throws VertexNotPartOfEdgeException, NoMoreMovesException, AgentLocationNotFoundException;
	public void moveAgents() throws AgentLocationNotFoundException;
	public V getAgentLocation(A agent) throws AgentLocationNotFoundException;
	
//	public void setAgentLocation(A agent);
	
	public boolean addAgent(A agent, V start) throws AgentAlreadyInSimulationException;
}
