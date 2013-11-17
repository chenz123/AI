import java.util.AbstractList;


public interface SyrianHeuristicAgent<G extends SyrianGraph, V extends SyrianVertex, E extends SyrianEdge> {

//	public AbstractList<E> getPathToChemicals(G g, V v) throws AgentAlreadyHasChemicalsException;
//	
//	public AbstractList<E> getPathToTarget(G g, V v) throws AgentHasNoChemicalsException;

	AbstractList<HeuristicNode> getPathToTargetWithChemicalsAllowReVisits(
			SyrianGraph graph, SyrianVertex source);
}
