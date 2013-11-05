package agents;

import java.io.IOException;

import aiutils.Utils;

import exceptions.NoMoreMovesException;
import exceptions.VertexNotPartOfEdgeException;
import graph.Edge;
import graph.BaseGraph;
import graph.Vertex;

public class BaseAgent<G extends BaseGraph<V, E>, V extends Vertex<E, V>, E extends Edge<V, E>> implements Agent<G, V, E>{

	private static final String AGENT_COLOR = "blue";
	private V location;
	private G graph;
	private long score;
	
	public BaseAgent(G graph, V start){
		this.graph = graph;
		this.location = start;
	}

	@Override
	public long drive(E e) throws VertexNotPartOfEdgeException, NoMoreMovesException {
		// make sure this is a valid move
		if (e.hasVertex(this.location)){
			// move agent
			this.location = e.otherVertex(this.location);
			// update score
			this.score += e.getWeight();
			return e.getWeight();
		}
		
		return this.score;
	}

	@Override
	public int noOp() {
		return 0;
	}

	@Override
	public V getLocation() {
		return this.location;
	}

	@Override
	public G getGraph() {
		return this.graph;
	}
	
	public void exportToDotFile(String filename) throws IOException {

		String sep = System.getProperty("line.separator");
		String res = this.getGraph().getDotFileStatements();
		
		res += this.getLocation().getNumber()+"[color="+BaseAgent.AGENT_COLOR+"]" + sep;
		Utils.writeFile(res, filename);
	}

}
