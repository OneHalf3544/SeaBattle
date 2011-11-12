package ru.semikov.sea.swing;

import javax.swing.*;
import java.awt.*;

public class FrameGameLauncher {
	
	/**
	 * Launch the application.
     * @param args command line arguments. Doesn't used
     */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameModel model = new GameModel(new Dimension(15, 15), 6);
                GameFrame view = new GameFrame(model);
                view.setVisible(true);
            }
        });
	}

}
