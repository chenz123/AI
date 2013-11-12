
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
}
