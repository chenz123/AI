package agents;

import exceptions.NoMoreMovesException;
import exceptions.VertexHasNoChemicalsException;
import exceptions.VertexHasNoMilitaryException;
import exceptions.VertexNotPartOfEdgeException;
import syriangraph.SyrianEdge;
import syriangraph.SyrianGraph;
import syriangraph.SyrianVertex;

public interface SyrianAgent extends Agent<SyrianGraph<SyrianVertex, SyrianEdge>, SyrianVertex, SyrianEdge>{

	public long drive(SyrianEdge e, boolean takeEscort, boolean carryChemicals) throws VertexNotPartOfEdgeException, NoMoreMovesException, VertexHasNoMilitaryException, VertexHasNoChemicalsException;
}
