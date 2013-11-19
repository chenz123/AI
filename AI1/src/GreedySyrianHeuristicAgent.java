import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;


import java.util.Iterator;
@SuppressWarnings("unused")


public class GreedySyrianHeuristicAgent extends BaseSyrianHeuristicAgent<SimpleHeuristicNode>{

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
		AbstractList<SimpleHeuristicNode> path = this
				.getPathToTargetWithChemicalsAllowReVisits(graph,
						this.getLocation());
		System.out.println("Path: ");
		for (SimpleHeuristicNode hn : path) {
			System.out.println(hn.toString());
		}
		SimpleHeuristicNode move = path.get(path.size() - 1);
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
	public AbstractList<SimpleHeuristicNode> getPathToTargetWithChemicalsAllowReVisits(
			SyrianGraph graph, SyrianVertex source) {

		ArrayList<SimpleHeuristicNode> toBeExpanded, alreadyExpanded;
		// create node expansion list
		// AbstractList<HeuristicNode> cheapest = new
		// ArrayList<HeuristicNode>();
		toBeExpanded = new ArrayList<SimpleHeuristicNode>();
		alreadyExpanded = new ArrayList<SimpleHeuristicNode>();

		// // get total distance to chemicals * 2:
		int totalDistanceToChemicalsTimesTwo = 0;
		for (SyrianVertex v : graph.getVerticesWithChemicals()) {
			totalDistanceToChemicalsTimesTwo += this.distancesFromTarget.get(v
					.getNumber()) * 2;
		}
		
		toBeExpanded.add(new SimpleHeuristicNode(this.getLocation(), null, null, this
				.hasChemicals(), this.hasEscort(),
				totalDistanceToChemicalsTimesTwo));
		SimpleHeuristicNode toExpand = toBeExpanded.remove(0);
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
		AbstractList<SimpleHeuristicNode> path = new ArrayList<SimpleHeuristicNode>();
		while (toExpand != null) {
			path.add(toExpand);
			toExpand = toExpand.getParent();
		}
		// remove first step which is a dummy to location
		path.remove(path.size() - 1);
		return path;
	}

	
	public void addNewHeuristicNode(SyrianVertex destination,
			SimpleHeuristicNode hn, SyrianEdge e, boolean b, boolean c, int hnv,
			AbstractList<SimpleHeuristicNode> toBeExpanded,
			AbstractList<SimpleHeuristicNode> alreadyExpanded) {
		SimpleHeuristicNode candidate = new SimpleHeuristicNode(destination, hn, e, b, c,
				hnv);
//		for (HeuristicNode node : toBeExpanded) {
//			if (node.equals(candidate)) {
//				return;
//			}
//		}
		for (SimpleHeuristicNode node : alreadyExpanded) {
			if (node.equals(candidate)) {
				return;
			}
		}
		aiutils.Utils.addToSortedList(toBeExpanded, candidate,
				super.hnc);
	}
	
	private void expand(SyrianGraph g, SimpleHeuristicNode hn,
			AbstractList<SimpleHeuristicNode> toBeExpanded,
			AbstractList<SimpleHeuristicNode> alreadyExpanded) {
		this.setExpansions(this.getExpansions() + 1);
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