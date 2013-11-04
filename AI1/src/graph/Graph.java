package graph;

import java.util.AbstractCollection;

public interface Graph<V, E> {
	public AbstractCollection<V> getVertices();
	
	
	public AbstractCollection<E> getEdges();
	
	public void setEdges(AbstractCollection<E> edges);
	
	public void setVertices(AbstractCollection<V> vertices);
}
