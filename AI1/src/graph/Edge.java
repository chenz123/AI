package graph;

import exceptions.VertexNotPartOfEdgeException;


public interface Edge<V extends Vertex<E,V>, E extends Edge<V,E>> {
	
	public void setNumber(int number);
	
	public void setName(String name);
	
	public void setWeight(int weight);
	
	public void setV1(V v1);
	
	public void setV2(V v2);
	
	public long getWeight();
	
	public int getNumber();
	
	public String getName();
	
	public V getV1();
	
	public V getV2();
	
	public V otherVertex(V t) throws VertexNotPartOfEdgeException;
}
