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

//		 s.addAgent(new SyrianHumanAgent("Human 1",
//		 s.getGraph().getVertexByNumber(1),
//		 s.getGraph().getVertexByNumber(4)));
//		 s.addAgent(new SyrianTerroristBusterAgent("Terrorist Buster 1", s
//		 .getGraph().getVertexByNumber(2), s.getGraph()
//		 .getVertexByNumber(4)));
		
//		 s.addAgent(new SyrianTerroristBusterAgent("Terrorist Buster 2", s
//		 .getGraph().getVertexByNumber(10), s.getGraph()
//		 .getVertexByNumber(4)));
//		
//		 s.addAgent(new SyrianGreedyAgent("Greedy 1", s.getGraph()
//		 .getVertexByNumber(16), s.getGraph().getVertexByNumber(4)));

		s.addAgent(new AStarSyrianHeuristicAgent(
				"Heuristic 1", s.getGraph().getVertexByNumber(1),
						s.getGraph().getVertexByNumber(4), s.getGraph()));
//
//		s.addAgent(new BaseSyrianHeuristicAgent(
//				"Heuristic 1", s.getGraph().getVertexByNumber(16),
//						s.getGraph().getVertexByNumber(4), s.getGraph()));
		//
		// s.addAgent(new SyrianMaxExpandHeuristicAgent("Dumb Target Chaser 1",
		// .getGraph().getVertexByNumber(16), s.getGraph()
		// .getVertexByNumber(4)));

		// prepare for a new run output
		try {
			Runtime.getRuntime()
					.exec(new String[] { "/bin/bash", "-c", "rm *.jpg" })
					.waitFor();
			Runtime.getRuntime()
					.exec(new String[] { "/bin/bash", "-c", "rm *.dot" })
					.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// long turn = System.currentTimeMillis();

		BaseDisplay<SyrianSimulation, SyrianGraph, SyrianAgent, SyrianVertex, SyrianEdge> d = new BaseDisplay<SyrianSimulation, SyrianGraph, SyrianAgent, SyrianVertex, SyrianEdge>(
				s);

		//d.loadPicture(s.toDotFile("start.dot"));
		d.repaint();
		d.pack();
		d.setSize(500, 500);

	}
}
