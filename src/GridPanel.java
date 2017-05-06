import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**

 * Creates a panel with a Gridlayout storing PitPanels
 * @author Prem Panchal & Andrew Jong
 *
 */

public class GridPanel extends JPanel
{
	private PitPanel[] p1Pits;
	private PitPanel[] p2Pits;

	GridPanel() {
		this(new StoneIcon.ImageStoneIcon(30,"images/white_stone.png"));
	}

	GridPanel(StoneIcon icon)
	{
		this.setLayout(new GridLayout(2, 6));

		p1Pits = new PitPanel[6];
		p2Pits = new PitPanel[6];
		for(int i = 0; i<6;i++)
		{
			PitPanel pit = new PinkPitPanel(icon, 4);
			pit.setSize(100, 100);
			p1Pits[i] = pit;
			pit.addMouseListener(new Controller.PitPanelListener(Side.P1, 5-i, pit));
			this.add(pit);
		}
		for(int i=0; i<6;i++)
		{
			PitPanel pit = new PinkPitPanel(icon, 4);
			pit.setSize(100, 100);
			p2Pits[i] = pit;
			pit.addMouseListener(new Controller.PitPanelListener(Side.P2, i, pit));
			this.add(pit);
		}		
	}	
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(900,300);
	}
	
	public PitPanel[] getP1Pits()
	{
		return p1Pits;
	}
	public PitPanel[] getP2Pits()
	{
		return p2Pits;
	}

	public void setP1Pits(PinkPitPanel[] p1Pits) {
		this.p1Pits = p1Pits;
	}

	public void setP2Pits(PinkPitPanel[] p2Pits) {
		this.p2Pits = p2Pits;
	}
	
}
