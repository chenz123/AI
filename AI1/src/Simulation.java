import java.util.AbstractCollection;
import java.util.AbstractList;


public interface Simulation<G extends Graph<V, E>, A extends Agent<G, V, E>, V extends Vertex, E extends Edge<V>>  {

	/**
	 * check to see if any agents have any moves left.
	 * @return
	 * 			true - if an agent with moves exists
	 * 			false - otherwise
	 */
	public boolean agentsHaveMovesLeft();

	/**
	 * move all agents on this graph one time - deprecated
	 */
	public void moveAgents();
	
	/**
	 * move a single agent on this simulation on time
	 * @param a - the agent to move
	 * @return
	 * 			path to output .dot file if such exists
	 * 			null otherwise
	 */
	public String moveAgent(A a);

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
	public AbstractList<A> getAgents();
	
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
	 * reset all colors on vertices and edges
	 */
	public void resetColors();
	
	/**
	 * export simulation to a dot file
	 * @param filename
	 * 			the filename to write to
	 * @return
	 * 			filename of output file
	 */
	public String toDotFile(String filename);
	
	
	public void moveNextAgent();
	
	public A getCurrentAgent();
	
	public void setCurrentAgent(A agent);
	
	/**
	 * moves an next agent one move
	 * @return
	 * 			path to an output .dot file if such exists.
	 * 			otherwise null
	 */
	public String moveNextAgentInLine();
	
	
	public void advanceAgent() throws NoAgentsInSimulationException;
	
	public AbstractCollection<A> getFinishedAgents();
}
