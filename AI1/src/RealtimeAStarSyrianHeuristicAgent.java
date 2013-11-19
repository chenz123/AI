import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings("unused")
public class RealtimeAStarSyrianHeuristicAgent extends
		BaseSyrianHeuristicAgent<RealtimeHeuristicNode>
		implements
		SyrianHeuristicAgent<SyrianGraph, SyrianVertex, SyrianEdge, RealtimeHeuristicNode> {

	private int maxDepth;

	public RealtimeAStarSyrianHeuristicAgent(String name,
			SyrianVertex location, SyrianVertex target, SyrianGraph g,
			int maxDepth) {
		super(name, location, target, g);
		this.maxDepth = maxDepth;
	}

	@Override
	public SyrianEdge getMove(SyrianGraph graph)
			throws AgentHasNoMoveException, AgentIsDoneException {

		if (graph.getVerticesWithChemicals().size() == 0
				&& !this.hasChemicals()) {
			throw new AgentIsDoneException(this);
		}
		AbstractList<RealtimeHeuristicNode> path = this
				.getPathToTargetWithChemicalsAllowReVisits(graph,
						this.getLocation());
		System.out.println("Path: ");
		for (RealtimeHeuristicNode hn : path) {
			System.out.println(hn.toString());
		}

		RealtimeHeuristicNode move = path.get(path.size() - 1);
		// take chemicals if needed
		if (move.hasChemicals() && !this.hasChemicals()) {
			try {
				this.takeChemicals();
			} catch (AgentAlreadyHasChemicalsException e) {
				System.out.println("Error in path!");
				e.printStackTrace();
			} catch (LocationDoesntHaveChemicalsException e) {
				System.out.println("Error in path!");
				e.printStackTrace();
			}
		}

		// take military if needed
		if (move.hasEscort() && !this.hasEscort()) {

			try {
				this.takeEscort();
			} catch (AgentAlreadyHasEscortException e) {
				System.out.println("Error in path!");
				e.printStackTrace();
			} catch (LocationDoesntHaveEscortException e) {
				System.out.println("Error in path!");
				e.printStackTrace();
			}

		}
		// drop escort if needed
		if (this.hasEscort() && !move.hasEscort()) {
			try {
				this.dropEscort();
			} catch (AgentHasNoEscortException e) {
				System.out.println("Error in path!");
				e.printStackTrace();
			}
		}
		// drop chemicals if needed
		if (this.hasEscort() && !move.hasEscort()) {
			try {
				this.dropChemicals();
			} catch (AgentHasNoChemicalsException e) {
				System.out.println("Error in path!");
				e.printStackTrace();
			}
		}

		System.out.println("MOVE: " + move.toString());
		return move.getPath();
	}

	@Override
	public AbstractList<RealtimeHeuristicNode> getPathToTargetWithChemicalsAllowReVisits(
			SyrianGraph graph, SyrianVertex source) {

		ArrayList<RealtimeHeuristicNode> toBeExpanded, alreadyExpanded;
		// create node expansion list
		// AbstractList<HeuristicNode> cheapest = new
		// ArrayList<HeuristicNode>();
		toBeExpanded = new ArrayList<RealtimeHeuristicNode>();
		alreadyExpanded = new ArrayList<RealtimeHeuristicNode>();

		// // get total distance to chemicals * 2:
		// compute agent's distance
		int totalDistanceToChemicalsTimesTwo = this.hasChemicals() ? this.distancesFromTarget
				.get(this.getLocation().getNumber()) * 2 : 0;
		// compute other chemical's distances
		for (SyrianVertex v : graph.getVerticesWithChemicals()) {
			totalDistanceToChemicalsTimesTwo += this.distancesFromTarget.get(v
					.getNumber()) * 2;
		}

		toBeExpanded.add(new RealtimeHeuristicNode(this.getLocation(), null,
				null, this.hasChemicals(), this.hasEscort(),
				totalDistanceToChemicalsTimesTwo, 0));
		RealtimeHeuristicNode toExpand = toBeExpanded.remove(0);
		// this.expandedHNs = new ArrayList<RealtimeHeuristicNode>();
		while (toExpand != null && !(toExpand.getRoot() == this.getTarget() && toExpand
				.hasChemicals())) {
			// print current queue
			// Iterator<HeuristicNode> it = toBeExpanded.iterator();
			// System.out.println("Current list:");
			// while (it.hasNext()){
			// System.out.println(it.next().toString());
			// }
			// sleep to control infinite loops
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Now trying to expanding a state of vertex "
					+ toExpand.getRoot().getNumber());
			if (toExpand.getDepth() <= this.maxDepth) {
				// increase depth by one for all expanded nodes
				this.expand(graph, toExpand, toBeExpanded, alreadyExpanded,
						toExpand.getDepth() + 1);
			} else {
				
				System.out.println("Couldn't expand! max depth reached!");
			}
			// for (SyrianEdge e :
			// graph.getAllEdgesForVertex(toExpand.getRoot())) {
			// //SyrianVertex newVertex = e.getOther(toExpand.getRoot());
			// this.expand(graph, toExpand);
			// }
			alreadyExpanded.add(toExpand);
			if (!toBeExpanded.isEmpty()) {
				toExpand = toBeExpanded.remove(0);
			} else {
				toExpand = null;
			}

		}
		// didn't reach goal, select minimal from expanded
		if (toExpand == null){
			
			RealtimeHeuristicNode candidate = null;
			for (RealtimeHeuristicNode rhn : alreadyExpanded) {
				if (candidate == null
						|| candidate.getHn() > rhn.getHn()
						|| (candidate.getHn() == rhn.getHn() && candidate
								.getRoot().getNumber() > rhn.getRoot()
								.getNumber()) || candidate.getDepth() < rhn.getDepth()) {
					candidate = rhn;
				}
			}
			toExpand = candidate;
			System.out.println("Chose node: " + toExpand.toString());
		}

		// backtrack to beginning of route:
		AbstractList<RealtimeHeuristicNode> path = new ArrayList<RealtimeHeuristicNode>();
		while (toExpand != null) {
			path.add(toExpand);
			toExpand = toExpand.getParent();
		}
		// remove first step which is a dummy to location
		path.remove(path.size() - 1);
		return path;
	}

	public void addNewHeuristicNode(SyrianVertex destination,
			RealtimeHeuristicNode hn, SyrianEdge e, boolean b, boolean c,
			int hnv, AbstractList<RealtimeHeuristicNode> toBeExpanded,
			AbstractList<RealtimeHeuristicNode> alreadyExpanded, int depth) {
		RealtimeHeuristicNode candidate = new RealtimeHeuristicNode(
				destination, hn, e, b, c, hnv, depth);
		// for (HeuristicNode node : toBeExpanded) {
		// if (node.equals(candidate)) {
		// return;
		// }
		// }
		for (RealtimeHeuristicNode node : alreadyExpanded) {
			if (node.equals(candidate)) {
				return;
			}
		}
		aiutils.Utils.addToSortedList(toBeExpanded, candidate, super.hnc);
	}

	private void expand(SyrianGraph g, RealtimeHeuristicNode hn,
			AbstractList<RealtimeHeuristicNode> toBeExpanded,
			AbstractList<RealtimeHeuristicNode> alreadyExpanded, int depth) {

		this.setExpansions(this.getExpansions() + 1);

		AbstractCollection<SyrianEdge> edges = g.getAllEdgesForVertex(hn
				.getRoot());
		for (SyrianEdge e : edges) {
			SyrianVertex destination = e.getOther(hn.getRoot());
			int sourceDistanceFromTarget = this.distancesFromTarget.get(hn
					.getRoot().getNumber());
			int destinationDistanceFromTarget = this.distancesFromTarget
					.get(destination.getNumber());
			// trivial expansion
			this.addNewHeuristicNode(destination, hn, e, false, false,
					hn.getHn() + e.getWeight(), toBeExpanded, alreadyExpanded,
					depth);
			if (hn.getRoot().hasChemicals() || hn.hasChemicals()) {
				// expand with chemicals
				int chn = -1;
				this.addNewHeuristicNode(
						destination,
						hn,
						e,
						true,
						false,
						(chn = hn.getHn()
								// cost to move
								- ((sourceDistanceFromTarget - destinationDistanceFromTarget) * 2)
								+ (e.getWeight() * 2)
								* (e.hasTerrorists() ? SyrianSimulation.CROSSING_TERRORISTS_WITH_CHEMICALS_PENALTY
										: 1)), toBeExpanded, alreadyExpanded,
						depth);
				System.out.println("added option with hn: " + chn + " "
						+ hn.getRoot().getNumber() + "->"
						+ destination.getNumber());
			}
			if (hn.getRoot().hasEscort() || hn.hasEscort()) {
				// expand with escort
				this.addNewHeuristicNode(destination, hn, e, false, true,
						hn.getHn() + e.getWeight() * 2, toBeExpanded,
						alreadyExpanded, depth);
			}
			if ((hn.getRoot().hasEscort() || hn.hasEscort())
					&& (hn.getRoot().hasChemicals() || hn.hasChemicals())) {
				// expand with chemicals and escort
				this.addNewHeuristicNode(
						destination,
						hn,
						e,
						true,
						true,
						hn.getHn()
								- ((sourceDistanceFromTarget - destinationDistanceFromTarget) * 2)
								+ e.getWeight() * 4, toBeExpanded,
						alreadyExpanded, depth);
			}
		}
	}
}
