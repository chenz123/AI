import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashMap;

public class SyrianGreedyAgent extends SyrianAgent {

	public SyrianGreedyAgent(String name, SyrianVertex location,
			SyrianVertex target) {
		super(name, location, target);
	}

	@Override
	public SyrianEdge getMove(SyrianGraph graph)
			throws AgentHasNoMoveException, AgentIsDoneException {

		HashMap<String, HashMap<Integer, Integer>> shortestPaths = this
				.hasChemicals() ? graph.shortestPathsForEdges(
				this.getLocation(), graph.getUnblockedEdges()) : graph
				.shortestPathsForEdges(this.getLocation(), graph.getEdges());
		HashMap<Integer, Integer> distances = shortestPaths.get("distances");
		HashMap<Integer, Integer> previous = shortestPaths.get("previous");
		System.out.println("prevs: " + previous.toString());
		System.out.println("distances: " + distances.toString());

		SyrianVertex destination = null;

		if (graph.getVerticesWithChemicals().size() == 0) {
			if (this.getTarget() == this.getLocation()) {
				throw new AgentIsDoneException(this);
			}
			// we're done evacuating chemicals, go to target...
			destination = this.getTarget();

			// backtrack to the vertex we want to move to
			System.out.println("Moving from " + this.getLocation().getNumber()
					+ " to " + this.getTarget().getNumber() + " prev: "
					+ previous.toString());
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

		// first pick up chemicals if needed, simplifies things
		if (!this.hasChemicals() && this.getLocation().hasChemicals()) {
			try {
				this.takeChemicals();
			} catch (AgentAlreadyHasChemicalsException e1) {
				System.out
						.println("Critical error in greedy agent, tried to take chemicals when already having them!");
				e1.printStackTrace();
			} catch (LocationDoesntHaveChemicalsException e1) {
				System.out
						.println("Critical error in greedy agent, tried to take chemicals when no chemicals are at location!");
				e1.printStackTrace();
			}
		}

		// our options vary according to whether or not we have chemicals with
		// us
		// create target as list for "options"
		AbstractCollection<SyrianVertex> targetList = new ArrayList<SyrianVertex>();
		targetList.add(this.getTarget());

		AbstractCollection<SyrianVertex> options = this.hasChemicals() ? targetList
				: graph.getVerticesWithChemicals();

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
		System.out.println("Greedy agent going to: " + destination);
		if (destination == null) {
			throw new AgentHasNoMoveException(this);
		}

		// if (this.hasChemicals() && distances.get(destination.getNumber()) ==
		// 0) {
		// // we can move to terrorists
		// for (SyrianEdge e : graph.getEdgesWithTerrorists()) {
		// if (e.hasVertex(this.getLocation())) {
		// return e;
		// }
		// }
		// }

		// backtrack to the vertex we want to move to
		try {
		while (previous.get(destination.getNumber()) != this.getLocation()
				.getNumber())
			destination = graph.getVertexByNumber(previous.get(destination
					.getNumber()));
		} catch (NullPointerException npe){
			throw new AgentHasNoMoveException(this);
		}
		SyrianEdge cheapest = null;
		for (SyrianEdge e : (this.hasChemicals() ? graph.getClearEdgesFor(
				this.getLocation(), destination) : graph.getEdgesFor(
				this.getLocation(), destination))) {
			if (cheapest == null
					|| (e.getWeight() < cheapest.getWeight())
					|| (e.getWeight() == cheapest.getWeight() && e.getNumber() < cheapest
							.getNumber())) {
				cheapest = e;
			}
		}
		if (cheapest != null) {
			return cheapest;
		}
		throw new AgentHasNoMoveException(this);
	}

}
