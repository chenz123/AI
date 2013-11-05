package aiutils;

import exceptions.VertexAlreadyExistsException;
import graph.Blockable;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.Weighted;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;

import syriangraph.EdgeAlreadyExistsException;
import syriangraph.SyrianGraph;

public class GraphDefinitionParser<G extends Graph<V, E>, V extends Vertex<E, V>, E extends Edge<V, E> & Blockable & Weighted> {

	public static HashMap<String, Method> cmd_map;

	public GraphDefinitionParser() throws SecurityException, NoSuchMethodException {
		cmd_map = new HashMap<String, Method>();
		cmd_map.put("#V", this.getClass().getMethod("parseVerticesDefinition", Graph.class, int.class));
		cmd_map.put("#E", this.getClass().getMethod("parsEdgeDefinition", Graph.class, int.class, int.class, String.class, String.class));
	}

	public void parseVerticesDefinition(G graph, int number) {
		
	}
	
	public void parseEdgeDefinition(G graph, int v1, int v2, String weightString, String state){
		
		try {
			V v1v = graph.getVertexByNumber(v1);
			V v2v = graph.getVertexByNumber(v2, true);
			graph.addEdge(v1v, v2v);
		} catch (VertexAlreadyExistsException e) {
			System.out.println("Grpah creation failed. Duplicate vertex.");
			e.printStackTrace();
		} catch (EdgeAlreadyExistsException e) {
			System.out.println("Grpah creation failed. Duplicate edge.");
			e.printStackTrace();
		}
		
	}

}
