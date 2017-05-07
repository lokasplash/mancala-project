import javax.swing.*;

/**
 * @author Vincent Diep & Andrew Jong
 */
public class UndoRedoPanel extends JPanel {
	private JButton undoButton;
	private JButton redoButton;

	/**
	 * Constructor creates an undo redo panel with both undo and redo set to disabled by default. Adds the
	 * appropriate action listener to each button.
	 */
	UndoRedoPanel() {

		undoButton = new JButton("Undo");
		redoButton = new JButton("Redo");
		undoButton.setEnabled(false);
		redoButton.setEnabled(false);

		undoButton.addActionListener(new Controller.UndoListener());
		redoButton.addActionListener(new Controller.RedoListener());

		this.add(undoButton);
		this.add(redoButton);
	}

	/**
	 * Set the number of undos left this turn
	 * @param left num undos left
	 */
	public void setUndosLeft(int left) {
		undoButton.setText("Undo (" + left + " left this turn)");

	}

	/**
	 * Set the number of redos left.
	 * @param left num redos left
	 */
	public void setRedosLeft(int left) {
		redoButton.setText("Redo (" + left + ")");
	}


	/**
	 * Set whether the undo button is enabled
	 * @param canUndo true or false
	 */
	public void setCanUndo(boolean canUndo) {
		undoButton.setEnabled(canUndo);
	}

	/**
	 * Set whether the redo button is enabled
	 * @param canRedo t/f
	 */
	public void setCanRedo(boolean canRedo) {
		redoButton.setEnabled(canRedo);
	}
}
