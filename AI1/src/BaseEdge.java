
public class BaseEdge<V extends Vertex> implements Edge<V>{

	private V v1, v2;
	private int weight, number;
	private static int ids = 1;
	public BaseEdge(V v1, V v2){
		this.v1 = v1;
		this.v2 = v2;
		this.number = BaseEdge.ids++;
	}
	
	@Override
	public V getV1() {
		return this.v1;
	}

	@Override
	public V getV2() {
		return this.v2;
	}

	@Override
	public int getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public V getOther(V v) {
		return v == this.getV1() ? this.getV2() : this.getV1();
	}

	@Override
	public int getNumber() {
		return this.number;
	}

	@Override
	public boolean hasVertex(V v) {
		return this.getV1() == v || this.getV2() == v;
	}

}
