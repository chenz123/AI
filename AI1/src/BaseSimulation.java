import java.util.AbstractCollection;
import java.util.ArrayList;

public abstract class BaseSimulation<G extends Graph<V, E>, A extends Agent<G, V, E>, V extends Vertex, E extends Edge<V>>
		implements Simulation<G, A, V, E> {

	private G graph;
	private AbstractCollection<A> agents;

	public BaseSimulation(G g) {
		this.graph = g;
		this.agents = new ArrayList<A>();
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
	public void moveAgents() {
		for (A a : this.getAgents()) {
			try {
				System.out.println("Moving agent " + a.getName());
				this.moveAgent(a);
			} catch (agentHasNoMoveException e) {
				System.out.println("Agent " + a.getName()
						+ "does not have any moves left - doing no-op");
				a.noOp();
			}
		}
	}

	@Override
	public void addAgent(A agent) {
		this.agents.add(agent);
	}

	@Override
	public G getGraph() {
		return this.graph;
	}

	@Override
	public AbstractCollection<A> getAgents() {
		return this.agents;
	}

	@Override
	public void setGraph(G graph) {
		this.graph = graph;
	}

	
	public AbstractCollection<A> getAgentsInVertex(V v){
		AbstractCollection<A> res = new ArrayList<A>();
		for (A a: this.getAgents()){
			if (a.getLocation() == v){
				res.add(a);
			}
		}
		
		return res;
	}
}
