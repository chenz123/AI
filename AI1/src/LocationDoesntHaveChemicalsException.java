public class LocationDoesntHaveChemicalsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocationDoesntHaveChemicalsException(Agent<?,?,?> a){
		super("Location "+a.getLocation()+" doesn't have any chemicals! Agent "+a.getName()+" tried to take them anyway!");
	}
}
