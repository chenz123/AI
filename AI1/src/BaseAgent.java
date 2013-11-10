public abstract class BaseAgent<G extends Graph<V, E>, V extends Vertex, E extends Edge<V>>
		implements Agent<G, V, E> {

	private V target;
	
	private static final int NO_OP_SMALL_COST = 1;
	private V location;
	private String name;
	private int score;
/*
	public BaseAgent(String name) {
		this.name = name;
		this.score = 0;
	}
*/
	public BaseAgent(String name, V location, V target) {
		this.location = location;
		this.target = target;
		this.name = name;
		this.score = 0;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public V getLocation() {
		return this.location;
	}

	public void setLocation(V location) {
		this.location = location;
	}

	public boolean hasMovesLeft(G graph) {
		try {
			return this.getMove(graph) != null;
		} catch (agentHasNoMoveException e) {
			return false;
		}
	}

	public int addScore(int toAdd) {
		this.score += toAdd;
		return this.score;
	}

	public void noOp() {
		this.score += BaseAgent.NO_OP_SMALL_COST;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public V getTarget(){
		return this.target;
	}
	
	public void setTarget(V target){
		this.target = target;
	}
}
