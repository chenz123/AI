import java.util.AbstractCollection;


public interface Graph<V extends Vertex, E extends Edge<V>> {

	public E addEdge(E e) throws EdgeAlreadyInGraphException;
	
	public V addVertex(V v) throws VertexAlreadyInGraphException;
	
	public AbstractCollection<E> getAllEdgesForVertex(V v);
	
	public V addVertex();
	
	public AbstractCollection<E> getEdges();
	public AbstractCollection<V> getVertices();
	public V getVertexByNumber(int number);
}
