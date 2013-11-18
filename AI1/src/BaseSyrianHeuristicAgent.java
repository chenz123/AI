import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;


import java.util.Iterator;
@SuppressWarnings("unused")


public abstract class BaseSyrianHeuristicAgent extends SyrianAgent implements
		SyrianHeuristicAgent<SyrianGraph, SyrianVertex, SyrianEdge> {

	public static HeuristicNodeComparator hnc = new HeuristicNodeComparator();
	protected HashMap<Integer, Integer> distancesFromTarget;

	public BaseSyrianHeuristicAgent(String name, SyrianVertex location,
			SyrianVertex target, SyrianGraph g) {
		super(name, location, target);
		this.distancesFromTarget = g.shortestPathsForEdges(this.getTarget(),
				g.getEdges()).get("distances");

	}

	
	
	public void addNewHeuristicNode(SyrianVertex destination,
			HeuristicNode hn, SyrianEdge e, boolean b, boolean c, int hnv,
			AbstractList<HeuristicNode> toBeExpanded,
			AbstractList<HeuristicNode> alreadyExpanded) {
		HeuristicNode candidate = new HeuristicNode(destination, hn, e, b, c,
				hnv);
//		for (HeuristicNode node : toBeExpanded) {
//			if (node.equals(candidate)) {
//				return;
//			}
//		}
		for (HeuristicNode node : alreadyExpanded) {
			if (node.equals(candidate)) {
				return;
			}
		}
		aiutils.Utils.addToSortedList(toBeExpanded, candidate,
				BaseSyrianHeuristicAgent.hnc);
	}

	@Override
	public int getPerformance() {
		return this.getScore() * SyrianHeuristicAgent.PERFORMANCE_FACTOR + this.getExpansions();
	}



	@Override
	public void setPerformance() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public int getExpansions() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void setExpansions() {
		// TODO Auto-generated method stub
		
	}

}
