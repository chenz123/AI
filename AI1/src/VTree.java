import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;

public class VTree<G extends Graph<V, E>, V extends Vertex, E extends Edge<V>> {

	private VTreeNode<V, E> root;
	private AbstractList<VTreeNode<V, E>> expandable;
	private AbstractList<V> alreadyVisited;
	private VTreeNodeDistanceComparator<VTreeNode<V, E>, V, E> vtndc;
	//private EdgeDistanceComparator<E> edc;
	private G graph;
	public VTree(V source, G graph) {
		
		this.root = new VTreeNode<V, E>(source, 0, null, null);
		this.graph = graph;
		this.expandable = new ArrayList<VTreeNode<V, E>>();
		this.alreadyVisited = new ArrayList<V>();		
		
		// this comparator was made to keep the list ordered
		//this.edc = new EdgeDistanceComparator<>();
		this.vtndc = new VTreeNodeDistanceComparator<>();
		// add the root to the nodes-to-be-expanded list in sorted order
		aiutils.Utils.addToSortedList(this.expandable, this.root, this.vtndc);
		this.expandable.add(
				Collections.binarySearch(expandable, root, vtndc) + 1,
				this.root);
	}

	public AbstractList<E> getPathTo(V destination){
		
		VTreeNode<V, E> toBeExpanded = this.expandable.remove(0);
		while (!(toBeExpanded.getVertex() == destination)) {	
			System.out.println("Expanding "+toBeExpanded.getVertex().getNumber());
			// expand another node
			AbstractCollection<E> childrenPaths = this.graph.getAllEdgesForVertex(toBeExpanded.getVertex());
			for (E e: childrenPaths){
				// create target vertex object
				if (!this.alreadyVisited.contains(e.getOther(toBeExpanded.getVertex()))){
				VTreeNode<V, E> child = new VTreeNode<V, E>(e.getOther(toBeExpanded.getVertex()), toBeExpanded.getDistance() + e.getWeight(), e, toBeExpanded);
				System.out.println("Adding "+child.getVertex().getNumber()+" to expansion");
				aiutils.Utils.addToSortedList(this.expandable, child, this.vtndc);
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// make sure never to epand this again
			this.alreadyVisited.add(toBeExpanded.getVertex());
			
			// make sure not to take old scheduled expansions that
			// are no longer relevant
			while (alreadyVisited.contains(toBeExpanded.getVertex()))
				toBeExpanded = this.expandable.remove(0);
		}
		
		// got to destination! now create path
		return createPathTo(toBeExpanded);
	}
	
	public AbstractList<E> createPathTo(VTreeNode<V, E> vt){
		// done backtracking
		if (vt.getVertex() == this.root.getVertex()){
			// we've backtracked all the way to source,
			// create a list and add paths on recursion's return
			return new ArrayList<E>();
		}
		
		AbstractList<E> pathToHere = createPathTo(vt.getParent());
		
		pathToHere.add(vt.getPath());
		
		return pathToHere;
		
	}
	// private void expand(){
	// this.expand(nexts.remove(0));
	// }

	// public
}