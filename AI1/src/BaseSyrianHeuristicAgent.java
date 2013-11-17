import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BaseSyrianHeuristicAgent extends SyrianAgent implements
		SyrianHeuristicAgent<SyrianGraph, SyrianVertex, SyrianEdge> {

	public static HeuristicNodeComparator hnc = new HeuristicNodeComparator();
	private HashMap<Integer, Integer> distancesFromTarget;

	public BaseSyrianHeuristicAgent(String name, SyrianVertex location,
			SyrianVertex target, SyrianGraph g) {
		super(name, location, target);
		this.distancesFromTarget = g.shortestPathsForEdges(this.getTarget(),
				g.getEdges()).get("distances");

	}

	@Override
	public SyrianEdge getMove(SyrianGraph graph)
			throws agentHasNoMoveException, AgentIsDoneException {
		// if (!this.hasChemicals()) {
		// // get to chemicals first.
		// // lack backtracking makes it impossible to
		// // merge path to chemicals and carriage to target
		//
		// try {
		// return this.getPathToChemicals(graph, this.getLocation()).get(0);
		// } catch (AgentAlreadyHasChemicalsException e) {
		// System.err.println("Sorry, agent tried to take chemicals while already holding them. Will do no-op instead.");
		// throw new agentHasNoMoveException(this);
		// }
		// }
		//
		// try {
		// return this.getPathToTargetWithChemicalsAllowReVisits(graph,
		// this.getLocation()).get(0);
		// } catch (AgentHasNoChemicalsException e) {
		// System.err.println("Sorry, agent tried to go to target without chemicals. Will do no-op instead.");
		// throw new agentHasNoMoveException(this);
		// }

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
		// // trivial case
		// System.out.println("Adding start case with no chemicals or escort");
		// cheapest.add(new HeuristicNode(this.getLocation(), null, null, false,
		// false, totalDistanceToChemicalsTimesTwo));
		// // with chemicals
		// if (this.hasChemicals() || this.getLocation().hasChemicals()) {
		// System.out.println("Adding start case with chemicals");
		// cheapest.add(new HeuristicNode(this.getLocation(), null, null,
		// true, false, totalDistanceToChemicalsTimesTwo));
		// }
		// // with escort
		// if (this.hasEscort() || this.getLocation().hasEscort()) {
		// System.out.println("Adding start case with escort");
		// cheapest.add(new HeuristicNode(this.getLocation(), null, null,
		// false, true, totalDistanceToChemicalsTimesTwo));
		// }
		// // with both
		// if ((this.hasChemicals() || this.getLocation().hasChemicals())
		// && (this.hasEscort() || this.getLocation().hasEscort())) {
		// System.out.println("Adding start case with chemicals and escort");
		// cheapest.add(new HeuristicNode(this.getLocation(), null, null,
		// true, true, totalDistanceToChemicalsTimesTwo));
		// }
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

	// @Override
	// public AbstractList<SyrianEdge> getPathToChemicals(SyrianGraph graph,
	// SyrianVertex source)
	// throws AgentAlreadyHasChemicalsException {
	// if (this.hasChemicals()) {
	// throw new AgentAlreadyHasChemicalsException(this);
	// }
	//
	// // get all nodes with chemicals for this step
	// AbstractCollection<SyrianVertex> verticesWithChemicals =
	// graph.getVerticesWithChemicals();
	//
	// Queue<SyrianExpandedNode<SyrianVertex>> cheapestList = new
	// LinkedList<SyrianExpandedNode<SyrianVertex>>();
	// // add start location to expand list
	// cheapestList.add(new
	// SyrianExpandedNode<SyrianVertex>(this.getLocation()));
	//
	// // start expanding
	// SyrianExpandedNode<SyrianVertex> toExpand = cheapestList.remove();
	// while (!verticesWithChemicals.contains(toExpand.getRoot())){
	// // get all possibilities for this edge
	// AbstractCollection<SyrianEdge> edges =
	// graph.getAllEdgesForVertex(toExpand.getRoot());
	// // calculate cost for each edge - THIS IS CONSTANT IN THIS CASE, so we
	// expand
	// // pretty much randomly
	// for (SyrianEdge e : edges){
	// cheapestList.add(new
	// SyrianExpandedNode<SyrianVertex>(e.getOther(toExpand.getRoot()),
	// e.getWeight()));
	// }
	// toExpand = cheapestList.remove();
	// }
	//
	// // chemical destination should now be in "toExpand", if any was found
	// while
	//
	//
	// return null;
	// }
	//
	// @Override
	// public AbstractList<SyrianEdge> getPathToTarget(SyrianGraph graph,
	// SyrianVertex source)
	// throws AgentHasNoChemicalsException {
	// if (!this.hasChemicals()) {
	//
	// throw new AgentHasNoChemicalsException(this);
	// }
	// // TODO Auto-generated method stub
	// return null;
	// }
	//

	// TODO: change "distancefromtarget" to match the heuristic!!!!
	// this is where "the magic happens" and the heuristic is modified
	// private void addAllPossibilities(AbstractList<HeuristicNode> cheaps,
	// SyrianVertex root, HeuristicNode parent, SyrianEdge path,
	// boolean canHaveChemicals, boolean canHaveEscort) {
	// System.out.println("Attempt to add new states for vertex "
	// + root.getNumber() + " parent: "
	// + (parent != null ? parent.getRoot().getNumber() : parent)
	// + " path: " + (path != null ? path.getNumber() : path)
	// + " chemicals: " + canHaveChemicals + " escort: "
	// + canHaveEscort);
	// int parentDistanceFromTarget = this.distancesFromTarget.get(parent
	// .getRoot().getNumber());
	// int thisDistanceFromTarget = this.distancesFromTarget.get(root
	// .getNumber());
	//
	// AbstractCollection<HeuristicNode> candidates = new
	// ArrayList<HeuristicNode>();
	// // trivial case
	// candidates.add(new HeuristicNode(root, parent, path, false, false,
	// parent.getHn()));
	// // move with chemicals only (don't move through terrorist infested
	// // nodes!)
	// if (path != null && canHaveChemicals && !path.hasTerrorists()) {
	// candidates
	// .add(new HeuristicNode(
	// root,
	// parent,
	// path,
	// true,
	// false,
	// parent.getHn()
	// - ((parentDistanceFromTarget - thisDistanceFromTarget) * 2)));
	// System.out.println("Will attempt to add states with chemicals:");
	// for (HeuristicNode candidate : candidates)
	// System.out.println(candidate.toString());
	// }
	// // move with escort
	// if (canHaveEscort) {
	// candidates.add(new HeuristicNode(root, parent, path, false, true,
	// parent.getHn()));
	// System.out.println("Will attempt to add states with escort:");
	// for (HeuristicNode candidate : candidates)
	// System.out.println(candidate.toString());
	// }
	// // move with chemicals and escort
	// if (canHaveChemicals && canHaveEscort) {
	// candidates
	// .add(new HeuristicNode(
	// root,
	// parent,
	// path,
	// true,
	// true,
	// parent.getHn()
	// - ((parentDistanceFromTarget - thisDistanceFromTarget) * 2)));
	// System.out
	// .println("Will attempt to add states with chemicals AND escort:");
	// for (HeuristicNode candidate : candidates)
	// System.out.println(candidate.toString());
	// }
	// // removes states that were already expanded or are already in line
	// for (HeuristicNode runner : cheaps) {
	// Iterator<HeuristicNode> it = candidates.iterator();
	// while (it.hasNext()) {
	// HeuristicNode candidate = it.next();
	// // compare using "equals" to make sure state
	// // for this vertex doesn't already exist
	// if (candidate.equals(runner)) {
	// it.remove();
	// }
	// }
	// }
	// for (HeuristicNode runner : this.expandedHNs) {
	// Iterator<HeuristicNode> it = candidates.iterator();
	// while (it.hasNext()) {
	// HeuristicNode candidate = it.next();
	// // compare using "equals" to make sure state
	// // for this vertex doesn't already exist
	// if (candidate.equals(runner)) {
	// it.remove();
	// }
	// }
	// }
	//
	// System.out.println("Will add " + candidates.size() + " new states: ");
	// // insert all survivors
	// for (HeuristicNode candidate : candidates) {
	// System.out.println(candidate.toString());
	// aiutils.Utils.addToSortedList(cheaps, candidate,
	// BaseSyrianHeuristicAgent.hnc);
	// }
	// }

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

	private void addNewHeuristicNode(SyrianVertex destination,
			HeuristicNode hn, SyrianEdge e, boolean b, boolean c, int hnv,
			AbstractList<HeuristicNode> toBeExpanded,
			AbstractList<HeuristicNode> alreadyExpanded) {
		HeuristicNode candidate = new HeuristicNode(destination, hn, e, b, c,
				hnv);
		for (HeuristicNode node : toBeExpanded) {
			if (node.equals(candidate)) {
				return;
			}
		}
		for (HeuristicNode node : alreadyExpanded) {
			if (node.equals(candidate)) {
				return;
			}
		}
		aiutils.Utils.addToSortedList(toBeExpanded, candidate,
				BaseSyrianHeuristicAgent.hnc);
	}

}
