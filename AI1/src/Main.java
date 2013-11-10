import java.io.IOException;

public class Main {

	public static void main(String args[]) {

		System.out.println("Starting simulation...");

		SyrianSimulation s;
		String filename = "graph1.graph";
		try {
			s = new SyrianSimulation(new SyrianGraph(filename));
		} catch (IOException e) {
			System.out.println("Error reading graph file " + filename + "!");
			e.printStackTrace();
			return;
		}

		// s.addAgent(new SyrianHumanAgent("Human 1",
		// s.getGraph().getVertexByNumber(1),
		// s.getGraph().getVertexByNumber(4)));
		s.addAgent(new SyrianTerroristBusterAgent("Terrorist Buster 1", s
				.getGraph().getVertexByNumber(2), s.getGraph()
				.getVertexByNumber(4)));

		s.addAgent(new SyrianTerroristBusterAgent("Terrorist Buster 2", s
				.getGraph().getVertexByNumber(10), s.getGraph()
				.getVertexByNumber(4)));
		long turn = System.currentTimeMillis();

		try {
			// while (s.agentsHaveMovesLeft()) {
			while (!s.getAgents().isEmpty()) {
				s.toDotFile("currentTurn.dot");
				s.toDotFile("Turn" + (turn++) + ".dot");
				s.moveAgents();

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (NoAgentsInSimulationException e) {
			System.out.println("Simulation Done!");
			s.printScores();
		}
		s.toDotFile("currentTurn.dot");
		s.toDotFile("Turn" + turn + ".dot");
		s.printScores();

		for (SyrianAgent a : s.getAgents()) {
			System.out.println("Agent " + a.getName()
					+ " finished with score: " + a.getScore());
		}

		System.out.println("Simulation ended");

	}
}
