
public class NoAgentsInSimulationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoAgentsInSimulationException(){
		super("No agents left in simulation!");
	}
}
