
public class SyrianExpandedNode<V extends SyrianVertex> implements ExpandedNode<V> {

	private V root;
	
	public SyrianExpandedNode(V root){
		this.root = root;
	}
	@Override
	public V getRoot() {
		// TODO Auto-generated method stub
		return this.root;
	}

}
