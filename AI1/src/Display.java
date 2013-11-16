import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public interface Display<S extends Simulation<G, A, V, E>, G extends Graph<V, E>, A extends Agent<G, V, E>, V extends Vertex, E extends Edge<V>>
		extends ActionListener, KeyListener {

	public void loadPicture(String path);

	public S getSimulation();

	public void setSimulation(S simulation);

	public void playNextMove();
	
	public void zoomIn();

	public void zoomOut();
}
