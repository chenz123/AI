import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;


import java.util.Iterator;
@SuppressWarnings("unused")


public class GreedySyrianHeuristicAgent extends BaseSyrianHeuristicAgent{

//	public static HeuristicNodeComparator hnc = new HeuristicNodeComparator();
//	private HashMap<Integer, Integer> distancesFromTarget;

	public GreedySyrianHeuristicAgent(String name, SyrianVertex location,
			SyrianVertex target, SyrianGraph g) {
		super(name, location, target, g);
//		this.distancesFromTarget = g.shortestPathsForEdges(this.getTarget(),
//				g.getEdges()).get("distances");

	}

	@Override
	public SyrianEdge getMove(SyrianGraph graph)
			throws AgentHasNoMoveException, AgentIsDoneException {
		
		if (this.getLocation() == this.getTarget() && graph.getVerticesWithChemicals().size() == 0){
			throw new AgentIsDoneException(this);
		}
		AbstractList<HeuristicNode> path = this
				.getPathToTargetWithChemicalsAllowReVisits(graph,
						this.getLocation());
		System.out.println("Path: ");
		for (HeuristicNode hn : path) {
			System.out.println(hn.toString());
		}
		HeuristicNode move = path.get(path.size() - 1);
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
		System.out.println("MOVE: " + move.toString());
		return move.getPath();
	}

	@Override
	public AbstractList<HeuristicNode> getPathToTargetWithChemicalsAllowReVisits(
			SyrianGraph graph, SyrianVertex source) {

		ArrayList<HeuristicNode> toBeExpanded, alreadyExpanded;
		// create node expansion list
		// AbstractList<HeuristicNode> cheapest = new
		// ArrayList<HeuristicNode>();
		toBeExpanded = new ArrayList<HeuristicNode>();
		alreadyExpanded = new ArrayList<HeuristicNode>();

		// // get total distance to chemicals * 2:
		int totalDistanceToChemicalsTimesTwo = 0;
		for (SyrianVertex v : graph.getVerticesWithChemicals()) {
			totalDistanceToChemicalsTimesTwo += this.distancesFromTarget.get(v
					.getNumber()) * 2;
		}
		
		toBeExpanded.add(new HeuristicNode(this.getLocation(), null, null, this
				.hasChemicals(), this.hasEscort(),
				totalDistanceToChemicalsTimesTwo));
		HeuristicNode toExpand = toBeExpanded.remove(0);
		// this.expandedHNs = new ArrayList<HeuristicNode>();
		while (!(toExpand.getRoot() == this.getTarget() && toExpand
				.hasChemicals())) {
			// sleep to control infinite loops
			try {
				Thread.currentThread();
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Now expanding a state of vertex "
					+ toExpand.getRoot().getNumber());
			this.expand(graph, toExpand, toBeExpanded, alreadyExpanded);
			// for (SyrianEdge e :
			// graph.getAllEdgesForVertex(toExpand.getRoot())) {
			// //SyrianVertex newVertex = e.getOther(toExpand.getRoot());
			// this.expand(graph, toExpand);
			// }
			alreadyExpanded.add(toExpand);
			toExpand = toBeExpanded.remove(0);

		}

		// backtrack to beginning of route:
		AbstractList<HeuristicNode> path = new ArrayList<HeuristicNode>();
		while (toExpand != null) {
			path.add(toExpand);
			toExpand = toExpand.getParent();
		}
		// remove first step which is a dummy to location
		path.remove(path.size() - 1);
		return path;
	}

	private void expand(SyrianGraph g, HeuristicNode hn,
			AbstractList<HeuristicNode> toBeExpanded,
			AbstractList<HeuristicNode> alreadyExpanded) {
		// trivial expansion
		AbstractCollection<SyrianEdge> edges = g.getAllEdgesForVertex(hn
				.getRoot());
		for (SyrianEdge e : edges) {
			SyrianVertex destination = e.getOther(hn.getRoot());
			int sourceDistanceFromTarget = this.distancesFromTarget.get(hn
					.getRoot().getNumber());
			int destinationDistanceFromTarget = this.distancesFromTarget
					.get(destination.getNumber());
			this.addNewHeuristicNode(destination, hn, e, false, false,
					hn.getHn(), toBeExpanded, alreadyExpanded);
			if (hn.getRoot().hasChemicals() || hn.hasChemicals()) {
				// expand with chemicals
				this.addNewHeuristicNode(
						destination,
						hn,
						e,
						true,
						false,
						hn.getHn()
								- ((sourceDistanceFromTarget - destinationDistanceFromTarget) * 2),
						toBeExpanded, alreadyExpanded);
			}
			if (hn.getRoot().hasEscort() || hn.hasEscort()) {
				// expand with escort
				this.addNewHeuristicNode(destination, hn, e, false, true,
						hn.getHn(), toBeExpanded, alreadyExpanded);
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
								- ((sourceDistanceFromTarget - destinationDistanceFromTarget) * 2),
						toBeExpanded, alreadyExpanded);
			}
		}
	}
}