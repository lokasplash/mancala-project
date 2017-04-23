import java.awt.BorderLayout;

import javax.swing.JPanel;

public class BoardPanel extends JPanel{

	JPanel left;
	JPanel right;
	JPanel center;
	


	BoardPanel(){
		left = new JPanel();
		right = new JPanel();
		center = new JPanel();
		
		this.setLayout(new BorderLayout());
		
		this.add(left, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);
		this.add(center, BorderLayout.CENTER);

	}
	
	
}
