package graph;

import exceptions.VertexNotPartOfEdgeException;

public class BaseEdge<V extends Vertex<E, V>, E extends Edge<V, E>> implements
		Edge<V, E> {

	private int number;
	private long weight;
	private String name;
	private V v1, v2;
	private static int ids = 1;

	public BaseEdge(V v1, V v2) {
		this.v1 = v1;
		this.v2 = v2;
		this.setNumber(BaseEdge.ids++);
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

	public boolean hasVertex(V v) {
		return this.v1 == v ? true : this.v2 == v;
	}

	@Override
	public V otherVertex(V v) throws VertexNotPartOfEdgeException {
		if (!this.hasVertex(v)) {
			throw new VertexNotPartOfEdgeException("Vertex " + v.getNumber()
					+ " is not connected to edge " + this.getNumber());
		}
		return this.v1 == v ? this.v2 : this.v1;
	}

	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public long getWeight() {
		return this.weight;
	}

	public String toString() {
		return "E[" + this.v1.getNumber() + "->" + this.v2.getNumber() + "]";
	}

}
