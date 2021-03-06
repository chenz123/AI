public abstract class SyrianAgent extends
		BaseAgent<SyrianGraph, SyrianVertex, SyrianEdge> {

	private boolean hasEscort, hasChemicals;

	/*
	 * public SyrianAgent(String name) { super(name); this.hasEscort = false;
	 * this.hasChemicals = false; }
	 */

	public SyrianAgent(String name, SyrianVertex location, SyrianVertex target) {
		super(name, location, target);
		this.hasEscort = false;
		this.hasChemicals = false;
	}

	public boolean hasEscort() {
		return this.hasEscort;
	}

	public boolean hasChemicals() {
		return this.hasChemicals;
	}

	public void takeChemicals() throws AgentAlreadyHasChemicalsException,
			LocationDoesntHaveChemicalsException {
		if (this.hasChemicals) {
			throw new AgentAlreadyHasChemicalsException(this);
		}
		if (!this.getLocation().hasChemicals()) {
			throw new LocationDoesntHaveChemicalsException(this);
		}
		this.getLocation().setChemicals(
				this.getLocation().getChemicalCount() - 1);
		this.hasChemicals = true;
	}

	public void takeEscort() throws AgentAlreadyHasEscortException,
			LocationDoesntHaveEscortException {
		if (this.hasEscort) {
			throw new AgentAlreadyHasEscortException(this);
		}
		if (!this.getLocation().hasEscort()) {
			throw new LocationDoesntHaveEscortException(this);
		}
		this.getLocation().setEscort(this.getLocation().getEscortCount() - 1);
		this.hasEscort = true;
	}

	public void dropEscort() throws AgentHasNoEscortException {
		if (!this.hasEscort) {
			throw new AgentHasNoEscortException(this);
		}

		this.getLocation().addEscort(1);
		this.hasEscort = false;
	}

	public void dropChemicals() throws AgentHasNoChemicalsException {
		if (!this.hasChemicals) {
			throw new AgentHasNoChemicalsException(this);
		}
		this.getLocation().addChemicals(1);
		this.hasChemicals = false;
	}

	public void move(SyrianGraph g) throws AgentHasNoMoveException,
			AgentIsDoneException {

		// evacuate all possible chemicals
		if (this.hasChemicals() && this.getLocation() == this.getTarget()) {
			System.out.println("Agent " + this.getName()
					+ " evacuated chemicals to it's target");
			this.setChemicalsEvacuated(this.getChemicalsEvacuated() + 1);
			// chemicals are now safe, erase them
			this.hasChemicals = false;
		}

		SyrianEdge path = this.getMove(g);
		System.out.println("Agent " + this.getName() + " is moving from "
				+ this.getLocation().getNumber() + " to "
				+ path.getOther(this.getLocation()).getNumber());
		SyrianVertex destination = path.getOther(this.getLocation());

		// calculate score
		int factor = 1;

		if (path.hasTerrorists()) {

			// entered hostile territory
			if (!this.hasEscort()) {
				if (this.hasChemicals()) {

					// major factor for going unescorted through
					// hostile territory!
					factor *= SyrianSimulation.CROSSING_TERRORISTS_WITH_CHEMICALS_PENALTY;
				} else {
					// driving unescorted through terrorists
					// this is OK according to mission description
					// TODO: ask

				}
			} else { // has escort
				if (this.hasChemicals()) {
					// TODO: ask if that's the right behaviour
					// driving escorted through edge
					// ** doesn't clear edge **
					// nothing happens (but regular side-effects)
				} else {
					// clear path
					System.out.println("Agent " + this.getName()
							+ " busted some terrorists!");
					this.setTerroristsBusted(this.getTerroristsBusted() + 1);
					path.clearTerrorists();
				}
			}

		}

		// multipliers
		factor *= this.hasChemicals() ? 2 : 1;
		factor *= this.hasEscort() ? 2 : 1;

		// regular side effects of traversing
		int totalCost = factor * path.getWeight();

		this.addScore(totalCost);

		// move agent
		this.setLocation(destination);
		System.out.println("After moving, agent " + this.getName()
				+ "'s score is: " + this.getScore());
	}
	
}
