
public class SyrianDumbBestFirstSearchAgent extends SyrianAgent{

	public SyrianDumbBestFirstSearchAgent(String name, SyrianVertex location,
			SyrianVertex target) {
		super(name, location, target);
	}

	@Override
	public SyrianEdge getMove(SyrianGraph graph)
			throws agentHasNoMoveException, AgentIsDoneException {
		VTree<SyrianGraph, SyrianVertex, SyrianEdge> vt = new VTree<SyrianGraph, SyrianVertex, SyrianEdge>(this.getLocation(), graph);
		
		if (this.getTarget() == this.getLocation()){
			throw new AgentIsDoneException(this);
		}
		return vt.getPathTo(this.getTarget()).get(0);
		
	}

}
