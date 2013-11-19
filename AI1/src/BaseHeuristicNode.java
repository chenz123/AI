
public class BaseHeuristicNode<H extends BaseHeuristicNode<H>> implements HeuristicNode<H>{
	private SyrianVertex root;
	private H parent;
	private SyrianEdge path;
	private boolean hasChemicals, hasEscort;
	private int hn;

	// private int distanceFromTarget;

	public BaseHeuristicNode(SyrianVertex source, H parent,
			SyrianEdge path, boolean hasChemicals, boolean hasEscort, int hn) {
		this.setRoot(source);
		this.setParent(parent);
		this.setPath(path);
		this.setHasChemicals(hasChemicals);
		this.setHasEscort(hasEscort);
		this.hn = hn;
	}

	public SyrianVertex getRoot() {
		return root;
	}

	public void setRoot(SyrianVertex root) {
		this.root = root;
	}

	public H getParent() {
		return parent;
	}

	public void setParent(H parent) {
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

	public boolean equals(H other) {
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
