import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import aiutils.Utils;

public class SyrianGraph extends BaseGraph<SyrianVertex, SyrianEdge> {

	public SyrianGraph(String filename) throws IOException {

		super(new ArrayList<SyrianVertex>(), new ArrayList<SyrianEdge>());

		System.out.println("Reading File: " + filename);
		String contents = Utils.readFile(filename);
		System.out.println("Contents: " + contents);
		Scanner sc = new Scanner(contents);
		while (sc.hasNext()) {
			String current = sc.next();
			// look for graph order
			if (current.startsWith("#")) {
				switch (current.charAt(1)) {
				// this is an order to create vertices
				case ('V'):
					// create vertices
					int verticesCount = Integer.parseInt(sc.next());
					while (verticesCount-- > 0) {
						SyrianVertex v = this.addVertex();
						System.out.println("Created vertex #" + v.getNumber());
					}
					break;
				case ('E'):
					// this is an order to create an edge
					SyrianVertex startVertex = this.getVertexByNumber(Integer
							.parseInt(sc.next()));
					SyrianVertex endVertex = this.getVertexByNumber(Integer
							.parseInt(sc.next()));
					int weight = Integer.parseInt(sc.next().substring(1));
					boolean blocked = sc.next().equalsIgnoreCase("B") ? true
							: false;

					SyrianEdge newEdge = this.addEdge(startVertex, endVertex,
							weight, blocked);
					System.out.println("Added edge " + newEdge.toString());
					break;
				case ('C'):
					// this is an order to place chemicals
					int vertexNumber1 = Integer.parseInt(sc.next());
					int chemicalsCount = Integer.parseInt(sc.next());
					this.getVertexByNumber(vertexNumber1).addChemicals(
							chemicalsCount);
					System.out.println("Added " + chemicalsCount
							+ " chemicals to vertex #" + vertexNumber1);
					break;
				case ('M'):
					// this is an order to place military escorts
					int vertexNumber2 = Integer.parseInt(sc.next());
					int militaryCount = Integer.parseInt(sc.next());
					this.getVertexByNumber(vertexNumber2).addEscort(
							militaryCount);
					System.out.println("Added " + militaryCount
							+ " miltary units to vertex #" + vertexNumber2);
					break;
				}
			} else {
				// this is not recognized
				// skip
			}
		}
		sc.close();
		System.out.println("Loading simulation from file: " + filename);
	}

	private SyrianEdge addEdge(SyrianVertex startVertex,
			SyrianVertex endVertex, int weight, boolean blocked) {
		SyrianEdge newEdge = new SyrianEdge(startVertex, endVertex, weight,
				blocked);
		this.getEdges().add(newEdge);
		return newEdge;

	}

	@Override
	public SyrianVertex addVertex() {
		SyrianVertex newVertex = new SyrianVertex();
		this.getVertices().add(newVertex);
		return newVertex;
	}


}
