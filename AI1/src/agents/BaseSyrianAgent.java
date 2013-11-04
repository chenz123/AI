package agents;

import exceptions.NoMoreMovesException;
import exceptions.VertexHasNoMilitaryException;
import exceptions.VertexNotPartOfEdgeException;
import syriangraph.SyrianEdge;
import syriangraph.SyrianGraph;
import syriangraph.SyrianVertex;

public class BaseSyrianAgent extends BaseAgent<SyrianGraph<SyrianVertex, SyrianEdge>, SyrianVertex, SyrianEdge> {

	public static int MILITARY_ESCORT_PENALTY_FACTOR = 2;
	public static int CHEMICALS_CARRY_PENALTY_FACTOR = 2;
	
	private boolean takeEscort, carryChemicals;
	
	public BaseSyrianAgent(SyrianGraph<SyrianVertex, SyrianEdge> graph,
			SyrianVertex start) {
		super(graph, start);
	}
	
	public long drive(SyrianEdge e, boolean takeEscort, boolean carryChemicals) throws VertexNotPartOfEdgeException, NoMoreMovesException {
		
		if (e == null){
			throw new NoMoreMovesException("Agent has no moves to perform!");
		}
		
		// result modifier, increases when carrying chemicals
		// or taking military
		int factor = 1;
		
		// make sure taking escort is valid
		if (takeEscort){
			if (this.getLocation().hasMilitary()){
				factor *= MILITARY_ESCORT_PENALTY_FACTOR;
			} else {
				throw new VertexHasNoMilitaryException("No military situated in vertex "+this.getLocation().getNumber());
			}
		}
		
		// make sure carrying chemicals is valid
		if (carryChemicals){
			factor = this.getLocation()
		}
		
		return factor * super.drive(e);
	}

}
