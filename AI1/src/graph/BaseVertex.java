package graph;

import java.util.AbstractCollection;

public class BaseVertex<T extends Edge<?>> implements Vertex<T>{

	private int number;
	private String name;
	private AbstractCollection<T> edges;
	
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
	public void setEdges(AbstractCollection<T> edges) {
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
	public AbstractCollection<T> getEdges() {
		return this.edges;
	}

	@Override
	public boolean addEdge(T e) {
		return this.edges.add(e);
	}

	@Override
	public boolean removeEdge(T e) {
		return this.edges.remove(e);
	}

}
