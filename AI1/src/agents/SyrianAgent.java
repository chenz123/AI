package agents;

import syriangraph.SyrianEdge;
import syriangraph.SyrianGraph;
import syriangraph.SyrianVertex;

public interface SyrianAgent<G extends SyrianGraph, V extends SyrianVertex, E extends SyrianEdge>
		extends Agent<SyrianGraph, SyrianVertex, SyrianEdge> {

	public void setChemicals(boolean b);
	public void setMilitary(boolean b);
	public boolean getChmicals();
	public boolean getMilitary();
}
