import java.util.AbstractCollection;
import java.util.HashMap;

public class SyrianTerroristBusterAgent extends SyrianAgent {

	public SyrianTerroristBusterAgent(String name, SyrianVertex location, SyrianVertex target) {
		super(name, location, target);
	}

	@Override
	public SyrianEdge getMove(SyrianGraph graph) throws agentHasNoMoveException {
		// first pick up escort if needed, simplifies things
		if (!this.hasEscort() && this.getLocation().hasEscort()){
			try {
				this.takeEscort();
			} catch (AgentAlreadyHasEscortException e) {
				System.out.println("Critical error in terrorist buster, tried to take escort when already has escort!");
				e.printStackTrace();
			} catch (LocationDoesntHaveEscortException e) {
				System.out.println("Critical error in terrorist buster, tried to take escort when no escort available!");
				e.printStackTrace();
			}
		}
		
		HashMap<String, HashMap<Integer, Integer>> shortestPaths = graph
				.shortestPathsAll(this.getLocation());
		HashMap<Integer, Integer> distances = shortestPaths.get("distances");
		HashMap<Integer, Integer> previous = shortestPaths.get("previous");

		SyrianVertex destination = null;

		// our options vary according to whether or not we have escort with us
		AbstractCollection<SyrianVertex> options = this.hasEscort() ? graph.getVerticesAdjacentToTerrorists() : graph.getVerticesWithEscort();
		
		for (SyrianVertex v : options) {
			if (destination == null
					|| (distances.get(v.getNumber()) < distances.get(destination.getNumber()) || (distances
							.get(v.getNumber()) == distances.get(destination.getNumber()) && v
							.getNumber() < destination.getNumber()))) {
				destination = v;
			}
		}

		if (destination == null){
			throw new agentHasNoMoveException(this);
		}
		
		if (this.hasEscort() && distances.get(destination.getNumber()) == 0){
			// we can move to terrorists! DESTROY THEM!
			for (SyrianEdge e : graph.getEdgesWithTerrorists()){
				if (e.hasVertex(this.getLocation())){
					return e;
				}
			}
		}
		
		// backtrack to the vertex we want to move to
		while (previous.get(destination.getNumber()) != this.getLocation().getNumber())
			destination = graph.getVertexByNumber(previous.get(destination.getNumber()));
		
		SyrianEdge cheapest = null;
		for (SyrianEdge e : graph.getEdgesFor(this.getLocation(), destination)){
			if (cheapest == null || (e.getWeight() < cheapest.getWeight()) || (e.getWeight() == cheapest.getWeight() && e.getNumber() < cheapest.getNumber())){
				cheapest = e;
			}
		}
		
		throw new agentHasNoMoveException(this);
	}

}
