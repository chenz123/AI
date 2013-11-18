public abstract class BaseAgent<G extends Graph<V, E>, V extends Vertex, E extends Edge<V>>
		implements Agent<G, V, E> {

	private V target;
	
	private static final int NO_OP_SMALL_COST = 1;

	public static final String PATH_COLOR = "purple";
	private V location;
	private String name;
	private int score;
	private int chemicalsEvacuated;
	private int terroristsBusted;
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
		this.chemicalsEvacuated = 0;
		this.terroristsBusted = 0;
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
		} catch (AgentHasNoMoveException e) {
			return false;
		} catch (AgentIsDoneException e) {
			return false; // will be removed later (TODO: think if this is good...)
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
	
	public void setChemicalsEvacuated(int num){
		this.chemicalsEvacuated = num;
	}
	
	public int getChemicalsEvacuated(){
		return this.chemicalsEvacuated;
	}
	
	public int getTerroristsBusted(){
		return this.terroristsBusted;
	}
	
	public void setTerroristsBusted(int te){
		this.terroristsBusted = te;
	}
}
