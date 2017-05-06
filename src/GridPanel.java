import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

/**
 * Creates a panel with a Gridlayout storing PitPanels
 * @author Prem Panchal & Andrew Jong
 */

public class GridPanel extends JPanel {
	private ArrayList<PitPanel> p1Pits;
	private ArrayList<PitPanel> p2Pits;

	GridPanel() {
		this(new StoneIcon.ImageStoneIcon(30, "images/white_stone.png"), 6, 4);
	}

	GridPanel(StoneIcon icon, int numPitsPerSide, int startingStones) {
		this.setLayout(new GridLayout(2, numPitsPerSide));

		p1Pits = new ArrayList<>(numPitsPerSide);
		p2Pits = new ArrayList<>(numPitsPerSide);
		for (int i = numPitsPerSide - 1; i >= 0; i--) {
			PitPanel pit = new PinkPitPanel(icon, startingStones);
			pit.setSize(100, 100);
			p1Pits.add(pit);
			pit.addMouseListener(new Controller.PitPanelListener(Side.P1, i, pit));
			this.add(pit);
		}
		Collections.reverse(p1Pits);
		for (int i = 0; i < numPitsPerSide; i++) {
			PitPanel pit = new PinkPitPanel(icon, startingStones);
			pit.setSize(100, 100);
			p2Pits.add(pit);
			pit.addMouseListener(new Controller.PitPanelListener(Side.P2, i, pit));
			this.add(pit);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 200);
	}

	public void setP1Pits(int[] stonesInPits) {
		for (int i = 0; i < stonesInPits.length; i++) {
			p1Pits.get(i).setStones(stonesInPits[i]);
		}
	}

	public void setP2Pits(int[] stonesInPits) {
		for (int i = 0; i < stonesInPits.length; i++) {
			p2Pits.get(i).setStones(stonesInPits[i]);
		}
	}

}
