
public abstract class SyrianAgent extends BaseAgent<SyrianGraph, SyrianVertex, SyrianEdge>{

	private boolean hasEscort, hasChemicals;
	
	public SyrianAgent(String name) {
		super(name);
		this.hasEscort = false;
		this.hasChemicals = false;
	}

	public boolean hasEscort() {
		return this.hasEscort;
	}

	public boolean hasChemicals(){
		return this.hasChemicals;
	}
	
	public void takeChemicals() throws AgentAlreadyHasChemicalsException, LocationDoesntHaveChemicalsException{
		if (this.hasChemicals){
			throw new AgentAlreadyHasChemicalsException(this);
		}
		if (!this.getLocation().hasChemicals()){
			throw new LocationDoesntHaveChemicalsException(this);
		}
			this.getLocation().setChemicals(this.getLocation().getChemicalCount() - 1);
			this.hasChemicals = true;
	}
	
	public void takeEscort() throws AgentAlreadyHasEscortException, LocationDoesntHaveEscortException{
		if (this.hasEscort){
			throw new AgentAlreadyHasEscortException(this);
		}
		if (!this.getLocation().hasChemicals()){
			throw new LocationDoesntHaveEscortException(this);
		}
			this.getLocation().setEscort(this.getLocation().getEscortCount() - 1);
			this.hasEscort = true;
	}
}
