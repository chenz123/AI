
public interface Agent<G extends Graph<V, E>, V extends Vertex, E extends Edge<V>> {

	public boolean hasMovesLeft(G graph);
	
	public void setName(String name);
	public String getName();
	public V getLocation();
	public void setLocation(V location);
	public E getMove(G graph) throws agentHasNoMoveException;
	public int addScore(int toAdd);
	public void noOp();
	public int getScore();
	public V getTarget();
	public void setTarget(V target);
	
}
