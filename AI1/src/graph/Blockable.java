package graph;

import syriangraph.EdgeNotBlockedException;

public interface Blockable {

	public boolean isBlocked();
	public void clear() throws EdgeNotBlockedException;
}
