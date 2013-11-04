package syriangraph;

import graph.BaseVertex;

public class SyrianVertex<E extends SyrianEdge<V, E>, V extends SyrianVertex<E, V>> extends BaseVertex<V, E>{
	
	private boolean hasMilitary, hasChemicals, isGoal;
	
	public SyrianVertex(int number, boolean hasChemicals, boolean hasMilitary, boolean isGoal){
		super(number);
		this.hasChemicals = hasChemicals;
		this.hasMilitary = hasMilitary;
		this.isGoal = isGoal;
	}

	/*
	 * Syrian-specific attributes, methods etc.
	 */
	
	public boolean hasChemicals(){
		return this.hasChemicals;
	}
	
	public boolean hasMilitary(){
		return this.hasMilitary;
	}
	
	public boolean isGoal(){
		return this.isGoal;
	}
}
