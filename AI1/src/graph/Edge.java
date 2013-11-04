package graph;


public interface Edge<T extends Vertex<?>> {
	
	public void setNumber(int number);
	
	public void setName(String name);
	
	public void setV1(T v1);
	
	public void setV2(T v2);
	
	public int getNumber();
	
	public String getName();
	
	public T getV1();
	
	public T getV2();
}
