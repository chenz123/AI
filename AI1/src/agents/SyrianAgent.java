package agents;

import exceptions.NoMoreMovesException;
import exceptions.VertexHasNoChemicalsException;
import exceptions.VertexHasNoMilitaryException;
import exceptions.VertexNotPartOfEdgeException;
import syriangraph.SyrianEdge;
import syriangraph.SyrianGraph;
import syriangraph.SyrianVertex;

public interface SyrianAgent<G extends SyrianGraph, V extends SyrianVertex, E extends SyrianEdge>
		extends Agent<SyrianGraph, SyrianVertex, SyrianEdge> {

	public long drive(E e, boolean takeEscort, boolean carryChemicals)
			throws VertexNotPartOfEdgeException, NoMoreMovesException,
			VertexHasNoMilitaryException, VertexHasNoChemicalsException;

	public void decide();

}
