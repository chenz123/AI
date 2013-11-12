public class VTreeNode<V extends Vertex, E extends Edge<V>> {

	private V v;
	private int distance;
	private E path;
	private VTreeNode<V, E> parent;
	public VTreeNode(V v, int distance, E path, VTreeNode<V, E> parent) {
		this.v = v;
		this.distance = distance;
		this.path = path;
		this.parent = parent;
	}

	public int getDistance() {
		return this.distance;
	}
	
	public V getVertex(){
		return this.v;
	}
	
	public E getPath(){
		return this.path;
	}
//
//	public V getParent() {
//		return this.getPath().getOther(this.getVertex());
//	}

	public VTreeNode<V, E> getParent() {
		// TODO Auto-generated method stub
		return this.parent;
	}
}
