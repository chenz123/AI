import java.util.Comparator;


public class HeuristicNodeComparator implements Comparator<HeuristicNode>{

	@Override
	public int compare(HeuristicNode o1, HeuristicNode o2) {
		// TODO Auto-generated method stub
		return o1.getHn() - o2.getHn();
	}

}
