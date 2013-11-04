package agents;

import graph.Graph;

public interface Agent<T extends Graph<?, ?>> {

	public int drive(T g);
	
	public int noOp();
	
}
