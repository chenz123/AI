import java.util.AbstractCollection;


public interface Simulation<G extends Graph<V, E>, A extends Agent<G, V, E>, V extends Vertex, E extends Edge<V>>  {

	public boolean agentsHaveMovesLeft();

	public void moveAgents() throws agentHasNoMoveException;
	
	public void moveAgent(A a) throws agentHasNoMoveException;

	public void addAgent(A agent, V location);

	public G getGraph();
	
	public AbstractCollection<A> getAgents();
	
	public void setGraph(G graph);
	
	public AbstractCollection<A> getAgentsInVertex(V v);
}
