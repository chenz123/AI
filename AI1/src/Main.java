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

		s.addAgent(new SyrianHumanAgent("Human Syrian Agent 1"), s.getGraph().getVertexByNumber(1));
		s.addAgent(new SyrianTerroristBusterAgent("Syrian Terrorist Buster Agent"), s.getGraph().getVertexByNumber(2));
		int turn = 0;
		
		while (s.agentsHaveMovesLeft()) {
			s.toDotFile("currentTurn.dot");
			s.toDotFile("Turn"+(turn++)+".dot");
			s.moveAgents();
		}
		s.toDotFile("currentTurn.dot");
		s.toDotFile("Turn"+turn+".dot");
		
		for (SyrianAgent a : s.getAgents()){
			System.out.println("Agent " + a.getName() + " finished with score: " + a.getScore()); 
		}

		System.out.println("Simulation ended");

	}
}
