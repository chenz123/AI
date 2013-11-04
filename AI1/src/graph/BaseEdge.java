package graph;

import exceptions.VertexNotPartOfEdgeException;

public class BaseEdge<V extends Vertex<E, V>, E extends Edge<V, E>> implements Edge<V, E>{

	private int number;
	private long weight;
	private String name;
	private V v1, v2;
	
	public BaseEdge(int number){
		this.setNumber(number);
	}
	
	@Override
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setV1(V v1) {
		this.v1 = v1;
	}

	@Override
	public void setV2(V v2) {
		this.v2 = v2;
	}

	@Override
	public int getNumber() {
		return this.number;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public V getV1() {
		return this.v1;
	}

	@Override
	public V getV2() {
		return this.v2;
	}

	@Override
	public V otherVertex(V t) throws VertexNotPartOfEdgeException {
		if (this.v1 != t){
			if (this.v2 != t){
				throw new VertexNotPartOfEdgeException("Vertex " + t.getNumber() + " is not part of edge " + this.getNumber());
			}
			return this.v1;
		}
		
		return this.v2;
	}

	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public long getWeight() {
		return this.weight;
	}

}
