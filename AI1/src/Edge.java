/**
 * This class represents an edge on a graph
 * it is used to create a path between two vertices
 * @author Reut Sharabani & Chen Zrubavel
 *
 * @param <V> - the Vertex this edge is based on
 */
public interface Edge<V extends Vertex> {

	/**
	 * get first vertex on this node
	 * @return
	 * 			first vertex on this node
	 */
	public V getV1();
	
	/**
	 * get second vertex on this node
	 * @return
	 * 			second vertex on this node
	 */
	public V getV2();
	
	/**
	 * get edge's weight
	 * @return
	 * 			edge's weight
	 */
	public int getWeight();
	
	/**
	 * set an edges weight
	 * @param weight - weight to set edge to
	 */
	public void setWeight(int weight);
	
	/**
	 * get the other vertex on this edge
	 * @param v - the vertex we DON'T want.
	 * @return
	 * 			the other vertex. *** if not on edge will return vertex 2 ***
	 */
	public V getOther(V v);
	
	/**
	 * get edge's number
	 * @return
	 * 			the edge's number
	 */
	public int getNumber();
	
	/**
	 * check to see if a vertex has a certain edge
	 * @param v - the vertex to check if exists ont he edge
	 * @return
	 * 			true - if vertex exists on the edge
	 * 			false - otherwise
	 */
	public boolean hasVertex(V v);
	
	/**
	 * set this edge's color
	 * @param color
	 * 			a color to set the edge to (e.g. "red", "green", "blue")
	 */
	public void setColor(String color);
	
	/**
	 * get this edge's color
	 * @return
	 * 			this edge's color
	 */
	public String getColor();

	/**
	 * check if this edge has a color
	 * @return
	 * 			true - if this edge's color is set
	 * 			false - otherwise
	 */
	public boolean hasColor();
}
