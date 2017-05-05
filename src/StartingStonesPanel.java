import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Panel to decide How many stones the game starts with
 * @author Prem Panchal
 *
 */
public class StartingStonesPanel extends JPanel
{
	/**
	 * Creates Buttons and actionlisteners that mutate the gameModel reference.
	 */
   StartingStonesPanel(){
	   JTextArea question = new JTextArea();
	   question.setText("How Many Stones per pit?");
	   this.add(question);
	   
	   JPanel buttons = new JPanel();
	   buttons.setLayout(new FlowLayout());
	   JButton threeStones = new JButton("3");
	   JButton fourStones = new JButton("4");
	   buttons.add(threeStones);
	   threeStones.addActionListener(new Controller.StartingStonesButtonListener(3));
	   buttons.add(fourStones);
	   fourStones.addActionListener(new Controller.StartingStonesButtonListener(4));
	   this.add(buttons);

   }
   
   
   @Override
	public Dimension getPreferredSize(){
		return new Dimension(600,200);
	}
}
