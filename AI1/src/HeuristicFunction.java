
public interface HeuristicFunction<VTN extends VTreeNode<V, E>, V extends Vertex, E extends Edge<V>> {

	public int getHeuristicEstimate(VTN v);
	
}
