import java.util.AbstractList;

public class SyrianBasicHeuristicAgent extends SyrianAgent {

	public SyrianBasicHeuristicAgent(String name, SyrianVertex location,
			SyrianVertex target) {
		super(name, location, target);
	}

	@Override
	public SyrianEdge getMove(SyrianGraph graph)
			throws agentHasNoMoveException, AgentIsDoneException {
		
		// this is basically the reverse of g(), we want to
		// expand nodes that seem to cost the least
		// in short: we expand the node that it's easiest
		// to get to.
		VTree<SyrianGraph, SyrianVertex, SyrianEdge> vt = new VTree<SyrianGraph, SyrianVertex, SyrianEdge>(
				this.getLocation(), graph,
				new ZeroHeuristicFunction<SyrianVertex, SyrianEdge>());

		if (this.getTarget() == this.getLocation()) {
			throw new AgentIsDoneException(this);
		}
		AbstractList<SyrianEdge> path = vt.getPathTo(this.getTarget());
		
		// paint all path in path's colors
		for (SyrianEdge e: path){
			e.setColor(BaseAgent.PATH_COLOR);
		}
		
		return path.get(0);

	}

}
