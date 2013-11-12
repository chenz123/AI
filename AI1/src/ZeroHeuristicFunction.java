
public class ZeroHeuristicFunction<V extends Vertex, E extends Edge<V>> implements
		HeuristicFunction<VTreeNode<V, E>, V, E> {

	@Override
	public int getHeuristicEstimate(VTreeNode<V, E> v) {
		return 0;
	}

}
