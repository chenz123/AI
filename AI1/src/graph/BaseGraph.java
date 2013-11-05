package graph;

import java.util.AbstractCollection;
import java.util.ArrayList;

import syriangraph.EdgeAlreadyExistsException;
import syriangraph.SyrianEdge;
import syriangraph.SyrianVertex;

import exceptions.VertexAlreadyExistsException;

public abstract class BaseGraph<V extends Vertex<E, V>, E extends Edge<V, E>>
		implements Graph<V, E> {

	private AbstractCollection<V> vertices;
	private AbstractCollection<E> edges;

	public BaseGraph() {
		this.vertices = new ArrayList<V>();
		this.edges = new ArrayList<E>();
	}

	public BaseGraph(AbstractCollection<V> vertices, AbstractCollection<E> edges) {
		this.edges = edges;
		this.vertices = vertices;
	}

	public AbstractCollection<V> getVertices() {
		return this.vertices;
	}

	public AbstractCollection<E> getEdges() {
		return this.edges;
	}

	public void setEdges(AbstractCollection<E> edges) {
		this.edges = edges;
	}

	public void setVertices(AbstractCollection<V> vertices) {
		this.vertices = vertices;
	}

	@Override
	public V getVertexByNumber(int num) {
		for (V v : this.getVertices()) {
			if (v.getNumber() == num) {
				return v;
			}
		}
		return null;
	}

	@Override
	public V getVertexByNumber(int num, boolean b)
			throws VertexAlreadyExistsException {
		for (V v : this.getVertices()) {
			if (v.getNumber() == num) {
				return v;
			}
		}
		// create node if needed
		return b ? this.addVertex() : null;
	}

	public E getEdgeByVertices(V v1, V v2) {
		for (E e : this.getEdges()) {
			if (e.hasVertex(v1) && e.hasVertex(v2)) {
				return e;
			}
		}
		return null;
	}

}
