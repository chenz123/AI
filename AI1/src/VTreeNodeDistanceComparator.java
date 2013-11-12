import java.util.Comparator;

public class VTreeNodeDistanceComparator<T extends VTreeNode<V, E>, V extends Vertex, E extends Edge<V>> implements Comparator<T>{

	@Override
	public int compare(T o1, T o2) {
		return o1.getDistance() - o2.getDistance();
	}
	
}
