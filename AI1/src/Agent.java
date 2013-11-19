/**
 * This is the agent class, representing
 * various (simple and less simple) agents
 * 
 * @author Reut Sharabani & Chen Zrubavel
 *
 * @param <G> - a graph class this agent is based on
 * @param <V> - a vertex class this agent is based on
 * @param <E> - an edge class this agent is based on
 */
public interface Agent<G extends Graph<V, E>, V extends Vertex, E extends Edge<V>> {

	/**
	 * check to see if the agent has any moves
	 * on the current graph
	 * @param graph - the graph to check the agent on
	 * @return 
	 * 			true - if there are moves left
	 * 			false - otherwise
	 */
	public boolean hasMovesLeft(G graph);
	
	/**
	 * set the agent's name
	 * @param name - the name to set the agent to
	 */
	public void setName(String name);
	
	/**
	 * get the agent's name
	 * @return
	 * 			the agent's name
	 */
	public String getName();
	
	/**
	 * get the agent's current location
	 * @return
	 * 			the agent's location
	 */
	public V getLocation();
	
	/**
	 * set the agent's current location
	 * @param location - the location to set the agent to
	 */
	public void setLocation(V location);
	
	/**
	 * get the agent's next move
	 * @param graph - the graph the agent is on
	 * @return
	 * 			the next Edge object (or an extension of
	 * 			an Edge object) the agent will traverse next.
	 * @throws AgentHasNoMoveException
	 * 			if agent has no moves left
	 * @throws AgentIsDoneException
	 * 			if agent is done and will have no moves
	 */
	public E getMove(G graph) throws AgentHasNoMoveException, AgentIsDoneException;
	
	/**
	 * add score to the agent's current score
	 * @param toAdd - how much score to add
	 * @return
	 * 		the agent's current score
	 */
	public int addScore(int toAdd);
	
	/**
	 * make the agent do nothing
	 */
	public void noOp();
	
	/**
	 * get the agent's score
	 * @return
	 * 			the agent's score
	 */
	public int getScore();
	
	/**
	 * 
	 * @return
	 */
	public V getTarget();
	
	/**
	 * set the agent's target vertex
	 * @param target - a target vertex for the agent
	 */
	public void setTarget(V target);
	
	/**
	 * return the number of chemicals the agent evacuated
	 * @return
	 * 			number of chemicals evacuated by the agent
	 */
	public int getChemicalsEvacuated();
	
	/**
	 * set the number of chemicals evacuated by the agent
	 * @param ce
	 * 			the number of chemicals evacuated by the agent
	 */
	public void setChemicalsEvacuated(int ce);
	
	/**
	 * get number of terrorists busted by the agent
	 * @return
	 * 			the number of terrorists the agent busted
	 */
	public int getTerroristsBusted();
	
	/**
	 * set the number of terrorists the agent's busted
	 * @param tb - the number of terrorists the agent busted
	 */
	public void setTerroristsBusted(int tb);
	
	/**
	 * move the agent on a graph
	 * @param graph - the graph the agent is moving on
	 * @throws AgentHasNoMoveException
	 * 			if agent has no move now
	 * @throws AgentIsDoneException
	 * 			if agent has no moves left
	 */
	public void move(G graph) throws AgentHasNoMoveException, AgentIsDoneException;
}
