package agroludos.views.utility;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Classe che si occupa di gestire la posizione delle componenti passate ai metodi
 * qui definiti.
 * 
 * @author Luca Suriano
 *
 */
public class PositionHandler {
	
	public static void centerComp(Component c){
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		
		final int x = (screenSize.width - c.getWidth()) / 2;
		final int y = (screenSize.height - c.getHeight()) / 2;
		
		c.setLocation(x, y);
	}
	
	public static void setNegOffset(Component c, int amount){
		int x = c.getX();
		int y = c.getY();
		c.setLocation(x - amount, y);
	}
}
