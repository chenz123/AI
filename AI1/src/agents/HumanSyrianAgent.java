package agents;

import java.util.Scanner;

import exceptions.VertexHasNoChemicalsException;
import exceptions.VertexHasNoMilitaryException;
import exceptions.VertexNotPartOfEdgeException;
import syriangraph.SyrianEdge;
import syriangraph.SyrianGraph;
import syriangraph.SyrianVertex;

public class HumanSyrianAgent
		extends BaseSyrianAgent {

	public HumanSyrianAgent(SyrianGraph graph, SyrianVertex start) {
		super(graph, start);
	}

	private Scanner sc = new Scanner(System.in);
	// stores user selection to take escort or chemicals
	private boolean military, chemicals;


	@Override
	public void decide() {
		int selection = -1;

		System.out.println("Choose an action number:");
		System.out.println("1. Move");
		System.out.println("2. Set chemicals");
		System.out.println("3. Set escort");
		System.out.println("*. Done");

		switch (selection) {
		case 1:
			try {
				this.chooseDestination();
			} catch (VertexNotPartOfEdgeException e) {
				// this shouldn't happen lol
				System.out.println("Sorry for this error, but it "
						+ "appears you can't drive there!"
						+ " This is definitely the coders' bug!");
			}
			break;
		case 2:
			try {
				this.setChemicals();
			} catch (VertexHasNoChemicalsException vhnce) {
				System.out.println(vhnce.getMessage());
			}
			break;
		case 3:
			try {
				this.setEscort();
			} catch (VertexHasNoMilitaryException vhnme) {
				System.out.println(vhnme.getMessage());
			}
			decide();
			break;

		}

	}

	private void setEscort() throws VertexHasNoMilitaryException {
		if (!this.getLocation().hasMilitary()) {
			throw new VertexHasNoMilitaryException("This vertex("
					+ this.getLocation().getNumber() + ") has no military!");
		}
		if (this.military) {
			System.out.println("1. Don't ask for escort");
		} else {
			System.out.println("1. Ask for escort");
		}
		System.out.println("*. Back");
		int selection = sc.nextInt();

		switch (selection) {
		case 1:
			this.military = !this.military;
			break;
		}
	}

	private void setChemicals() throws VertexHasNoChemicalsException {
		if (!this.getLocation().hasChemicals()) {
			throw new VertexHasNoChemicalsException("This vertex("
					+ this.getLocation().getNumber() + ") has no chemicals!");
		}
		if (this.chemicals) {
			System.out.println("1. Don't take chemicals");
		} else {
			System.out.println("1. Take chemicals");
		}
		System.out.println("*. Back");
		int selection = sc.nextInt();

		switch (selection) {
		case 1:
			this.chemicals = !this.chemicals;
			break;
		}
	}

	private void chooseDestination() throws VertexNotPartOfEdgeException {
		int selectionStart = 1;
		for (SyrianVertex v : this.getLocation().getNeighbours()) {
			System.out.println(selectionStart + ". Move to vertex "
					+ v.getNumber() + "("
					+ (v.hasMilitary() ? "HAS MILITARY " : " ")
					+ (v.hasChemicals() ? "HAS CHEMICALS" : "")
					+ (v.hasChemicals() ? "-BLOCKED" : "") + ")");
		}
	}
}