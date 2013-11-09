import java.util.AbstractCollection;
import java.util.ArrayList;


public abstract class BaseGraph<V extends Vertex, E extends Edge<V>> implements Graph<V, E>{

	private AbstractCollection<E> edges;
	private AbstractCollection<V> vertices;
	
	public BaseGraph(AbstractCollection<V> vertices, AbstractCollection<E> edges){
		this.edges = edges;
		this.vertices = vertices;
	}
	@Override
	public E addEdge(E e) throws EdgeAlreadyInGraphException {
		if (!this.edges.contains(e)){
			this.edges.add(e);
			return e;
		}
		
		throw new EdgeAlreadyInGraphException("Edge "+e.getNumber() + " is already in the graph");
	}

	@Override
	public V addVertex(V v) throws VertexAlreadyInGraphException {
		if (!this.vertices.contains(v)){
			this.vertices.add(v);
			return v;
		}
		
		throw new VertexAlreadyInGraphException("Vertex "+v.getNumber() + " is already in the graph");
		
	}

	@Override
	public AbstractCollection<E> getAllEdgesForVertex(V v) {
		AbstractCollection<E> result = new ArrayList<E>();
		
		for (E e : this.edges){
			if (e.hasVertex(v)){
				result.add(e);
			}
		}
		
		return result;
	}
	
	@Override
	public V getVertexByNumber(int number){
		for (V v : this.getVertices()){
			if (v.getNumber() == number){
				return v;
			}
		}
		return null;
	}
	
	public AbstractCollection<E> getEdges(){
		return this.edges;
	}
	
	public AbstractCollection<V> getVertices(){
		return this.vertices;
	}
	
}
