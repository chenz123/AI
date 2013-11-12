import java.util.Comparator;

public class VTreeNodeHeuristicComparator<T extends VTreeNode<V, E>, V extends Vertex, E extends Edge<V>> implements Comparator<T>{

	private HeuristicFunction<T, V, E> function;
	public VTreeNodeHeuristicComparator(HeuristicFunction<T, V, E> function){
		this.function = function;
	}
	
	@Override
	public int compare(T o1, T o2) {
		return function.getHeuristicEstimate(o1) - function.getHeuristicEstimate(o2);
	}
	
}
