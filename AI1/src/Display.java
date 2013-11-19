
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
/**
 * This is the not-so-good display class that should be improved ASAP.
 * It's sole duty is to display the simulation (preferably graphically)
 * 
 * @author Reut Sharabani & Chen Zrubavel
 *
 * @param <S> - the simulation type to display
 * @param <G> - the graph the simulation is based on
 * @param <A> - the agent family the simulation is based on
 * @param <V> - the vertex this simulation's graph uses
 * @param <E> - the edge this simulation's graph uses
 */
public interface Display<S extends Simulation<G, A, V, E>, G extends Graph<V, E>, A extends Agent<G, V, E>, V extends Vertex, E extends Edge<V>>
		extends ActionListener, KeyListener {

	public void loadPicture(String path);

	public S getSimulation();

	public void setSimulation(S simulation);

	public void playNextMove();
	
	public void zoomIn();

	public void zoomOut();
}
