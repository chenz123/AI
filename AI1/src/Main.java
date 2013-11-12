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
		
		s.addAgent(new SyrianGreedyAgent("Greedy 1", s
				.getGraph().getVertexByNumber(16), s.getGraph()
				.getVertexByNumber(4)));
		
		s.addAgent(new SyrianDumbBestFirstSearchAgent("Dumb Target Chaser 1", s
				.getGraph().getVertexByNumber(16), s.getGraph()
				.getVertexByNumber(4)));
		
		// prepare for a new run output
		try {
			Runtime.getRuntime().exec(new String[]{"/bin/bash","-c", "rm *.jpg"}).waitFor();
			Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", "rm *.dot"}).waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//long turn = System.currentTimeMillis();

		try {
			// while (s.agentsHaveMovesLeft()) {
			while (!s.getAgents().isEmpty()) {
				//s.toDotFile("currentTurn.dot");
				//s.toDotFile("Turn" + (turn++) + ".dot");
				s.moveAgents();

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (NoAgentsInSimulationException e) {
			System.out.println("Simulation Done!");
			s.printScores();
		}
		//s.toDotFile("currentTurn.dot");
		//s.toDotFile("Turn" + turn + ".dot");
		s.printScores();

		// prepare for a new run output
		int i = 0;
		try {
			System.out.println("Running commands to generate pictures of agents' moves");
			Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", "for i in *.dot; do dot -Tjpg \"$i\" > \"$i\".jpg; done"}).waitFor();
			Runtime.getRuntime().exec(new String[]{"/bin/bash", "-c", "rm *.dot"}).waitFor();
			System.out.println("Finished generating jpgs of agents' moves");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		for (SyrianAgent a : s.getAgents()) {
			System.out.println("Agent " + a.getName()
					+ " finished with score: " + a.getScore());
		}

		System.out.println("Simulation ended");

	}
}
