package syriangraph;

import graph.BaseEdge;
import graph.Blockable;

//public class SyrianEdge implements Edge{

public class SyrianEdge extends BaseEdge<SyrianVertex, SyrianEdge> implements Blockable{

	private boolean blocked;
	public SyrianEdge(SyrianVertex v1, SyrianVertex v2, long weight, boolean blocked) {
		super(v1, v2, weight);
		this.blocked = blocked;
	}

	public SyrianEdge(SyrianVertex v1, SyrianVertex v2, long weight) {
		super(v1, v2, weight);
		this.blocked = false;
	}
	
	public SyrianEdge(SyrianVertex v1, SyrianVertex v2) {
		super(v1, v2, 0);
		this.blocked = false;
	}


	@Override
	public boolean isBlocked() {
		return this.blocked;
	}

	@Override
	public void clear() throws EdgeNotBlockedException {
		if (!this.isBlocked()){
			throw new EdgeNotBlockedException("Edge "+this.toString()+" was not blocked while clearing was attempted");
		}
		this.blocked = false;
	}

	@Override
	public void setBlocked(boolean b) {
		this.blocked = b;
	}

	
}
