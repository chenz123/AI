package graph;

import java.util.AbstractCollection;
import java.util.ArrayList;

import exceptions.VertexNotPartOfEdgeException;

public class BaseVertex<V extends Vertex<E, V>, E extends Edge<V, E>> implements Vertex<E, V>{

	private int number;
	private String name;
	private AbstractCollection<E> edges;
	
	public BaseVertex(int number){
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

	@SuppressWarnings("unchecked")
	@Override
	public AbstractCollection<V> getNeighbours() throws VertexNotPartOfEdgeException {
		
		AbstractCollection<V> res = new ArrayList<V>();
		
		for (E e: this.getEdges()){
			res.add(e.otherVertex((V) this));
		}
		
		return res;
	}
	

}
