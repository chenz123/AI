import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;


import java.util.Iterator;
@SuppressWarnings("unused")


public abstract class BaseSyrianHeuristicAgent<H extends BaseHeuristicNode<?>> extends SyrianAgent implements
		SyrianHeuristicAgent<SyrianGraph, SyrianVertex, SyrianEdge, H> {

	public HeuristicNodeComparator<H> hnc = new HeuristicNodeComparator<H>();
	protected HashMap<Integer, Integer> distancesFromTarget;
	private int expansions = 0;

	public BaseSyrianHeuristicAgent(String name, SyrianVertex location,
			SyrianVertex target, SyrianGraph g) {
		super(name, location, target);
		this.distancesFromTarget = g.shortestPathsForEdges(this.getTarget(),
				g.getEdges()).get("distances");

	}

	
//	
//	public void addNewHeuristicNode(SyrianVertex destination,
//			HeuristicNode hn, SyrianEdge e, boolean b, boolean c, int hnv,
//			AbstractList<HeuristicNode> toBeExpanded,
//			AbstractList<HeuristicNode> alreadyExpanded) {
//		HeuristicNode candidate = new HeuristicNode(destination, hn, e, b, c,
//				hnv);
////		for (HeuristicNode node : toBeExpanded) {
////			if (node.equals(candidate)) {
////				return;
////			}
////		}
//		for (HeuristicNode node : alreadyExpanded) {
//			if (node.equals(candidate)) {
//				return;
//			}
//		}
//		aiutils.Utils.addToSortedList(toBeExpanded, candidate,
//				BaseSyrianHeuristicAgent.hnc);
//	}

	@Override
	public int getPerformance() {
		return this.getScore() * SyrianHeuristicAgent.PERFORMANCE_FACTOR + this.getExpansions();
	}

	@Override
	public int getExpansions() {
		return this.expansions;
	}



	@Override
	public void setExpansions(int expansions) {
		this.expansions  = expansions;
	}

	public String toString(){
		String res = super.toString();
		String sep = System.getProperty("line.separator");
		res += "Expansions: " + this.getExpansions() + sep;
		res += "Performace: " + this.getPerformance() + sep;
		return res;
	}
}
