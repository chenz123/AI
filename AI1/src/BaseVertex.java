
public class BaseVertex<V extends Vertex> implements Vertex{

	private int number;
	private static int ids = 1;
	private String color;
	
	public BaseVertex(){
		this.number = BaseVertex.ids++;
	}
	
	@Override
	public int getNumber() {
		return this.number;
	}

	@Override
	public String getColor() {
		return this.color;
	}

	@Override
	public void setColor(String color) {
		this.color = color;
	}
}
