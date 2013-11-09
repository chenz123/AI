
public class BaseVertex implements Vertex{

	private int number;
	private static int ids = 1;
	
	
	public BaseVertex(){
		this.number = BaseVertex.ids++;
	}
	
	@Override
	public int getNumber() {
		return this.number;
	}

}
