import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;

public abstract class BaseSimulation<G extends Graph<V, E>, A extends Agent<G, V, E>, V extends Vertex, E extends Edge<V>>
		implements Simulation<G, A, V, E> {

	private G graph;
	private AbstractList<A> agents;
	private AbstractList<A> finishedAgents;
	private A currentAgent;

	private static int MOVE = 0;

	public BaseSimulation(G g) {
		this.graph = g;
		this.agents = new ArrayList<A>();
		this.finishedAgents = new ArrayList<A>();
	}

	@Override
	public boolean agentsHaveMovesLeft() {
		for (A a : this.getAgents()) {
			System.out.println("Checking if " + a.getName()
					+ " has more moves for now...");
			if (a.hasMovesLeft(this.getGraph())) {
				System.out.println(a.getName() + " has more moves!");
				return true;
			} else {
				System.out.println(a.getName() + " doesn't have more moves!");
			}
		}

		return false;
	}

	@Override
	public void addAgent(A agent) {
		if (this.agents.size() == 0) {
			this.currentAgent = agent;
		}
		this.agents.add(agent);
	}

	@Override
	public G getGraph() {
		return this.graph;
	}

	@Override
	public AbstractList<A> getAgents() {
		return this.agents;
	}

	@Override
	public void setGraph(G graph) {
		this.graph = graph;
	}

	public AbstractCollection<A> getAgentsInVertex(V v) {
		AbstractCollection<A> res = new ArrayList<A>();
		for (A a : this.getAgents()) {
			if (a.getLocation() == v) {
				res.add(a);
			}
		}

		return res;
	}

	public void printScores() {
		for (A a : finishedAgents) {
			System.out.println("Agent " + a.getName()
					+ " finished with score: " + a.getScore() + ", he busted "
					+ a.getTerroristsBusted() + " terrorists and evacuated "
					+ a.getChemicalsEvacuated() + " chemicals.");
		}
		for (A a : agents) {
			System.out.println("Agent " + a.getName()
					+ " finished with score: " + a.getScore() + ", he busted "
					+ a.getTerroristsBusted() + " terrorists and evacuated "
					+ a.getChemicalsEvacuated() + " chemicals.");
		}
	}

	public void resetColors() {
		for (V v : this.getGraph().getVertices()) {
			v.setColor(null);
		}
		for (E e : this.getGraph().getEdges()) {
			e.setColor(null);
		}
	}

	public void setCurrentAgent(A agent) {
		this.currentAgent = agent;
	}

	public A getCurrentAgent() {
		return this.currentAgent;
	}

	public String moveNextAgentInLine(){
		return this.moveAgent(this.currentAgent);
	}

	@Override
	public void moveNextAgent() {

		try {
			this.getCurrentAgent().move(this.getGraph());
		} catch (AgentHasNoMoveException e) {
			System.out.println("Agent " + this.getCurrentAgent().getName()
					+ " has no moves. Doing no-op.");
			this.getCurrentAgent().noOp();
			// e.printStackTrace();
		} catch (AgentIsDoneException e) {
			System.out.println("Agent " + this.getCurrentAgent().getName()
					+ " is done and will now be removed from the simulation.");
			A toRemove = this.getCurrentAgent();
			this.getAgents().remove(toRemove);
			// e.printStackTrace();
		}
		
		try {
			this.advanceAgent();
		} catch (NoAgentsInSimulationException e) {
			System.out.println("Simulation is done.");
			System.out.println("Scores:");
			for (A a : this.getFinishedAgents()){
				System.out.println(a.toString());
			}
		}
	}

	public void advanceAgent() throws NoAgentsInSimulationException {
		if (this.agents.isEmpty()){
			throw new NoAgentsInSimulationException();
		}
		// get next agent or return to first agent if no next exists.
		this.currentAgent = this.agents.get(this.agents
				.indexOf(this.currentAgent) == (this.agents.size() - 1) ? 0
				: this.agents.indexOf(this.currentAgent) + 1);
	}

	
	@Override
	public String moveAgent(A a){
		try {
			a.move(this.getGraph());
		} catch (AgentHasNoMoveException e) {
			System.out.println("Agent " + a.getName() + " had no moves! Doing no-op.");
			a.noOp();
//			e.printStackTrace();
		} catch (AgentIsDoneException e) {
			System.out.println("Agent " + a.getName() + " is done. Removing him from simulation!");
			this.finishedAgents.add(a);
			System.out.println(this.finishedAgents.size() + " agents finished");
			this.agents.remove(a);
			System.out.println(this.agents.size() + " agents left");
//			e.printStackTrace();
		} catch (NullPointerException npe){
			System.out.println("We're sorry, but it seems the system can't handle your request to move agents right now!");
		}
		String outfile = this.toDotFile("outfile"+(BaseSimulation.MOVE++)+".dot");
		this.resetColors();
		// get next agent in turn
		try {
			this.advanceAgent();
		} catch (NoAgentsInSimulationException e) {
			System.out.println("Simulation finished!");
			this.currentAgent = null;
		}
		return outfile;
	}

	public AbstractCollection<A> getFinishedAgents(){
		return this.finishedAgents;
	}
}
