import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BaseDisplay<S extends Simulation<G, A, V, E>, G extends Graph<V, E>, A extends Agent<G, V, E>, V extends Vertex, E extends Edge<V>>
		extends JFrame implements Display<S, G, A, V, E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private S simulation;
	private JPanel panel;

	private double scaleFactor;
	private String imagePath;

	public BaseDisplay(S sim) {

		super("Syrian Agent Simulation");
		this.panel = new JPanel(new BorderLayout());
		this.addKeyListener(this);
		this.simulation = sim;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(500, 500);
		this.setContentPane(this.panel);
		this.setVisible(true);
		this.scaleFactor = 1;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		switch (arg0.getKeyChar()) {
		case 'n':
			// next pressed
			System.out.println("Playing next move");
			this.playNextMove();
			break;
		case '+':
			System.out.println("Zooming in");
			this.zoomIn();
			this.loadPicture(this.imagePath);
			break;
		case '-':
			System.out.println("Zooming out");
			this.zoomOut();
			this.loadPicture(this.imagePath);
			break;

		}
	}

	@Override
	public void loadPicture(String path) {
		this.imagePath = path;
		this.panel.removeAll();

		ImageIcon image = new ImageIcon(path);
		
		System.out.println("Height: " + (int)(image.getIconWidth() * this.scaleFactor));
		System.out.println("Width: " + (int)(image.getIconHeight() * this.scaleFactor));
		
		Image scaledImage = image.getImage().getScaledInstance(
				(int)(image.getIconWidth() * this.scaleFactor),
				(int)(image.getIconHeight() * this.scaleFactor), Image.SCALE_DEFAULT);
		this.panel.add(new JLabel("", new ImageIcon(scaledImage), JLabel.CENTER),
				BorderLayout.CENTER);
		this.panel.repaint();
		System.out.println("Loading picture from :  " + path);
		this.pack();
		this.panel.setSize(500, 500);
		this.setSize(500, 500);
	}

	@Override
	public S getSimulation() {
		return this.simulation;
	}

	@Override
	public void playNextMove() {
		String path = this.simulation.moveAgent();

		System.out.println("Generating jpg from dot file " + path);
		String newPath = path + ".jpg";
		try {
			Runtime.getRuntime()
					.exec(new String[] { "/bin/bash", "-c",
							"dot -Tjpg \"" + path + "\" > \"" + newPath + "\"" })
					.waitFor();
		} catch (InterruptedException | IOException e) {
			System.out
					.println("Error while generating picture for move! Sorry...");
			e.printStackTrace();
		}

		this.loadPicture(newPath);

	}

	@Override
	public void setSimulation(S simulation) {
		this.simulation = simulation;
	}

	@Override
	public void zoomIn() {
		this.scaleFactor /= 0.9;
	}

	@Override
	public void zoomOut() {
		this.scaleFactor /= 1.1;
	}
}
