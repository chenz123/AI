package graph;

import java.util.AbstractCollection;
import java.util.ArrayList;

import exceptions.VertexNotPartOfEdgeException;

public class BaseVertex<V extends Vertex<E, V>, E extends Edge<V, E>>
		implements Vertex<E, V> {

	private int number;
	private String name;
	private AbstractCollection<E> edges;
	private static int ids = 1;

	public BaseVertex() {
		System.out.println("Creating vertex " + BaseVertex.ids);
		System.out.flush();
		this.setNumber(BaseVertex.ids++);
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
	public void setEdges(AbstractCollection<E> edges) {
		this.edges = edges;
	}

	@Override
	public int getNumber() {
		return this.number;
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public AbstractCollection<E> getEdges() {
		return this.edges;
	}

	@Override
	public boolean addEdge(E e) {
		return this.edges.add(e);
	}

	@Override
	public boolean removeEdge(E e) {
		return this.edges.remove(e);
	}

	// @SuppressWarnings("unchecked")
	@Override
	public AbstractCollection<V> getNeighbours()
			throws VertexNotPartOfEdgeException {

		AbstractCollection<V> res = new ArrayList<V>();

		for (E e : this.getEdges()) {
			if (e.getV1() == this) {
				if (e.getV2() == this) {
					res.add(e.getV2());
				} else {
					// something went wrong, vertex doesn't have this edge!
					throw new VertexNotPartOfEdgeException("Vertex "
							+ this.getNumber() + " is not part of edge "
							+ e.getNumber());
				}
			} else {
				res.add(e.getV1());
			}
		}

		return res;
	}

	@Override
	public E hasEdgeTo(V v) {
		for (E e : this.getEdges()) {
			if (e.hasVertex(v))
				return e;
		}
		return null;
	}

}
