import javax.swing.*;

/**
 * Created by andrew on 4/22/17.
 */
public class UndoRedoPanel extends JPanel {
	JButton undoButton;
	JButton redoButton;

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

	public void setUndoButton(boolean canUndo) {
		if (canUndo == true) {
			undoButton.setEnabled(true);
		} else {
			undoButton.setEnabled(false);
		}
	}

	public void setRedoButton(boolean canRedo) {
		if (canRedo == true) {
			redoButton.setEnabled(true);
		} else {
			redoButton.setEnabled(false);
		}
	}
}
