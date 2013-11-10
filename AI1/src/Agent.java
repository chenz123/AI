
public interface Agent<G extends Graph<V, E>, V extends Vertex, E extends Edge<V>> {

	public boolean hasMovesLeft(G graph);
	
	public void setName(String name);
	public String getName();
	public V getLocation();
	public void setLocation(V location);
	public E getMove(G graph) throws agentHasNoMoveException, AgentIsDoneException;
	public int addScore(int toAdd);
	public void noOp();
	public int getScore();
	public V getTarget();
	public void setTarget(V target);
	public int getChemicalsEvacuated();
	public void setChemicalsEvacuated(int ce);
	public int getTerroristsBusted();
	public void setTerroristsBusted(int tb);
	public void move(G graph) throws agentHasNoMoveException, AgentIsDoneException;
}
