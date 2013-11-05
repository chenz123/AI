package syriangraph;

import exceptions.VertexAlreadyExistsException;
import graph.BaseGraph;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;

public class SyrianGraph extends BaseGraph<SyrianVertex, SyrianEdge> {

	private HashMap<String, Method> cmd_map;

	public SyrianGraph(AbstractCollection<SyrianVertex> vertices,
			AbstractCollection<SyrianEdge> edges) {
		super(vertices, edges);
	}

	public SyrianGraph(String filename) throws FileNotFoundException {
		super();
		// define parsing functions in hashmap
		cmd_map = new HashMap<String, Method>();
		// cmd_map.put("#V", parseVerticesDefinition());
		// cmd_map.put("#E", parsEdgeDefinition());
		// cmd_map.put("#C", parseChemicalDefinition());
		// cmd_map.put("#M", parseMilitaryDefinition());

		Scanner sc = new Scanner(new File(filename));
		System.out.println("Reading file to syrian graph!");
		while (sc.hasNext())
			this.processInitCMD(sc.next());
		// TODO CONTINUE

	}

	private void processInitCMD(String cmd) {
		System.out.println("Processing cmd:" + cmd);
		// if (cmd_map)

	}

	private Readable File(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	public AbstractCollection<SyrianEdge> getEdgesForVertex(SyrianVertex v) {

		AbstractCollection<SyrianEdge> res = new ArrayList<SyrianEdge>();
		// collect all edges for a given vertex
		for (SyrianEdge e : getEdges()) {
			if (e.getV1() == v || e.getV2() == v) {
				res.add(e);
			}
		}

		return res;
	}

	@Override
	public SyrianVertex addVertex() throws VertexAlreadyExistsException {
		SyrianVertex v = new SyrianVertex();
		this.addVertex(v);
		return v;
	}

	@Override
	public SyrianVertex addVertex(SyrianVertex v)
			throws VertexAlreadyExistsException {
		if (null == this.getVertexByNumber(v.getNumber())) {
			this.getVertices().add(v);
		}
		throw new VertexAlreadyExistsException("Vertex " + v.getNumber()
				+ " already exists in graph.");
	}

	// @Override
	// public SyrianEdge addEdge(SyrianVertex v1, SyrianVertex v2, long weight,
	// boolean blocked) throws EdgeAlreadyExistsException {
	// if (null == this.getEdgeByVertices(v1, v2)){
	// throw new EdgeAlreadyExistsException("Edge "+new SyrianEdge(v1,
	// v2).toString()+" already exists in graph.");
	// }
	// SyrianEdge e = new SyrianEdge(v1, v2, weight, blocked);
	// this.getEdges().add(e);
	// return e;
	// }

	public SyrianEdge addEdge(SyrianVertex v1, SyrianVertex v2) throws EdgeAlreadyExistsException {
		if (null == this.getEdgeByVertices(v1, v2)) {
			throw new EdgeAlreadyExistsException("Edge(" + v1.getNumber() + ","
					+ v2.getNumber() + " already exists in graph.");
		}
		SyrianEdge e = new SyrianEdge(v1, v2);
		this.getEdges().add(e);
		return e;
	}

	public SyrianEdge addEdge(SyrianVertex v1, SyrianVertex v2, long weight,
			boolean blocked) throws EdgeAlreadyExistsException {
		if (null == this.getEdgeByVertices(v1, v2)) {
			throw new EdgeAlreadyExistsException("Edge between "
					+ v1.getNumber() + " and " + v2.getNumber()
					+ " already exists in graph.");
		}
		SyrianEdge e = new SyrianEdge(v1, v2, weight, blocked);
		this.getEdges().add(e);
		return e;
	}

}
