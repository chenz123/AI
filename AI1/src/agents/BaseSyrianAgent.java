package agents;

import exceptions.NoMoreMovesException;
import exceptions.VertexHasNoChemicalsException;
import exceptions.VertexHasNoMilitaryException;
import exceptions.VertexNotPartOfEdgeException;
import syriangraph.SyrianEdge;
import syriangraph.SyrianGraph;
import syriangraph.SyrianVertex;

public abstract class BaseSyrianAgent<G extends SyrianGraph<V, E>, V extends SyrianVertex<E, V>, E extends SyrianEdge<V, E>>
		extends
		BaseAgent<G, V, E>
		implements SyrianAgent<G, V, E> {

	public static int MILITARY_ESCORT_PENALTY_FACTOR = 2;
	public static int CHEMICALS_CARRY_PENALTY_FACTOR = 2;

	public BaseSyrianAgent(G graph,
			V start) {
		super(graph, start);
	}

	public long drive(E e, boolean takeEscort, boolean carryChemicals)
			throws VertexNotPartOfEdgeException, NoMoreMovesException,
			VertexHasNoMilitaryException, VertexHasNoChemicalsException {

		if (e == null) {
			throw new NoMoreMovesException("Agent has no moves to perform!");
		}

		// result modifier, increases when carrying chemicals
		// or taking military
		int factor = 1;

		// make sure taking escort is valid
		if (takeEscort) {
			if (this.getLocation().hasMilitary()) {
				factor *= MILITARY_ESCORT_PENALTY_FACTOR;
			} else {
				throw new VertexHasNoMilitaryException(
						"No military situated in vertex "
								+ this.getLocation().getNumber());
			}
		}

		// make sure carrying chemicals is valid
		if (carryChemicals) {
			if (this.getLocation().hasMilitary()) {
				factor *= CHEMICALS_CARRY_PENALTY_FACTOR;
			} else {
				throw new VertexHasNoChemicalsException(
						"No chemicals situated in vertex "
								+ this.getLocation().getNumber());
			}
		}

		// return penalty modified result considering agent's decisions
		return factor * super.drive(e);
	}

}