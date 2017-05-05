import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by andrew on 5/5/17.
 */
public abstract class PitPanel extends JPanel {
	protected int numStones = 4;
	StoneIcon stoneType;

	PitPanel(StoneIcon stoneType) {
		this.stoneType = stoneType;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//draw the background
		drawBackground(g2);
		// draw the pit
		drawPit(g2);
		// Draw the num stones
		drawStones(g2);
	}

	protected abstract void drawStones(Graphics2D g2);

	protected abstract void drawPit(Graphics2D g2);

	protected abstract void drawBackground(Graphics2D g2);

	public int getNumStones() {
		return numStones;
	}

	public void setNumStones(int numStones) {
		this.numStones = numStones;
	}

	protected Shape pit;


	public Shape getShape() {
		return pit;
	}


	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}

}
