import java.util.AbstractCollection;
import java.util.HashMap;

public class SyrianTerroristBusterAgent extends SyrianAgent {

	public SyrianTerroristBusterAgent(String name, SyrianVertex location,
			SyrianVertex target) {
		super(name, location, target);
	}

	@Override
	public SyrianEdge getMove(SyrianGraph graph)
			throws AgentHasNoMoveException, AgentIsDoneException {
		
		System.out.println("location: " + this.getLocation().getNumber());
		HashMap<String, HashMap<Integer, Integer>> shortestPaths = graph
				.shortestPathsForEdges(this.getLocation(), graph.getEdges());
		System.out.println("Edges count: "+graph.getEdges().size());
		HashMap<Integer, Integer> distances = shortestPaths.get("distances");
		HashMap<Integer, Integer> previous = shortestPaths.get("previous");
		System.out.println("prevs: " + previous.toString());
		System.out.println("distances: " + distances.toString());

		SyrianVertex destination = null;

		if (graph.getEdgesWithTerrorists().size() == 0 /*|| (graph.getVerticesWithEscort().size() == 0)*/) {
			if (this.getTarget() == this.getLocation()) {
				throw new AgentIsDoneException(this);
			}
			// we're done busting terrorists, go to target...
			destination = this.getTarget();

			// backtrack to the vertex we want to move to
			System.out.println("Moving from "+this.getLocation().getNumber()+" to "+this.getTarget().getNumber() + " prev: "+previous.toString());
			while (previous.get(destination.getNumber()) != this.getLocation()
					.getNumber())
				destination = graph.getVertexByNumber(previous.get(destination
						.getNumber()));

			// get cheapest route to target (could be several edges)
			SyrianEdge cheapest = null;
			for (SyrianEdge e : graph.getEdgesFor(this.getLocation(),
					destination)) {
				if (cheapest == null
						|| (e.getWeight() < cheapest.getWeight())
						|| (e.getWeight() == cheapest.getWeight() && e
								.getNumber() < cheapest.getNumber())) {
					cheapest = e;
				}
			}
			
			return cheapest;
		}
		System.out.println("1111");
		// first pick up escort if needed, simplifies things
		if (!this.hasEscort() && this.getLocation().hasEscort()) {
			try {
				this.takeEscort();
			} catch (AgentAlreadyHasEscortException e) {
				System.out
						.println("Critical error in terrorist buster, tried to take escort when already has escort!");
				e.printStackTrace();
			} catch (LocationDoesntHaveEscortException e) {
				System.out
						.println("Critical error in terrorist buster, tried to take escort when no escort available!");
				e.printStackTrace();
			}
		}

		System.out.println("2222");
		// our options vary according to whether or not we have escort with us
		AbstractCollection<SyrianVertex> options = this.hasEscort() ? graph
				.getVerticesAdjacentToTerrorists() : graph
				.getVerticesWithEscort();

		for (SyrianVertex v : options) {
			if (destination == null
					|| (distances.get(v.getNumber()) < distances
							.get(destination.getNumber()) || (distances.get(v
							.getNumber()) == distances.get(destination
							.getNumber()) && v.getNumber() < destination
							.getNumber()))) {
				destination = v;
			}
		}
		System.out.println("Buster going to: "+destination);
		if (destination == null) {
			throw new AgentHasNoMoveException(this);
		}

		System.out.println("3333");
		if (this.hasEscort() && distances.get(destination.getNumber()) == 0) {
			// we can move to terrorists! DESTROY THEM!
			for (SyrianEdge e : graph.getEdgesWithTerrorists()) {
				if (e.hasVertex(this.getLocation())) {
					return e;
				}
			}
		}

		// backtrack to the vertex we want to move to
		while (previous.get(destination.getNumber()) != this.getLocation()
				.getNumber()){
			destination = graph.getVertexByNumber(previous.get(destination
					.getNumber()));
		}
		
		System.out.println("Destination: "+destination.getNumber() + " location: " +this.getLocation().getNumber());
		SyrianEdge cheapest = null;
		for (SyrianEdge e : graph.getEdgesFor(this.getLocation(), destination)) {
			if (cheapest == null
					|| (e.getWeight() < cheapest.getWeight())
					|| (e.getWeight() == cheapest.getWeight() && e.getNumber() < cheapest
							.getNumber())) {
				cheapest = e;
			}
		}

		System.out.println("4444 "+graph.getEdgesFor(this.getLocation(), destination).size());
		if (cheapest != null){
			return cheapest;
		}
		throw new AgentHasNoMoveException(this);
	}

}
