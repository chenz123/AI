package syriangraph;

import graph.BaseVertex;

// GENERICS LOL
public class SyrianVertex extends BaseVertex<SyrianEdge, SyrianVertex>{
	
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
