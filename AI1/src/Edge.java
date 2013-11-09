
public interface Edge<V extends Vertex> {

	public V getV1();
	public V getV2();
	public int getWeight();
	public void setWeight(int weight);
	public V getOther(V v);
	public int getNumber();
	public boolean hasVertex(V v);
}
