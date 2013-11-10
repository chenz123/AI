import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class BaseGraph<V extends Vertex, E extends Edge<V>>
		implements Graph<V, E> {

	private AbstractCollection<E> edges;
	private AbstractCollection<V> vertices;

	public BaseGraph(AbstractCollection<V> vertices, AbstractCollection<E> edges) {
		this.edges = edges;
		this.vertices = vertices;
	}

	@Override
	public E addEdge(E e) throws EdgeAlreadyInGraphException {
		if (!this.edges.contains(e)) {
			this.edges.add(e);
			return e;
		}

		throw new EdgeAlreadyInGraphException("Edge " + e.getNumber()
				+ " is already in the graph");
	}

	@Override
	public V addVertex(V v) throws VertexAlreadyInGraphException {
		if (!this.vertices.contains(v)) {
			this.vertices.add(v);
			return v;
		}

		throw new VertexAlreadyInGraphException("Vertex " + v.getNumber()
				+ " is already in the graph");

	}

	@Override
	public AbstractCollection<E> getAllEdgesForVertex(V v) {
		AbstractCollection<E> result = new ArrayList<E>();

		for (E e : this.edges) {
			if (e.hasVertex(v)) {
				result.add(e);
			}
		}

		return result;
	}

	@Override
	public V getVertexByNumber(int number) {
		for (V v : this.getVertices()) {
			if (v.getNumber() == number) {
				return v;
			}
		}
		return null;
	}

	public AbstractCollection<E> getEdges() {
		return this.edges;
	}

	public AbstractCollection<V> getVertices() {
		return this.vertices;
	}

	public HashMap<String, HashMap<Integer,Integer>> shortestPathsAll(V src) {

		// an implementation of Dijkstra's shortest path algorithm
		HashMap<Integer, Integer> distances = new HashMap<Integer, Integer>();
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		HashMap<Integer, Integer> previous = new HashMap<Integer, Integer>();
		HashMap<String, HashMap<Integer,Integer>> res = new HashMap<String, HashMap<Integer,Integer>>();
		for (V v : this.getVertices()) {
			distances.put(v.getNumber(), Integer.MAX_VALUE);
			visited.put(v.getNumber(), false);
			previous.put(v.getNumber(), null);
		}
		ArrayList<V> notVisited = new ArrayList<V>();
		// start from source, distance to itself = 0
		distances.put(src.getNumber(), 0);
		notVisited.add(src);
		while (!notVisited.isEmpty()) {
			// find closest vertex that WASN'T visited...
			V current = notVisited.get(0);
			for (V v : notVisited) {
				if (distances.get(v.getNumber()) < distances.get(current.getNumber())
						|| (distances.get(v.getNumber()) == distances.get(current.getNumber()) && v
								.getNumber() < current.getNumber())) {

				}
			}	
			notVisited.remove(current);
			// closest vertex is now in "current"
			visited.put(current.getNumber(), true);
			
			for (E e : this.getAllEdgesForVertex(current)) {
				// neightbor node is the other vertex on this edge
				V neighbour = e.getOther(current);
				int alternatePath = distances.get(current.getNumber()) + e.getWeight();
				if (alternatePath < distances.get(neighbour.getNumber())
						&& !visited.get(neighbour.getNumber())) {
					distances.put(neighbour.getNumber(),  alternatePath);
					previous.put(neighbour.getNumber(), current.getNumber());
					notVisited.add(neighbour);
				}
			}
		}
		res.put("distances", distances);
		res.put("previous", previous);
		return res;
	}

	public AbstractCollection<E> getEdgesFor(V v1, V v2){
		AbstractCollection<E> result = new ArrayList<E>();
		for (E e : this.getEdges()){
			if (e.hasVertex(v1) && e.hasVertex(v2)){
				result.add(e);
			}
		}
		return result;
	}
}
