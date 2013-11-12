

public final class VTreeNodeDistanceComparator<T extends VTreeNode<V, E>, V extends Vertex, E extends Edge<V>> extends VTreeNodeHeuristicComparator<VTreeNode<V,E>, V, E>{

	public VTreeNodeDistanceComparator(){
		super(new ZeroHeuristicFunction<V, E>());
	}
	private VTreeNodeDistanceComparator(
			HeuristicFunction<VTreeNode<V, E>, V, E> function) {
		super(function);
	}

}
