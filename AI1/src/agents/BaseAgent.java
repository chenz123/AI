package agents;

import graph.Edge;
import graph.BaseGraph;
import graph.Vertex;

public abstract class BaseAgent<G extends BaseGraph<V, E>, V extends Vertex<E, V>, E extends Edge<V, E>> implements Agent<G, V, E>{

	@SuppressWarnings("unused")
	private static final String AGENT_COLOR = "blue";
	private long score;
	private String name;
	
	private static int ids=1;
	
	public BaseAgent(){
		this.score = 0;
		this.name = "BaseAgent" + BaseAgent.ids++;
	}

	@Override
	public void noOp() {
	}

	public long getScore(){
		return this.score;
	}
	
	public String getName(){
		return this.name;
	}

}
