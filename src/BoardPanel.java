import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class BoardPanel extends JPanel{

	JPanel left;
	JPanel right;
	JPanel center;
	
	/**
	 * How much of the width of a BoardPanel an InternalBoardPanel should take up
	 */
	private static final double RATIO = 0.30;



	BoardPanel(){
//		left = new JPanel();
//		right = new JPanel();
		
		left = new InternalBoardPanel();
		right = new InternalBoardPanel();
		center = new JPanel();
		
		// this makes stretching of panels occur
		left.setLayout(new BorderLayout());
		right.setLayout(new BorderLayout());
		center.setLayout(new BorderLayout());
		
		this.setLayout(new BorderLayout());
		
		this.add(left, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);
		this.add(center, BorderLayout.CENTER);

	}
	
	public class InternalBoardPanel extends JPanel{
		
		@Override
		public Dimension getPreferredSize() {
			Dimension parent = this.getParent().getSize();
			double x = parent.width * RATIO;
			
			return new Dimension((int) x, this.getHeight());
		}
	}
	
	
}
