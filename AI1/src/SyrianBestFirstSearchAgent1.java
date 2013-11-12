
public class SyrianBestFirstSearchAgent1 extends SyrianAgent{

	public SyrianBestFirstSearchAgent1(String name, SyrianVertex location,
			SyrianVertex target, SyrianGraph graph) {
		super(name, location, target);
		// make some pre-calculations on graph
		
	}

	@Override
	public SyrianEdge getMove(SyrianGraph graph)
			throws agentHasNoMoveException, AgentIsDoneException {
		VTree<SyrianGraph, SyrianVertex, SyrianEdge> vt = new VTree<SyrianGraph, SyrianVertex, SyrianEdge>(this.getLocation(), graph, new ZeroHeuristicFunction<SyrianVertex, SyrianEdge>());
		
		if (this.getTarget() == this.getLocation()){
			throw new AgentIsDoneException(this);
		}
		return vt.getPathTo(this.getTarget()).get(0);
		
	}

}
