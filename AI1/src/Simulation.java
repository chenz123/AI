import java.util.AbstractCollection;


public interface Simulation<G extends Graph<V, E>, A extends Agent<G, V, E>, V extends Vertex, E extends Edge<V>>  {

	/**
	 * check to see if any agents have any moves left.
	 * @return
	 * 			true - if an agent with moves exists
	 * 			false - otherwise
	 */
	public boolean agentsHaveMovesLeft();

	/**
	 * move all agents on this graph one time
	 * @throws agentHasNoMoveException
	 * 			if an agent has no moves // TODO: verify
	 * @throws NoAgentsInSimulationException
	 * 			if no agents are in the simulation
	 */
	public void moveAgents() throws agentHasNoMoveException, NoAgentsInSimulationException;
	
	/**
	 * move a single agent on this simulation on time
	 * @param a - the agent to move
	 * @throws agentHasNoMoveException
	 * 			if the agent has no move
	 * @throws AgentIsDoneException
	 * 			if agent is done in current
	 * 			simulation
	 */
	public void moveAgent(A a) throws agentHasNoMoveException, AgentIsDoneException;

	/**
	 * add an agent to the simulation
	 * @param agent
	 * 			the agent to add to the simulation
	 */
	public void addAgent(A agent);

	/**
	 * get the graph used in this simulation
	 * @return
	 * 			the simulation's graph
	 */
	public G getGraph();
	
	/**
	 * get agents from simulation
	 * @return
	 * 			a collection of all agents on simulation
	 */
	public AbstractCollection<A> getAgents();
	
	/**
	 * set the simulation's graph
	 * @param graph - the graph to use in the simulation
	 */
	public void setGraph(G graph);
	
	/**
	 * get all agents found on a certain vertex
	 * @param v - the vertex to find agents on
	 * @return
	 * 			a collection of all agents on a
	 * 			certain vertex
	 */
	public AbstractCollection<A> getAgentsInVertex(V v);

	/**
	 * print all scores for agents in this
	 * simulation so far
	 */
	public void printScores();

	/**
	 * export simulation to a dot file
	 * @param filename
	 * 			the filename to write to
	 */
	public void toDotFile(String filename);
}
