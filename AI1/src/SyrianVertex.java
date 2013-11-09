
public class SyrianVertex extends BaseVertex{

	private int escort, chemicals;

	public boolean hasEscort() {
		return this.escort > 0;
	}
	
	public boolean hasChemicals() {
		return this.chemicals > 0;
	}

	public void addEscort(int howMany){
		this.escort += howMany;
	}
	
	public void addChemicals(int howMany){
		this.chemicals += howMany;
	}

	public int getChemicalCount() {
		return this.chemicals;
	}

	public int getEscortCount() {
		return this.escort;
	}

	public void setChemicals(int i) {
		this.chemicals = i;
	}

	public void setEscort(int i) {
		this.escort = i;
		
	}
}
