package syriangraph;

import java.util.AbstractCollection;

import exceptions.AgentAlreadyInVertexException;
import exceptions.AgentNotInVertexException;
import agents.Agent;

public interface Habitable<A extends Agent<?,?,?>> {

	public void addAgent(A agent) throws AgentAlreadyInVertexException;
	
	public boolean removeAgent(A agent) throws AgentNotInVertexException;
	
	public AbstractCollection<A> getAgents();
}
