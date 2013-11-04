package graph;

public class BaseEdge<T extends Vertex<?>> implements Edge<T>{

	private int number;
	private String name;
	private T v1, v2;
	
	public BaseEdge(int number){
		this.setNumber(number);
	}
	
	@Override
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setV1(T v1) {
		this.v1 = v1;
	}

	@Override
	public void setV2(T v2) {
		this.v2 = v2;
	}

	@Override
	public int getNumber() {
		return this.number;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public T getV1() {
		return this.v1;
	}

	@Override
	public T getV2() {
		return this.v2;
	}

}
