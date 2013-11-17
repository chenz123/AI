import java.io.IOException;

import aiutils.Utils;

public class SyrianSimulation extends
		BaseSimulation<SyrianGraph, SyrianAgent, SyrianVertex, SyrianEdge> {

	public static final int CROSSING_TERRORISTS_WITH_CHEMICALS_PENALTY = 1000;

	public SyrianSimulation(SyrianGraph g) {
		super(g);
		// TODO Auto-generated constructor stub
	}

	public String toDotFile(String filename) {

		String sep = System.getProperty("line.separator");
		String out = "graph {" + sep;

		// vertices
		for (SyrianVertex v : this.getGraph().getVertices()) {
			out += v.getNumber()
					+ "["
					+ (v.hasEscort() ? "shape=pentagon " : "")
					+ (v.getColor() == null ? "" : "style=filled fillcolor="
							+ v.getColor())
					+ (v.hasChemicals() ? " fontcolor=green" : "")
					+ " label = \"Vertex #" + v.getNumber() + "\\nChemicals: "
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
			out += e.getV1().getNumber() + " -- " + e.getV2().getNumber() + "["
					+ (e.hasColor() ? "color=" + e.getColor() : "")
					+ (e.hasTerrorists() ? " fontcolor=red " : "")
					+ " penwidth=3 label = \"" + e.getNumber() + "(W:"
					+ e.getWeight() + ", T:" + (e.hasTerrorists() ? "Y" : "N")
					+ ")\"];" + sep;
		}

		out += "}";

		try {
			Utils.writeFile(out, filename);
		} catch (IOException e1) {
			System.err.println("Failed to print graph to file: " + filename);
			e1.printStackTrace();
		}
		return filename;
	}

	@Override
	public void moveAgents() {
		// TODO Auto-generated method stub
		System.out.println("Dont use this!");
		System.out.println("Dont use this!");
	}
}
