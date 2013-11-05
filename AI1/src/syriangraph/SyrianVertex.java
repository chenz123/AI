package syriangraph;

import graph.BaseVertex;

public class SyrianVertex extends BaseVertex<SyrianVertex, SyrianEdge>{
	
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
	
	public SyrianVertex(int num) {
		super(num);
	}

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
