import java.util.Comparator;


public class HeuristicNodeComparator<H extends BaseHeuristicNode<?>> implements Comparator<H>{

	@Override
	public int compare(H o1, H o2) {
		// TODO Auto-generated method stub
		return o1.getHn() - o2.getHn();
	}

}
