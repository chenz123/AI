import java.util.AbstractCollection;
import java.util.HashMap;


public interface Graph<V extends Vertex, E extends Edge<V>> {

	/**
	 * add an edge to the graph
	 * @param e - the edge to add to the graph
	 * @return
	 * 			the edge
	 * @throws EdgeAlreadyInGraphException
	 * 			if edge already exists on the graph
	 */
	public E addEdge(E e) throws EdgeAlreadyInGraphException;
	
	/**
	 * add a vertex to the graph
	 * @param v - the vertex to add to the graph
	 * @return
	 * 			the vertex
	 * @throws VertexAlreadyInGraphException
	 * 			if the vertex already exists in the graph
	 */
	public V addVertex(V v) throws VertexAlreadyInGraphException;
	
	/**
	 * get all edges connected to a certain vertex
	 * @param v - the vertex who's edges we want
	 * @return
	 * 			a collection of the edges connected to this vertex
	 */
	public AbstractCollection<E> getAllEdgesForVertex(V v);
	//public AbstractList<E> getAllEdgesForVertexSorted(V v, Comparator<E> c);

	/**
	 * add a vertex to the graph
	 * @return
	 * 			the vertex
	 */
	/*
	public V addVertex();
	*/
	
	/**
	 * get all edges on the graph
	 * @return
	 * 			a collection of all edges on the graph
	 */
	public AbstractCollection<E> getEdges();
	
	/**
	 * get all vertices on graph
	 * @return
	 * 			a collection of all vertices on graph
	 */
	public AbstractCollection<V> getVertices();

	/**
	 * get a vertex by it's number
	 * @param number - vertex's number
	 * @return
	 * 			the vertex corresponding to the number
	 * 			null - otherwise
	 */
	public V getVertexByNumber(int number);
	
	/**
	 * get shortest path based on a certain
	 * collection of edges (e.g. unblocked edges)
	 * @param src - the source vertex
	 * @param edges - a collection of edges the path
	 * 				   is allowed to go through
	 * @return
	 * 			a HashMap containing two Hashmaps
	 * 			on key: distances
	 * 				the ditances to all vertices
	 * 				(each vertex distance has the
	 * 				vertex's number as it's key)
	 * 			on key: previous
	 * 				the previous vertex in order to
	 * 				get to a vertex fastest (each vertex
	 * 				has the vertex number as it's key)
	 * 
	 * 				example:
	 * 
	 * 				on key "distances" of result is the HashMap:
	 * 				{"distances"-> 1=2 3=null 4=3}
	 * 				meaning the distance to 1 is 2,
	 * 				3 is the source, etc...
	 * 
	 * 				on key "previous" of result is HashMap:
	 * 				{"previous"-> 1=3 3=null 4=1}
	 * 				meaning the previous vertex in order
	 * 				to get to 4 is 1, to 1 is 3 (the source)
	 * 				and to 3, which is the source, is null (already there)
	 */
	public HashMap<String, HashMap<Integer,Integer>> shortestPathsForEdges(V src, AbstractCollection<E> edges);
	
	/**
	 * get all edges connecting v1 to v2
	 * @param v1 - a vertex on the edge
	 * @param v2 - the other vertex on the edge
	 * @return
	 * 			a collection of all edges
	 * 			connecting the two vertices
	 */
	public AbstractCollection<E> getEdgesFor(V v1, V v2);
	
}
