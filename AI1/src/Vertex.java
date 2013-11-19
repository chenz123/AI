/**
 * the Vertex class is used to generate graphs and
 * describe an Edge's endpoints, usually with various properties 
 * @author reuts
 *
 */
public interface Vertex{

	/**
	 * get the vertex number
	 * @return
	 * 			the vertex number
	 */
	public int getNumber();
	
	/**
	 * get this vertex's color
	 * @return
	 * 			this vertex's color
	 */
	public String getColor();
	
	/**
	 * set this vertex's color
	 * @param color
	 * 			this vertex's color
	 */
	public void setColor(String color);
}
