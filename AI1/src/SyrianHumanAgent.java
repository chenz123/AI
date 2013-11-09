import java.util.AbstractCollection;
import java.util.HashMap;
import java.util.Scanner;

public class SyrianHumanAgent extends SyrianAgent {

	private Scanner sc;

	public SyrianHumanAgent(String name) {
		super(name);
		sc = new Scanner(System.in);
	}

	@Override
	public SyrianEdge getMove(SyrianGraph graph) throws agentHasNoMoveException {

		// handle military
		if (this.hasEscort()){
			System.out.println("Enter 'D' to drop escort at this location or any other key to keep carrying them");
			String  selection = sc.next();
			if (selection.equalsIgnoreCase("d")){
				this.getLocation().setEscort(this.getLocation().getEscortCount()+1);
			}
		} else {
			System.out.println("Enter 'E' to move with escort or any other key to move without escort");
			String selection = sc.next();
			if (selection.equalsIgnoreCase("e")){
				try {
					this.takeEscort();
				} catch (AgentAlreadyHasEscortException e) {
					System.out.println("We're sorry, there was an error and it seems like you are already carrying chemicals");
				} catch (LocationDoesntHaveEscortException e) {
					System.out.println("We're sorry, there was an error and it seems like this location does not have any chemials in it");
				}
			}
		}
		
		// handle chemicals
		if (this.hasChemicals()){
			System.out.println("Enter 'D' to drop chemicals at this location or any other key to keep carrying them");
			String  selection = sc.next();
			if (selection.equalsIgnoreCase("d")){
				this.getLocation().setChemicals(this.getLocation().getChemicalCount()+1);
			}
		} else {
			System.out.println("Enter 'C' to move with chemicals or any other key to move without chemicals");
			String selection = sc.next();
			if (selection.equalsIgnoreCase("c")){
				try {
					this.takeChemicals();
				} catch (AgentAlreadyHasChemicalsException e) {
					System.out.println("We're sorry, there was an error and it seems like you are already carrying chemicals");
				} catch (LocationDoesntHaveChemicalsException e) {
					System.out.println("We're sorry, there was an error and it seems like this location does not have any chemials in it");
				}
			}
		}
		
		AbstractCollection<SyrianEdge> paths = graph.getAllEdgesForVertex(this
				.getLocation());

		HashMap<Integer, SyrianEdge> options = new HashMap<Integer, SyrianEdge>();

		if (!paths.isEmpty()) {
			System.out.println("Insert path number to go to it's destination");
			for (SyrianEdge e : paths) {
				SyrianVertex destination = e.getOther(this.getLocation());
				// add option
				System.out.println("Added option "+e.getNumber());
				options.put(e.getNumber(), e);
				System.out
						.format("Go through path number %d (W:%d, T:%s) to destination %d(E:%s, C:%s)",
								e.getNumber(), e.getWeight(),
								e.hasTerrorists() ? "YES" : "NO", destination
										.getNumber(),
								destination.hasEscort() ? "YES" : "NO",
								destination.hasChemicals() ? "YES" : "NO");
				System.out.println();

			}
		}
		System.out.println("Any other key - No op");
		SyrianEdge selection = null;
		try {
			selection = options.get(Integer.parseInt(sc.next()));
			if (selection != null) {
				//System.out.println(this.getName() + " selected option "+selection.getNumber());
				return selection;
			}
		} catch (NumberFormatException nfe) {
			throw new agentHasNoMoveException(this);
		}
		throw new agentHasNoMoveException(this);
	}
	
	public boolean hasMovesLeft(SyrianGraph g){
		System.out.println("Simulate another turn? ('Y' - yes, anything else - no)");
		if (sc.next().equalsIgnoreCase("y")){
			return true;
		}
		
		return false;
	}


}
