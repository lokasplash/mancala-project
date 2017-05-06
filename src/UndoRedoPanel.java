import javax.swing.*;

/**
 * @author Vincent Diep
 */
public class UndoRedoPanel extends JPanel {
	private JButton undoButton;
	private JButton redoButton;

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

	public void setUndosLeft(int left) {
		undoButton.setText("Undo (" + left + " left this turn)");

	}

	public void setRedosLeft(int left) {
		redoButton.setText("Redo (" + left + ")");
	}

	public void setCanUndo(boolean canUndo) {
		undoButton.setEnabled(canUndo);
	}

	public void setCanRedo(boolean canRedo) {
		redoButton.setEnabled(canRedo);
	}
}
