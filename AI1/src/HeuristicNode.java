public class HeuristicNode {

	private SyrianVertex root;
	private HeuristicNode parent;
	private SyrianEdge path;
	private boolean hasChemicals, hasEscort;
	private int hn;

	// private int distanceFromTarget;

	public HeuristicNode(SyrianVertex source, HeuristicNode parent,
			SyrianEdge path, boolean hasChemicals, boolean hasEscort, int hn) {
		this.setRoot(source);
		this.setParent(parent);
		this.setPath(path);
		this.setHasChemicals(hasChemicals);
		this.setHasEscort(hasEscort);
		// this.distanceFromTarget = distanceFromTarget;
		// add or subtract chemical distance from target if needed
		// otherwise nothing happened.

		this.hn = hn;
		// hasChemicals ? parent.getHn()
		// + ((parent.getDistanceFromTarget() - this
		// .getDistanceFromTarget()) * 2) : parent.getHn();
	}

	//
	// private int getDistanceFromTarget() {
	// return this.distanceFromTarget;
	// }

	public SyrianVertex getRoot() {
		return root;
	}

	public void setRoot(SyrianVertex root) {
		this.root = root;
	}

	public HeuristicNode getParent() {
		return parent;
	}

	public void setParent(HeuristicNode parent) {
		this.parent = parent;
	}

	public SyrianEdge getPath() {
		return path;
	}

	public void setPath(SyrianEdge path) {
		this.path = path;
	}

	public boolean hasChemicals() {
		return hasChemicals;
	}

	public void setHasChemicals(boolean agentHasChemicals) {
		this.hasChemicals = agentHasChemicals;
	}

	public boolean hasEscort() {
		return hasEscort;
	}

	public void setHasEscort(boolean agentHasEscort) {
		this.hasEscort = agentHasEscort;
	}

	public int getHn() {
		return this.hn;
	}

	public void setHn(int hn) {
		this.hn = hn;
	}

	public boolean equals(HeuristicNode other) {
		// compare base node and state
		return /*this.getParent() == other.getParent()
				&&*/ this.getRoot() == other.getRoot()
				&& this.hasChemicals() == other.hasChemicals()
				&& this.hasEscort() == other.hasEscort();
	}

	public String toString() {
		return "HN for vertex #"
				+ this.root.getNumber()
				+ " parent: "
				+ (this.getParent() == null ? this.parent : this.parent
						.getRoot().getNumber()) + " hn: " + this.getHn()
				+ " chemicals: " + this.hasChemicals() + " escort: "
				+ this.hasEscort();
	}
}
