import java.util.AbstractList;


public interface SyrianHeuristicAgent<G extends SyrianGraph, V extends SyrianVertex, E extends SyrianEdge> {

//	public AbstractList<E> getPathToChemicals(G g, V v) throws AgentAlreadyHasChemicalsException;
//	
//	public AbstractList<E> getPathToTarget(G g, V v) throws AgentHasNoChemicalsException;

	public int PERFORMANCE_FACTOR = 1;
	AbstractList<HeuristicNode> getPathToTargetWithChemicalsAllowReVisits(
			SyrianGraph graph, SyrianVertex source);
	
	public int getPerformance();
	public void setPerformance();
	
	public int getExpansions();
	public void setExpansions();

}
