public class LocationDoesntHaveEscortException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocationDoesntHaveEscortException(Agent<?, ?, ?> a) {
		super("Location " + a.getLocation().getNumber()
				+ " doesn't have escort! Agent " + a.getName()
				+ " tried to take some anyway!");
	}
}
