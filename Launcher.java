package kim.halme.vrliput;

import javax.swing.SwingUtilities;

public class Launcher {
	
	private static MainGUI guiMain;
	private static Program prog;
	
	public static void main(String[] args) {
		
		prog = new Program();
		
		SwingUtilities.invokeLater(() -> {guiMain = new MainGUI(prog); guiMain.getFrame().setVisible(true);});
		
		
	}

}
