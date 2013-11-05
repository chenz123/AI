package syriangraph;

import exceptions.VertexAlreadyExistsException;
import graph.BaseGraph;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SyrianGraph extends BaseGraph<SyrianVertex, SyrianEdge> {

	private HashMap<String, Method> cmd_map;

	public SyrianGraph(AbstractCollection<SyrianVertex> vertices,
			AbstractCollection<SyrianEdge> edges) {
		super(vertices, edges);
	}

	public void parseMilitaryDefinition(String unitsNum, String vNum) {
		System.out.println("Parsing military: ");
		System.out.println("Got " + unitsNum + " units");
		System.out.println("Units are at: " + vNum);
	}

	public void parseChemicalDefinition(String unitsNum, String vNum) {
		System.out.println("Parsing chemicals: ");
		System.out.println("Got " + unitsNum + " units");
		System.out.println("Units are at: " + vNum);
	}

	public void parseSyrianEdgeFromFile(String v1, String v2, String weight,
			String blocked) throws NumberFormatException, EdgeAlreadyExistsException {
		
		System.out.println("Got syrian edge: v1:" + v1 + " v2:" + v2 + " w:"
				+ weight + " b:" + blocked);
		System.out.println("Looking for vertices:");
		// get vertices from graph
		SyrianVertex v1v = this.getVertexByNumber(Integer.parseInt(v1));
		System.out.println("Got vertex: "+v1);
		SyrianVertex v2v = this.getVertexByNumber(Integer.parseInt(v2));
		System.out.println("Got vertex: "+v2);
		// add edge connecting vertices
		SyrianEdge e = this.addEdge(v1v, v2v);
		System.out.println("Added edge:" + e.toString());
		// add weight to syrian vertex
		e.setWeight(Integer.parseInt(weight.substring(1)));
		// set edge to blocked if needed
		e.setBlocked("B".equalsIgnoreCase(blocked) ? true : false);
	}

	public SyrianGraph(String filename) throws FileNotFoundException {
		super();
		// define parsing functions in hashmap
		cmd_map = new HashMap<String, Method>();
		try {
			cmd_map.put("#V", BaseGraph.class.getMethod(
					"parseVerticesFromfile", String.class));
			System.out.println("Added method at "
					+ cmd_map.get("#V").toString() + " to cmd_map");
			cmd_map.put(
					"#E",
					this.getClass().getMethod("parseSyrianEdgeFromFile",
							String.class, String.class, String.class,
							String.class));
			System.out.println("Added method at "
					+ cmd_map.get("#E").toString() + " to cmd_map");
			cmd_map.put(
					"#C",
					this.getClass().getMethod("parseChemicalDefinition",
							String.class, String.class));
			System.out.println("Added method at "
					+ cmd_map.get("#C").toString() + " to cmd_map");
			cmd_map.put(
					"#M",
					this.getClass().getMethod("parseMilitaryDefinition",
							String.class, String.class));
			System.out.println("Added method at "
					+ cmd_map.get("#M").toString() + " to cmd_map");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		Scanner sc = new Scanner(new File(filename));
		System.out.println("Reading file to syrian graph!");
		while (sc.hasNext())
			try {
				this.processInitCMD(sc.next(), sc);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		// TODO CONTINUE

	}

	private void processInitCMD(String cmd, Scanner sc)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		System.out.println("Processing cmd:" + cmd);
		Method m = cmd_map.get(cmd);
		// get array of argument types
		Class<?> pTypeArr[] = m.getParameterTypes();
		// create array for arguments
		Object params[] = new Object[pTypeArr.length];

		for (int p = 0; p < m.getParameterTypes().length; p++) {
			// replace argument type with actual argument!
			params[p] = (pTypeArr[p].cast(sc.next()));
		}
		// run method with argument list
		m.invoke(this, params);
		// move line
		sc.nextLine();

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
		this.getVertices().add(v);
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

	public SyrianEdge addEdge(SyrianVertex v1, SyrianVertex v2)
			throws EdgeAlreadyExistsException {
		if (null != this.getEdgeByVertices(v1, v2)) {
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
