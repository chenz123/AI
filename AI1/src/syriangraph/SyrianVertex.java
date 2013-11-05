package syriangraph;

import java.util.AbstractCollection;
import java.util.ArrayList;

import exceptions.AgentAlreadyInVertexException;
import exceptions.AgentNotInVertexException;
import agents.Agent;
import graph.BaseVertex;

public class SyrianVertex extends BaseVertex<SyrianVertex, SyrianEdge> implements Habitable<Agent<?,?,?>>{
	
	private boolean hasMilitary, hasChemicals, isGoal;
	private AbstractCollection<Agent<?,?,?>> agents;
	
	public SyrianVertex(int number, boolean hasChemicals, boolean hasMilitary, boolean isGoal){
		super();
		this.hasChemicals = hasChemicals;
		this.hasMilitary = hasMilitary;
		this.isGoal = isGoal;
	}

	/*
	 * Syrian-specific attributes, methods etc.
	 */
	
	public SyrianVertex() {
		super();
	}

	public boolean hasChemicals(){
		return this.hasChemicals;
	}
	
	public boolean hasMilitary(){
		return this.hasMilitary;
	}
	
	public boolean isGoal(){
		return this.isGoal;
	}

	@Override
	public void addAgent(Agent<?, ?, ?> agent) throws AgentAlreadyInVertexException {
		//  make sure agent array exists
		if (null == this.agents){
			this.agents = new ArrayList<Agent<?,?,?>>();
		}
		
		// make sure agent isn't already here (no-op should do nothing, not add him again...)
		if (this.agents.contains(agent)){
			throw new AgentAlreadyInVertexException("Agent " + agent.getName() + " was already at vertex "+this.getNumber());
		}
		
		this.agents.add(agent);
	}

	@Override
	public boolean removeAgent(Agent<?, ?, ?> agent) throws AgentNotInVertexException {
		
		if (null == this.agents || !this.agents.contains(agent)){
			throw new AgentNotInVertexException("Agent "+ agent.getName() + " was not in vertex " + this.getNumber());
		}
		
		return this.agents.remove(agent);
	}

	@Override
	public AbstractCollection<Agent<?, ?, ?>> getAgents() {
		return this.agents;
	}
}
