import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.Comparator;
import java.util.HashMap;


public interface Graph<V extends Vertex, E extends Edge<V>> {

	public E addEdge(E e) throws EdgeAlreadyInGraphException;
	
	public V addVertex(V v) throws VertexAlreadyInGraphException;
	
	public AbstractCollection<E> getAllEdgesForVertex(V v);
	//public AbstractList<E> getAllEdgesForVertexSorted(V v, Comparator<E> c);
	
	public V addVertex();
	
	public AbstractCollection<E> getEdges();
	public AbstractCollection<V> getVertices();
	public V getVertexByNumber(int number);
	
	public HashMap<String, HashMap<Integer,Integer>> shortestPathsForEdges(V src, AbstractCollection<E> edges);
	
	public AbstractCollection<E> getEdgesFor(V v1, V v2);
	
}
