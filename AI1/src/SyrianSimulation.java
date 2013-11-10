import java.io.IOException;

import aiutils.Utils;

public class SyrianSimulation extends
		BaseSimulation<SyrianGraph, SyrianAgent, SyrianVertex, SyrianEdge> {

	public SyrianSimulation(SyrianGraph g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void moveAgent(SyrianAgent a) throws agentHasNoMoveException, AgentIsDoneException {
		a.move(this.getGraph());
		/*
		SyrianEdge path = a.getMove(this.getGraph());
		
		System.out.println("Agent " + a.getName() + " is moving from "
				+ a.getLocation().getNumber() + " to "
				+ path.getOther(a.getLocation()).getNumber());
		SyrianVertex destination = path.getOther(a.getLocation());

		// calculate score
		int factor = 1;

		if (path.hasTerrorists()) {

			// entered hostile territory
			if (!a.hasEscort()) {
				if (a.hasChemicals()) {

					// major factor for going unescorted through
					// hostile territory!
					factor *= 1000;
				} else {
					// driving unescorted through terrorists
					// this is OK according to mission description
					// TODO: ask

				}
			} else { // has escort
				if (a.hasChemicals()) {
					// TODO: ask if that's the right behaviour
					// driving escorted through edge
					// ** doesn't clear edge **
					// nothing happens (but regular side-effects)
				} else {
					// clear path
					path.clearTerrorists();
				}
			}

		}

		// multipliers
		factor *= a.hasChemicals() ? 2 : 1;
		factor *= a.hasEscort() ? 2 : 1;

		// regular side effects of traversing
		int totalCost = factor * path.getWeight();

		a.addScore(totalCost);

		// move agent
		a.setLocation(destination);
		System.out.println("After moving, agent " + a.getName()
				+ "'s score is: " + a.getScore());
		*/
	}

	public void toDotFile(String filename) {

		String sep = System.getProperty("line.separator");
		String out = "graph {" + sep;

		// vertices
		for (SyrianVertex v : this.getGraph().getVertices()) {
			out += v.getNumber() + "[label = \"Vertex #" + v.getNumber() + "\\nChemicals: "
					+ v.getChemicalCount() + " \\nMilitary: "
					+ v.getEscortCount();
			for (SyrianAgent a : this.getAgentsInVertex(v)) {
				out += "\\n" + a.getName() + " (SCORE: " + a.getScore() + " | "
						+ (a.hasChemicals() ? "C" : "")
						+ (a.hasEscort() ? "E" : "") + ")";
			}
			out += "\"];" + sep;
		}

		// // agents
		// for (SyrianAgent a : this.getAgents()) {
		// out += a.getLocation().getNumber() + " [ label = \"" + a.getName()
		// + "\\nScore: " +a.getScore() + "\"];" + sep;
		// }
		for (SyrianEdge e : this.getGraph().getEdges()) {
			out += e.getV1().getNumber() + " -- " + e.getV2().getNumber()
					+ "[ label = \"" + e.getNumber() + "(W:" + e.getWeight()
					+ ", T:" + (e.hasTerrorists() ? "Y" : "N") + ")\"];" + sep;
		}

		out += "}";

		try {
			Utils.writeFile(out, filename);
		} catch (IOException e1) {
			System.err.println("Failed to print graph to file: " + filename);
			e1.printStackTrace();
		}
	}

}
