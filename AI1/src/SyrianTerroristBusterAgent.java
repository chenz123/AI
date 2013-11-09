
public class SyrianTerroristBusterAgent extends SyrianAgent {

	public SyrianTerroristBusterAgent(String name) {
		super(name);
	}

	@Override
	public SyrianEdge getMove(SyrianGraph graph) throws agentHasNoMoveException {
		throw new agentHasNoMoveException(this);
	}


}
