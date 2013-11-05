package agents;

import java.util.AbstractCollection;
import java.util.Scanner;

import exceptions.VertexNotPartOfEdgeException;

import syriangraph.SyrianEdge;
import syriangraph.SyrianGraph;
import syriangraph.SyrianVertex;

public class HumanSyrianAgent extends BaseSyrianAgent {

	private Scanner sc = new Scanner(System.in);

	@Override
	public SyrianEdge getNextMove(SyrianGraph graph, SyrianVertex location) {

		// take chemicals if you want
		if (location.hasChemicals()) {
			System.out.println("Do you want to take chemicals? (y/n)");
			String selection = sc.next();
			while (!selection.equalsIgnoreCase("y")
					&& !selection.equalsIgnoreCase("n")) {
				System.out
						.println("Please insert Y for taking chemicals or N for not taking them.");
				selection = sc.next();
			}

			if (selection.equalsIgnoreCase("Y")) {
				this.setChemicals(true);
			}
		}

		// ask for military if you want
		if (location.hasMilitary()) {
			System.out.println("Do you want to take military? (y/n)");
			String selection = sc.next();
			while (!selection.equalsIgnoreCase("y")
					&& !selection.equalsIgnoreCase("n")) {
				System.out
						.println("Please insert Y for taking military or N for not taking military.");
				selection = sc.next();
			}

			if (selection.equalsIgnoreCase("Y")) {
				this.setMilitary(true);
			}
		}

		System.out.println("Select a number to go to destination:");
		AbstractCollection<SyrianEdge> optsCollection = location.getEdges();
		SyrianEdge opts[] = new SyrianEdge[optsCollection.size()];
		optsCollection.toArray(opts);

		// AbstractCollection<SyrianVertex> neighboursCollection =
		// location.getNeighbours();
		// SyrianVertex neighbours[] = new
		// SyrianVertex[neighboursCollection.size()];
		// neighboursCollection.toArray(neighbours);
		for (int opt = 0; opt < opts.length; opt++) {
			SyrianEdge current = opts[opt];
			int num = current.getNumber();
			SyrianVertex v1 = current.getV1();
			SyrianVertex v2 = current.getV2();
			long weight = current.getWeight();
			boolean blocked = current.isBlocked();
			System.out.println("Enter " + opt + " to go through " + num + "[W:"
					+ weight + " - " + (blocked ? "BLOCKED!" : "") + "] to "
					+ (v1 == location ? v2.getNumber() : v1.getNumber()));
		}
		int sel = -999;
		while ((sel = sc.nextInt()) > opts.length && sc.nextInt() < 0) {
			System.out.println("Please select something within range!");
		}

		return opts[sel];
	}

}