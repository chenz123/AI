
public class AgentAlreadyHasChemicalsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AgentAlreadyHasChemicalsException(Agent<?,?,?> a){
		super("Agent "+a.getName()+" already has chemicals!");
	}

}
