
public class AgentHasNoChemicalsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgentHasNoChemicalsException(Agent<?, ?, ?> a){
		super("Agent "+a.getName() + " has no chemicals!");
	}
}
