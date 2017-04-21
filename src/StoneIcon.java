import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public abstract class StoneIcon implements Icon {
	int size;
	
	StoneIcon(int size){
		this.size = size;
	}

	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return size;
	}


	
	public static class DrawnStoneIcon extends StoneIcon{

		DrawnStoneIcon(int size) {
			super(size);
			// TODO Auto-generated constructor stub
		}
	
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			// TODO Auto-generated method stub
			Graphics2D g2 = (Graphics2D) g;
			Graphics g3 = g2.create();
			
			g2.fillOval(x, y, size, size);
			g2.setColor(Color.BLUE);
			g2.fillOval(x+size/4, y+size/4, size/2, size/2);
			
			g2.setColor(Color.BLACK);
			
		}
		
	}
	
	public static class ImageStoneIcon extends StoneIcon{
		ImageIcon imageIcon;

		ImageStoneIcon(int size, String filename) {
			super(size);
			// TODO Auto-generated constructor stub
			imageIcon = new ImageIcon(filename);
			
			Image image = imageIcon.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT);
			imageIcon.setImage(image);
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			// TODO Auto-generated method stub
			imageIcon.paintIcon(c, g, x, y);
		}
		
	}

}
